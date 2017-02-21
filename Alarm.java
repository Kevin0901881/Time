package com.timeapp.time;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class Alarm extends AppCompatActivity {

    private TextView alarmText;
    private TextView alarmTextPink;
    /*private TextView alarmTitle;
    private TextView time;
    private TextView ampm;
    private TextView day;
    private TextView vibrate;
    private TextView repeat;*/
    private TextView noMoreAlarms;
    private TextView clockNav;
    private TextView clockNavOrange;
    private TextView alarmNav;
    private TextView settingsNav;
    private TextView settingsNavOrange;
    private TextView themeNav;
    private TextView themeNavOrange;

    //private ImageView line;
    private ImageView addAlarm;
    private ImageView addAlarmPressed;
    private ImageView alarmCircle;

    private TimePicker timePicker;

    private ListView lv;

    //private RelativeLayout nextAlarm;
    private RelativeLayout alarms;
    private RelativeLayout background;

    private Rect rect;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;
    private Rect rect5;

    public Bundle bundle;

    private View darkView;

    private int checker = 0;
    private int goToActivity = 0;
    private int shortAnimationDuration = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#373737"));
        }

        bundle = new Bundle();

        //load bundle from storage here

        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");
        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");

        background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));

        //nextAlarm = (RelativeLayout) findViewById(R.id.next_alarm);

        alarmText = (TextView) findViewById(R.id.alarm_text);
        alarmText.setTypeface(oswaldRegular);
        alarmTextPink = (TextView) findViewById(R.id.alarm_text_pink);
        alarmTextPink.setTypeface(oswaldRegular);
        alarmTextPink.setVisibility(View.GONE);
        /*alarmTitle = (TextView) findViewById(R.id.alarm_title);
        alarmTitle.setTypeface(oswaldLight);
        time = (TextView) findViewById(R.id.time);
        time.setTypeface(oswaldRegular);
        ampm = (TextView) findViewById(R.id.ampm);
        ampm.setTypeface(oswaldRegular);
        day = (TextView) findViewById(R.id.day);
        day.setTypeface(oswaldRegular);
        vibrate = (TextView) findViewById(R.id.vibrate);
        vibrate.setTypeface(oswaldRegular);
        repeat = (TextView) findViewById(R.id.repeat);
        repeat.setTypeface(oswaldRegular);*/
        noMoreAlarms = (TextView) findViewById(R.id.no_more_alarms);
        noMoreAlarms.setTypeface(oswaldLight);
        alarmNav = (TextView) findViewById(R.id.alarm_nav);
        alarmNav.setTypeface(oswaldRegular);
        alarmNav.setVisibility(View.GONE);
        clockNav = (TextView) findViewById(R.id.clock_nav);
        clockNav.setTypeface(oswaldRegular);
        clockNav.setVisibility(View.GONE);
        clockNavOrange = (TextView) findViewById(R.id.clock_nav_orange);
        clockNavOrange.setTypeface(oswaldRegular);
        clockNavOrange.setVisibility(View.GONE);
        settingsNav = (TextView) findViewById(R.id.settings_nav);
        settingsNav.setTypeface(oswaldRegular);
        settingsNav.setVisibility(View.GONE);
        settingsNavOrange = (TextView) findViewById(R.id.settings_nav_orange);
        settingsNavOrange.setTypeface(oswaldRegular);
        settingsNavOrange.setVisibility(View.GONE);
        themeNav = (TextView) findViewById(R.id.theme_nav);
        themeNav.setTypeface(oswaldRegular);
        themeNav.setVisibility(View.GONE);
        themeNavOrange = (TextView) findViewById(R.id.theme_nav_orange);
        themeNavOrange.setTypeface(oswaldRegular);
        themeNavOrange.setVisibility(View.GONE);
        darkView = findViewById(R.id.dark_view);
        darkView.setVisibility(View.GONE);

        //line = (ImageView) findViewById(R.id.line);
        addAlarm = (ImageView) findViewById(R.id.add_alarm);
        addAlarm.setScaleX(0f);
        addAlarm.setScaleY(0f);
        addAlarmPressed = (ImageView) findViewById(R.id.add_alarm_pressed);
        addAlarmPressed.setVisibility(View.GONE);
        alarmCircle = (ImageView) findViewById(R.id.alarm_circle);
        alarmCircle.setVisibility(View.GONE);

        //nextAlarm = (RelativeLayout) findViewById(R.id.next_alarm);
        alarms = (RelativeLayout) findViewById(R.id.other_alarms);

        lv = (ListView) findViewById(R.id.alarms);

        List<String> arrayList = new ArrayList<String>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.alarm_listview,
                arrayList
        );

        lv.setAdapter(arrayAdapter);

        Animation AlarmUp = AnimationUtils.loadAnimation(this, R.anim.time_up_animation);
        AlarmUp.setStartOffset(600);

        /*line.setScaleX(0);

        line.animate()
                .scaleX(1)
                .setDuration(500)
                .setStartDelay(500)
                .setInterpolator(new DecelerateInterpolator(2));*/

        addAlarm.animate()
                .scaleX(1)
                .scaleY(1)
                .setStartDelay(750)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator(2));

        alarms.startAnimation(AlarmUp);
        noMoreAlarms.startAnimation(AlarmUp);

        addAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinone();
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutone();
                    } else {
                        crossfadeoutone();
                        circleopen();
                    }

                    return true;
                }
                return false;
            }
        });

        alarmText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinfive();
                    rect5 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect5.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutfive();
                    } else {
                        crossfadeoutfive();
                        navigationopen();
                    }

                    return true;
                }
                return false;
            }
        });

        darkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationclose();
            }
        });
    }

    private void navigationopen(){
        checker = 1;

        alarmText.setVisibility(View.GONE);

        alarmNav.setAlpha(0f);
        settingsNav.setAlpha(0f);
        themeNav.setAlpha(0f);
        clockNav.setAlpha(0f);
        clockNavOrange.setAlpha(0f);
        settingsNavOrange.setAlpha(0f);
        themeNavOrange.setAlpha(0f);
        darkView.setAlpha(0f);

        clockNav.setScaleX(0.5f);
        clockNav.setScaleY(0.5f);
        alarmNav.setScaleX(0.5f);
        alarmNav.setScaleY(0.5f);
        settingsNav.setScaleX(0.5f);
        settingsNav.setScaleY(0.5f);
        themeNav.setScaleX(0.5f);
        themeNav.setScaleY(0.5f);

        alarmNav.setVisibility(View.VISIBLE);
        settingsNav.setVisibility(View.VISIBLE);
        themeNav.setVisibility(View.VISIBLE);
        clockNav.setVisibility(View.VISIBLE);
        clockNavOrange.setVisibility(View.VISIBLE);
        settingsNavOrange.setVisibility(View.VISIBLE);
        themeNavOrange.setVisibility(View.VISIBLE);
        darkView.setVisibility(View.VISIBLE);

        darkView.animate()
                .alpha(0.9f)
                .setStartDelay(0)
                .setDuration(shortAnimationDuration)
                .setListener(null)
                .start();

        alarmNav.setPivotX(300);
        alarmNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(0)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setListener(null)
                .start();

        clockNav.setPivotX(300);
        clockNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setStartDelay(65)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN){
                                    crossfadeintwo();
                                    rect2 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP){
                                    if(!rect2.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 2;
                                        crossfadeouttwo();
                                        checker = 1;
                                    } else {
                                        crossfadeouttwo();

                                        goToActivity = 1;
                                    }

                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                })
                .start();

        settingsNav.setPivotX(300);
        settingsNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setStartDelay(130)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN){
                                    crossfadeinthree();
                                    rect3 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP){
                                    if(!rect3.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 2;
                                        crossfadeoutthree();
                                        checker = 1;
                                    } else {
                                        crossfadeoutthree();

                                        goToActivity = 2;
                                    }

                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                })
                .start();

        themeNav.setPivotX(300);
        themeNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setStartDelay(195)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        themeNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN){
                                    crossfadeinfour();
                                    rect4 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP){
                                    if(!rect4.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 2;
                                        crossfadeoutfour();
                                        checker = 1;
                                    } else {
                                        crossfadeoutfour();

                                        goToActivity = 3;
                                    }

                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                })
                .start();
    }

    private void navigationclose(){
        themeNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(0)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        themeNav.setVisibility(View.GONE);
                        themeNavOrange.setVisibility(View.GONE);
                    }
                })
                .start();

        settingsNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(65)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsNav.setVisibility(View.GONE);
                        settingsNavOrange.setVisibility(View.GONE);
                        alarmText.setVisibility(View.VISIBLE);
                    }
                })
                .start();

        clockNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(130)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNav.setVisibility(View.GONE);
                    }
                })
                .start();

        alarmNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(190)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        alarmNav.setVisibility(View.GONE);
                    }
                })
                .start();

        darkView.animate()
                .alpha(0f)
                .setStartDelay(190)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        darkView.setVisibility(View.GONE);
                        alarmText.setVisibility(View.VISIBLE);

                        checker = 0;

                        if (goToActivity == 1) {
                            Intent goAlarm = new Intent(Alarm.this, Clock.class);
                            startActivity(goAlarm);
                            overridePendingTransition(R.anim.activity2_slide_down, R.anim.activity_slide_down);
                        } else if (goToActivity == 2) {

                        } else if (goToActivity == 3) {
                            Intent goTheme = new Intent(Alarm.this, Theme.class);
                            startActivity(goTheme);
                        }
                    }
                })
                .start();
    }

    private void crossfadeinone(){
        addAlarmPressed.setAlpha(0f);
        addAlarmPressed.setVisibility(View.VISIBLE);

        addAlarmPressed.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutone(){
        addAlarmPressed.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        addAlarmPressed.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeintwo(){
        clockNavOrange.setAlpha(0f);
        clockNavOrange.setVisibility(View.VISIBLE);

        clockNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeouttwo(){
        clockNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 1) {
            onBackPressed();
        }
    }

    private void crossfadeinthree(){
        settingsNavOrange.setAlpha(0f);
        settingsNavOrange.setVisibility(View.VISIBLE);

        settingsNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutthree(){
        settingsNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 1) {
            onBackPressed();
        }
    }

    private void crossfadeinfour(){
        themeNavOrange.setAlpha(0f);
        themeNavOrange.setVisibility(View.VISIBLE);

        themeNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutfour(){
        themeNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        themeNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 1) {
            onBackPressed();
        }
    }

    private void crossfadeinfive(){
        alarmTextPink.setAlpha(0f);
        alarmTextPink.setVisibility(View.VISIBLE);

        alarmTextPink.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);
    }

    private void crossfadeoutfive(){
        alarmTextPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        alarmTextPink.setVisibility(View.GONE);
                    }
                });
    }

    private void circleopen() {
        alarmCircle.setVisibility(View.VISIBLE);
        addAlarm.setOnTouchListener(null);

        alarmCircle.animate()
                .scaleX(21)
                .scaleY(21)
                .setDuration(500)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent goAddAlarm = new Intent(Alarm.this, AddAlarm.class);
                        startActivity(goAddAlarm);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                });

        addAlarm.animate()
                .alpha(0f)
                .setDuration(200)
                .setStartDelay(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        addAlarm.setVisibility(View.GONE);
                    }
                });
    }

    public void onDestroy(){
        super.onDestroy();


    }

    public void onStop(){
        super.onStop();

        alarmCircle.setScaleX(1);
        alarmCircle.setScaleY(1);
        addAlarm.setAlpha(1f);

        alarmCircle.setVisibility(View.GONE);
        addAlarm.setVisibility(View.VISIBLE);
    }

    public void onResume(){
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinone();
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutone();
                    } else {
                        crossfadeoutone();
                        circleopen();
                    }

                    return true;
                }
                return false;
            }
        });

    }

    public void onBackPressed(){
        if (checker == 1){
            navigationclose();
            crossfadeoutfive();
        }
    }
}
