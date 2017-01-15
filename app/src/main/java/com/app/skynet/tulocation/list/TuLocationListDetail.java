package com.app.skynet.tulocation.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.skynet.tulocation.R;

import java.util.ArrayList;

public class TULocationListDetail extends ActionBarActivity {
    private AccessPoint ap;
    private TextView tvMac, tvBssid;
    private EditText etXPos, etYPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulocation_list_details);
        Intent i = getIntent();
        ap = (AccessPoint) i.getSerializableExtra("chosenAP");
        initText();
    }
    private void initText(){
        TextView tvMac = (TextView)findViewById(R.id.apMac);
        TextView tvBssid = (TextView)findViewById(R.id.apBSSID);
        EditText etXPos = (EditText)findViewById(R.id.setX);
        EditText etYPos = (EditText)findViewById(R.id.setY);
        tvMac.setText(ap.getMac());
        tvBssid.setText(ap.getBSSID());
        etXPos.setText(String.valueOf(ap.getPosX()), TextView.BufferType.EDITABLE);
        etYPos.setText(String.valueOf(ap.getPosY()), TextView.BufferType.EDITABLE);
    }
    public void save(View view) {
        ap.setPosX(Double.parseDouble(etXPos.getText().toString()));
        ap.setPosY(Double.parseDouble(etYPos.getText().toString()));
        ap.setMac("LOL");
    }
}