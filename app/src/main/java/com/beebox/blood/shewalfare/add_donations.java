package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import com.google.ads.*;
import com.google.android.gms.ads.InterstitialAd;

public class add_donations extends Activity implements View.OnClickListener {

    BootstrapEditText date,names,units;

    BootstrapButton button;


    private DBHelper mydb;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donations);


        date = (BootstrapEditText)findViewById(R.id.donationdate);
        names = (BootstrapEditText)findViewById(R.id.donationhospitalname);
        units = (BootstrapEditText)findViewById(R.id.donationunit);


        button = (BootstrapButton)findViewById(R.id.donationconfirm);
        button.setOnClickListener(this);


        mydb = new DBHelper(this);








    }

    @Override
    public void onClick(View v) {


        if (date.getText().toString().isEmpty() || names.getText().toString().isEmpty() || units.getText().toString().isEmpty()) {
            Toast.makeText(add_donations.this, "Add all values", Toast.LENGTH_SHORT).show();
        }
        else
           {

             if (mydb.insertContact(date.getText().toString(),names.getText().toString(),units.getText().toString())) {

                 Toast.makeText(add_donations.this, "Done", Toast.LENGTH_SHORT).show();
                 this.finish();
                 startActivity(new Intent(add_donations.this, Bottom_Navigation.class));
             }
           }

    }

    @Override
    public void onBackPressed()
    {
        this.finish();
        startActivity(new Intent(add_donations.this,Bottom_Navigation.class));
        finish();

    }

}
