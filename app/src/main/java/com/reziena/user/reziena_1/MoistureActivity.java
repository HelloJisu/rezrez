package com.reziena.user.reziena_1;import android.content.Context;import android.content.Intent;import android.content.SharedPreferences;import android.graphics.Rect;import android.os.AsyncTask;import android.os.Bundle;import android.os.Handler;import android.support.design.widget.BottomSheetBehavior;import android.support.v7.app.AppCompatActivity;import android.util.Log;import android.view.Display;import android.view.MotionEvent;import android.view.View;import android.view.WindowManager;import android.widget.ImageButton;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.RelativeLayout;import android.widget.SeekBar;import android.widget.TextView;import android.widget.Toast;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;import java.io.BufferedReader;import java.io.InputStream;import java.io.InputStreamReader;import java.io.OutputStream;import java.net.HttpURLConnection;import java.net.URL;import java.text.SimpleDateFormat;import java.util.Date;import java.util.Locale;import java.util.Random;import java.util.Timer;import java.util.TimerTask;public class MoistureActivity extends AppCompatActivity {    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();    private DatabaseReference databaseReference = firebaseDatabase.getReference();    LinearLayout imageButton,popline;    public static ImageView moisture_image;    public TextView cancel, okay, again, moisture_status;    private static TextView result_grade, result_per,no;    public static TextView mois_status;    public static RelativeLayout moisture_result;    static Handler moisHandler = new Handler();    public static String grade, per = null;    Intent home;    TimerTask second;    public static int moisRand=8;    public static boolean moisNow = false;    public static boolean ing = false;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_moisture);        // 여기!!!        if (HomeActivity.isConn) {            HomeActivity.send("0x31");            //Toast.makeText(getApplicationContext(), "Moisture Measuring Mode 진입 (CODE: 0x31)", Toast.LENGTH_SHORT).show();        } else {            Intent intent = new Intent(getApplicationContext(), BTNoActivity.class);            startActivity(intent);            finish();        }        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;        lpWindow.dimAmount = 0.5f;        lpWindow.copyFrom(getWindow().getAttributes());        lpWindow.width = 1000;        lpWindow.height = 1100;        getWindow().setAttributes(lpWindow);        // popupt창 사이즈 지정        imageButton = findViewById(R.id.imageButton);        cancel = findViewById(R.id.cancel);        mois_status = findViewById(R.id.status);        okay = findViewById(R.id.okay);        moisture_image = findViewById(R.id.moisture_image);        moisture_result = findViewById(R.id.moisture_result);        //moisture_status = findViewById(R.id.moisture_status);        result_grade = findViewById(R.id.result_grade);        result_per = findViewById(R.id.result_per);        no = findViewById(R.id.no);        popline=findViewById(R.id.popline);        moisture_result.setVisibility(View.INVISIBLE);        mois_status.setText("Follow the Picture");        home = getIntent();        /*if (BluetoothConnectionService.success) {            HomeActivity.send("0x31");        } else {            Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);            startActivity(intent);            finish();        }*/        // seekbar 터치 막기        //Random rand = new Random();        //int r = rand.nextInt(6);        View.OnClickListener onClickListener = new View.OnClickListener() {            @Override            public void onClick(View v) {                switch (v.getId()) {                    case R.id.imageButton: case R.id.no:                        Log.e("moisture", "cancel");                        HomeActivity.send("0x34");  // 측정취소                        homeactivity.dashback.setImageResource(0);                        finish();                        break;                }            }        };        imageButton.setOnClickListener(onClickListener);        no.setOnClickListener(onClickListener);        second = new TimerTask() {            @Override            public void run() {                runOnUiThread(() -> {                    if (moisRand!=8) {                        setMoisFront();                        setMoisResult();                        second.cancel();                    }                });            }        };        Timer timer = new Timer();        //timer.schedule(second, 0, 1000);    }    public static void setMoisResult() {        Log.e("setMoisResult", "init");        moisHandler.post(new Runnable() {            @Override            public void run() {                if (moisture_image!=null) moisture_image.setVisibility(View.INVISIBLE);                if (moisture_result!=null) moisture_result.setVisibility(View.VISIBLE);                if (per==null) {                    if(result_grade!=null) result_grade.setText("0");                    if(result_per!=null) result_per.setText("측정에 실패했습니다");                    //  moisture_status.setText("");                } else {                    if(result_grade!=null) result_grade.setText(grade);                    if(result_per!=null) result_per.setText(per + "% of moisture \n contained");                    if(mois_status!=null) mois_status.setText("Complete");                    if(no!=null) no.setText("Finish");                }            }        });        moisRand = 8;        Log.e("setMoisResult", "Fin");    }    public static void setMoisFront() {        Log.e("setMoisFront", "init");        if (moisRand!=8) {            if (moisRand<=18) grade = "C";            if (moisRand>=20 && moisRand<=40) grade="C+";            if (moisRand>=42 && moisRand<=48) grade="B";            if (moisRand>=50 && moisRand<=57) grade="B+";            if (moisRand>=60 && moisRand<=68) grade="A";            if (moisRand>=71 && moisRand<=99) grade="A+";        }        setMoisResult();    }    protected void onResume() {        super.onResume();        moisNow = true;    }    protected void onPause() {        super.onPause();        moisNow = false;        ing = false;        moisRand = 8;        Log.e("onPause", "moisRand=="+String.valueOf(moisRand));        second.cancel();        if (per != null) {            save();        }    }    private void save() {        setData task = new setData();        task.execute("http://"+HomeActivity.IP_Address+"/saveMoisture.php", per);        int bef_m=-8;        // 원래 모이스처 가져오기        SharedPreferences bef_moisture = getSharedPreferences("now_m", MODE_PRIVATE);        bef_m = bef_moisture.getInt("now_m", -8);        Log.e("bef_m", String.valueOf(bef_m));        // 새로운 모이스처 저장하기        SharedPreferences now_moisturse = getSharedPreferences("now_m", MODE_PRIVATE);        SharedPreferences bef_moisturse = getSharedPreferences("bef_m", MODE_PRIVATE);        SharedPreferences.Editor editor1 = now_moisturse.edit();        SharedPreferences.Editor editor2 = bef_moisturse.edit();        editor1.putInt("now_m", Integer.parseInt(per));        editor2.putInt("bef_m", bef_m);        editor1.commit();        editor2.commit();        Log.e("bef_m ", bef_m+"퍼센트");        Log.e("now_m ", per+"퍼센트");        per = null;    }    class setData extends AsyncTask<String, Void, String> {        @Override        protected String doInBackground(String... params) {            String serverURL = params[0];            String per = params[1];            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );            Date currentTime = new Date();            String date = mSimpleDateFormat.format ( currentTime );            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);            String userID = sp_userID.getString("userID", "");            String postParameters = "date="+date+"&id="+userID+"&per="+per;            Log.e("moisture-postParameters", postParameters);            try {                URL url = new URL(serverURL);                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();                httpURLConnection.setReadTimeout(5000);                httpURLConnection.setConnectTimeout(5000);;                httpURLConnection.setRequestMethod("POST");                httpURLConnection.connect();                OutputStream outputStream = httpURLConnection.getOutputStream();                outputStream.write(postParameters.getBytes("UTF-8"));                outputStream.flush();                outputStream.close();                // response                int responseStatusCode = httpURLConnection.getResponseCode();                String responseStatusMessage = httpURLConnection.getResponseMessage();                Log.e("response-moisture", "POST response Code - " + responseStatusCode);                Log.e("response-moisture", "POST response Message - "+ responseStatusMessage);            } catch (Exception e) {                Log.e("ERROR", "InsertDataError ", e);            }            return null;        }    }    public boolean dispatchTouchEvent(MotionEvent ev){        Rect dialogBounds = new Rect();        getWindow().getDecorView().getHitRect(dialogBounds);        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){            return false;        }        return super.dispatchTouchEvent(ev);    }    @Override    public void onBackPressed() {        super.onBackPressed();        homeactivity.dashback.setImageResource(0);    }}