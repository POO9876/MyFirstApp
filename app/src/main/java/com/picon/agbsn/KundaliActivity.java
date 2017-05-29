package com.picon.agbsn;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KundaliActivity extends AppCompatActivity {

    Spinner vadhunakshatra,vadhu,varnakshatra,var;
    ArrayAdapter rashiAr,nakshaAr;
    String[] rashi,naksha;
    MediaPlayer mp;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_AGBSN = "kundali";
    private static final String TAG_MATCHES = "matches";
    public static final String TAG_DOSH = "dosh";

    JSONArray product = null;

    String matches,dosh,mrashi,frashi,mNakshatra,fNakshatra;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali);

              //  tv= (TextView)findViewById(R.id.matchskundali);
                vadhunakshatra = (Spinner)findViewById(R.id.vadhunakshatra);
                vadhu =(Spinner)findViewById(R.id.vadhu);
                varnakshatra=(Spinner)findViewById(R.id.varnakshatra);
                var=(Spinner)findViewById(R.id.var);
                 rashi  =  getResources().getStringArray(R.array.rashi);



        rashiAr = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,rashi);
        vadhu.setAdapter(rashiAr);
        var.setAdapter(rashiAr);





        var.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int rashi = var.getSelectedItemPosition();

                switch (rashi)

                {


                    case 0:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {" अश्विनी  (अश्विनी)","भरणी(भरणी)","कृत्तिका 1(कृत्तिका 1)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 1:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"कृत्तिका 2,3,4 (कृत्तिका 2,3,4)","रोहिणी (रोहिणी)","म्रृगशीर्षा 1,2 (म्रृगशीर्षा 1,2)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 2:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"म्रृगशीर्षा 3,4 (म्रृगशीर्षा 3,4)","आद्रा (आद्रा)","पुनर्वसु 1,2,3 (पुनर्वसु 1,2,3)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 3:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"पुनर्वसु 4  (पुनर्वसु 4)","पुष्य (पुष्य)","आश्ळेषा (आश्लेषा)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 4:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"मघा (मघा)","पूर्व फाल्गुनी (पूर्व फाल्गुनी)","उत्तर फाल्गुनी 1 (उत्तर फाल्गुनी 1)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 5:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"उत्तर फाल्गुनी 2,3,4 (उत्तर फाल्गुनी 2,3,4)","हस्त (हस्त)","चित्रा 1,2 (चित्रा 1,2)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 6:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"चित्रा 3,4 (चित्रा 3,4)","स्वाति (स्वाति)","विशाखा 1,2,3 (विशाखा 1,2,3)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 7:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"विशाखा 4 (Visagam 4)","अनुराधा (अनुराधा)","ज्येष्ठा (ज्येष्ठा)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 8:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"मूल (मूळ)","पूर्वाषाढा (पूर्वाषाढा)","उत्तराषाढा 1 (उत्तराषाढा 1)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 9:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"उत्तराषाढा 2,3,4 (उत्तराषाढा 2,3,4)","श्रवण (श्रवण)","श्रविष्ठा 1,2 (धनिष्ठा 1,2)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 10:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"श्रविष्ठा 3,4 (धनिष्ठा 3,4)","शतभिषक् (शततारका)","पूर्वभाद्रपदा 1,2,3 (पूर्वप्रोष्ठपदा 1,2,3)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;

                    case 11:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"पूर्वभाद्रपदा 4 (पूर्वप्रोष्ठपदा 4)","उत्तरभाद्रपदा (उत्तरप्रोष्ठपदा)","रेवती (रेवती)"});
                        varnakshatra.setAdapter(nakshaAr);
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vadhu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                int rashi = vadhu.getSelectedItemPosition();

                switch (rashi)

                {


                    case 0:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {" अश्विनी  (अश्विनी)","भरणी(भरणी)","कृत्तिका 1(कृत्तिका 1)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 1:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"कृत्तिका 2,3,4 (कृत्तिका 2,3,4)","रोहिणी (रोहिणी)","म्रृगशीर्षा 1,2 (म्रृगशीर्षा 1,2)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 2:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"म्रृगशीर्षा 3,4 (म्रृगशीर्षा 3,4)","आद्रा (आद्रा)","पुनर्वसु 1,2,3 (पुनर्वसु 1,2,3)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 3:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"पुनर्वसु 4  (पुनर्वसु 4)","पुष्य (पुष्य)","आश्ळेषा (आश्लेषा)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 4:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"मघा (मघा)","पूर्व फाल्गुनी (पूर्व फाल्गुनी)","उत्तर फाल्गुनी 1 (उत्तर फाल्गुनी 1)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 5:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"उत्तर फाल्गुनी 2,3,4 (उत्तर फाल्गुनी 2,3,4)","हस्त (हस्त)","चित्रा 1,2 (चित्रा 1,2)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 6:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"चित्रा 3,4 (चित्रा 3,4)","स्वाति (स्वाति)","विशाखा 1,2,3 (विशाखा 1,2,3)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 7:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"विशाखा 4 (Visagam 4)","अनुराधा (अनुराधा)","ज्येष्ठा (ज्येष्ठा)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 8:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"मूल (मूळ)","पूर्वाषाढा (पूर्वाषाढा)","उत्तराषाढा 1 (उत्तराषाढा 1)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 9:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"उत्तराषाढा 2,3,4 (उत्तराषाढा 2,3,4)","श्रवण (श्रवण)","श्रविष्ठा 1,2 (धनिष्ठा 1,2)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 10:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"श्रविष्ठा 3,4 (धनिष्ठा 3,4)","शतभिषक् (शततारका)","पूर्वभाद्रपदा 1,2,3 (पूर्वप्रोष्ठपदा 1,2,3)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;

                    case 11:
                        nakshaAr = new ArrayAdapter<String>(KundaliActivity.this,android.R.layout.simple_spinner_dropdown_item,new String[] {"पूर्वभाद्रपदा 4 (पूर्वप्रोष्ठपदा 4)","उत्तरभाद्रपदा (उत्तरप्रोष्ठपदा)","रेवती (रेवती)"});
                        vadhunakshatra.setAdapter(nakshaAr);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }

    public void matchKundali(View v)
    {


        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {



         frashi =  String.valueOf(vadhu.getSelectedItemPosition()+1);
            mrashi =  String.valueOf(var.getSelectedItemPosition()+1);
            fNakshatra =  String.valueOf(vadhunakshatra.getSelectedItemPosition()+1);
            mNakshatra =  String.valueOf(varnakshatra.getSelectedItemPosition()+1);


         /*  frashi =  vadhu.getSelectedItem().toString();
            mrashi =  var.getSelectedItem().toString();
            fNakshatra =  vadhunakshatra.getSelectedItem().toString();
            mNakshatra =  varnakshatra.getSelectedItem().toString();*/


        new PostDat().execute();


        }
        else
        {
            Toast.makeText(KundaliActivity.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }


    }

    private class PostDat extends AsyncTask<String, String, String> {

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(KundaliActivity.this);
            pDialog.setMessage("Loading kundali. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            try {


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("fRashi", frashi));
                params.add(new BasicNameValuePair("mRashi", mrashi));
                params.add(new BasicNameValuePair("fNakshra", fNakshatra));
                params.add(new BasicNameValuePair("mNakshra", mNakshatra));

                String url = "http://agbsnbrahminparichay.com/agbsn/notifications/kundalimatch.php";

                Log.e("kundali!", frashi+fNakshatra+mrashi+mNakshatra);

                JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
                product = json.getJSONArray(TAG_AGBSN);

                Log.e("task get!", product.toString());


                //  for (int i = 0; i < product.length(); i++) {
                JSONObject c = product.getJSONObject(0);

                matches = c.getString(TAG_MATCHES);
                dosh = c.getString(TAG_DOSH);


                //}


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();

            myDailog();
          //  tv.setText("गुण मिलान : "+matches+"  "+"गुण दोष : "+dosh);
           // Toast.makeText(KundaliActivity.this, "matching :"+matches+"dosh :"+dosh, Toast.LENGTH_LONG).show();



        }

    }

    public void myDailog()
    {
    // custom dialog
    final Dialog dialog = new Dialog(this);
    dialog.setContentView(R.layout.custom);
    //dialog.setTitle("कुंडली मिलान");
        dialog.setTitle("Kundali Match");

    // set the custom dialog components - text, image and button
    TextView text = (TextView) dialog.findViewById(R.id.text);
    text.setText("गुण मिलान : "+matches+"  "+"गुण दोष : "+dosh);

    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
    // if button is clicked, close the custom dialog
    dialogButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
          //  mp.stop();
        }
    });

    dialog.show();
}

}
