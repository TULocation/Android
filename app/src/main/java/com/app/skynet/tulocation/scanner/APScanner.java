package com.app.skynet.tulocation.scanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.app.skynet.tulocation.list.APList;
import java.util.List;
import java.util.Observable;

public class APScanner extends Observable implements IAPScanner{
    private Context mContext;
    private WifiManager mainWifiObj;
    private BroadcastReceiver receiver;
    private APList apScanned;
    public APScanner(Context mContext, APList apList) {
        this.mContext = mContext;
        apScanned = apList;
        init();
    }
    private void init(){
        mainWifiObj = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        if (receiver == null) receiver = new TestReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    @Override
    public void scan(){
        mainWifiObj.startScan();
    }
    private void notifyScanFinished() {
        setChanged();
        notifyObservers();
    }
    private class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mainWifiObj.startScan();
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();
            apScanned.eraseAllAP();
            for (ScanResult scanResult : wifiTmp) {
                apScanned.addAP(scanResult.SSID, scanResult.BSSID,0,0,scanResult.level);
            }
            //------------ FOR TESTING PURPOSES

            if(wifiTmp.isEmpty()) {
                apScanned.addAP("List", "empty", 0, 0, 50);
            }
            else {
                apScanned.addAP("List", "not empty", 0, 0, 50);
            }

            //------------ FOR TESTING PURPOSES
            notifyScanFinished();
        }
    }
    public void unregister(){
        try {
            mContext.unregisterReceiver(receiver);
        }
        catch(IllegalArgumentException e) {}
    }
}
