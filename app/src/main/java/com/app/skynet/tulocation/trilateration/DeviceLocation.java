package com.app.skynet.tulocation.trilateration;

import android.content.Context;

import com.app.skynet.tulocation.database.APtoDB;
import com.app.skynet.tulocation.list.AccessPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by init0 on 10.02.17.
 */

public class DeviceLocation implements Observer {

    private double xCord = 0;
    private double yCord = 0;
    private APtoDB db;
    private List<AccessPoint> apeki = new ArrayList<>();
    public DeviceLocation(Context context) {
        this.db = new APtoDB(context);
        db.open();
        apeki.clear();
        apeki.addAll(db.getAllComments());
        db.close();
        processPos();
//        db.open();
//        apeki.clear();
//        apeki.addAll(db.getAllComments());
//        db.close();
    }

    private void processPos(){
        Trilateration tri = new Trilateration(apeki.get(0), apeki.get(1), apeki.get(2));
        Double[] tmp = tri.calculateCords();
        xCord = tmp[0];
        yCord = tmp[1];
    }

    public double getyCord() {
        return yCord;
    }

    public double getxCord() {
        return xCord;
    }

    @Override
    public String toString() {
        return "DeviceLocation{" +
                "xCord=" + xCord +
                ", yCord=" + yCord +
                '}';
    }

    @Override
    public void update(Observable observable, Object data) {
        db.open();
        apeki.clear();
        apeki.addAll(db.getAllComments());
        db.close();
        processPos();
    }
}
