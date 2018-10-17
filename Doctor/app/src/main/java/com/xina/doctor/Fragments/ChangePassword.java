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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xina.doctor.Activities.Dashboard;
import com.xina.doctor.Activities.Login;
import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.ChangePasswordModel;
import com.xina.doctor.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by S K on 25-06-2018.
 */

public class ChangePassword extends Fragment {


    View myView;
    EditText oldPwd,newPwd,reNewPwd;
    Button chngPwd;
    String oldPassword,newPassword,reNewPassword,userid="dt12345";
    SharedPreferences sp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView= inflater.inflate(R.layout.change_password,container,false);

        ((Dashboard)getActivity()).setTitle("Change Password");
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        oldPwd = myView.findViewById(R.id.oldPwd);
        newPwd = myView.findViewById(R.id.NewPwd);
        reNewPwd = myView.findViewById(R.id.ReNewPwd);
        chngPwd = myView.findViewById(R.id.ChngPwd);
        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp = getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        userid = sp.getString("id","");


        chngPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                oldPassword = oldPwd.getText().toString().trim();
                newPassword = newPwd.getText().toString().trim();
                reNewPassword = reNewPwd.getText().toString().trim();

                if (oldPassword.isEmpty()){
                    flag =1;
                    oldPwd.setError("Please fill this field");
                } if (newPassword.isEmpty()){
                    flag = 1;
                    newPwd.setError("Please fill this field");
                } if (reNewPassword.isEmpty()){
                    flag = 1;
                    reNewPwd.setError("Please fill this field");
                } if (newPassword.length()<6){
                    flag = 1;
                    newPwd.setError("Password should not be less then 6 characters");
                } if (!newPassword.equals(reNewPassword)){
                    flag = 1;
                    newPwd.setError("This password Does not match with other");
                } if (flag==0){

                    final ProgressDialog progressDialog=new ProgressDialog(getActivity());
                    progressDialog.setMessage("Changing password...please wait");
                    progressDialog.show();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<ChangePasswordModel> call = apiInterface.changePassword(userid,oldPassword,newPassword);
                    call.enqueue(new Callback<ChangePasswordModel>() {
                        @Override
                        public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                            if (response.body().getSuccess()==200){
                                progressDialog.dismiss();
                                Intent intent = new Intent(getActivity(), Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                getActivity().finish();
                            }
                            if (response.body().getSuccess()==204){
                                Toast.makeText(getActivity(), "Old password is not valid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
