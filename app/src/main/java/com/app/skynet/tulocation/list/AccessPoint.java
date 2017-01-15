package com.app.skynet.tulocation.list;


import java.io.Serializable;

public class AccessPoint implements Serializable {
    private String BSSID;
    private String mac;
    private double posX;
    private double posY;
    private double signalStrength;
    public AccessPoint(String BSSID, String mac, double posX, double posY, double signalStrength){
        this.BSSID = BSSID;
        this.setMac(mac);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSignalStrength(signalStrength);
    }
    public AccessPoint(){
        this(null,null,0,0,0);
    }

    public void replaceSettings(AccessPoint ap) {
        this.setBSSID(ap.BSSID);
        this.setMac(ap.mac);
        this.setPosX(ap.posX);
        this.setPosY(ap.posY);
        this.setSignalStrength(ap.signalStrength);
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(double signalStrength) {
        this.signalStrength = signalStrength;
    }
    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }
    @Override
    public String toString() {
        return this.BSSID + " [" +this.mac + "]";
    }

}
