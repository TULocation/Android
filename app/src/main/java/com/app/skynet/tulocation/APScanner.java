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

public class APScanner implements IAPScanner{
    final Object signal = new Object();
    private Context mContext;
    private WifiManager mainWifiObj;
    private BroadcastReceiver receiver;
    private APList apScanned;
    public APScanner(Context mContext) {
        this.mContext = mContext;
        init();
    }
    private void init(){
        apScanned =  new APList();
        mainWifiObj = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        if (receiver == null) receiver = new TestReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    @Override
    public APList scan(){
        mainWifiObj.startScan();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return apScanned;
    }
    private class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            apScanned.eraseAllAP();
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();
            for (ScanResult scanResult : wifiTmp) {
                apScanned.addAP(scanResult.SSID, scanResult.BSSID,0,0,scanResult.level);
            }

        }
    }
}
