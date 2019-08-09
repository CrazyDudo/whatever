package com.crazyduo.whatever.statistics;

import android.graphics.drawable.Drawable;

class TrafficInfo {
    String Packname;
    Drawable Icon;
    String Appname;
    Long Rx;
    Long  Tx;

    public String getPackname() {
        return Packname;
    }

    public void setPackname(String packname) {
        Packname = packname;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }

    public String getAppname() {
        return Appname;
    }

    public void setAppname(String appname) {
        Appname = appname;
    }

    public Long getRx() {
        return Rx;
    }

    public void setRx(Long rx) {
        Rx = rx;
    }

    public Long getTx() {
        return Tx;
    }

    public void setTx(Long tx) {
        Tx = tx;
    }
}
