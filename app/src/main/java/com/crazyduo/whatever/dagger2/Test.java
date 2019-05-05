package com.crazyduo.whatever.dagger2;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Test {
    private static final String TAG = "Test";

    private Context mContext;


    @Inject
    public Test(Context context) {

        mContext = context;

    }

    public void move() {
        Log.d(TAG, "move: =======");

    }
}
