package com.app.skynet.tulocation.list;


import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.skynet.tulocation.R;
import com.app.skynet.tulocation.TULocationMain;

import java.util.ArrayList;

public class TULocationList extends ActionBarActivity implements List {
    ListView list;
    ArrayAdapter adapter;
    ArrayList<String> wifiList;
    @Override
    public AccessPoint getElement(int index) {
        return apList.get(index);
    }
    public void dojebNowegoAccessPointa(String BSSID, String mac, double posX, double posY, double signalStrength) {
        apList.add(new AccessPoint(BSSID, mac, posX, posY, signalStrength));
        wifiList.add(apList.get(apList.size() - 1).toString());
        adapter.notifyDataSetChanged();
    }
    public void wyjebAccessPointa(int index) {
        apList.remove(index);
        adapter.notifyDataSetChanged();
    }
    public void wyjebWszystkieAccessPointy() {
        apList.clear();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list);
        wifiList =  new ArrayList<String>();
        list = (ListView) findViewById(R.id.listView);
        initList();
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, wifiList);
        list.setAdapter(adapter);
    }
    private void initList() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                System.out.println(position);
            }
        });
    }
    public void jebnijSkana(View view) {
        dojebNowegoAccessPointa("huehuenazwa", "huehueMAK", 10, 15, 20.0);
        adapter.notifyDataSetChanged();
    }
    public void zajebAPzBazy(View view) {
        //HANDLE CLICK
    }
}