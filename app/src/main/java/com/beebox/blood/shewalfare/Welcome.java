package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        SharedPreferences preferences = getSharedPreferences("check",MODE_PRIVATE);
       final int ck = preferences.getInt("work", 0);
        final long app = preferences.getLong("AppVersion", 0);
        final  long checknew = getAppVersion(getApplicationContext());

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        Thread timer = new Thread()
        {
            public void run()
            {
              try{

                  sleep(2000);

              }
              catch(Exception e)
              {
                  Toast.makeText(getApplicationContext(),""+e ,Toast.LENGTH_SHORT).show();
              }
                finally {




                  if ( app == checknew) {
                      startActivity(new Intent(Welcome.this,Bottom_Navigation.class));
                      finish();


                  }

                  else if (checknew > app ) {

                      startActivity(new Intent(Welcome.this,start_user.class));
            //          Toast.makeText(Welcome.this,"App Id is",Toast.LENGTH_LONG).show();
                      finish();


                  } else {

                      startActivity(new Intent(Welcome.this,start_user.class));
              //        Toast.makeText(Welcome.this,"App Id is",Toast.LENGTH_LONG).show();
                      finish();
                      
                  }

              }
            }

        };
        timer.start();

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
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
