package com.app.skynet.tulocation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        if (receiver == null) receiver = new TestReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    public void scan(){
        APList apScanned = new APList();
        //--- Scanning magic part

        //---
        globalAPList = apScanned;
    }
    class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            System.out.println(action);
            List<ScanResult> wifiTmp = mainWifiObj.getScanResults();
            wifiList.clear();
            for (ScanResult scanResult : wifiTmp) {
                wifiList.add(scanResult.BSSID + " : " + scanResult.level);
                System.out.println("TEST: is "+scanResult.toString());
            }
        }
    }
}
