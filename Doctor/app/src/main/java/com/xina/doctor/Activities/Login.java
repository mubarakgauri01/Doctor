package com.xina.doctor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sinch.android.rtc.SinchError;
import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.LoginDetail;
import com.xina.doctor.Models.LoginModel;
import com.xina.doctor.R;
import com.xina.doctor.Services.SinchService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends com.xina.doctor.SinchActivities.BaseActivity implements View.OnClickListener, SinchService.StartFailedListener {
    Button login;
    EditText password,id;
    static String doctor="doctor";
    static String tkn="dt1234";
    TextView forgotpassword;
    String e,p,user,email,mobile,speciality,degree,name,tokenid;

   int flag = 0;
    ProgressDialog pDialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setTitle("Login");
        init();
    }

    private void init() {
        login=findViewById(R.id.login);
        forgotpassword=findViewById(R.id.forgotpassword);

        id=findViewById(R.id.email);
        password=findViewById(R.id.password);


        forgotpassword.setOnClickListener(this);
        login.setOnClickListener(this);

        sp = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        id.setText(sp.getString("id", ""));

      //   e=sp.getString("id","");

      // Toast.makeText(this,e, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        if (v==login)
            loginToHome();

        if(v==forgotpassword)
        {
            Forgot();
        }
    }


    public void Forgot(){
        Intent intent=new Intent(Login.this,ForgotPassword.class);
        startActivity(intent);
    }

public void loginToHome() {
    flag = 0;
    e = id.getText().toString().trim();
    p = password.getText().toString().trim();
    user=doctor;
    tokenid=tkn;




    if (e.isEmpty()) {
        id.setError("Please Enter The User Email");
        id.requestFocus();

        flag = 1;
    } else if (p.length() < 6 && password.length() != 0) {
        password.setError("Password is   Wrong");
        password.requestFocus();
        flag = 1;
    } else if (p.isEmpty()) {
        flag = 1;
        password.setError("Please Enter Your Password");

    } else if (flag == 0) {
        pDialog = new ProgressDialog(Login.this);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.setMax(100);
        pDialog.setMessage("Loging in ...Please wait");
        pDialog.show();

        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> call = apiClient.getLogin(e,p,user,tokenid);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.body().getSuccess() == 200) {

                    pDialog.dismiss();

                    LoginModel loginModel = response.body();
                    LoginDetail loginDetail = loginModel.getDetail();


                    String e = loginDetail.getUserid();

                    id.setText(e);


                    sp = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();


                    ed.putString("password", p);
                    ed.putString("id", e);
                    ed.putString("degree", degree);
                    ed.putString("speciality", speciality);
                    ed.putString("name", name);
                    ed.putString("mobile", mobile);
                    ed.apply();
                    ed.commit();
                    pDialog.dismiss();

                    Intent intent = new Intent(Login.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (!getSinchServiceInterface().isStarted()) {
                        getSinchServiceInterface().startClient("uves");
                    }
                    startActivity(intent);
                    finish();

                }

            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                View r = findViewById(android.R.id.content);
                Snackbar.make(r, "Something went wrong", Snackbar.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

}

    @Override
    public void onStartFailed(SinchError error) {

    }

    @Override
    public void onStarted() {

    }
}
