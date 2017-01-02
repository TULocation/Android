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

import java.util.ArrayList;
import java.util.List;

public class TULocationMain extends ActionBarActivity {
    WifiManager mainWifiObj;
    BroadcastReceiver receiver;
    ListView list;
    ArrayAdapter adapter;
    ArrayList<String> wifiList;
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        System.out.println("TRIGED BY USER");
        mainWifiObj.startScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_main);
        wifiList =  new ArrayList<String>();
        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, wifiList);
        list.setAdapter(adapter);
        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        if (receiver == null)
            receiver = new TestReceiver();

        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
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
            adapter.notifyDataSetChanged();
        }
    }

}
