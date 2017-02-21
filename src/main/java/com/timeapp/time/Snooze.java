package com.timeapp.time;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class Snooze extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private TextView alarmText;
    private TextView stopAlarm;
    private TextView snooze;
    private TextClock time;
    private TextClock ampm;

    private RelativeLayout background;
    private RelativeLayout tutorial;

    private GestureDetectorCompat gDetector;

    Animation fadeIn;
    Animation fadeOut;

    private int snoozeLength;
    private int stopAfter;
    private int delay;
    private int minutes;
    private int pickHour;
    private int pickMinute;
    private int theme;

    private String notifTime;
    private String notifAmpm;

    private Vibrator v;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    private Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        pickHour = sp.getInt("pickHour", 0);
        pickMinute = sp.getInt("pickMinute", 0);

        theme = sp.getInt("theme", 0);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 1000, 1000};

        if (AddAlarm.vibrateCheck) {
            v.vibrate(pattern, 0);
        }

        snoozeLength = sp.getInt("snoozeLength", 3);
        stopAfter = sp.getInt("stopAfter", 3);

        if (stopAfter == 1) {
            delay = 2 * 60 * 1000;
        } else if (stopAfter == 2) {
            delay = 5 * 60 * 1000;
        } else if (stopAfter == 3) {
            delay = 10 * 60 * 1000;
        } else if (stopAfter == 4) {
            delay = 15 * 60 * 1000;
        } else if (stopAfter == 5) {
            delay = 30 * 60 * 1000;
        }

        background = (RelativeLayout) findViewById(R.id.background);

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");
        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));
        tutorial = (RelativeLayout) findViewById(R.id.tutorial);

        alarmText = (TextView) findViewById(R.id.alarm_text);
        alarmText.setTypeface(oswaldLight);
        alarmText.setText(sp.getString("alarmTitle", null));
        stopAlarm = (TextView) findViewById(R.id.stop_alarm);
        stopAlarm.setTypeface(oswaldLight);
        snooze = (TextView) findViewById(R.id.snooze);
        snooze.setTypeface(oswaldLight);
        time = (TextClock) findViewById(R.id.time);
        time.setTypeface(oswaldRegular);
        ampm = (TextClock) findViewById(R.id.ampm);
        ampm.setTypeface(oswaldRegular);

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        this.gDetector = new GestureDetectorCompat(this, this);
        gDetector.setOnDoubleTapListener(this);
        gDetector.setIsLongpressEnabled(true);

        if (stopAfter <= 5) {
            t = new Timer();
            t.schedule(new TimerTask() {

                           @Override
                           public void run() {
                               runOnUiThread(new Runnable() {
                                   public void run() {
                                       end();
                                   }
                               });

                           }
                       },
                    //change when the alarm stops
                    delay); // 1000 means start from 1 sec, and the second 1000 is do the loop each 1 sec.
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    snooze();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    snooze();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void end(){
        if (AddAlarm.vibrateCheck) {
            v.cancel();
        }

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(Snooze.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(Snooze.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        am.cancel(pi);
        pi.cancel();

        if (pickMinute < 10) {
            if (pickHour == 0) {
                notifTime = "12:0" + pickMinute;
            } else if (pickHour < 13) {
                notifTime = pickHour + ":0" + pickMinute;
            } else if (pickHour >= 13) {
                notifTime = (pickHour - 12) + ":0" + pickMinute;
            }
        } else if (pickMinute >= 10) {
            if (pickHour == 0) {
                notifTime = "12:" + pickMinute;
            } else if (pickHour < 13) {
                notifTime = pickHour + ":" + pickMinute;
            } else if (pickHour >= 13) {
                notifTime = (pickHour - 12) + ":" + pickMinute;
            }
        }

        if (pickHour < 12) {
            notifAmpm = "AM";
        } else {
            notifAmpm = "PM";
        }

        Notification.Builder nb  = new Notification.Builder(this)
                .setContentTitle("Alarm missed")
                .setContentText("from " + notifTime + notifAmpm)
                .setSmallIcon(R.drawable.time_logo);

        Notification n = nb.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);

        editor.putBoolean("alarmSetCheck", false);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        stop();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        snooze();
        return true;
    }

    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (theme == 0) {
            background.setBackgroundColor(Color.parseColor("#373737"));
            alarmText.setTextColor(Color.parseColor("#FFFFFF"));
            time.setTextColor(Color.parseColor("#FFFFFF"));
            ampm.setTextColor(Color.parseColor("#FFFFFF"));
            snooze.setTextColor(Color.parseColor("#FFFFFF"));
            stopAlarm.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            alarmText.setTextColor(Color.parseColor("#373737"));
            time.setTextColor(Color.parseColor("#373737"));
            ampm.setTextColor(Color.parseColor("#373737"));
            snooze.setTextColor(Color.parseColor("#373737"));
            stopAlarm.setTextColor(Color.parseColor("#373737"));
        }
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return true;
    }

    private void snooze(){
        Intent goAlarmSnoozed = new Intent(Snooze.this, AlarmSnoozed.class);
        startActivity(goAlarmSnoozed);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        if (AddAlarm.vibrateCheck) {
            v.cancel();
        }

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(Snooze.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(Snooze.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        //snooze for ten minutes, will add more options later
            
        if (snoozeLength == 1){
            minutes = 2 * 60 * 1000;
        } else if (snoozeLength == 2){
            minutes = 5 * 60 * 1000;
        } else if (snoozeLength == 3){
            minutes = 10 * 60 * 1000;
        } else if (snoozeLength == 4){
            minutes = 15 * 60 * 1000;
        } else if (snoozeLength == 5){
            minutes = 30 * 60 * 1000;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, AddAlarm.calAlarm.getTimeInMillis() + minutes, pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, AddAlarm.calAlarm.getTimeInMillis(), pi);
        }

        finish();
    }

    private void stop(){
        Intent goAlarmStopped = new Intent(Snooze.this, AlarmStopped.class);
        startActivity(goAlarmStopped);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        if (AddAlarm.vibrateCheck) {
            v.cancel();
        }

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(Snooze.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(Snooze.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        am.cancel(pi);
        pi.cancel();

        editor.putBoolean("alarmSetCheck", false);

        finish();
    }
}
