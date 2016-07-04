package com.beebox.blood.shewalfare;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout;

    BootstrapButton button1,button2,button3;


    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    InterstitialAd minterstital;



    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {








        linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_blank, container, false);

        button1 = (BootstrapButton)linearLayout.findViewById(R.id.donorfragment1);
        button2 = (BootstrapButton)linearLayout.findViewById(R.id.donorfragment2);
        button3 = (BootstrapButton)linearLayout.findViewById(R.id.donorfragment3);




        minterstital = new InterstitialAd(getContext());
        minterstital.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        final AdRequest adRequest = new AdRequest.Builder().build();
        minterstital.loadAd(adRequest);

        minterstital.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                minterstital.show();
            }
        });



        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        cd = new ConnectionDetector(getContext());

        // Inflate the layout for this fragment
        return linearLayout;
    }


    @Override
    public void onClick(View v) {

        isInternetPresent = cd.isConnectingToInternet();

        if (v.getId() == R.id.donorfragment1)
        {

            if (!isInternetPresent)
            {
                Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
            }
            else
            {
                getActivity().finish();
              startActivity(new Intent(getContext(), MainActivity.class));

            }

        }
        if (v.getId() == R.id.donorfragment2)
        {


                getActivity().finish();
                startActivity(new Intent(getContext(),add_donations.class));

        }
        if (v.getId() == R.id.donorfragment3)
        {

               getActivity().finish();
               startActivity(new Intent(getContext(), my_donations.class));

        }



    }
}
