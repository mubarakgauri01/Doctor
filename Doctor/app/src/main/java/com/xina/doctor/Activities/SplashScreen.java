package com.xina.doctor.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;
import com.xina.doctor.R;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashScreen extends AwesomeSplash {
    String id = "", password = "";

    public static final int RequestPermissionCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FIRST_SUB_WINDOW, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Background Animation
        configSplash.setBackgroundColor(R.color.splashcolor);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_TOP);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //Logo Animation
        configSplash.setLogoSplash(R.drawable.logosynergy);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceInDown);

        //Title  Animation
        configSplash.setTitleSplash("Your Health Care");
        configSplash.setAnimTitleTechnique(Techniques.Bounce);
        configSplash.setTitleTextSize(20f);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setTitleTextColor(R.color.splashTitle);

        if(checkPermission()) {

           String AudioSavePathInDevice =
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                             "AudioRecording.3gp";

//            MediaRecorderReady();
//
//            try {
//                mediaRecorder.prepare();
//                mediaRecorder.start();
//            } catch (IllegalStateException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

            //buttonStart.setEnabled(false);
            //buttonStop.setEnabled(true);

            Toast.makeText(SplashScreen.this, "Recording started",
                    Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
        }


    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(SplashScreen.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
    @Override
    public void animationsFinished() {
        checkConnection();
    }
    public  void finishAnim(){

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        id = pref.getString("id", "");
        password = pref.getString("password", "");

        if (id == "" && password == "") {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
        finish();

    }
    public  void checkInternet(){

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("You're not connected to the internet");
        adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        adb.create().show();

    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(SplashScreen.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SplashScreen.this,"PermissionDenied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public void checkConnection() {



        if (isOnline()) {
            finishAnim();

        } else {
            checkInternet();
        }
    }
}
