package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class PoojaList extends ActionBarActivity {

    String myJSON;
    TextView tv;
    private static final String TAG_RESULTS = "poojan";
    private static final String TAG_NAME = "name";
    private static final String TAG_QUAINTITY = "quantity";
    private static final String TAG_UNIT = "unit";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja_list);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            getData();

        }
        else
        {
            Toast.makeText(PoojaList.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }

    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String quantity = c.getString(TAG_QUAINTITY);
                String unit = c.getString(TAG_UNIT);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAME,name);
                persons.put(TAG_QUAINTITY,quantity);
                persons.put(TAG_UNIT,unit);
                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(PoojaList.this, personList, R.layout.poojaitem, new String[]{TAG_NAME,TAG_QUAINTITY,TAG_UNIT}, new int[]{ R.id.ppname, R.id.ppquantiy,R.id.ppunit});

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(PoojaList.this, "Loading Data", "Please wait...",false,false);
            }



            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://agbsnbrahminparichay.com/agbsn/pooja.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                progressDialog.dismiss();
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

}