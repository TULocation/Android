package com.app.skynet.tulocation.list;

import java.util.ArrayList;

public class APList{
    public ArrayList<AccessPoint> apList;
    private ArrayList<String> wifiList;
    public APList(){
        apList =  new ArrayList<AccessPoint>();
        wifiList = new ArrayList<String>();
    }
    public AccessPoint getElement(int index) {
        return apList.get(index);
    }
    public void dojebNowegoAccessPointa(String BSSID, String mac, double posX, double posY, double signalStrength) {
        apList.add(new AccessPoint(BSSID, mac, posX, posY, signalStrength));
        getWifiList().add(apList.get(apList.size() - 1).toString());
    }
    public void wyjebAccessPointa(int index) {
        apList.remove(index);
    }
    public void wyjebWszystkieAccessPointy() {
        apList.clear();
    }
    public ArrayList<String> getWifiList() {
        return wifiList;
    }
    public void setWifiList(ArrayList<String> wifiList) {
        this.wifiList = wifiList;
    }
}
