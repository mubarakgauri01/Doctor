package com.xina.doctor.Activities;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xina.doctor.Fragments.CallHistory;
import com.xina.doctor.Fragments.ChangePassword;
import com.xina.doctor.Fragments.DoctorProfile;
import com.xina.doctor.Fragments.PatientsList;
import com.xina.doctor.R;
import com.xina.doctor.SinchActivities.LoginActivity;

public class Dashboard extends com.xina.doctor.SinchActivities.BaseActivity
        {
    CardView patientCardView,callCardview;
    android.app.Fragment fragment=null;
    FragmentManager manager;
    TextView textViewTitle;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //setSupportActionBar(toolbar);
        textViewTitle = (TextView) findViewById(R.id.txtTitle);



        patientCardView=findViewById(R.id.patientList);
          callCardview=findViewById(R.id.callHistory);

          patientCardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  fragment=new PatientsList();
                  manager=getFragmentManager();
                  manager.beginTransaction().replace(R.id.content_frame,fragment).commit();
              }
          });
          callCardview.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  fragment=new CallHistory();
                  manager=getFragmentManager();
                  manager.beginTransaction().replace(R.id.content_frame,fragment).commit();
              }
          });

          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (!getSinchServiceInterface().isStarted()) {
                      getSinchServiceInterface().startClient("uves");
                  }
              }
          },3000);

    }

    public void setTitle(String title) {
        textViewTitle.setText(title);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(getBaseContext()==this) {
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setMessage("Do you wanna exit");
                adb.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                adb.setNegativeButton("no", null);
                adb.create().show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        android.app.Fragment fragment=null;
       FragmentManager manager;

        if (id == R.id.Profile) {
            //getSupportActionBar().hide();

            //getSupportActionBar().setTitle("Profile");
            //getActionBar().setTitle("PROFILE");
            fragment=new DoctorProfile();
            manager=getFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame,fragment).commit();
        }

       /* if (id == R.id.Change_Password) {
            fragment=new ChangePassword();
            manager=getFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame,fragment).isAddToBackStackAllowed().commit();
        }*/

       if (id==R.id.Change_Password){
           fragment=new ChangePassword();
           manager=getFragmentManager();
           manager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack("Dashboard.java").commit();
       }if (id==R.id.callButton){

           startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        if (id == R.id.Logout) {
            SharedPreferences preferences= getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(Dashboard.this,Login.class));
            finish();
            startActivity(new Intent(Dashboard.this,Login.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        FragmentManager fragmentManager=getFragmentManager();

        if (id == R.id.Home) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new PatientsList()).addToBackStack(null).commit();
        } else if (id == R.id.Profile) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new DoctorProfile()).addToBackStack(null).commit();
        } else if (id == R.id.Change_Password) {

            fragmentManager.beginTransaction().replace(R.id.content_frame,new ChangePassword()).addToBackStack(null).commit();
        }

        else if (id == R.id.Call) {

            fragmentManager.beginTransaction().replace(R.id.content_frame,new CallHistory()).addToBackStack(null).commit();

        }

        else if (id == R.id.Logout) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
