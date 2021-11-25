package com.crazyduo.whatever.basic;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyduo.whatever.R;

public class MemoryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memery_info);

        getMaxMemoryInfo();
        getMemoryInfo();
    }


    private void getMemoryInfo() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);
        Log.e("Memory", "系统总内存:" + (info.totalMem / (1024 * 1024)) + "M");
        Log.e("Memory", "系统剩余内存:" + (info.availMem / (1024 * 1024)) + "M");
        Log.e("Memory", "系统是否处于低内存运行：" + info.lowMemory);
        Log.e("Memory", "系统剩余内存低于" + (info.threshold / (1024 * 1024)) + "M时为低内存运行");
    }

    private void getMaxMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.e("MaxMemory:", Long.toString(maxMemory / (1024 * 1024)));
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.e("MemoryClass:", Long.toString(activityManager.getMemoryClass()));
        Log.e("LargeMemoryClass:", Long.toString(activityManager.getLargeMemoryClass()));
    }
}