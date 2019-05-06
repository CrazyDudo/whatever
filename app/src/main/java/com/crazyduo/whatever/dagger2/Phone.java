package com.crazyduo.whatever.dagger2;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Phone {

    private static final String TAG = "Phone";


    @Inject
    public Phone(String phoneName) {

    }


    public void printPhoneName() {
        Log.d(TAG, "pirntPhoneName: =======");
    }
}