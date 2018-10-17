package com.xina.doctor.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xina.doctor.Activities.Dashboard;
import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.PatientListDetails;
import com.xina.doctor.Models.PatientsListModel;
import com.xina.doctor.R;
import com.xina.doctor.adapters.PatientsListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class PatientsList extends Fragment {
    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    List<PatientListDetails> detailsItemsArrayList = new ArrayList<>();
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.patient_list,container,false);

        ((Dashboard)getActivity()).setTitle("Patient List");
        recyclerView = myView.findViewById(R.id.rcvidi);
        recyclerView.setHasFixedSize(true);
        detailsItemsArrayList = new ArrayList<>();

        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...please wait");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PatientsListModel> call = apiInterface.getList();
        call.enqueue(new Callback<PatientsListModel>() {
            @Override
            public void onResponse(Call<PatientsListModel> call, Response<PatientsListModel> response) {
                progressDialog.dismiss();
                PatientsListModel patientsListModel = response.body();
                List<PatientListDetails> list = patientsListModel.getDetails();
                PatientListDetails detailsItem =null;

                for (int i = 0; i < list.size(); i++) {
                    detailsItem = new PatientListDetails();
                    String name = list.get(i).getName();
                    String mobile = list.get(i).getMobile();
                    String desease = list.get(i).getDisease();
                    String address= list.get(i).getAddress();
                    String description = list.get(i).getDescription();

                    detailsItem.setName(name);
                    detailsItem.setMobile(mobile);
                    detailsItem.setDisease(desease);
                    detailsItem.setDescription(description);
                    detailsItem.setAddress(address);
                    detailsItemsArrayList.add(detailsItem);
                }
                PatientsListAdapter adapter = new PatientsListAdapter(getActivity(),detailsItemsArrayList);
                RecyclerView.LayoutManager recyce = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PatientsListModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        return myView;
    }

}
