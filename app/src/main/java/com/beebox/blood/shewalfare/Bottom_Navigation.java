package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

public class Bottom_Navigation extends AppCompatActivity {

    SimpleTabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__navigation);


        getSupportActionBar().hide();

        int[] image = {android.R.drawable.ic_input_add,android.R.drawable.ic_dialog_alert, android.R.drawable.ic_menu_view,android.R.drawable.ic_menu_search,android.R.drawable.ic_menu_manage};
        int[] color = {ContextCompat.getColor(this, R.color.bootstrap_brand_primary),  ContextCompat.getColor(this, R.color.bootstrap_brand_warning), ContextCompat.getColor(this, R.color.bootstrap_brand_info),ContextCompat.getColor(this,R.color.bootstrap_brand_success),ContextCompat.getColor(this,R.color.bootstrap_brand_danger)};



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Donor ", ContextCompat.getColor(this, R.color.bootstrap_gray), android.R.drawable.ic_input_add);

        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem("Make Request",ContextCompat.getColor(this,R.color.bootstrap_brand_success),android.R.drawable.ic_dialog_info);

        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem("View Request",ContextCompat.getColor(this,R.color.bootstrap_brand_success),android.R.drawable.ic_menu_view);




        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager2);

        tabAdapter = new SimpleTabAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabAdapter);

        bottomNavigationView.setUpWithViewPager(viewPager,color,image);



    }
    @Override
    public void onBackPressed()
    {
        finish();

    }

}
