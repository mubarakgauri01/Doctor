package com.xina.doctor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.ForgetModel;
import com.xina.doctor.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by S K on 25-06-2018.
 */

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    Button send;
    String e,user,id,token;
    static String doctor = "doctor";
    int flag = 0;
    ProgressDialog pDialog;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setTitle("Forgot Password");

        email = findViewById(R.id.email);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == send) {
            SendPassword();
        }

    }

    public void SendPassword() {
        flag = 0;
        e = email.getText().toString().trim();
        user = doctor;
        if (e.isEmpty()) {
            email.setError("Please Enter The User Email");
            flag = 1;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            email.setError("Please Enter The Valid Email");
            flag = 1;
        } else if (flag == 0) {

            pDialog = new ProgressDialog(ForgotPassword.this);
            pDialog.setMessage("Wait a movement..");
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setMax(100);
            pDialog.show();

            ApiInterface apiClientt = ApiClient.getClient().create(ApiInterface.class);
            Call<ForgetModel> call = apiClientt.getForgotPassword(e,user);
            call.enqueue(new Callback<ForgetModel>() {
                @Override
                public void onResponse(Call<ForgetModel> call, Response<ForgetModel> response) {
                    if (response.body().getSuccess() == 200) {
                        id = (String) response.body().getForgotDetail().getId();
                        token= (String) response.body().getForgotDetail().getTokenId();


                        sp = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("id",id);
                        ed.putString("token_id",token);


                        ed.apply();

                        pDialog.dismiss();

                        Intent intent = new Intent(ForgotPassword.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else if (response.body().getSuccess() == 204) {
                        View r = findViewById(android.R.id.content);
                        Snackbar.make(r, "Username/Password is not correct", Snackbar.LENGTH_LONG).show();
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ForgetModel> call, Throwable t) {
                    View r = findViewById(android.R.id.content);
                    Snackbar.make(r, "Something went wrong", Snackbar.LENGTH_LONG).show();
                    pDialog.dismiss();
                }
            });
        }
    }
}
