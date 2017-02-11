package com.app.skynet.tulocation.list;


import java.io.Serializable;

public class AccessPoint implements Serializable {
    private String BSSID;
    private String mac;
    private double posX;
    private double posY;
    private double signalStrength;
    public AccessPoint(String BSSID, String mac, double posX, double posY, double distance){
        this.BSSID = BSSID;
        this.setMac(mac);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setDistance(distance);
    }
    public AccessPoint(){
        this(null,null,0,0,0);
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
    public double getDistance() {
        return signalStrength;
    }
    public void setDistance(double signalStrength) {
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
