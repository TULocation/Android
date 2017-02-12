package com.app.skynet.tulocation.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.app.skynet.tulocation.database.APtoDB;
import com.app.skynet.tulocation.list.APList;
import com.app.skynet.tulocation.list.AccessPoint;
import com.app.skynet.tulocation.trilateration.DeviceLocation;
import com.app.skynet.tulocation.trilateration.Trilateration;

import java.util.List;
import java.util.Observable;

public class APScanner extends Observable implements IAPScanner {
    private Context mContext;
    private WifiManager mainWifiObj;
    private BroadcastReceiver receiver;
//    private APList apScanned;
    private APtoDB db;


    public APScanner(Context mContext, APList apList) {
        this.mContext = mContext;
        this.db = new APtoDB(mContext);

//        apScanned = apList;
        init();
    }

    private void init() {

        mainWifiObj = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
//        mainWifiObj.startScan();
        if (receiver == null) receiver = new TestReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    @Override
    public void scan() {
       mainWifiObj.startScan();
    }

    private void notifyScanFinished() {
        setChanged();
        notifyObservers();

    }



    private class TestReceiver extends BroadcastReceiver {
        private AccessPoint point1;
        private AccessPoint point2;
        private AccessPoint point3;

        @Override
        public void onReceive(Context context, Intent intent) {
            db.open();
//            mainWifiObj.startScan();

            db.setFalseAP();
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();

//            apScanned.eraseAllAP();
//            ScanResult penta = getAp("00:6c:0e:00:99:e8", wifiTmp);
//            ScanResult ddr = getAp("00:16:b6:bc:cb:b7", wifiTmp);
//            ScanResult dlin = getAp("28:10:7b:53:c5:6e", wifiTmp);
//            point1 = new AccessPoint("", "", 200, 5, Trilateration.calculateDistance((double) penta.level, penta.frequency));
//            point2 = new AccessPoint("", "", 12, 50, Trilateration.calculateDistance((double) ddr.level, ddr.frequency));
//            point3 = new AccessPoint("", "", 5, 200, Trilateration.calculateDistance((double) dlin.level, dlin.frequency));
//            Trilateration tris = new Trilateration(point1, point2, point3);
//            tri = DeviceLocation.getInstance();
//            tri.setPos(tris.calculateCords().getxCord(), tris.calculateCords().getyCord());
//            tri =
//            tris.calculateCords();
            for (ScanResult scanResult : wifiTmp) {
//                apScanned.addAP(scanResult.SSID, scanResult.BSSID, 0, 0, scanResult.level);
                db.createAccessPoint(scanResult.SSID, scanResult.BSSID, 0, 0, Trilateration.calculateDistance(scanResult.level, scanResult.frequency));
            }
            db.close();
            notifyScanFinished();

        }

        public ScanResult getAp(String bssid, List<ScanResult> wifiTmp) {
            for (ScanResult scanResult : wifiTmp) {
                if (scanResult.BSSID.equals(bssid))
                    return scanResult;
            }
            return null;
        }
    }

    public void unregister() {
        try {

            mContext.unregisterReceiver(receiver);
//            db.close();
        } catch (IllegalArgumentException e) {
        }
    }
}
