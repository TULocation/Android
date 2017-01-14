package com.app.skynet.tulocation.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.skynet.tulocation.R;

public class TuLocationListDetail extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_apdetails, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}