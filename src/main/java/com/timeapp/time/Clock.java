package com.timeapp.time;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;


public class Clock extends AppCompatActivity {

    private TextView clockText;
    private TextView clockTextOrange;
    private TextView clockNav;
    private TextView alarmNav;
    private TextView alarmNavOrange;
    private TextView settingsNav;
    private TextView settingsNavOrange;
    private TextView themeNav;
    private TextView themeNavOrange;

    private ImageView line;

    private View darkView;

    private TextClock minHr;
    private TextClock sec;
    private TextClock date;
    private TextClock year;

    private View clockBackground;
    private RelativeLayout backgroundColor;
    private RelativeLayout dateAll;
    private LinearLayout time;

    private Rect rect;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;

    private static int shortAnimationDuration = 200;
    private static int checker = 0;
    private int goToActivity = 0;
    private int theme = 0;

    private AudioManager am;

    private SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");

        clockText = (TextView) findViewById(R.id.clock_text);
        clockText.setTypeface(oswaldRegular);
        clockTextOrange = (TextView) findViewById(R.id.clock_text_orange);
        clockTextOrange.setTypeface(oswaldRegular);
        clockNav = (TextView) findViewById(R.id.clock_nav);
        clockNav.setTypeface(oswaldRegular);
        alarmNav = (TextView) findViewById(R.id.alarm_nav);
        alarmNav.setTypeface(oswaldRegular);
        alarmNavOrange = (TextView) findViewById(R.id.alarm_nav_orange);
        alarmNavOrange.setTypeface(oswaldRegular);

        if (AddAlarm.alarmSetCheck) {
            alarmNav.setText("EDIT ALARM");
            alarmNavOrange.setText("EDIT ALARM");
        } else {
            alarmNav.setText("ADD ALARM");
            alarmNavOrange.setText("ADD ALARM");
        }

        settingsNav = (TextView) findViewById(R.id.settings_nav);
        settingsNav.setTypeface(oswaldRegular);
        settingsNavOrange = (TextView) findViewById(R.id.settings_nav_orange);
        settingsNavOrange.setTypeface(oswaldRegular);
        themeNav = (TextView) findViewById(R.id.theme_nav);
        themeNav.setTypeface(oswaldRegular);
        themeNavOrange = (TextView) findViewById(R.id.theme_nav_orange);
        themeNavOrange.setTypeface(oswaldRegular);

        line = (ImageView) findViewById(R.id.line);

        time = (LinearLayout) findViewById(R.id.time);
        dateAll = (RelativeLayout) findViewById(R.id.date_all);

        Animation dateDown = AnimationUtils.loadAnimation(this, R.anim.date_down_animation);
        Animation timeUp = AnimationUtils.loadAnimation(this, R.anim.time_up_animation);
        timeUp.setStartOffset(700);
        dateDown.setStartOffset(850);

        time.startAnimation(timeUp);
        dateAll.startAnimation(dateDown);

        line.setScaleX(0);
        line.setPivotX(-100);

        line.animate()
                .scaleX(1)
                .setDuration(500)
                .setStartDelay(600)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        minHr = (TextClock) findViewById(R.id.min_hr);
        minHr.setTypeface(oswaldRegular);
        sec = (TextClock) findViewById(R.id.sec);
        sec.setTypeface(oswaldRegular);
        date = (TextClock) findViewById(R.id.date);
        date.setTypeface(oswaldRegular);
        year = (TextClock) findViewById(R.id.year);
        year.setTypeface(oswaldRegular);

        darkView = findViewById(R.id.dark_view);

        backgroundColor = (RelativeLayout) findViewById(R.id.background_color);

        clockBackground = findViewById(R.id.background);

