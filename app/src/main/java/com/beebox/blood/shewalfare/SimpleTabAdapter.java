package com.beebox.blood.shewalfare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rampr on 2/25/2016.
 */
public class SimpleTabAdapter extends FragmentPagerAdapter {

    private int page = 5;
    private String[] tabtitle = new String[]{"Profile", "Make Requests", "View Request","Donors","FeedBack"};

    public SimpleTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {


            case 0:


                BlankFragment tab1 = new BlankFragment();
                return tab1;


            case 1:

                BlankFragment3 tab3 = new BlankFragment3();
                return tab3;

            case 2:

                BlankFragment4 tab4 = new BlankFragment4();
                return tab4;
            case 3:
                BlankFragment2 tab5 = new BlankFragment2();
                return tab5;

            case 4:
                BlankFragment5 tab2 = new BlankFragment5();
                return tab2;



        }
        return null;
        }


    @Override
    public int getCount() {
        return page;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}
