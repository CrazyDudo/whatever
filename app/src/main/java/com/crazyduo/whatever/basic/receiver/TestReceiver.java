package com.crazyduo.whatever.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 接收静态广播
 */
public class TestReceiver extends BroadcastReceiver {
    private static final String TAG="TestReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "收到广播action======" + intent.getAction());
        Toast.makeText(context, "收到广播action======" + intent.getAction(), Toast.LENGTH_LONG).show();
    }
}