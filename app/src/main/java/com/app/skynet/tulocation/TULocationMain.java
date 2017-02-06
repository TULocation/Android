package com.app.skynet.tulocation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.skynet.tulocation.list.APList;


public class TULocationMain extends ActionBarActivity {
    private APScanner apScanner;
    private APList globalAPList;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        System.out.println("TRIGED BY USER");
    }
    public void goToActivityList (View view){
        Intent intent = new Intent (this, com.app.skynet.tulocation.list.TULocationList.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        apScanner = new APScanner(this);
        globalAPList = new APList();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ap_list:
                Intent intent = new Intent (this, com.app.skynet.tulocation.list.TULocationList.class);
                startActivity(intent);
                return true;
            case R.id.action_map_view:
                //HANDLE CLICK
                return true;
            case R.id.action_app_settings:
                //HANDLE CLICK
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
