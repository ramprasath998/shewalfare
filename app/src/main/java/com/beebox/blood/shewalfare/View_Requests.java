package com.beebox.blood.shewalfare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class View_Requests extends Activity {

    //JSON TAGS





    private static final String TAG_ID="id";
    private static final String TAG_DATE="date";
    private static final String TAG_NAME = "name";
    private static final String TAG_BLOOD = "bloodgroup";
    private static final String TAG_UNIT="unit";
    private static final String TAG_PURPOSE="purpose";
    private static final String TAG_ROOMNO ="roomno";

    private static final String TAG_HOSPITANNAME="hospitalname";
    private static final String TAG_HOSPITALPLACE="hospitalplace";

    private static final String TAG_ATTENDARPHONE="attendarphone";
    private static final String TAG_ATTENDARNAME="attendarname";



    private List<Bloods2> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Volley Request Queue
    private RequestQueue requestQueue;


    private Context context;


    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;


    public  String JSON_URL="http://www.connectwithram.in/sheblooddonation/view_request_blood.php?page=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__requests);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRequest1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);


        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        adapter = new CardAdapter2(listSuperHeroes,this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

    private JsonArrayRequest getDataFromServer(int requestCount) {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarrequest1);

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
                        Toast.makeText(View_Requests.this, "No More Request Available", Toast.LENGTH_SHORT).show();
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
            Bloods2 superHero = new Bloods2();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object

               superHero.setId(json.getInt(TAG_ID));

                superHero.setName(json.getString(TAG_NAME));
                superHero.setBlood(json.getString(TAG_BLOOD));
                superHero.setAttendarname(json.getString(TAG_ATTENDARNAME));
                superHero.setAttendarphone(json.getString(TAG_ATTENDARPHONE));
                superHero.setUnit(json.getString(TAG_UNIT));
                superHero.setHospitalname(json.getString(TAG_HOSPITANNAME));
                superHero.setHospitalplace(json.getString(TAG_HOSPITALPLACE));
                superHero.setPurpose(json.getString(TAG_ROOMNO));
                superHero.setDate(json.getString(TAG_DATE));
                superHero.setRoomno(json.getString(TAG_PURPOSE));


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

    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(View_Requests.this,Bottom_Navigation.class));
        finish();

    }


}
