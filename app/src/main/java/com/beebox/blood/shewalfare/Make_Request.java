package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Make_Request extends Activity implements View.OnClickListener {


    BootstrapEditText pdate, pname, punit, ppurpose, proomno, phospitalname, phospitalplace, pattendarname, pattendarnumber;

    private static final String REGISTER_URL = "http://www.connectwithram.in/sheblooddonation/placerequest.php";


    Spinner pblood;

    BootstrapButton apply;

    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;


    public Calendar myCalendar = Calendar.getInstance();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__request);

        pdate = (BootstrapEditText) findViewById(R.id.patientdate);
        pname = (BootstrapEditText) findViewById(R.id.patientname);
        pblood = (Spinner) findViewById(R.id.patientblood);
        punit = (BootstrapEditText) findViewById(R.id.patientunit);
        ppurpose = (BootstrapEditText) findViewById(R.id.patientpurpose);
        proomno = (BootstrapEditText) findViewById(R.id.patientroomno);
        phospitalname = (BootstrapEditText) findViewById(R.id.patienthospital);
        phospitalplace = (BootstrapEditText) findViewById(R.id.patientplace);
        pattendarname = (BootstrapEditText) findViewById(R.id.patientattendername);
        pattendarnumber = (BootstrapEditText) findViewById(R.id.patientattendnumber);

        apply = (BootstrapButton) findViewById(R.id.placerequest);

        apply.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-mm-yyyy", Locale.US);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(Make_Request.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Select an Group");
        list.add("APositive");
        list.add("ANegative");
        list.add("A1Positive");
        list.add("A2Positive");
        list.add("A1BPositive");

        list.add("BPositive");
        list.add("BNegative");
        list.add("Bombay");

        list.add("OPositive");
        list.add("ONegative");
        list.add("ABPositive");
        list.add("ABNegative");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        pblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView selecteditem = (TextView) adapterView.getChildAt(0);

                selecteditem.setTextColor(getResources().getColor(android.R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        pblood.setAdapter(adapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void updateLabel() {

        String myFormat = "dd-MM-yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        pdate.setText(sdf.format(myCalendar.getTime()));


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.placerequest) {
            String date = pdate.getText().toString();
            String name = pname.getText().toString();
            String bloodgroup = pblood.getSelectedItem().toString();
            String unit = punit.getText().toString();

            String roomno = proomno.getText().toString();
            String purpose = ppurpose.getText().toString();
            String hospitalname = phospitalname.getText().toString();
            String hospitalplace = phospitalplace.getText().toString();
            String attendarname = pattendarname.getText().toString();
            String attendarphone = pattendarnumber.getText().toString();

            validate(date, name, bloodgroup, unit, purpose, roomno, hospitalname, hospitalplace, attendarname, attendarphone);


        }
    }

        public void validate(String date, String name, String bloodgroup, String unit, String purpose,String roomno ,String hospitalname, String hospitalplace, String attendarname, String attendarphone) {

            if (date.isEmpty() || name.isEmpty() || bloodgroup.isEmpty() || unit.isEmpty() || purpose.isEmpty() || hospitalname.isEmpty() || hospitalplace.isEmpty() || attendarname.isEmpty() || attendarphone.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Please fill all values ",Toast.LENGTH_SHORT).show();
            }


            else  if (date.isEmpty())
            {
                pdate.setError("please fill the values ");
            }
            else if (name.isEmpty())
            {
                pname.setError("please fill the values");
            }
            else if (unit.isEmpty())
            {
                punit.setError("please fill the values");
            }
            else if (bloodgroup.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Please select an Blood",Toast.LENGTH_SHORT).show();
            }
            else if(purpose.isEmpty())
            {
                ppurpose.setError("please fill the values");
            }
            else if (hospitalname.isEmpty())
            {
                phospitalname.setError("please fill the values");
            }
            else if (hospitalplace.isEmpty())
            {
                phospitalplace.setError("please fill the values");
            }
            else if (attendarname.isEmpty())
            {

                pattendarname.setError("please fill the values");
            }
            else if (attendarphone.isEmpty())
            {

                pattendarnumber.setError("please fill the values");
            }
            else if(!Patterns.PHONE.matcher(attendarphone).matches())
            {

                pattendarnumber.setError("Enter correct number");
            }

            else if(!(Pattern.matches("^[\\p{L} .'-]+$", name)))
            {

                pname.setError("Please enter correct name");

            }
            else if (!unit.matches("[0-9]+"))
            {
                Toast.makeText(getApplicationContext(),"Enter correct phone number",Toast.LENGTH_SHORT).show();
                pattendarnumber.setError("Enter correct number");
            }
            else if (!(Pattern.matches("^[\\p{L} .'-]+$", hospitalname)))
            {
                phospitalname.setError("Enter correct name avoid numbers ");
            }
            else if (!(Pattern.matches("^[\\p{L} .'-]+$", hospitalplace)))
            {
                phospitalplace.setError("enter correct place");
            }
            else if (!(Pattern.matches("^[\\p{L} .'-]+$", attendarname)))
            {
                pattendarname.setError("Enter correct name");
            }
            else if (!(Pattern.matches("^[\\p{L} .'-]+$", purpose)))
            {
                ppurpose.setError("Enter correct purpose");
            }
            else if (!roomno.matches("[0-9]+"))
            {
                proomno.setError("Enter correct values");

            }
            else {

                Register(date,name, bloodgroup, unit, purpose, roomno, hospitalname, hospitalplace, attendarname, attendarphone);

            }
        }

        private void Register(final String date, String name, String bloodgroup,String unit, String purpose,String roomno ,String hospitalname, String hospitalplace, String attendarname, String attendarphone) {

            class RegisterUser extends AsyncTask<String, Void, String> {
                ProgressDialog loading;
                RegisterUserClass ruc = new RegisterUserClass();


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(Make_Request.this, "Please Wait", "Registering", true, true);

                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("successfully registered"))
                    {


                        Toast.makeText(getApplicationContext(),"We will call back to you Soon  ",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(),Bottom_Navigation.class);
                        finish();
                        startActivity(intent);


                    }

                    Toast.makeText(getApplicationContext(),""+s, Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(String... params) {

                    HashMap<String, String> data = new HashMap<String,String>();
                    data.put("date",params[0]);
                    data.put("name",params[1]);
                    data.put("bloodgroup",params[2]);
                    data.put("unit",params[3]);
                    data.put("purpose",params[4]);
                    data.put("roomno",params[5]);
                    data.put("hospitalname",params[6]);
                    data.put("hospitalplace",params[7]);
                    data.put("attendarname",params[8]);
                    data.put("attendarphone",params[9]);


                    String result = ruc.sendPostRequest(REGISTER_URL,data);

                    return  result;
                }
            }

            RegisterUser ru = new RegisterUser();
            ru.execute(date,name,bloodgroup,unit,purpose,roomno,hospitalname,hospitalplace,attendarname,attendarphone);




        }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(Make_Request.this, Bottom_Navigation.class));
        finish();


    }



    }

