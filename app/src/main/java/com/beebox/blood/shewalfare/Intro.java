package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Intro extends AppIntro {




    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(AppIntroFragment.newInstance("She Blood Donors","Donate Blood Save a Life",R.drawable.shewelfare,R.drawable.redgradient));

        getSupportActionBar().hide();

    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {

        startActivity(new Intent(this,Home.class));
    }

    @Override
    public void onSlideChanged() {

    }
}
