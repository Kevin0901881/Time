package com.timeapp.time;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

public class ClockSleep extends AppCompatActivity {

    private TextClock minHr;
    private TextClock sec;
    private TextClock date;
    private TextClock year;

    private RelativeLayout clockBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_sleep);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");

        minHr = (TextClock) findViewById(R.id.min_hr);
        minHr.setTypeface(oswaldRegular);
        sec = (TextClock) findViewById(R.id.sec);
        sec.setTypeface(oswaldRegular);
        date = (TextClock) findViewById(R.id.date);
        date.setTypeface(oswaldRegular);
        year = (TextClock) findViewById(R.id.year);
        year.setTypeface(oswaldRegular);

        clockBackground = (RelativeLayout) findViewById(R.id.clock_background);

        clockBackground.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onBackPressed();
                return true;
            }
        });
    }

    public void onResume(){
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
