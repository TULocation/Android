package com.app.skynet.tulocation.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.skynet.tulocation.R;
import com.app.skynet.tulocation.canvas.CanvasView;
import com.app.skynet.tulocation.canvas.CustomShape;
import com.app.skynet.tulocation.list.APList;
import com.app.skynet.tulocation.scanner.APScanner;
import com.app.skynet.tulocation.trilateration.DeviceLocation;


public class TULocationMain extends ActionBarActivity {

    private CustomShape shape;
    private APScanner apScanner;
    private APList globalAPList = APList.getInstance();
    private DeviceLocation dev;
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        System.out.println("TRIGED BY USER");
        apScanner.scan();
//        customCanvas.drawcircle(500,500);
//        Canvas c = new Canvas();
//          can
//        c.drawCircle(100,100,30,new Paint());
//        customCanvas.draw(c);
//        customCanvas.refreshDrawableState();
//        customCanvas.getDrawingCache().
    }
    public void goToActivityList (View view){
        Intent intent = new Intent (this, com.app.skynet.tulocation.activities.TULocationList.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        globalAPList = APList.getInstance();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apScanner = new APScanner(this, globalAPList);
        setContentView(R.layout.activity_tulocation_main);
//        customCanvas = new CanvasView(this);
        shape = new CustomShape(this);
//        shape = (CustomShape) findViewById(R.id.canv);
        apScanner.addObserver(shape);
        shape.setWillNotDraw(false);
        shape.setWillNotCacheDrawing(false);
        /* (CanvasView) findViewById(R.id.signature_canvas);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ap_list:
                Intent intent = new Intent (this, com.app.skynet.tulocation.activities.TULocationList.class);
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

    @Override
    protected void onPause() {
        super.onPause();
        apScanner.unregister();
    }
}
