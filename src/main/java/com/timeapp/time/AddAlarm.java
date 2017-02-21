package com.timeapp.time;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class AddAlarm extends AppCompatActivity {

    private TextView time;
    private TextView ampm;
    private TextView noVibrate;
    private TextView vibrate;
    private TextView noRepeat;
    private TextView repeat;
    private TextView sunday;
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sundayPink;
    private TextView mondayPink;
    private TextView tuesdayPink;
    private TextView wednesdayPink;
    private TextView thursdayPink;
    private TextView fridayPink;
    private TextView saturdayPink;
    private TextView timeFromNow;
    private TextView ok;
    private TextView clockNav;
    private TextView clockNavOrange;
    private TextView alarmNav;
    private TextView settingsNav;
    private TextView settingsNavOrange;
    private TextView themeNav;
    private TextView themeNavOrange;
    private TextView alarmText;
    private TextView alarmTextPink;

    public static EditText alarmTitle;

    private ImageView line;
    private ImageView repeatRect;
    private ImageView repeatRectPink;
    private ImageView delete;
    private ImageView save;
    private ImageView deleteDark;
    private ImageView saveDark;

    private View okButton;
    private View okButtonPressed;
    private View darkView;
    private View touchHider;

    private RelativeLayout timePickerLayout;
    private RelativeLayout background;

    private Rect rect;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;
    private Rect rect5;
    private Rect rect6;
    private Rect rect7;
    private Rect rect8;
    private Rect rect9;
    private Rect rect10;
    private Rect rect11;
    private Rect rect12;

    public static Calendar calAlarm;

    private TimePicker timePicker;

    public static int pickHour = 0;
    public static int pickMinute = 0;
    public int hour = 0;
    public int minute = 0;
    private int checker = 0;
    public static int counter = 0; //to count the number of repeats, 0 means no repeat
    public static int checkerTwo = 0; //checks if repeat has been clicked (if all days are pink)
    private int goToActivity = 0;
    private int theme = 0;

    public static boolean vibrateCheck;
    public static boolean alarmSetCheck;
    public static boolean repeatSunday;
    public static boolean repeatMonday;
    public static boolean repeatTuesday;
    public static boolean repeatWednesday;
    public static boolean repeatThursday;
    public static boolean repeatFriday;
    public static boolean repeatSaturday;

    private SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        background = (RelativeLayout) findViewById(R.id.background);

        Thread myThread = null;
        Runnable myRunnableThread = new CountDownRunner();
        myThread = new Thread(myRunnableThread);
        myThread.start();

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");
        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        alarmText = (TextView) findViewById(R.id.alarm_text);
        alarmText.setTypeface(oswaldRegular);
        alarmTextPink = (TextView) findViewById(R.id.alarm_text_pink);
        alarmTextPink.setTypeface(oswaldRegular);
        alarmTextPink.setVisibility(View.GONE);

        time = (TextView) findViewById(R.id.time);
        time.setTypeface(oswaldRegular);
        ampm = (TextView) findViewById(R.id.ampm);
        ampm.setTypeface(oswaldRegular);
        noVibrate = (TextView) findViewById(R.id.no_vibrate);
        noVibrate.setTypeface(oswaldRegular);
        vibrate = (TextView) findViewById(R.id.vibrate);
        vibrate.setTypeface(oswaldRegular);
        vibrate.setVisibility(View.GONE);
        noRepeat = (TextView) findViewById(R.id.no_repeat);
        noRepeat.setTypeface(oswaldRegular);
        repeat = (TextView) findViewById(R.id.repeat);
        repeat.setTypeface(oswaldRegular);
        repeat.setVisibility(View.GONE);
        sunday = (TextView) findViewById(R.id.sunday);
        sunday.setTypeface(oswaldRegular);
        monday = (TextView) findViewById(R.id.monday);
        monday.setTypeface(oswaldRegular);
        tuesday = (TextView) findViewById(R.id.tuesday);
        tuesday.setTypeface(oswaldRegular);
        wednesday = (TextView) findViewById(R.id.wednesday);
        wednesday.setTypeface(oswaldRegular);
        thursday = (TextView) findViewById(R.id.thursday);
        thursday.setTypeface(oswaldRegular);
        friday = (TextView) findViewById(R.id.friday);
        friday.setTypeface(oswaldRegular);
        saturday = (TextView) findViewById(R.id.saturday);
        saturday.setTypeface(oswaldRegular);
        sundayPink = (TextView) findViewById(R.id.sunday_pink);
        sundayPink.setTypeface(oswaldRegular);
        sundayPink.setVisibility(View.GONE);
        mondayPink = (TextView) findViewById(R.id.monday_pink);
        mondayPink.setTypeface(oswaldRegular);
        mondayPink.setVisibility(View.GONE);
        tuesdayPink = (TextView) findViewById(R.id.tuesday_pink);
        tuesdayPink.setTypeface(oswaldRegular);
        tuesdayPink.setVisibility(View.GONE);
        wednesdayPink = (TextView) findViewById(R.id.wednesday_pink);
        wednesdayPink.setTypeface(oswaldRegular);
        wednesdayPink.setVisibility(View.GONE);
        thursdayPink = (TextView) findViewById(R.id.thursday_pink);
        thursdayPink.setTypeface(oswaldRegular);
        thursdayPink.setVisibility(View.GONE);
        fridayPink = (TextView) findViewById(R.id.friday_pink);
        fridayPink.setTypeface(oswaldRegular);
        fridayPink.setVisibility(View.GONE);
        saturdayPink = (TextView) findViewById(R.id.saturday_pink);
        saturdayPink.setTypeface(oswaldRegular);
        saturdayPink.setVisibility(View.GONE);
        timeFromNow = (TextView) findViewById(R.id.time_from_now);
        timeFromNow.setTypeface(oswaldLight);
        ok = (TextView) findViewById(R.id.ok);
        ok.setTypeface(oswaldRegular);
        touchHider = findViewById(R.id.touch_hider);
        touchHider.setVisibility(View.GONE);
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
        alarmTitle = (EditText) findViewById(R.id.alarm_title);
        alarmTitle.setTypeface(oswaldLight);

        line = (ImageView) findViewById(R.id.line);
        repeatRect = (ImageView) findViewById(R.id.repeat_box);
        repeatRectPink = (ImageView) findViewById(R.id.repeat_box_pink);
        repeatRectPink.setVisibility(View.GONE);
        delete = (ImageView) findViewById(R.id.delete);
        save = (ImageView) findViewById(R.id.save);
        deleteDark = (ImageView) findViewById(R.id.delete_dark);
        deleteDark.setVisibility(View.GONE);
        saveDark = (ImageView) findViewById(R.id.save_dark);
        saveDark.setVisibility(View.GONE);
        okButton = findViewById(R.id.ok_button);
        okButtonPressed = findViewById(R.id.ok_button_pressed);
        okButtonPressed.setVisibility(View.GONE);
        darkView = findViewById(R.id.dark_view);
        darkView.setVisibility(View.GONE);

        timePickerLayout = (RelativeLayout) findViewById(R.id.timepicker_layout);
        timePickerLayout.setVisibility(View.GONE);
        timePickerLayout.setTranslationY(30f);
        timePickerLayout.setAlpha(0f);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        pickHour = sp.getInt("pickHour", 0);
        pickMinute = sp.getInt("pickMinute", 0);
        counter = sp.getInt("counter", 0);
        checkerTwo = sp.getInt("checkerTwo", 0);
        alarmTitle.setText(sp.getString("alarmTitle", null));
        vibrateCheck = sp.getBoolean("vibrateCheck", false);
        alarmSetCheck = sp.getBoolean("alarmSetCheck", false);
        repeatSunday = sp.getBoolean("repeatSunday", false);
        repeatMonday = sp.getBoolean("repeatMonday", false);
        repeatTuesday = sp.getBoolean("repeatTuesday", false);
        repeatWednesday = sp.getBoolean("repeatWednesday", false);
        repeatThursday = sp.getBoolean("repeatThursday", false);
        repeatFriday = sp.getBoolean("repeatFriday", false);
        repeatSaturday = sp.getBoolean("repeatSaturday", false);

        if (alarmSetCheck) {
            alarmText.setText("EDIT ALARM");
            alarmTextPink.setText("EDIT ALARM");
            alarmNav.setText("EDIT ALARM");
            delete.setImageResource(R.drawable.delete_two);
            deleteDark.setImageResource(R.drawable.delete_two_dark);

            if (pickMinute < 10) {
                if (pickHour == 0) {
                    time.setText("12:0" + pickMinute);
                } else if (pickHour < 13) {
                    time.setText(pickHour + ":0" + pickMinute);
                } else if (pickHour >= 13) {
                    time.setText((pickHour - 12) + ":0" + pickMinute);
                }
            } else if (pickMinute >= 10) {
                if (pickHour == 0) {
                    time.setText("12:" + pickMinute);
                } else if (pickHour < 13) {
                    time.setText(pickHour + ":" + pickMinute);
                } else if (pickHour >= 13) {
                    time.setText((pickHour - 12) + ":" + pickMinute);
                }
            }

            if (pickHour < 12) {
                ampm.setText("AM");
            } else {
                ampm.setText("PM");
            }

            if (counter >= 1 /*|| checkerTwo == 1*/) {
                repeat.setVisibility(View.VISIBLE);
                repeatRectPink.setVisibility(View.VISIBLE);

                if (repeatSunday) {
                    sundayPink.setVisibility(View.VISIBLE);
                } else {
                    sundayPink.setVisibility(View.GONE);
                }

                if (repeatMonday) {
                    mondayPink.setVisibility(View.VISIBLE);
                } else {
                    mondayPink.setVisibility(View.GONE);
                }

                if (repeatTuesday) {
                    tuesdayPink.setVisibility(View.VISIBLE);
                } else {
                    tuesdayPink.setVisibility(View.GONE);
                }

                if (repeatWednesday) {
                    wednesdayPink.setVisibility(View.VISIBLE);
                } else {
                    wednesdayPink.setVisibility(View.GONE);
                }

                if (repeatThursday) {
                    thursdayPink.setVisibility(View.VISIBLE);
                } else {
                    thursdayPink.setVisibility(View.GONE);
                }

                if (repeatFriday) {
                    fridayPink.setVisibility(View.VISIBLE);
                } else {
                    fridayPink.setVisibility(View.GONE);
                }

                if (repeatSaturday) {
                    saturdayPink.setVisibility(View.VISIBLE);
                } else {
                    saturdayPink.setVisibility(View.GONE);
                }

            } else if (counter == 0 && checkerTwo == 0) {
                repeat.setVisibility(View.GONE);
                repeatRectPink.setVisibility(View.GONE);

                sundayPink.setVisibility(View.GONE);
                mondayPink.setVisibility(View.GONE);
                tuesdayPink.setVisibility(View.GONE);
                wednesdayPink.setVisibility(View.GONE);
                thursdayPink.setVisibility(View.GONE);
                fridayPink.setVisibility(View.GONE);
                saturdayPink.setVisibility(View.GONE);
            }

            if (vibrateCheck) {
                vibrate.setVisibility(View.VISIBLE);
            } else {
                vibrate.setVisibility(View.GONE);
            }

        } else {
            alarmText.setText("ADD ALARM");
            alarmTextPink.setText("ADD ALARM");
            alarmNav.setText("ADD ALARM");
            delete.setImageResource(R.drawable.delete);
            deleteDark.setImageResource(R.drawable.delete_dark);

            if (pickMinute < 10) {
                if (pickHour == 0) {
                    time.setText("12:0" + pickMinute);
                } else if (pickHour < 13) {
                    time.setText(pickHour + ":0" + pickMinute);
                } else if (pickHour >= 13) {
                    time.setText((pickHour - 12) + ":0" + pickMinute);
                }
            } else if (pickMinute >= 10) {
                if (pickHour == 0) {
                    time.setText("12:" + pickMinute);
                } else if (pickHour < 13) {
                    time.setText(pickHour + ":" + pickMinute);
                } else if (pickHour >= 13) {
                    time.setText((pickHour - 12) + ":" + pickMinute);
                }
            }

            ampm.setText("AM");
        }

        noVibrate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinfour();
                    rect4 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect4.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutfour();
                    } else {
                        crossfadeinfour();
                        vibrateCheck = true;
                    }

                    return true;
                }
                return false;
            }
        });

        vibrate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutfour();
                    rect4 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect4.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinfour();
                    } else {
                        crossfadeoutfour();
                        vibrateCheck = false;
                    }

                    return true;
                }
                return false;
            }
        });

        noRepeat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinsix();
                    rect5 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect5.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutfive();
                    } else {
                        checkerTwo = 1;
                        counter = 7;
                        crossfadeinfive();
                        repeatSunday = true;
                        repeatMonday = true;
                        repeatTuesday = true;
                        repeatWednesday = true;
                        repeatThursday = true;
                        repeatFriday = true;
                        repeatSaturday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        repeat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutsix();
                    rect5 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect5.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinsix();
                    } else {
                        crossfadeoutfive();
                        checkerTwo = 0;
                        counter = 0;
                        repeatSunday = false;
                        repeatMonday = false;
                        repeatTuesday = false;
                        repeatWednesday = false;
                        repeatThursday = false;
                        repeatFriday = false;
                        repeatSaturday = false;
                    }

                    return true;
                }
                return false;
            }
        });

        darkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerLayout.setVisibility(View.VISIBLE);
                timePickerLayout.animate()
                        .translationY(-30f)
                        .alpha(1f)
                        .setDuration(150)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                checker = 1;
                            }
                        });

                darkView.setVisibility(View.VISIBLE);
                darkView.setAlpha(0f);

                darkView.animate()
                        .alpha(0.7f)
                        .setDuration(150)
                        .setListener(null);

                touchHider.setVisibility(View.VISIBLE);
                touchHider.setOnClickListener(null);
            }
        });

        okButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinthree();
                    rect3 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect3.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutthree();
                    } else {
                        crossfadeoutthree();
                        timePickerLayout.animate()
                                .translationY(30f)
                                .alpha(0f)
                                .setDuration(150)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        checker = 0;
                                        timePickerLayout.setVisibility(View.GONE);
                                    }
                                });

                        darkView.animate()
                                .alpha(0f)
                                .setDuration(150)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        darkView.setVisibility(View.GONE);
                                    }
                                });

                        touchHider.setVisibility(View.GONE);

                        pickHour = timePicker.getCurrentHour();
                        pickMinute = timePicker.getCurrentMinute();

                        if (pickHour < 12) {
                            ampm.setText("AM");
                        } else {
                            ampm.setText("PM");
                        }

                        if (pickMinute < 10) {
                            if (pickHour == 0) {
                                time.setText("12:0" + pickMinute);
                            } else if (pickHour < 13) {
                                time.setText(pickHour + ":0" + pickMinute);
                            } else if (pickHour >= 13) {
                                time.setText((pickHour - 12) + ":0" + pickMinute);
                            }
                        } else if (pickMinute >= 10) {
                            if (pickHour == 0) {
                                time.setText("12:" + pickMinute);
                            } else if (pickHour < 13) {
                                time.setText(pickHour + ":" + pickMinute);
                            } else if (pickHour >= 13) {
                                time.setText((pickHour - 12) + ":" + pickMinute);
                            }
                        }
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
                    crossfadeinten();
                    rect5 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect5.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutten();
                    } else {
                        crossfadeoutten();
                        navigationopen();
                    }

                    return true;
                }
                return false;
            }
        });

        save.setOnTouchListener(new View.OnTouchListener() {
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
                        alarmSetCheck = true;

                        setAlarm();

                        sp();

                        Intent goClock = new Intent(AddAlarm.this, Clock.class);
                        startActivity(goClock);
                        overridePendingTransition(R.anim.activity2_slide_down, R.anim.activity_slide_down);
                    }

                    return true;
                }
                return false;
            }
        });

        delete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeintwo();
                    rect2 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect2.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeouttwo();
                    } else {
                        if (alarmSetCheck) {
                            cancelAlarm();

                            alarmSetCheck = false;
                        }

                        spDelete();

                        Intent goClock = new Intent(AddAlarm.this, Clock.class);
                        startActivity(goClock);
                        overridePendingTransition(R.anim.activity2_slide_down, R.anim.activity_slide_down);
                    }

                    return true;
                }
                return false;
            }
        });

        sunday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinsunday();
                    rect6 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect6.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutsunday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatSunday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        sundayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutsunday();
                    rect6 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect6.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinsunday();
                    } else {
                        counter = counter - 1;
                        repeatSunday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        monday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinmonday();
                    rect7 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect7.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutmonday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatMonday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        mondayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutmonday();
                    rect7 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect7.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinmonday();
                    } else {
                        counter = counter - 1;
                        repeatMonday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        tuesday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeintuesday();
                    rect8 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect8.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeouttuesday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatTuesday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        tuesdayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeouttuesday();
                    rect8 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect8.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeintuesday();
                    } else {
                        counter = counter - 1;
                        repeatTuesday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        wednesday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinwednesday();
                    rect9 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect9.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutwednesday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatWednesday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        wednesdayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutwednesday();
                    rect9 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect9.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinwednesday();
                    } else {
                        counter = counter - 1;
                        repeatWednesday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        thursday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinthursday();
                    rect10 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect10.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutthursday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatThursday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        thursdayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutthursday();
                    rect10 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect10.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinthursday();
                    } else {
                        counter = counter - 1;
                        repeatThursday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        friday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinfriday();
                    rect11 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect11.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutfriday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatFriday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        fridayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutfriday();
                    rect11 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect11.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinfriday();
                    } else {
                        counter = counter - 1;
                        repeatFriday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        saturday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinsaturday();
                    rect12 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect12.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutsaturday();
                    } else {
                        counter = counter + 1;
                        crossfadeinrepeat();
                        repeatSaturday = true;
                    }

                    return true;
                }
                return false;
            }
        });

        saturdayPink.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeoutsaturday();
                    rect12 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect12.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeinsaturday();
                    } else {
                        counter = counter - 1;
                        repeatSaturday = false;
                        if (counter >= 1) {
                            crossfadeoutrepeat();
                        } else {
                            crossfadeoutfive();
                        }
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void navigationopen(){
        checker = 3;

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
                .setDuration(200)
                .setListener(null)
                .start();

        alarmNav.setPivotX(300);
        alarmNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(0)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(200)
                .setListener(null)
                .start();

        clockNav.setPivotX(300);
        clockNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(200)
                .setStartDelay(65)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN){
                                    crossfadeinseven();
                                    rect2 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP){
                                    if(!rect2.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 3;
                                        crossfadeoutseven();
                                        checker = 2;
                                    } else {
                                        crossfadeoutseven();

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
                .setDuration(200)
                .setStartDelay(130)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN){
                                    crossfadeineight();
                                    rect3 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP){
                                    if(!rect3.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 3;
                                        crossfadeouteight();
                                        checker = 2;
                                    } else {
                                        crossfadeouteight();

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
                .setDuration(200)
                .setStartDelay(195)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        checker = 2;

                        themeNav.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    crossfadeinnine();
                                    rect4 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                                    return true;
                                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                                    if (!rect4.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                        checker = 3;
                                        crossfadeoutnine();
                                        checker = 2;
                                    } else {
                                        crossfadeoutnine();

                                        goToActivity = 3;
                                    }

                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                });
    }

    private void navigationclose(){
        themeNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(0)
                .setDuration(200)
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
                .setDuration(200)
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
                .setDuration(200)
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
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        checker = 0;
                        darkView.setVisibility(View.GONE);
                        alarmText.setVisibility(View.VISIBLE);

                        checker = 0;

                        if (goToActivity == 1) {
                            Intent goAlarm = new Intent(AddAlarm.this, Clock.class);
                            startActivity(goAlarm);
                            overridePendingTransition(R.anim.activity2_slide_down, R.anim.activity_slide_down);
                            goToActivity = 0;
                        } else if (goToActivity == 2) {
                            Intent goSettings = new Intent(AddAlarm.this, Settings.class);
                            startActivity(goSettings);
                            goToActivity = 0;
                        } else if (goToActivity == 3) {
                            Intent goTheme = new Intent(AddAlarm.this, Theme.class);
                            startActivity(goTheme);
                            goToActivity = 0;
                        }
                    }
                });
    }

    private void crossfadeinone(){
        saveDark.setAlpha(0f);
        saveDark.setVisibility(View.VISIBLE);

        saveDark.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutone(){
        saveDark.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        saveDark.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeintwo(){
        deleteDark.setAlpha(0f);
        deleteDark.setVisibility(View.VISIBLE);

        deleteDark.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeouttwo(){
        deleteDark.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        deleteDark.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinthree(){
        okButtonPressed.setAlpha(0f);
        okButtonPressed.setVisibility(View.VISIBLE);

        okButtonPressed.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutthree(){
        okButtonPressed.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        okButtonPressed.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinfour(){
        vibrate.setAlpha(0f);
        vibrate.setVisibility(View.VISIBLE);

        vibrate.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutfour(){
        vibrate.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        vibrate.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinfive(){
        repeatRectPink.setAlpha(0f);
        sundayPink.setAlpha(0f);
        mondayPink.setAlpha(0f);
        tuesdayPink.setAlpha(0f);
        wednesdayPink.setAlpha(0f);
        thursdayPink.setAlpha(0f);
        fridayPink.setAlpha(0f);
        saturdayPink.setAlpha(0f);
        repeatRectPink.setVisibility(View.VISIBLE);
        sundayPink.setVisibility(View.VISIBLE);
        mondayPink.setVisibility(View.VISIBLE);
        tuesdayPink.setVisibility(View.VISIBLE);
        wednesdayPink.setVisibility(View.VISIBLE);
        thursdayPink.setVisibility(View.VISIBLE);
        fridayPink.setVisibility(View.VISIBLE);
        saturdayPink.setVisibility(View.VISIBLE);

        repeatRectPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        sundayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        mondayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        tuesdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        wednesdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        thursdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        fridayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
        saturdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutfive() {
        repeat.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        repeat.setVisibility(View.GONE);
                    }
                });

        repeatRectPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        repeatRectPink.setVisibility(View.GONE);
                    }
                });

        if (counter >= 1 || checkerTwo == 1){
            sundayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            sundayPink.setVisibility(View.GONE);
                        }
                    });
            mondayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mondayPink.setVisibility(View.GONE);
                        }
                    });
            tuesdayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            tuesdayPink.setVisibility(View.GONE);
                        }
                    });
            wednesdayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            wednesdayPink.setVisibility(View.GONE);
                        }
                    });
            thursdayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            thursdayPink.setVisibility(View.GONE);
                        }
                    });
            fridayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fridayPink.setVisibility(View.GONE);
                        }
                    });
            saturdayPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            saturdayPink.setVisibility(View.GONE);
                        }
                    });
        }
    }

    private void crossfadeinsix(){
        repeat.setAlpha(0f);
        repeat.setVisibility(View.VISIBLE);

        repeat.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutsix(){
        repeat.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        repeat.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinsunday(){
        sundayPink.setAlpha(0f);
        sundayPink.setVisibility(View.VISIBLE);

        sundayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutsunday(){
        sundayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        sundayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinmonday(){
        mondayPink.setAlpha(0f);
        mondayPink.setVisibility(View.VISIBLE);

        mondayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutmonday(){
        mondayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mondayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeintuesday(){
        tuesdayPink.setAlpha(0f);
        tuesdayPink.setVisibility(View.VISIBLE);

        tuesdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeouttuesday(){
        tuesdayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        tuesdayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinwednesday(){
        wednesdayPink.setAlpha(0f);
        wednesdayPink.setVisibility(View.VISIBLE);

        wednesdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutwednesday(){
        wednesdayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        wednesdayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinthursday(){
        thursdayPink.setAlpha(0f);
        thursdayPink.setVisibility(View.VISIBLE);

        thursdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutthursday(){
        thursdayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thursdayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinfriday(){
        fridayPink.setAlpha(0f);
        fridayPink.setVisibility(View.VISIBLE);

        fridayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutfriday(){
        fridayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fridayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinsaturday(){
        saturdayPink.setAlpha(0f);
        saturdayPink.setVisibility(View.VISIBLE);

        saturdayPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutsaturday(){
        saturdayPink.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        saturdayPink.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinrepeat(){
        if (counter == 1) {
            repeat.setAlpha(0f);
            repeatRectPink.setAlpha(0f);
            repeat.setVisibility(View.VISIBLE);
            repeatRectPink.setVisibility(View.VISIBLE);

            repeat.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .setListener(null);
            repeatRectPink.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .setListener(null);
        }
    }

    private void crossfadeoutrepeat(){
        if (counter == 0) {
            repeat.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            repeat.setVisibility(View.GONE);
                        }
                    });

            repeatRectPink.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            repeatRectPink.setVisibility(View.GONE);
                        }
                    });
        }
    }

    private void crossfadeinseven(){
        clockNavOrange.setAlpha(0f);
        clockNavOrange.setVisibility(View.VISIBLE);

        clockNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutseven(){
        clockNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 2) {
            onBackPressed();
        }
    }

    private void crossfadeineight(){
        settingsNavOrange.setAlpha(0f);
        settingsNavOrange.setVisibility(View.VISIBLE);

        settingsNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeouteight(){
        settingsNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 2) {
            onBackPressed();
        }
    }

    private void crossfadeinnine(){
        themeNavOrange.setAlpha(0f);
        themeNavOrange.setVisibility(View.VISIBLE);

        themeNavOrange.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutnine(){
        themeNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        themeNavOrange.setVisibility(View.GONE);
                    }
                });

        if (checker == 2) {
            onBackPressed();
        }
    }

    private void crossfadeinten(){
        alarmTextPink.setAlpha(0f);
        alarmTextPink.setVisibility(View.VISIBLE);

        alarmTextPink.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutten(){
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

    private void sp(){
        editor.putInt("pickHour", pickHour);
        editor.putInt("pickMinute", pickMinute);
        editor.putInt("counter", counter);
        editor.putInt("checkerTwo", checkerTwo);
        editor.putString("alarmTitle", alarmTitle.getText().toString());
        editor.putBoolean("vibrateCheck", vibrateCheck);
        editor.putBoolean("alarmSetCheck", alarmSetCheck);
        editor.putBoolean("repeatSunday", repeatSunday);
        editor.putBoolean("repeatMonday", repeatMonday);
        editor.putBoolean("repeatTuesday", repeatTuesday);
        editor.putBoolean("repeatWednesday", repeatWednesday);
        editor.putBoolean("repeatThursday", repeatThursday);
        editor.putBoolean("repeatFriday", repeatFriday);
        editor.putBoolean("repeatSaturday", repeatSaturday);
        editor.commit();
    }

    private void spDelete(){
        editor.remove("pickHour");
        editor.remove("pickMinute");
        editor.remove("counter");
        editor.remove("checkerTwo");
        editor.remove("alarmTitle");
        editor.remove("vibrateCheck");
        editor.remove("alarmSetCheck");
        editor.remove("repeatSunday");
        editor.remove("repeatMonday");
        editor.remove("repeatTuesday");
        editor.remove("repeatWednesday");
        editor.remove("repeatThursday");
        editor.remove("repeatFriday");
        editor.remove("repeatSaturday");
        editor.commit();
    }

    private void setAlarm(){
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(AddAlarm.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(AddAlarm.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        calAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Date date = new Date();

        calNow.setTime(date);
        calAlarm.setTime(date);
        calAlarm.set(Calendar.HOUR_OF_DAY, pickHour);
        calAlarm.set(Calendar.MINUTE, pickMinute);
        calAlarm.set(Calendar.SECOND, 0);

        if (calAlarm.before(calNow) || calAlarm == calNow){
            calAlarm.add(Calendar.DATE, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, calAlarm.getTimeInMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, calAlarm.getTimeInMillis(), pi);
        }
    }

    private void cancelAlarm(){
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(AddAlarm.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        am.cancel(pi);
        pi.cancel();
    }

    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theme = sp.getInt("theme", 0);

        if (theme == 0) {
            background.setBackgroundColor(Color.parseColor("#373737"));
            alarmText.setTextColor(Color.parseColor("#252525"));
            alarmTitle.setTextColor(Color.parseColor("#FFFFFF"));
            time.setTextColor(Color.parseColor("#FFFFFF"));
            timeFromNow.setTextColor(Color.parseColor("#FFFFFF"));
            ampm.setTextColor(Color.parseColor("#FFFFFF"));
            noVibrate.setTextColor(Color.parseColor("#FFFFFF"));
            noRepeat.setTextColor(Color.parseColor("#FFFFFF"));
            sunday.setTextColor(Color.parseColor("#FFFFFF"));
            monday.setTextColor(Color.parseColor("#FFFFFF"));
            tuesday.setTextColor(Color.parseColor("#FFFFFF"));
            wednesday.setTextColor(Color.parseColor("#FFFFFF"));
            thursday.setTextColor(Color.parseColor("#FFFFFF"));
            friday.setTextColor(Color.parseColor("#FFFFFF"));
            saturday.setTextColor(Color.parseColor("#FFFFFF"));
            alarmTitle.setHintTextColor(Color.parseColor("#FFFFFF"));
            line.setImageResource(R.drawable.add_alarm_line);
            repeatRect.setImageResource(R.drawable.repeat_rectangle);
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            alarmText.setTextColor(Color.parseColor("#BBBBBB"));
            alarmTitle.setTextColor(Color.parseColor("#373737"));
            time.setTextColor(Color.parseColor("#373737"));
            timeFromNow.setTextColor(Color.parseColor("#373737"));
            ampm.setTextColor(Color.parseColor("#373737"));
            noVibrate.setTextColor(Color.parseColor("#373737"));
            noRepeat.setTextColor(Color.parseColor("#373737"));
            sunday.setTextColor(Color.parseColor("#373737"));
            monday.setTextColor(Color.parseColor("#373737"));
            tuesday.setTextColor(Color.parseColor("#373737"));
            wednesday.setTextColor(Color.parseColor("#373737"));
            thursday.setTextColor(Color.parseColor("#373737"));
            friday.setTextColor(Color.parseColor("#373737"));
            saturday.setTextColor(Color.parseColor("#373737"));
            alarmTitle.setHintTextColor(Color.parseColor("#373737"));
            line.setImageResource(R.drawable.add_alarm_line_dark);
            repeatRect.setImageResource(R.drawable.repeat_rectangle_dark);
        }
    }

    public void onBackPressed(){
        if (checker == 0) {
            Intent goClock = new Intent(AddAlarm.this, Clock.class);
            startActivity(goClock);
            overridePendingTransition(R.anim.activity2_slide_down, R.anim.activity_slide_down);
        } else if (checker == 1) {

            timePickerLayout.animate()
                    .translationY(30f)
                    .alpha(0f)
                    .setDuration(150)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            checker = 0;
                            timePickerLayout.setVisibility(View.GONE);
                        }
                    });

            darkView.animate()
                    .alpha(0f)
                    .setDuration(150)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation){
                            darkView.setVisibility(View.GONE);
                        }
                    });

            touchHider.setVisibility(View.GONE);
        } else if (checker == 2) {
            navigationclose();
            crossfadeoutten();
        }
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    timeFromNow = (TextView) findViewById(R.id.time_from_now);

                    if (pickMinute >= minutes){
                        minute = pickMinute - minutes;

                        if (pickHour > hours) {
                            hour = pickHour - hours;
                        }
                        else if (pickHour < hours) {
                            hour = (24 - hours) + pickHour;
                        }
                    }

                    else if (pickMinute < minutes){
                        minute = (60 - minutes) + pickMinute;

                        if (pickHour > hours) {
                            hour = (pickHour - hours) - 1;
                        }
                        else if (pickHour < hours) {
                            hour = ((24 - hours) + pickHour) - 1;
                        }
                    }

                    if (pickHour == hours && pickMinute > minutes) {
                        hour = 0;
                    }

                    else if (pickHour == hours && pickMinute < minutes) {
                        hour = 23;
                    }

                    else if (pickHour == hours && pickMinute == minutes) {
                        hour = 24;
                        minute = 0;
                    }

                    timeFromNow.setText(hour + "h " + minute + "m from now");

                }catch (Exception e) {}
            }
        });
    }

    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }


}
