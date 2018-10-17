package com.xina.doctor.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xina.doctor.Activities.Dashboard;
import com.xina.doctor.Activities.UpdateProfile;
import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.ProfileDetail;
import com.xina.doctor.Models.ProfileModel;
import com.xina.doctor.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class DoctorProfile extends Fragment  {
    TextView name,email,mobile,specicality,degree;
    ImageView editProfile,showImage;
    SharedPreferences preferences;
    View myView;
    public  static  String user="doctor";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        myView= inflater.inflate(R.layout.doctor_profile,container,false);


        //.getSupport().setTitle("parvez");

        ((Dashboard)getActivity()).setTitle("Doctor Profile");


        editProfile=myView.findViewById(R.id.editProfileID);
        name=myView.findViewById(R.id.nameShowId);
        email=myView.findViewById(R.id.emailShowId);
        mobile=myView.findViewById(R.id.mobileShowId);
        specicality=myView.findViewById(R.id.specialityShowId);
        degree=myView.findViewById(R.id.degreeShowid);
        showImage=myView.findViewById(R.id.showImageViewId);



        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UpdateProfile.class);
                startActivity(intent);

            }
        });
           showProfile();
        return myView;
    }

    private void showProfile() {

        preferences = this.getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        String userId=preferences.getString("id","");

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating...Please wait");
        progressDialog.show();

        ApiInterface api_interface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileModel> call = api_interface.getProfile(userId,user);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.body().getSuccess() == 200) {
                    progressDialog.dismiss();
                    ProfileModel nurseProfileMOdel = response.body();
                    List<ProfileDetail> list = nurseProfileMOdel.getDetail();

                    for (int i = 0; i < list.size(); i++) {
                        String n_name = list.get(i).getName();
                        String n_email=list.get(i).getEmail();
                        String n_mobile=list.get(i).getMobile();
                        String n_image=list.get(i).getImage();
                        String n_speciality=list.get(i).getSpeciality();
                        String n_degree=list.get(i).getDegree();


                        name.setText(n_name);
                        email.setText(n_email);
                        mobile.setText(n_mobile);
                        specicality.setText(n_speciality);
                        degree.setText(n_degree);
                        Picasso.with(getActivity()).load(n_image).into(showImage);


                        preferences = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor ed = preferences.edit();
                        ed.putString("name", n_name);
                        ed.putString("email", n_email);
                        ed.putString("mobile", n_mobile);
                        ed.putString("speciality", n_speciality);
                        ed.putString("image", n_image);
                        ed.putString("degree", n_degree);
                        ed.commit();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
