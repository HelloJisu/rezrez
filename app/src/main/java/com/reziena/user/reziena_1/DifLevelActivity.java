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
import android.widget.LinearLayout;
import android.widget.TextView;

public class DifLevelActivity extends AppCompatActivity {
    TextView yes, no;
    public static Activity diflevel;
    String where="";
    LinearLayout imagebutton;
    Intent goIntent;
    TreatActivity_forehead treatforehead = (TreatActivity_forehead) TreatActivity_forehead.treatforehead;
    TreatActivity_underleft treatunderleft = (TreatActivity_underleft) TreatActivity_underleft.treatunderleft;
    TreatActivity_underright treatunderright = (TreatActivity_underright) TreatActivity_underright.treatunderright;
    TreatActivity_cheekright treatcheekright = (TreatActivity_cheekright) TreatActivity_cheekright.treatcheekright;
    TreatActivity_cheekleft treatcheekleft = (TreatActivity_cheekleft) TreatActivity_cheekleft.treatcheekleft;
    TreatActivity_eye treatactivityeye = (TreatActivity_eye)TreatActivity_eye.treateye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dif_level);
        diflevel=DifLevelActivity.this;

        // popupt창 사이즈 지정
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        Intent intent = getIntent();
        if (intent.getExtras()!=null) {
            where = intent.getExtras().getString("whereTreat");
            Log.e("whereTreat??", where);
        }

        where = HomeActivity.whereTreat;
        imagebutton = findViewById(R.id.imageButton);

        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.imageButton:
                case R.id.yes:
                    finish();
                    break;
                case R.id.no:
                    Intent intent1 = new Intent(getApplicationContext(), noActivity.class);
                    switch (where) {
                        case "underright": intent1 = new Intent(getApplicationContext(), TreatActivity_underright2.class); break;
                        case "underleft": intent1 = new Intent(getApplicationContext(), TreatActivity_underleft2.class); break;
                        case "cheekright": intent1 = new Intent(getApplicationContext(), TreatActivity_cheekright2.class); break;
                        case "cheekleft": intent1 = new Intent(getApplicationContext(), TreatActivity_cheekleft2.class); break;
                        case "eyeright": intent1 = new Intent(getApplicationContext(), TreatActivity_eyeright2.class); break;
                        case "eyeleft": intent1 = new Intent(getApplicationContext(), TreatActivity_eyerleft2.class); break;
                        case "forehead": intent1 = new Intent(getApplicationContext(), TreatActivity_foreheadright.class); ;break;
                    }
                    startActivity(intent1);
                    finish();
                    break;
            }
        };
        yes.setOnClickListener(onClickListener);
        no.setOnClickListener(onClickListener);
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

    }
}