package com.example.totallearn.blue;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.totallearn.R;
import com.example.totallearn.databinding.ActivityBlueBinding;

import java.util.Set;

public class BlueActivity extends AppCompatActivity {

    public static final int REQUEST_ENBLE_BT = 200;

    private ListView listView;
    private ArrayAdapter<String> mArrayAdapter;

    // 创建一个接受 ACTION_FOUND 的 BroadcastReceiver
    private final BroadcastReceiver mReceiver = new BroadcastReceiver(){

        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            // 当 Discovery 发现了一个设备
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                // 从 Intent 中获取发现的 BluetoothDevice
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 将名字和地址放入要显示的适配器中
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);
        listView = findViewById(R.id.list_view);

        // 注册这个 BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);

        // 在 onDestroy 中 unRegister
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item);
        findViewById(R.id.bt_blue).setOnClickListener(v->{
            getBlue();
        });
    }

    private void getBlue(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            // 说明此设备不支持蓝牙操作
        }
        // 没有开始蓝牙
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,REQUEST_ENBLE_BT);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0){
            for(BluetoothDevice device:pairedDevices){
                // 把名字和地址取出来添加到适配器中
                mArrayAdapter.add(device.getName()+"\n"+ device.getAddress());
            }
        }

        listView.setAdapter(mArrayAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiver.getDebugUnregister();
    }


}