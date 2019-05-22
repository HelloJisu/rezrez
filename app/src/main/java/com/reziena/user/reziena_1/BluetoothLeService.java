package com.reziena.user.reziena_1;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_READ;
import static android.bluetooth.BluetoothGattDescriptor.PERMISSION_WRITE;

public class BluetoothLeService extends Service {
    private final static String TAG = BluetoothLeService.class.getSimpleName();

    private static Queue<BluetoothGattDescriptor> descriptorWriteQueue = new LinkedList<>();
    private Queue<BluetoothGattCharacteristic> readCharacteristicQueue = new LinkedList<>();

    private final IBinder mBinder = new LocalBinder();

    public static BluetoothManager mBluetoothManager;
    public static BluetoothAdapter mBluetoothAdapter;
    private static String mBluetoothDeviceAddress;

    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";

    public static Context mContext;
    static Handler mHandler = new Handler();

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        String Tag = "mGattCallback";

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.e("onConnectionStateChange", "init");
            Log.e(Tag, String.valueOf(status));
            Log.e(Tag+ "newState", String.valueOf(newState));

            if (status == BluetoothGatt.GATT_FAILURE) {
                Log.e(Tag, "GATT_FAILURE");
                HomeActivity.isConn = false;
                disconnect();
                return;
            } else if (status != BluetoothGatt.GATT_SUCCESS) {
                HomeActivity.deviceBattery=-1;
                Log.e(Tag, "!GATT_SUCCESS");
                HomeActivity.isConn = false;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
                    }
                });

                if (BluetoothActivity.Bluetooth) {

                    if (!BTOnActivity.BTOn) {
                        Intent intent = new Intent(getApplicationContext(), BTNoActivity.class);
                        intent.putExtra("where", "missing");
                        //intent.putExtra("key", "first");
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                } else {
                    disconnect();

                    if (HomeActivity.disconnect > 3) {
                        HomeActivity.disconnect = 0;
                        if (!BTOnActivity.BTOn) {
                            Intent intent = new Intent(getApplicationContext(), BTNoActivity.class);
                            intent.putExtra("where", "missing");
                            //intent.putExtra("key", "first");
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    } else {
                        //HomeActivity.mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
                        HomeActivity.mBluetoothLeService.initialize();
                        HomeActivity.mBluetoothLeService.connect(mBluetoothDeviceAddress);
                        HomeActivity.isConnecting = true;
                        //HomeActivity.mBluetoothGatt = HomeActivity.device.connectGatt(mContext, false, mGattCallback, BluetoothDevice.TRANSPORT_LE);
                    }
                }
                return;
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {

                SharedPreferences sp_macAdd = getSharedPreferences("macAdd", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp_macAdd.edit();
                editor1.putString("macAdd", mBluetoothDeviceAddress);
                editor1.commit();

                HomeActivity.disconnect = 0;
                //HomeActivity.isConn = true;
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                String getTime = sdf.format(date);
                Log.e("BT Time", "connect!!: "+getTime);
                Log.e(Tag, "Connected to GATT server.-----------------------------------------------");
                broadcastUpdate(ACTION_GATT_CONNECTED);
                HomeActivity.mBluetoothGatt.discoverServices();
                HomeActivity.isConn = true;
                Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                intent.putExtra("key", "home");
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                //startActivity(intent);

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                HomeActivity.isConn = false;
                disconnect();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // 베터리
                    if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
                        HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                    else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                    if (HomeActivity.deviceBattery==-1)
                        HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
                }
            });
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.e("onServicesDiscovered", "init");
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);

                // 여기가 성공


                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 베터리
                        if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
                            HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                        else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                        if (HomeActivity.deviceBattery==-1)
                            HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
                    }
                });

                BluetoothGattCharacteristic characteristic
                        = gatt.getService(HomeActivity.Nordic_UART_Service).getCharacteristic(HomeActivity.Nordic_UART_RX);

                BluetoothGattDescriptor descriptor = new BluetoothGattDescriptor(HomeActivity.Nordic_UART_Descriptor, PERMISSION_READ);
                characteristic.addDescriptor(descriptor);
                characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
            }
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status){
            Log.e("onDescriptorWrite", "init / " + status);
            BluetoothGattCharacteristic characteristic =
                    gatt.getService(HomeActivity.Nordic_UART_Service)
                            .getCharacteristic(HomeActivity.Nordic_UART_RX);

            // 여기가 송신
            //characteristic.setValue(HomeActivity.hexText);

            // byte,
            characteristic.setValue(HomeActivity.data, BluetoothGattCharacteristic.FORMAT_UINT8, 0);
            boolean success = HomeActivity.mBluetoothGatt.writeCharacteristic(characteristic);
            Log.e("sending", "success?"+success);

            boolean readDescriptor = HomeActivity.mBluetoothGatt.readDescriptor(descriptor);
            Log.e("readDescriptor ", "Succees? " + readDescriptor);
        }

        // 여기가 수신
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.e("stringToHex", "호출 ----------------------------------------");
            Log.e("onCharacteristicChanged", stringToHex(characteristic.getStringValue(0)) +"/");
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            readCharacteristicQueue.remove();
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e("onCharacteristicRead", characteristic.getStringValue(0));
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            }
            if(readCharacteristicQueue.size() > 0)
                HomeActivity.mBluetoothGatt.readCharacteristic(readCharacteristicQueue.element());
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if(status != BluetoothGatt.GATT_SUCCESS) gatt.writeCharacteristic(characteristic);
            super.onCharacteristicWrite(gatt, characteristic, status);
        }
    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            final String action = intent.getAction();

            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED))
            {
                final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);

                switch(state){
                    case BluetoothDevice.BOND_BONDING:
                        // Bonding...
                        Log.e("BOND?", "BOND_BONDING");
                        break;

                    case BluetoothDevice.BOND_BONDED:
                        // Bonded...
                        Log.e("BOND?", "BONDED");
                        unregisterReceiver(mReceiver);
                        break;

                    case BluetoothDevice.BOND_NONE:
                        Log.e("BOND?", "BOND_NONE");
                        // Not bonded...
                        break;
                }
            }
        }
    };

    static String receive = "";
    public static Integer stringToHex(String s) {
        receive = "";
        for (int i = 0; i < s.length(); i++) {

            if (i==0) {
                HomeActivity.kind = String.format("%02X", (int) s.charAt(i));

                Log.e("kind", HomeActivity.kind);
            }
            else {
                HomeActivity.receiveResult = s.charAt(i);

                //HomeActivity.receiveResult = Integer.parseInt(hex , 16);
                Log.e("receiveResult", HomeActivity.receiveResult +"");
            }
            receive += String.format("0x%02X", (int) s.charAt(i)) +" ";
            //Log.e("receive Hex", String.format("0x%02X", (int) s.charAt(i)));
            //Log.e("receive Decimal", s.charAt(i) +"");
        }
        //Log.e("stringToHex3", HomeActivity.receiveResult);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "RECEIVE: "+receive, Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("receive Hex", receive + "-------------------------------------------------");
        HomeActivity.receiveData();
        return HomeActivity.receiveResult;
    }

    public static final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.e("mScanCallback", "onServiceConnected / init");
            HomeActivity.mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            HomeActivity.mBluetoothLeService.initialize();
            HomeActivity.mBluetoothLeService.connect(mBluetoothDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("mScanCallback", "onServiceDisconnected / init");
            HomeActivity.mBluetoothLeService = null;
            disconnect();
        }

        public void onBindingDied(ComponentName name) {
            Log.e("onBindingDied", "init");
        }

        public void onNullBinding(ComponentName name) {
            Log.e("onNullBinding", "init");
        }
    };

    public void enableTXNotification() {
        BluetoothGattService RxService = HomeActivity.mBluetoothGatt.getService(HomeActivity.Nordic_UART_Service);
        if (RxService == null) return;
        BluetoothGattCharacteristic TxChar = RxService.getCharacteristic(HomeActivity.Nordic_UART_TX);
        BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(HomeActivity.Nordic_UART_RX);

        if (TxChar!=null && RxChar!=null) {
            HomeActivity.mBluetoothGatt.setCharacteristicNotification(TxChar, true);
            HomeActivity.mBluetoothGatt.setCharacteristicNotification(RxChar, true);
        }

        // 이게 디스크립터
        BluetoothGattDescriptor descriptor = TxChar.getDescriptor(HomeActivity.CLIENT_CHARACTERISTIC_CONFIG);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        HomeActivity.mBluetoothGatt.writeDescriptor(descriptor);

        // 글자 안 깨지게 하기
        BluetoothGattDescriptor descriptorTx
                = new BluetoothGattDescriptor(HomeActivity.CLIENT_CHARACTERISTIC_CONFIG, PERMISSION_READ | PERMISSION_WRITE);
        descriptorTx.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        HomeActivity.mBluetoothGatt.writeDescriptor(descriptorTx);

    }

    public static void writeRXCharacteristic(byte value) {
        BluetoothGattService RxService = HomeActivity.mBluetoothGatt.getService(HomeActivity.Nordic_UART_Service);
        if (RxService == null) return;
        BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(HomeActivity.Nordic_UART_RX);
        if (RxChar == null) return;
        RxChar.setValue(value, BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        HomeActivity.mBluetoothGatt.writeCharacteristic(RxChar);
    }

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        Log.e("broadcastUpdate(,)", "init");

        if (HomeActivity.Nordic_UART_Service.equals(characteristic.getUuid())) {

            int flag = characteristic.getProperties();
            int format = -1;
            if ((flag & 0x01) != 0) {
                format = BluetoothGattCharacteristic.FORMAT_UINT16;
                Log.e(TAG, "Heart rate format UINT16.");
            } else {
                format = BluetoothGattCharacteristic.FORMAT_UINT8;
                Log.e(TAG, "Heart rate format UINT8.");
            }
            final int heartRate = characteristic.getIntValue(format, 1);
            Log.e(TAG, String.format("Received heart rate: %d", heartRate));
            intent.putExtra(EXTRA_DATA, String.valueOf(heartRate));
        } else {
            // For all other profiles, writes the data formatted in HEX.
            int d = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
            byte data = (byte) d;
            /*if (data != null && data.length > 0) {
                Log.e("broadcastUpdate(,)", "second if init");
                final StringBuilder stringBuilder = new StringBuilder(data.length);
                for(byte byteChar : data)
                    stringBuilder.append(String.format("%02X ", byteChar));


                intent.putExtra(EXTRA_DATA, new String(data) + "\n" + stringBuilder.toString());
            }*/
            intent.putExtra(EXTRA_DATA, data);
            //stringToHex(String.valueOf(data));
            // 수신,,,?
            //Log.e("broadcastUpdate(,)", stringToHex(String.valueOf(data)));
        }
        sendBroadcast(intent);
    }

    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            Log.e("LocalBinder", "init");
            mContext = getApplicationContext();
            return BluetoothLeService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("onBind", "init");
        HomeActivity.isBound = true;
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("onUnbind", "init");
        //HomeActivity.isBound = false;
        close();
        Log.e("onUnbind", "fin");
        return super.onUnbind(intent);
    }

    public boolean initialize() {
        Log.e("initialize", "init");
        if (mBluetoothManager == null) {
            Log.e("initialize", "mBluetoothManager / null");
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Log.e("again", "mBluetoothManager / null");
                return false;
            }
        } else
            Log.e("initialize", "mBluetoothManager / not null");
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) return false;

        return true;
    }

    public boolean connect(final String address) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String getTime = sdf.format(date);
        Log.e("BT Time", "startConnect: "+getTime);
        Log.e("connect", "init / " + address);
        if (mBluetoothAdapter == null || address == null) {
            Log.e("mBluetoothAdapter", "null");
            return false;
        } else Log.e("mBluetoothAdapter", "not null");

        /*if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress)
                && HomeActivity.mBluetoothGatt != null) {
            if (HomeActivity.mBluetoothGatt.connect()) {
                Log.e("mBluetoothGatt", ".connect() true");
                return true;
            }
            else {
                Log.e("mBluetoothGatt", " .connect() false");
                return false;
            }
        }*/

        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Log.e("device", "null");
            return false;
        }

        HomeActivity.mBluetoothGatt = device.connectGatt(this, false, mGattCallback, BluetoothDevice.TRANSPORT_LE);
        mBluetoothDeviceAddress = address;

        Log.e("connect", "Fin");
        return true;
    }

    public static void disconnect() {
        Log.e("service", "disconnectGattServer / init");
        HomeActivity.isConnecting = false;
        if (mBluetoothAdapter == null || HomeActivity.mBluetoothGatt == null) return;
        HomeActivity.isConn = false;
        HomeActivity.mBluetoothGatt.disconnect();
        HomeActivity.mBluetoothGatt.close();
        mBluetoothManager = null;
        HomeActivity.disconnect++;
        //HomeActivity.gattCharacteristics = null;
        //HomeActivity.characteristics = null;
        //HomeActivity.mGattCharacteristics = null;
        HomeActivity.deviceBattery = -1;

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // 베터리
                if (HomeActivity.deviceBattery<=HomeActivity.lowBattery)
                    HomeActivity.imageView2.setImageResource(R.drawable.bdev);
                else HomeActivity.imageView2.setImageResource(R.drawable.ellipsehomethera_icon);
                if (HomeActivity.deviceBattery==-1)
                    HomeActivity.imageView2.setImageResource(R.drawable.nondeviceicon);
            }
        });

        Log.e("/disconnect", String.valueOf(HomeActivity.disconnect));
    }

    public void close() {
        if (HomeActivity.mBluetoothGatt == null) return;
        HomeActivity.mBluetoothGatt.close();
        HomeActivity.mBluetoothGatt = null;
    }

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || HomeActivity.mBluetoothGatt == null) return;

        readCharacteristicQueue.add(characteristic);
        if((readCharacteristicQueue.size() == 1) && (descriptorWriteQueue.size() == 0))
            HomeActivity.mBluetoothGatt.readCharacteristic(characteristic);
    }

    public static void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || HomeActivity.mBluetoothGatt == null) return;
        HomeActivity.mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);

        BluetoothGattDescriptor descriptor
                = new BluetoothGattDescriptor(HomeActivity.CLIENT_CHARACTERISTIC_CONFIG, PERMISSION_READ | PERMISSION_WRITE);

        characteristic.addDescriptor(descriptor);
        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);

        descriptorWriteQueue.add(descriptor);

        if(descriptorWriteQueue.size() == 1) HomeActivity.mBluetoothGatt.writeDescriptor(descriptor);
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        if (HomeActivity.mBluetoothGatt == null) return null;
        return HomeActivity.mBluetoothGatt.getServices();
    }
}