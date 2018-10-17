package com.xina.doctor.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xina.doctor.Activities.Dashboard;
import com.xina.doctor.R;

public class CallHistory extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Call History");
        myView= inflater.inflate(R.layout.call_history,container,false);
        ((Dashboard)getActivity()).setTitle("Call History");
        return myView;

    }

}
