package com.crazyduo.whatever.di;

import android.util.Log;

public class Engine {
    private static final String TAG = "Engine";

    String type;

    public Engine(String type) {
        this.type = type;
    }

    public void run() {
        Log.d(TAG, "engine run =========" + type);

    }
}
