package com.beebox.blood.shewalfare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment4 extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout;
    BootstrapButton button;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;



    public BlankFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_blank_fragment4, container, false);

        button = (BootstrapButton)linearLayout.findViewById(R.id.viewbuttonfragment);
        button.setOnClickListener(this);
        cd = new ConnectionDetector(getContext());

        return linearLayout;
    }

    @Override
    public void onClick(View v) {

        isInternetPresent = cd.isConnectingToInternet();

        if (v.getId() == R.id.viewbuttonfragment)
        {

            if (!isInternetPresent)
            {
                Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
            }
            else
            {
                getActivity().finish();
                startActivity(new Intent(getContext(),View_Requests.class));

            }
        }

    }
}
