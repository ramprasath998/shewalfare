package com.beebox.blood.shewalfare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String REGISTER_URL = "http://www.connectwithram.in/sheblooddonation/newdonor.php";
    BootstrapEditText nametext,  phonetext, citytext,emailtext;

    String bloodtext;

   BootstrapButton donate;


    Spinner bloodgroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TypefaceProvider.registerDefaultIconSets();

        getSupportActionBar().hide();

        nametext = (BootstrapEditText) findViewById(R.id.name);

        phonetext = (BootstrapEditText) findViewById(R.id.phone);
        citytext = (BootstrapEditText) findViewById(R.id.city);
        emailtext = (BootstrapEditText)findViewById(R.id.email);


        donate = (BootstrapButton) findViewById(R.id.click);
        donate.setOnClickListener(this);

        bloodgroups = (Spinner)findViewById(R.id.blood);


        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();

        list.add("Select a Blood Group");
        list.add("APositive");
        list.add("ANegative");
        list.add("BPositive");
        list.add("BNegative");
        list.add("OPositive");
        list.add("ONegative");
        list.add("ABPositive");
        list.add("ABNegative");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner2, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodgroups.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.click) {
            validate();
        }


    }

    private void validate() {

        String name = nametext.getText().toString().trim();
        String blood = bloodgroups.getSelectedItem().toString().trim();
        String phone = phonetext.getText().toString().trim();
        String city = citytext.getText().toString().trim();

        String email = emailtext.getText().toString().trim();
        if (name.length() == 0) {
            nametext.setError("Enter valid name");

        }
        else if (blood.equals("Select a Blood Group"))
        {
            Toast.makeText(getApplication(),"Select an blood group",Toast.LENGTH_SHORT).show();
        }

        else if (phone.length() < 10 || phone.length() > 10 )
        {
            phonetext.setError("enter correct phone number");
        }
        else if (city.length() == 0)
        {
            citytext.setError("");
        }

        else if (!isValidEmail(email))
        {
            emailtext.setError("");
        }



       else {

            registeration(name, blood, phone, city,email);
        }


    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    private void registeration(String name, String blood, final String phone, String city, String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait","Registering", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals("successfully registered"))
                {

                    nametext.setText("");

                    phonetext.setText("");
                    citytext.setText("");
                    emailtext.setText("");
                    Toast.makeText(getApplicationContext(),"You are an HERO",Toast.LENGTH_LONG).show();

                }

                Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("blood",params[1]);
                data.put("phone",params[2]);
                data.put("city",params[3]);
                data.put("email",params[4]);
                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,blood,phone,city,email);
    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(MainActivity.this,Bottom_Navigation.class));

    }


}