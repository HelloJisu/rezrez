package com.reziena.user.reziena_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
import java.util.Locale;

import at.grabner.circleprogress.CircleProgressView;

public class SkintypeActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    TextView next, pre, done, again, okay;
    SeekBar s1, s2, s3, s4;
    TextView q1, q2, q3, q4, skintypetxt;
    CircleProgressView mois_c,oiㅣ_c,res_c,elas_c,anti_c, mois_c2,oiㅣ_c2,res_c2,elas_c2,anti_c2;
    ArrayList<Integer> list = new ArrayList<>();
    LinearLayout imageButton, content3, content4;
    ImageView result1, result2, result3, result4;
    int page=1;
    int mois=0, oil=0, resis=0, elas=0, anti=0;
    String skin_type="";
    String detail="";
    LinearLayout content1, content2;
    int width;

    public SkintypeActivity() {
    }

    private void setPage() {
        if(page==5) {
            content1.setVisibility(View.INVISIBLE);
            content2.setVisibility(View.VISIBLE);
            content4.setVisibility(View.VISIBLE);
            content3.setVisibility(View.INVISIBLE);
            pre.setVisibility(View.INVISIBLE); done.setVisibility(View.INVISIBLE); next.setVisibility(View.INVISIBLE);
            again.setVisibility(View.VISIBLE);
            setResult_detail();
        }
        else {
            content1.setVisibility(View.VISIBLE);
            content3.setVisibility(View.VISIBLE);
            content2.setVisibility(View.INVISIBLE);
            content4.setVisibility(View.INVISIBLE);
            switch (page) {
                case 1:
                    q1.setText(R.string.q1);q2.setText(R.string.q2);q3.setText(R.string.q3);q4.setText(R.string.q4);
                    pre.setVisibility(View.INVISIBLE); done.setVisibility(View.INVISIBLE); next.setVisibility(View.VISIBLE); again.setVisibility(View.GONE);
                    break;
                case 2:
                    q1.setText(R.string.q5);q2.setText(R.string.q6);q3.setText(R.string.q7);q4.setText(R.string.q8);
                    pre.setVisibility(View.VISIBLE); next.setVisibility(View.VISIBLE); done.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    q1.setText(R.string.q9);q2.setText(R.string.q10);q3.setText(R.string.q11);q4.setText(R.string.q12);
                    pre.setVisibility(View.VISIBLE); next.setVisibility(View.VISIBLE); done.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    q1.setText(R.string.q13);q2.setText(R.string.q14);q3.setText(R.string.q15);q4.setText(R.string.q16);
                    pre.setVisibility(View.VISIBLE); done.setVisibility(View.VISIBLE); next.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }

    private void reset() {
        mois=0; oil=0; resis=0; elas=0; anti=0;
        for(int i=0; i<16; i++) {
            list.set(i, 0);
        }
        page=1; setPage(); setSeekbar(); skin_type=""; detail="";

    }

    private void setDataSkin() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String date = mSimpleDateFormat.format(currentTime);

        String skin = mois+"/"+oil+"/"+resis+"/"+elas+"/"+anti+"/"+date;

        SharedPreferences spSkin = getSharedPreferences("skin", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = spSkin.edit();

        editor1.putString("skin", skin);
        editor1.commit();
    }

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            //String result = params[1];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");

            String postParameters = "date="+date+"&id="+userID+"&mois="+mois+"&oil="+oil+"&resis="+resis+"&elas="+elas+"&anti="+anti;

            Log.e("skintype-postParameters", postParameters);

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

                // response
                int responseStatusCode = httpURLConnection.getResponseCode();
                String responseStatusMessage = httpURLConnection.getResponseMessage();
                Log.e("response-skintype", "POST response Code - " + responseStatusCode);
                Log.e("response-skintype", "POST response Message - "+ responseStatusMessage);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    // 정상적인 응답 데이터
                    inputStream = httpURLConnection.getInputStream();
                    Log.e("skintype-inputstream: ", "정상적");
                } else {
                    // error
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("skintype-inputstream: ", "비정상적: " + httpURLConnection.getErrorStream());
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line=bufferedReader.readLine()) != null) {
                    sb.append(line);
                    Log.e("read", String.valueOf(sb.append(line)));
                }

            } catch (Exception e) {
                Log.e("skintype-ERROR", "InsertDataError "+e.getMessage());
            }
            return null;
        }
    }

    private void setSeekbar() {
        switch (page) {
            case 1:
                s1.setProgress(list.get(0)); s2.setProgress(list.get(1)); s3.setProgress(list.get(2)); s4.setProgress(list.get(3));
                break;
            case 2:
                s1.setProgress(list.get(4)); s2.setProgress(list.get(5)); s3.setProgress(list.get(6)); s4.setProgress(list.get(7));
                break;
            case 3:
                s1.setProgress(list.get(8)); s2.setProgress(list.get(9)); s3.setProgress(list.get(10)); s4.setProgress(list.get(11));
                break;
            case 4:
                s1.setProgress(list.get(12)); s2.setProgress(list.get(13)); s3.setProgress(list.get(14)); s4.setProgress(list.get(15));
                break;
        }
    }

    private void saveSeekbar() {
        switch (page) {
            case 1:
                list.set(0, s1.getProgress()); list.set(1, s2.getProgress()); list.set(2, s3.getProgress()); list.set(3, s4.getProgress());
                break;
            case 2:
                list.set(4, s1.getProgress()); list.set(5, s2.getProgress()); list.set(6, s3.getProgress()); list.set(7, s4.getProgress());
                break;
            case 3:
                list.set(8, s1.getProgress()); list.set(9, s2.getProgress()); list.set(10, s3.getProgress()); list.set(11, s4.getProgress());
                break;
            case 4:
                list.set(12, s1.getProgress()); list.set(13, s2.getProgress()); list.set(14, s3.getProgress()); list.set(15, s4.getProgress());
                break;
        }
    }

    private void calculator() {

        // 값 더하기
        for (int i = 0; i < 16; i++) {
            switch (i) {
                case 0:
                    if (list.get(i) == 0) {
                        mois += 4;
                    } else if (list.get(i) == 1) {
                        mois += 3;
                    } else if (list.get(i) == 2) {
                        mois += 2;
                    } else if (list.get(i) == 3) {
                        mois += 1;
                    }
                    mois += 1;
                    break;
                case 1:
                    mois += list.get(i);
                    mois += 1;
                    break;
                case 2:
                    if (list.get(i) == 0) {
                        mois += 4;
                    } else if (list.get(i) == 1) {
                        mois += 3;
                    } else if (list.get(i) == 2) {
                        mois += 2;
                    } else if (list.get(i) == 3) {
                        mois += 1;
                    }
                    mois += 1;
                    break;
                case 3:
                    if (list.get(i) == 0) {
                        oil += 4;
                    } else if (list.get(i) == 1) {
                        oil += 3;
                    } else if (list.get(i) == 2) {
                        oil += 2;
                    } else if (list.get(i) == 3) {
                        oil += 1;
                    }
                    oil += 1;
                    break;

                case 4:
                    if (list.get(i) == 2) {
                        oil += 4;
                    } else if (list.get(i) == 1 || list.get(i) == 3) {
                        oil += 2;
                    }
                    oil += 1;
                    break;
                case 5:
                    if (list.get(i) == 0) {
                        oil += 4;
                    } else if (list.get(i) == 1) {
                        oil += 3;
                    } else if (list.get(i) == 2) {
                        oil += 2;
                    } else if (list.get(i) == 3) {
                        oil += 1;
                    }
                    oil += 1;
                    break;
                case 6:
                    if (list.get(i) == 0) {
                        resis += 4;
                    } else if (list.get(i) == 1) {
                        resis += 3;
                    } else if (list.get(i) == 2) {
                        resis += 2;
                    } else if (list.get(i) == 3) {
                        resis += 1;
                    }
                    resis += 1;
                    break;
                case 7:
                    if (list.get(i) == 0) {
                        resis += 4;
                    } else if (list.get(i) == 1) {
                        resis += 3;
                    } else if (list.get(i) == 2) {
                        resis += 2;
                    } else if (list.get(i) == 3) {
                        resis += 1;
                    }
                    resis += 1;
                    break;

                case 8:
                    if (list.get(i) == 0) {
                        resis += 4;
                    } else if (list.get(i) == 1) {
                        resis += 3;
                    } else if (list.get(i) == 2) {
                        resis += 2;
                    } else if (list.get(i) == 3) {
                        resis += 1;
                    }
                    resis += 1;
                    break;
                case 9:
                    if (list.get(i) == 0) {
                        elas += 4;
                    } else if (list.get(i) == 1) {
                        elas += 3;
                    } else if (list.get(i) == 2) {
                        elas += 2;
                    } else if (list.get(i) == 3) {
                        elas += 1;
                    }
                    elas += 1;
                    break;
                case 10:
                    if (list.get(i) == 0) {
                        elas += 4;
                    } else if (list.get(i) == 1) {
                        elas += 3;
                    } else if (list.get(i) == 2) {
                        elas += 2;
                    } else if (list.get(i) == 3) {
                        elas += 1;
                    }
                    elas += 1;
                    break;
                case 11:
                    if (list.get(i) == 0) {
                        elas += 4;
                    } else if (list.get(i) == 1) {
                        elas += 3;
                    } else if (list.get(i) == 2) {
                        elas += 2;
                    } else if (list.get(i) == 3) {
                        elas += 1;
                    }
                    elas += 1;
                    break;

                case 12:
                    if (list.get(i) == 0) {
                        elas += 4;
                    } else if (list.get(i) == 1) {
                        elas += 3;
                    } else if (list.get(i) == 2) {
                        elas += 2;
                    } else if (list.get(i) == 3) {
                        elas += 1;
                    }
                    elas += 1;
                    break;
                case 13:
                    anti += list.get(i);
                    anti += 1;
                    break;
                case 14:
                    if (list.get(i) == 0) {
                        anti += 4;
                    } else if (list.get(i) == 1) {
                        anti += 3;
                    } else if (list.get(i) == 2) {
                        anti += 2;
                    } else if (list.get(i) == 3) {
                        anti += 1;
                    }
                    anti += 1;
                    break;
                case 15:
                    if (list.get(i) == 0) {
                        anti += 4;
                    } else if (list.get(i) == 1) {
                        anti += 3;
                    } else if (list.get(i) == 2) {
                        anti += 2;
                    } else if (list.get(i) == 3) {
                        anti += 1;
                    }
                    anti += 1;
                    break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Log.i(i + "___ ", String.valueOf(list.get(i)));
        }
        Log.i("______________________", String.valueOf(list.size()));
        Log.i("mois: ", String.valueOf(mois));
        Log.i("oil: ", String.valueOf(oil));
        Log.i("resis: ", String.valueOf(resis));
        Log.i("elas: ", String.valueOf(elas));
        Log.i("anti: ", String.valueOf(anti));

        mois = Math.round(mois / 3 * 20);
        oil = Math.round(oil / 3 * 20);
        resis = Math.round(resis / 3 * 20);
        elas = Math.round(elas / 4 * 20);
        anti = Math.round(anti / 3 * 20);

        mois_c.setValue(mois);
        oiㅣ_c.setValue(oil);
        res_c.setValue(resis);
        elas_c.setValue(elas);
        anti_c.setValue(anti);

        Log.i("______________________", String.valueOf(list.size()));
        Log.i("mois: ", String.valueOf(mois));
        Log.i("oil: ", String.valueOf(oil));
        Log.i("resis: ", String.valueOf(resis));
        Log.i("elas: ", String.valueOf(elas));
        Log.i("anti: ", String.valueOf(anti));

        /*if (od>2.5) skin_type+="O ";
        else if (od<2.5) skin_type+="D ";
        else {
            if (list.get(1)>2.5) skin_type+="O ";
            else skin_type+="D ";
        }

        if (sr>2.5) skin_type+="S ";
        else if (sr<2.5) skin_type+="R ";
        else {
            if (list.get(5)>2.5) skin_type+="S ";
            else skin_type+="R ";
        }

        if (pn>2.5) skin_type+="P ";
        else if (pn<2.5) skin_type+="N " ;
        else {
            if (list.get(8)>2.5) skin_type+="P ";
            else skin_type+="N ";
        }

        if (wt>2.5) skin_type+="W";
        else if (wt<2.5) skin_type+="T";
        else {
            if (list.get(12)>2.5) skin_type+="W";
            else skin_type+="T";
        }*/

        /*od = 4 - od;
        sr = 4 - sr;
        pn = 4 - pn;
        wt = 4 - wt;*/

        databaseReference.child("result").child("skintype").setValue(skin_type);
    }

    double mois2=0.00,oil2=0.00,resis2=0.00,elas2=0.00,anti2=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skintype);

        for(int i=0; i<16; i++) {
            list.add(0);
        }

        Intent subintent = getIntent();
        mois2 = subintent.getExtras().getDouble("a");
        oil2 = subintent.getExtras().getDouble("b");
        resis2 = subintent.getExtras().getDouble("c");
        elas2 = subintent.getExtras().getDouble("d");
        anti2 = subintent.getExtras().getDouble("e");

        // popupt창 사이즈 지정
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.9); //Display 사이즈의 100%
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        next = findViewById(R.id.next); pre = findViewById(R.id.pre); done = findViewById(R.id.done); again = findViewById(R.id.again);
        s1 = findViewById(R.id.s1); s2 = findViewById(R.id.s2); s3 = findViewById(R.id.s3); s4 = findViewById(R.id.s4);
        q1 = findViewById(R.id.q1);  q2 = findViewById(R.id.q2); q3 = findViewById(R.id.q3);  q4 = findViewById(R.id.q4);
        imageButton = findViewById(R.id.imageButton); content1 = findViewById(R.id.content1); content2 = findViewById(R.id.content2);
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
        content3=findViewById(R.id.content3);
        skintypetxt=findViewById(R.id.skintypetxt);
        content4=findViewById(R.id.content4);

        setPage();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.next:
                        saveSeekbar(); page++; setPage(); setSeekbar();
                        break;
                    case R.id.pre:
                        saveSeekbar(); page--; setSeekbar(); setPage();
                        break;
                    case R.id.imageButton:
                        finish();
                        break;
                    case R.id.done:
                        //Intent intent = new Intent(getApplicationContext(), SkintypeResultActivity.class);
                        //intent.putExtra("result", list);
                        //startActivity(intent);
                        saveSeekbar(); calculator(); page++; setPage();
                        break;
                    case R.id.again:
                        reset();
                        break;
                }
            }
        };
        next.setOnClickListener(onClickListener);
        pre.setOnClickListener(onClickListener);
        done.setOnClickListener(onClickListener);
        imageButton.setOnClickListener(onClickListener);
        again.setOnClickListener(onClickListener);
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void setResult_detail() {
        skin_type = "";
        if (mois >50) skin_type+="M "; else skin_type+="m ";
        if (oil >50) skin_type+="O "; else skin_type+="o ";
        if (resis >50) skin_type+="R "; else skin_type+="r ";
        if (elas >50) skin_type+="E "; else skin_type+="e ";
        if (anti >50) skin_type+="A"; else skin_type+="a";

        mois_c.setValue(mois);
        oiㅣ_c.setValue(oil);
        res_c.setValue(resis);
        elas_c.setValue(elas);
        anti_c.setValue(anti);

        mois_c2.setValue(Math.round(mois2));
        oiㅣ_c2.setValue(Math.round(oil2));
        res_c2.setValue(Math.round(resis2));
        elas_c2.setValue(Math.round(elas2));
        anti_c2.setValue(Math.round(anti2));

        skintypetxt.setText(skin_type);

        setDataSkin();

        setData task = new setData();
        task.execute("http://"+HomeActivity.IP_Address+"/saveSkintype.php", "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        homeactivity.backgroundimg.setImageResource(0);
        homeactivity.dashback.setImageResource(0);
    }
}