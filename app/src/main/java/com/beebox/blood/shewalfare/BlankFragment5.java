package com.beebox.blood.shewalfare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.suredigit.inappfeedback.FeedbackDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment5 extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout;

    FeedbackDialog feedbackDialog;

   BootstrapButton label1;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;



    public BlankFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_blank_fragment5, container, false);

        feedbackDialog = new FeedbackDialog(getContext(),"AF-5033E12D65E4-5D");

        label1 = (BootstrapButton)linearLayout.findViewById(R.id.label);
        label1.setOnClickListener(this);
        cd = new ConnectionDetector(getContext());




        return linearLayout;
    }

    @Override
    public void onClick(View v) {

        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent)
        {
            Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
        }
        else
        {
            feedbackDialog.show();
        }

    }
}
