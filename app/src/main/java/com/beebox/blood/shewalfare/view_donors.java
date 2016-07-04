package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class view_donors extends Activity   {



    //JSON TAGS

    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE="phone";
    private static final String TAG_BLOOD = "blood";
    private static final String TAG_CITY ="city";



    private List<Bloods> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Volley Request Queue
    private RequestQueue requestQueue;


    private Context context;


    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;


    public  String JSON_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trials);



        String types = getIntent().getStringExtra("bloods").toString();



        if (types.equals("APositive"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=APositive&page=";
        }
        else if(types.equals("ANegative"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=ANegative&page=";
        }
        else if (types.equals("BPositive"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=BPositive&page=";
        }
        else if (types.equals("BNegative"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=BNegative&page=";
        }
        else if (types.equals("ABPositive"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=ABPositive&page=";
        }
        else if (types.equals("ABNegative"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=ABNegative&page=";
        }
        else if (types.equals("OPositive"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=OPositive&page=";
        }
        else if (types.equals("ONegative"))
        {
            JSON_URL = "http://www.connectwithram.in/sheblooddonation/trials.php?blood=ONegative&page=";
        }




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new MyLinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //Calling method to get data to fetch data

        getData();


             //Adding an scroll change listener to recyclerview

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (isLastItemDisplaying(recyclerView)) {
                    //Calling the method getdata again
                    getData();
                }
                            }
        });


       //initializing our adapter
        adapter = new CardAdapter(listSuperHeroes,this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    private JsonArrayRequest getDataFromServer(int requestCount) {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JSON_URL + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseData(response);
                        //Hiding the progressbar
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(view_donors.this, "No More Donors Available", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will get data from the web api
    private void getData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromServer(requestCount));
        //Incrementing the request counter
        requestCount++;
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Bloods superHero = new Bloods();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object

                superHero.setName(json.getString(TAG_NAME));
                superHero.setBlood(json.getString(TAG_BLOOD));
                superHero.setPhone(json.getString(TAG_PHONE));
                superHero.setCity(json.getString(TAG_CITY));



            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listSuperHeroes.add(superHero);
        }

        //Notifying the adapter that data has been added or changed
        adapter.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }


}
