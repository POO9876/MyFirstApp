package com.picon.agbsn;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class PanditList extends AppCompatActivity {

    String myJSON;
    TextView tv;
    private static final String TAG_RESULTS = "pandit";
    private static final String TAG_NAME = "pname";
    private static final String TAG_MOBILE = "mobile";

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    private static String TAG = "PermissionDemo";
    private static final int RECORD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandit_list);
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
            Toast.makeText(PanditList.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }

        int permission = ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");
            makeRequest();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tv = (TextView) view.findViewById(R.id.pmobile);
                String text = tv.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + text));
                if (ActivityCompat.checkSelfPermission(PanditList.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                RECORD_REQUEST_CODE);
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String mobile = c.getString(TAG_MOBILE);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAME,name);
                persons.put(TAG_MOBILE,mobile);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(PanditList.this, personList, R.layout.pandititem, new String[]{TAG_NAME,TAG_MOBILE}, new int[]{ R.id.pname, R.id.pmobile});

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{


            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(PanditList.this, "Loading Data", "Please wait...",false,false);
            }



            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://agbsnbrahminparichay.com/agbsn/pandit.php");

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
