package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BTNoActivity extends AppCompatActivity {

    TextView header_txt, content, skip, okay;
    ImageView image;
    LinearLayout imageButton;
    public static Activity btNoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
        btNoActivity = BTNoActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btno);

        header_txt = findViewById(R.id.header_txt);
        content = findViewById(R.id.content);
        image = findViewById(R.id.image);
        skip = findViewById(R.id.skip);
        okay = findViewById(R.id.okay);
        imageButton = findViewById(R.id.imageButton);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;

        lpWindow.copyFrom(getWindow().getAttributes());
        lpWindow.width = 1000;
        lpWindow.height = 1100;

        getWindow().setAttributes(lpWindow);

        Log.e("BTNoActivity", "isConnecting: "+HomeActivity.isConnecting);

        Intent intent = getIntent();
        if (intent!=null) {
            String where = intent.getExtras().getString("where");

            if (where.equals("missing")) {
                header_txt.setText("Disconnected");
                content.setText("Device is missing! \n\n Do you want to connect \n\n the device?");
                image.setImageResource(R.drawable.nodev);
                okay.setText("Yes");

            } else if (where.equals("no")) {
                header_txt.setText("Bluetooth");
                content.setText("Cannot find any device around");
                image.setImageResource(R.drawable.qdev);
                okay.setText("Try again");
            }
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.skip:
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                    case R.id.okay:
                        // try again
                        HomeActivity.disconnect=4;
                        homeactivity.dashback.setImageResource(0);
                        Intent intent1 = new Intent(getApplicationContext(), BTOnActivity.class);
                        intent1.putExtra("key", "again");
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.imageButton:
                        homeactivity.dashback.setImageResource(0);
                        finish();
                        break;
                }
            }
        };

        skip.setOnClickListener(onClickListener);
        okay.setOnClickListener(onClickListener);
        imageButton.setOnClickListener(onClickListener);

        if (HomeActivity.isConn) {
            Log.e("BTNoActivity", "success!!!");
            finish();
        }
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
