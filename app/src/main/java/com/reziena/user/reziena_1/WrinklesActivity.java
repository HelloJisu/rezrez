package com.reziena.user.reziena_1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reziena.user.reziena_1.MainActivity;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WrinklesActivity extends AppCompatActivity {

    String permissionstring;
    LinearLayout imageButton;
    HomeActivity homeactivity = (HomeActivity) HomeActivity.homeactivity;
    TextView ready;
    static final int REQUEST_CAMERA = 1;
    static final int  REQUEST_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrinkles);


        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        imageButton = findViewById(R.id.imageButton);
        ready = findViewById(R.id.okay);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay:
                        int permissionCheck = ContextCompat.checkSelfPermission(WrinklesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        int permissionCamera = ContextCompat.checkSelfPermission(WrinklesActivity.this, Manifest.permission.CAMERA);

                        if (permissionCheck == PackageManager.PERMISSION_GRANTED && permissionCamera == PackageManager.PERMISSION_GRANTED) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            requestPermission();
                        }
                        break;

                    case R.id.imageButton:
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                }

            }
        };
        imageButton.setOnClickListener(onClickListener);
        ready.setOnClickListener(onClickListener);
    }



    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) WrinklesActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        homeactivity.dashback.setImageResource(0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){//동의 했을 경우
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{//거부했을 경우
                    Toast toast=Toast.makeText(this,"기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }
}
