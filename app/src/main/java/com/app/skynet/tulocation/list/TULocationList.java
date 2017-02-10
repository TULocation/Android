package com.app.skynet.tulocation.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.skynet.tulocation.APScanner;
import com.app.skynet.tulocation.IAPScanner;
import com.app.skynet.tulocation.R;

import java.util.Observable;
import java.util.Observer;


public class TULocationList extends AppCompatActivity implements Observer {
    private ListView list;
    private int chosen = 0;
    private APScanner s;
    private ArrayAdapter adapter;
    private APList apList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list);
        list = (ListView) findViewById(R.id.listView);
        initList();
        apList = new APList();
        s = new APScanner(this, apList);
        s.addObserver(this);
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, apList.getWifiList());
        list.setAdapter(adapter);
        refreshList();
    }
    private void initList() {
        final Intent detailIntent = new Intent (this, com.app.skynet.tulocation.list.TULocationListDetail.class);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                setChosen(position);
                setContentView(R.layout.activity_tulocation_list_details);
                detailIntent.putExtra("chosenAP", apList.getAP(position));
                startActivity(detailIntent);
            }
        });
    }
    public void scanButton(View view) {
        s.scan();
    }
    public void loadDBButton(View view) {
        refreshList();
    }
    private void refreshList(){
        adapter.notifyDataSetChanged();
    }
    public int getChosen() {
        return chosen;
    }
    public void setChosen(int chosen) {
        this.chosen = chosen;
    }
    @Override
    public void update(Observable observable, Object o) {
        refreshList();
    }
    @Override
    public void onPause() {

    }
}
