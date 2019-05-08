package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class NointernetActivity extends AppCompatActivity {
    TextView okay;
    public static Activity skinhistoryactivity;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    LoginActivity loginActivity = (LoginActivity) LoginActivity.loginactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nointernet);
        skinhistoryactivity=NointernetActivity.this;

        // popupt창 사이즈 지정

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        okay = findViewById(R.id.okay);

        Intent intent = getIntent();
        String where = intent.getStringExtra("where");

        Log.e("noInternet", where);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay:
                        /*switch (where) {
                            case "LoginActivity":
                                loginActivity.finish(); break;
                            case "HomeActivity":
                                homeactivity.finish(); break;
                        }*/
                        finishAffinity();
                        break;
                }
            }
        };
        okay.setOnClickListener(onClickListener);
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

    }
}