package com.picon.agbsn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class RefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = RefreshActivity.class.getSimpleName();
    LinearLayout lv;
    RadioGroup rg;
    RadioButton rb;
    String gender;
    private String URL_FEMALE = "http://agbsnbrahminparichay.com/agbsn/pagefemale.php?offset=";
    private String URL_MALE = "http://agbsnbrahminparichay.com/agbsn/pagemale.php?offset=";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListAdapter adapter;
    private List<User> movieList;
    TextView tv,upper;
    String counts;

    // initially offset will be 0, later will be updated while parsing the json
    private int offSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        listView = (ListView) findViewById(R.id.listView);
        lv = (LinearLayout) findViewById(R.id.upper);
        rg = (RadioGroup)findViewById(R.id.gender);
        tv = (TextView)findViewById(R.id.candicate);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        movieList = new ArrayList<>();
        adapter = new SwipeListAdapter(this, movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                TextView  idd = (TextView) view.findViewById(R.id.id);
                TextView name = (TextView) view.findViewById(R.id.name);
                TextView fathername = (TextView) view.findViewById(R.id.fathername);
                TextView age = (TextView) view.findViewById(R.id.age);
                TextView address = (TextView) view.findViewById(R.id.address);
                TextView city = (TextView) view.findViewById(R.id.city);
                TextView state = (TextView) view.findViewById(R.id.state);
                TextView enNumber = (TextView) view.findViewById(R.id.enNumber);
                TextView  imageurl = (TextView) view.findViewById(R.id.url);
                TextView education = (TextView) view.findViewById(R.id.education);
                TextView other = (TextView) view.findViewById(R.id.other);
                TextView mobile1 = (TextView) view.findViewById(R.id.mobile1);
                TextView mobile2 = (TextView) view.findViewById(R.id.mobile2);
                TextView occupations = (TextView) view.findViewById(R.id.occupations);
                TextView marital = (TextView) view.findViewById(R.id.marital);
                TextView height = (TextView) view.findViewById(R.id.height);
                TextView dob = (TextView) view.findViewById(R.id.dob);
                TextView dop = (TextView) view.findViewById(R.id.dop);
                TextView dot = (TextView) view.findViewById(R.id.dot);
                TextView manglik = (TextView) view.findViewById(R.id.manglik);
                TextView caste = (TextView) view.findViewById(R.id.caste);




                String enNumbertxt = enNumber.getText().toString();
                String urltxt = imageurl.getText().toString();
                String nametxt = name.getText().toString();
                String fathernametxt = fathername.getText().toString();
                String addresstxt = address.getText().toString();
                String citytxt = city.getText().toString();
                String statetxt = state.getText().toString();
                String dottxt = dot.getText().toString();
                String doptxt = dop.getText().toString();
                String dobtxt = dob.getText().toString();
                String maritaltxt = marital.getText().toString();
                String mangliktxt = manglik.getText().toString();
                String castetxt = caste.getText().toString();
                String heighttxt = height.getText().toString();
                String mobile1txt = mobile1.getText().toString();
                String mobile2txt = mobile2.getText().toString();
                String educationtxt = education.getText().toString();
                String othertxt = other.getText().toString();
                String occupationtxt = occupations.getText().toString();

                Intent ii = new Intent(view.getContext(),FullDisplay17.class);
               ii.putExtra("name",nametxt);
               ii.putExtra("entry_no",enNumbertxt);
               ii.putExtra("gender",gender);
                ii.putExtra("year","2017");
                ii.putExtra("url",urltxt);



                //2017

                //2017
                ii.putExtra("fathername",fathernametxt);
                ii.putExtra("address",addresstxt);
                ii.putExtra("city",citytxt);
                ii.putExtra("state",statetxt);
                ii.putExtra("dob",dobtxt);
                ii.putExtra("dot",dottxt);
                ii.putExtra("dop",doptxt);
                ii.putExtra("marital",maritaltxt);
                ii.putExtra("manglik",mangliktxt);
                ii.putExtra("caste",castetxt);
                ii.putExtra("height",heighttxt);
                ii.putExtra("mobile1",mobile1txt);
                ii.putExtra("mobile2",mobile2txt);
                ii.putExtra("education",educationtxt);
                ii.putExtra("other",othertxt);
                ii.putExtra("occupation",occupationtxt);


                view.getContext().startActivity(ii);
                // Toast.makeText(RefreshActivity.this, "Entry :"+enNumbertxt+"Image Url"+urltxt+"gender"+gender+"name"+nametxt, Toast.LENGTH_SHORT).show();

            }
        });


         swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
      /*  swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);


                                    }
                                }
        );*/


    }


    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {


        //Toast.makeText(this, "wait !!!", Toast.LENGTH_SHORT).show();
        if(gender.equals("male"))
        {
            fetchMale();


        }
        else
        {

            fetchFemale();
        }



    }

    public void searchRefresh(View v)
    {

        int selectedId = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(selectedId);
        gender = rb.getText().toString();
        if(gender.equals("male"))
        {
            fetchMale();

        }
        else
        {

            fetchFemale();
        }

        lv.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Pull Down to Refresh !!", Toast.LENGTH_LONG).show();
        tv.setText("Detail Of Candidate");

    }

    /**
     * Fetching movies json by making http call
     */
    private void fetchFemale() {

        // showing refresh animation before making http call

        String url= URL_FEMALE + offSet;

        swipeRefreshLayout.setRefreshing(true);

        // Volley's json array request object
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {

                            // looping through json and adding to movies list
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject movieObj = response.getJSONObject(i);


                                    String id = movieObj.getString("id");
                                    counts = movieObj.getString("count");

                                    String name = movieObj.getString("name");
                                    String fathername = movieObj.getString("fathername");
                                    String age = movieObj.getString("age");
                                    String address = movieObj.getString("address");
                                    String city = movieObj.getString("city");
                                    String state = movieObj.getString("state");
                                    String enNumber = movieObj.getString("enNumber");
                                    String imageurl = movieObj.getString("imageurl");
                                    String education = movieObj.getString("education");
                                    String other = movieObj.getString("other");
                                    String occupations = movieObj.getString("occupations");
                                    String marital = movieObj.getString("marital");
                                    String height = movieObj.getString("height");
                                    String mobile1 = movieObj.getString("mobile1");
                                    String mobile2 = movieObj.getString("mobile2");
                                    String dob = movieObj.getString("dob");
                                    String dot = movieObj.getString("dot");
                                    String dop = movieObj.getString("dop");
                                    String manglik = movieObj.getString("manglik");
                                    String caste = movieObj.getString("caste");







                                    User m = new User( id,
                                            name,
                                            fathername,
                                            age,
                                            address,
                                            city,
                                            state,
                                            enNumber,
                                            imageurl,
                                            education,
                                            other,
                                            mobile1,
                                            mobile2,
                                            occupations,
                                            marital,
                                            dob,
                                            dot,
                                            dop,
                                            height,
                                            manglik,
                                            caste);

                                    movieList.add(0, m);


                                    try {

                                        // updating offset value to highest value
                                        if ((Integer.parseInt(id) >= offSet))
                                            offSet = Integer.parseInt(id);

                                    }
                                    catch (NumberFormatException nfe) {
                                        nfe.printStackTrace();
                                    }
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }

                        // stopping swipe refresh
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);
    }
    private void fetchMale() {

        // showing refresh animation before making http call

        String url = URL_MALE + offSet;

        swipeRefreshLayout.setRefreshing(true);

        // Volley's json array request object
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {

                            // looping through json and adding to movies list
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject movieObj = response.getJSONObject(i);

                                    String id = movieObj.getString("id");
                                    counts = movieObj.getString("count");
                                    String name = movieObj.getString("name");
                                    String fathername = movieObj.getString("fathername");
                                    String age = movieObj.getString("age");
                                    String address = movieObj.getString("address");
                                    String city = movieObj.getString("city");
                                    String state = movieObj.getString("state");
                                    String enNumber = movieObj.getString("enNumber");
                                    String imageurl = movieObj.getString("imageurl");
                                    String education = movieObj.getString("education");
                                    String other = movieObj.getString("other");
                                    String occupations = movieObj.getString("occupations");
                                    String marital = movieObj.getString("marital");
                                    String height = movieObj.getString("height");
                                    String mobile1 = movieObj.getString("mobile1");
                                    String mobile2 = movieObj.getString("mobile2");
                                    String dob = movieObj.getString("dob");
                                    String dot = movieObj.getString("dot");
                                    String dop = movieObj.getString("dop");
                                    String manglik = movieObj.getString("manglik");
                                    String caste = movieObj.getString("caste");

                                    User m = new User( id,
                                            name,
                                            fathername,
                                            age,
                                            address,
                                            city,
                                            state,
                                            enNumber,
                                            imageurl,
                                            education,
                                            other,
                                            mobile1,
                                            mobile2,
                                            occupations,
                                            marital,
                                            dob,
                                            dot,
                                            dop,
                                            height,
                                            manglik,
                                            caste);

                                    movieList.add(0, m);

                                    try {

                                        // updating offset value to highest value
                                        if ((Integer.parseInt(id) >= offSet))
                                            offSet = Integer.parseInt(id);

                                    }
                                    catch (NumberFormatException nfe) {
                                        nfe.printStackTrace();
                                    }

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }

                        // stopping swipe refresh
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);
    }
}