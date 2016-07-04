package com.beebox.blood.shewalfare;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class start_user extends Activity implements View.OnClickListener {


    BootstrapButton con;
    BootstrapEditText email;

    SharedPreferences preferences;

    SharedPreferences gcmid;




    ConnectionDetector connectionDetector;

    Boolean isInternetPresent = false;



    private BroadcastReceiver mRegistrationBroadcastReceiver;




    public static final String REGGCM="http://connectwithram.in/sheblooddonation/shegcmregister.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_user);

        con = (BootstrapButton)findViewById(R.id.confirmphone);


        email = (BootstrapEditText)findViewById(R.id.gcmemailid);

        connectionDetector = new ConnectionDetector(this);



        con.setOnClickListener(this);



        preferences = getSharedPreferences("check",MODE_PRIVATE);

        gcmid = getSharedPreferences("registrationid", MODE_PRIVATE);

        emailaccounts();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {

            //When the broadcast received
            //We are sending the broadcast from GCMRegistrationIntentService




            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully


                if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS)) {
                    //Getting the registration token from the intent
                    String token = intent.getStringExtra("token");
                    //Displaying the token as toast

                    registergcmid(email.getText().toString(), token);
                    SharedPreferences.Editor gcm = gcmid.edit();
                    gcm.putString("id", token);
                    gcm.putString("phone", email.getText().toString().trim());

                    gcm.commit();
                    //if the intent is not with success then displaying error messages
                } else if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)) {
                    Toast.makeText(getApplicationContext(), "GCM registration error!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();
                }


            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("new_intro", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));
    }

    //Unregistering receiver on activity paused
    @Override
    protected void onPause() {
        super.onPause();
        Log.w("new_intro", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
    @Override
    public void onClick(View v) {

        isInternetPresent = connectionDetector.isConnectingToInternet();

        if (v.getId() == R.id.confirmphone)
        {




            if (!isInternetPresent)
            {
                Toast.makeText(getApplicationContext(),"Please Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }



            else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
            {
                Toast.makeText(getApplicationContext(),"Enter Correct Email-ID",Toast.LENGTH_SHORT).show();

            }
            else
            {
                startregistration();
                con.setVisibility(View.INVISIBLE);

            }
        }
    }

    private void emailaccounts() {


        final AlertDialog loadingemail = new SpotsDialog(start_user.this);
        loadingemail.setTitle("Please Wait");
        loadingemail.setMessage("Fetching Registered Email Id");
        loadingemail.show();

        Runnable progressrunnable = new Runnable() {
            @Override
            public void run() {
                loadingemail.dismiss();
            }
        };
                   String possibleemails = "";
                   try
                   {
                       Account[] accounts = AccountManager.get(getApplicationContext()).getAccountsByType("com.google");
                       for(Account account :accounts)
                       {
                           possibleemails += ""+account.name;

                       }
                   }catch (Exception e)
                   {
                        Toast.makeText(getApplicationContext(),"Please Post an bug report to admin",Toast.LENGTH_SHORT).show();
                   }

        if (possibleemails.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"No Registered Emails Found",Toast.LENGTH_SHORT).show();
            email.setHint("Enter Email Id");
        }
        else
        {
            email.setText(possibleemails);

            SharedPreferences.Editor editors = preferences.edit();
            editors.putString("emailsgcm",possibleemails);
            editors.commit();

        }


        android.os.Handler pdhandler = new android.os.Handler();
        pdhandler.postDelayed(progressrunnable,1500);

    }

    private void registergcmid(String userphone, String registrationId) {


     //   Toast.makeText(getApplicationContext(),userphone+" "+registrationId,Toast.LENGTH_SHORT).show();

        class RegisterUser extends AsyncTask<String,Void,String>{

            AlertDialog loading = new SpotsDialog(start_user.this);
            RegisterUserClass ruc = new RegisterUserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading.setTitle("One Time Registration");
                loading.setMessage("Please Wait");
                loading.show();
            }


            @Override
            protected String doInBackground(String... params) {

                HashMap<String,String> data = new HashMap<>();
                data.put("phone",params[0]);
                data.put("gcmid", params[1]);
                String result = ruc.sendPostRequest(REGGCM,data);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                long appversion = getAppVersion(getApplicationContext());

                if (s.equals("successfully registered") || s.equals("Update Successful"))
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("work",2);
                    editor.putLong("AppVersion",appversion);

                    editor.commit();
                    finish();
                    startActivity(new Intent(start_user.this,Bottom_Navigation.class));

                }
                else {

                    Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();
                    con.setVisibility(View.VISIBLE);
                }

            }

        }
        RegisterUser ru = new RegisterUser();
        ru.execute(userphone, registrationId);
    }



    private void startregistration() {

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        //if play service is not available
        if(ConnectionResult.SUCCESS != resultCode) {
            //If play service is supported but not installed
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //Displaying message that play service is not installed
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

                //If play service is not supported
                //Displaying an error message
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }

            //If play service is available
        } else {
            //Starting intent to register device
            Intent itent = new Intent(this, GCMRegistrationIntentService.class);
            startService(itent);
        }
    }



    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }




}
