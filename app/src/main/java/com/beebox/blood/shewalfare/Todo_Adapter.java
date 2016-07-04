package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rampr on 4/11/2016.
 */
public class Todo_Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<My_history> contactList;


    public Todo_Adapter (Activity activity,List<My_history> contactList)
    {
        this.activity = activity;
        this.contactList = contactList;
    }


    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.lists, null);


        TextView ids= (TextView) convertView.findViewById(R.id.donateid);
        TextView name = (TextView) convertView.findViewById(R.id.donationhospitalname);

        TextView date = (TextView) convertView.findViewById(R.id.donationdate);
        TextView unit = (TextView) convertView.findViewById(R.id.donationunit);





        My_history list = contactList.get(position);



        ids.setText((list.getId()));

        name.setText(list.getHospitalname());

        unit.setText(list.getUnits());
        date.setText(list.getDate());




        return convertView;
    }
}
