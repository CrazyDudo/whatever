package com.crazyduo.whatever.dagger2;

import android.util.Log;

import javax.inject.Inject;




class Car {
    private static final String TAG = "Car";
    @Inject
    public Car() {
    }

    public void move() {
        Log.d(TAG, "move: ");
    }
}
