package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class BluetoothActivity extends AppCompatActivity {
    TextView okay;
    public static Activity bluetoothactivity;
    BTOnActivity btOnActivity = (BTOnActivity) BTOnActivity.btOnActivity;
    BTNoActivity btNoActivity = (BTNoActivity) BTNoActivity.btNoActivity;
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
    TextView deviceBattery;
    public static String battery="";
    TimerTask second;
    String string;
    LinearLayout imageButton;
    ImageView image;
    int sendCount = 0;

    public static boolean Bluetooth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        bluetoothactivity=BluetoothActivity.this;

        Log.e("지금은 ", "BluetoothActivity");
        Log.e("BluetoothActivity", "isConnecting: "+HomeActivity.isConnecting);
        Bluetooth = true;

        // popupt창 사이즈 지정
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        Intent subintent = getIntent();
        string = subintent.getStringExtra("key");

        if(string!=null) {
            Log.e("즐겁네유", string);
        }

        okay = findViewById(R.id.okay);
        deviceBattery = findViewById(R.id.deviceBattery);
        imageButton = findViewById(R.id.imageButton);
        image = findViewById(R.id.image);

        try {
            Log.e("Battery:: ", battery);
            if (Integer.parseInt(battery)<=0) {
                deviceBattery.setText("");
            } else {
                if (Integer.parseInt(battery) <= HomeActivity.lowBattery) {
                    HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                    deviceBattery.setText("Low battery.\n (Automatically turned off\n when below 10%)\n\n Battery : " + battery + "%");
                } else {
                    HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                    deviceBattery.setText("Connected \n\n Battery : " + battery + "%");
                }
            }
        } catch (Exception e) {}

        second = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    HomeActivity.send("0x61");
                    //Log.e("지금은", "TimerTask()이다 "+battery);
                    if (sendCount<10) {
                        //HomeActivity.send("0x61"); // 0x61
                        sendCount++;
                    }
                    image.setImageResource(R.drawable.btconn);
                    try {
                        if (Integer.parseInt(battery)<=0) {
                            deviceBattery.setText("");
                        } else {
                            if (Integer.parseInt(battery) <= HomeActivity.lowBattery) {
                                HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                                deviceBattery.setText("Low battery.\n (Automatically turned off\n when below 10%)\n\n Battery : " + battery + "%");
                            } else {
                                HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                                deviceBattery.setText("Connected \n\n Battery : " + battery + "%");
                            }
                        }
                    } catch (Exception e) {
                        Log.e("BluetoothActivity", "Exception1:: "+e.getMessage());
                    }
                });
            }
        };

        if (HomeActivity.isConn) {
            try {
                HomeActivity.send("0x61"); // 0x61
                Log.e("sending", "0x61");
            } catch (Exception e) {
                Log.e("BluetoothActivity", "Exception2:: "+e.getMessage());
            }

            /*new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Log.e("sending", "1");
                    HomeActivity.send("3"); // 0x61
                }
            }, 500);*/

            // -------------------------------------------------------------------------------------
            /*if (HomeActivity.deviceBattery<=HomeActivity.lowBattery) {
                HomeActivity.imageView2.setImageResource(R.drawable.bdev);
            } else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
            if (HomeActivity.deviceBattery==-1) {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Log.e("sending", "2");
                        HomeActivity.send("3"); // 0x61
                    }
                }, 500);
                deviceBattery.setText("Can't Receive");
                HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);

                // -------------------------------------------------------------------------------------
                if (HomeActivity.deviceBattery<=HomeActivity.lowBattery) {
                    HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                } else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                if (HomeActivity.deviceBattery==-1) {
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Log.e("sending", "3");
                            HomeActivity.send("3"); // 0x61
                        }
                    }, 500);
                    deviceBattery.setText("Can't Receive");
                    HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);

                    // -------------------------------------------------------------------------------------
                    if (HomeActivity.deviceBattery<=HomeActivity.lowBattery) {
                        HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                    } else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                    if (HomeActivity.deviceBattery==-1) {
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Log.e("sending", "3");
                                HomeActivity.send("3"); // 0x61
                            }
                        }, 500);
                        deviceBattery.setText("Can't Receive");
                        HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
                    }
                }
            }*/

            /*while(HomeActivity.deviceBattery==-1 && sendCount<4) {
                HomeActivity.send("1"); // 0x61
                sendCount++;

                Log.e("battery send: ", String.valueOf(sendCount));
                Log.e("battery: ", HomeActivity.deviceBattery+"");
            }*/
            /*if (HomeActivity.deviceBattery==-1) {
                BluetoothLeService.disconnect();
                finish();
            }*/
            Timer timer = new Timer();
            timer.schedule(second, 30, 500);
        } else {
            deviceBattery.setText("Disconnected");
            HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay: case R.id.imageButton:
                        homeactivity.backgroundimg.setImageResource(0);
                        if (string!=null) {
                            if(string.equals("dash")){
                                homeactivity.dashback.setImageResource(0);
                            }
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
                        } else finish();
                        break;
                }
            }
        };
        okay.setOnClickListener(onClickListener);
        imageButton.setOnClickListener(onClickListener);
    }



    public boolean dispatchTouchEvent(MotionEvent ev) {
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
        if(string.equals("dash")){
            homeactivity.dashback.setImageResource(0);
        }
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

    protected void onPause() {
        super.onPause();
        Bluetooth = false;
        try {
            second.cancel();
        } catch (Exception e) {
            Log.e("BluetoothActivity", "Exception:: " + e.getMessage());
        }
        finish();
    }
}