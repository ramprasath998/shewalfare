package com.beebox.blood.shewalfare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rampr on 4/10/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDonation.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_DATE = "date";
    public static final String CONTACTS_COLUMN_HOSPITALNAME = "hospitalname";
    public static final String CONTACTS_COLUMN_UNITS = "units";
    private HashMap hp;

    SQLiteDatabase db;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, date varchar,hospitalname varchar,units varchar)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

    }

    public boolean insertContact  (String date, String hospitalname, String units)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("hospitalname", hospitalname);
        contentValues.put("units", units);

        db.insert("contacts", null, contentValues);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String date, String hospitalname, String units)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("hospitalname",hospitalname);
        contentValues.put("units",units);
               db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_DATE)));
            res.moveToNext();
        }
        return array_list;
    }


    public List<My_history>getall()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        List<My_history> arraylist = new ArrayList<My_history>();


        Cursor cursor = db.rawQuery("select * from contacts",null);


        if (cursor.moveToFirst()) {
            do {
                My_history phoneBook = new My_history();

                phoneBook.setId(cursor.getString(0));
                phoneBook.setDate(cursor.getString(1));
                phoneBook.setHospitalname(cursor.getString(2));
                phoneBook.setUnits(cursor.getString(3));

                arraylist.add(phoneBook);
            } while (cursor.moveToNext());
        }
        return arraylist;



    }

    




}
