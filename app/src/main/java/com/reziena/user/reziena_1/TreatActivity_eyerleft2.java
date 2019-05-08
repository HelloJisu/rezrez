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

public class TreatActivity_eyerleft2 extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinkle_txt;
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;
    String wrinkle_string;
    String underleftstring,underrightstring;
    public static Activity eyeleftactivity;
    RenderScript rs;
    Bitmap blurBitMap;
    private long mLastClickTime = 0;
    private static Bitmap bitamp;
    ImageView content1, content2;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, backgroundimg, question;
    LinearLayout component;
    TextView component_txt,u_tright_txt1,u_tright_txt2,e_tright_txt,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    RelativeLayout treatback, underleft_layout,treat_default;
    int undercount=0, data=0, level=0, timer_sec;
    public static int countStart=0, countFin=0;
    ImageView e_tleft_line1,e_tleft_line2,e_tleft_line3,e_tleft_line4,e_tleft_line5,e_tleft_line6,
            e_tleft_line7;
    TimerTask second;
    LinearLayout back;
    String part;
    AnimationDrawable etanim1,etanim2,etanim3,etanim4,etanim5,etanim6,etanim7;
    static String finish;
    public static String IP_Address = "52.32.36.182";
    String treat;
    public static ImageView imageView2;
    public static TextView direction;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
    Date currentTime = new Date();
    String date = mSimpleDateFormat.format ( currentTime );

    public static void intentpage(String string) {
        finish=string;
    }

    public void animation() {
        //Log.e("underleft_init", "animation");
        second = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (countStart == 1) {
                        e_tleft_line1.setBackgroundResource(R.drawable.eyeleftanim1);
                        etanim1 = (AnimationDrawable) e_tleft_line1.getBackground();
                        etanim1.start();
                    } if (countFin == 1) {
                        if (etanim1!=null) etanim1.stop();
                        e_tright_txt.setText("6 left");
                    }
                    if (countStart == 2) {
                        e_tleft_line2.setBackgroundResource(R.drawable.eyeleftanim2);
                        etanim2 = (AnimationDrawable) e_tleft_line2.getBackground();
                        etanim2.start();
                    } if (countFin == 2) {
                        if (etanim2!=null) etanim2.stop();
                        e_tright_txt.setText("5 left");
                    }
                    if (countStart == 3) {
                        e_tleft_line3.setBackgroundResource(R.drawable.eyeleftanim3);
                        etanim3 = (AnimationDrawable) e_tleft_line3.getBackground();
                        etanim3.start();
                    } if (countFin == 3) {
                        if (etanim3!=null) etanim3.stop();
                        e_tright_txt.setText("4 left");
                        e_tright_txt.setTextColor(Color.parseColor("#450969"));
                    }
                    if (countStart == 4) {
                        e_tleft_line4.setBackgroundResource(R.drawable.eyeleftanim4);
                        etanim4 = (AnimationDrawable) e_tleft_line4.getBackground();
                        etanim4.start();
                    } if (countFin == 4) {
                        if (etanim4!=null) etanim4.stop();
                        e_tright_txt.setText("3 left");
                        e_tright_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    if (countStart == 5) {
                        e_tleft_line5.setBackgroundResource(R.drawable.eyeleftanim5);
                        etanim5 = (AnimationDrawable) e_tleft_line5.getBackground();
                        etanim5.start();
                    } if (countFin == 5) {
                        if (etanim5!=null) etanim5.stop();
                        e_tright_txt.setText("2 left");
                    }
                    if (countStart == 6) {
                        e_tleft_line6.setBackgroundResource(R.drawable.eyeleftanim6);
                        etanim6 = (AnimationDrawable) e_tleft_line6.getBackground();
                        etanim6.start();
                    } if (countFin == 6) {
                        if (etanim6!=null) etanim6.stop();
                        e_tright_txt.setText("1 left");
                    }
                    if (countStart == 7) {
                        e_tleft_line7.setBackgroundResource(R.drawable.eyeleftanim7);
                        etanim7 = (AnimationDrawable) e_tleft_line7.getBackground();
                        etanim7.start();
                    } if (countFin == 7) {
                        if (etanim7!=null) etanim7.stop();

                        second.cancel();
                        //
                        getDataTreat();
                        //GetData task = new GetData();
                        //task.execute("http://"+IP_Address+"/callingTreathome.php", "");

                        // 여기서 고쵸
                        SharedPreferences sp_treat = getSharedPreferences("eye_l", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sp_treat.edit();
                        editor1.putInt("eye_l", 0);
                        editor1.commit();

                        //Log.e("underleft", "save");
                        if (! TreatActivity_eyerleft2.this.isFinishing()) {
                            Intent intent = new Intent(getApplicationContext(),DoneActivity.class);
                            intent.putExtra("stringlist","eyeleft");
                            startActivity(intent);
                        }
                    }
                    setBack(countFin);
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, 0, 100);
    }

    private void setBack(int count) {
        switch (count) {
            case 7:
                e_tleft_line7.setBackgroundResource(R.drawable.elf7);
                e_tright_txt.setText("DONE");
                e_tright_txt.setTextColor(Color.parseColor("#9E0958"));
            case 6:
                e_tleft_line6.setBackgroundResource(R.drawable.elf6);
            case 5:
                e_tleft_line5.setBackgroundResource(R.drawable.elf5);
            case 4:
                e_tleft_line4.setBackgroundResource(R.drawable.elf4);
            case 3:
                e_tleft_line3.setBackgroundResource(R.drawable.elf3);
            case 2:
                e_tleft_line2.setBackgroundResource(R.drawable.elf2);
            case 1:
                e_tleft_line1.setBackgroundResource(R.drawable.elf1);
        }
    }

    private void getDataTreat() {
        SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        String tDate = treaat_date.getString("tDate", "tDate=none");
        treat = treat_zone.getString("tZone", "");
        //Log.e("treaat_date", "가져오기-세션");
        //Log.e("treaat_date", tDate);
        //Log.e("treat_zone", treat);

        setDataTreat();
    }

    private void setDataTreat() {
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = treat_zone.edit();
        editor2.putString("tZone", treat+"/eye_l");
        editor2.commit();

        //Log.e("안희 좀...", treat+"/eye_l");

        setDataTreat tasks1 = new setDataTreat();
        tasks1.execute("http://"+HomeActivity.IP_Address+"/callingTreat.php", treat+"/eye_l");

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", treat+"/eye_l");
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
                Log.e("setDataTreat", e.getMessage());
            }
            return null;

        }
    }

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);
            // Log.e("gfdesetrdf",getResult);
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

            Log.e("쉬발",getResult);

            if (getResult.contains("No_results")) {
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "eye_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", "eye_l");
                editor1.commit();
                editor2.commit();

            } else {
                showResult(getResult);
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/updateTreat.php", treat+"/eye_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", treat+"/eye_l");
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
        setContentView(R.layout.activity_treat_eyeleft2);
        eyeleftactivity=TreatActivity_eyerleft2.this;

        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");

        Resources res = getResources();
        //값 받아오기
        question = findViewById(R.id.question_el);
        e_tleft_line1=(ImageView)findViewById(R.id.e_tleft_line1);
        e_tleft_line2=(ImageView)findViewById(R.id.e_tleft_line2);
        e_tleft_line3=(ImageView)findViewById(R.id.e_tleft_line3);
        e_tleft_line4=(ImageView)findViewById(R.id.e_tleft_line4);
        e_tleft_line5=(ImageView)findViewById(R.id.e_tleft_line5);
        e_tleft_line6=(ImageView)findViewById(R.id.e_tleft_line6);
        e_tleft_line7=(ImageView)findViewById(R.id.e_tleft_line7);
        e_tright_txt=(TextView)findViewById(R.id.e_tleft_txt);
        component_txt=findViewById(R.id.componenttxt_el);
        backgroundimg=findViewById(R.id.background);
        wrinkle_txt = databaseReference.child("result").child("winkle");
        imageView2 = findViewById(R.id.imageView2);
        back=findViewById(R.id.backbutton);
        direction = findViewById(R.id.direction);

        String str ="THIS COLUMN HAS 7 LINES.\nPLACE THE DEVICE TO THE COLORED\nLINE AS SHOWN. AND PRESS THE CENTER\nBUTTON TO START TREATING ONE LINE";
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
                    case R.id.question_el:
                        question.setClickable(false);
                        intent = new Intent(getApplicationContext(), ExplainActivity.class);
                        intent.putExtra("key","eyeleft");
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.imageView2:
                        // BT
                        imageView2.setClickable(false);
                        intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                        intent.putExtra("key","eyeleft");
                        startActivity(intent);
                        break;
                }
            }
        };
        back.setOnClickListener(onClickListener);
        question.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);

        SharedPreferences sp_treat = getSharedPreferences("eye_l", MODE_PRIVATE);
        int n = sp_treat.getInt("eye_l", 0);
        //Log.e("아아아아앙시바ㅏ아ㅏ", String.valueOf(n));
        HomeActivity.countStart = n;
    }

    public void onResume() {
        super.onResume();


        if (HomeActivity.staticLevel!=null) direction.setText(HomeActivity.staticLevel);
        else direction.setText("DEVICE LEVEL : 0");
        SharedPreferences sp_treat = getSharedPreferences("eye_l", MODE_PRIVATE);
        countFin = sp_treat.getInt("eye_l", 0);
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

        SharedPreferences sp_treat = getSharedPreferences("eye_l", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp_treat.edit();
        editor1.putInt("eye_l", countFin);
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
