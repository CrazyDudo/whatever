package com.crazyduo.whatever.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {

    /**
     * 默认点击吐司
     * @param context
     */
    public static void toast(Context context){
        Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义吐司内容
     * @param context
     * @param str
     */
    public static void toast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }



}
