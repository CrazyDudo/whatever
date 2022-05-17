package com.crazyduo.whatever.wifi;

import android.app.Application;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyduo.whatever.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiTestActivity extends AppCompatActivity {

    private static final String TAG = "WifiTestActivity";
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_test);
        ButterKnife.bind(this);

        String wifiMacAddress = getConnectedWifiMacAddress(this);
        tvText.append(wifiMacAddress+"");
        Log.d(TAG, "onCreate: wifiMacAddress: " + wifiMacAddress);
    }

    public static String getConnectedWifiMacAddress(Context context) {
        String connectedWifiMacAddress = null;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiList;

        if (wifiManager != null) {
            wifiList = wifiManager.getScanResults();
            WifiInfo info = wifiManager.getConnectionInfo();
            if (wifiList != null && info != null) {
                for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult result = wifiList.get(i);
                    if (info.getBSSID().equals(result.BSSID)) {
                        connectedWifiMacAddress = result.BSSID;
                    }
                }
            }
        }
        return connectedWifiMacAddress;
    }


//    // Declaring Wi-Fi manager
//    val wifi = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
//
//    // On button Click
//        btn.setOnClickListener {
//
//        // wifi.isWifiEnabled is a boolean, is Wi-Fi is ON,
//        // it switches down and vice-versa
//        wifi.isWifiEnabled = !wifi.isWifiEnabled
//
//        // For displaying Wi-fi status in TextView
//        if (!wifi.isWifiEnabled) {
//            textView.text = "Wifi is ON"
//        } else {
//            textView.text = "Wifi is OFF"
//        }
//    }
//


    public void switchWifi(View view) {
        WifiManager wifiManager= (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
        Toast.makeText(this, "打开/关闭WIFI...", Toast.LENGTH_SHORT).show();
    }
}
