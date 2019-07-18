package com.wpes.worldofmedicine.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpes.worldofmedicine.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPrescription extends Fragment {


    public MyPrescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myprescription, container, false);
    }

}
