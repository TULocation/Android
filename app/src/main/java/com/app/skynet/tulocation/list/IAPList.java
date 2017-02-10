package com.app.skynet.tulocation.list;

import java.util.ArrayList;

public interface IAPList {
    public void addAP(String BSSID, String mac, double posX, double posY, double signalStrength);
    public void removeAP(int index);
    public void eraseAllAP();
    public AccessPoint getAP(int index);
    public ArrayList<String> getWifiList();
}
