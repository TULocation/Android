package com.app.skynet.tulocation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.app.skynet.tulocation.list.APList;
import java.util.ArrayList;
import java.util.List;

import static com.app.skynet.tulocation.TULocationMain.globalAPList;

public class APScanner {
    Context mContext;
    WifiManager mainWifiObj;
    BroadcastReceiver receiver;
    ArrayList<String> wifiList;
    public APScanner(Context mContext) {
        this.mContext = mContext;
        wifiList =  new ArrayList<String>();
        mainWifiObj = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        scan();
    }
    public void scan(){
        APList apScanned = new APList();
        //--- Scanning magic part

        //---
        globalAPList = apScanned;
    }
    /*class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();
            wifiList.clear();
            for (ScanResult scanResult : wifiTmp) {
                wifiList.add(scanResult.BSSID + " : " + scanResult.level);
                System.out.println("TEST: is "+scanResult.toString());
            }
        }
    }*/
}
