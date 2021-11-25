package com.example.clientapp.preferences;

/**
 * Created by zhangliangming on 2018-04-29.
 */

public class TestPreferencesProvider extends PreferencesProvider {
    @Override
    public String getAuthorities() {
        return ".preferences.TestPreferencesProvider";
    }
}