        clockBackground.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent goClockSleep = new Intent(Clock.this, ClockSleep.class);
                startActivity(goClockSleep);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                return true;
            }
        });

        clockTextOrange.setVisibility(View.GONE);
        clockNav.setVisibility(View.GONE);
        alarmNav.setVisibility(View.GONE);
        settingsNav.setVisibility(View.GONE);
        themeNav.setVisibility(View.GONE);
        alarmNavOrange.setVisibility(View.GONE);
        settingsNavOrange.setVisibility(View.GONE);
        themeNavOrange.setVisibility(View.GONE);
        darkView.setVisibility(View.GONE);

        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        clockText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinone();
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutone();
                    } else {
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
                crossfadeoutone();
            }
        });
    }

    private void navigationopen(){
        checker = 1;

        clockText.setVisibility(View.GONE);

        clockNav.setAlpha(0f);
        alarmNav.setAlpha(0f);
        settingsNav.setAlpha(0f);
        themeNav.setAlpha(0f);
        alarmNavOrange.setAlpha(0f);
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

        clockNav.setVisibility(View.VISIBLE);
        alarmNav.setVisibility(View.VISIBLE);
        settingsNav.setVisibility(View.VISIBLE);
        themeNav.setVisibility(View.VISIBLE);
        alarmNavOrange.setVisibility(View.VISIBLE);
        settingsNavOrange.setVisibility(View.VISIBLE);
        themeNavOrange.setVisibility(View.VISIBLE);
        darkView.setVisibility(View.VISIBLE);
        clockBackground.setVisibility(View.GONE);

        darkView.animate()
                .alpha(0.9f)
                .setStartDelay(0)
                .setDuration(shortAnimationDuration)
                .setListener(null)
                .start();

        clockNav.setPivotX(300);
        clockNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setStartDelay(0)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setListener(null)
                .start();

        alarmNav.setPivotX(300);
        alarmNav.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(shortAnimationDuration)
                .setStartDelay(65)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        alarmNav.setOnTouchListener(new View.OnTouchListener() {
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
                        clockText.setVisibility(View.VISIBLE);
                    }
                })
                .start();

        alarmNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(130)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        alarmNav.setVisibility(View.GONE);
                        alarmNavOrange.setVisibility(View.GONE);
                    }
                })
                .start();

        clockNav.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setStartDelay(190)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockNav.setVisibility(View.GONE);
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
                        clockBackground.setVisibility(View.VISIBLE);

                        checker = 0;

                        if (goToActivity == 1) {
                            Intent goAlarm = new Intent(Clock.this, AddAlarm.class);
                            startActivity(goAlarm);
                            overridePendingTransition(R.anim.activity2_slide_up, R.anim.activity_slide_up);
                            goToActivity = 0;
                        } else if (goToActivity == 2) {
                            Intent goSettings = new Intent(Clock.this, Settings.class);
                            startActivity(goSettings);
                            goToActivity = 0;
                        } else if (goToActivity == 3) {
                            Intent goTheme = new Intent(Clock.this, Theme.class);
                            startActivity(goTheme);
                            goToActivity = 0;
                        }
                    }
                })
                .start();
    }

    private void crossfadeinone(){
        clockTextOrange.setAlpha(0f);
        clockTextOrange.setVisibility(View.VISIBLE);

        clockTextOrange.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);
    }

    private void crossfadeoutone(){
        clockTextOrange.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clockTextOrange.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeintwo(){
        alarmNavOrange.setAlpha(0f);
        alarmNavOrange.setVisibility(View.VISIBLE);

        alarmNavOrange.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);
    }

    private void crossfadeouttwo(){
        alarmNavOrange.animate()
                .alpha(0f)
                .setDuration(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        alarmNavOrange.setVisibility(View.GONE);
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
                .setDuration(shortAnimationDuration)
                .setListener(null);
    }

    private void crossfadeoutthree() {
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
                .setDuration(shortAnimationDuration)
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

    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theme = sp.getInt("theme", 0);

        if (theme == 0) {
            backgroundColor.setBackgroundColor(Color.parseColor("#373737"));
            minHr.setTextColor(Color.parseColor("#FFFFFF"));
            sec.setTextColor(Color.parseColor("#FFFFFF"));
            date.setTextColor(Color.parseColor("#FFFFFF"));
            year.setTextColor(Color.parseColor("#FFFFFF"));
            clockText.setTextColor(Color.parseColor("#252525"));
            line.setImageResource(R.drawable.line_clock);
        } else if (theme == 1) {
            backgroundColor.setBackgroundColor(Color.parseColor("#E8E8E8"));
            minHr.setTextColor(Color.parseColor("#373737"));
            sec.setTextColor(Color.parseColor("#373737"));
            date.setTextColor(Color.parseColor("#373737"));
            year.setTextColor(Color.parseColor("#373737"));
            clockText.setTextColor(Color.parseColor("#BBBBBB"));
            line.setImageResource(R.drawable.line_clock_dark);
        }
    }

    public void onBackPressed(){
        if (checker == 1){
            navigationclose();
            crossfadeoutone();
        }
    }
}
