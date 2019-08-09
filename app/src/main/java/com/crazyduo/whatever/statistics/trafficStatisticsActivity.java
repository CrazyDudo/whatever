package com.crazyduo.whatever.statistics;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crazyduo.whatever.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 流量统计
 */
public class trafficStatisticsActivity extends AppCompatActivity {

    @BindView(R.id.btn_data)
    Button btnData;
    @BindView(R.id.btn_battery)
    Button btnBattery;
    @BindView(R.id.edt_package_name)
    EditText edtPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_statistics);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_data, R.id.btn_battery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_data:
//                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
//                startActivity(intent);

                Logger.d(getMobleTraffic());

//                Logger.d(TrafficStats.getUidRxBytes(getAppUid()));
//                Logger.d(TrafficStats.getUidTxBytes(getAppUid()));


                break;
            case R.id.btn_battery:

                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                startActivity(intent);
                break;
        }
    }


    /**
     * 获取应用使用的流量
     *
     * @return
     */
    private String getMobleTraffic() {
        long mobleTraffic = 0L;
        int uid = getAppUid();
        if (uid == -1) {
            return "0.00M";
        } else {
            mobleTraffic = (TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED) ? 0
                    : TrafficStats.getUidRxBytes(uid);
            mobleTraffic += (TrafficStats.getUidTxBytes(uid) == TrafficStats.UNSUPPORTED) ? 0
                    : TrafficStats.getUidTxBytes(uid);
            String traffic = mobleTraffic / (float) (1024 * 1024) + "M";
            Logger.d(mobleTraffic);
            return traffic.substring(0, traffic.indexOf(".") + 3) + "M";
        }

    }

    /**
     * get uid by package name
     *
     * @return
     */
    private int getAppUid() {
        int uid;
        String packageName;
        if (edtPackageName.getText().toString().isEmpty()) {

            packageName = "com.quark.browser";
        } else {
            packageName = edtPackageName.getText().toString().trim();
        }
//        String packageName = "com.example.android_architecture_sample";
        try {
//            ApplicationInfo info = this.getPackageManager().getApplicationInfo(this.getPackageName(), 0);
            ApplicationInfo info = this.getPackageManager().getApplicationInfo(packageName, 0);
            uid = info.uid;
        } catch (
                PackageManager.NameNotFoundException e) {
            uid = -1;
        }
        Logger.d("UID = " + uid);
        return uid;
    }


    /**
     * 返回所有的有互联网访问权限的应用程序的流量信息。
     * TrafficInfo 为一个Bean 模型类。使用的时候可以自定义一个、。
     *
     * @return
     */
    public List<TrafficInfo> getTrafficInfo(Context context) {
        //获取到配置权限信息的应用程序
        PackageManager pms = context.getPackageManager();
        List<PackageInfo> packinfos = pms
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
        //存放具有Internet权限信息的应用
        List<TrafficInfo> trafficInfos = new ArrayList<TrafficInfo>();
        for (PackageInfo packinfo : packinfos) {
            //获取该应用的所有权限信息
            String[] permissions = packinfo.requestedPermissions;
            if (permissions != null && permissions.length > 0) {
                for (String permission : permissions) {
                    //筛选出具有Internet权限的应用程序
                    if ("android.permission.INTERNET".equals(permission)) {
                        //用于封装具有Internet权限的应用程序信息
                        TrafficInfo trafficInfo = new TrafficInfo();
                        //封装应用信息
                        trafficInfo.setPackname(packinfo.packageName);
                        trafficInfo.setIcon(packinfo.applicationInfo.loadIcon(pms));
                        trafficInfo.setAppname(packinfo.applicationInfo.loadLabel(pms).toString());
                        //获取到应用的uid（user id）
                        int uid = packinfo.applicationInfo.uid;
                        //TrafficStats对象通过应用的uid来获取应用的下载、上传流量信息


                        //发送的 上传的流量byte
                        trafficInfo.setRx(TrafficStats.getUidRxBytes(uid));
                        //下载的流量 byte
                        trafficInfo.setTx(TrafficStats.getUidTxBytes(uid));
                        trafficInfos.add(trafficInfo);
                        trafficInfo = null;
                        break;
                    }
                }
            }
        }
        return trafficInfos;
    }

}
