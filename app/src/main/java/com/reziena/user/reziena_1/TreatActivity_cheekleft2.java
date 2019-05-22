package com.reziena.user.reziena_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.RenderScript;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.reziena.user.reziena_1.utils.RSBlurProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TreatActivity_cheekleft2 extends AppCompatActivity {

    RenderScript rs;
    Bitmap blurBitMap;
    private static Bitmap bitamp;
    public static Activity cheekleftactivity;
    private long mLastClickTime = 0;
    String wrinkle_string;
    ImageView backgroundimg;
    String cheekleftstring,cheekrightstring;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, question;
    LinearLayout component;
    TextView component_txt,u_tright_txt1,u_tright_txt2,u_tleft_txt1,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    int cheekcount=0, data=0, level=0, timer_sec;
    public static int countStart=0, countFin=0;
    ImageView c_tleft_line1,c_tleft_line2,c_tleft_line3,c_tleft_line4,c_tleft_line5,c_tleft_line6,c_tleft_line7,c_tleft_line8
            ,c_tleft_line9,c_tleft_line10,c_tleft_line11,c_tleft_line12,c_tleft_line13,c_tleft_line14,c_tleft_line15,c_tleft_line16,c_tleft_line17
            ,c_tleft_line18,c_tleft_line19,c_tleft_line20,c_tleft_line21,c_tleft_line22;
    TimerTask second;
    LinearLayout back;
    String part;
    AnimationDrawable ctlani1,ctlani2,ctlani3,ctlani4,ctlani5,ctlani6,ctlani7,ctlani8,ctlani9,ctlani10,ctlani11,ctlani12,ctlani13,ctlani14,ctlani15
            ,ctlani16,ctlani17,ctlani18,ctlani19,ctlani20,ctlani21,ctlani22;
    static String finish;
    ImageView content1, content2;
    public static String IP_Address = "52.32.36.182";
    String treat;
    public static TextView direction;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
    Date currentTime = new Date();
    String date = mSimpleDateFormat.format ( currentTime );
    public static ImageView imageView2;

    public void animation() {
        second = new TimerTask() {
            @Override
            public void run() {
                // end of run
                runOnUiThread(() -> {
                    if (countStart == 15) {
                        String str ="THIS COLUMN HAS 14 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
                        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
                        ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 73, 127, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        component_txt.setText(ssb);
                        Log.e("countStart==","1");
                        c_tleft_line15.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani15 = (AnimationDrawable) c_tleft_line15.getBackground();
                        ctlani15.start();
                    } if (countFin == 15) {
                        Log.e("countFin==","1");
                        if (ctlani15!=null) ctlani15.stop();
                        c_tleft_txt1.setText("7 left");
                    }
                    if (countStart == 16) {
                        Log.e("countStart==","2");
                        c_tleft_line16.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani16 = (AnimationDrawable) c_tleft_line16.getBackground();
                        ctlani16.start();
                    } if (countFin == 16) {
                        Log.e("countFin==","2");
                        if (ctlani16!=null) ctlani16.stop();
                        c_tleft_txt1.setText("6 left");
                    }
                    if (countStart == 17) {
                        Log.e("countStart==","3");
                        c_tleft_line17.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani17 = (AnimationDrawable) c_tleft_line17.getBackground();
                        ctlani17.start();
                    } if (countFin == 17) {
                        Log.e("countFin==","3");
                        if (ctlani17!=null) ctlani17.stop();
                        c_tleft_txt1.setText("5 left");
                    }
                    if (countStart == 18) {
                        c_tleft_line18.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani18 = (AnimationDrawable) c_tleft_line18.getBackground();
                        ctlani18.start();
                    } if (countFin == 18) {
                        if (ctlani18!=null) ctlani18.stop();
                        c_tleft_txt1.setText("4 left");
                        c_tleft_txt1.setTextColor(Color.parseColor("#450969"));
                    }
                    if (countStart == 19) {
                        c_tleft_line19.setBackgroundResource(R.drawable.cheekleftmiddle1);
                        ctlani19 = (AnimationDrawable) c_tleft_line19.getBackground();
                        ctlani19.start();
                    } if (countFin == 19) {
                        if (ctlani19!=null) ctlani19.stop();
                        c_tleft_txt1.setText("3 left");
                        c_tleft_txt1.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    if (countStart == 20) {
                        c_tleft_line20.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani20 = (AnimationDrawable) c_tleft_line20.getBackground();
                        ctlani20.start();
                    } if (countFin == 20) {
                        if (ctlani20!=null) ctlani20.stop();
                        c_tleft_txt1.setText("2 left");
                    }
                    if (countStart == 21) {
                        c_tleft_line21.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani21 = (AnimationDrawable) c_tleft_line21.getBackground();
                        ctlani21.start();
                    } if (countFin == 21) {
                        if (ctlani21!=null) ctlani21.stop();
                        c_tleft_txt1.setText("1 left");
                    }
                    if (countStart == 22) {
                        c_tleft_line22.setBackgroundResource(R.drawable.cheekleftanim1);
                        ctlani22 = (AnimationDrawable) c_tleft_line22.getBackground();
                        ctlani22.start();
                    } if (countFin == 22) {
                        if (ctlani22!=null) ctlani22.stop();
                        c_tleft_txt1.setText("DONE");
                        c_tleft_txt1.setTextColor(Color.parseColor("#9E0958"));
                        data = 25;
                        cheekleftstring="true";

                        second.cancel();
                        //
                        getDataTreat();
                        //GetData task = new GetData();
                        //task.execute("http://"+IP_Address+"/callingTreathome.php", "");

                        // 여기서 고쵸
                        SharedPreferences sp_treat = getSharedPreferences("cheek_l", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sp_treat.edit();
                        editor1.putInt("cheek_l", 0);
                        editor1.commit();

                        if (! TreatActivity_cheekleft2.this.isFinishing()) {
                            Intent intent = new Intent(getApplicationContext(),DoneActivity.class);
                            intent.putExtra("stringlist","cheekleft");
                            startActivity(intent);
                        }
                    }
                    if (countStart == 1) {
                        c_tleft_line1.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani1 = (AnimationDrawable) c_tleft_line1.getBackground();
                        ctlani1.start();
                    } if (countFin == 1) {
                        if (ctlani1!=null) ctlani1.stop();
                        c_tleft_txt2.setText("13 left");
                    }
                    if (countStart == 2) {
                        c_tleft_line2.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani2 = (AnimationDrawable) c_tleft_line2.getBackground();
                        ctlani2.start();
                    } if (countFin == 2) {
                        if (ctlani2!=null) ctlani2.stop();
                        c_tleft_txt2.setText("12 left");
                    }
                    if (countStart == 3) {
                        c_tleft_line3.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani3 = (AnimationDrawable) c_tleft_line3.getBackground();
                        ctlani3.start();
                    } if (countFin == 3) {
                        if (ctlani3!=null) ctlani3.stop();
                        c_tleft_txt2.setText("11 left");
                    }
                    if (countStart == 4) {
                        c_tleft_line4.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani4 = (AnimationDrawable) c_tleft_line4.getBackground();
                        ctlani4.start();
                    } if (countFin == 4) {
                        if (ctlani4!=null) ctlani4.stop();
                        c_tleft_txt2.setText("10 left");
                    }
                    if (countStart == 5) {
                        c_tleft_line5.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani5 = (AnimationDrawable) c_tleft_line5.getBackground();
                        ctlani5.start();
                    } if (countFin == 5) {
                        if (ctlani5!=null) ctlani5.stop();
                        c_tleft_txt2.setText("9 left");
                    }
                    if (countStart == 6) {
                        c_tleft_line6.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani6 = (AnimationDrawable) c_tleft_line6.getBackground();
                        ctlani6.start();
                    } if (countFin == 6) {
                        if (ctlani6!=null) ctlani6.stop();
                        c_tleft_txt2.setText("8 left");
                    }
                    if (countStart == 7) {
                        c_tleft_line7.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani7 = (AnimationDrawable) c_tleft_line7.getBackground();
                        ctlani7.start();
                    } if (countFin == 7) {
                        if (ctlani7!=null) ctlani7.stop();
                        c_tleft_txt2.setText("7 left");
                        c_tleft_txt2.setTextColor(Color.parseColor("#450969"));
                    }
                    if (countStart == 8) {
                        c_tleft_line8.setBackgroundResource(R.drawable.cheekleftmiddle2);
                        ctlani8 = (AnimationDrawable) c_tleft_line8.getBackground();
                        ctlani8.start();
                    } if (countFin == 8) {
                        if (ctlani8!=null) ctlani8.stop();
                        c_tleft_txt2.setText("6 left");
                        c_tleft_txt2.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    if (countStart == 9) {
                        c_tleft_line9.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani9 = (AnimationDrawable) c_tleft_line9.getBackground();
                        ctlani9.start();
                    } if (countFin == 9) {
                        if (ctlani9!=null) ctlani9.stop();
                        c_tleft_txt2.setText("5 left");
                    }
                    if (countStart == 10) {
                        c_tleft_line10.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani10 = (AnimationDrawable) c_tleft_line10.getBackground();
                        ctlani10.start();
                    } if (countFin == 10) {
                        if (ctlani10!=null) ctlani10.stop();
                        c_tleft_txt2.setText("4 left");
                    }
                    if (countStart == 11) {
                        c_tleft_line11.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani11 = (AnimationDrawable) c_tleft_line11.getBackground();
                        ctlani11.start();
                    } if (countFin == 11) {
                        if (ctlani11!=null) ctlani11.stop();
                        c_tleft_txt2.setText("3 left");
                    }
                    if (countStart == 12) {
                        c_tleft_line12.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani12 = (AnimationDrawable) c_tleft_line12.getBackground();
                        ctlani12.start();
                    } if (countFin == 12) {
                        if (ctlani12!=null) ctlani12.stop();
                        c_tleft_txt2.setText("2 left");
                    }
                    if (countStart == 13) {
                        c_tleft_line13.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani13 = (AnimationDrawable) c_tleft_line13.getBackground();
                        ctlani13.start();
                    } if (countFin == 13) {
                        if (ctlani13!=null) ctlani13.stop();
                        c_tleft_txt2.setText("1 left");
                    }
                    if (countStart == 14) {
                        c_tleft_line14.setBackgroundResource(R.drawable.cheekleftanim2);
                        ctlani14 = (AnimationDrawable) c_tleft_line14.getBackground();
                        ctlani14.start();
                    } if (countFin == 14) {
                        if (ctlani14!=null) ctlani14.stop();
                        component_txt.setText("GOOD JOB");
                        c_tleft_txt2.setText("DONE");
                        c_tleft_txt2.setTextColor(Color.parseColor("#9E0958"));
                    }
                    setBack(countFin);

                }); // end of runOnUiThread
            }   // end of run
        };  // second timerTask
        Timer timer = new Timer();
        timer.schedule(second, 0, 1000);
    }

    private void setBack(int count) {
        Log.e("count == ", count+"");
        switch (count) {
            case 22:
                c_tleft_line15.setBackgroundResource(R.drawable.line321finish);
            case 21:
                c_tleft_line16.setBackgroundResource(R.drawable.line321finish);
            case 20:
                c_tleft_line17.setBackgroundResource(R.drawable.line321finish);
            case 19:
                c_tleft_line18.setBackgroundResource(R.drawable.line321finish);
            case 18:
                c_tleft_line19.setBackgroundResource(R.drawable.linemf321);
            case 17:
                c_tleft_line20.setBackgroundResource(R.drawable.line321finish);
            case 16:
                c_tleft_line21.setBackgroundResource(R.drawable.line321finish);
            case 15:
                c_tleft_line22.setBackgroundResource(R.drawable.line321finish);
            case 14:
                c_tleft_line14.setBackgroundResource(R.drawable.line5finish);
            case 13:
                c_tleft_line13.setBackgroundResource(R.drawable.line5finish);
            case 12:
                c_tleft_line12.setBackgroundResource(R.drawable.line5finish);
            case 11:
                c_tleft_line11.setBackgroundResource(R.drawable.line5finish);
            case 10:
                c_tleft_line10.setBackgroundResource(R.drawable.line5finish);
            case 9:
                c_tleft_line9.setBackgroundResource(R.drawable.line5finish);
            case 8:
                c_tleft_line8.setBackgroundResource(R.drawable.linemf5);
            case 7:
                c_tleft_line7.setBackgroundResource(R.drawable.line5finish);
            case 6:
                c_tleft_line6.setBackgroundResource(R.drawable.line5finish);
            case 5:
                c_tleft_line5.setBackgroundResource(R.drawable.line5finish);
            case 4:
                c_tleft_line4.setBackgroundResource(R.drawable.line5finish);
            case 3:
                c_tleft_line3.setBackgroundResource(R.drawable.line5finish);
            case 2:
                c_tleft_line2.setBackgroundResource(R.drawable.line5finish);
            case 1:
                c_tleft_line1.setBackgroundResource(R.drawable.line5finish);
        }
    }

    private void getDataTreat() {
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        treat = treat_zone.getString("tZone", "");
        Log.e("treat_zone", treat);

        setDataTreat();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_cheekleft2);

        cheekleftactivity = TreatActivity_cheekleft2.this;

        //값 받아오기
        question = findViewById(R.id.question_cl2);
        content1 = findViewById(R.id.treatup_cl2);
        content2 = findViewById(R.id.treatdown_cl2);
        back=findViewById(R.id.backbutton);
        c_tleft_line1 = (ImageView) findViewById(R.id.c_tleft_line1);
        c_tleft_line2 = (ImageView) findViewById(R.id.c_tleft_line2);
        c_tleft_line3 = (ImageView) findViewById(R.id.c_tleft_line3);
        c_tleft_line4 = (ImageView) findViewById(R.id.c_tleft_line4);
        c_tleft_line5 = (ImageView) findViewById(R.id.c_tleft_line5);
        c_tleft_line6 = (ImageView) findViewById(R.id.c_tleft_line6);
        c_tleft_line7 = (ImageView) findViewById(R.id.c_tleft_line7);
        c_tleft_line8 = (ImageView) findViewById(R.id.c_tleft_line8);
        c_tleft_line9 = (ImageView) findViewById(R.id.c_tleft_line9);
        c_tleft_line10 = (ImageView) findViewById(R.id.c_tleft_line10);
        c_tleft_line11 = (ImageView) findViewById(R.id.c_tleft_line11);
        c_tleft_line12 = (ImageView) findViewById(R.id.c_tleft_line12);
        c_tleft_line13 = (ImageView) findViewById(R.id.c_tleft_line13);
        c_tleft_line14 = (ImageView) findViewById(R.id.c_tleft_line14);
        c_tleft_line15 = (ImageView) findViewById(R.id.c_tleft_line15);
        c_tleft_line16 = (ImageView) findViewById(R.id.c_tleft_line16);
        c_tleft_line17 = (ImageView) findViewById(R.id.c_tleft_line17);
        c_tleft_line18 = (ImageView) findViewById(R.id.c_tleft_line18);
        c_tleft_line19 = (ImageView) findViewById(R.id.c_tleft_line19);
        c_tleft_line20 = (ImageView) findViewById(R.id.c_tleft_line20);
        c_tleft_line21 = (ImageView) findViewById(R.id.c_tleft_line21);
        c_tleft_line22 = (ImageView) findViewById(R.id.c_tleft_line22);
        u_tleft_txt1 = (TextView) findViewById(R.id.u_tleft_txt1);
        u_tleft_txt2 = (TextView) findViewById(R.id.u_tleft_txt2);
        c_tright_txt1 = (TextView) findViewById(R.id.c_tright_txt1);
        c_tright_txt2 = (TextView) findViewById(R.id.c_tright_txt2);
        c_tleft_txt1 = (TextView) findViewById(R.id.c_tleft_txt1);
        c_tleft_txt2 = (TextView) findViewById(R.id.c_tleft_txt2);
        component_txt = findViewById(R.id.componenttxt_cl);
        backgroundimg = findViewById(R.id.background);
        imageView2 = findViewById(R.id.imageView2);
        direction = findViewById(R.id.direction);

        String str ="THIS COLUMN HAS 8 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 72, 126, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        component_txt.setText(ssb);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.backbutton:
                        finish();
                        break;
                    case R.id.question_cl2:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","cheekleft2");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","cheekleft2");
                        startActivity(intent);
                        break;
                }
            }
        };
        back.setOnClickListener(onClickListener);
        question.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);

        SharedPreferences sp_treat = getSharedPreferences("cheek_l", MODE_PRIVATE);
        int n = sp_treat.getInt("cheek_l", 0);
        //Log.e("아아아아앙시바ㅏ아ㅏ", String.valueOf(n));
        HomeActivity.countStart = n;
    }

    public void onResume() {
        super.onResume();

        //Log.e("지금은!!", "onResume()");

        if (HomeActivity.staticLevel!=null) direction.setText(HomeActivity.staticLevel);
        else direction.setText("DEVICE LEVEL : 0");
        SharedPreferences sp_treat = getSharedPreferences("cheek_l", MODE_PRIVATE);

        countFin = sp_treat.getInt("cheek_l", 0);
        setBack(countFin);

        imageView2.setClickable(true);
        question.setClickable(true);

        // 베터리
        if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
            HomeActivity.imageView2.setImageResource(R.drawable.bdev);
        else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
        if (HomeActivity.deviceBattery==-1)
            HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);

        HomeActivity.setDeviceLevel();
        animation();
    }

    private void setDataTreat() {
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = treat_zone.edit();
        editor2.putString("tZone", treat+"/cheek_l");
        editor2.commit();

        //Log.e("안희 좀...", treat+"/cheek_l");

        setDataTreat tasks1 = new setDataTreat();
        tasks1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", treat+"/cheek_l");

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"/cheek_l");

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
                httpURLConnection.setConnectTimeout(5000);

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

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);
            //Log.e("gfdesetrdf",getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String where = params[1];

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
                Log.e("postParameters", postParameters);
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

    /*class GetData extends AsyncTask<String, Void, String> {
        List<String[]> treatArray = new ArrayList<>();

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);

            Log.e("쉬발",getResult);
            if (getResult.contains("No_results")) {
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"cheek_l");

            } else {
                showResult(getResult);
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/updateTreat.php", treat+"/cheek_l");
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            Log.e("으악!!!!!!!!!!!!!!!",date);

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID+"&date="+date;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                Log.e("moisture-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.e("moisture-response", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("moisture-response", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("moisture-error-stream", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    treat=item.getString("value");
                }

                Log.e("databse",treat);
            } catch (JSONException e) {
                Log.d("treat-JSON", "showResult : "+e.getMessage());
            }
        }
    }*/

    public void onPause() {
        super.onPause();
        //Log.e("지금은!!", "onPause()");
        second.cancel();
        SharedPreferences sp_treat = getSharedPreferences("cheek_l", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp_treat.edit();
        editor1.putInt("cheek_l", countFin);
        editor1.commit();
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
}
