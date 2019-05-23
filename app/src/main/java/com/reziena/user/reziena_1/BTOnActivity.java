package com.reziena.user.reziena_1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;import java.util.UUID;

import static com.reziena.user.reziena_1.BluetoothLeService.ACTION_DATA_AVAILABLE;
import static com.reziena.user.reziena_1.BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED;
import static com.reziena.user.reziena_1.BluetoothLeService.EXTRA_DATA;

public class BTOnActivity extends AppCompatActivity {

    static BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    public static Activity btOnActivity;
    public static int countDown;
    TextView txt;
    Handler mHandler;
    Thread t;
    TextView no_retry;
    private static String action="";
    static BluetoothDevice device;
    static TimerTask timerTask;
    LinearLayout imageButton;
    String text;
    ImageView image;
    static Context mcontext;
    static BluetoothGatt mBluetoothGatt;

    static boolean found = false;

    static private BluetoothLeScanner mBLEScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bton);

        mcontext = getApplicationContext();

        txt = findViewById(R.id.txt);
        no_retry = findViewById(R.id.no_retry);
        imageButton = findViewById(R.id.imageButton);
        image = findViewById(R.id.image);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
        Log.e("지금은 ", "BTOnActivity");
        HomeActivity.BTOn = true;

        mHandler = new Handler();

        Intent intent = getIntent();
        String get = intent.getStringExtra("key");
        if (get!=null) {
            if (get.equals("first")) {
                image.setImageResource(R.drawable.btno);
                text = "Turn on the device and \n please wait. \n\n";
            } else if (get.equals("again")){
                image.setImageResource(R.drawable.btno);
                text = "Are you ready to connect the device? \n Turn on the device and please wait.\n\n";
            }
        } else {
            image.setImageResource(R.drawable.btno);
            text = "Turn on the device and \n please wait. \n\n";
        }

        try {
            startThread();
        } catch (Exception e) {
            Log.e("BTOnActivity", "startThread Exception:: " +e.getMessage());
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.no_retry: case R.id.imageButton:
                        HomeActivity.isConnecting = false;
                        try {
                            homeactivity.dashback.setImageResource(0);
                        } catch (Exception e) {
                            Log.e("BTOnActivity", "setImageResource Exception:: " +e.getMessage());
                        }
                        finish();
                        break;
                    /*case R.id.retry:
                        intent = new Intent(getApplicationContext(), BTOnActivity.class);
                        startActivity(intent);
                        finish();
                        //txt2.setText(countDown+"초");
                        //retry.setVisibility(View.INVISIBLE);
                        //no_retry.setVisibility(View.INVISIBLE);
                        //startThread();
                        break;*/
                }
            }
        };
        no_retry.setOnClickListener(onClickListener);
        imageButton.setOnClickListener(onClickListener);
    }

    private void startThread() {
        Log.e("init", "startThread");
        countDown = 45;

        txt.setText(text+countDown+"/45");

        timerTask = new TimerTask() {
            @Override
            public void run() {
                countDown--;
                Log.e("countdown", String.valueOf(countDown));
                // UI 작업 X

                // UI 작업 O
                runOnUiThread(() -> {
                    txt.setText(text+countDown+"/45");
                    if (countDown<=0 || HomeActivity.isConn) {
                        timerTask.cancel();
                        Intent intent;
                        if (HomeActivity.isConn) {
                            intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        } else {
                            // 못 찾음
                            intent = new Intent(getApplicationContext(), BTNoActivity.class);
                            intent.putExtra("where", "no");
                        }
                        startActivity(intent);
                        //finish();
                    }
                });
            } // end of run
        }; // end of timerTask
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);

        Log.e("BTOnActivity", "disconnect: "+HomeActivity.disconnect);
        Log.e("BTOnActivity", "isConnecting: "+HomeActivity.isConnecting);
        if (HomeActivity.disconnect > 3 || !HomeActivity.isConnecting) {
            Log.e("BTOnActivity", "discoveryStart()");
            discoveryStart();
        }
    }

    public static void discoveryStart() {

        List<ScanFilter> filters= new ArrayList<>();
        ScanFilter scan_filter= new ScanFilter.Builder()
                .setServiceUuid( new ParcelUuid( HomeActivity.Nordic_UART_Service ) )
                //.setDeviceName("Young&be")
                .build();
        filters.add( scan_filter );

        ScanSettings settings= new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER )
                .build();

        mBLEScanner = mBtAdapter.getBluetoothLeScanner();
        mBLEScanner.startScan(Collections.singletonList(scan_filter), settings, mScanCallback);

        //HomeActivity.mBluetoothLeService.disconnect();
        try {
            HomeActivity.mBluetoothLeService.connect(HomeActivity.devAdd);
        } catch (Exception e) {
            Log.e("Exception", "서비스를 찾을 수 없습니다.");
            Toast toast = Toast.makeText(mcontext, "서비스를 찾을 수 없습니다 \n 기기를 켜주세요", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private static ScanCallback mScanCallback = new ScanCallback() {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            processResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult result : results) {
                processResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {}

        public void processResult(final ScanResult result) {

            String devInfo = String.valueOf(result);
            int find = 0;
            Log.e("find", String.valueOf(result.getDevice()));
            if (devInfo.contains(HomeActivity.devName)) {
                found = true;
                find++;

                HomeActivity.devAdd = String.valueOf(result.getDevice());
                device = result.getDevice();
                if (find==1) {
                    Log.e("find_device____", devInfo);
                    try {
                        mBLEScanner.stopScan(mScanCallback);
                        Log.e("stopScan", "stopped");
                    } catch (Exception e) {
                        Log.e("stopScan", "error"+e.getMessage());
                    }
                    if (!HomeActivity.mBluetoothLeService.initialize())
                        Log.e("mBluetoothLeService", "not initialize");
                    if (!HomeActivity.isConn)
                        HomeActivity.mBluetoothLeService.connect(HomeActivity.devAdd);
                }
            }
        }
    };

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        HomeActivity.BTOn = false;
        try {
            timerTask.cancel();
        } catch (Exception e) {
            Log.e("BTOnActivity", "Exception:: "+e.getMessage());
        }
        finish();
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }
}