package com.app.skynet.tulocation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.app.skynet.tulocation.R;
import com.app.skynet.tulocation.database.APtoDB;
import com.app.skynet.tulocation.list.AccessPoint;


public class TULocationListDetail extends ActionBarActivity {
    private AccessPoint ap;
    private TextView tvMac, tvBssid;
    private EditText etXPos, etYPos;
    private APtoDB db;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list_details);
        Intent i = getIntent();
        String mac = (String) i.getSerializableExtra("chosenAP");

        this.db = new APtoDB(this);
        db.open();
        ap = db.getAP(mac);
        Log.i(TULocationListDetail.class.getName(),ap.toString());
        db.close();
        initText();
    }
    private void initText(){
        tvMac = (TextView)findViewById(R.id.apMac);
        tvBssid = (TextView)findViewById(R.id.apBSSID);
        etXPos = (EditText)findViewById(R.id.setX);
        etYPos = (EditText)findViewById(R.id.setY);
        tvMac.setText(ap.getMac());
        tvBssid.setText(ap.getBSSID());
        etXPos.setText(String.valueOf(ap.getPosX()), TextView.BufferType.EDITABLE);
        etYPos.setText(String.valueOf(ap.getPosY()), TextView.BufferType.EDITABLE);
    }
    public void save(View view) {
        ap.setPosX(Double.parseDouble(etXPos.getText().toString()));
        ap.setPosY(Double.parseDouble(etYPos.getText().toString()));
        db.open();
        db.updateAPPos(ap);
        db.close();
        Intent intent = new Intent();
        intent.putExtra("editedValue",ap);
        setResult(RESULT_OK, intent);
        finish();
    }
}