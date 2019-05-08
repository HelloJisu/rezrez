package com.reziena.user.reziena_1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class bReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        String btTag = "이것은 bReceiver";

        if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            Log.e(btTag,"Now Action?:: " + action);

            if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
                HomeActivity.imageView2.setImageResource(R.drawable.bdev);
            else
                HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
        }
        else if(BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(intent.getAction())) {
            if (!HomeActivity.isConn) {
                try {
                    HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
                } catch (Exception e) {}
                HomeActivity.isConn = false;
            }
        }
        else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(intent.getAction()))
            Log.e(btTag, "Now Action?:: " + action);
        else if (BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction()))
            Log.e(btTag, "Now Action?:: " + action);
    }
}