package com.app.skynet.tulocation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.app.skynet.tulocation.list.APList;
import java.util.List;

import static com.app.skynet.tulocation.TULocationMain.globalAPList;

public class APScanner {
    Context mContext;
    WifiManager mainWifiObj;
    BroadcastReceiver receiver;
    APList apScanned;
    public APScanner(Context mContext) {
        this.mContext = mContext;
        apScanned =  new APList();
        mainWifiObj = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        if (receiver == null) receiver = new TestReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    public void scan(){
        mainWifiObj.startScan();

    }
    class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            apScanned.eraseAllAP();
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();
            for (ScanResult scanResult : wifiTmp) {
                apScanned.addAP(scanResult.BSSID, scanResult.SSID,0,0,scanResult.level);
            }
            apScanned.addAP("testowa", "testowymak", 10, 15, 20.0);
            globalAPList = apScanned;
            System.out.println("SSSSS");
        }

    }
}
