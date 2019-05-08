package com.reziena.user.reziena_1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.*;
import android.os.*;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;

import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.net.URLEncoder;
import java.util.*;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static java.net.Authenticator.RequestorType.SERVER;

public class Signin2Activity extends AppCompatActivity {
    TextView okay;
    private EditText name;
    private String email;
    String password;
    RadioGroup gender;
    private long mLastClickTime = 0;
    RadioButton genderresult;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;
    LinearLayout signin;
    private Uri mImageCaptureUri;
    private int id_view;
    CircleImageView profile;
    String month, year, genderstring, countrystring, day;
    public static Activity signin2;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    String namestring, idstring, profileurl;
    private static final String DEFAULT_LOCAL = "Portugal";
    public int yearint, dayint, monthint;
    Drawable alphasignin;
    int serverResponseCode = 0;
    private static final int REQUEST_STORAGE = 1;
    String nameP, pathP;

    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);
        signin2 = Signin2Activity.this;

        Intent subintent = getIntent();

        namestring = subintent.getExtras().getString("name");
        idstring = subintent.getExtras().getString("id");
        profileurl = subintent.getExtras().getString("profile");
        password = subintent.getExtras().getString("password");
        name = findViewById(R.id.name);
        profile = findViewById(R.id.signinprofile);
        signin = findViewById(R.id.signin_signin2);

        alphasignin = signin.getBackground();
        //profile.setImageResource(R.drawable.no_profile);

        Spinner birthday_year = findViewById(R.id.birthday_year);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<Integer> yearAdapter = new ArrayList<>();
        for(int i=0;i<100;i++){
            yearAdapter.add(year--);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_item,yearAdapter);
        birthday_year.setAdapter(arrayAdapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(birthday_year);
            window.setHeight(500); //pixel
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spinner birthday_month = findViewById(R.id.birthday_month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.birthday_month, R.layout.spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthday_month.setAdapter(monthAdapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(birthday_month);
            window.setHeight(500); //pixel
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spinner birthday_day = findViewById(R.id.birthday_day);
        ArrayList<Integer> birthAdapter = new ArrayList<>();
        for(int i=1;i<=31;i++){
            birthAdapter.add(i);
        }
        ArrayAdapter<Integer> birthdayAdapter = new ArrayAdapter<Integer>(this, R.layout.spinner_item,birthAdapter);
        birthday_day.setAdapter(birthdayAdapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(birthday_day);
            window.setHeight(500); //pixel
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spinner couuntry = findViewById(R.id.country);
        ArrayAdapter countryarray = ArrayAdapter.createFromResource(this,
                R.array.country, R.layout.spinner_item);
        countryarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        couuntry.setAdapter(countryarray);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(couuntry);
            window.setHeight(500); //pixel
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spinner gender = findViewById(R.id.gender);
        ArrayAdapter genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, R.layout.spinner_item);
        countryarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);


        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderstring = gender.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        birthday_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearint = Integer.parseInt(String.valueOf(birthday_year.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        birthday_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthint = Integer.parseInt(String.valueOf(birthday_month.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        birthday_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayint = Integer.parseInt(String.valueOf(birthday_day.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        couuntry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countrystring = String.valueOf(couuntry.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(namestring!=null){
            name.setText(namestring);
        }

        if(profileurl==null){
            profile.setImageResource(R.drawable.no_profile);
        }

        if(profileurl!=null){
            Glide.with(this).load(profileurl).into(profile);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signin_signin2:
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 10000){
                            Toast.makeText(Signin2Activity.this, "loading....", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        if( name.getText().toString().length() == 0 ) {
                            Toast.makeText(Signin2Activity.this, "이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                            name.requestFocus();
                            return;
                        }
                        String saveName = name.getText().toString();
                        String birth = yearint+"/"+monthint+"/"+dayint;

                        if (mImageCaptureUri!=null) {
                            nameP = getName(mImageCaptureUri);
                            pathP = getPath(mImageCaptureUri);
                        } else {
                            nameP = "no_profile";
                            pathP = "no_profile";
                        }
                        Log.e("nameP==", nameP);
                        Log.e("pathP==", pathP);
                        Long tsLong = System.currentTimeMillis() / 1000;
                        profileurl = "/uploads/"+"IMG_" + tsLong.toString()+".JPEG";

                        SharedPreferences sp_userName = getSharedPreferences("userName", MODE_PRIVATE);
                        SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sp_userName.edit();
                        SharedPreferences.Editor editor2 = sp_userID.edit();
                        editor1.putString("userName", saveName);
                        editor2.putString("userID", idstring);
                        editor1.commit();
                        editor2.commit();
                        Log.e("Login ", saveName+"님 로그인");
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("name","skintypedialog");
                        intent.putExtra("signin","finish");
                        intent.putExtra("isFirst", "yes");
                        startActivity(intent);
                        finish();

                        if (mImageCaptureUri!=null) {
                            uploadProfile up = new uploadProfile();
                            up.execute(mImageCaptureUri.getPath());
                        } else {
                            profileurl = "no_profile";
                        }
                        setData task = new setData();
                        task.execute("http://"+ HomeActivity.IP_Address +"/saveUser.php", idstring, password, saveName, genderstring, birth, profileurl, countrystring);
                        break;
                    case R.id.signinprofile:
                        checkPermissions();
                        doTakeAlbumAction();
                        break;
                }
            }
        };
        signin.setOnClickListener(onClickListener);
        profile.setOnClickListener(onClickListener);
    }

    class setData extends AsyncTask<String, Void, String> {
        String id, pw, name, gender, birth, profile, country;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.e("sign-onPostExecute", "response - " + result);

            if (result == null){
                Log.e("onPostExecute", "erre");
            }
            else {
                settings(result);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            id = params[1];
            pw = params[2];
            name = params[3];
            gender = params[4];
            birth = params[5];
            profile = params[6];
            country = params[7];

            if (pw==null) pw="social";

            String postParameters = "id="+id+"&pw="+pw+"&name="+name+"&gender="+gender+"&birth="+birth+"&profile="+profile+"&country="+country;
            Log.e("sign-postParameters", postParameters);

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                // response
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                String responseStatusMessage = httpURLConnection.getResponseMessage();
                Log.e("sign-response", "POST response Code - " + responseStatusCode);
                Log.e("sign-response", "POST response Message - "+ responseStatusMessage);

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    // 정상적인 응답 데이터
                    Log.e("sign-inputstream: ", "정상적");
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    // error
                    Log.e("sign-inputstream: ", "비정상적: " + httpURLConnection.getErrorStream());
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
                Log.e("sign-ERROR", "InsertDataError "+e.getMessage());
            }
            return null;

        }

        private void settings(String result){
            if (result.contains("Error")) {
                Log.e("Error", "you have Error");
            } else if (result.contains("1 record added")){
                Log.e("Login ", "1 record added");
            }
        }
    }

    public void doTakeAlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode!=RESULT_OK)
            return;

        switch(requestCode)
        {
            case PICK_FROM_ALBUM: {
                mImageCaptureUri = data.getData();
                Log.e("SmartWheel", mImageCaptureUri.getPath().toString());
            }
            case PICK_FROM_CAMERA:{
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri,"image/");

                intent.putExtra("outputX",200);
                intent.putExtra("outputY",200);
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
                intent.putExtra("scale",true);
                intent.putExtra("return-data",true);
                startActivityForResult(intent,CROP_FROM_IMAGE);
                break;
            }
            case CROP_FROM_IMAGE:{
                if(resultCode!=RESULT_OK){
                    Log.e("resultCode!=RESULT_OK", "ㅇㅅㅇ");
                    return;
                }
                final Bundle extras = data.getExtras();

                if(extras!=null){
                    photo = extras.getParcelable("data");
                    profile.setImageBitmap(photo);
                    break;
                }
            }
        }
    } // end of onActivityResult

    private String hashMapToUrl(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    class uploadProfile extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) {
            super.onPostExecute(getResult);
            //profileurl = getResult;
            Log.e("getResult==", getResult);
        }

        @Override
        protected String doInBackground(String... params) {

            String upLoadServerUri = "http://52.32.36.182/saveProfile.php";//서버컴퓨터의 ip주소

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            String encodeImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            HashMap<String,String> detail = new HashMap<>();
            detail.put("name", profileurl);
            detail.put("image", encodeImage);

            try {
                String dataToSend = hashMapToUrl(detail);

                URL url = new URL(upLoadServerUri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //set timeout of 30 seconds
                con.setConnectTimeout(1000 * 30);
                con.setReadTimeout(1000 * 30);
                //method
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                OutputStream os = con.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                //make request
                writer.write(dataToSend);
                writer.flush();
                writer.close();
                os.close();

                //get the response
                int responseCode = con.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK) {
                    Log.e("File Upload Completed", "근데 왜 안돼냐 싀바" );
                    //read the response
                    StringBuilder sb = new StringBuilder();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String line;

                    //loop through the response from the server
                    while ((line = reader.readLine()) != null){
                        sb.append(line).append("\n");
                    }

                    //return the response
                    return sb.toString();
                }else {
                    Log.e("","ERROR - Invalid response code from server "+ responseCode);
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("","ERROR "+e);
                return null;
            }
        }
    }

    private void storeCropImage(Bitmap bitmap, String filePath){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel";
        File directory_smartWheel = new File(dirPath);

        if(!directory_smartWheel.exists()){
            directory_smartWheel.mkdir();
        }

        //File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try{
            //copyFile.createNewFile();
            ///out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,out);

            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(copyFile)));

            out.flush();
            out.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void checkPermissions() {

        int permissionCheck1 = ContextCompat.checkSelfPermission(this , WRITE_EXTERNAL_STORAGE);
        if(permissionCheck1 == PackageManager.PERMISSION_GRANTED) Log.e("퍼미션", "WRITE_EXTERNAL_STORAGE granted!");
        else {
            Log.e("퍼미션", "WRITE_EXTERNAL_STORAGE not granted!");
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_STORAGE) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("퍼미션", "WRITE_EXTERNAL_STORAGE granted!");
            } else {
                Log.e("퍼미션", "WRITE_EXTERNAL_STORAGE 삭제하지말라고!!");
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }

    // 파일명 찾기
    private String getName(Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = 		cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void onResume() {
        super.onResume();
        alphasignin.setAlpha(255);
    }
}