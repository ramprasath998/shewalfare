package com.beebox.blood.shewalfare;

/**
 * Created by rampr on 4/11/2016.
 */
public class My_history {

    String id;
    String date;
    String hospitalname;
    String units;



    public My_history()
    {

    }

    public My_history(String id,String date,String hospitalname,String units)
    {
        this.id = id;
        this.date = date;
        this.hospitalname = hospitalname;
        this.units = units;

    }

    public My_history(String date,String hospitalname,String units)
    {
        this.date = date;
        this.units = units;
        this.hospitalname = hospitalname;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String  id)
    {
        this.id = id;
    }


    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }



    public String getHospitalname()
    {
        return this.hospitalname;
    }

    public void setHospitalname(String hospitalname)
    {
        this.hospitalname = hospitalname;
    }



    public String getUnits()
    {
        return this.units;
    }

    public void setUnits(String units)
    {
        this.units = units;
    }




}
