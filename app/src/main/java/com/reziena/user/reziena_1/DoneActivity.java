package com.reziena.user.reziena_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

import static java.security.AccessController.getContext;

@SuppressLint("ValidFragment")
public class DoneActivity extends AppCompatActivity implements View.OnClickListener {
    MyDialogListener dialogListener;
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";
    private static final String BUNDLE_KEy_STRING = "bundle_key_string_effect";
    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private Context context;
    String donestring;
    Intent intent;
    String stringlist;
    TextView positive, non_positive;
    TreatActivity_cheekleft2 cheekleft = (TreatActivity_cheekleft2) TreatActivity_cheekleft2.cheekleftactivity;
    TreatActivity_cheekright2 cheekright = (TreatActivity_cheekright2) TreatActivity_cheekright2.cheekrightactivity;
    TreatActivity_underleft2 underleft = (TreatActivity_underleft2) TreatActivity_underleft2.underleftativity;
    TreatActivity_underright2 underright = (TreatActivity_underright2) TreatActivity_underright2.underrightactivity;
    HomeActivity homeActivity = (HomeActivity) HomeActivity.homeactivity;
    TreatActivity treatactivity = (TreatActivity) TreatActivity.treatactivity;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference underrightdata, underleftdata, cheekleftdata, cheekrightdata;
    public String underrightstring, underleftstring, cheekrightstring, cheekleftstring;
    TextView oppositTxT;
    LinearLayout imagebutton;

