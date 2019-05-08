package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reziena.user.reziena_1.HomeActivity;
import com.reziena.user.reziena_1.R;

public class ExplainActivity extends AppCompatActivity {

    ViewPager vp;
    TextView txtpop;
    int a=0;
    String string;
    ImageView popup,e1,e2,e3,e4, g1, g2, g3, g4;
    public static Activity explainactivity;
    LinearLayout imagebutton;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    TreatActivity_forehead treatforehead = (TreatActivity_forehead) TreatActivity_forehead.treatforehead;
    TreatActivity_underleft treatunderleft = (TreatActivity_underleft) TreatActivity_underleft.treatunderleft;
    TreatActivity_underright treatunderright = (TreatActivity_underright) TreatActivity_underright.treatunderright;
    TreatActivity_cheekright treatcheekright = (TreatActivity_cheekright) TreatActivity_cheekright.treatcheekright;
    TreatActivity_cheekleft treatcheekleft = (TreatActivity_cheekleft) TreatActivity_cheekleft.treatcheekleft;
    TreatActivity_eye treatactivityeye = (TreatActivity_eye)TreatActivity_eye.treateye;
    TreatActivity_cheekleft2 cheekleft = (TreatActivity_cheekleft2) TreatActivity_cheekleft2.cheekleftactivity;
    TreatActivity_cheekright2 cheekright = (TreatActivity_cheekright2) TreatActivity_cheekright2.cheekrightactivity;
    TreatActivity_underleft2 underleft = (TreatActivity_underleft2) TreatActivity_underleft2.underleftativity;
    TreatActivity_underright2 underright = (TreatActivity_underright2) TreatActivity_underright2.underrightactivity;
    TreatActivity_foreheadright foreheadright = (TreatActivity_foreheadright) TreatActivity_foreheadright.foreheadrightactivity;
    TreatActivity_foreheadleft foreheadleft = (TreatActivity_foreheadleft) TreatActivity_foreheadleft.foreheadleftactivity;
    TreatActivity_eyeright2  eyeright= (TreatActivity_eyeright2) TreatActivity_eyeright2.eyerightactivity;
    TreatActivity_eyerleft2 eyeleft = (TreatActivity_eyerleft2) TreatActivity_eyerleft2.eyeleftactivity;
    TreatActivity treatActivity = (TreatActivity) TreatActivity.treatactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        explainactivity= ExplainActivity.this;

        Intent subintent = getIntent();
        string = subintent.getStringExtra("key");

        if(string!=null) {
            Log.e("즐겁네유", string);
        }

        // popupt창 사이즈 지정

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        vp = findViewById(R.id.vp);
        imagebutton=findViewById(R.id.imageButton);

        g1=findViewById(R.id.guide1);
        g2=findViewById(R.id.guide2);
        g3=findViewById(R.id.guide3);
        g4=findViewById(R.id.guide4);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        int scrollState=0;
        int targetPage= 0;
        int prevPage= 0;

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while(i<4)
                {
                    if(position==0)
                    {
                        g1.setImageResource(R.drawable.gellipse2);
                        g2.setImageResource(R.drawable.gellipse1);
                        g3.setImageResource(R.drawable.gellipse1);
                        g4.setImageResource(R.drawable.gellipse1);
                    }
                    if(position==1)
                    {
                        g1.setImageResource(R.drawable.gellipse1);
                        g2.setImageResource(R.drawable.gellipse2);
                        g3.setImageResource(R.drawable.gellipse1);
                        g4.setImageResource(R.drawable.gellipse1);
                    }
                    if(position==2)
                    {
                        g1.setImageResource(R.drawable.gellipse1);
                        g2.setImageResource(R.drawable.gellipse1);
                        g3.setImageResource(R.drawable.gellipse2);
                        g4.setImageResource(R.drawable.gellipse1);
                    }
                    if(position==3)
                    {
                        g1.setImageResource(R.drawable.gellipse1);
                        g2.setImageResource(R.drawable.gellipse1);
                        g3.setImageResource(R.drawable.gellipse1);
                        g4.setImageResource(R.drawable.gellipse2);
                    }
                    i++;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageButton:
                        if(string.equals("treat")){
                            treatActivity.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("cheekright")){
                            treatcheekright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("cheekleft")){
                            treatcheekleft.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("forehead")){
                            treatforehead.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("eye")){
                            treatactivityeye.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("underright")){
                            treatunderright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("underleft")){
                            treatunderleft.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("underleft2")){
                            underleft.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("underright2")){
                            underright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("cheekright2")){
                            cheekright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("cheekleft2")){
                            cheekleft.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("foreheadright")){
                            foreheadright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("foreheadleft")){
                            foreheadleft.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("eyeright")){
                            eyeright.backgroundimg.setImageResource(0);
                        }
                        if(string.equals("eyeleft")){
                            eyeleft.backgroundimg.setImageResource(0);
                        }
                        finish();
                        break;
                }
            }
        };
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
        if(string.equals("treat")){
            treatActivity.backgroundimg.setImageResource(0);
        }
        if(string.equals("cheekright")){
            treatcheekright.backgroundimg.setImageResource(0);
        }
        if(string.equals("cheekleft")){
            treatcheekleft.backgroundimg.setImageResource(0);
        }
        if(string.equals("forehead")){
            treatforehead.backgroundimg.setImageResource(0);
        }
        if(string.equals("eye")){
            treatactivityeye.backgroundimg.setImageResource(0);
        }
        if(string.equals("underright")){
            treatunderright.backgroundimg.setImageResource(0);
        }
        if(string.equals("underleft")){
            treatunderleft.backgroundimg.setImageResource(0);
        }
        if(string.equals("underleft2")){
            underleft.backgroundimg.setImageResource(0);
        }
        if(string.equals("underright2")){
            underright.backgroundimg.setImageResource(0);
        }
        if(string.equals("cheekright2")){
            cheekright.backgroundimg.setImageResource(0);
        }
        if(string.equals("cheekleft2")){
            cheekleft.backgroundimg.setImageResource(0);
        }
        if(string.equals("foreheadright")){
            foreheadright.backgroundimg.setImageResource(0);
        }
        if(string.equals("foreheadleft")){
            foreheadleft.backgroundimg.setImageResource(0);
        }
        if(string.equals("eyeright")){
            eyeright.backgroundimg.setImageResource(0);
        }
        if(string.equals("eyeleft")){
            eyeleft.backgroundimg.setImageResource(0);
        }
        finish();
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Explain1Activity();
                case 1:
                    return new Explain2Activity();
                case 2:
                    return new Explain3Activity();
                case 3:
                    return new Explain4Activity();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }
    }
}