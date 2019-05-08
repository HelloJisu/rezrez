package com.reziena.user.reziena_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.RenderScript;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reziena.user.reziena_1.utils.RSBlurProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.objdetect.HOGDescriptor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TreatActivity extends AppCompatActivity {

    public static boolean todayCheck;
    String treatResult="";
    int wrinkleresult;
    String wrinkle;
    Intent home;
    RenderScript rs;
    Bitmap blurBitMap;
    Animation alphaback;
    private long mLastClickTime = 0;
    private static Bitmap bitamp;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, back, backgroundimg;
    LinearLayout component,backbutton;
    TextView component_txt,u_tright_txt1,u_tright_txt2,u_tleft_txt1,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    RelativeLayout treatback, underright_layout, underleft_layout,treat_default,cheekright_layout,cheekleft_layout;
    int cheekcount=0, undercount=0, foreheadcount=0, level=0, timer_sec, count=0;
    ImageView u_tright_line1,u_tright_line2,u_tright_line3,u_tright_line4,u_tright_line5,u_tright_line6,
            u_tright_line7,u_tright_line8,u_tright_line9,u_tright_line10,u_tright_line11,u_tright_line12,u_tright_line13,
            u_tleft_line1,u_tleft_line2,u_tleft_line3,u_tleft_line4,u_tleft_line5,u_tleft_line6,
            u_tleft_line7,u_tleft_line8,u_tleft_line9,u_tleft_line10,u_tleft_line11,u_tleft_line12,u_tleft_line13
            ,c_tright_line1,c_tright_line2,c_tright_line3,c_tright_line4,c_tright_line5,c_tright_line6,c_tright_line7,c_tright_line8
            ,c_tright_line9,c_tright_line10,c_tright_line11,c_tright_line12,c_tright_line13,c_tright_line14,c_tright_line15,c_tright_line16,c_tright_line17
            ,c_tright_line18,c_tright_line19,c_tright_line20,c_tright_line21,c_tright_line22,c_tleft_line1,c_tleft_line2,c_tleft_line3,c_tleft_line4,c_tleft_line5,c_tleft_line6,c_tleft_line7,c_tleft_line8
            ,c_tleft_line9,c_tleft_line10,c_tleft_line11,c_tleft_line12,c_tleft_line13,c_tleft_line14,c_tleft_line15,c_tleft_line16,c_tleft_line17
            ,c_tleft_line18,c_tleft_line19,c_tleft_line20,c_tleft_line21,c_tleft_line22,question;
    TimerTask second;
    String part;
    public static Activity treatactivity;
    ImageView imageView2;
    public static TextView direction;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat);
        treatactivity=TreatActivity.this;
        home = getIntent();
        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");
        cheekleftdata = databaseReference.child("result").child("cheekleftstring");
        cheekrightdata = databaseReference.child("result").child("cheekrightstring");

        Resources res = getResources();
        //값 받아오기

        backgroundimg=findViewById(R.id.background); question=findViewById(R.id.question_treat);
        forehead =  (ImageView)findViewById(R.id.forehead); underleft =  (ImageView)findViewById(R.id.underleft); underright =  (ImageView)findViewById(R.id.underright);
        cheekl =  (ImageView)findViewById(R.id.cheek_left); cheekr =  (ImageView)findViewById(R.id.cheek_right); mouth =  (ImageView)findViewById(R.id.mouth);
        eyeleft = (ImageView)findViewById(R.id.eyeleft); eyeright=(ImageView)findViewById(R.id.eyeright); component_txt=(TextView)findViewById(R.id.componenttxt);
        treatback = (RelativeLayout)findViewById(R.id.treatayout);
        back=(ImageView)findViewById(R.id.backw); u_tright_line1=(ImageView)findViewById(R.id.u_tright_line1); u_tright_line2=(ImageView)findViewById(R.id.u_tright_line2);
        u_tright_line3=(ImageView)findViewById(R.id.u_tright_line3); u_tright_line4=(ImageView)findViewById(R.id.u_tright_line4); u_tright_line5=(ImageView)findViewById(R.id.u_tright_line5);
        u_tright_line6=(ImageView)findViewById(R.id.u_tright_line6); u_tright_line7=(ImageView)findViewById(R.id.u_tright_line7); u_tright_line8=(ImageView)findViewById(R.id.u_tright_line8);
        u_tright_line9=(ImageView)findViewById(R.id.u_tright_line9); u_tright_line10=(ImageView)findViewById(R.id.u_tright_line10); u_tright_line11=(ImageView)findViewById(R.id.u_tright_line11);
        u_tright_line12=(ImageView)findViewById(R.id.u_tright_line12); u_tright_line13=(ImageView)findViewById(R.id.u_tright_line13); u_tleft_line1=(ImageView)findViewById(R.id.u_tleft_line1);
        u_tleft_line2=(ImageView)findViewById(R.id.u_tleft_line2); u_tleft_line3=(ImageView)findViewById(R.id.u_tleft_line3); u_tleft_line4=(ImageView)findViewById(R.id.u_tleft_line4);
        u_tleft_line5=(ImageView)findViewById(R.id.u_tleft_line5); u_tleft_line6=(ImageView)findViewById(R.id.u_tleft_line6); u_tleft_line7=(ImageView)findViewById(R.id.u_tleft_line7);
        u_tleft_line8=(ImageView)findViewById(R.id.u_tleft_line8); u_tleft_line9=(ImageView)findViewById(R.id.u_tleft_line9); u_tleft_line10=(ImageView)findViewById(R.id.u_tleft_line10);
        u_tleft_line11=(ImageView)findViewById(R.id.u_tleft_line11); u_tleft_line12=(ImageView)findViewById(R.id.u_tleft_line12); u_tleft_line13=(ImageView)findViewById(R.id.u_tleft_line13);
        c_tright_line1=(ImageView)findViewById(R.id.c_tright_line1); c_tright_line2=(ImageView)findViewById(R.id.c_tright_line2); c_tright_line3=(ImageView)findViewById(R.id.c_tright_line3);
        c_tright_line4=(ImageView)findViewById(R.id.c_tright_line4); c_tright_line5=(ImageView)findViewById(R.id.c_tright_line5); c_tright_line6=(ImageView)findViewById(R.id.c_tright_line6);
        c_tright_line7=(ImageView)findViewById(R.id.c_tright_line7); c_tright_line8=(ImageView)findViewById(R.id.c_tright_line8); c_tright_line9=(ImageView)findViewById(R.id.c_tright_line9);
        c_tright_line10=(ImageView)findViewById(R.id.c_tright_line10); c_tright_line11=(ImageView)findViewById(R.id.c_tright_line11); c_tright_line12=(ImageView)findViewById(R.id.c_tright_line12);
        c_tright_line13=(ImageView)findViewById(R.id.c_tright_line13); c_tright_line14=(ImageView)findViewById(R.id.c_tright_line14); c_tright_line15=(ImageView)findViewById(R.id.c_tright_line15);
        c_tright_line16=(ImageView)findViewById(R.id.c_tright_line16); c_tright_line17=(ImageView)findViewById(R.id.c_tright_line17); c_tright_line18=(ImageView)findViewById(R.id.c_tright_line18);
        c_tright_line19=(ImageView)findViewById(R.id.c_tright_line19); c_tright_line20=(ImageView)findViewById(R.id.c_tright_line20); c_tright_line21=(ImageView)findViewById(R.id.c_tright_line21);
        c_tright_line22=(ImageView)findViewById(R.id.c_tright_line22); c_tleft_line1=(ImageView)findViewById(R.id.c_tleft_line1); c_tleft_line2=(ImageView)findViewById(R.id.c_tleft_line2);
        c_tleft_line3=(ImageView)findViewById(R.id.c_tleft_line3); c_tleft_line4=(ImageView)findViewById(R.id.c_tleft_line4); c_tleft_line5=(ImageView)findViewById(R.id.c_tleft_line5);
        c_tleft_line6=(ImageView)findViewById(R.id.c_tleft_line6); c_tleft_line7=(ImageView)findViewById(R.id.c_tleft_line7); c_tleft_line8=(ImageView)findViewById(R.id.c_tleft_line8);
        c_tleft_line9=(ImageView)findViewById(R.id.c_tleft_line9); c_tleft_line10=(ImageView)findViewById(R.id.c_tleft_line10); c_tleft_line11=(ImageView)findViewById(R.id.c_tleft_line11);
        c_tleft_line12=(ImageView)findViewById(R.id.c_tleft_line12); c_tleft_line13=(ImageView)findViewById(R.id.c_tleft_line13); c_tleft_line14=(ImageView)findViewById(R.id.c_tleft_line14);
        c_tleft_line15=(ImageView)findViewById(R.id.c_tleft_line15); c_tleft_line16=(ImageView)findViewById(R.id.c_tleft_line16); c_tleft_line17=(ImageView)findViewById(R.id.c_tleft_line17);
        c_tleft_line18=(ImageView)findViewById(R.id.c_tleft_line18); c_tleft_line19=(ImageView)findViewById(R.id.c_tleft_line19); c_tleft_line20=(ImageView)findViewById(R.id.c_tleft_line20);
        c_tleft_line21=(ImageView)findViewById(R.id.c_tleft_line21); c_tleft_line22=(ImageView)findViewById(R.id.c_tleft_line22);
        u_tright_txt1=(TextView)findViewById(R.id.u_tright_txt1); u_tright_txt2=(TextView)findViewById(R.id.u_tright_txt2); u_tleft_txt1=(TextView)findViewById(R.id.u_tleft_txt1);
        u_tleft_txt2=(TextView)findViewById(R.id.u_tleft_txt2); c_tright_txt1=(TextView)findViewById(R.id.c_tright_txt1); c_tright_txt2=(TextView)findViewById(R.id.c_tright_txt2);
        c_tleft_txt1=(TextView)findViewById(R.id.c_tleft_txt1);c_tleft_txt2=(TextView)findViewById(R.id.c_tleft_txt2);
        backbutton=findViewById(R.id.backbutton);
        imageView2 = findViewById(R.id.imageView2);
        direction = findViewById(R.id.direction);

        // 여기!!!
        if (HomeActivity.isConn) {
            HomeActivity.send("0x22");
        } else {
            Intent intent = new Intent(getApplicationContext(), BTNoActivity.class);
            startActivity(intent);
            finish();
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                level=1;
                switch (v.getId()) {
                    case R.id.backbutton:
                        finish();
                        break;
                    case R.id.underright:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = null;
                            intent = new Intent(getBaseContext(), TreatActivity_underright.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.underleft:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = null;
                            intent = new Intent(getBaseContext(), TreatActivity_underleft.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.cheek_right:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = null;
                            intent = new Intent(getBaseContext(), TreatActivity_cheekright.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","treat");
                        startActivity(intent);
                        break;

                    case R.id.cheek_left:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = null;
                            intent = new Intent(getBaseContext(), TreatActivity_cheekleft.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.forehead:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = null;
                            intent = new Intent(getBaseContext(), TreatActivity_forehead.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.eyeright:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = "eye";
                            intent = new Intent(getBaseContext(), TreatActivity_eye.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.eyeleft:
                        if (todayCheck) {
                            intent = new Intent(getBaseContext(), TreatDoneActivity.class);
                            startActivity(intent);
                        } else {
                            HomeActivity.where = "eye";
                            intent = new Intent(getBaseContext(), TreatActivity_eye.class);
                            intent.putExtra("wrinkle", wrinkleresult);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        break;
                    case R.id.question_treat:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","treat");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                }
            }
        };
        question.setOnClickListener(onClickListener);
        backbutton.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        forehead.setOnClickListener(onClickListener);
        underleft.setOnClickListener(onClickListener);
        underright.setOnClickListener(onClickListener);
        cheekl.setOnClickListener(onClickListener);
        cheekr.setOnClickListener(onClickListener);
        mouth.setOnClickListener(onClickListener);
        eyeleft.setOnClickListener(onClickListener);
        eyeright.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
    }

    public void onStart() { super.onStart(); }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.where = null;
        if (HomeActivity.staticLevel!=null) direction.setText(HomeActivity.staticLevel);
        else direction.setText("DEVICE LEVEL : 0");
        imageView2.setClickable(true);
        question.setClickable(true);

        // 베터리
        if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
            imageView2.setImageResource(R.drawable.bdev);
        else imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
        if (HomeActivity.deviceBattery==-1)
            imageView2.setImageResource(R.drawable.nondeviceicon);

        getDataTreat();
        HomeActivity.setDeviceLevel();
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
    }

    private void getDataTreat() {

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String end = mSimpleDateFormat.format(currentTime);
        String start;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDay = null;
        Date endDay = null;

        //Log.e("getDataTreat init", "TreatActivity");
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences treat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        treatResult = treat_zone.getString("tZone", "tZone");
        start = treat_date.getString("tDate", "tDate");
        //Log.e("treat_zone~!~!", treatResult);
        //Log.e("treat_date~!~!", start);

        if (!start.equals("tDate")) {
            if (start.equals(end)) {
                // dyrl
                todayCheck = true;
            } else todayCheck = false;
        } else todayCheck = false;

        if (todayCheck) {
            component_txt.setText("Done for today’s area");
        }

        String[] hihi = treatResult.split("/");
        //Log.e("hihi.length==", String.valueOf(hihi.length));
        if (treatResult=="tZone") {
            GetData task = new GetData();
            task.execute("http://"+HomeActivity.IP_Address+"/callingTreathome.php", "");
        } else if (hihi.length>=8) {
            //Log.e("Start", start);
            //Log.e("End", end);
            try {
                startDay = formatter.parse(start);
                endDay = formatter.parse(end);
            } catch (ParseException e) { e.printStackTrace(); }

            // 오늘 - 첫날 빼기 (몇일인지) == diffDay
            long diff = endDay.getTime() - startDay.getTime();
            long diffDay = diff/(24 * 60 * 60 * 1000);
            //Log.e("diffy==", String.valueOf(diff));
            //Log.e("diffDay==", String.valueOf(diffDay));

            // 하루 지나면
            if (diffDay>0) {
                treatResult = "";
                setDataTreat();

                setDataTreat task1 = new setDataTreat();
                task1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", "");

                //setData task = new setData();
                //task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "");
            } checkResult();
        }
        else {
            checkResult();
        }
    }

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);
            //Log.e("gfdesetrdf",getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String where = params[1];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "date="+date+"&id="+userID+"&where="+where;
            //Log.e("sdffghrfhgyughj", "update/"+postParameters);

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);;

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("postParameters", postParameters);
                outputStream.flush();
                outputStream.close();
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    //Log.e("gfhgfyghjgyj", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    //Log.e("hjfrdsrtrth", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("hjhkjhukhtyrdfh", e.getMessage());
            }
            return null;
        }
    }

    class setDataTreat extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);
            //Log.e("setDataTreat",getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String where = params[1];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID+"&where="+where;
            //Log.e("setDataTreat", "update/"+postParameters);

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);;

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("postParameters", postParameters);
                outputStream.flush();
                outputStream.close();
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    //Log.e("setDataTreat", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    //Log.e("setDataTreat", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("setDataTreat", e.getMessage());
            }
            return null;

        }
    }

    private void setDataTreat() {
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = treat_zone.edit();
        editor2.putString("tZone", treatResult);
        editor2.commit();
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            //Log.e("getTreathome", "onPostExecute - " + getResult);

            if (getResult==null) {}
            else if (getResult.contains("No_results")) {}
            else {
                showResult(getResult);
                checkResult();
                setDataTreat();
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
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                //Log.e("treat1-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    //Log.e("treat1-response", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    //Log.e("treat1-response", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("treat1-error-stream", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    treatResult=item.getString("treat");
                }

            } catch (JSONException e) {
                Log.e("treat1-JSON", "showResult : "+e.getMessage());
            }

        }
    }

    public void checkResult() {

        // underleft
        if (treatResult.contains("under_l")) {
            underleft.setEnabled(false);
            underleft.setImageResource(R.drawable.underleftdone);
        }
        // underright
        if (treatResult.contains("under_r")) {
            underright.setEnabled(false);
            underright.setImageResource(R.drawable.underrightdone);
        }
        // cheekl
        if (treatResult.contains("cheek_l")) {
            cheekl.setEnabled(false);
            cheekl.setImageResource(R.drawable.cheekleftdone);
        }
        // cheekr
        if (treatResult.contains("cheek_r")) {
            cheekr.setEnabled(false);
            cheekr.setImageResource(R.drawable.cheekrightdone);
        }
        // eye_l
        if (treatResult.contains("eye_l")) {
            eyeleft.setEnabled(false);
            eyeleft.setImageResource(R.drawable.doneeyeleft);
        }
        // eye_r
        if (treatResult.contains("eye_r")) {
            eyeright.setEnabled(false);
            eyeright.setImageResource(R.drawable.doneeyeright);
        }
        // fore_l
        /*if (treatResult.contains("fore_l")) {
            forehead.setEnabled(false);
            forehead.setImageResource(R.drawable.doneforehead);
        }
        // fore_r
        if (treatResult.contains("fore_r")) {
            forehead.setEnabled(false);
            forehead.setImageResource(R.drawable.doneforehead);
        }*/

        if (treatResult.contains("fore_r")&&treatResult.contains("fore_l")) {
            forehead.setEnabled(false);
            forehead.setImageResource(R.drawable.doneforehead);
        }
    }
}
