package com.beebox.blood.shewalfare;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.List;

/**
 * Created by Ram on 20-01-2016.
 */
public class CardAdapter2 extends RecyclerView.Adapter<CardAdapter2.ViewHolder> {
    private Context context;

    //List to store all superheroes
    List<Bloods2> superHeroes;


    //Constructor of this class
    public CardAdapter2(List<Bloods2> superHeroes, Context context) {
        super();
        //Getting all superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_request, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //Getting the particular item from the list
        final Bloods2 superHero = superHeroes.get(position);


        //Showing data on the views

        holder.textViewName.setText(superHero.getName());
        holder.textViewBlood.setText(superHero.getBlood());
        holder.textViewunit.setText(superHero.getUnit());
        holder.textViewdate.setText(superHero.getDate());
        holder.textViewPurpose.setText(superHero.getPurpose());
        holder.textViewroomno.setText(superHero.getRoomno());
        holder.textViewattendarname.setText(superHero.getAttendarname());
        holder.textViewattendarphone.setText(superHero.getAttendarphone());
        holder.textViewhospitalname.setText(superHero.getHospitalname());
        holder.textViewhospitalplace.setText(superHero.getHospitalplace());

        holder.complete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String[] pass = new String[]{superHero.getName().toString(), superHero.getBlood().toString(), superHero.getUnit().toString(), superHero.getDate().toString(),
                        superHero.getPurpose().toString(), superHero.getRoomno().toString(), superHero.getAttendarname().toString(), superHero.getAttendarphone().toString(),
                        superHero.getHospitalname().toString(), superHero.getHospitalplace().toString()};

                Intent complete = new Intent(context, Complete_request.class);

                Bundle bundle = new Bundle();
                bundle.putStringArray("array", pass);

                complete.putExtras(bundle);


                context.startActivity(complete);

            }
        });


        holder.sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareintent = new Intent();
                shareintent.setType("text/plain");
                String sharingdatas = "Name :"+superHero.getName().toString()+"\nBlood :"+superHero.getBlood().toString()+"\nUnit :"+superHero.getUnit().toString()+"\nDate :"+superHero.getDate().toString()+"\nHospital Name:"+superHero.getHospitalname().toString()+"\nAttendar name :"+superHero.getAttendarname().toString()+"\nAttendar Phone :"+superHero.getAttendarphone().toString()+"\nHospital place :"+superHero.getHospitalplace().toString();
                shareintent.putExtra(Intent.EXTRA_TEXT,sharingdatas);
                shareintent.setAction(Intent.ACTION_SEND);

                context.startActivity(Intent.createChooser(shareintent,"Share..."));

            }
        });






   //     Typeface custom1 = Typeface.createFromAsset(context.getAssets(), "fonts/OPSSEMIBOLD.ttf");

     //   holder.textViewName.setTypeface(custom1);
      //  holder.textViewBlood.setTypeface(custom1);



    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }



    public static   class ViewHolder extends RecyclerView.ViewHolder {
        //Views

        public View view;



        public TextView textViewName;

        public TextView textViewBlood;

        public TextView textViewunit;
        public TextView textViewdate;

        public TextView textViewPurpose;
        public TextView textViewroomno;
        public TextView textViewhospitalname;
        public TextView textViewhospitalplace;

       public TextView textViewattendarname;

        public TextView textViewattendarphone;

        public BootstrapButton complete,sharebutton;






        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.showpatientname);
            textViewBlood = (TextView) itemView.findViewById(R.id.showpatientblood);
            textViewunit = (TextView) itemView.findViewById(R.id.showunit);
            textViewdate = (TextView)itemView.findViewById(R.id.showdate);

            textViewhospitalname = (TextView)itemView.findViewById(R.id.showhospitalname);
            textViewhospitalplace = (TextView)itemView.findViewById(R.id.showhospitalplace);

            textViewPurpose = (TextView)itemView.findViewById(R.id.showpurpose);
            textViewroomno = (TextView)itemView.findViewById(R.id.showroomno);


            textViewattendarname = (TextView)itemView.findViewById(R.id.showattendarname);
            textViewattendarphone = (TextView)itemView.findViewById(R.id.showattendarplace);

            complete = (BootstrapButton)itemView.findViewById(R.id.deleteid);
            sharebutton = (BootstrapButton)itemView.findViewById(R.id.shareid);




        }


    }

}




