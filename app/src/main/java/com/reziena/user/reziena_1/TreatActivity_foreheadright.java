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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TreatActivity_foreheadright extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinkle_txt;
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;
    String wrinkle_string;
    String underleftstring,underrightstring;
    public static Activity foreheadrightactivity;
    RenderScript rs;
    private long mLastClickTime = 0;
    Bitmap blurBitMap;
    private static Bitmap bitamp;
    ImageView content1, content2;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, backgroundimg;
    LinearLayout component;
    TextView component_txt,f_tright_txt1,f_tright_txt2,f_tright_txt3,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    RelativeLayout treatback, underleft_layout,treat_default;
    int undercount=0, data=0, level=0, timer_sec;
    public static int countStart=0, countFin=0;
    ImageView f_tright_line1,f_tright_line2,f_tright_line3,f_tright_line4,f_tright_line5,f_tright_line6,
            f_tright_line7,f_tright_line8,f_tright_line9,f_tright_line10,f_tright_line11,f_tright_line12,f_tright_line13,f_tright_line14,
            f_tright_line15,f_tright_line16,f_tright_line17,f_tright_line18,f_tright_line19,question;
    TimerTask second;
    String part;
    AnimationDrawable ftanim1,ftanim2,ftanim3,ftanim4,ftanim5,ftanim6,ftanim7,ftanim8,ftanim9,ftanim10,ftanim11,ftanim12,ftanim13,ftanim14,ftanim15,
            ftanim16,ftanim17,ftanim18,ftanim19;
    static String finish;
    public static String IP_Address = "52.32.36.182";
    String treat;
    LinearLayout back;

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
                Log.e("카운터",String.valueOf(countStart));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (countStart == 1) {
                            f_tright_line1.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim1 = (AnimationDrawable) f_tright_line1.getBackground();
                            ftanim1.start();
                        } if (countFin == 1) {
                            if (ftanim1!=null) ftanim1.stop();
                            f_tright_txt1.setText("7 left");
                        }
                        if (countStart == 2) {
                            f_tright_line2.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim2 = (AnimationDrawable) f_tright_line2.getBackground();
                            ftanim2.start();
                        } if (countFin == 2) {
                            if (ftanim2!=null) ftanim2.stop();
                            f_tright_txt1.setText("6 left");
                        }
                        if (countStart == 3) {
                            f_tright_line3.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim3 = (AnimationDrawable) f_tright_line3.getBackground();
                            ftanim3.start();
                        } if (countFin == 3) {
                            if (ftanim3!=null) ftanim3.stop();
                            f_tright_txt1.setText("5 left");
                        }
                        if (countStart == 4) {
                            f_tright_line4.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim4 = (AnimationDrawable) f_tright_line4.getBackground();
                            ftanim4.start();
                        } if (countFin == 4) {
                            if (ftanim4!=null) ftanim4.stop();
                            f_tright_txt1.setText("4 left");
                            f_tright_txt1.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 5) {
                            f_tright_line5.setBackgroundResource(R.drawable.forerightanim2);
                            ftanim5 = (AnimationDrawable) f_tright_line5.getBackground();
                            ftanim5.start();
                        } if (countFin == 5) {
                            if (ftanim5!=null) ftanim5.stop();
                            f_tright_txt1.setText("3 left");
                            f_tright_txt1.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 6) {
                            f_tright_line6.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim6 = (AnimationDrawable) f_tright_line6.getBackground();
                            ftanim6.start();
                        } if (countFin == 6) {
                            if (ftanim6!=null) ftanim6.stop();
                            f_tright_txt1.setText("2 left");
                        }
                        if (countStart == 7) {
                            f_tright_line7.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim7 = (AnimationDrawable) f_tright_line7.getBackground();
                            ftanim7.start();
                        } if (countFin == 7) {
                            if (ftanim7!=null) ftanim7.stop();
                            f_tright_txt1.setText("1 left");
                        }
                        if (countStart == 8) {
                            f_tright_line8.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim8 = (AnimationDrawable) f_tright_line8.getBackground();
                            ftanim8.start();
                        } if (countFin == 8) {
                            if (ftanim8!=null) ftanim8.stop();
                            f_tright_txt1.setText("DONE");
                            f_tright_txt1.setTextColor(Color.parseColor("#9E0958"));
                        }
                        if (countStart == 9) {
                            f_tright_line9.setBackgroundResource(R.drawable.forerightanim3);
                            ftanim9 = (AnimationDrawable) f_tright_line9.getBackground();
                            ftanim9.start();
                        } if (countFin == 9) {
                            if (ftanim9!=null) ftanim9.stop();
                            f_tright_txt2.setText("6 left");
                        }
                        if (countStart == 10) {
                            f_tright_line10.setBackgroundResource(R.drawable.forerightanim4);
                            ftanim10 = (AnimationDrawable) f_tright_line10.getBackground();
                            ftanim10.start();
                        } if (countFin == 10) {
                            if (ftanim10!=null) ftanim10.stop();
                            f_tright_txt2.setText("5 left");
                        }
                        if (countStart == 11) {
                            f_tright_line11.setBackgroundResource(R.drawable.forerightanim5);
                            ftanim11 = (AnimationDrawable) f_tright_line11.getBackground();
                            ftanim11.start();
                        } if (countFin == 11) {
                            if (ftanim11!=null) ftanim11.stop();
                            f_tright_txt2.setText("4 left");
                            f_tright_txt2.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 12) {
                            f_tright_line12.setBackgroundResource(R.drawable.forerightanim6);
                            ftanim12 = (AnimationDrawable) f_tright_line12.getBackground();
                            ftanim12.start();
                        } if (countFin == 12) {
                            if (ftanim12!=null) ftanim12.stop();
                            f_tright_txt2.setText("3 left");
                            f_tright_txt2.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 13) {
                            f_tright_line13.setBackgroundResource(R.drawable.forerightanim7);
                            ftanim13 = (AnimationDrawable) f_tright_line13.getBackground();
                            ftanim13.start();
                        } if (countFin == 13) {
                            if (ftanim13!=null) ftanim13.stop();
                            f_tright_txt2.setText("2 left");
                        }
                        if (countStart == 14) {
                            f_tright_line14.setBackgroundResource(R.drawable.forerightanim8);
                            ftanim14 = (AnimationDrawable) f_tright_line14.getBackground();
                            ftanim14.start();
                        } if (countFin == 14) {
                            if (ftanim14!=null) ftanim14.stop();
                            f_tright_txt2.setText("1 left");
                        }
                        if (countStart == 15) {
                            f_tright_line15.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim15 = (AnimationDrawable) f_tright_line15.getBackground();
                            ftanim15.start();
                        } if (countFin == 15) {
                            if (ftanim15!=null) ftanim15.stop();
                            f_tright_txt2.setText("DONE");
                            f_tright_txt2.setTextColor(Color.parseColor("#9E0958"));
                        }
                        if (countStart == 16) {
                            f_tright_line16.setBackgroundResource(R.drawable.forerightanim9);
                            ftanim16 = (AnimationDrawable) f_tright_line16.getBackground();
                            ftanim16.start();
                        } if (countFin == 16) {
                            if (ftanim16!=null) ftanim16.stop();
                            f_tright_txt3.setText("3 left");
                        }
                        if (countStart == 17) {
                            f_tright_line17.setBackgroundResource(R.drawable.forerightanim10);
                            ftanim17 = (AnimationDrawable) f_tright_line17.getBackground();
                            ftanim17.start();
                        } if (countFin == 17) {
                            if (ftanim17!=null) ftanim17.stop();
                            f_tright_txt3.setText("2 left");
                            f_tright_txt3.setTextColor(Color.parseColor("#450969"));
                        }
                        if (countStart == 18) {
                            f_tright_line18.setBackgroundResource(R.drawable.forerightanim11);
                            ftanim18 = (AnimationDrawable) f_tright_line18.getBackground();
                            ftanim18.start();
                        } if (countFin == 18) {
                            if (ftanim18!=null) ftanim18.stop();
                            f_tright_txt3.setText("1 left");
                            f_tright_txt3.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        if (countStart == 19) {
                            f_tright_line19.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim19 = (AnimationDrawable) f_tright_line19.getBackground();
                            ftanim19.start();
                        } if (countFin == 19) {
                            if (ftanim19!=null) ftanim19.stop();
                            f_tright_txt3.setText("DONE");
                            f_tright_txt3.setTextColor(Color.parseColor("#9E0958"));

                            //Log.e("second.cacel", "시작하기 0.000001초 전");
                            second.cancel();
                             //Log.e("second.cacel", "끝!!!!!!!!!!!!!!!!!");

                            //
                            getDataTreat();
                            //GetData task = new GetData();
                            //task.execute("http://"+IP_Address+"/callingTreathome.php", "");

                            // 여기서 고쵸
                            SharedPreferences sp_treat = getSharedPreferences("fore_r", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp_treat.edit();
                            editor1.putInt("fore_r", 0);
                            editor1.commit();

                            //Log.e("fore_right", "save");
                            if (! TreatActivity_foreheadright.this.isFinishing()) {
                                Intent intent = new Intent(getApplicationContext(), TreatActivity_foreheadleft.class);
                                startActivity(intent);
                            }
                            //countFin=0;
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
            case 19:
                f_tright_line19.setBackgroundResource(R.drawable.frl1);
            case 18:
                f_tright_line18.setBackgroundResource(R.drawable.fll11);
            case 17:
                f_tright_line17.setBackgroundResource(R.drawable.fll10);
            case 16:
                f_tright_line16.setBackgroundResource(R.drawable.fll9);
            case 15:
                f_tright_line15.setBackgroundResource(R.drawable.frl1);
            case 14:
                f_tright_line14.setBackgroundResource(R.drawable.fll8);
            case 13:
                f_tright_line13.setBackgroundResource(R.drawable.fll7);
            case 12:
                f_tright_line12.setBackgroundResource(R.drawable.fll6);
            case 11:
                f_tright_line11.setBackgroundResource(R.drawable.fll5);
            case 10:
                f_tright_line10.setBackgroundResource(R.drawable.fll4);
            case 9:
                f_tright_line9.setBackgroundResource(R.drawable.fll3);
            case 8:
                f_tright_line8.setBackgroundResource(R.drawable.frl1);
            case 7:
                f_tright_line7.setBackgroundResource(R.drawable.frl1);
            case 6:
                f_tright_line6.setBackgroundResource(R.drawable.frl1);
            case 5:
                f_tright_line5.setBackgroundResource(R.drawable.frl21);
            case 4:
                f_tright_line4.setBackgroundResource(R.drawable.frl1);
            case 3:
                f_tright_line3.setBackgroundResource(R.drawable.frl1);
            case 2:
                f_tright_line2.setBackgroundResource(R.drawable.frl1);
            case 1:
                f_tright_line1.setBackgroundResource(R.drawable.frl1);
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
        editor2.putString("tZone", treat+"/fore_r");
        editor2.commit();

        //Log.e("안희 좀...", treat+"/fore_l");

        setDataTreat tasks1 = new setDataTreat();
        tasks1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", treat+"/fore_r");

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"/fore_r");
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
                   // Log.e("setDataTreat", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "fore_r");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", "fore_r");
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
                editor2.putString("tZone", treat+"/fore_r");
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
        setContentView(R.layout.activity_treat_foreright2);
        foreheadrightactivity=TreatActivity_foreheadright.this;

        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");

        Resources res = getResources();

        //값 받아오기
        f_tright_txt1 = findViewById(R.id.f_tright_txt1);
        f_tright_txt2 = findViewById(R.id.f_tright_txt2);
        f_tright_txt3 = findViewById(R.id.f_tright_txt3);
        question=findViewById(R.id.question_fr);
        f_tright_line1=(ImageView)findViewById(R.id.f_tright_line1);
        f_tright_line2=(ImageView)findViewById(R.id.f_tright_line2);
        f_tright_line3=(ImageView)findViewById(R.id.f_tright_line3);
        f_tright_line4=(ImageView)findViewById(R.id.f_tright_line4);
        f_tright_line5=(ImageView)findViewById(R.id.f_tright_line5);
        f_tright_line6=(ImageView)findViewById(R.id.f_tright_line6);
        f_tright_line7=(ImageView)findViewById(R.id.f_tright_line7);
        f_tright_line8=(ImageView)findViewById(R.id.f_tright_line8);
        f_tright_line9=(ImageView)findViewById(R.id.f_tright_line9);
        f_tright_line10=(ImageView)findViewById(R.id.f_tright_line10);
        f_tright_line11=(ImageView)findViewById(R.id.f_tright_line11);
        f_tright_line12=(ImageView)findViewById(R.id.f_tright_line12);
        f_tright_line13=(ImageView)findViewById(R.id.f_tright_line13);
        f_tright_line14=(ImageView)findViewById(R.id.f_tright_line14);
        f_tright_line15=(ImageView)findViewById(R.id.f_tright_line15);
        f_tright_line16=(ImageView)findViewById(R.id.f_tright_line16);
        f_tright_line17=(ImageView)findViewById(R.id.f_tright_line17);
        f_tright_line18=(ImageView)findViewById(R.id.f_tright_line18);
        f_tright_line19=(ImageView)findViewById(R.id.f_tright_line19);
        component_txt=findViewById(R.id.componenttxt_fr);
        backgroundimg=findViewById(R.id.background);
        wrinkle_txt = databaseReference.child("result").child("winkle");
        imageView2 = findViewById(R.id.imageView2);
        back=findViewById(R.id.backbutton);
        direction = findViewById(R.id.direction);

        String str ="THIS COLUMN HAS 19 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 73, 127, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        component_txt.setText(ssb);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.backbutton:
                        finish();
                        break;
                    case R.id.question_fr:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","foreheadright");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","foreheadright");
                        startActivity(intent);
                        break;
                }
            }
        };
        back.setOnClickListener(onClickListener);
        question.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);

        SharedPreferences sp_treat = getSharedPreferences("fore_r", MODE_PRIVATE);
        int n = sp_treat.getInt("fore_r", 0);
        //Log.e("아아아아앙시바ㅏ아ㅏ", String.valueOf(n));
        HomeActivity.countStart = n;
    }

    public void onResume() {
        super.onResume();

        direction.setText(HomeActivity.staticLevel);
        SharedPreferences sp_treat = getSharedPreferences("fore_r", MODE_PRIVATE);
        countFin = sp_treat.getInt("fore_r", 0);
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

        SharedPreferences sp_treat = getSharedPreferences("fore_r", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp_treat.edit();
        editor1.putInt("fore_r", countFin);
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