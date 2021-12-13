package com.crazyduo.whatever;

import android.app.Application;
import android.content.Context;

import com.crazyduo.whatever.greendao.GreenDaoManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class AppApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        init();
    }

    private void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        //GreenDao的初始化
        GreenDaoManager.getInstance();
    }

    public static Context getContext() {
        return context;
    }
}
