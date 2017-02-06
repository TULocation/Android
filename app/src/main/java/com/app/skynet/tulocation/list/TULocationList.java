package com.app.skynet.tulocation.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.skynet.tulocation.R;

import java.util.ArrayList;

public class TULocationList extends ActionBarActivity {
    private ListView list;
    private int chosen = 0;
    private ArrayAdapter adapter;
    public static APList globalAPList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list);
        list = (ListView) findViewById(R.id.listView);
        globalAPList = new APList();
        initList();
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, globalAPList.getWifiList());
        list.setAdapter(adapter);
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
                detailIntent.putExtra("chosenAP", globalAPList.apList.get(position));
                startActivity(detailIntent);
            }
        });
    }
    public void scanButton(View view) {
        globalAPList.addAP("huehuenazwa", "huehueMAK", 10, 15, 20.0);
        refreshList();
    }
    public void loadDBButton(View view) {
        //HANDLE CLICK
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
}
