package com.crazyduo.whatever.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver extends BroadcastReceiver {
    private static final String TAG="TestReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "收到广播action=" + intent.getAction());
    }
}