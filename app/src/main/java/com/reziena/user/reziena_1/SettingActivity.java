package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView okay,no;
    public static Activity bluetoothactivity;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    LinearLayout imagebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bluetoothactivity=SettingActivity.this;


        // popupt창 사이즈 지정

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        okay = findViewById(R.id.okay);
        no = findViewById(R.id.no);
        imagebutton=findViewById(R.id.imageButton);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay:
                        // 로그아웃
                        SharedPreferences sp_userName = getSharedPreferences("userName", MODE_PRIVATE);
                        SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
                        SharedPreferences now_wrinkle = getSharedPreferences("now_w", MODE_PRIVATE);
                        SharedPreferences bef_wrinkle = getSharedPreferences("bef_w", MODE_PRIVATE);
                        SharedPreferences now_moisture = getSharedPreferences("now_m", MODE_PRIVATE);
                        SharedPreferences bef_moisture = getSharedPreferences("bef_m", MODE_PRIVATE);
                        SharedPreferences treat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                        SharedPreferences spSkin = getSharedPreferences("skin", MODE_PRIVATE);

                        SharedPreferences.Editor editor1 = sp_userName.edit();
                        SharedPreferences.Editor editor2 = sp_userID.edit();
                        SharedPreferences.Editor editor3 = now_wrinkle.edit();
                        SharedPreferences.Editor editor4 = bef_wrinkle.edit();
                        SharedPreferences.Editor editor5 = now_moisture.edit();
                        SharedPreferences.Editor editor6 = bef_moisture.edit();
                        SharedPreferences.Editor editor7 = treat_date.edit();
                        SharedPreferences.Editor editor8 = treat_zone.edit();
                        SharedPreferences.Editor editor9 = spSkin.edit();
                        editor1.remove("userName");
                        editor2.remove("userID");
                        editor3.remove("now_w");
                        editor4.remove("bef_w");
                        editor5.remove("now_m");
                        editor6.remove("bef_m");
                        editor7.remove("tDate");
                        editor8.remove("tZone");
                        editor9.remove("skin");

                        editor1.commit();
                        editor2.commit();
                        editor3.commit();
                        editor4.commit();
                        editor5.commit();
                        editor6.commit();
                        editor7.commit();
                        editor8.commit();
                        editor9.commit();
                        Log.e("remove", "yeal~!"); //하기실어

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.putExtra("finish","finish");
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.imageButton:
                    case R.id.no:
                        homeactivity.backgroundimg.setImageResource(0);
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                }
            }
        };
        imagebutton.setOnClickListener(onClickListener);
        okay.setOnClickListener(onClickListener);
        no.setOnClickListener(onClickListener);
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