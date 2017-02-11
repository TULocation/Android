package com.app.skynet.tulocation.trilateration;

/**
 * Created by init0 on 10.02.17.
 */

public class DeviceLocation {

    private double xCord;
    private double yCord;

    public DeviceLocation(double xCord, double yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
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
}
