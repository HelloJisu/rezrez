package com.reziena.user.reziena_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TreatDoneActivity extends AppCompatActivity {

    LinearLayout imagebutton;
    TextView okay, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_done);

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
                    case R.id.imageButton:
                    case R.id.okay:
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.no:
                        finish();
                        break;
                }
            }
        };
        no.setOnClickListener(onClickListener);
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
        //super.onBackPressed();
    }
}