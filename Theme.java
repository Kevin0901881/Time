package com.timeapp.time;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


public class Theme extends AppCompatActivity {

    private TextView darkText;
    private TextView lightText;
    private TextView minhrDark;
    private TextView minhrLight;
    private TextView secDark;
    private TextView secLight;

    /*private ImageView dark;
    private ImageView light;*/

    private View viewDark;
    private View viewLight;
    private View backgroundDark;
    private View backgroundLight;

    private Rect rect;
    private Rect rect2;

    public static int theme = 0;

    private SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");

        darkText = (TextView) findViewById(R.id.dark_text);
        darkText.setTypeface(oswaldRegular);
        darkText.setVisibility(View.GONE);
        darkText.setScaleY(0.5f);
        darkText.setScaleX(0.5f);
        lightText = (TextView) findViewById(R.id.light_text);
        lightText.setTypeface(oswaldRegular);
        lightText.setVisibility(View.GONE);
        lightText.setScaleY(0.5f);
        lightText.setScaleX(0.5f);
        minhrDark = (TextView) findViewById(R.id.min_hr_dark);
        minhrDark.setTypeface(oswaldRegular);
        minhrLight = (TextView) findViewById(R.id.min_hr_light);
        minhrLight.setTypeface(oswaldRegular);
        secDark = (TextView) findViewById(R.id.sec_dark);
        secDark.setTypeface(oswaldRegular);
        secLight = (TextView) findViewById(R.id.sec_light);
        secLight.setTypeface(oswaldRegular);

        backgroundDark = findViewById(R.id.background_dark);
        backgroundLight = findViewById(R.id.background_light);

        /*dark = (ImageView) findViewById(R.id.dark);
        light = (ImageView) findViewById(R.id.light);*/

        viewDark = findViewById(R.id.view_dark);
        viewDark.setVisibility(View.GONE);
        viewLight = findViewById(R.id.view_light);
        viewLight.setVisibility(View.GONE);

        backgroundDark.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateinone();
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        animateoutone();
                    } else {
                        theme = 0;
                        sp();
                        animateoutone();
                        onBackPressed();
                    }

                    return true;
                }
                return false;
            }
        });

        backgroundLight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateintwo();
                    rect2 = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!rect2.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        animateouttwo();
                    } else {
                        theme = 1;
                        sp();
                        animateouttwo();
                        onBackPressed();
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void animateinone() {
        darkText.setVisibility(View.VISIBLE);
        viewDark.setVisibility(View.VISIBLE);
        darkText.setAlpha(0f);
        viewDark.setAlpha(0f);

        viewDark.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);

        darkText.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(200)
                .setInterpolator(new OvershootInterpolator())
                .setListener(null);
    }

    private void animateoutone() {
        darkText.animate()
                .scaleX(0.5f)
                .scaleY(0.5f)
                .alpha(0f)
                .setDuration(200)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        darkText.setVisibility(View.GONE);
                    }
                });

        viewDark.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewDark.setVisibility(View.GONE);
                    }
                });
    }

    private void animateintwo() {
        lightText.setVisibility(View.VISIBLE);
        viewLight.setVisibility(View.VISIBLE);
        lightText.setAlpha(0f);
        viewLight.setAlpha(0f);

        viewLight.animate()
                .alpha(1f)
                .setDuration(200)
                .setListener(null);

        lightText.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(200)
                .setInterpolator(new OvershootInterpolator())
                .setListener(null);
    }

    private void animateouttwo() {
        lightText.animate()
                .scaleX(0.5f)
                .scaleY(0.5f)
                .alpha(0f)
                .setDuration(200)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        lightText.setVisibility(View.GONE);
                    }
                });

        viewLight.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewLight.setVisibility(View.GONE);
                    }
                });
    }

    public void sp(){
        editor.putInt("theme", theme);
        editor.commit();
    }
    
    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
