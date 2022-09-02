package com.example.clientapp.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.clientapp.R;

import java.util.List;

/**
 * 发送广播
 */
public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TEST_ACTION = "com.android.factorymode.action.SECRET_KEY_UPLOADED";

    private static final String TAG = "BroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_send_dynamic).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:

                Intent intent = new Intent();
                intent.setAction(TEST_ACTION);
                //指定接收者包名
//                intent.setPackage("com.crazyduo.whatever");
                //接收者app停止中也可以收到
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

//                List<ResolveInfo> resolveInfos = getPackageManager().queryBroadcastReceivers(intent, 0);
//                Log.e(TAG, "onClick:size()========" + resolveInfos.size());
//                Log.e(TAG, "onClick:getAction()========" + intent.getAction());
//                for (int i = 0; i < resolveInfos.size(); i++) {
//                    Log.e(TAG, "onClick:packageName======== " + resolveInfos.get(i).activityInfo.packageName);
//                    intent.setPackage(resolveInfos.get(i).activityInfo.packageName);
//                    sendBroadcast(intent);
//                }

                sendImplicitBroadcast(BroadcastActivity.this, intent);
                break;

            case R.id.btn_send_dynamic:
                //发送动态广播
//                Intent intentDynamic = new Intent("action.dynamic.whatever");
                Intent intentDynamic = new Intent(TEST_ACTION);
                intentDynamic.putExtra("message", "pay");
                intentDynamic.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
                intentDynamic.addFlags(0x01000000); //Intent.FLAG_RECEIVER_INCLUDE_BACKGROUND
                intentDynamic.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intentDynamic);
                Toast.makeText(this, "send dynamic broadcast", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 发送静态广播
     *
     * @param context
     * @param i
     */
    private void sendImplicitBroadcast(Context context, Intent i) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> matches = pm.queryBroadcastReceivers(i, 0);

        if (matches == null || matches.size() == 0) {
            Toast.makeText(this, "未查到广播接收者", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "查询公钥上送结果广播接受者失败");
        }
        for (ResolveInfo resolveInfo : matches) {
            Intent intent = new Intent(i);
            intent.setPackage(resolveInfo.activityInfo.applicationInfo.packageName);
//            intent.setAction("com.android.factorymode.action.SECRET_KEY_UPLOADED");
            context.sendBroadcast(intent);
            Log.e(TAG, "handleUploadResult---> sendBroadcast action = " + intent.getAction());
        }
    }


}