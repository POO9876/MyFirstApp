package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button feedbackBtn;
    String name,email,message;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    JSONParser jsonP = new JSONParser();

    WebView web;
    EditText nameF,messsageF,emailF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        nameF = (EditText)findViewById(R.id.editTextName);
        messsageF = (EditText)findViewById(R.id.editTextMessage);
        emailF = (EditText)findViewById(R.id.editTextEmail);


        feedbackBtn = (Button)findViewById(R.id.buttonSend);





        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    email =  emailF.getText().toString().trim();
                    name =  nameF.getText().toString().trim();
                    message =  messsageF.getText().toString().trim();


                    if(name.equals(""))
                    {

                        Toast.makeText(Feedback.this, "Enter valid name !!!", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                        if (!email.matches(emailPattern))
                        {
                            Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            if(message.equals("") )
                            {

                                Toast.makeText(Feedback.this, "Enter valid message !!!", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {


                                new PostDat().execute();
                            }
                        }



                    }




                }
                else
                {
                    Toast.makeText(Feedback.this, "Internet Not Available", Toast.LENGTH_LONG).show();

                }


            }
        });

        //  web = (WebView) findViewById(R.id.webView);
      //  web.setBackgroundColor(Color.TRANSPARENT); //for gif without background
      //  web.loadUrl("file:///android_asset/htmls/feedback.html");





    }

    private class PostDat extends AsyncTask<String, String, String> {

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Feedback.this);
            pDialog.setMessage("Please wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("message", message));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("email", email));

            String url = "http://agbsnbrahminparichay.com/agbsn/feedback.php";

            String urlThnk = "http://agbsnbrahminparichay.com/agbsn/sendmail.php";

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            JSONObject jso = jsonParser.makeHttpRequest(urlThnk, "POST", params);

            return null;
        }

        protected void onPostExecute(String file_url) {

            Toast.makeText(Feedback.this, "Thank You for using it !!!", Toast.LENGTH_SHORT).show();
            nameF.setText("");
            emailF.setText("");
            messsageF.setText("");
            pDialog.dismiss();

        }
    }


}
