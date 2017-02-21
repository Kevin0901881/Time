package com.timeapp.time;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.LineNumberReader;

public class About extends AppCompatActivity {

    private TextView aboutText;
    private TextView time;
    private TextView description;
    private TextView version;
    private TextView copyright;

    private ImageView logo;

    private RelativeLayout title;
    private RelativeLayout background;
    private LinearLayout versionCopyright;

    private int theme;

    private SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private String appName = "com.timeapp.time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
        }

        background = (RelativeLayout) findViewById(R.id.background);

        sp = getSharedPreferences(appName, MODE_PRIVATE);
        editor = getSharedPreferences(appName, MODE_PRIVATE).edit();

        Typeface oswaldRegular = Typeface.createFromAsset(getAssets(), "fonts/oswaldRegular.ttf");
        Typeface oswaldLight = Typeface.createFromAsset(getAssets(), "fonts/oswaldLight.ttf");

        aboutText = (TextView) findViewById(R.id.about_text);
        aboutText.setTypeface(oswaldRegular);
        time = (TextView) findViewById(R.id.time);
        time.setTypeface(oswaldRegular);
        description = (TextView) findViewById(R.id.description);
        description.setTypeface(oswaldLight);
        version = (TextView) findViewById(R.id.version);
        version.setTypeface(oswaldLight);
        copyright = (TextView) findViewById(R.id.copyright);
        copyright.setTypeface(oswaldLight);

        logo = (ImageView) findViewById(R.id.logo);

        title = (RelativeLayout) findViewById(R.id.title);
        versionCopyright = (LinearLayout) findViewById(R.id.version_copyright);

        Animation up = AnimationUtils.loadAnimation(this, R.anim.time_up_animation);
        up.setStartOffset(500);
        up.setDuration(200);

        title.startAnimation(up);
        versionCopyright.startAnimation(up);

        logo.setScaleX(0.6f);
        logo.setScaleY(0.6f);
        logo.setAlpha(0f);
        logo.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .setStartDelay(500)
                .setInterpolator(new DecelerateInterpolator(2))
                .setListener(null);
    }

    public void onResume(){
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        theme = sp.getInt("theme", 0);

        if (theme == 0) {
            background.setBackgroundColor(Color.parseColor("#373737"));
            aboutText.setTextColor(Color.parseColor("#252525"));
            time.setTextColor(Color.parseColor("#FFFFFF"));
            description.setTextColor(Color.parseColor("#FFFFFF"));
            version.setTextColor(Color.parseColor("#FFFFFF"));
            copyright.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (theme == 1) {
            background.setBackgroundColor(Color.parseColor("#E8E8E8"));
            aboutText.setTextColor(Color.parseColor("#BBBBBB"));
            time.setTextColor(Color.parseColor("#373737"));
            description.setTextColor(Color.parseColor("#373737"));
            version.setTextColor(Color.parseColor("#373737"));
            copyright.setTextColor(Color.parseColor("#373737"));
        }
    }
}
