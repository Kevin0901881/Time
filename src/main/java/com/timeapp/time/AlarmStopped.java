package com.timeapp.time;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AlarmStopped extends AppCompatActivity {

    private RelativeLayout background;

    private TextView stopped;

    private int theme;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_stopped);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        theme = sp.getInt("theme", 0);

        background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));

        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));

        stopped = (TextView) findViewById(R.id.stopped);
        stopped.setTypeface(oswaldLight);

        Animation aa = new AlphaAnimation(1f, 1f);
        aa.setDuration(3000);

        stopped.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Wakelocker.release();
    }

    public void onResume(){
        super.onResume();

        if (theme == 0) {
            background.setBackgroundColor(Color.parseColor("#373737"));
            stopped.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            stopped.setTextColor(Color.parseColor("#373737"));
        }
    }
}
