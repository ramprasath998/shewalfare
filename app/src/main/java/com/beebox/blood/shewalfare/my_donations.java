package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.transition.ActionBarTransition;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class my_donations extends Activity {


    ListView listView;

    private DBHelper mydb;

    private SimpleCursorAdapter simpleCursorAdapter;


    private ArrayList<String> userId = new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> units = new ArrayList<String>();

    private List<My_history> contactList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donations);

        listView = (ListView)findViewById(R.id.donationlist);

        int id[] = new int[]{
       //   R.id.listid,


        };

        String[] columns = new String []
                {
                  DBHelper.CONTACTS_COLUMN_ID,



                };






        mydb = new DBHelper(this);



        //ArrayList arrayList = mydb.getAllCotacts();

       // ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//        listView.setAdapter(arrayAdapter);




        int row = mydb.numberOfRows();

        if (row < 0)
        {
            Toast.makeText(getApplicationContext(),"Please add your Donations Details",Toast.LENGTH_LONG).show();
            startActivity(new Intent(my_donations.this,Bottom_Navigation.class));

        }



        contactList = mydb.getall();

        Todo_Adapter adapter = new Todo_Adapter(this,contactList);

        listView.addHeaderView(getLayoutInflater().inflate(R.layout.header,null,false));


        listView.setAdapter(adapter);



    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(my_donations.this,Bottom_Navigation.class));

    }

}
