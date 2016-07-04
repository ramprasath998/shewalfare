package com.beebox.blood.shewalfare;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class  BlankFragment2 extends Fragment {

    LinearLayout linearLayout;
    BootstrapButton donorss,passwd;
    Spinner bloodspinner;
    BootstrapEditText password;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    static int flag = 0;


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
       donorss = (BootstrapButton)linearLayout.findViewById(R.id.viewbuttonfragment);
       bloodspinner = (Spinner)linearLayout.findViewById(R.id.fragmentspinner);
       password = (BootstrapEditText)linearLayout.findViewById(R.id.adminpassword);
        passwd = (BootstrapButton)linearLayout.findViewById(R.id.passwordfragmentbutton);
        cd = new ConnectionDetector(getContext());






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



        adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bloodspinner.setAdapter(adapter);



        donorss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isInternetPresent = cd.isConnectingToInternet();


                if (!isInternetPresent) {

                    Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                } else {
                    String blood = bloodspinner.getSelectedItem().toString();
                    if (blood.equals("Select an Group")) {
                        Toast.makeText(getContext(), "Please select an Blood Group", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent bloodintents = new Intent(getContext(), view_donors.class);
                        bloodintents.putExtra("bloods", blood);
                        startActivity(bloodintents);
                    }


                }
            }

        });

        passwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psd = password.getText().toString();
                if (psd.equals("8010")||psd.equals("0028"))
                {
                    donorss.setVisibility(View.VISIBLE);
                    passwd.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    bloodspinner.setVisibility(View.VISIBLE);
                    password.setText("");
                }
                if (psd.equals(""))
                {
                    Toast.makeText(getContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                }

            }
        });




        return linearLayout;
    }



}
