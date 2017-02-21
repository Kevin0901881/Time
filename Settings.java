package com.timeapp.time;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView settingsText;
    private TextView settingsTextGreen;
    private TextView alarmHeader;
    private TextView otherHeader;
    private TextView snoozeLengthText;
    private TextView snoozeLengthOption2;
    private TextView snoozeLengthOption5;
    private TextView snoozeLengthOption10;
    private TextView snoozeLengthOption15;
    private TextView snoozeLengthOption30;
    private TextView stopAfterText;
    private TextView stopAfterOption2;
    private TextView stopAfterOption5;
    private TextView stopAfterOption10;
    private TextView stopAfterOption15;
    private TextView stopAfterOption30;
    private TextView stopAfterOptionNever;
    private TextView snoozeVolumeText;
    private TextView snoozeVolumeOptionYes;
    private TextView snoozeVolumeOptionNo;
    private TextView about;

    private View aboutView;
    private View aboutView2;

    private int snoozeLength = 0;
    private int stopAfter = 0;
    private int snoozeVolume = 0;

    /*private View view1;
    private View view2;
    private View view1Dark;
    private View view2Dark;*/

    private RelativeLayout background;

    private int theme = 0;

    private Rect rect;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;

    private SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        background = (RelativeLayout) findViewById(R.id.background);

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");
        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        settingsText = (TextView) findViewById(R.id.settings_text);
        settingsText.setTypeface(oswaldRegular);
        settingsTextGreen = (TextView) findViewById(R.id.settings_text_green);
        settingsTextGreen.setTypeface(oswaldRegular);
        settingsTextGreen.setVisibility(View.GONE);
        alarmHeader = (TextView) findViewById(R.id.alarm_header);
        alarmHeader.setTypeface(oswaldRegular);
        otherHeader = (TextView) findViewById(R.id.other_header);
        otherHeader.setTypeface(oswaldRegular);
        snoozeLengthText = (TextView) findViewById(R.id.snooze_length_text);
        snoozeLengthText.setTypeface(oswaldRegular);
        snoozeLengthOption2 = (TextView) findViewById(R.id.snooze_length_option_2);
        snoozeLengthOption2.setTypeface(oswaldLight);
        snoozeLengthOption5 = (TextView) findViewById(R.id.snooze_length_option_5);
        snoozeLengthOption5.setTypeface(oswaldLight);
        snoozeLengthOption10 = (TextView) findViewById(R.id.snooze_length_option_10);
        snoozeLengthOption10.setTypeface(oswaldLight);
        snoozeLengthOption15 = (TextView) findViewById(R.id.snooze_length_option_15);
        snoozeLengthOption15.setTypeface(oswaldLight);
        snoozeLengthOption30 = (TextView) findViewById(R.id.snooze_length_option_30);
        snoozeLengthOption30.setTypeface(oswaldLight);
        stopAfterText = (TextView) findViewById(R.id.snooze_stop_after);
        stopAfterText.setTypeface(oswaldRegular);
        stopAfterOption2 = (TextView) findViewById(R.id.snooze_stop_after_option_2);
        stopAfterOption2.setTypeface(oswaldLight);
        stopAfterOption5 = (TextView) findViewById(R.id.snooze_stop_after_option_5);
        stopAfterOption5.setTypeface(oswaldLight);
        stopAfterOption10 = (TextView) findViewById(R.id.snooze_stop_after_option_10);
        stopAfterOption10.setTypeface(oswaldLight);
        stopAfterOption15 = (TextView) findViewById(R.id.snooze_stop_after_option_15);
        stopAfterOption15.setTypeface(oswaldLight);
        stopAfterOption30 = (TextView) findViewById(R.id.snooze_stop_after_option_30);
        stopAfterOption30.setTypeface(oswaldLight);
        stopAfterOptionNever = (TextView) findViewById(R.id.snooze_stop_after_option_never);
        stopAfterOptionNever.setTypeface(oswaldLight);
        snoozeVolumeText = (TextView) findViewById(R.id.snooze_volume);
        snoozeVolumeText.setTypeface(oswaldRegular);
        snoozeVolumeOptionYes = (TextView) findViewById(R.id.snooze_volume_option_yes);
        snoozeVolumeOptionYes.setTypeface(oswaldLight);
        snoozeVolumeOptionNo = (TextView) findViewById(R.id.snooze_volume_option_no);
        snoozeVolumeOptionNo.setTypeface(oswaldLight);
        about = (TextView) findViewById(R.id.about);
        about.setTypeface(oswaldRegular);

        aboutView = findViewById(R.id.about_view);
        aboutView.setVisibility(View.GONE);
        aboutView2 = findViewById(R.id.about_view_2);

        snoozeLength = sp.getInt("snoozeLength", 3);
        stopAfter = sp.getInt("stopAfter", 3);
        snoozeVolume = sp.getInt("snoozeVolume", 2);

        /*view1 = findViewById(R.id.view_1);
        view2 = findViewById(R.id.view_2);
        view1Dark = findViewById(R.id.view_1_dark);
        view1Dark.setVisibility(View.GONE);
        view2Dark = findViewById(R.id.view_2_dark);
        view2Dark.setVisibility(View.GONE);*/

        aboutView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinone();
                    rect4 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect4.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutone();
                    } else {
                        crossfadeoutone();

                        Intent goAbout = new Intent(Settings.this, About.class);
                        startActivity(goAbout);
                    }

                    return true;
                }
                return false;
            }
        });

        snoozeVolumeOptionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeVolume = 1;
                if (theme == 0) {
                    snoozeVolumeOptionYes.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeVolumeOptionNo.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    snoozeVolumeOptionYes.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeVolumeOptionNo.setTextColor(Color.parseColor("#373737"));
                }
                sp();
            }
        });

        snoozeVolumeOptionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeVolume = 2;
                if (theme == 0) {
                    snoozeVolumeOptionYes.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeVolumeOptionNo.setTextColor(Color.parseColor("#4CAF50"));
                } else if (theme == 1) {
                    snoozeVolumeOptionYes.setTextColor(Color.parseColor("#373737"));
                    snoozeVolumeOptionNo.setTextColor(Color.parseColor("#4CAF50"));
                }
                sp();
            }
        });

        stopAfterOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 1;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme ==  1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption5.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption10.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption15.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption30.setTextColor(Color.parseColor("#373737"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
                }
                sp();
            }
        });

        stopAfterOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 2;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption5.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption5.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption10.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption15.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption30.setTextColor(Color.parseColor("#373737"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        stopAfterOption10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 3;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption10.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption5.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption10.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption15.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption30.setTextColor(Color.parseColor("#373737"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        stopAfterOption15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 4;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption15.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption5.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption10.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption15.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOption30.setTextColor(Color.parseColor("#373737"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        stopAfterOption30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 5;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption30.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption5.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption10.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption15.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption30.setTextColor(Color.parseColor("#4CAF50"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        stopAfterOptionNever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAfter = 6;
                if (theme == 0) {
                    stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#4CAF50"));
                } else if (theme == 1) {
                    stopAfterOption2.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption5.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption10.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption15.setTextColor(Color.parseColor("#373737"));
                    stopAfterOption30.setTextColor(Color.parseColor("#373737"));
                    stopAfterOptionNever.setTextColor(Color.parseColor("#4CAF50"));
                }

                sp();
            }
        });

        snoozeLengthOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeLength = 1;
                if (theme == 0) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        snoozeLengthOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeLength = 2;
                if (theme == 0) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        snoozeLengthOption10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeLength = 3;
                if (theme == 0) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        snoozeLengthOption15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeLength = 4;
                if (theme == 0) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#FFFFFF"));
                } else if (theme == 1) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#4CAF50"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#373737"));
                }

                sp();
            }
        });

        snoozeLengthOption30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozeLength = 5;
                if (theme == 0) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#FFFFFF"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#4CAF50"));
                } else if (theme == 1) {
                    snoozeLengthOption2.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption5.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption10.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption15.setTextColor(Color.parseColor("#373737"));
                    snoozeLengthOption30.setTextColor(Color.parseColor("#4CAF50"));
                }

                sp();
            }
        });

        settingsText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadein1();
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeout1();
                    } else {
                        crossfadeout1();
                    }

                    return true;
                }
                return false;
            }
        });

        /*view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinview1();
                    rect2 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect2.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutview1();
                    } else {
                        crossfadeoutview1();
                    }

                    return true;
                }
                return false;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    crossfadeinview2();
                    rect3 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect3.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        crossfadeoutview2();
                    } else {
                        crossfadeoutview2();
                    }

                    return true;
                }
                return false;
            }
        });*/
    }

    /*private void crossfadeinview1(){
        view1Dark.setAlpha(0f);
        view1Dark.setVisibility(View.VISIBLE);

        view1Dark.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutview1(){
        view1Dark.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view1Dark.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeinview2(){
        view2Dark.setAlpha(0f);
        view2Dark.setVisibility(View.VISIBLE);

        view2Dark.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutview2(){
        view2Dark.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view2Dark.setVisibility(View.GONE);
                    }
                });
    }*/

    private void crossfadeinone(){
        aboutView.setAlpha(0f);
        aboutView.setVisibility(View.VISIBLE);

        aboutView.animate()
                .alpha(0.1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeoutone(){
        aboutView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        aboutView.setVisibility(View.GONE);
                    }
                });
    }

    private void sp(){
        editor.putInt("snoozeLength", snoozeLength);
        editor.putInt("stopAfter", stopAfter);
        editor.putInt("snoozeVolume", snoozeVolume);
        editor.commit();
    }

    private void crossfadein1(){
        settingsTextGreen.setAlpha(0f);
        settingsTextGreen.setVisibility(View.VISIBLE);

        settingsTextGreen.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);
    }

    private void crossfadeout1(){
        settingsTextGreen.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        settingsTextGreen.setVisibility(View.GONE);
                    }
                });
    }

    /*private void navigationopen(){
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
    }*/

    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theme = sp.getInt("theme", 0);

        if (theme == 0) {
            background.setBackgroundColor(Color.parseColor("#373737"));
            settingsText.setTextColor(Color.parseColor("#252525"));
            snoozeLengthText.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeLengthOption2.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeLengthOption5.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeLengthOption10.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeLengthOption15.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeLengthOption30.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterText.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOption2.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOption5.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOption10.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOption15.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOption30.setTextColor(Color.parseColor("#FFFFFF"));
            stopAfterOptionNever.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeVolumeText.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeVolumeOptionYes.setTextColor(Color.parseColor("#FFFFFF"));
            snoozeVolumeOptionNo.setTextColor(Color.parseColor("#FFFFFF"));
            about.setTextColor(Color.parseColor("#FFFFFF"));


            if (snoozeLength == 1){
                snoozeLengthOption2.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 2){
                snoozeLengthOption5.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 3){
                snoozeLengthOption10.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 4){
                snoozeLengthOption15.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 5){
                snoozeLengthOption30.setTextColor(Color.parseColor("#4CAF50"));
            }

            if (stopAfter == 1){
                stopAfterOption2.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 2){
                stopAfterOption5.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 3){
                stopAfterOption10.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 4){
                stopAfterOption15.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 5){
                stopAfterOption30.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 6){
                stopAfterOptionNever.setTextColor(Color.parseColor("#4CAF50"));
            }

            if (snoozeVolume == 1){
                snoozeVolumeOptionYes.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeVolume == 2){
                snoozeVolumeOptionNo.setTextColor(Color.parseColor("#4CAF50"));
            }


            //view1Dark.setBackgroundColor(Color.parseColor("#252525"));
            //view1Dark.setBackgroundColor(Color.parseColor("#252525"));
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            settingsText.setTextColor(Color.parseColor("#BBBBBB"));
            snoozeLengthText.setTextColor(Color.parseColor("#373737"));
            snoozeLengthOption2.setTextColor(Color.parseColor("#373737"));
            snoozeLengthOption5.setTextColor(Color.parseColor("#373737"));
            snoozeLengthOption10.setTextColor(Color.parseColor("#373737"));
            snoozeLengthOption15.setTextColor(Color.parseColor("#373737"));
            snoozeLengthOption30.setTextColor(Color.parseColor("#373737"));
            stopAfterText.setTextColor(Color.parseColor("#373737"));
            stopAfterOption2.setTextColor(Color.parseColor("#373737"));
            stopAfterOption5.setTextColor(Color.parseColor("#373737"));
            stopAfterOption10.setTextColor(Color.parseColor("#373737"));
            stopAfterOption15.setTextColor(Color.parseColor("#373737"));
            stopAfterOption30.setTextColor(Color.parseColor("#373737"));
            stopAfterOptionNever.setTextColor(Color.parseColor("#373737"));
            snoozeVolumeText.setTextColor(Color.parseColor("#373737"));
            snoozeVolumeOptionYes.setTextColor(Color.parseColor("#373737"));
            snoozeVolumeOptionNo.setTextColor(Color.parseColor("#373737"));
            about.setTextColor(Color.parseColor("#373737"));

            if (snoozeLength == 1){
                snoozeLengthOption2.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 2){
                snoozeLengthOption5.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 3){
                snoozeLengthOption10.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 4){
                snoozeLengthOption15.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeLength == 5){
                snoozeLengthOption30.setTextColor(Color.parseColor("#4CAF50"));
            }

            if (stopAfter == 1){
                stopAfterOption2.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 2){
                stopAfterOption5.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 3){
                stopAfterOption10.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 4){
                stopAfterOption15.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 5){
                stopAfterOption30.setTextColor(Color.parseColor("#4CAF50"));
            } else if (stopAfter == 6){
                stopAfterOptionNever.setTextColor(Color.parseColor("#4CAF50"));
            }

            if (snoozeVolume == 1){
                snoozeVolumeOptionYes.setTextColor(Color.parseColor("#4CAF50"));
            } else if (snoozeVolume == 2){
                snoozeVolumeOptionNo.setTextColor(Color.parseColor("#4CAF50"));
            }

            //view1Dark.setBackgroundColor(Color.parseColor("#BBBBBB"));
            //view2Dark.setBackgroundColor(Color.parseColor("#BBBBBB"));
        }
    }
}
