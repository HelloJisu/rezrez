package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingDashActivity extends AppCompatActivity {
    TextView okay;
    public static Activity bluetoothactivity;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    TextView center, setting;
    String string;
    LinearLayout imagebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingdash);
        bluetoothactivity=SettingDashActivity.this;

        center=findViewById(R.id.center);
        okay = findViewById(R.id.okay);
        setting = findViewById(R.id.header_txt);
        imagebutton = findViewById(R.id.imageButton);

        // popupt창 사이즈 지정

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        Intent subintent = getIntent();
        string = subintent.getStringExtra("string");

        if(string.equals("about")){
            String str = "Young &be is an rejuvenating solution for everyone wishing for youthful lookinf skin. The Young &be device is connected to Young &be application to track usage and give personalized care guide for different skin type and condition.\n\nwww.reziena.com";
            setting.setText("About us");
            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
            ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 232, 248, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            center.setText(ssb);
        }
        if(string.equals("product")){
            center.setText("More Product");
            setting.setText("More Product");
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay:
                        homeactivity.backgroundimg.setImageResource(0);
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                    case R.id.imageButton:
                        homeactivity.backgroundimg.setImageResource(0);
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                }
            }
        };
        okay.setOnClickListener(onClickListener);
        imagebutton.setOnClickListener(onClickListener);
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        homeactivity.backgroundimg.setImageResource(0);
        homeactivity.dashback.setImageResource(0);
    }
}