package com.app.skynet.tulocation.list;
import java.util.ArrayList;

public interface List {
    public ArrayList<AccessPoint> apList = new ArrayList<AccessPoint>();
    public AccessPoint getElement(int index);
    public void wyjebAccessPointa(int index);
}
