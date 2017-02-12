package com.app.skynet.tulocation.trilateration;

import android.util.Log;


import com.app.skynet.tulocation.list.AccessPoint;

/**
 * Created by init0 on 10.02.17.
 */

public class Trilateration {

    private AccessPoint firstNode;
    private AccessPoint secondNode;
    private AccessPoint thirdNode;

    public Trilateration(AccessPoint firstNode, AccessPoint secondNode, AccessPoint thirdNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.thirdNode = thirdNode;
    }


    public static double calculateDistance(double levelInDb, double freqInMHz)    {
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(levelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    public Double[] calculateCords(){
        double fC1 = this.firstNode.getPosX() * this.firstNode.getPosX();
        double fC2 = this.firstNode.getPosY() * this.firstNode.getPosY();
        double sC1 = this.secondNode.getPosX() * this.secondNode.getPosX();
        double sC2 = this.secondNode.getPosY() * this.secondNode.getPosY();
        double tC1 = this.thirdNode.getPosX() * this.thirdNode.getPosX();
        double tC2 = this.thirdNode.getPosY() * this.thirdNode.getPosY();

        double distF = this.firstNode.getDistance() * this.firstNode.getDistance();
        double distS = this.secondNode.getDistance() * this.secondNode.getDistance();
        double distT = this.thirdNode.getDistance() * this.thirdNode.getDistance();

        double diff21 = (this.secondNode.getPosX() - this.firstNode.getPosX());
        double diff13 = (this.firstNode.getPosX() - this.thirdNode.getPosX());
        double diff32 = (this.thirdNode.getPosX() - this.secondNode.getPosX());
        double square3 = (tC1 + tC2 - distT);
        double square2 = (sC1 + sC2 - distS);
        double square1 = (fC1 + fC2 - distF);

        double numerator1 = diff21 * square3 + diff13 * square2 + diff32 * square1;
        double denominator1 = 2 * (this.thirdNode.getPosY() * diff21 + this.secondNode.getPosY() * diff13 + this.firstNode.getPosY() * diff32);
        double y = numerator1/denominator1;

        double diff12y = (this.firstNode.getPosY() - this.secondNode.getPosY());
        double diff12x = (this.firstNode.getPosX() - this.secondNode.getPosX());
        double numerator2 = distS - distF + fC1 - sC1 + fC2 - sC2 - 2 * diff12y * y;
        double denumerator2 = 2 * diff12x;
        double x = numerator2/denumerator2;
        Log.i(Trilateration.class.getName(),"W TRI " + x + " " + y);
      return new Double[]{ x,y};
//        dev.setPos(x,y);
    }

}
