package com.app.skynet.tulocation.list;


import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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
    public void wyjebAccessPointa(int index) {
        apList.remove(index);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list);
        wifiList =  new ArrayList<String>();
        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, wifiList);
        list.setAdapter(adapter);
    }
    public void jeb(View view) {
        apList.add(new AccessPoint("huehuenazwa", "huehueMAK", 10, 15, 20.0));
        for(AccessPoint i:apList) {
            wifiList.add(i.toString());
            System.out.println("TEST: is "+wifiList.toString());
        }
        adapter.notifyDataSetChanged();
    }
}
