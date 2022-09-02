package com.crazyduo.whatever.basic.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.crazyduo.whatever.R;

/**
 * 动态广播接收
 */
public class ReceiverActivity extends AppCompatActivity {

    BroadcastReceiver receiver;

    private static final String TAG = "ReceiverActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        receiver = new MyBroadcastReceiver();
        // 动态注册广播接受者
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.dynamic.whatever");//要接收的广播
        intentFilter.addAction("com.android.factorymode.action.SECRET_KEY_UPLOADED");//要接收的广播
        registerReceiver(receiver, intentFilter);//注册接收者
        Log.d(TAG, "onCreate: 注册接收者");
    }


    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: "+intent.getAction());
            Toast.makeText(ReceiverActivity.this, "收到广播,action=" + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }
}