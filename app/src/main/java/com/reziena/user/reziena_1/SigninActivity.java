package com.reziena.user.reziena_1;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener,AuthenticationListener {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    LinearLayout login_signin, signin_signin;
    private long mLastClickTime = 0;
    ImageView logincheck;
    String pass=" ^[a-zA-Z0-9]*$";
    public  Pattern email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    LinearLayout login, signin;
    private InputMethod.SessionCallback callback;
    SignInButton btn_login;
    LoginButton facebook_login;
    // 구글로그인 result 상수
    private static final int RC_SIGN_IN = 1000;
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    HomeActivity homeActivity = (HomeActivity) HomeActivity.homeactivity;
    ImageView facebook, messageicon,google, twittericon,kakao;
    private LoginCallback mLoginCallback;
    private CallbackManager callbackManager;
    Intent intent;
    private Context mContext;
    private SessionCallback mKakaocallback;
    private String tokeninsta = null;
    private String tokenfb = null;
    private AppPreferences appPreferences = null;
    private AuthenticationDialog authenticationDialog = null;
    private View info = null;
    String fbname, fbemail, fbprofile, fbid;
    String gomail, goname, goprofile, goid;
    String kaname, kaemail, kaprofile, kaid;
    String isname, isprofile, isid;
    AccessToken accessToken;
    AccessToken tokenfab;
    public static Activity loginactivity;
    Drawable alphalogin;
    Drawable alphasignin;
    String finish;

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";
    private static boolean iConnected;


    @SuppressLint({"Range", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mLoginCallback = new LoginCallback();
        appPreferences = new AppPreferences(this);
        accessToken = AccessToken.getCurrentAccessToken();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        login_signin = findViewById(R.id.login_signin);
        signin_signin = findViewById(R.id.signin_signin);
        logincheck = findViewById(R.id.sang);
        callbackManager = CallbackManager.Factory.create();
        kakao=findViewById(R.id.message);
        google = findViewById(R.id.google_sign);
        facebook = findViewById(R.id.facebook);
        twittericon=findViewById(R.id.twitter);
        btn_login=findViewById(R.id.googlelogin);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1041067070844-ijmf37v4571254nhjmt4r80cj9r3n9hj.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        mAuth = FirebaseAuth.getInstance();

        tokeninsta = appPreferences.getString(AppPreferences.TOKEN);

        if(tokenfb != null){
            LoginManager loginManager = LoginManager.getInstance();
            loginManager.logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
            loginManager.registerCallback(callbackManager, mLoginCallback);
        }

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);


        Drawable alphalogin = login_signin.getBackground();
        Drawable alphasignin = signin_signin.getBackground();


        alphalogin.setAlpha(50);
        alphasignin.setAlpha(50);

        login_signin.setEnabled(false);
        signin_signin.setEnabled(false);
        btn_login.setOnClickListener(this);
        twittericon.setOnClickListener(this);
        kakao.setOnClickListener(this);
        google.setOnClickListener(this);
        facebook.setOnClickListener(this);


        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("Range")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = etEmail.getText().toString();

                if( email.contains("@")&& email.contains(".")) {
                    etEmail.setTextColor(Color.parseColor("#450969"));
                    // 비밀번호 일치 검사
                    etPasswordConfirm.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @SuppressLint("Range")
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String password = etPassword.getText().toString();
                            String confirm = etPasswordConfirm.getText().toString();
                            if(s.length()==0){
                                logincheck.setVisibility(View.INVISIBLE);
                            }
                            else {
                                if(s.length()>=6&&s.length()<=12&&Pattern.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{6,12}$",password)) {
                                    if (password.equals(confirm)) {
                                        logincheck.setVisibility(View.VISIBLE);
                                        etPasswordConfirm.setTextColor(Color.parseColor("#450969"));
                                        logincheck.setImageResource(R.drawable.logincheck);
                                        alphasignin.setAlpha(255);//알파값 20
                                        signin_signin.setEnabled(true);
                                    } else {
                                        logincheck.setVisibility(View.VISIBLE);
                                        etPasswordConfirm.setTextColor(Color.parseColor("#9E0958"));
                                        logincheck.setImageResource(R.drawable.loginx);
                                        alphasignin.setAlpha(50);//알파값 20
                                        signin_signin.setEnabled(false);
                                    }
                                }
                                else{
                                    alphasignin.setAlpha(50);//알파값 20
                                    signin_signin.setEnabled(false);
                                    logincheck.setVisibility(View.VISIBLE);
                                    logincheck.setImageResource(R.drawable.loginx);
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    etPassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String password = etPassword.getText().toString();
                            String confirm = etPasswordConfirm.getText().toString();
                            Log.e("password",password);
                            Log.e("password",confirm);
                            if(s.length()==0){
                                logincheck.setVisibility(View.INVISIBLE);
                            }
                            else {
                                if(s.length()>=6&&s.length()<=12&&Pattern.matches(" ^(?=.*[a-zA-Z])(?=.*[0-9]).{6,12}$",password)) {
                                    if (password.equals(confirm)) {
                                        logincheck.setVisibility(View.VISIBLE);
                                        etPasswordConfirm.setTextColor(Color.parseColor("#450969"));
                                        logincheck.setImageResource(R.drawable.logincheck);
                                        alphasignin.setAlpha(255);//알파값 20
                                        signin_signin.setEnabled(true);

                                    } else {
                                        logincheck.setVisibility(View.VISIBLE);
                                        etPasswordConfirm.setTextColor(Color.parseColor("#9E0958"));
                                        logincheck.setImageResource(R.drawable.loginx);
                                        alphasignin.setAlpha(50);//알파값 20
                                        signin_signin.setEnabled(false);
                                    }
                                }
                                else{
                                    alphasignin.setAlpha(50);//알파값 20
                                    signin_signin.setEnabled(false);
                                    logincheck.setVisibility(View.VISIBLE);
                                    logincheck.setImageResource(R.drawable.loginx);
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String confirm = etPasswordConfirm.getText().toString();
                            if(confirm==null){
                                logincheck.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                } else {
                    etEmail.setTextColor(Color.parseColor("#9E0958"));
                    alphasignin.setAlpha(50);//알파값 20
                    signin_signin.setEnabled(false);
                    logincheck.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signin_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 5000){
                    Toast.makeText(SigninActivity.this, "loading....", Toast.LENGTH_SHORT).show();
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                // 이메일 입력 확인
                if( etEmail.getText().toString().length() == 0 ) {
                    Toast.makeText(SigninActivity.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( etPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(SigninActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( etPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(SigninActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(SigninActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }
                getUser task = new getUser();
                task.execute("http://"+HomeActivity.IP_Address+"/getUser.php", etEmail.getText().toString());
            }
        });
    }

    public void onTokenReceived(String auth_token) {
        if (auth_token == null)
            return;
        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        tokeninsta = auth_token;
        getUserInfoByAccessToken(tokeninsta);
    }

    private void getUserInfoByAccessToken(String token) {
        new RequestInstagramAPI().execute();
    }


    public class LoginCallback implements FacebookCallback<LoginResult> {
        // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.

        String name;
        String profile;
        String email;
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.e("Callback :: ", "onSuccess");
            requestMe(loginResult.getAccessToken());
            tokenfab=loginResult.getAccessToken();
            tokenfb=loginResult.getAccessToken().toString();
        }


        // 로그인 창을 닫을 경우, 호출됩니다.
        @Override
        public void onCancel() {
            Log.e("Callback :: ", "onCancel");
        }

        // 로그인 실패 시에 호출됩니다.
        @Override
        public void onError(FacebookException error) {
            Log.e("Callback :: ", "onError : " + error.getMessage());
        }


        // 사용자 정보 요청
        public void requestMe(AccessToken token) {// 페이스북
            GraphRequest graphRequest = GraphRequest.newMeRequest(token,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                fbname = response.getJSONObject().getString("name");
                                fbprofile = String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(300, 300));
                                fbid = String.valueOf(Profile.getCurrentProfile().getId());

                                getUser task = new getUser();
                                task.execute("http://"+HomeActivity.IP_Address+"/getUser.php", fbid, fbname, fbprofile, "facebook");

                                Log.e("페북",fbname);
                                Log.e("페북",fbprofile);
                                Log.e("페북",fbid);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday");
            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                // 로그인 성공 했을때

                GoogleSignInAccount acct = result.getSignInAccount();
                firebaseAuthWithGoogle(acct);

                goprofile = acct.getPhotoUrl().toString();
                goname = acct.getDisplayName();
                gomail = acct.getEmail();
                goid = acct.getId();
                String tokenKey = acct.getServerAuthCode();

                Log.e("GoogleLogin", "personName=" + goname); //구글 이름
                Log.e("GoogleLogin", "personId=" + goid); //구글 아이디
                Log.e("GoogleLogin", "tokenKey=" + tokenKey);

                getUser task = new getUser();
                task.execute("http://"+HomeActivity.IP_Address+"/getUser.php", goid, goname, goprofile, "google");

            } else {
                Log.e("GoogleLogin", "login fail cause=" + result.getStatus().getStatusMessage());
                // 로그인 실패 했을때
            }
        }
    }

    private static class CheckConnect extends Thread {
        private String host;

        public CheckConnect(String host) {
            this.host = host;
        }

        @Override
        public void run() {

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(host).openConnection();
                conn.setRequestProperty("User-Agent", "Android");
                conn.setConnectTimeout(1000);
                conn.connect();
                int responseCode = conn.getResponseCode();
                if (responseCode == 204) iConnected = true;
                else iConnected = false;
            } catch (Exception e) {
                e.printStackTrace();
                iConnected = false;
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        public boolean isSuccess(){
            return iConnected;
        }
    }

    public static boolean isOnline() {
        CheckConnect cc = new CheckConnect(CONNECTION_CONFIRM_CLIENT_URL);
        cc.start();
        try{
            cc.join();
            return cc.isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private class RequestInstagramAPI extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_info_url) + tokeninsta);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    JSONObject jsonData = jsonObject.getJSONObject("data");
                    if (jsonData.has("id")) {
                        //сохранение данных пользователя
                        appPreferences.putString(AppPreferences.USER_ID, jsonData.getString("id")); //인스타그램 아이디
                        appPreferences.putString(AppPreferences.USER_NAME, jsonData.getString("username")); //인스타그램 이름
                        appPreferences.putString(AppPreferences.PROFILE_PIC, jsonData.getString("profile_picture")); //인스타그램 프로필 사진
                        isprofile = jsonData.getString("profile_picture");
                        isname = jsonData.getString("username");
                        //TODO: сохранить еще данные
                        login();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                //Toast toast = Toast.makeText(getApplicationContext(),"Ошибка входа!",Toast.LENGTH_LONG);
                //toast.show();
            }
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent = new Intent(getApplicationContext(), LoginmainActivity.class);
                startActivity(intent);
                break;
            case R.id.facebook:
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(callbackManager, mLoginCallback);
                break;
            case R.id.google_sign:
                btn_login.performClick();
                break;
            case R.id.googlelogin:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);
                break;
            case R.id.message:
                isKakaoLogin();
                break;
            case R.id.twitter:
                authenticationDialog = new AuthenticationDialog(this, this);
                authenticationDialog.setCancelable(true);
                authenticationDialog.show();
                break;
        }
    }

    private void isKakaoLogin() {
        // 카카오 세션을 오픈한다
        mKakaocallback = new SessionCallback();
        com.kakao.auth.Session.getCurrentSession().addCallback(mKakaocallback);
        com.kakao.auth.Session.getCurrentSession().checkAndImplicitOpen();
        com.kakao.auth.Session.getCurrentSession().open(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN, this);
    }

    public void login() {
        isprofile = appPreferences.getString(AppPreferences.PROFILE_PIC); //인스타그램 프로필
        //Picasso.with(this).load().into(pic);
        isid = appPreferences.getString(AppPreferences.USER_ID); //인스타그램 아이디

        Log.e("인스타",isid);
        Log.e("인스타",isname);

        getUser task = new getUser();
        task.execute("http://"+HomeActivity.IP_Address+"/getUser.php", isid, "", isprofile, "instagram");
    }

    protected void KakaorequestMe() {

        UserManagement.getInstance().requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                int ErrorCode = errorResult.getErrorCode();
                int ClientErrorCode = -777;

                if (ErrorCode == ClientErrorCode) {
                 //   Toast.makeText(getApplicationContext(), "카카오톡 서버의 네트워크가 불안정합니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG" , "오류로 카카오로그인 실패 ");
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("TAG" , "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                kaprofile = userProfile.getProfileImagePath();
                kaid = String.valueOf(userProfile.getId());
                kaname = userProfile.getNickname();//카카오톡


                Log.e("success", "prifileUrl:" + kaprofile); //카카오톡 프로필 url
                Log.e("success", "userId:" + kaid); //카카오톡 userid
                Log.e("success", "userName:" + kaname); //카카오톡 이름

                getUser task = new getUser();
                task.execute("http://"+HomeActivity.IP_Address+"/getUser.php", kaid, kaname, kaprofile, "kakao");
            }

            @Override
            public void onNotSignedUp() {
                // 자동가입이 아닐경우 동의창
            }
        });
    }

    class getUser extends AsyncTask<String, Void, String> {
        String id, name, profile, kind;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.e("login-onPostExecute", "response - " + result);

            if (result==null || result.contains("No_results/")) {
                Log.e("onPostExecute", "회원없음");
                Intent intent1 = new Intent(getApplicationContext(),Signin2Activity.class);
                intent1.putExtra("id",id);
                intent1.putExtra("name",name);
                intent1.putExtra("password",etPassword.getText().toString());
                intent1.putExtra("profile",profile);
                startActivity(intent1);
                finish();
            } else {
                if (result.contains("name")) {
                    // 이미 회원이 있을 때
                    Intent intent = new Intent(getApplicationContext(), LoginpopActivity.class);
                    startActivity(intent);
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etEmail.setText("");
                } else {
                    {
                        Log.e("onPostExecute", "회원없음");
                        Intent intent1 = new Intent(getApplicationContext(),Signin2Activity.class);
                        intent1.putExtra("id",id);
                        intent1.putExtra("name",name);
                        intent1.putExtra("password",etPassword.getText().toString());
                        intent1.putExtra("profile",profile);
                        startActivity(intent1);
                        finish();
                    }
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            id = params[1];

            String postParameters = "id="+id;

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
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                String responseStatusMessage = httpURLConnection.getResponseMessage();
                Log.e("response", "POST response Code - " + responseStatusCode);
                Log.e("response", "POST response Message - "+ responseStatusMessage);

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    // 정상적인 응답 데이터
                    Log.e("inputstream: ", "정상적");
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    // error
                    Log.e("inputstream: ", "비정상적: " + httpURLConnection.getErrorStream());
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
                Log.e("ERROR_loginActivity", "InsertDataError ", e);
            }
            return null;
        }
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            // 사용자 정보를 가져옴, 회원가입 미가입시 자동가입 시킴
            KakaorequestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("Fail", "Session CallBack Error > " + exception.getMessage());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                        } else {

                        }
                    }
                });
    }

    class Login extends AsyncTask<String, Void, String> {
        String saveID="", savePW="", getName="";

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.e("onPostExecute", "response - " + result);

            if ((result == null)||(result.contains("No_results/"))){
                // 아이디나 비번을 다시 확인해주세요!
                Intent intent = new Intent(getApplicationContext(), LoginnoActivity.class);
                startActivity(intent);
                etEmail.setText("");
                etPassword.setText("");
            } else {
                // 로그인
                showResult(result);
                SharedPreferences sp_userName = getSharedPreferences("userName", MODE_PRIVATE);
                SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp_userName.edit();
                SharedPreferences.Editor editor2 = sp_userID.edit();
                editor1.putString("userName", getName);
                editor2.putString("userID", saveID);
                editor1.commit();
                editor2.commit();
                Log.e("Login ", getName+"님 로그인");
                Log.e("Login ", saveID+"님 아이디");
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            saveID = params[1];
            savePW = params[2];

            String postParameters = "id="+saveID+"&pw="+savePW;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);;

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                Log.e("loginmainPostParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                // response
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                String responseStatusMessage = httpURLConnection.getResponseMessage();
                Log.e("response", "POST response Code - " + responseStatusCode);
                Log.e("response", "POST response Message - "+ responseStatusMessage);

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    // 정상적인 응답 데이터
                    Log.e("inputstream: ", "정상적");
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    // error
                    Log.e("inputstream: ", "비정상적: " + httpURLConnection.getErrorStream());
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
                Log.e("ERROR_loginmainActivity", "InsertDataError ", e);
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    getName= item.getString("name");
                }
            } catch (JSONException e) {
                Log.d("login-JSON", "showResult : "+e.getMessage());
            }

        }
    }
}
