package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class Main_Home extends Activity implements View.OnClickListener {


    SharedPreferences gcid;
    String PROJECT_NUMBER="805742101799";


    public static String newid="";

    Button newdonor,searchdonor,viewrequest,makerequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__home);


        newdonor = (Button)findViewById(R.id.newdonortext);
        searchdonor = (Button)findViewById(R.id.searchdonortext);
        viewrequest = (Button)findViewById(R.id.viewkrequeststext);
        makerequest = (Button)findViewById(R.id.makerequesttext);

        newdonor.setOnClickListener(this);
        searchdonor.setOnClickListener(this);
        viewrequest.setOnClickListener(this);
        makerequest.setOnClickListener(this);




        /*

        gcid = getSharedPreferences("registrationid",MODE_PRIVATE);
        final String val = gcid.getString("id",null);
        Toast.makeText(getApplicationContext(), " " + val, Toast.LENGTH_SHORT).show();

        final GCMClientManager pushmanager = new GCMClientManager(this,PROJECT_NUMBER);
        pushmanager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {

                if (val.equals(registrationId))
                {
                    Toast.makeText(getApplicationContext(),"both are same",Toast.LENGTH_SHORT).show();

                }

                else {

                    pushmanager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
                        @Override
                        public void onSuccess(String registrationId, boolean isNewRegistration) {
                            Toast.makeText(getApplicationContext(),"New Registration",Toast.LENGTH_SHORT).show();
                            newid = registrationId;

                            registernewgcm(val,newid);
                        }
                    });


                }

            }
        });

*/
    }

    private void registernewgcm(final String val, final String newid) {

        class RegisterUSE extends AsyncTask<String,Void,String>{

            RegisterUserClass registerUserClass = new RegisterUserClass();

            @Override
            protected void onPostExecute(String s)
            {

                SharedPreferences.Editor editor = gcid.edit();
                editor.putString("id",newid);
                editor.commit();



            }


            @Override

            protected String doInBackground(String... params) {

                HashMap<String,String>data = new HashMap<>();
                data.put("oldid",val);
                data.put("newid",newid);


                String result = registerUserClass.sendPostRequest("REGCM",data);

                return null;
            }
        }

        RegisterUSE ruc = new RegisterUSE();
        ruc.execute(val,newid);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.newdonortext)
        {
            Toast.makeText(getApplicationContext(),"new Donor",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Bottom_Navigation.class));
        }

        if (v.getId() == R.id.searchdonortext)
        {
            //showalertdialog();

            final AlertDialog.Builder b = new AlertDialog.Builder(this);
            String[]types={"A Positive","A Negative","B Positive","B Negative","O Positive","O Negative","AB Positive","AB Negative","Bombay Group"};
            b.setItems(types, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                    switch (which)
                    {
                        case 0:
                            Intent a = new Intent(Main_Home.this,view_donors.class);
                            a.putExtra("bloods","APositive");
                            startActivity(a);
                            break;
                        case 1:
                            Intent b1 = new Intent(Main_Home.this,view_donors.class);
                            b1.putExtra("bloods", "ANegative");
                            startActivity(b1);
                            break;
                        case 2:
                            Intent c = new Intent(Main_Home.this,view_donors.class);
                            c.putExtra("bloods","BPositive");
                            startActivity(c);
                            break;
                        case 3:
                            Intent d = new Intent(Main_Home.this,view_donors.class);
                            d.putExtra("bloods","BNegative");
                            startActivity(d);
                            break;
                        case 4:
                            Intent e = new Intent(Main_Home.this,view_donors.class);
                            e.putExtra("bloods","OPositive");
                            startActivity(e);
                            break;

                        case 5:
                            Intent f = new Intent(Main_Home.this,view_donors.class);
                            f.putExtra("bloods","ONegative");
                            startActivity(f);
                            break;

                        case 6:
                            Intent g = new Intent(Main_Home.this,view_donors.class);
                            g.putExtra("bloods","ABPositive");
                            startActivity(g);
                            break;
                        case 7:
                            Intent h = new Intent(Main_Home.this,view_donors.class);
                            h.putExtra("bloods","ABNegative");
                            startActivity(h);
                            break;
                        case 8:
                            Intent i = new Intent(Main_Home.this,view_donors.class);
                            i.putExtra("bloods","Bombay");
                            startActivity(i);
                            break;

                    }


                }
            });

            b.show();






        }

        if (v.getId() == R.id.makerequesttext)
        {
            startActivity(new Intent(this,Make_Request.class));
        }
        if (v.getId() == R.id.viewkrequeststext)
        {
            startActivity(new Intent(this,View_Requests.class));
        }




    }

    private void showalertdialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final  View dialogview = inflater.inflate(R.layout.customlayout,null);

        builder.setView(dialogview);



        AlertDialog d = builder.create();
        d.show();


    }
}
