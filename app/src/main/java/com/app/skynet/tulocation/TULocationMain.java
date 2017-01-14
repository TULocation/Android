package com.app.skynet.tulocation;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class TULocationMain extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ap_list:
               //HANDLE CLICK
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
