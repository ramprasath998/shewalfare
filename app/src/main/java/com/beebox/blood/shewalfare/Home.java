package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Home extends Activity implements View.OnClickListener {


    Boolean isInternetPresent = false;

    Button donate, newdonor;

    ConnectionDetector cd;


    Spinner spin;


    TextView title,head1,head2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TypefaceProvider.registerDefaultIconSets();

        spin = (Spinner)findViewById(R.id.spinnerblood);


        title = (TextView)findViewById(R.id.shewelfatetitle);

        head1 = (TextView)findViewById(R.id.textdonor);
        head2 = (TextView)findViewById(R.id.searchtext);







        Typeface custom = Typeface.createFromAsset(getAssets(),"fonts/ubu.ttf");

        title.setTypeface(custom);


        Typeface title2 = Typeface.createFromAsset(getAssets(),"fonts/FrancoisOne.ttf");


        head1.setTypeface(title2);
        head2.setTypeface(title2);


        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Select an Group");
        list.add("APositive");
        list.add("ANegative");
        list.add("BPositive");
        list.add("BNegative");
        list.add("OPositive");
        list.add("ONegative");
        list.add("ABPositive");
        list.add("ABNegative");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        cd = new ConnectionDetector(getApplicationContext());

        donate = (Button) findViewById(R.id.donateblood);
        newdonor = (Button) findViewById(R.id.request);
        donate.setOnClickListener(this);
        newdonor.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.donateblood) {

            isInternetPresent = cd.isConnectingToInternet();

            if(!isInternetPresent)
            {


                Toast.makeText(getApplication(),"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
            }
            else
            {
                startActivity(new Intent(Home.this, Make_Request.class));
            }




       }
        if (view.getId() == R.id.request) {

            isInternetPresent = cd.isConnectingToInternet();
            if(!isInternetPresent)
            {
                Toast.makeText(getApplication(),"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
            }
            else {

                String blood = spin.getSelectedItem().toString();
                if (blood.equals("Select an Group"))
                {
                    Toast.makeText(getApplicationContext(),"Please select an Blood Group",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Intent bloodintent = new Intent(Home.this,View_Requests.class);
                    bloodintent.putExtra("bloods", blood);
                    startActivity(bloodintent);
                }


            }


              // finish();
        }
    }
}