    boolean uneye_l=false, uneye_r=false, cheek_l=false, cheek_r=false, eye_r=false, eye_l=false, fore_l = false, fore_r=false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatfinish_ur);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        Intent subintent = getIntent();
        stringlist = subintent.getStringExtra("stringlist");
        Log.e("아늬", stringlist);

        HomeActivity.where = null;

        positive = findViewById(R.id.positive);
        non_positive = findViewById(R.id.non_positive);

        positive.setOnClickListener(this);
        non_positive.setOnClickListener(this);

        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");
        cheekleftdata = databaseReference.child("result").child("cheekleftstring");
        cheekrightdata = databaseReference.child("result").child("cheekrightstring");

        oppositTxT = findViewById(R.id.oppositTxT);
        imagebutton=findViewById(R.id.imageButton);

        cheekrightdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String string = dataSnapshot.getValue(String.class);
                cheekrightstring = string;
                if (stringlist.equals("cheekleft")) {
                    if (cheekrightstring.equals("true")) {
                        oppositTxT.setText("finish");
                        non_positive.setText("Main");
                        positive.setText("Treat");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        cheekleftdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String string = dataSnapshot.getValue(String.class);
                cheekleftstring = string;
                if (stringlist.equals("cheekright")) {
                    if (cheekleftstring.equals("true")) {
                        oppositTxT.setText("finish");
                        non_positive.setText("Main");
                        positive.setText("Treat");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        underleftdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String string = dataSnapshot.getValue(String.class);
                underleftstring = string;
                if (stringlist.equals("underright")) {
                    if (underleftstring.equals("true")) {
                        oppositTxT.setText("finish");
                        non_positive.setText("Main");
                        positive.setText("Treat");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        underrightdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String string = dataSnapshot.getValue(String.class);
                underrightstring = string;
                if (stringlist.equals("underleft")) {
                    if (underrightstring.equals("true")) {
                        oppositTxT.setText("finish");
                        non_positive.setText("Main");
                        positive.setText("Treat");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void onResume() {
        super.onResume();
        getDataTreat();
        /*GetData task = new GetData();
        task.execute("http://"+HomeActivity.IP_Address+"/callingTreathome.php", "");*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive:
                Log.e("onclick", "positive");
                if (positive.getText().equals("Home")) {
                    Log.e("선택한거", "Home");
                    intent = new Intent(v.getContext(), HomeActivity.class);
                    v.getContext().startActivity(intent);
                    finish();
                } else {
                    if (stringlist.equals("underright")) {
                        Log.e("선택한거", "finish  X");
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "uneye_l";
                        intent = new Intent(v.getContext(), TreatActivity_underleft2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("underleft")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "uneye_r";
                        intent = new Intent(v.getContext(), TreatActivity_underright2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("cheekright")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "cheek_l";
                        intent = new Intent(v.getContext(), TreatActivity_cheekleft2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("cheekleft")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "cheek_r";
                        intent = new Intent(v.getContext(), TreatActivity_cheekright2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("eyeright")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "eye_l";
                        intent = new Intent(v.getContext(), TreatActivity_eyerleft2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("eyeleft")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "eye_r";
                        intent = new Intent(v.getContext(), TreatActivity_eyeright2.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("foreright")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "fore_l";
                        intent = new Intent(v.getContext(), TreatActivity_foreheadleft.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                    if (stringlist.equals("foreleft")) {
                        HomeActivity.countStart = 0;
                        HomeActivity.where = "fore_r";
                        intent = new Intent(v.getContext(), TreatActivity.class);
                        v.getContext().startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.non_positive:
                Log.e("onclick", "non_positive");
                intent = new Intent(v.getContext(), TreatActivity.class);
                v.getContext().startActivity(intent);
                finish();
                /*if (non_positive.getText().equals("No")) {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    //homeActivity.finish();
                    finish();
                }*/
                /*
                if (stringlist.equals("underright")) {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    homeActivity.finish();
                    finish();
                }
                if (stringlist.equals("underleft")) {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    homeActivity.finish();
                    finish();
                }
                if (stringlist.equals("cheekright")) {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    homeActivity.finish();
                    finish();
                }
                if (stringlist.equals("cheekleft")) {
                    intent = new Intent(v.getContext(), TreatActivity.class);
                    v.getContext().startActivity(intent);
                    homeActivity.finish();
                    finish();
                }*/
                break;
            case R.id.imageButton:
                cheekleft.backgroundimg.setImageResource(0);
                cheekright.backgroundimg.setImageResource(0);
                underright.backgroundimg.setImageResource(0);
                underleft.backgroundimg.setImageResource(0);
                break;
        }
    }

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);
            Log.e("treatCheck_result",getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID;
            Log.e("treatCheck", "update/"+postParameters);

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

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
                    Log.e("treatCheck", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("treatCheck", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("treatCheck", e.getMessage());
            }
            return null;
        }
    }

    private void getDataTreat() {

        String start;

        Log.e("getDataTreat init", "TreatActivity");
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences treat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        String treatResult = treat_zone.getString("tZone", "tZone");
        start = treat_date.getString("tDate", "tDate");
        Log.e("treat_zone~!~!", treatResult);
        Log.e("treat_date~!~!", start);

        String[] hihi = treatResult.split("/");
        Log.e("hihi.length==", String.valueOf(hihi.length));
        if (treatResult=="tZone") {
            GetData task = new GetData();
            task.execute("http://"+HomeActivity.IP_Address+"/callingTreathome.php", "");
        }
        else {
            setResult(treatResult);
            setResult();
        }
    }

    private void setResult() {
        // underleft
        if (stringlist.equals("underleft")) {
            Log.e("stringlist==underleft", "uneye_r?" + String.valueOf(uneye_r));
            if (!uneye_r) {
                // uneye_r을 안했으면
                Log.e("현재: under_left", "근데 너 uneye_r안햇어" + String.valueOf(uneye_r));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: under_left", "근데 너 uneye_r햇어" + String.valueOf(uneye_r));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // underright
        if (stringlist.equals("underright")) {
            Log.e("stringlist==underright", "uneye_l?" + String.valueOf(uneye_l));
            if (!uneye_l) {
                // uneye_l을 안했으면
                Log.e("현재: under_right", "근데 너 uneye_l안햇어" + String.valueOf(uneye_l));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: under_right", "근데 너 uneye_l햇어" + String.valueOf(uneye_l));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // cheekl
        if (stringlist.equals("cheekleft")) {
            Log.e("stringlist==cheekleft", "cheek_r?" + String.valueOf(cheek_r));
            if (!cheek_r) {
                // uneye_r을 안했으면
                Log.e("현재: cheek_left", "근데 너 cheek_r안햇어" + String.valueOf(cheek_r));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: cheek_left", "근데 너 cheek_r햇어" + String.valueOf(cheek_r));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // cheekr
        if (stringlist.equals("cheekright")) {
            Log.e("stringlist==cheekright", "cheek_r?" + String.valueOf(cheek_l));
            if (!cheek_l) {
                // uneye_l을 안했으면
                Log.e("현재: cheek_right", "근데 너 cheek_l안햇어" + String.valueOf(cheek_l));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: cheek_right", "근데 너 cheek_l햇어" + String.valueOf(cheek_l));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // eyeleft
        if (stringlist.equals("eyeleft")) {
            Log.e("stringlist==eyeleft", "eye_r?" + String.valueOf(eye_r));
            if (!eye_r) {
                // eye_r 안했으면
                Log.e("현재: eye_left", "근데 너 eye_r안햇어" + String.valueOf(eye_r));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: eye_left", "너 eye_r햇어" + String.valueOf(cheek_r));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // eyeright
        if (stringlist.equals("eyeright")) {
            Log.e("stringlist==eyeright", "eye_l?" + String.valueOf(eye_l));
            if (!eye_l) {
                // eye_l 안했으면
                Log.e("현재: eye_right", "근데 너 eye_l안햇어" + String.valueOf(eye_l));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: eye_right", "너 eye_l햇어" + String.valueOf(eye_l));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }

        // foreleft
        if (stringlist.equals("foreleft")) {
            Log.e("stringlist==foreleft", "fore_r?" + String.valueOf(fore_r));
            if (!fore_r) {
                // fore_r 안했으면
                Log.e("현재: fore_left", "근데 너 fore_r안햇어" + String.valueOf(fore_r));
                oppositTxT.setText("Good job!\n\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                non_positive.setText("No");
                positive.setText("Okay");
            } else {
                // 했으면
                Log.e("현재: fore_left", "너 fore_r햇어" + String.valueOf(fore_r));

                setDataDate();

                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                non_positive.setText("Treatment page");
                positive.setText("Home");
            }
        }
        // foreright
        if (stringlist.equals("foreright")) {
            Log.e("stringlist==fore_r", "fore_l?" + String.valueOf(fore_l));

            HomeActivity.countStart = 0;
            HomeActivity.where = "fore_l";
            intent = new Intent(getApplicationContext(), TreatActivity_foreheadleft.class);
            startActivity(intent);
            finish();

                /*if (!fore_l) {
                    // fore_l 안했으면
                    Log.e("현재: fore_r", "근데 너 fore_l안햇어" + String.valueOf(fore_l));
                    oppositTxT.setText("Good job!\n\nYou have completed\nYou have completed\none side of the care zone.\nLet’s move to the opposite side.");
                    non_positive.setText("No");
                    positive.setText("Okay");
                } else {
                    // 했으면
                    Log.e("현재: fore_r", "너 fore_l햇어" + String.valueOf(fore_l));

                    setData task = new setData();
                    task.execute("http://"+HomeActivity.IP_Address+"/treatCheck.php", "");

                    oppositTxT.setText("Excellent!\n\nYou have completed \n today's care zone!");
                    non_positive.setText("Treatment page");
                    positive.setText("Home");
                }*/
        }
        if (stringlist.equals("foreleft")) {
            setDataDate();
        }
    }

    private void setDataDate() {

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date();
        String date = mSimpleDateFormat.format ( currentTime );

        SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = treaat_date.edit();
        editor1.putString("tDate", date);
        editor1.commit();
    }

    private void setResult(String result) {
        if (result.contains("under_l")) uneye_l=true;
        if (result.contains("under_r")) uneye_r=true;
        if (result.contains("cheek_l")) cheek_l=true;
        if (result.contains("cheek_r")) cheek_r=true;
        if (result.contains("eye_r")) eye_r=true;
        if (result.contains("eye_l")) eye_l=true;
        if (result.contains("fore_r")) fore_r=true;
        if (result.contains("fore_l")) fore_l=true;
        Log.e("un_l, un_r, ch_l, ch_r", String.valueOf(uneye_l)+String.valueOf(uneye_r)+String.valueOf(cheek_l)+String.valueOf(cheek_r));
        Log.e("eyel, eyer, forel,forer", String.valueOf(eye_l)+String.valueOf(eye_r)+String.valueOf(fore_l)+String.valueOf(fore_r));
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);

            Log.e("treat1--", "onPostExecute - " + getResult);

            if (getResult == null) {
            } else if (getResult.contains("No_results")) {
            } else {
                setResult(getResult);
                setResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format(currentTime);

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "date=" + date + "&id=" + userID;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                Log.e("treat1-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.e("treat1-response", "code - HTTP_OK - " + responseStatusCode);
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("treat1-response", "code - HTTP_NOT_OK - " + responseStatusCode);
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.e("treat1-error-stream", e.getMessage());
            }
            return null;
        }

        /*private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    treatResult+=item.getString("value");
                }
                Log.e("treatResult", treatResult);

            } catch (JSONException e) {
                Log.d("treat1-JSON", "showResult : "+e.getMessage());
            }

        }*/
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cheekleft.backgroundimg.setImageResource(0);
        cheekright.backgroundimg.setImageResource(0);
        underright.backgroundimg.setImageResource(0);
        underleft.backgroundimg.setImageResource(0);
    }
}