package com.app.skynet.tulocation.list;

import java.util.ArrayList;

public class APList implements IAPList{
    private ArrayList<AccessPoint> apList;
    private ArrayList<String> wifiList;
    public APList(){
        apList =  new ArrayList<AccessPoint>();
        wifiList = new ArrayList<String>();
    }
    public void addAP(String BSSID, String mac, double posX, double posY, double signalStrength) {
        apList.add(new AccessPoint(BSSID, mac, posX, posY, signalStrength));
        wifiList.add(apList.get(apList.size() - 1).toString());
    }
    public void removeAP(int index) {
        apList.remove(index);
        wifiList.remove(index);
    }
    public void eraseAllAP() {
        apList.clear();
        wifiList.clear();
    }
    @Override
    public void setAP(AccessPoint ap, int index) {
        apList.set(index, ap);
        wifiList.set(index, ap.toString());
    }
    public AccessPoint getAP(int index) {
        return apList.get(index);
    }
    public ArrayList<String> getWifiList(){
        return wifiList;
    }
}
