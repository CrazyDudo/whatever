package com.crazyduo.whatever.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyduo.whatever.R;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_SD_WRITE_CODE = 1;
    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        check();
    }

    private void check() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //如果有权限,正常流程
        } else {
            //如果没有权限,现在申请
            EasyPermissions.requestPermissions(this,
                    "写文件需要写文sd卡权限",
                    REQUEST_PERMISSION_SD_WRITE_CODE,
                    perms);
        }

    }


}
