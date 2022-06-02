package com.example.clientapp.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.clientapp.R;

import java.util.List;

public class BroadcastActivity extends AppCompatActivity {

    public static final String TEST_ACTION = "com.android.factorymode.action.SECRET_KEY_UPLOADED";

    private static final String TAG = "BroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TEST_ACTION);
                //指定接收者包名
                intent.setPackage("com.crazyduo.whatever");

                List<ResolveInfo> resolveInfos = getPackageManager().queryBroadcastReceivers(intent, 0);
                Log.e(TAG, "onClick:size()========" + resolveInfos.size());
                Log.e(TAG, "onClick:getAction()========" + intent.getAction());
                for (int i = 0; i < resolveInfos.size(); i++) {
                    Log.e(TAG, "onClick:packageName======== " + resolveInfos.get(i).activityInfo.packageName);
                }

                sendBroadcast(intent);
            }
        });
    }

}