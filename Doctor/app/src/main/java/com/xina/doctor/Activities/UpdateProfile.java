package com.xina.doctor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xina.doctor.ApiClient.ApiClient;
import com.xina.doctor.Fragments.DoctorProfile;
import com.xina.doctor.Interfaces.ApiInterface;
import com.xina.doctor.Models.UpdateModel;
import com.xina.doctor.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener{
   public EditText name,degree,speciality,mobile,email;
    public Button update;
   public static  String user="doctor",userid;
    private Bitmap bitmap;
    static String tkn="tkn";
    private static final int INTENT_REQUEST_CODE = 100;
    private int PICK_IMAGE_REQUEST = 1;
    private String mImageUrl = "";
     public  ImageView upImage;
     SharedPreferences preferences;
    int flag = 0;
    ProgressDialog pDialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setTitle("Edit Details");
        init();

        update.setOnClickListener(this);
        upImage.setOnClickListener(this);

    }

    private void init() {
        name=findViewById(R.id.upNameId);
        email=findViewById(R.id.upEmailId);
        mobile=findViewById(R.id.upMobileId);
        degree=findViewById(R.id.upDegreeid);
        speciality=findViewById(R.id.upSpecialityId);
        upImage=findViewById(R.id.upImageId);
        update=findViewById(R.id.upButtonId);


        preferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        name.setText(preferences.getString("name", ""));
        email.setText(preferences.getString("email", ""));
        mobile.setText(preferences.getString("mobile", ""));
        speciality.setText(preferences.getString("speciality", ""));
        degree.setText(preferences.getString("degree", ""));
        // selectImageView.setIm(preferences.getString("name", ""));
        Picasso.with(getApplicationContext()).load(preferences.getString("image", "")).into(upImage);
    }

    @Override
    public void onClick(View v) {
        if (v==update)
        {
            UpdateProfile();
        }

        if (v==upImage){
            selectImageHere();
        }
    }


    public void selectImageHere() {
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageIntent, "Select Picture"),
                PICK_IMAGE_REQUEST);
    }

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST &&
                resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                upImage.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        filePath);
                //Setting the Bitmap to ImageView
                upImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void UpdateProfile() {

        sp=getSharedPreferences("pref",MODE_PRIVATE);
        userid = sp.getString("id","");


        flag = 0;
        String d_name = name.getText().toString().trim();
        String d_email = email.getText().toString().trim();
        String d_degree = degree.getText().toString().trim();
        String d_speciality = speciality.getText().toString().trim();
        String d_mobile=mobile.getText().toString().trim();

        String d_image = "";
        if (bitmap != null)
            d_image = getStringImage(bitmap);

        String u = user;
        String c = mobile.getText().toString().trim();
          if (d_name.isEmpty()){
              name.setError("Please fill name");
              name.requestFocus();
          }
       else if (d_email.isEmpty()){
            email.setError("Please fill email");
            email.requestFocus();
        }
       else if (d_degree.isEmpty()){
            degree.setError("Please fill degreee");
            degree.requestFocus();
        }
          else if (d_speciality.isEmpty()){
              speciality.setError("Please fill specality");
              speciality.requestFocus();
          }
          else if (d_mobile.isEmpty()){
              mobile.setError("Please fill mobile no.");
              mobile.requestFocus();
          }

          else if (mobile.length()!=10){
              mobile.setError("Please enter valid length");
          }

          else if (!Patterns.EMAIL_ADDRESS.matcher(d_email).matches()) {
              email.setError("Please Enter The Valid Email");
          }


          else {
            pDialog = new ProgressDialog(UpdateProfile.this);
            pDialog.setMessage("Updating....Please wait");
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setMax(100);
            pDialog.show();

            ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
            Call<UpdateModel> call = apiClient.getUpdateDetails(userid,d_name,d_email,d_mobile,d_image,user,tkn,d_speciality,d_degree);
            call.enqueue(new Callback<UpdateModel>() {
                @Override
                public void onResponse(Call<UpdateModel> call, Response<UpdateModel> response) {
                    Toast.makeText(UpdateProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getSuccess() == 200) {


                        sp = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.apply();
                        pDialog.dismiss();
/*
                        Intent intent = new Intent(UpdateProfile.this, Dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();*/
                    } else if (response.body().getSuccess() == 204) {
                        View r = findViewById(android.R.id.content);
                        Snackbar.make(r, "Username/Password is not correct", Snackbar.LENGTH_LONG).show();
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<UpdateModel> call, Throwable t) {
                    View r = findViewById(android.R.id.content);
                    Snackbar.make(r, "Something went wrong", Snackbar.LENGTH_LONG).show();

                    pDialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), DoctorProfile.class));
        finish();
    }
}
