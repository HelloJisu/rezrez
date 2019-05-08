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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reziena.user.reziena_1.utils.RSBlurProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.Point;

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

public class TreatActivity_underleft2 extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinkle_txt;
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;
    String wrinkle_string;
    String underleftstring,underrightstring;
    public static Activity underleftativity;
    private long mLastClickTime = 0;
    RenderScript rs;
    Bitmap blurBitMap;
    private static Bitmap bitamp;
    ImageView content1, content2;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, backgroundimg;
    LinearLayout component;
    TextView component_txt,u_tright_txt1,u_tright_txt2,u_tleft_txt1,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    RelativeLayout treatback, underleft_layout,treat_default;
    int undercount=0, data=0, level=0, timer_sec;
    public static int countStart=0, countFin=0;
    ImageView u_tleft_line1,u_tleft_line2,u_tleft_line3,u_tleft_line4,u_tleft_line5,u_tleft_line6,
            u_tleft_line7,u_tleft_line8,u_tleft_line9,u_tleft_line10,u_tleft_line11,u_tleft_line12,u_tleft_line13, question;
    LinearLayout back;
    TimerTask second;
    String part;
    AnimationDrawable utlani1,utlani2,utlani3,utlani4,utlani5,utlani6,utlani7,utlani8,utlani9,utlani10,utlani11,utlani12,utlani13;
    static String finish;
    public static String IP_Address = "52.32.36.182";
    String treat;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
    Date currentTime = new Date();
    String date = mSimpleDateFormat.format ( currentTime );

    public static ImageView imageView2;
    public static TextView direction;

    public static void intentpage(String string) {
        finish=string;
    }
    public void animation() {
        //Log.e("underleft_init", "animation");
        second = new TimerTask() {
            @Override
            public void run() {
                //Log.e("카운터",String.valueOf(count_ul));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.e("countFin==", String.valueOf(countFin));
                        if (countStart == 1) {
                            u_tleft_line8.setBackgroundResource(R.drawable.underleftanim1);
                            utlani8 = (AnimationDrawable) u_tleft_line8.getBackground();
                            utlani8.start();
                        } if (countFin == 1) {
                            if (utlani8!=null) utlani8.stop();
                            u_tleft_txt1.setText("5 left");
                        }

                        if (countStart == 2) {
                            u_tleft_line9.setBackgroundResource(R.drawable.underleftanim1);
                            utlani9 = (AnimationDrawable) u_tleft_line9.getBackground();
                            utlani9.start();
                        } if (countFin == 2) {
                            if (utlani9!=null) utlani9.stop();
                            u_tleft_txt1.setText("4 left");
                        }
                        if (countStart == 3) {
                            u_tleft_line10.setBackgroundResource(R.drawable.underleftanim1);
                            utlani10 = (AnimationDrawable) u_tleft_line10.getBackground();
                            utlani10.start();
                        } if (countFin==3) {
                            if (utlani10!=null) utlani10.stop();
                            u_tleft_txt1.setText("3 left");
                            u_tleft_txt1.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 4) {
                            u_tleft_line11.setBackgroundResource(R.drawable.underleftmiddle1);
                            utlani11 = (AnimationDrawable) u_tleft_line11.getBackground();
                            utlani11.start();
                        } if (countFin==4) {
                            if (utlani11!=null) utlani11.stop();
                            u_tleft_txt1.setText("2 left");
                            u_tright_txt2.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 5) {
                            u_tleft_line12.setBackgroundResource(R.drawable.underleftanim1);
                            utlani12 = (AnimationDrawable) u_tleft_line12.getBackground();
                            utlani12.start();
                        } if (countFin==5) {
                            if (utlani12!=null) utlani12.stop();
                            u_tleft_txt1.setText("1 left");

                        }
                        if (countStart == 6) {
                            u_tleft_line13.setBackgroundResource(R.drawable.underleftanim1);
                            utlani13 = (AnimationDrawable) u_tleft_line13.getBackground();
                            utlani13.start();
                        } if (countFin==6) {
                            if (utlani13!=null) utlani13.stop();
                            u_tleft_txt1.setText("DONE");
                        }
                        if (countStart == 7) {
                            String str ="THIS COLUMN HAS 7 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
                            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
                            ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 72, 126, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            component_txt.setText(ssb);
                            u_tleft_line1.setBackgroundResource(R.drawable.underleftanim2);
                            utlani1 = (AnimationDrawable) u_tleft_line1.getBackground();
                            utlani1.start();
                        } if (countFin==7) {
                            if (utlani1!=null) utlani1.stop();
                            u_tleft_txt2.setText("6 left");
                        }
                        if (countStart == 8) {
                            u_tleft_line2.setBackgroundResource(R.drawable.underleftanim2);
                            utlani2 = (AnimationDrawable) u_tleft_line2.getBackground();
                            utlani2.start();
                        } if (countFin==8) {
                            if (utlani2!=null) utlani2.stop();
                            u_tleft_txt2.setText("5 left");
                        }
                        if (countStart == 9) {
                            u_tleft_line3.setBackgroundResource(R.drawable.underleftanim2);
                            utlani3 = (AnimationDrawable) u_tleft_line3.getBackground();
                            utlani3.start();
                        } if (countFin==9) {
                            if (utlani3!=null) utlani3.stop();
                            u_tleft_txt2.setText("4 left");
                            u_tleft_txt2.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 10) {
                            u_tleft_line4.setBackgroundResource(R.drawable.underleftmiddle2);
                            utlani4 = (AnimationDrawable) u_tleft_line4.getBackground();
                            utlani4.start();
                        } if (countFin==10) {
                            if (utlani4!=null) utlani4.stop();
                            u_tleft_txt2.setText("3 left");
                            u_tleft_txt2.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 11) {
                            u_tleft_line5.setBackgroundResource(R.drawable.underleftanim2);
                            utlani5 = (AnimationDrawable) u_tleft_line5.getBackground();
                            utlani5.start();
                        } if (countFin==11) {
                            if (utlani5!=null) utlani5.stop();
                            u_tleft_txt2.setText("2 left");
                        }
                        if (countStart == 12) {
                            u_tleft_line6.setBackgroundResource(R.drawable.underleftanim2);
                            utlani6 = (AnimationDrawable) u_tleft_line6.getBackground();
                            utlani6.start();
                        } if (countFin==12) {
                            if (utlani6!=null) utlani6.stop();
                            u_tleft_txt2.setText("1 left");
                        }
                        if (countStart == 13) {
                            u_tleft_line7.setBackgroundResource(R.drawable.underleftanim2);
                            utlani7 = (AnimationDrawable) u_tleft_line7.getBackground();
                            utlani7.start();
                        } if (countFin==13) {
                            if (utlani7!=null) utlani7.stop();
                            u_tleft_txt2.setText("DONE");
                            u_tleft_line7.setBackgroundResource(R.drawable.line5finish);
                            data=25;
                            underleftstring="true";

                            //
                            second.cancel();

                            getDataTreat();
                            //GetData task = new GetData();
                            //task.execute("http://"+IP_Address+"/callingTreathome.php", "");

                            // 여기서 고쵸

                            SharedPreferences sp_treat = getSharedPreferences("under_l", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp_treat.edit();
                            editor1.putInt("under_l", 0);
                            editor1.commit();

                            Log.e("underleft", "save");
                            if (! TreatActivity_underleft2.this.isFinishing()) {
                                Intent intent = new Intent(getApplicationContext(),DoneActivity.class);
                                intent.putExtra("stringlist","underleft");
                                startActivity(intent);
                            }
                        }
                        setBack(countFin);
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, 0, 1000);
    }

    private void setBack(int count) {
        switch (count) {
            case 13:
                component_txt.setText("GOOD JOB");
                u_tleft_txt2.setTextColor(Color.parseColor("#9E0958"));
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 12:
                u_tleft_line6.setBackgroundResource(R.drawable.line5finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 11:
                u_tleft_line5.setBackgroundResource(R.drawable.line5finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 10:
                u_tleft_line4.setBackgroundResource(R.drawable.linemf5);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 9:
                u_tleft_line3.setBackgroundResource(R.drawable.line5finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 8:
                u_tleft_line2.setBackgroundResource(R.drawable.line5finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 7:
                u_tleft_line1.setBackgroundResource(R.drawable.line5finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 6:
                u_tleft_line13.setBackgroundResource(R.drawable.line9finish);
                u_tleft_txt1.setTextColor(Color.parseColor("#9E0958"));
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 5:
                u_tleft_line12.setBackgroundResource(R.drawable.line9finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 4:
                u_tleft_line11.setBackgroundResource(R.drawable.linemf9);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 3:
                u_tleft_line10.setBackgroundResource(R.drawable.line9finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 2:
                u_tleft_line9.setBackgroundResource(R.drawable.line9finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
            case 1:
                u_tleft_line8.setBackgroundResource(R.drawable.line9finish);
                //Log.e("switch countFin ==", String.valueOf(countFin));
        }
    }

    private void getDataTreat() {
        SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        String tDate = treaat_date.getString("tDate", "tDate=none");
        treat = treat_zone.getString("tZone", "");
        //Log.e("treaat_date", tDate);
        //Log.e("treat_zone", treat);

        setDataTreat();
    }

    private void setDataTreat() {
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = treat_zone.edit();
        editor2.putString("tZone", treat+"/under_l");
        editor2.commit();

        //Log.e("안희 좀...", treat+"/under_l");

        setDataTreat tasks1 = new setDataTreat();
        tasks1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", treat+"/under_l");

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"/under_l");
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

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);

            //Log.e("쉬발",getResult);

            if (getResult.contains("No_results")) {
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "under_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", "under_l");
                editor1.commit();
                editor2.commit();

            } else {
                showResult(getResult);
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/updateTreat.php", treat+"/under_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", treat+"/under_l");
                editor1.commit();
                editor2.commit();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            //Log.e("으악!!!!!!!!!!!!!!!",date);

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

                //Log.e("databse",treat);
            } catch (JSONException e) {
                Log.e("treat-JSON", "showResult : "+e.getMessage());
            }
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_underleft2);
        underleftativity=TreatActivity_underleft2.this;

        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");

        Resources res = getResources();
        //값 받아오기
        content1 = findViewById(R.id.treatup_ul2);
        content2 = findViewById(R.id.treatdown_ul2);
        question = findViewById(R.id.question_ul2);

        /*u_tleft[0] = findViewById(R.id.u_tleft_line8);
        u_tleft[1] = findViewById(R.id.u_tleft_line9);
        u_tleft[2] = findViewById(R.id.u_tleft_line10);
        u_tleft[3] = findViewById(R.id.u_tleft_line11);
        u_tleft[4] = findViewById(R.id.u_tleft_line12);
        u_tleft[5] = findViewById(R.id.u_tleft_line13);
        u_tleft[6] = findViewById(R.id.u_tleft_line1);
        u_tleft[7] = findViewById(R.id.u_tleft_line2);
        u_tleft[8] = findViewById(R.id.u_tleft_line3);
        u_tleft[9] = findViewById(R.id.u_tleft_line4);
        u_tleft[10] = findViewById(R.id.u_tleft_line5);
        u_tleft[11] = findViewById(R.id.u_tleft_line6);
        u_tleft[12] = findViewById(R.id.u_tleft_line7);
        u_tleft[13] = findViewById(R.id.u_tleft_line8);*/


        u_tleft_line8 = findViewById(R.id.u_tleft_line8);
        u_tleft_line9 = findViewById(R.id.u_tleft_line9);
        u_tleft_line10 = findViewById(R.id.u_tleft_line10);
        u_tleft_line11 = findViewById(R.id.u_tleft_line11);
        u_tleft_line12 = findViewById(R.id.u_tleft_line12);
        u_tleft_line13 = findViewById(R.id.u_tleft_line13);
        u_tleft_line1 = findViewById(R.id.u_tleft_line1);
        u_tleft_line2 = findViewById(R.id.u_tleft_line2);
        u_tleft_line3 = findViewById(R.id.u_tleft_line3);
        u_tleft_line4 = findViewById(R.id.u_tleft_line4);
        u_tleft_line5 = findViewById(R.id.u_tleft_line5);
        u_tleft_line6 = findViewById(R.id.u_tleft_line6);
        u_tleft_line7 = findViewById(R.id.u_tleft_line7);
        u_tleft_line8 = findViewById(R.id.u_tleft_line8);

        u_tright_txt1=(TextView)findViewById(R.id.u_tright_txt1);
        u_tright_txt2=(TextView)findViewById(R.id.u_tright_txt2);
        u_tleft_txt1=(TextView)findViewById(R.id.u_tleft_txt1);
        u_tleft_txt2=(TextView)findViewById(R.id.u_tleft_txt2);
        c_tright_txt1=(TextView)findViewById(R.id.c_tright_txt1);
        c_tright_txt2=(TextView)findViewById(R.id.c_tright_txt2);
        c_tleft_txt1=(TextView)findViewById(R.id.c_tleft_txt1);
        c_tleft_txt2=(TextView)findViewById(R.id.c_tleft_txt2);
        component_txt=findViewById(R.id.componenttxt_ul);
        backgroundimg=findViewById(R.id.background);
        imageView2 = findViewById(R.id.imageView2);
        direction = findViewById(R.id.direction);
        wrinkle_txt = databaseReference.child("result").child("winkle");
        back=findViewById(R.id.backbutton);

        String str ="THIS COLUMN HAS 6 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
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
                    case R.id.question_ul2:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","underleft2");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","underleft2");
                        startActivity(intent);
                        break;
                }
            }
        };
        back.setOnClickListener(onClickListener);
        question.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);

        SharedPreferences sp_treat = getSharedPreferences("under_l", MODE_PRIVATE);
        int n = sp_treat.getInt("under_l", 0);
        //Log.e("아아아아앙시바ㅏ아ㅏ", String.valueOf(n));
        HomeActivity.countStart = n;
    }

    public void onResume() {
        super.onResume();


        if (HomeActivity.staticLevel!=null) direction.setText(HomeActivity.staticLevel);
        else direction.setText("DEVICE LEVEL : 0");
        SharedPreferences sp_treat = getSharedPreferences("under_l", MODE_PRIVATE);
        countFin = sp_treat.getInt("under_l", 0);
        setBack(countFin);

        imageView2.setClickable(true);
        question.setClickable(true);

        // 베터리
        if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
            imageView2.setImageResource(R.drawable.bdev);
        else imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
        if (HomeActivity.deviceBattery==-1)
            imageView2.setImageResource(R.drawable.nondeviceicon);

        HomeActivity.setDeviceLevel();
        animation();
    }

    public void onPause() {
        super.onPause();
        second.cancel();

        SharedPreferences sp_treat = getSharedPreferences("under_l", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp_treat.edit();
        editor1.putInt("under_l", countFin);
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
