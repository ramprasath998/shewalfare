package com.beebox.blood.shewalfare;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Ram on 20-01-2016.
    */
public class CardAdapter  extends RecyclerView.Adapter<CardAdapter.ViewHolder>  {
    private Context context;

    //List to store all superheroes
    List<Bloods> superHeroes;


    //Constructor of this class
    public CardAdapter(List<Bloods> superHeroes, Context context){
        super();
        //Getting all superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloodlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //Getting the particular item from the list
        Bloods superHero =  superHeroes.get(position);


        //Showing data on the views

        holder.textViewName.setText(superHero.getName());
        holder.textViewPhone.setText(superHero.getPhone());
        holder.textViewBlood.setText(superHero.getBlood());
        holder.textViewCity.setText(superHero.getCity());


        Typeface custom1 = Typeface.createFromAsset(context.getAssets(),"fonts/OPSSEMIBOLD.ttf");

        holder.textViewName.setTypeface(custom1);
        holder.textViewBlood.setTypeface(custom1);
        holder.textViewCity.setTypeface(custom1);
        holder.textViewPhone.setTypeface(custom1);






        holder.details.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {



               String call = "tel:"+holder.textViewPhone.getText().toString().trim();

/*
               Intent ins = new Intent(view.getContext(),Call.class);
               ins.putExtra("phone",holder.textViewPhone.getText().toString());
               ins.putExtra("name",holder.textViewName.getText().toString());
               ins.putExtra("city",holder.textViewCity.getText().toString());
               ins.putExtra("blood",holder.textViewBlood.getText().toString());
                context.startActivity(ins);


           */
               Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(call));
              context.startActivity(callIntent);




           }
       });







    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }



    public static   class ViewHolder extends RecyclerView.ViewHolder {
        //Views

        public View view;
        public TextView textViewName;
        public TextView textViewPhone;
        public TextView textViewBlood;
        public TextView textViewCity;

        public BootstrapButton details;
        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.testname1);
            textViewPhone = (TextView) itemView.findViewById(R.id.testphone1);
            textViewBlood = (TextView) itemView.findViewById(R.id.testblood1);
            textViewCity = (TextView) itemView.findViewById(R.id.testcity1);



            details = (BootstrapButton)itemView.findViewById(R.id.viewdetails);


        }


    }

}




