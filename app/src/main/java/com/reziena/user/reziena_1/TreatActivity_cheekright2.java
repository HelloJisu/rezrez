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

public class TreatActivity_cheekright2 extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinkle_txt;
    RenderScript rs;
    private long mLastClickTime = 0;
    Bitmap blurBitMap;
    private static Bitmap bitamp;
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;
    String wrinkle_string;
    String cheekrightstring,cheekleftstring;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, backgroundimg, question;
    LinearLayout back;
    LinearLayout component;
    TextView component_txt,u_tright_txt1,u_tright_txt2,u_tleft_txt1,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    int cheekcount=0, data=0, level=0, timer_sec;
    public static int countStart=0, countFin=0;
    ImageView c_tright_line1,c_tright_line2,c_tright_line3,c_tright_line4,c_tright_line5,c_tright_line6,c_tright_line7,c_tright_line8
            ,c_tright_line9,c_tright_line10,c_tright_line11,c_tright_line12,c_tright_line13,c_tright_line14,c_tright_line15,c_tright_line16,c_tright_line17
            ,c_tright_line18,c_tright_line19,c_tright_line20,c_tright_line21,c_tright_line22;
    TimerTask second;
    String part;
    AnimationDrawable ctrani1,ctrani2,ctrani3,ctrani4,ctrani5,ctrani6,ctrani7,ctrani8,ctrani9,ctrani10,ctrani11,ctrani12,ctrani13,ctrani14,ctrani15
            ,ctrani16,ctrani17,ctrani18,ctrani19,ctrani20,ctrani21,ctrani22;
    static String finish;
    public static Activity cheekrightactivity;
    ImageView content1, content2;
    public static String IP_Address = "52.32.36.182";
    String treat;
    public static Thread t;
    public static ImageView imageView2;
    public static TextView direction;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
    Date currentTime = new Date();
    String date = mSimpleDateFormat.format ( currentTime );

    public static void intentpage(String string) {
        finish=string;
    }

    public void animation() {
        second = new TimerTask() {
            @Override
            public void run() {
                //count++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (countStart == 15) {
                            c_tright_line15.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani15 = (AnimationDrawable) c_tright_line15.getBackground();
                            ctrani15.start();
                        } if (countFin == 15) {
                            if (ctrani15!=null) ctrani15.stop();
                            c_tright_txt1.setText("7 left");
                        }
                        if (countStart == 16) {
                            c_tright_line16.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani16 = (AnimationDrawable) c_tright_line16.getBackground();
                            ctrani16.start();
                        } if (countFin == 16) {
                            if (ctrani16!=null) ctrani16.stop();
                            c_tright_txt1.setText("6 left");
                        }
                        if (countStart == 17) {
                            c_tright_line17.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani17 = (AnimationDrawable) c_tright_line17.getBackground();
                            ctrani17.start();
                        } if (countFin == 17) {
                            if (ctrani17!=null) ctrani17.stop();
                            c_tright_txt1.setText("5 left");
                        }
                        if (countStart == 18) {
                            c_tright_line18.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani18 = (AnimationDrawable) c_tright_line18.getBackground();
                            ctrani18.start();
                        } if (countFin == 18) {
                            if (ctrani18!=null) ctrani18.stop();
                            c_tright_txt1.setText("4 left");
                            c_tright_txt1.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 19) {
                            c_tright_line19.setBackgroundResource(R.drawable.cheekrightmiddle1);
                            ctrani19 = (AnimationDrawable) c_tright_line19.getBackground();
                            ctrani19.start();
                        } if (countFin == 19) {
                            if (ctrani19!=null) ctrani19.stop();
                            c_tright_txt1.setText("3 left");
                            c_tright_txt1.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 20) {
                            c_tright_line20.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani20 = (AnimationDrawable) c_tright_line20.getBackground();
                            ctrani20.start();
                        } if (countFin == 20) {
                            if (ctrani20!=null) ctrani20.stop();
                            c_tright_txt1.setText("2 left");
                        }
                        if (countStart == 21) {
                            c_tright_line21.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani21 = (AnimationDrawable) c_tright_line21.getBackground();
                            ctrani21.start();
                        } if (countFin == 21) {
                            if (ctrani21!=null) ctrani21.stop();
                            c_tright_txt1.setText("1 left");
                        }
                        if (countStart == 22) {
                            c_tright_line22.setBackgroundResource(R.drawable.cheekrightanim1);
                            ctrani22 = (AnimationDrawable) c_tright_line22.getBackground();
                            ctrani22.start();
                        } if (countFin == 22) {
                            if (ctrani22!=null) ctrani22.stop();
                            c_tright_txt1.setText("DONE");
                            c_tright_txt1.setTextColor(Color.parseColor("#9E0958"));
                            data = 25;
                            cheekrightstring = "true";

                            second.cancel();
                            //
                            getDataTreat();
                            //GetData task = new GetData();
                            //task.execute("http://" + IP_Address + "/callingTreathome.php", "");

                            // 여기서 고쵸
                            SharedPreferences sp_treat = getSharedPreferences("cheek_r", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp_treat.edit();
                            editor1.putInt("cheek_r", 0);
                            editor1.commit();

                            if (!TreatActivity_cheekright2.this.isFinishing()) {
                                Intent intent = new Intent(getApplicationContext(), DoneActivity.class);
                                intent.putExtra("stringlist", "cheekright");
                                startActivity(intent);
                            }
                        }
                        if (countStart == 1) {
                            String str ="THIS COLUMN HAS 14 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
                            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
                            ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 73, 127, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            component_txt.setText(ssb);
                            c_tright_line1.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani1 = (AnimationDrawable) c_tright_line1.getBackground();
                            ctrani1.start();
                        } if (countFin == 1) {
                            if (ctrani1!=null) ctrani1.stop();
                            c_tright_txt2.setText("13 left");
                        }
                        if (countStart == 2) {
                            c_tright_line2.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani2 = (AnimationDrawable) c_tright_line2.getBackground();
                            ctrani2.start();
                        } if (countFin == 2) {
                            if (ctrani2!=null) ctrani2.stop();
                            c_tright_txt2.setText("12 left");
                        }
                        if (countStart == 3) {
                            c_tright_line3.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani3 = (AnimationDrawable) c_tright_line3.getBackground();
                            ctrani3.start();
                        } if (countFin == 3) {
                            if (ctrani3!=null) ctrani3.stop();
                            c_tright_txt2.setText("11 left");
                        }
                        if (countStart == 4) {
                            c_tright_line4.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani4 = (AnimationDrawable) c_tright_line4.getBackground();
                            ctrani4.start();
                        } if (countFin == 4) {
                            if (ctrani4!=null) ctrani4.stop();
                            c_tright_txt2.setText("10 left");
                        }
                        if (countStart == 5) {
                            c_tright_line5.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani5 = (AnimationDrawable) c_tright_line5.getBackground();
                            ctrani5.start();
                        } if (countFin == 5) {
                            if (ctrani5!=null) ctrani5.stop();
                            c_tright_txt2.setText("9 left");
                        }
                        if (countStart == 6) {
                            c_tright_line6.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani6 = (AnimationDrawable) c_tright_line6.getBackground();
                            ctrani6.start();
                        } if (countFin == 6) {
                            if (ctrani6!=null) ctrani6.stop();
                            c_tright_txt2.setText("8 left");
                        }
                        if (countStart == 7) {
                            c_tright_line7.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani7 = (AnimationDrawable) c_tright_line7.getBackground();
                            ctrani7.start();
                        } if (countFin == 7) {
                            if (ctrani7!=null) ctrani7.stop();
                            c_tright_txt2.setText("7 left");
                            c_tright_txt2.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 8) {
                            c_tright_line8.setBackgroundResource(R.drawable.cheekrightmiddle2);
                            ctrani8 = (AnimationDrawable) c_tright_line8.getBackground();
                            ctrani8.start();
                        } if (countFin == 8) {
                            if (ctrani8!=null) ctrani8.stop();
                            c_tright_txt2.setText("6 left");
                            c_tright_txt2.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 9) {
                            c_tright_line9.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani9 = (AnimationDrawable) c_tright_line9.getBackground();
                            ctrani9.start();
                        } if (countFin == 9) {
                            if (ctrani9!=null) ctrani9.stop();
                            c_tright_txt2.setText("5 left");
                        }
                        if (countStart == 10) {
                            c_tright_line10.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani10 = (AnimationDrawable) c_tright_line10.getBackground();
                            ctrani10.start();
                        } if (countFin == 10) {
                            if (ctrani10!=null) ctrani10.stop();
                            c_tright_txt2.setText("4 left");
                        }
                        if (countStart == 11) {
                            c_tright_line11.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani11 = (AnimationDrawable) c_tright_line11.getBackground();
                            ctrani11.start();
                        } if (countFin == 11) {
                            if (ctrani11!=null) ctrani11.stop();
                            c_tright_txt2.setText("3 left");
                        }
                        if (countStart == 12) {
                            c_tright_line12.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani12 = (AnimationDrawable) c_tright_line12.getBackground();
                            ctrani12.start();
                        } if (countFin == 12) {
                            if (ctrani12!=null) ctrani12.stop();
                            c_tright_txt2.setText("2 left");
                        }
                        if (countStart == 13) {
                            c_tright_line13.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani13 = (AnimationDrawable) c_tright_line13.getBackground();
                            ctrani13.start();
                        } if (countFin == 13) {
                            if (ctrani13!=null) ctrani13.stop();
                            c_tright_txt2.setText("1 left");
                        }
                        if (countStart == 14) {
                            c_tright_line14.setBackgroundResource(R.drawable.cheekrightanim2);
                            ctrani14 = (AnimationDrawable) c_tright_line14.getBackground();
                            ctrani14.start();
                        } if (countFin == 14) {
                            if (ctrani14!=null) ctrani14.stop();
                            component_txt.setText("GOOD JOB");
                            c_tright_txt2.setText("DONE");
                            c_tright_txt2.setTextColor(Color.parseColor("#9E0958"));
                        }

                        setBack(countFin);

                    }   // end of run
                }); // end of runOnUiThread
            } // end of run
        };  // second timerTask
        Timer timer = new Timer();
        timer.schedule(second, 0, 100);
    }

    private void setBack(int count) {
        switch (count) {
            case 22:
                c_tright_line15.setBackgroundResource(R.drawable.line123finish);
            case 21:
                c_tright_line16.setBackgroundResource(R.drawable.line123finish);
            case 20:
                c_tright_line17.setBackgroundResource(R.drawable.line123finish);
            case 19:
                c_tright_line18.setBackgroundResource(R.drawable.line123finish);
            case 18:
                c_tright_line19.setBackgroundResource(R.drawable.linemf123);
            case 17:
                c_tright_line20.setBackgroundResource(R.drawable.line123finish);
            case 16:
                c_tright_line21.setBackgroundResource(R.drawable.line123finish);
            case 15:
                c_tright_line22.setBackgroundResource(R.drawable.line123finish);
            case 14:
                c_tright_line14.setBackgroundResource(R.drawable.line1finish);
            case 13:
                c_tright_line13.setBackgroundResource(R.drawable.line1finish);
            case 12:
                c_tright_line12.setBackgroundResource(R.drawable.line1finish);
            case 11:
                c_tright_line11.setBackgroundResource(R.drawable.line1finish);
            case 10:
                c_tright_line10.setBackgroundResource(R.drawable.line1finish);
            case 9:
                c_tright_line9.setBackgroundResource(R.drawable.line1finish);
            case 8:
                c_tright_line8.setBackgroundResource(R.drawable.linemf1);
            case 7:
                c_tright_line7.setBackgroundResource(R.drawable.line1finish);
            case 6:
                c_tright_line6.setBackgroundResource(R.drawable.line1finish);
            case 5:
                c_tright_line5.setBackgroundResource(R.drawable.line1finish);
            case 4:
                c_tright_line4.setBackgroundResource(R.drawable.line1finish);
            case 3:
                c_tright_line3.setBackgroundResource(R.drawable.line1finish);
            case 2:
                c_tright_line2.setBackgroundResource(R.drawable.line1finish);
            case 1:
                c_tright_line1.setBackgroundResource(R.drawable.line1finish);
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
        editor2.putString("tZone", treat+"/cheek_r");
        editor2.commit();

        //Log.e("안희 좀...", treat+"/cheek_r");

        setDataTreat tasks1 = new setDataTreat();
        tasks1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", treat+"/cheek_r");

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"/cheek_r");
    }

    class setDataTreat extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);
            Log.e("setDataTreat",getResult);
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
                //Log.e("setDataTreat", e.getMessage());
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

    class GetData extends AsyncTask<String, Void, String> {
        List<String[]> treatArray = new ArrayList<>();

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);

            Log.e("쉬발",getResult);

            if (getResult.contains("No_results")) {
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "cheek_r");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", "cheek_r");
                editor1.commit();
                editor2.commit();

            } else {
                showResult(getResult);
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/updateTreat.php", treat+"/cheek_r");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", treat+"/cheek_r");
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

    public void onStart() {
        super.onStart();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_cheekright2);

        cheekrightdata = databaseReference.child("result").child("cheekrightstring");
        cheekleftdata = databaseReference.child("result").child("cheekleftstring");
        cheekrightactivity = TreatActivity_cheekright2.this;

        Resources res = getResources();
        //값 받아오기
        question = findViewById(R.id.question_cr2);
        content1 = findViewById(R.id.treatup_cr2);
        content2 = findViewById(R.id.treatdown_cr2);
        c_tright_line1=(ImageView)findViewById(R.id.c_tright_line1);
        c_tright_line2=(ImageView)findViewById(R.id.c_tright_line2);
        c_tright_line3=(ImageView)findViewById(R.id.c_tright_line3);
        c_tright_line4=(ImageView)findViewById(R.id.c_tright_line4);
        c_tright_line5=(ImageView)findViewById(R.id.c_tright_line5);
        c_tright_line6=(ImageView)findViewById(R.id.c_tright_line6);
        c_tright_line7=(ImageView)findViewById(R.id.c_tright_line7);
        c_tright_line8=(ImageView)findViewById(R.id.c_tright_line8);
        c_tright_line9=(ImageView)findViewById(R.id.c_tright_line9);
        c_tright_line10=(ImageView)findViewById(R.id.c_tright_line10);
        c_tright_line11=(ImageView)findViewById(R.id.c_tright_line11);
        c_tright_line12=(ImageView)findViewById(R.id.c_tright_line12);
        c_tright_line13=(ImageView)findViewById(R.id.c_tright_line13);
        c_tright_line14=(ImageView)findViewById(R.id.c_tright_line14);
        c_tright_line15=(ImageView)findViewById(R.id.c_tright_line15);
        c_tright_line16=(ImageView)findViewById(R.id.c_tright_line16);
        c_tright_line17=(ImageView)findViewById(R.id.c_tright_line17);
        c_tright_line18=(ImageView)findViewById(R.id.c_tright_line18);
        c_tright_line19=(ImageView)findViewById(R.id.c_tright_line19);
        c_tright_line20=(ImageView)findViewById(R.id.c_tright_line20);
        c_tright_line21=(ImageView)findViewById(R.id.c_tright_line21);
        c_tright_line22=(ImageView)findViewById(R.id.c_tright_line22);
        u_tleft_txt1=(TextView)findViewById(R.id.u_tleft_txt1);
        u_tleft_txt2=(TextView)findViewById(R.id.u_tleft_txt2);
        c_tright_txt1=(TextView)findViewById(R.id.c_tright_txt1);
        c_tright_txt2=(TextView)findViewById(R.id.c_tright_txt2);
        c_tleft_txt1=(TextView)findViewById(R.id.c_tleft_txt1);
        c_tleft_txt2=(TextView)findViewById(R.id.c_tleft_txt2);
        component_txt=findViewById(R.id.componenttxt_cr);
        backgroundimg=findViewById(R.id.background);
        imageView2 = findViewById(R.id.imageView2);
        back=findViewById(R.id.backbutton);
        direction = findViewById(R.id.direction);

        String str ="THIS COLUMN HAS 9 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 72, 126, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        component_txt.setText(ssb);

        wrinkle_txt = databaseReference.child("result").child("winkle");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.backbutton:
                        finish();
                        break;
                    case R.id.question_cr2:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","cheekright2");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","cheekright2");
                        startActivity(intent);
                        break;
                }
            }
        };
        back.setOnClickListener(onClickListener);
        question.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);

        SharedPreferences sp_treat = getSharedPreferences("cheek_r", MODE_PRIVATE);
        int n = sp_treat.getInt("cheek_r", 0);
        //Log.e("아아아아앙시바ㅏ아ㅏ", String.valueOf(n));
        HomeActivity.countStart = n;
    }

    public void onResume() {
        super.onResume();


        if (HomeActivity.staticLevel!=null) direction.setText(HomeActivity.staticLevel);
        else direction.setText("DEVICE LEVEL : 0");
        SharedPreferences sp_treat = getSharedPreferences("cheek_r", MODE_PRIVATE);
        countFin = sp_treat.getInt("cheek_r", 0);
        setBack(countFin);

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

        SharedPreferences sp_treat = getSharedPreferences("cheek_r", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp_treat.edit();
        editor1.putInt("cheek_r", countFin);
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
