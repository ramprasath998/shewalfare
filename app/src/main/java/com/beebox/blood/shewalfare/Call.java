package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Call extends Activity {


    TextView a,b,c,d;

    Button callls,vote;



    String name,phone,city,blood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);


        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        city = getIntent().getStringExtra("city");
        blood = getIntent().getStringExtra("blood");
       // String rates = getIntent().getStringExtra("rating");


        final String call = "tel:"+phone.toString().trim();
        a = (TextView)findViewById(R.id.displaytext);
        b = (TextView)findViewById(R.id.displayphone);
        c = (TextView)findViewById(R.id.displaycity);
        d = (TextView)findViewById(R.id.displayblood);

        callls = (Button)findViewById(R.id.calldonor);





        callls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(call));
                startActivity(callIntent);


            }
        });

        a.setText(name);
        b.setText(phone);
        c.setText(city);
        d.setText(blood);


    }





}
