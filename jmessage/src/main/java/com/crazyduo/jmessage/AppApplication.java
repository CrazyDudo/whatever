package com.crazyduo.jmessage;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
public class AppApplication extends Application {
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initJpush();
        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    private void initJpush() {
        JPushInterface.setDebugMode(true);//如果时正式版就改成false
        JPushInterface.init(this);
        JMessageClient.init(this);
        JMessageClient.registerEventReceiver( new GlobalEventListener(this));
    }

    public static Application getContext() {
        return mApplication;
    }
}
