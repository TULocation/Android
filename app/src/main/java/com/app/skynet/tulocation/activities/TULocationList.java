package com.app.skynet.tulocation.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.skynet.tulocation.R;
import com.app.skynet.tulocation.database.APtoDB;
import com.app.skynet.tulocation.list.APList;
import com.app.skynet.tulocation.list.AccessPoint;
import com.app.skynet.tulocation.scanner.APScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class TULocationList extends ActionBarActivity implements Observer {
    private ListView list;
    private int chosen = 0;
    private APScanner s;
    private ArrayAdapter adapter;
    private APList apList;
    private APtoDB db;
    private List<AccessPoint> lista = new ArrayList<AccessPoint>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list);
        this.db = new APtoDB(this);
        db.open();

        lista = db.getAllComments();


        list = (ListView) findViewById(R.id.listView);
        initList();
        apList = APList.getInstance();
        s = new APScanner(this, apList);

        s.addObserver(this);
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.dark_list, lista);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        refreshList();
        db.close();
    }
    private void initList() {
        final Intent detailIntent = new Intent (this, TULocationListDetail.class);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                setChosen(position);
                db.open();
                AccessPoint tmp = db.getAP(lista.get(position).getMac());
                Log.i(TULocationList.class.getName(), tmp.getMac() + " " + tmp.getPosX() + " " + tmp.getPosY());
                detailIntent.putExtra("chosenAP", lista.get(position).getMac());
                db.close();
                startActivityForResult(detailIntent,1);
            }
        });
    }
    public void scanButton(View view) {
        s.scan();
    }
    public void loadDBButton(View view) {
        //------------ FOR TESTING PURPOSES
        //apList.addAP("AAAAAAA", "BBBBBBB", 0, 0, 50);
        refreshList();
    }
    private void refreshList(){
        db.open();
        lista.clear();
        lista.addAll(db.getAllComments());
        db.close();
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
    public void onStop() {
        super.onStop();

        s.unregister();
    }
    public void onResume(){
        super.onResume();
        refreshList();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                lista.set(chosen,(AccessPoint) data.getSerializableExtra("editedValue"));
            }
        }
    }
}
