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


public class AlarmSnoozed extends AppCompatActivity {

    private TextView snoozed;

    private RelativeLayout background;

    private int snoozeLength;
    private int theme;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_snoozed);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        theme = sp.getInt("theme", 0);

        background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));

        snoozeLength = sp.getInt("snoozeLength", 3);

        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.parseColor("#373737"));

        snoozed = (TextView) findViewById(R.id.snoozed);
        snoozed.setTypeface(oswaldLight);

        if (snoozeLength == 1){
            snoozed.setText("Snoozed for 2 minutes");
        } else if (snoozeLength == 2){
            snoozed.setText("Snoozed for 5 minutes");
        } else if (snoozeLength == 3){
            snoozed.setText("Snoozed for 10 minutes");
        } else if (snoozeLength == 4){
            snoozed.setText("Snoozed for 15 minutes");
        } else if (snoozeLength == 5){
            snoozed.setText("Snoozed for 30 minutes");
        }

        Animation aa = new AlphaAnimation(1f, 1f);
        aa.setDuration(3000);

        snoozed.startAnimation(aa);
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
            snoozed.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            snoozed.setTextColor(Color.parseColor("#373737"));
        }
    }
}
