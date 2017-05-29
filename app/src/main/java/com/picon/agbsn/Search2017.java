package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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


public class Search2017 extends AppCompatActivity {

    Spinner marital_statusSP,stateSP,citiesSP,occupationSP,educationSP,castSP;
    ArrayAdapter<String> adaptermaritalstatus,adaptercities,adaptereducation,adapteroccupation,adapterstate;
    String maritalstatus[],mp[],gujrat[],maharashtra[],up[],rajesthan[],statewise[],occupation[],education[],others[],chhatishgarh[];
    String blank[]= {"All"};
    String select[]= {"-Select City-"};
    ProgressDialog dialog = null;
    RadioGroup rgGender, rgMangal;
    String enteryNo, gender, manglik, city, state, profess, marital, educa, name, stat;
    EditText enterno, etname;
    public static final String ROOT_URL = "http://agbsnbrahminparichay.com/";
    CheckBox cb;
    LinearLayout aLayout;
    TextView tvvvv;

    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_ENTRY = "entry";
    private static final String TAG_ENTRYDATA = "entry";


    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_2017);

        cb = (CheckBox) findViewById(R.id.asearch);

        tvvvv = (TextView) findViewById(R.id.textViewServer);
        tvvvv.setSelected(true);
        tvvvv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvvvv.setSingleLine(true);

        aLayout = (LinearLayout) findViewById(R.id.aLayout);
        enterno = (EditText) findViewById(R.id.enterno);
        marital_statusSP = (Spinner) findViewById(R.id.marital_status);
        stateSP = (Spinner) findViewById(R.id.state);
        citiesSP = (Spinner) findViewById(R.id.cities);
        occupationSP = (Spinner) findViewById(R.id.occupation);
        educationSP = (Spinner) findViewById(R.id.education);
        rgGender = (RadioGroup) findViewById(R.id.gender);
        rgMangal = (RadioGroup) findViewById(R.id.mangal);
        etname = (EditText) findViewById(R.id.ame);

        education= getResources().getStringArray(R.array.educations);
        occupation= getResources().getStringArray(R.array.occupations);
        maritalstatus = getResources().getStringArray(R.array.maritalstatuss);
        mp= getResources().getStringArray(R.array.mps);
        statewise= getResources().getStringArray(R.array.statewises);
        maharashtra= getResources().getStringArray(R.array.maharashtras);
        rajesthan= getResources().getStringArray(R.array.rajesthans);
        up = getResources().getStringArray(R.array.ups);
        gujrat = getResources().getStringArray(R.array.gujrats);
        others = getResources().getStringArray(R.array.other);
        chhatishgarh = getResources().getStringArray(R.array.chhatishgarh);


        adaptermaritalstatus = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,maritalstatus);
        adaptereducation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,education);
        adapteroccupation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,occupation);
        adapterstate = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,statewise);


        stateSP.setAdapter(adapterstate);
        marital_statusSP.setAdapter(adaptermaritalstatus);
        occupationSP.setAdapter(adapteroccupation);
        educationSP.setAdapter(adaptereducation);

      //  dialog = new ProgressDialog(this);
       // dialog.setMessage("Uploading  Wait !!!");
       // dialog.setCancelable(false);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    aLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    aLayout.setVisibility(View.GONE);

                }
            }
        });

        stateSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int state = stateSP.getSelectedItemPosition();

                switch (state)

                {



                    case 0:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,select);
                        citiesSP.setAdapter(adaptercities);
                        break;
                    case 1:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,blank);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 2:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,mp);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 3:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,up);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 4:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,gujrat);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 5:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,maharashtra);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 6:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,rajesthan);
                        citiesSP.setAdapter(adaptercities);
                        break;


                    case 7:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,chhatishgarh);
                        citiesSP.setAdapter(adaptercities);
                        break;
                    case 8:
                        adaptercities = new ArrayAdapter<String>(Search2017.this,android.R.layout.simple_spinner_dropdown_item,others);
                        citiesSP.setAdapter(adaptercities);
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            getData();

        }
        else
        {
            Toast.makeText(Search2017.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }

    }

    public void searchRecord(View v) {

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {

            if(!cb.isChecked())
            {

                enteryNo = enterno.getText().toString().trim();
                gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();


              if(!enterno.getText().toString().equals(""))
              {

                  Intent i = new Intent(Search2017.this, ProfileActivity17.class);
                  i.putExtra("enteryNo", enteryNo);
                  i.putExtra("gender",  gender);
                  i.putExtra("year",  "2017");

                  startActivity(i);
                  finish();
              }



                else
                {

                    Toast.makeText(this, "Fill entry no. first !!", Toast.LENGTH_SHORT).show();

                }
            }
            else
            {


                if(enterno.getText().toString().equals(""))
                {

                    if(!marital_statusSP.getSelectedItem().toString().equalsIgnoreCase("-select marital status-") )
                    {

                        if(!educationSP.getSelectedItem().toString().equalsIgnoreCase("-select education-")){

                            if(!occupationSP.getSelectedItem().toString().equalsIgnoreCase("-select occupation-"))
                            {
                                if(!stateSP.getSelectedItem().toString().equalsIgnoreCase("-select state-"))
                                {

                                    if(!citiesSP.getSelectedItem().toString().equalsIgnoreCase("-select district-"))
                                    {


                                        enteryNo = enterno.getText().toString().trim();
                                        gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
                                        name = etname.getText().toString();
                                        manglik = ((RadioButton) findViewById(rgMangal.getCheckedRadioButtonId())).getText().toString();
                                        marital = marital_statusSP.getSelectedItem().toString();
                                        state = stateSP.getSelectedItem().toString();
                                        city = citiesSP.getSelectedItem().toString();
                                        educa = educationSP.getSelectedItem().toString();
                                        profess = occupationSP.getSelectedItem().toString();

                                        System.out.println(name+gender+manglik+marital+state+city+educa+profess);
                                        Intent i = new Intent(Search2017.this, ProfileActivity17.class);
                                        i.putExtra("enteryNo", enteryNo);
                                        i.putExtra("gender",  gender);
                                        i.putExtra("year",  "2017");
                                        i.putExtra("manglik",  manglik);
                                        i.putExtra("marital",  marital);
                                        i.putExtra("city",  city);
                                        i.putExtra("state",  state);
                                        i.putExtra("profess",  profess);
                                        i.putExtra("educa",  educa);
                                        i.putExtra("name",  name);
                                        Log.d("manglik",manglik);
                                        startActivity(i);
                                        finish();


                                    }
                                    else
                                    {
                                        Toast.makeText(this, "select city for searching !!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(this, "select state for searching !!", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(this, "select Occupation for searching !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(this, "select Education for searching !!", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else
                    {
                        Toast.makeText(this, "select marital status for searching !!", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {

                    Toast.makeText(this, "Don't fill entry no. !!", Toast.LENGTH_SHORT).show();

                }




            }


        }
        else
        {
            Toast.makeText(Search2017.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }


    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_ENTRY);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(0);
                String newsdata = c.getString(TAG_ENTRYDATA);
                Log.d("entry",newsdata);
                tvvvv.setText(newsdata);

            }

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
                progressDialog = ProgressDialog.show(Search2017.this, "Loading Data", "Please wait...",false,false);
            }

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://agbsnbrahminparichay.com/agbsn/entrylayout.php");

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
