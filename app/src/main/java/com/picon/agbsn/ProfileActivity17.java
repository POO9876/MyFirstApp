package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity17 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    JSONParser jsonParser = new JSONParser();
     Config config;
    JSONObject jso,jsonmy;
    JSONArray array=null;

    int lenthCount;
    String name,gender,manglik,no,profess,educa,city,state,marital,txt,year;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent i = getIntent();
        no = i.getStringExtra("enteryNo");
        year = i.getStringExtra("year");
        gender = i.getStringExtra("gender");
        name = i.getStringExtra("name");
        manglik = i.getStringExtra("manglik");
        state = i.getStringExtra("state");
        city = i.getStringExtra("city");
        profess= i.getStringExtra("profess");
        educa= i.getStringExtra("educa");
        marital= i.getStringExtra("marital");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        getData();

    }


    private void getData(){
        class GetData extends AsyncTask<Void,Void,String>{
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(ProfileActivity17.this, "Loading Entries", "Please wait...",true,true);

                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            }



            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {

                    List<NameValuePair> paras = new ArrayList<NameValuePair>();

                    paras.add(new BasicNameValuePair("gender",gender ));
                    paras.add(new BasicNameValuePair("entry_no",no ));
                    paras.add(new BasicNameValuePair("year",year ));
                    paras.add(new BasicNameValuePair("manglik",manglik));
                    paras.add(new BasicNameValuePair("district",city));
                    paras.add(new BasicNameValuePair("state",state));
                    paras.add(new BasicNameValuePair("marital",marital));
                    paras.add(new BasicNameValuePair("name",name));
                    paras.add(new BasicNameValuePair("occupation",profess));
                    paras.add(new BasicNameValuePair("education",educa));

                    System.out.println(gender+no+year+manglik+city+state+marital+name+profess+educa);

                    jso = jsonParser.makeHttpRequest(Config.GET_URL, "POST", paras);


                    System.out.println(jso.get("agsbs"));

                    URL url = new URL(Config.GET_URL);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                parseJSON(s);
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    public void showData(){
        adapter = new CardAdapter17(Config.names,Config.urls,Config.entrys,Config.years,Config.genders, Config.bitmaps,
                Config.fathername,Config.address,Config.city,Config.state,Config.dob,Config.dot,Config.dop,Config.marital,
                Config.manglik,Config.caste,Config.height,Config.mobile1,Config.mobile2,Config.education,Config.other,
                Config.occupation);
        recyclerView.setAdapter(adapter);
    }

    private void parseJSON(String json){
        try {
            //  JSONObject jsonObject = new JSONObject(json);
                   array = jso.getJSONArray(Config.TAG_JSON_ARRAY);

                config = new Config(array.length());


                lenthCount = array.length();

         //   Log.d("Count",String.valueOf(lenthCount));

                for (int i = 0; i < array.length(); i++) {
                    JSONObject j = array.getJSONObject(i);
                    Config.names[i] = getName(j);
                    Config.urls[i] = getURL(j);
                    Config.entrys[i] = getEntry(j);
                    Config.years[i] = getYears(j);
                    Config.genders[i] = getGenders(j);
                    //2017

                    Config.fathername[i]=getFathername(j);
                    Config.address[i]=getAddress(j);
                    Config.city[i]=getCity(j);
                    Config.state[i]=getState(j);
                    Config.dob[i]=getDob(j);
                    Config.dot[i]=getDot(j);
                    Config.dop[i]=getDop(j);
                    Config.marital[i]=getMarital(j);
                    Config.manglik[i]=getManglik(j);
                    Config.caste[i]=getCaste(j);
                    Config.height[i]=getHeight(j);
                    Config.mobile1[i]=getMobile1(j);
                    Config.mobile2[i]=getMobile2(j);
                    Config.education[i]=getEducation(j);
                    Config.other[i]=getOther(j);
                    Config.occupation[i]=getOccupation(j);

                }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetBitmap17 gb = new GetBitmap17(this,this, Config.urls);
        gb.execute();
        Toast.makeText(this, "Total Record "+array.length()+" Found", Toast.LENGTH_SHORT).show();

    }

    private String getName(JSONObject j){
        String name = null;
        try {
            name = j.getString(Config.TAG_IMAGE_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    private String getURL(JSONObject j){
        String url = null;
        try {
            url = j.getString(Config.TAG_IMAGE_URL17);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getEntry(JSONObject j){
        String name = null;
        try {
            name = j.getString(Config.TAG_ENTRYNO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }
    private String getYears(JSONObject j){
        String year = null;
        try {
            year = j.getString(Config.TAG_YEAR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return year;
    }
    private String getGenders(JSONObject j){
        String gender = null;
        try {
            gender = j.getString(Config.TAG_GENDER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gender;
    }
    private String getFathername(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_FATHER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getMobile1(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_MOBILE1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getMobile2(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_MOBILE2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getDob(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_DOB);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getDop(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_DOP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getDot(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_DOT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getState(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_STATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getCity(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_CITY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getAddress(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_ADDRESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getEducation(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_EDUCATION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getOther(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_OTHEREDUCATION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getCaste(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_CASTE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getMarital(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_MARITAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getManglik(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_MANGLIK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getHeight(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_HEIGHT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getOccupation(JSONObject j){
        String x = null;
        try {
            x =j.getString(Config.TAG_OCCUPATION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }


}