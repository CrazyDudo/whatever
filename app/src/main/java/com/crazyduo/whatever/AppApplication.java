package com.crazyduo.whatever;

import android.app.Application;

import com.crazyduo.whatever.greendao.GreenDaoManager;

public class AppApplication extends Application {
    private static Application mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        init();
    }

    private void init() {
        //GreenDao的初始化
        GreenDaoManager.getInstance();
    }
    public static Application getContext() {
        return mApplication;
    }
}
