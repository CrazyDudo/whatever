package com.crazyduo.whatever.dagger2;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

@Singleton
class Car {
    private static final String TAG = "Car";
    private Context mContext;


    public Car(Context context) {
        mContext = context;
    }


    public void move() {
        Log.d(TAG, "move: =======");

    }
}
