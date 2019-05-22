package com.reziena.user.reziena_1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.renderscript.RenderScript;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reziena.user.reziena_1.utils.RSBlurProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.UUID;

import at.grabner.circleprogress.CircleProgressView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomeActivity extends AppCompatActivity {

    public static boolean isHave=false;
    public static boolean isConn =false;

    public static String kind = "";
    public static int receiveResult = 0;

    public static String sendMessage = null;

    public static boolean isConnecting = false;

    public static int sendCount = 1;

    LoginActivity loginActivity = (LoginActivity) LoginActivity.loginactivity;
    Signin2Activity signin2 = (Signin2Activity) Signin2Activity.signin2;
    private static final int REQUEST_BLUETOOTH = 1;
    public static Handler mHandler;
    boolean measureWrinkle=false;
    String start;
    int last=0;
    public static int lowBattery = 15;
    CircleProgressView mois_c,oiㅣ_c,res_c,elas_c,anti_c, mois_c2,oiㅣ_c2,res_c2,elas_c2,anti_c2;

    public static Context mcontext;

    public static BluetoothDevice device;

    public static String wrinkleLevel="";
    String lastSkinDate = "0000-00-00";

    static int max=0;
    public static String where=null;
    public static String whereTreat=null;
    public static int countStart=0;
    public static String staticLevel=null, checkDiff;

    // 스마트폰끼리의 UUID
    public static final UUID Nordic_UART_Descriptor = UUID.fromString("00002902-0000-1000-8000-00805F9B34");
    public static final UUID Nordic_UART_Service = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID Nordic_UART_RX = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID Nordic_UART_TX = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID Nordic_UART_BATTERY = UUID.fromString("00002a19-0000-1000-8000-00805f9b34fb");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public static final String devName = "Young&be";
    public static String devAdd;
    public static BluetoothGatt mBluetoothGatt;
    private ExpandableListView mGattServicesList;

    private static final int REQUEST_ENABLE_BT = 5;
    public static BluetoothLeService mBluetoothLeService;

    public static byte data=0x61;

    int count;
    public static int deviceLevel=0;

    Animation alphaback;
    RenderScript rs, rs2;
    Bitmap blurBitMap, blurBitMap2;
    private static Bitmap bitamp, bitamp2;
    LoginActivity loginactivity = (LoginActivity) LoginActivity.loginactivity;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinklemain_txt;
    public static Activity homeactivity;
    String wrinkle_string;
    RelativeLayout design_bottom_sheet,moisture,wrinkles,close;
    LinearLayout toolbar,toolbar_dash, historyBtn, dashall2,historylayout,historylayout2,historyclose,historyc,treatclose,closedash;
    LinearLayout home1,home2,home3,home4,home5,home8,home9,dashall,ha1,ha2,ha3,ha4,ha5,ha6,ha7,dashall3,dashall4,treatbtn,more;
    ImageView logo,backgroundimg,dashback,arrow,arrow2,logimg,aboutimg;
    CircleImageView image_main;
    BottomSheetBehavior bottomSheetBehavior;
    TextView  moisture_score_main, wrinkle_score_main, skintype_main, skintype, doneday, missday, percent,profile,product,logout, about, xcount, checkcount;
    TextView goldString,silverString,bronzeString,weektxt,weekof;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;
    private Uri mImageCaptureUri;
    String wrinklestringg;
    String finish;

    static private BluetoothLeScanner mBLEScanner;
    private static BluetoothAdapter mBtAdapter;

    TextView home_setName, showLastSkinDate;

    ImageView[] crown = new ImageView[4];

    int moisture_per=0, wrinkle_per=0;

    ImageView[] check = new ImageView[7];
    public static ImageView imageView2;
    int max_mois, max_wrink;

    private String userName;
    public static String IP_Address = "52.32.36.182";

    public static ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<>();
    public static List<BluetoothGattCharacteristic> characteristics;
    private static BluetoothGattCharacteristic mNotifyCharacteristic;
    public static List<BluetoothGattCharacteristic> gattCharacteristics;
    static BluetoothGattCharacteristic characteristic = null;
    ArrayList<BluetoothGattCharacteristic> charas;

    private String DB_skintype="", DB_moisture="", DB_wrinkle="";
    int countCheck = -1;
    public static int deviceBattery = -1;

    int find = 0;
    public static boolean isFirst = true;

    public static boolean isBound = false;

    public static int disconnect=0;

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";
    private static boolean iConnected;

    private void checkPermission() {
        int permissionLOCATION = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permissionLOCATION == PackageManager.PERMISSION_GRANTED) {
            discoveryStart();
        }
        else
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_BLUETOOTH);
    }

    public static void setNotification() {
        Log.e("setNotification", "init");
        //sendMessage = "0x61";

        if (mGattCharacteristics != null) {
            Log.e("mGattCharacteristics", "size"+String.valueOf(mGattCharacteristics.size()));
            if (isConn) {
                Log.e("isConn", "true");
                int k = 0;
                for (int i = 0; i < mGattCharacteristics.size() - 1; i++) {
                    switch (i) {
                        case 0: k = 4; break; case 1: k = 0; break; case 2: k = 2; break;
                        case 3: k = 1; break; case 4: k = 3; break; case 5: k = 1; break;
                    }
                    for (int j = 0; j < k; j++) {
                        if (mGattCharacteristics.get(i).get(j).getUuid().equals(Nordic_UART_RX)) {
                            characteristic = mGattCharacteristics.get(i).get(j);
                        }
                    }
                }
                Log.e("characteristic", characteristic.getUuid().toString());
                final int charaProp = characteristic.getProperties();
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                    if (mNotifyCharacteristic != null) {
                        BluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, true);
                        mNotifyCharacteristic = null;
                    }
                    Log.e("readCharacteristic", "호출");
                    mBluetoothLeService.readCharacteristic(characteristic);
                }
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {

                    Log.e("enableTXNotification", "호출");
                    mNotifyCharacteristic = characteristic;
                    mBluetoothLeService.enableTXNotification();
                }
            }
        }
    }

    public static void send(String m) {
        if (characteristic!=null) {
            if (m.contains("/")) {
                where = m.split("/")[0];
                m = m.split("/")[1];
            }

            sendMessage = m;

            switch (sendMessage) {
                case "0x22": data=0x22; break;
                case "0x31": data=0x31; break;
                case "0x34": data=0x34; break;
                case "0x61": data=0x61; break;
            }

            Toast.makeText(mcontext, "SEND: "+sendMessage, Toast.LENGTH_SHORT).show();
            Log.e("send", sendMessage+"-----------------------------------------------");

            final int charaProp = characteristic.getProperties();
            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                mNotifyCharacteristic = characteristic;

                mBluetoothLeService.writeRXCharacteristic(data);
                mBluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, true);
            }
        }
    }

    private void checkBT() {
        // 장치가 블루투스 지원하지 않는 경우
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.e("STATUS", "BLE 지원 불가능");
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle("BLE 지원 안됨");
            builder.setMessage("블루투스가 지원이 안됩니다,");
            builder.setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();
            finish();
        }
        // 블루투스가 꺼져있으면 사용자에게 블루투스 활성화를 요청한다
        if (!mBtAdapter.isEnabled()) {
            Log.e("STATUS", "BLE 비활성상태");
            Toast.makeText(getApplicationContext(), "BLE 비활성상태", Toast.LENGTH_SHORT).show();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);  // 뒤에것은 어떤 요청인지 알기 위해
        } else {
            checkPermission();
        }
    }

    private void discoveryStart() {
        Log.e("discoveryStart()", "init");

        List<ScanFilter> filters= new ArrayList<>();
        ScanFilter scan_filter= new ScanFilter.Builder()
                .setServiceUuid( new ParcelUuid(Nordic_UART_Service) )
                //.setDeviceName("Young&be")
                .build();
        filters.add( scan_filter );

        ScanSettings settings= new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER )
                .build();

        mBLEScanner = mBtAdapter.getBluetoothLeScanner();

        mBtAdapter.getProfileConnectionState(BluetoothAdapter.STATE_CONNECTED);
        mBLEScanner.startScan(Collections.singletonList(scan_filter), settings, mScanCallback);
        isConnecting = true;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String getTime = sdf.format(date);
        Log.e("BT Time", "startScan: "+getTime);
    }

    private ScanCallback mScanCallback = new ScanCallback() {

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

        private void processResult(final ScanResult result) {

            String devInfo = String.valueOf(result);
            Log.e("find__", String.valueOf(result.getDevice()));
            if (devInfo.contains(devName)) {
                find++;
                Log.e("find__", find+" / "+String.valueOf(result.getDevice()));
                devAdd = String.valueOf(result.getDevice());
                device = result.getDevice();
                if (find==1) {

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                    String getTime = sdf.format(date);
                    Log.e("BT Time", "findDevice: "+getTime);
                    Log.e("find_device____", devInfo);

                    Intent gattServiceIntent = new Intent(getApplicationContext(), BluetoothLeService.class);
                    Log.e("HomeActivity", "isBound::"+isBound);

                    /*if (!isBound) {
                        try {
                            isBound = bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                            Log.e("isBound", isBound+"");
                        } catch(Exception e) {
                            Log.e("HomeActivity", "Exception:: "+e.getMessage());
                        }
                    } else {
                        if (!mBluetoothLeService.initialize())
                            finish();
                        if (!isConn) mBluetoothLeService.connect(devAdd);
                    }*/

                    try {
                        isBound = bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                        Log.e("isBound", isBound+"");
                    } catch(Exception e) {
                        Log.e("HomeActivity", "Exception:: "+e.getMessage());
                    }

                    try {
                        mBLEScanner.stopScan(mScanCallback);
                        Log.e("stopScan", "stopped");
                        return;
                    } catch (Exception e) {
                        Log.e("stopScan", "error"+e.getMessage());
                    }
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), BTOnActivity.class);
                startActivity(intent);
            }
        }
    };

    protected void onDestroy() {
        super.onDestroy();

        disconnectGattServer();
        Log.e("HomeActivity", "onDestroy, isBound:: "+isBound);
        Intent intent = new Intent(getApplicationContext(), BluetoothLeService.class);
        if (isBound) mBluetoothLeService.onUnbind(intent);
    }

    private ScanCallback mStopCallback = new ScanCallback() {

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

        private void processResult(final ScanResult result) {
            Log.e("Stop Scan", result+"");
        }
    };

    private void bond() {
        Log.e("init: ", "bonding!!;");
        /** Filtering Broadcast Receiver */
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        /** Start Broadcast Receiver */
        this.registerReceiver(mBroadcastReceiver, filter);
    }

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.e("BT", "onReceive: ACTION____________come in mBroadcastReceiver2");
            Log.e("action", action);

            device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device.getBondState()==BluetoothDevice.BOND_NONE) {
                Log.e("BT", "BOND_NONE");
            } else if (device.getBondState()==BluetoothDevice.BOND_BONDED) {
                Log.e("bondedState?!", "BOND_BONDED");
                //finish();
            }
        }
    };

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            String getTime = sdf.format(date);
            Log.e("BT Time", "serviceConnected: "+getTime);
            Log.e("mScanCallback", "onServiceConnected / init");
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize())
                finish();
            if (!isConn) mBluetoothLeService.connect(devAdd);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("mScanCallback", "onServiceDisconnected / init");
            mBluetoothLeService = null;
            disconnectGattServer();
            discoveryStart();
        }

        public void onBindingDied(ComponentName name) {
            Log.e("onBindingDied", "init");
        }

        public void onNullBinding(ComponentName name) {
            Log.e("onNullBinding", "init");
        }
    };

    public static void disconnectGattServer() {
        Log.e("disconnectGattServer", "init / HOme");
        isConn = false;
        if( mBluetoothGatt != null ) {
            //gattCharacteristics = null;
            //characteristics = null;
            mBluetoothGatt.disconnect();
            mBluetoothGatt.close();
            //mGattCharacteristics = new ArrayList<>();
            deviceBattery = -1;

            disconnect++;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_BLUETOOTH:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("PermissionResult", "permission granted");
                    discoveryStart();
                } else {//거부했을 경우
                    Toast toast = Toast.makeText(this, "기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    Log.e("BT 활성화 ", "OK");
                    checkPermission();
                } else if (resultCode==RESULT_CANCELED) {
                    Log.e("BT 활성화 ", "NO");
                } else Log.e("BT 활성화 ", resultCode+"");
                break;
        }
    }

    @Override
    public void onStart() { super.onStart(); }

    // calling Moisture
    class GetData1 extends AsyncTask<String, Void, String> {
        String level="";
        int now_m=-8;

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            if (getResult==null) {
                //Log.e("getdata-moisture", "getResult==null");
                level = "-";
            } else {
                //Log.e("getdata-moisture", "getResult=="+getResult);
                if (getResult.contains("No_results")||getResult.contains("nul")) {
                    level = "-";
                } else {
                    showResult(getResult);

                    // 가져온 현재 모이스처 저장하기
                    SharedPreferences now_moisturse = getSharedPreferences("now_m", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = now_moisturse.edit();
                    editor1.putInt("now_m", now_m);
                    editor1.commit();
                    //Log.e("now_m ", level+"퍼센트");
                }
            }
            moisture_score_main.setText(DB_moisture);

            //Log.e("moisture",DB_moisture);

        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;
            //Log.e("moisture-userID", userID);

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("moisture-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    //Log.e("moisture-response", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    //Log.e("moisture-response", "code - HTTP_NOT_OK - " + responseStatusCode);
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("moisture-error-stream", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    now_m = item.getInt("level");
                    max_mois = item.getInt("id");
                    max_mois -= 1;
                    //Log.e("moisture-level ", DB_moisture+"!!!!!!!!!!");
                }
                //Log.e("moisture::::", String.valueOf(moisture_per));

                if (now_m!=-8) {
                    if (now_m<=18) level = "C";
                    if (now_m>=20 && now_m<=40) level="C+";
                    if (now_m>=42 && now_m<=48) level="B";
                    if (now_m>=50 && now_m<=57) level="B+";
                    if (now_m>=60 && now_m<=68) level="A";
                    if (now_m>=71 && now_m<=99) level="A+";

                }
                moisture_score_main.setText(level);

            } catch (JSONException e) {
                //Log.e("moisture-JSON", "showResult : ", e);
            }

        }
    }

    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }

        homeactivity = HomeActivity.this;
        loginActivity.finish();
        mcontext = getApplicationContext();
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        Log.e("checkBT", "oncreate");
        checkBT();

        // 값 받아오기\
        weekof=findViewById(R.id.weekof);
        xcount=findViewById(R.id.xcount);
        checkcount=findViewById(R.id.checkcount);
        goldString=findViewById(R.id.goldString);
        silverString=findViewById(R.id.silverString);
        bronzeString=findViewById(R.id.bronzeString);
        dashall=findViewById(R.id.dashall);
        dashall2 = findViewById(R.id.dashall2);
        dashall3 = findViewById(R.id.dashall3);
        moisture = findViewById(R.id.moisture);
        wrinkles = findViewById(R.id.wrinkle);
        toolbar=findViewById(R.id.toolbar);
        toolbar_dash = findViewById(R.id.toolbar_dash);
        design_bottom_sheet = findViewById(R.id.design_bottom_sheet);
        image_main = findViewById(R.id.image_main); // profile
        treatbtn = findViewById(R.id.treatBtn);
        moisture_score_main = findViewById(R.id.moisture_result_main);
        wrinkle_score_main = findViewById(R.id.wrinkle_result_main);
        logo = findViewById(R.id.logo);
        historyBtn = findViewById(R.id.historyBtn);
        check[0] = findViewById(R.id.check1);
        check[1] = findViewById(R.id.check2);
        check[2] = findViewById(R.id.check3);
        check[3] = findViewById(R.id.check4);
        check[4] = findViewById(R.id.check5);
        home1=findViewById(R.id.home1);
        home2=findViewById(R.id.home2);
        home9=findViewById(R.id.home9);
        backgroundimg=findViewById(R.id.backgroundimage);
        dashback=findViewById(R.id.dashback);
        skintype_main=findViewById(R.id.skintype_main);
        home_setName = findViewById(R.id.home_setName);
        ha1=findViewById(R.id.ha1);
        ha2=findViewById(R.id.ha2);
        ha3=findViewById(R.id.ha3);
        ha4=findViewById(R.id.ha4);
        ha5=findViewById(R.id.ha5);
        ha6=findViewById(R.id.ha6);
        ha7=findViewById(R.id.ha7);
        dashall3=findViewById(R.id.dashall3);
        arrow=findViewById(R.id.arrow);
        skintype=findViewById(R.id.skintype);
        mois_c=findViewById(R.id.moisture_c);;
        oiㅣ_c=findViewById(R.id.oil_c);
        res_c=findViewById(R.id.res_c);
        elas_c=findViewById(R.id.elas_c);
        anti_c=findViewById(R.id.anti);
        mois_c2=findViewById(R.id.moisture_c2);;
        oiㅣ_c2=findViewById(R.id.oil_c2);
        res_c2=findViewById(R.id.res_c2);
        elas_c2=findViewById(R.id.elas_c2);
        anti_c2=findViewById(R.id.anti2);
        historylayout=findViewById(R.id.historylayer);
        historylayout2=findViewById(R.id.historylayer2);
        historyclose=findViewById(R.id.historybtnw);
        historyc=findViewById(R.id.historyclose);
        treatclose=findViewById(R.id.treatclose);
        doneday=findViewById(R.id.doneday);
        missday=findViewById(R.id.missday);
        percent=findViewById(R.id.percent);
        close=findViewById(R.id.close);
        logout=findViewById(R.id.logout);
        profile = findViewById(R.id.profile);
        product = findViewById(R.id.product);
        about = findViewById(R.id.about);
        closedash=findViewById(R.id.closedash);
        aboutimg = findViewById(R.id.aboutimg);
        logimg = findViewById(R.id.logimg);
        weektxt = findViewById(R.id.weektxt);

        crown[0] = findViewById(R.id.first);
        crown[1] = findViewById(R.id.second);
        crown[2] = findViewById(R.id.third);
        crown[3] = findViewById(R.id.fourth);

        more = findViewById(R.id.more);
        showLastSkinDate = findViewById(R.id.showLastSkinDate);

        treatbtn.setClickable(true);
        historyBtn.setClickable(true);

        moisture.setVisibility(View.INVISIBLE);
        wrinkles.setVisibility(View.INVISIBLE);

        String dialogstring;

        imageView2 = findViewById(R.id.imageView2);

        mHandler = new Handler();

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int height = (int) (display.getHeight() * 0.477);

        // init the Bottom Sheet Behavior
        bottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet);
        bottomSheetBehavior.setPeekHeight(height);

        Intent subintent = getIntent();

        if(subintent.getExtras()!=null) {

            dialogstring = subintent.getExtras().getString("name");
            finish = subintent.getExtras().getString("signin");

            if (finish != null) {
                Log.e("finish", finish);
                if (finish.equals("finish")) {
                    signin2.finish();
                }
            }

            if(dialogstring!=null){
                if(dialogstring.equals("skintypedialog")){
                    final Intent intent = new Intent(getApplicationContext(),SkintypeAsk.class);
                    startActivity(intent);
                }
            }
        }
        toolbar_dash.setEnabled(false);

        //animation
        final Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha);
        final Animation alpha2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha2);
        final Animation scaletranslate2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scaletranslate2);
        final Animation animationup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation animationdown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation translateup = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translateup);
        final Animation translatedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translatedown);
        final Animation dashup1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashup1);
        final Animation dashup2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashup2);
        final Animation dashup3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashup3);
        final Animation dashup4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashup4);
        final Animation dashup5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashup5);
        final Animation moveright =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveright);
        final Animation home1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation home111 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home222 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home333 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home444 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home555 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home666 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home777 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationup);
        final Animation home1111 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home2222= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home3333 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home4444 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home5555 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home6666 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home7777 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationdown);
        final Animation home11 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation home22 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation home33 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation home44 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation home55 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation home66 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation homedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_down);
        final Animation dashscale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation dashscale2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation dashscale3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup);
        final Animation dashscale11 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation dashscale22 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation dashscale33 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashallup2);
        final Animation historydown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim);
        final Animation historydown2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim);
        final Animation historydown4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim3);
        final Animation historydown3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim2);
        final Animation historydown5=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim4);
        final Animation historydown6=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim4);
        final Animation historydown7=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim4);
        final Animation historydown8=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historydownanim4);
        final Animation historyup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup);
        final Animation historyu2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup);
        final Animation historyu4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup3);
        final Animation historyu3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup2);
        final Animation historyu5=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup4);
        final Animation historyu6=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup4);
        final Animation historyu7=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup4);
        final Animation historyu8=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup4);
        final Animation historydown9=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.historyup5);

        alphaback = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha_back);
        wrinklemain_txt = databaseReference.child("result").child("winkle");
        wrinklemain_txt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                wrinklestringg=dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        historyc.setVisibility(View.INVISIBLE);
        treatclose.setVisibility(View.INVISIBLE);
        more.setVisibility(View.INVISIBLE);
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View view, int i) {

                // Dash -> Home으로 Dragging 해도 화면 전환이 되지 않게 함
                if (i == 1) {      //STATE_DRAGGING
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

                // Dash 화면
                if (i == BottomSheetBehavior.STATE_COLLAPSED) {      //STATE_EXPANDED
                    toolbar_dash.setEnabled(false);
                    closedash.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // TODO Auto-generated method stub
                            if(event.getAction()==MotionEvent.ACTION_DOWN){
                                if(toolbar.getClass()==v.getClass()){
                                    dashall.startAnimation(dashscale);
                                    dashall2.startAnimation(dashscale2);
                                    dashall3.startAnimation(dashscale3);
                                    ha5.startAnimation(home1);
                                    ha6.startAnimation(home2);
                                    ha3.startAnimation(home3);
                                    ha4.startAnimation(home4);
                                    ha7.startAnimation(home5);
                                }
                            }
                            if(event.getAction()==MotionEvent.ACTION_UP){
                                if(toolbar.getClass()==v.getClass()){
                                    dashall.startAnimation(dashscale11);
                                    dashall2.startAnimation(dashscale22);
                                    dashall3.startAnimation(dashscale33);
                                    ha1.startAnimation(home111);
                                    weekof.startAnimation(home111);
                                    ha2.startAnimation(home222);
                                    ha3.startAnimation(home333);
                                    ha4.startAnimation(home444);
                                    ha5.startAnimation(home555);
                                    ha6.startAnimation(home666);
                                    treatbtn.setClickable(false);
                                    historyBtn.setClickable(false);
                                    moisture.setVisibility(View.VISIBLE);
                                    wrinkles.setVisibility(View.VISIBLE);
                                    more.setVisibility(View.VISIBLE);
                                    ha7.startAnimation(home777);
                                    toolbar.setVisibility(View.INVISIBLE);
                                    arrow.setImageResource(R.drawable.xdash);
                                    new Handler().postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                        }
                                    }, 20);
                                }
                            }
                            return true;
                        }
                    });
                }
                if (i == BottomSheetBehavior.STATE_EXPANDED) {      //STATE_EXPANDED
                    toolbar_dash.setEnabled(true);
                    closedash.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // TODO Auto-generated method stub
                            if(event.getAction()==MotionEvent.ACTION_DOWN){
                                if(toolbar.getClass()==v.getClass()){
                                    dashall.startAnimation(dashscale);
                                    dashall2.startAnimation(dashscale2);
                                    dashall3.startAnimation(dashscale3);
                                }
                            }
                            if(event.getAction()==MotionEvent.ACTION_UP){
                                if(toolbar.getClass()==v.getClass()){
                                    dashall.startAnimation(dashscale11);
                                    dashall2.startAnimation(dashscale22);
                                    dashall3.startAnimation(dashscale33);
                                    ha1.startAnimation(home1111);
                                    weekof.startAnimation(home1111);
                                    ha2.startAnimation(home2222);
                                    ha3.startAnimation(home3333);
                                    ha4.startAnimation(home4444);
                                    ha5.startAnimation(home5555);
                                    ha6.startAnimation(home6666);
                                    ha7.startAnimation(home7777);
                                    treatbtn.setClickable(true);
                                    historyBtn.setClickable(true);
                                    moisture.setVisibility(View.INVISIBLE);
                                    wrinkles.setVisibility(View.INVISIBLE);
                                    more.setVisibility(View.INVISIBLE);
                                    toolbar.setVisibility(View.VISIBLE);
                                    arrow.setImageResource(R.drawable.uparrowdash);
                                    new Handler().postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                        }
                                    }, 20);
                                }
                            }
                            return true;
                        }
                    });
                }
            }

            // 슬라이드시 화면 보이게
            @Override
            public void onSlide(@NonNull View view, float slideOffset) {
                //animateBottomSheetArrows(slideOffset);
            }

        });

        toolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(toolbar.getClass()==v.getClass()){
                        dashall.startAnimation(dashscale);
                        dashall2.startAnimation(dashscale2);
                        dashall3.startAnimation(dashscale3);
                        ha5.startAnimation(home1);
                        ha6.startAnimation(home2);
                        ha3.startAnimation(home3);
                        ha4.startAnimation(home4);
                        ha7.startAnimation(home5);
                    }
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(toolbar.getClass()==v.getClass()){
                        dashall.startAnimation(dashscale11);
                        dashall2.startAnimation(dashscale22);
                        dashall3.startAnimation(dashscale33);
                        ha1.startAnimation(home111);
                        weekof.startAnimation(home111);
                        ha2.startAnimation(home222);
                        ha3.startAnimation(home333);
                        ha4.startAnimation(home444);
                        ha5.startAnimation(home555);
                        ha6.startAnimation(home666);
                        ha7.startAnimation(home777);
                        treatbtn.setClickable(false);
                        historyBtn.setClickable(false);
                        moisture.setVisibility(View.VISIBLE);
                        wrinkles.setVisibility(View.VISIBLE);
                        more.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.INVISIBLE);
                        arrow.setImageResource(R.drawable.xdash);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            }
                        }, 20);
                    }
                }
                return true;
            }
        });

        toolbar_dash.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(toolbar.getClass()==v.getClass()){
                        dashall.startAnimation(dashscale);
                        dashall2.startAnimation(dashscale2);
                        dashall3.startAnimation(dashscale3);
                    }
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(toolbar.getClass()==v.getClass()){
                        dashall.startAnimation(dashscale11);
                        dashall2.startAnimation(dashscale22);
                        dashall3.startAnimation(dashscale33);
                        ha1.startAnimation(home1111);
                        weekof.startAnimation(home1111);
                        ha2.startAnimation(home2222);
                        ha3.startAnimation(home3333);
                        ha4.startAnimation(home4444);
                        ha5.startAnimation(home5555);
                        ha6.startAnimation(home6666);
                        ha7.startAnimation(home7777);
                        moisture.setVisibility(View.INVISIBLE);
                        wrinkles.setVisibility(View.INVISIBLE);
                        more.setVisibility(View.VISIBLE);
                        treatbtn.setClickable(true);
                        historyBtn.setClickable(true);
                        toolbar.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.uparrowdash);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                        }, 20);
                    }
                }
                return true;
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.skintype:
                        skintype.setClickable(false);
                        intent = new Intent(getApplicationContext(), SkintypeActivity.class);
                        intent.putExtra("a",mois);
                        intent.putExtra("b",oil);
                        intent.putExtra("c",resis);
                        intent.putExtra("d",elas);
                        intent.putExtra("e",anti);
                        startActivity(intent);
                        break;
                    case R.id.treatclose:
                    case R.id.treatBtn:
                        //Log.e("treatBtn","onclick");
                        if (measureWrinkle) {
                            //Log.e("treatBtn","measureWrinkle");
                            if (isConn) {
                                intent = new Intent(getApplicationContext(), LoadingActivity.class);
                                intent.putExtra("wrinkle", DB_wrinkle);
                                overridePendingTransition(0, 0);
                                startActivity(intent);
                            } else {
                                intent = new Intent(getApplicationContext(), BTNoActivity.class);
                                intent.putExtra("where", "missing");
                                startActivity(intent);
                            }
                        } else {
                            //treatbtn.setClickable(false);
                            //treatclose.setClickable(false);
                            //Log.e("treatBtn","else");
                            intent = new Intent(getApplicationContext(),noActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.moisture:
                        moisture.setClickable(false);
                        if (isConn) {
                            intent = new Intent(getApplicationContext(), MoistureActivity.class);
                            startActivity(intent);
                        } else {
                            intent = new Intent(getApplicationContext(), BTNoActivity.class);
                            intent.putExtra("key", "dash");
                            startActivity(intent);
                        }
                        break;
                    case R.id.wrinkle:
                        wrinkles.setClickable(false);
                        intent = new Intent(getApplicationContext(), WrinklesActivity.class);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                        break;
                    case R.id.toolbar:
                        toolbar.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.toolbar_dash:
                        break;
                    case R.id.historyBtn:
                        historylayout.startAnimation(historydown);
                        historylayout2.startAnimation(historydown2);
                        historyclose.startAnimation(historydown3);
                        historylayout.setVisibility(View.VISIBLE);
                        historylayout2.setVisibility(View.VISIBLE);
                        historyclose.setVisibility(View.VISIBLE);
                        ha6.startAnimation(historydown4);
                        ha7.startAnimation(historydown5);
                        ha3.startAnimation(historydown6);
                        ha4.startAnimation(historydown7);
                        ha5.startAnimation(historydown8);
                        historyBtn.setEnabled(false);
                        treatbtn.setEnabled(false);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        historyc.setVisibility(View.VISIBLE);
                        treatclose.setVisibility(View.VISIBLE);
                        break;
                    case R.id.historyclose:
                        historylayout.startAnimation(historyup);
                        historylayout2.startAnimation(historyu2);
                        historyclose.startAnimation(historyu3);
                        historylayout.setVisibility(View.INVISIBLE);
                        historylayout2.setVisibility(View.INVISIBLE);
                        historyclose.setVisibility(View.INVISIBLE);
                        ha6.startAnimation(historyu4);
                        ha7.startAnimation(historyu5);
                        ha3.startAnimation(historyu6);
                        ha4.startAnimation(historyu7);
                        ha5.startAnimation(historyu8);
                        historyBtn.setEnabled(true);
                        treatbtn.setEnabled(true);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        historyc.setVisibility(View.INVISIBLE);
                        treatclose.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.imageView2:
                        imageView2.setClickable(false);
                        // BT
                        if (isConn) {
                            intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                            intent.putExtra("key", "home");
                            startActivity(intent);
                        } else {
                            intent = new Intent(getApplicationContext(), BTOnActivity.class);
                            intent.putExtra("key", "again");
                            startActivity(intent);
                        }
                        break;
                    case R.id.logimg:
                    case R.id.logout:
                        logout.setClickable(false);
                        intent = new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.aboutimg:
                    case R.id.about:
                        about.setClickable(false);
                        intent = new Intent(getApplicationContext(),SettingDashActivity.class);
                        intent.putExtra("string","about");
                        startActivity(intent);
                        break;
                }
            }
        };
        treatclose.setOnClickListener(onClickListener);
        historyBtn.setOnClickListener(onClickListener);
        moisture.setOnClickListener(onClickListener);
        wrinkles.setOnClickListener(onClickListener);
        toolbar.setOnClickListener(onClickListener);
        toolbar_dash.setOnClickListener(onClickListener);
        treatbtn.setOnClickListener(onClickListener);
        logo.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        skintype.setOnClickListener(onClickListener);
        historyc.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);
        product.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        profile.setOnClickListener(onClickListener);
        logimg.setOnClickListener(onClickListener);
        aboutimg.setOnClickListener(onClickListener);
        // SharedPreferences에서 이름 받아오기
        SharedPreferences sp_userName = getSharedPreferences("userName", MODE_PRIVATE);
        userName = sp_userName.getString("userName", "");
        home_setName.setText("HELLO, "+ userName+"!");
        //Log.e("SharedPreferences", userName);
    }

    // calling Wrinkle
    class GetData2 extends AsyncTask<String, Void, String> {
        String level="";

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            if (getResult==null) {
                measureWrinkle = false;
                //Log.e("getdata-wrinkle", "getResult==null");
                DB_wrinkle = "-";
            } else {
                //Log.e("getdata-wrinkle", "getResult=="+getResult);
                if (getResult.contains("No_results")||getResult.contains("nul")) {
                    DB_wrinkle = "-";
                } else {
                    showResult(getResult);

                    // 가져온 현재 wrinkle 저장하기
                    SharedPreferences now_wrinkle = getSharedPreferences("now_w", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = now_wrinkle.edit();
                    editor1.putString("now_w", level);
                    editor1.commit();
                    //Log.e("now_w ", level+"퍼센트");
                }
            }
            wrinkle_score_main.setText(DB_wrinkle);

            //Log.e("wrinkle",DB_wrinkle);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "userID=none");
            //Log.e("userID:::", userID);
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("wrinkle-Connect1", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                Log.e("wrinkle-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("wrinkle-response", "code - " + responseStatusCode);

                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.e("wrinkle-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            measureWrinkle = true;
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);

                    level= item.getString("level");
                    wrinkle_per = item.getInt("level");
                    max_wrink = item.getInt("id");
                    max_wrink -= 1;
                }
                Log.e("wrinkle::::", String.valueOf(wrinkle_per));
                switch (level) {
                    case "100":
                        DB_wrinkle = "A+"; break;
                    case "95":
                        DB_wrinkle = "A"; break;
                    case "90":
                        DB_wrinkle = "B+"; break;
                    case "85":
                        DB_wrinkle = "B"; break;
                    case "80":
                        DB_wrinkle = "C+"; break;
                    case "75":
                        DB_wrinkle = "C"; break;
                }

            } catch (JSONException e) {
                //Log.e("wrinkle-JSON", "showResult : ", e);
            }
        }
    }

    // calling skintype
    class GetData3 extends AsyncTask<String, Void, String> {

        int mois=0, oil=0, resis=0, elas=0, anti=0;

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            //Log.e("skintype-", "onPostExecute - " + getResult);
            if (getResult==null) {
                DB_skintype = "No data yet";
            } else {
                if (getResult.contains("No_results")) {
                    DB_skintype = "No data yet";
                } else {
                    showResult(getResult);

                    DB_skintype = "";
                    if (mois >50) DB_skintype+="M "; else DB_skintype+="m ";
                    //Log.e("DB_skintype_M", DB_skintype);
                    if (oil >50) DB_skintype+="O "; else DB_skintype+="o ";
                    //Log.e("DB_skintype_O", DB_skintype);
                    if (resis >50) DB_skintype+="R "; else DB_skintype+="r ";
                    //Log.e("DB_skintype_R", DB_skintype);
                    if (elas >50) DB_skintype+="E "; else DB_skintype+="e ";
                    //Log.e("DB_skintype_E", DB_skintype);
                    if (anti >50) DB_skintype+="A"; else DB_skintype+="a";
                    //Log.e("DB_skintype_A", DB_skintype);

                    mois_c.setValue(mois);
                    oiㅣ_c.setValue(oil);
                    res_c.setValue(resis);
                    elas_c.setValue(elas);
                    anti_c.setValue(anti);

                    setDataSkin(String.valueOf(mois), String.valueOf(oil), String.valueOf(resis), String.valueOf(elas), String.valueOf(anti), lastSkinDate);
                }
            }
            skintype_main.setText(DB_skintype);
            showLastSkinDate.setText(lastSkinDate);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                Log.e("skintype-Connect", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("skintype-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("skintype-response", "code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                    //Log.e("read", String.valueOf(sb.append(line)));
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.e("skintype-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    //DB_skintype = item.getString("result");
                    mois = item.getInt("mois");
                    oil = item.getInt("oil");
                    resis = item.getInt("resis");
                    elas = item.getInt("elas");
                    anti = item.getInt("anti");
                    lastSkinDate = item.getString("date");
                }

            } catch (JSONException e) {
                //Log.e("JSON", "showResult : ", e);
            }
        }
    }

    Double mois=0.00, oil=0.00, resis=0.00, elas=0.00, anti=0.00;
    // calling skintype AVG

    class GetDataAVG extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            //Log.e("skintype-AVG", "onPostExecute - " + getResult);
            if (getResult!=null && !getResult.contains("null")) {
                showResult(getResult);
                mois_c2.setValue(Math.round(mois));
                oiㅣ_c2.setValue(Math.round(oil));
                res_c2.setValue(Math.round(resis));
                elas_c2.setValue(Math.round(elas));
                anti_c2.setValue(Math.round(anti));
                /*Log.e("skintype-AVG", "mois="+Math.round(mois)+"/oil="+Math.round(oil)+"/resis="+Math.round(resis)
                        +"/elas="+Math.round(elas)+"/anti="+Math.round(anti));*/
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("skintype-AVG-Connect", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("skintype-AVG-postParam", postParameters);
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                String responseStatusMessage = httpURLConnection.getResponseMessage();
                //Log.e("skintype-AVG-response", "code - " + responseStatusCode);
                //Log.e("skintype-AVG-response", "Message - " + responseStatusMessage);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.e("skintype-AVG-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    mois = item.getDouble("mois");
                    oil = item.getDouble("oil");
                    resis = item.getDouble("resis");
                    elas = item.getDouble("elas");
                    anti = item.getDouble("anti");
                }

            } catch (JSONException e) {
                Log.e("JSON", "AVG- showResult : ", e);
            }
        }
    }

    // calling skinAVG
    class GetDatas3 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            // DB에서 반환된 값이 없을 때 모두 nonCheck
            if (getResult!=null)
                showResult(getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("getdata-skinAVG", "connect complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("getdata-skinAVG", "postParameters"+postParameters);
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("getdata-skinAVG", "code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.e("getdata-skinAVG", "error"+e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            Log.e("getdata-skinAVG", "showResult 들어옴");
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    int moisi = item.getInt("mois");
                    int oili = item.getInt("oil");
                    int resisi = item.getInt("resis");
                    int elasi = item.getInt("elas");
                    int antii = item.getInt("anti");
                    //Log.e("값은!", String.valueOf(moisi)+"/"+String.valueOf(oili)+"/"+String.valueOf(resisi)+"/"+String.valueOf(elasi)+"/"+String.valueOf(antii));
                }
            } catch (JSONException e) {
                //Log.e("skinAVG-JSON", "showResult : "+e.getMessage());
            }
        }
    }

    private void setDataSkin(String mois, String oil, String resis, String elas, String anti, String lastSkinDate) {

        String skin = mois+"/"+oil+"/"+resis+"/"+elas+"/"+anti+"/"+lastSkinDate;

        SharedPreferences spSkin = getSharedPreferences("skin", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = spSkin.edit();

        editor1.putString("skin", skin);
        editor1.commit();
    }

    // calling Treat
    class GetData4 extends AsyncTask<String, Void, String> {
        List<String[]> wrinkleArray = new ArrayList<>();

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String date = mSimpleDateFormat.format(currentTime);
        Boolean todayCheck;

        @Override
        protected void onPostExecute(String getResult)
        {
            super.onPostExecute(getResult);

            String dates[] = date.split("-");
            int checkdate = 0;
            int firstcheck = 0;
            int count2=0;

            // DB에서 반환된 값이 없을 때 모두 nonCheck
            if (getResult==null) {
                //Log.e("getdata-treat", "getResult==null");
                //Log.e("getdata-treat", "getResult==null");
                check[0].setImageResource(R.drawable.noncheck);
                check[1].setImageResource(R.drawable.noncheck);
                check[2].setImageResource(R.drawable.noncheck);
                check[3].setImageResource(R.drawable.noncheck);
                check[4].setImageResource(R.drawable.noncheck);
            } else {
                // DB에서 반환된 값이 있을 때 JSON 형식으로 받아온 값을 정리(?)
                showResult(getResult);

                // Wrinkle measure한 첫 날 가져오고 오늘 날짜 가져오기
                String end = date;
                //Log.e("start==", start);
                //Log.e("end==", end);

                try {
                    checkdate = getDateDay(date,"yyyy-mm-dd");
                    firstcheck = getDateDay(start,"yyyy-mm-dd");
                    //Log.e("checkdate==", String.valueOf(checkdate));
                    //Log.e("firstcheck==", String.valueOf(firstcheck));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDay = null;
                Date endDay = null;
                try {
                    startDay = formatter.parse(start);
                    endDay = formatter.parse(end);
                } catch (ParseException e) { e.printStackTrace(); }

                // 오늘 - 첫날 빼기 (몇일인지) == diffDay
                long diff = endDay.getTime() - startDay.getTime();
                long diffDay = diff/(24 * 60 * 60 * 1000);
                //Log.e("diffy==", String.valueOf(diff));
                //Log.e("diffDay==", String.valueOf(diffDay));

                // diffDay를 5로 나눈 나머지 구하기 == last
                // (화면상에서는 5개만 보이니까..)
                last = (int) diffDay % 5;
                int max = wrinkleArray.size()-1;

                int count=0;

                //Log.e("max", String.valueOf(max));
                //Log.e("last", String.valueOf(last));

                // 5개에서 last를 뺀 부분을 nonCheck으로 채우기

                for (int i = 4; i>=last; i--) {
                    //Log.e("현재 I ", String.valueOf(i));
                    check[i].setImageResource(R.drawable.noncheck);
                }
                if (wrinkleArray.size()==0) {
                    for (int i=0; i<last; i++) {
                        //Log.e("두번째 현재 I ", String.valueOf(i));
                        check[i].setImageResource(R.drawable.ximg);
                    }
                }
                // 오늘 날짜부터 하루씩 빼면서 DB에 있는지 확인
                // 있으면 한 파트를 완료했는지 확인 후 했으면 check 안했으면 ximg
                if (max>=0) {
                    //Log.e("max>0", "들어옴");

                    // 오늘날짜에 했는지 안했는지 확인
                    int d1 = Integer.parseInt(dates[2]);
                    String ds1 = "";
                    if (d1 < 10) {
                        // 일이 한자리 숫자면 앞에 0 붙이기 (String 비교하려고)
                        ds1 = "0" + String.valueOf(d1);
                    } else ds1 = String.valueOf(d1);
                    if (wrinkleArray.get(max)[0].equals(dates[0] + "-" + dates[1] + "-" + ds1)) {
                        //Log.e("오늘 날짜같음", "오늘 날짜같음");
                        if ((wrinkleArray.get(max)[1].contains("under_l")) && (wrinkleArray.get(max)[1].contains("under_r"))) {
                            todayCheck = true;
                        } else if ((wrinkleArray.get(max)[1].contains("cheek_l")) && (wrinkleArray.get(max)[1].contains("cheek_r"))) {
                            todayCheck = true;
                        } else if ((wrinkleArray.get(max)[1].contains("eye_l")) && (wrinkleArray.get(max)[1].contains("eye_r"))) {
                            todayCheck = true;
                        } else if ((wrinkleArray.get(max)[1].contains("fore_l")) && (wrinkleArray.get(max)[1].contains("fore_r"))) {
                            todayCheck = true;
                        }
                        else {
                            todayCheck = false;
                        }
                    } else {
                        //Log.e("오늘 날짜다름", "오늘 날짜다름");
                        todayCheck = false;
                    }



                    for (int i = 0; i <= last; i++) {
                        // 젤 최근 날짜와 오늘날짜가 같은지
                        // 일 가져오기
                        int d = Integer.parseInt(dates[2]) - i;
                        String ds = "";
                        if (d < 10) {
                            // 일이 한자리 숫자면 앞에 0 붙이기 (String 비교하려고)
                            ds = "0" + String.valueOf(d);
                        } else ds = String.valueOf(d);
                        //Log.e("date_2", dates[0] + "-" + dates[1] + "-" + ds);
                        if (max >= 0) {
                            if (wrinkleArray.get(max)[0].equals(dates[0] + "-" + dates[1] + "-" + ds)) {
                                //Log.e("날짜같음", "날짜같음");
                                if ((wrinkleArray.get(max)[1].contains("under_l")) && (wrinkleArray.get(max)[1].contains("under_r"))) {
                                    //Log.e("under_l & under_r", "둘다있음");
                                    count++;
                                    check[last - i].setImageResource(R.drawable.check);
                                } else if ((wrinkleArray.get(max)[1].contains("cheek_l")) && (wrinkleArray.get(max)[1].contains("cheek_r"))) {
                                    //Log.e("cheek_l & cheek_r", "둘다있음");
                                    count++;
                                    check[last - i].setImageResource(R.drawable.check);
                                } else if ((wrinkleArray.get(max)[1].contains("eye_l")) && (wrinkleArray.get(max)[1].contains("eye_r"))) {
                                    //Log.e("eye_l & cheek_r", "eye_r");
                                    count++;
                                    check[last - i].setImageResource(R.drawable.check);
                                } else if ((wrinkleArray.get(max)[1].contains("fore_l")) && (wrinkleArray.get(max)[1].contains("fore_r"))) {
                                    //Log.e("fore_l & fore_r", "둘다있음");
                                    count++;
                                    check[last - i].setImageResource(R.drawable.check);
                                }
                                else {
                                    //Log.e("전부 no", "pair");
                                    check[last - i].setImageResource(R.drawable.noncheck);
                                }
                                max--;
                            } else if (i == 0) check[last - i].setImageResource(R.drawable.noncheck);
                            else check[last - i].setImageResource(R.drawable.ximg);
                        } else check[last - i].setImageResource(R.drawable.ximg);
                    }
                }//요기까지 보내주면 됌
                else {
                    //Log.e("오늘 날짜다름", "오늘 날짜다름");
                    todayCheck = false;
                }

                //Log.e("count",String.valueOf(count));

                // 이번주 마지막날이면
                if(last==4) {
                    // 오늘 했는지 확인
                    if (todayCheck) {
                        //Log.e("todayCheck", "true");
                        String GSB;
                        if (count == 5) {
                            //Log.e("GOLD", "GOLD");
                            GSB = "gold";
                        }
                        if (count == 4) {
                            //Log.e("Silver", "Silver");
                            GSB = "silver";
                        }
                        if (count == 3) {
                            //Log.e("orange", "orange");
                            GSB = "bronze";
                        } else {
                            //Log.e("NONE", "NONE");
                            GSB = "none";
                        }
                        setGSB task = new setGSB();
                        task.execute("http://"+IP_Address+"/setGSB.php", GSB);
                    }
                }
                // 오늘 했는지
                int allcount;
                if (TreatActivity.todayCheck) {
                    //allcount = (int)diffDay-count;
                    allcount = (int)diffDay;
                } else allcount = (int)diffDay;

                //Log.e("allcount1", String.valueOf(allcount));

                /*if(allcount==0){
                    allcount=allcount-1;
                }
                Log.e("allcount2", String.valueOf(allcount));
                if(allcount==-1){
                    allcount=0;
                }
                Log.e("allcount3", String.valueOf(allcount));
                if(allcount!=0){
                    allcount++;
                }
                Log.e("allcount4", String.valueOf(allcount));*/
                /*if(count!=0){
                    allcount=allcount+1;
                }*/
                //Log.e("acount", String.valueOf(count));
                missday.setText(String.valueOf(allcount-countCheck)+" days");
                doneday.setText(String.valueOf(countCheck)+" days");
                int percentint =0;
                if(count!=0) {
                    percentint = countCheck*5;
                }
                percent.setText(String.valueOf(percentint)+" %");

                //Log.e("allcount",String.valueOf(allcount));



                if(count!=0){
                    int tenRemainder = ((allcount)+1/5+1) % 10;
                    //Log.e("ㅇㅇ",String.valueOf(tenRemainder));
                    if(String.valueOf(tenRemainder).equals("1")){
                        weektxt.setText(String.valueOf((allcount)/5+1)+"ST");
                    }
                    else if(String.valueOf(tenRemainder).equals("2")){
                        weektxt.setText(String.valueOf((allcount)/5+1)+"ND");
                    }
                    else if(String.valueOf(tenRemainder).equals("3")){
                        weektxt.setText(String.valueOf((allcount)/5+1)+"RD");
                    }
                    else {
                        weektxt.setText(String.valueOf((allcount) / 5 + 1) + "TH");
                    }
                    xcount.setText(String.valueOf(allcount+1-countCheck));
                }
                String st2;
                int tenRemainder = ((allcount)/5+1) % 10;
                //Log.e("ㅇㅇ",String.valueOf(tenRemainder));
                if(String.valueOf(tenRemainder).equals("1")){
                    weektxt.setText(String.valueOf((allcount)/5+1)+"ST");
                }
                else if(String.valueOf(tenRemainder).equals("2")){
                    weektxt.setText(String.valueOf((allcount)/5+1)+"ND");
                }
                else if(String.valueOf(tenRemainder).equals("3")){
                    weektxt.setText(String.valueOf((allcount)/5+1)+"RD");
                }
                else {
                    weektxt.setText(String.valueOf((allcount) / 5 + 1) + "TH");
                }
                xcount.setText(String.valueOf(allcount-countCheck));

            }
        }

        @SuppressLint("LongLogTag")
        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID+"&date="+date;

            //Log.e("여기는", "트릿부르는곳이다");

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("callingTreat-Connect", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("callingTreat-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("callingTreat-response", "code - " + responseStatusCode);

                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("callingTreat-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            //Log.e("showResult", "들어옴");
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    wrinkleArray.add(new String[]{item.getString("date"),item.getString("value")});
                    //Log.e("wrinkleArray", String.valueOf(item.getString("date")));
                    //Log.e("wrinkleArray", String.valueOf(item.getString("value")));
                }
            } catch (JSONException e) {
                //Log.e("treat-JSON", "showResult : "+e.getMessage());
            }
        }
    }


    // 금은동 저장
    class setGSB extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            if (getResult==null||getResult.contains("No_results")||getResult.contains("nul")) {

            } else {
                //Log.e("setGSB", "getResult=="+getResult);
                //showResult(getResult);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String GSB = params[1];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "userID=none");
            //Log.e("userID:::", userID);
            String postParameters = "date="+date+"&id="+userID+"&GSB="+GSB;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("GSB-Connect", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("GSB-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("GSB-response", "code - " + responseStatusCode);

                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    //(responseStatusCode == 204)
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("GSB-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    start = item.getString("date");
                    //Log.e("start==", start);
                }

            } catch (JSONException e) {
                //Log.e("wrinkle-JSON", "showResult : ", e);
            }
            GetData4 task4 = new GetData4();
            task4.execute("http://"+IP_Address+"/callingTreatt.php", "");
        }
    }

    //요일 구하는 함수
    public int getDateDay(String date, String dateType) throws Exception {

        int day=0;

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
        Date nDate = dateFormat.parse(date) ;

        Calendar cal = Calendar.getInstance() ;
        cal.setTime(nDate);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;

        switch(dayNum){
            case 1:
                day=6;
                break ;
            case 2:
                day=0;
                break ;
            case 3:
                day=1;
                break ;
            case 4:
                day=2;
                break ;
            case 5:
                day=3;
                break ;
            case 6:
                day=4;
                break ;
            case 7:
                day=5;
                break ;
        }
        return day ;
    }

    // calling Wrinkle
    class getDatas4 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            if (getResult==null||getResult.contains("No_results")||getResult.contains("nul")) {

            } else {
                //Log.e("get-wrinkleDate", "getResult=="+getResult);
                showResult(getResult);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "userID=none");
            //Log.e("userID:::", userID);
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("wrinkleDate-Connect", "complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("wrinkleDate", "-post"+postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("wrinkleDate-response", "code - " + responseStatusCode);

                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    //(responseStatusCode == 204)
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("wrinkleDate-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    start = item.getString("date");
                    //Log.e("start==", start);
                }

            } catch (JSONException e) {
                //Log.e("wrinkleDate-JSON", "showResult : ", e);
            }
            GetData4 task4 = new GetData4();
            task4.execute("http://"+IP_Address+"/callingTreatt.php", "");
        }
    }

    class getDataTreatCheck extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            if (getResult==null||getResult.contains("No_results")||getResult.contains("nul")) {
                countCheck = 0;
                checkcount.setText(String.valueOf(countCheck));
            } else {
                //Log.e("getDataTreatCheck", "getResult=="+getResult);
                showResult(getResult);
                int set = countCheck/2;
                if (countCheck%2!=0) {
                    set++;
                }
                checkcount.setText(String.valueOf(set));
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "userID=none");
            //Log.e("userID:::", userID);
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("getDataTreatCheck-", "Connect Complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("getDataTreatCheck-", "postParameters: "+postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("getDataTreatCheck-", "response Code - " + responseStatusCode);
                //Log.e("getDataTreatCheck-", "response Message - " + httpURLConnection.getResponseMessage());

                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    //(responseStatusCode == 204)
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("getDataTreatCheck-error", e.getMessage());
            }
            return null;
        }

        private void showResult(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    countCheck = item.getInt("num");
                    //Log.e("getDataTreatCheck-", "countCheck=="+String.valueOf(countCheck));
                }

            } catch (JSONException e) {
                //Log.e("wrinkle-JSON", "showResult : ", e);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private static class CheckConnect extends Thread {
        private String host;

        public CheckConnect(String host) {
            this.host = host;
        }

        @Override
        public void run() {

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(host).openConnection();
                conn.setRequestProperty("User-Agent", "Android");
                conn.setConnectTimeout(1000);
                conn.connect();
                int responseCode = conn.getResponseCode();
                if (responseCode == 204) iConnected = true;
                else iConnected = false;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("connect Error", e.getMessage());
                iConnected = false;
            }
            if (conn != null) conn.disconnect();
        }
        public boolean isSuccess(){ return iConnected; }
    }

    public static boolean isOnline() {
        CheckConnect cc = new CheckConnect(CONNECTION_CONFIRM_CLIENT_URL);
        cc.start();
        try{
            cc.join();
            return cc.isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();

        isOnline();

        // 체크부르느거
        getDataTreatCheck task2 = new getDataTreatCheck();
        task2.execute("http://"+IP_Address+"/callingTreatCheck.php", "");

        // 왕관부르는거
        getGSB taskGSB = new getGSB();
        taskGSB.execute("http://"+IP_Address+"/callingGSB.php", "");

        getDatas4 tasks = new getDatas4();
        tasks.execute("http://"+IP_Address+"/callingWrinkleDate.php", "");

        GetDataAVG task3 = new GetDataAVG();
        task3.execute("http://"+IP_Address+"/\n" + "callingSkintypeAVG.php", "");

        product.setClickable(true);skintype.setClickable(true);moisture.setClickable(true);wrinkles.setClickable(true);
        imageView2.setClickable(true);profile.setClickable(true);about.setClickable(true);logout.setClickable(true);

        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

        // 베터리
        if (deviceBattery<=lowBattery)
            imageView2.setImageResource(R.drawable.bdev);
        else imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
        if (deviceBattery==-1) {
            imageView2.setImageResource(R.drawable.nondeviceicon);
            isConn = false;
            if (isFirst == false) {
                Log.e("HomeActivity", "onResume, isFirst == false + disconnectGattServer():: battery:: " +deviceBattery);
                disconnectGattServer();
            }
        }

        getDataMois();
        getDataWrink();
        getDataSkin();

        if (!iConnected) {
            //Log.e("!iConnected", String.valueOf(iConnected));
            // 인터넷 연결이 안됨 팝업
            Intent intent = new Intent(this, NointernetActivity.class);
            intent.putExtra("where", "HomeActivity");
            startActivity(intent);
        }
    }

    // 왕관 부르는거
    class getGSB extends AsyncTask<String, Void, String> {
        int gold=0, silver=0, bronze=0, none=0;
        ArrayList<String> arr = new ArrayList<>();

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            // DB에서 반환된 값이 없을 때 모두 nonCheck
            if (getResult==null || getResult.contains("No_results")) {
                //Log.e("getdata-GSB", "GSB==null");

                for (int i=0; i<4; i++) {
                    crown[i].setImageResource(R.drawable.none);
                }

            } else {
                //Log.e("getdata-GSB", "값 잇음!!!");
                //Log.e("getdata-GSB", "getResult: " + getResult);

                showResult(getResult);

                //Log.e("gold==", String.valueOf(gold));
                //Log.e("silver==", String.valueOf(silver));
                //Log.e("bronze==", String.valueOf(bronze));

                for (int i=0; i<arr.size(); i++) {
                    if (i<4) {

                    }
                    // 여기서 이미지
                    if (arr.get(i).equals("gold")) crown[i].setImageResource(R.drawable.gold);
                    else if (arr.get(i).equals("silver")) crown[i].setImageResource(R.drawable.silver);
                    else if (arr.get(i).equals("bronze")) crown[i].setImageResource(R.drawable.bronze);
                    else if (arr.get(i).equals("silver")) crown[i].setImageResource(R.drawable.none);
                    //Log.e("arr.get(i)", arr.get(i));
                }

            }
            goldString.setText(String.valueOf(gold));
            silverString.setText(String.valueOf(silver));
            bronzeString.setText(String.valueOf(bronze));
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                //Log.e("getdata-GSB", "connect complete");

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("getdata-GSB", "postParameters"+postParameters);
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.e("getdata-GSB", "code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                    //Log.e("getdata-GSB", String.valueOf(sb.append(line)));
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.e("getdata-GSB", "error"+e.getMessage());
            }
            return null;
        }

        private void showResult(String result) {
            //Log.e("getdata-GSB", "showResult 들어옴");
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    arr.add(item.getString("value"));
                    //Log.e("GSB_value", arr.get(i));
                    switch (item.getString("value")) {
                        case "gold": gold++;
                            break;
                        case "silver": silver++;
                            break;
                        case "bronze": bronze++;
                            break;
                        case "none": none++;
                            break;
                    }
                }
            } catch (JSONException e) {
                //Log.e("GSB-JSON", "showResult : "+e.getMessage());
            }
        }
    }

    private void getDataMois() {
        // 원래 모이스처, 현재 모이스처 가져오기
        SharedPreferences now_moisture = getSharedPreferences("now_m", MODE_PRIVATE);
        SharedPreferences bef_moisture = getSharedPreferences("bef_m", MODE_PRIVATE);
        int now_m = now_moisture.getInt("now_m", -8);
        int bef_m = bef_moisture.getInt("bef_m", -8);
        //Log.e("now_m", now_m);
        //Log.e("bef_m", bef_m);
        String wrinkle ="-";

        if (now_m!=-8) {
            if (now_m<=18) wrinkle = "C";
            if (now_m>=20 && now_m<=40) wrinkle="C+";
            if (now_m>=42 && now_m<=48) wrinkle="B";
            if (now_m>=50 && now_m<=57) wrinkle="B+";
            if (now_m>=60 && now_m<=68) wrinkle="A";
            if (now_m>=71 && now_m<=99) wrinkle="A+";

        } else {
            GetData1 task1 = new GetData1();
            task1.execute("http://"+IP_Address+"/callingMoisture.php", "");
        }
        moisture_score_main.setText(wrinkle);

    }

    private void getDataWrink() {
        // 원래 모이스처, 현재 모이스처 가져오기
        SharedPreferences now_wrinkle = getSharedPreferences("now_w", MODE_PRIVATE);
        SharedPreferences bef_wrinkle = getSharedPreferences("bef_w", MODE_PRIVATE);
        String now_w = now_wrinkle.getString("now_w", "now_w=none");
        String bef_w = bef_wrinkle.getString("bef_w", "bef_w=none");
        //Log.e("여기는 wrinkle", "가져오기");
        //Log.e("now_w", now_w);
        //Log.e("bef_w", bef_w);

        if (now_w.equals("now_w=none")) {
            measureWrinkle = false;
            now_w = "-";
            GetData2 task2 = new GetData2();
            task2.execute("http://"+IP_Address+"/callingWrinkle.php", "");
        } else if (bef_w.equals("bef_w=none")||bef_w.contains("nul")) {
            measureWrinkle = true;
        } else {
            measureWrinkle = true;
            setUpNDown(now_w, bef_w, "wrinkle");
        }

        //Log.e("wrinkle",now_w);
        //settingLevel(now_w);

        switch (now_w) {
            case "100":
                now_w = "A+"; break;
            case "95":
                now_w = "A"; break;
            case "90":
                now_w = "B+"; break;
            case "85":
                now_w = "B"; break;
            case "80":
                now_w = "C+"; break;
            case "75":
                now_w = "C"; break;
        }
        wrinkleLevel = now_w;

        wrinkle_score_main.setText(now_w);
        //wrinkle_score_main.setText(settingLevel(now_w));
    }

    private String settingLevel(String moisLevel) {
        String now_w="";
        switch (moisLevel) {
            case "100":
                now_w = "A+"; break;
            case "95":
                now_w = "A"; break;
            case "90":
                now_w = "B+"; break;
            case "85":
                now_w = "B"; break;
            case "80":
                now_w = "C+"; break;
            case "75":
                now_w = "C"; break;
        }
        wrinkleLevel = now_w;
        return now_w;
    }

    private void setUpNDown(String now, String bef, String dbName) {
        String mois="", wrink="";

        if (Integer.parseInt(bef) < Integer.parseInt(now)) {
            if (dbName.equals("moisture")) mois = "up";
            else wrink = "up";
        }
        else if (Integer.parseInt(bef) == Integer.parseInt(now)) {
            if (dbName.equals("moisture")) mois = "else";
            else wrink = "else";
        }
        else {
            if (dbName.equals("moisture")) mois = "down";
            else wrink = "down";
        }
        setArrow(mois, wrink, dbName);
    }

    private void setArrow(String mois, String wrink, String dbName) {
        if (dbName.equals("moisture")) {
            //Log.e("dbName==","moisture");
            if(mois.equals("up")) {
                //Log.e("setting-moisture", "up");
            } else if(mois.equals("down")) {
                //Log.e("setting-moisture", "down");
            } else {
                //Log.e("setting-moisture", "else");
            }
        }
        else if (dbName.equals("wrinkle")) {
            //Log.e("dbName==","wrinkle");
            if (wrink.equals("up")) {
                //Log.e("setting-wrinkle", "up");
            } else if (wrink.equals("down")) {
                //Log.e("setting-wrinkle", "down");
            } else {
                //Log.e("setting-wrinkle", "else");
            }
        }
    }

    private void getDataSkin() {
        String setSkin="";

        SharedPreferences spSkin = getSharedPreferences("skin", MODE_PRIVATE);
        String skin = spSkin.getString("skin", "skin=none");
        //Log.e("skin", skin);

        if (skin.equals("skin=none")) {
            GetData3 task3 = new GetData3();
            task3.execute("http://"+IP_Address+"/callingSkintype.php", "");
        } else {
            String[] skintypeS = skin.split("/");
            if (Integer.parseInt(skintypeS[0]) >50) setSkin+="M "; else setSkin+="m ";
            if (Integer.parseInt(skintypeS[1]) >50) setSkin+="O "; else setSkin+="o ";
            if (Integer.parseInt(skintypeS[2]) >50) setSkin+="R "; else setSkin+="r ";
            if (Integer.parseInt(skintypeS[3]) >50) setSkin+="E "; else setSkin+="e ";
            if (Integer.parseInt(skintypeS[4]) >50) setSkin+="A"; else setSkin+="a";
            lastSkinDate = skintypeS[5];
            skintype_main.setText(setSkin);
            showLastSkinDate.setText(lastSkinDate);

            mois_c.setValue(Integer.parseInt(skintypeS[0]));
            oiㅣ_c.setValue(Integer.parseInt(skintypeS[1]));
            res_c.setValue(Integer.parseInt(skintypeS[2]));
            elas_c.setValue(Integer.parseInt(skintypeS[3]));
            anti_c.setValue(Integer.parseInt(skintypeS[4]));
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    private static void setMoistureActivity() {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (kind.equals("32")) {
                    MoistureActivity.mois_status.setText("Detect...");
                    MoistureActivity.ing = true;
                }
                if (kind.equals("33")) {
                    Log.e("moisture", receiveResult+"");
                    if (MoistureActivity.moisNow) {
                        MoistureActivity.moisRand = receiveResult;
                        MoistureActivity.per = String.valueOf(receiveResult);
                        Log.e("moisRand", String.valueOf(MoistureActivity.moisRand));
                    }
                    MoistureActivity.setMoisFront();

                    MoistureActivity.ing = false;
                }
                if (receiveResult==34) {
                    MoistureActivity.mois_status.setText("Fail...");
                    MoistureActivity.moisRand = 8;
                    MoistureActivity.setMoisResult();
                }
            }
        });
    }

    private static void setTreatActivity() {

        if (kind.equals("41")){
            if (where != null) {
                switch (where) {
                    case "uneye_r":
                        max = 14;
                        if (TreatActivity_underright2.countFin > 0)
                            countStart = TreatActivity_underright2.countFin;break;
                    case "uneye_l":
                        max = 14;
                        if (TreatActivity_underleft2.countFin > 0)
                            countStart = TreatActivity_underleft2.countFin;break;
                    case "cheek_r":
                        max = 23;
                        if (TreatActivity_cheekright2.countFin > 0)
                            countStart = TreatActivity_cheekright2.countFin;break;
                    case "cheek_l":
                        max = 23;
                        if (TreatActivity_cheekleft2.countFin > 0)
                            countStart = TreatActivity_cheekleft2.countFin;break;
                    case "eye_r":
                        max = 8;
                        if (TreatActivity_eyeright2.countFin > 0)
                            countStart = TreatActivity_eyeright2.countFin;break;
                    case "eye_l":
                        max = 8;
                        max = 20;
                        if (TreatActivity_eyerleft2.countFin > 0)
                            countStart = TreatActivity_eyerleft2.countFin;break;
                    case "fore_r":
                        max = 20;
                        if (TreatActivity_foreheadright.countFin > 0)
                            countStart = TreatActivity_foreheadright.countFin;break;
                    case "fore_l":
                        max = 20;
                        if (TreatActivity_foreheadleft.countFin > 0)
                            countStart = TreatActivity_foreheadleft.countFin;break;
                }
                countStart++;
                switch (where) {
                    case "uneye_r": TreatActivity_underright2.countStart = countStart;break;
                    case "uneye_l": TreatActivity_underleft2.countStart = countStart;break;
                    case "cheek_r": TreatActivity_cheekright2.countStart = countStart;break;
                    case "cheek_l": TreatActivity_cheekleft2.countStart = countStart;break;
                    case "eye_r": TreatActivity_eyeright2.countStart = countStart;break;
                    case "eye_l": TreatActivity_eyerleft2.countStart = countStart;break;
                    case "fore_r": TreatActivity_foreheadright.countStart = countStart;break;
                    case "fore_l": TreatActivity_foreheadleft.countStart = countStart;break;
                }
            }
        }
        else if (kind.equals("42") || kind.equals("43")) {
            if (countStart < max) {
                switch (where) {
                    case "uneye_r": TreatActivity_underright2.countFin = countStart; break;
                    case "uneye_l": TreatActivity_underleft2.countFin = countStart; break;
                    case "cheek_r": TreatActivity_cheekright2.countFin = countStart; break;
                    case "cheek_l": TreatActivity_cheekleft2.countFin = countStart; break;
                    case "eye_r": TreatActivity_eyeright2.countFin = countStart; break;
                    case "eye_l": TreatActivity_eyerleft2.countFin = countStart; break;
                    case "fore_r": TreatActivity_foreheadright.countFin = countStart; break;
                    case "fore_l": TreatActivity_foreheadleft.countFin = countStart; break;
                }
            }
        }
    }

    public static void setDeviceLevel() {
        staticLevel = "DEVICE LEVEL : "+ deviceLevel;
        setLevelText();

        Log.e("level", deviceLevel+"");
        setLevelText();
        if (where!=null) {
            Log.e("where======", where);
            if (where.contains("eye")) {
                if (diffCheck("eye"))
                    setLevelColor();
                else setLevelColorDiff();
            } else {
                if (diffCheck())
                    setLevelColor();
                else setLevelColorDiff();

            }
        }
        else {
            if (diffCheck())
                setLevelColor();
            else setLevelColorDiff();
        }
    }

    private static void setBattery() {
        Log.e("setBattery", "init / " + receiveResult);
        BluetoothActivity.battery = String.valueOf(receiveResult);
        deviceBattery = receiveResult;
        Log.e("deviceBattery", deviceBattery+"");

        if (receiveResult <= lowBattery) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                }
            });
        }
    }

    public static boolean diffCheck() {
        int wrinkleLevel=0;
        if (HomeActivity.wrinkleLevel.contains("A")) wrinkleLevel=1;
        else if (HomeActivity.wrinkleLevel.contains("B")) wrinkleLevel=2;
        else if (HomeActivity.wrinkleLevel.contains("C")) wrinkleLevel=3;

        if (deviceLevel==wrinkleLevel) {
            Log.e("wrinkleLevel", "같음");
            return true;
        } else {
            Log.e("wrinkleLevel", "다름");
            return false;
        }
    }

    public static boolean diffCheck(String where) {
        int wrinkleLevel=0;
        Log.e("where????", where);
        if (where.equals("eye")) {
            wrinkleLevel=1;
        }
        if (deviceLevel==wrinkleLevel) {
            Log.e("wrinkleLevel", "같음");
            return true;
        } else {
            Log.e("wrinkleLevel", "다름");
            return false;
        }
    }

    public static void setLevelColorDiff() {
        if (TreatActivity.direction!=null) TreatActivity.direction.setTextColor(Color.WHITE);
        if (TreatActivity_cheekleft.direction!=null) TreatActivity_cheekleft.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_cheekleft2.direction!=null) TreatActivity_cheekleft2.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_cheekright.direction!=null) TreatActivity_cheekright.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_cheekright2.direction!=null) TreatActivity_cheekright2.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_eye.direction!=null) TreatActivity_eye.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_eyeright2.direction!=null) TreatActivity_eyeright2.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_eyerleft2.direction!=null) TreatActivity_eyerleft2.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_forehead.direction!=null) TreatActivity_forehead.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_foreheadleft.direction!=null) TreatActivity_foreheadleft.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_foreheadright.direction!=null) TreatActivity_foreheadright.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_underleft.direction!=null) TreatActivity_underleft.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_underleft2.direction!=null) TreatActivity_underleft2.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_underright.direction!=null) TreatActivity_underright.direction.setTextColor(Color.parseColor("#9E0958"));
        if (TreatActivity_underright2.direction!=null) TreatActivity_underright2.direction.setTextColor(Color.parseColor("#9E0958"));
    }

    public static void setLevelColor() {
        if (TreatActivity.direction!=null) TreatActivity.direction.setTextColor(Color.WHITE);
        if (TreatActivity_cheekleft.direction!=null) TreatActivity_cheekleft.direction.setTextColor(Color.WHITE);
        if (TreatActivity_cheekleft2.direction!=null) TreatActivity_cheekleft2.direction.setTextColor(Color.WHITE);
        if (TreatActivity_cheekright.direction!=null) TreatActivity_cheekright.direction.setTextColor(Color.WHITE);
        if (TreatActivity_cheekright2.direction!=null) TreatActivity_cheekright2.direction.setTextColor(Color.WHITE);
        if (TreatActivity_eye.direction!=null) TreatActivity_eye.direction.setTextColor(Color.WHITE);
        if (TreatActivity_eyeright2.direction!=null) TreatActivity_eyeright2.direction.setTextColor(Color.WHITE);
        if (TreatActivity_eyerleft2.direction!=null) TreatActivity_eyerleft2.direction.setTextColor(Color.WHITE);
        if (TreatActivity_forehead.direction!=null) TreatActivity_forehead.direction.setTextColor(Color.WHITE);
        if (TreatActivity_foreheadleft.direction!=null) TreatActivity_foreheadleft.direction.setTextColor(Color.WHITE);
        if (TreatActivity_foreheadright.direction!=null) TreatActivity_foreheadright.direction.setTextColor(Color.WHITE);
        if (TreatActivity_underleft.direction!=null) TreatActivity_underleft.direction.setTextColor(Color.WHITE);
        if (TreatActivity_underleft2.direction!=null) TreatActivity_underleft2.direction.setTextColor(Color.WHITE);
        if (TreatActivity_underright.direction!=null) TreatActivity_underright.direction.setTextColor(Color.WHITE);
        if (TreatActivity_underright2.direction!=null) TreatActivity_underright2.direction.setTextColor(Color.WHITE);
    }

    private static void setLevelText() {
        if (TreatActivity.direction!=null) TreatActivity.direction.setText(staticLevel);
        if (TreatActivity_cheekleft.direction!=null) TreatActivity_cheekleft.direction.setText(staticLevel);
        if (TreatActivity_cheekleft2.direction!=null) TreatActivity_cheekleft2.direction.setText(staticLevel);
        if (TreatActivity_cheekright.direction!=null) TreatActivity_cheekright.direction.setText(staticLevel);
        if (TreatActivity_cheekright2.direction!=null) TreatActivity_cheekright2.direction.setText(staticLevel);
        if (TreatActivity_eye.direction!=null) TreatActivity_eye.direction.setText(staticLevel);
        if (TreatActivity_eyeright2.direction!=null) TreatActivity_eyeright2.direction.setText(staticLevel);
        if (TreatActivity_eyerleft2.direction!=null) TreatActivity_eyerleft2.direction.setText(staticLevel);
        if (TreatActivity_forehead.direction!=null) TreatActivity_forehead.direction.setText(staticLevel);
        if (TreatActivity_foreheadleft.direction!=null) TreatActivity_foreheadleft.direction.setText(staticLevel);
        if (TreatActivity_foreheadright.direction!=null) TreatActivity_foreheadright.direction.setText(staticLevel);
        if (TreatActivity_underleft.direction!=null) TreatActivity_underleft.direction.setText(staticLevel);
        if (TreatActivity_underleft2.direction!=null) TreatActivity_underleft2.direction.setText(staticLevel);
        if (TreatActivity_underright.direction!=null) TreatActivity_underright.direction.setText(staticLevel);
        if (TreatActivity_underright2.direction!=null) TreatActivity_underright2.direction.setText(staticLevel);
    }

    public static void receiveData() {
        Log.e("function receiveData", "init");
        if (kind!=null) {
            Log.e("function receiveData", kind +"/"+receiveResult);

            switch (kind) {
                case "21":
                    deviceLevel = receiveResult;
                    setDeviceLevel();
                    break;

                case "32": case "33":
                    setMoistureActivity();
                    break;

                case "41": case "42": case "43": case "44":
                    setTreatActivity();

                case "62":
                    Log.e("switch(kind)", "62");
                    setBattery();
                    break;

            }

        } else if (kind==null) Log.e("receiveData", "kind==null");
        else Log.e("receiveData", "receiveResult==null");

        Log.e("function receiveData", "fin");
    }

    public class CustomBitmapPool implements BitmapPool {
        @Override
        public int getMaxSize() {
            return 0;
        }

        @Override
        public void setSizeMultiplier(float sizeMultiplier) {

        }

        @Override
        public boolean put(Bitmap bitmap) {
            return false;
        }

        @Override
        public Bitmap get(int width, int height, Bitmap.Config config) {
            return null;
        }

        @Override
        public Bitmap getDirty(int width, int height, Bitmap.Config config) {
            return null;
        }

        @Override
        public void clearMemory() {
        }

        @Override
        public void trimMemory(int level) {
        }
    }

    private void storeCropImage(Bitmap bitmap, String filePath){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel";
        File directory_smartWheel = new File(dirPath);

        if(!directory_smartWheel.exists()){
            directory_smartWheel.mkdir();
        }

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try{
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,out);

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(copyFile)));

            out.flush();
            out.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void screenshot(){
        backgroundimg.setImageBitmap(null);
        rs = RenderScript.create(this);
        View view=getWindow().getDecorView();
        view.setDrawingCacheEnabled(false);
        view.setDrawingCacheEnabled(true);
        bitamp = view.getDrawingCache();
        RSBlurProcessor rsBlurProcessor = new RSBlurProcessor(rs);
        blurBitMap = rsBlurProcessor.blur(bitamp, 20f, 3);
        backgroundimg.setImageBitmap(blurBitMap);
        backgroundimg.startAnimation(alphaback);
    }

    public void screenshotdash(){
        dashback.setImageBitmap(null);
        rs2 = RenderScript.create(this);
        View view=getWindow().getDecorView();
        view.setDrawingCacheEnabled(false);
        view.setDrawingCacheEnabled(true);
        bitamp2 = view.getDrawingCache();
        RSBlurProcessor rsBlurProcessor = new RSBlurProcessor(rs2);
        blurBitMap2 = rsBlurProcessor.blur(bitamp2, 20f, 3);
        dashback.setImageBitmap(blurBitMap2);
        dashback.startAnimation(alphaback);
    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action))
                isConn = true;
            else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                isConn = false;
                Intent intentBT = new Intent(mcontext, BTOnActivity.class);
                mcontext.startActivity(intentBT);
            }
            else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // 여기 서비스 성공
                List<BluetoothGattService> gattServices = mBluetoothLeService.getSupportedGattServices();
                if (gattServices != null) {
                }

                charas = null;
                for (BluetoothGattService gattService : gattServices) {
                    gattCharacteristics = gattService.getCharacteristics();

                    charas = new ArrayList<>();

                    // Loops through available Characteristics.
                    for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                        charas.add(gattCharacteristic);
                    }
                    if (isFirst)
                        mGattCharacteristics.add(charas);
                }

                List<BluetoothGattService> services = mBluetoothGatt.getServices();
                for (final BluetoothGattService service : services) {
                    characteristics = service.getCharacteristics();
                }

                isFirst = false;
                Log.e("setNotification", "호출");
                setNotification();
            }
            else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                // 으아늿
            }
        }
    };
}