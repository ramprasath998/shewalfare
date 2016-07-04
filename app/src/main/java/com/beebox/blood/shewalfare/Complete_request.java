package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.BootstrapLabel;

import java.util.HashMap;

public class Complete_request extends Activity implements View.OnClickListener {

    BootstrapLabel name,unit,bloodgroup,date,purpose,roomno,attendarname,attendarphone,hospitalname,hospitallocation;

   BootstrapEditText donorname,donornumber;

    BootstrapButton confirm;

    private static final String REGISTER_URL = "http://www.connectwithram.in/sheblooddonation/completerequest.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_request);

        Bundle bundle = getIntent().getExtras();

        String[] arraybundle = bundle.getStringArray("array");


        name = (BootstrapLabel)findViewById(R.id.name2);
        bloodgroup = (BootstrapLabel)findViewById(R.id.blood2);
        unit = (BootstrapLabel)findViewById(R.id.unit2);
        date = (BootstrapLabel)findViewById(R.id.date2);
        purpose = (BootstrapLabel)findViewById(R.id.purpose2);
        roomno = (BootstrapLabel)findViewById(R.id.room2);
        attendarname = (BootstrapLabel)findViewById(R.id.attendarname2);
        attendarphone = (BootstrapLabel)findViewById(R.id.attendarphone2);
        hospitalname = (BootstrapLabel)findViewById(R.id.hospitalname2);
        hospitallocation = (BootstrapLabel)findViewById(R.id.hospitalplace2);


        donorname = (BootstrapEditText)findViewById(R.id.assigndonor);
        donornumber = (BootstrapEditText)findViewById(R.id.assigndonorphone);
        confirm = (BootstrapButton)findViewById(R.id.confirmreg);
        confirm.setOnClickListener(this);



        name.setText(arraybundle[0]);
        bloodgroup.setText(arraybundle[1]);
        unit.setText(arraybundle[2]);
        date.setText(arraybundle[3]);
        purpose.setText(arraybundle[4]);
        roomno.setText(arraybundle[5]);
        attendarname.setText(arraybundle[6]);
        attendarphone.setText(arraybundle[7]);
        hospitalname.setText(arraybundle[8]);
        hospitallocation.setText(arraybundle[9]);








    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.confirmreg)
        {

            if (donornumber.getText().toString().isEmpty() || !Patterns.PHONE.matcher(donornumber.getText().toString()).matches())
            {

                donornumber.setError("Please enter valid number");

            }
            else if (donorname.getText().toString().isEmpty())
            {
                donorname.setError("Enter Valid Name");
            }

            else
            {
                processdata();
            }


        }

    }

    private void processdata() {

        String doname = donorname.getText().toString();
        String donphone = donornumber.getText().toString();

        String reqpname = name.getText().toString();
        String reqpblood = bloodgroup.getText().toString();
        String reqpunit = unit.getText().toString();
        String reqpdate = date.getText().toString();
        String reqpurpose = purpose.getText().toString();
        String reqpattendarname = attendarname.getText().toString();
        String reqattendarphone = attendarphone.getText().toString();
        String reqphospitalname = hospitalname.getText().toString();
        String reqhospitalplace = hospitallocation.getText().toString();


        startregister(reqpname,reqpblood,reqpunit,reqpdate,reqpattendarname,reqattendarphone,reqphospitalname,reqhospitalplace,reqpurpose,doname,donphone);

    }

    private void startregister( String reqpname, String reqpblood, String reqpunit, String reqpdate,  String reqpattendarname, String reqattendarphone, String reqphospitalname, String reqhospitalplace,String reqpurpose,String doname, String donphone ) {


        class RegisterUser extends AsyncTask<String,Void,String>{

            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Complete_request.this, "Please Wait", "Working on", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals("successfully Completed"))
                {

                    Toast.makeText(getApplicationContext(),"Thank you Donor Keep Supporting ",Toast.LENGTH_LONG).show();

                    finish();
                    startActivity(new Intent(Complete_request.this,Bottom_Navigation.class));

                }

                Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();
            }



            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("pname",params[0]);
                data.put("pblood",params[1]);
                data.put("punit",params[2]);
                data.put("pdate",params[3]);
                data.put("pattendarname",params[4]);
                data.put("pattendarphone",params[5]);
                data.put("phospitalname",params[6]);
                data.put("phospitalplace",params[7]);
                data.put("ppurpose",params[8]);
                data.put("donorname",params[9]);
                data.put("donorphone",params[10]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(reqpname,reqpblood,reqpunit,reqpdate,reqpattendarname,reqattendarphone,reqphospitalname,reqhospitalplace,reqpurpose,doname,donphone);

        }



    }
