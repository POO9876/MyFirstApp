package com.picon.agbsn;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog dialog = null;
    private JSONObject jsonObject;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    public static final String ROOT_URL = "http://agbsnbrahminparichay.com";
    private int STORAGE_PERMISSION_CODE = 23;
    public static final String REGISTER_URL = "http://selfiewithrangoli.com/register.php";

    private int mYear, mMonth, mDay, mHour, mMinute;


    Spinner marital_statusSP,stateSP,citiesSP,occupationSP,educationSP,castSP,idcardSP;
    ArrayAdapter<String> adaptermaritalstatus,adaptercities,adaptercast,adaptereducation,adapteroccupation,adapterstate,adaptercard;
    String maritalstatus[],mp[],gujrat[],maharashtra[],up[],rajesthan[],statewise[],occupation[],education[],cast[],others[],chhatishgarh[],cards[];
    String blank[]= {"-select state first-"};
    String name,fname,enNumer,heigh,address,mobile1,mobile2,otherEduc,birthT,birthD,birthP,
            gender,manglik,city,state,profession,educ,caste,marital,encodedImage,idcardsp,idcardno;


    EditText etBdate,etBplace,etBtime,etFullname,etFathername,etHeight,etAddress,etMobile1,etMobile2,etOtherEduc,etCardNo;
    RadioGroup rgGender,rgManglik;
    ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);



        etFullname =(EditText)findViewById(R.id.fullname);
        etCardNo =(EditText)findViewById(R.id.idnumber);
        etFathername =(EditText)findViewById(R.id.fathername);
        etAddress =(EditText)findViewById(R.id.fulladdress);
        etBtime =(EditText)findViewById(R.id.btime);
        etBdate =(EditText)findViewById(R.id.bdate);
        etBplace =(EditText)findViewById(R.id.bplace);
        etOtherEduc =(EditText)findViewById(R.id.othereducation);
        etHeight =(EditText)findViewById(R.id.height);
        etMobile1 =(EditText)findViewById(R.id.m1);
        etMobile2 =(EditText)findViewById(R.id.m2);
        etCardNo =(EditText)findViewById(R.id.idnumber);

        rgGender = (RadioGroup)findViewById(R.id.gender);
        rgManglik = (RadioGroup)findViewById(R.id.manglik);


        etBdate.setText(mDay + "-" + (mMonth + 1) + "-" + mYear);
        etBtime.setText(mHour + ":" + mMinute);


      /*  rbMale = (RadioButton)findViewById(R.id.male);
        rbFemale = (RadioButton)findViewById(R.id.female);
        rbYes = (RadioButton)findViewById(R.id.yes);
        rbNo = (RadioButton)findViewById(R.id.no);*/

        profile =(ImageView)findViewById(R.id.profile);

        marital_statusSP = (Spinner)findViewById(R.id.marital_status);
        stateSP = (Spinner)findViewById(R.id.cities);
        citiesSP = (Spinner)findViewById(R.id.state);
        occupationSP = (Spinner)findViewById(R.id.occupation);
        educationSP = (Spinner)findViewById(R.id.education);
        castSP = (Spinner)findViewById(R.id.cast);
        idcardSP = (Spinner)findViewById(R.id.idproof);


        education= getResources().getStringArray(R.array.education);
        cards= getResources().getStringArray(R.array.idcards);
        occupation= getResources().getStringArray(R.array.occupation);
        cast= getResources().getStringArray(R.array.cast);
        maritalstatus = getResources().getStringArray(R.array.maritalstatus);
        mp= getResources().getStringArray(R.array.mp);
        statewise= getResources().getStringArray(R.array.statewise);
        maharashtra= getResources().getStringArray(R.array.maharashtra);
        rajesthan= getResources().getStringArray(R.array.rajesthan);
        up = getResources().getStringArray(R.array.up);
        gujrat = getResources().getStringArray(R.array.gujrat);
        others = getResources().getStringArray(R.array.other);
        chhatishgarh = getResources().getStringArray(R.array.chhatishgarh);

        adaptermaritalstatus = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,maritalstatus);
        adaptereducation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,education);
        adapteroccupation = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,occupation);
        adaptercast = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cast);
        adapterstate = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,statewise);
        adaptercard = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cards);


        stateSP.setAdapter(adapterstate);
        marital_statusSP.setAdapter(adaptermaritalstatus);
        castSP.setAdapter(adaptercast);
        occupationSP.setAdapter(adapteroccupation);
        educationSP.setAdapter(adaptereducation);
        idcardSP.setAdapter(adaptercard);

       dialog = new ProgressDialog(this);
       dialog.setMessage("Uploading  Wait !!!");
       dialog.setCancelable(false);

      jsonObject = new JSONObject();

        stateSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int state = stateSP.getSelectedItemPosition();

                switch (state)

                {


                    case 0:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,blank);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 1:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,mp);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 2:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,up);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 3:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,gujrat);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 4:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,maharashtra);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 5:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,rajesthan);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 6:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,chhatishgarh);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 7:
                        adaptercities = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,others);
                        citiesSP.setAdapter(adaptercities);
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    public void registerForm(View v)
    {

            if(!etFullname.getText().toString().trim().equals(""))
            {
                if(!etFathername.getText().toString().trim().equals(""))
                {

                    if(!marital_statusSP.getSelectedItem().toString().equalsIgnoreCase("select marital status"))
                    {

                        if(!etAddress.getText().toString().trim().equals(""))
                        {

                            if(!stateSP.getSelectedItem().toString().equalsIgnoreCase("select state"))
                            {

                                if(!etBdate.getText().toString().trim().equals(""))
                                {
                                    if(!etBtime.getText().toString().trim().equals(""))
                                    {
                                        if(!etBplace.getText().toString().trim().equals(""))
                                        {

                                            if(!etMobile1.getText().toString().trim().equals(""))
                                            {
                                                if(!etMobile2.getText().toString().trim().equals(""))
                                                {
                                                    if(!educationSP.getSelectedItem().toString().equalsIgnoreCase("select education"))
                                                    {

                                                        if(!occupationSP.getSelectedItem().toString().equalsIgnoreCase("select Occupation"))
                                                        {

                                                            if(!etHeight.getText().toString().trim().equals(""))

                                                            {
                                                                if(!castSP.getSelectedItem().toString().equalsIgnoreCase("select sub-cast"))

                                                                {

                                                                    if(!idcardSP.getSelectedItem().toString().equalsIgnoreCase("-Select Id Proof-"))

                                                                    {


                                                                        if(!etCardNo.getText().toString().trim().equals(""))

                                                                        {


                                                                            if ((profile.getDrawable() != null)) {


                                                                                Bitmap image = ((BitmapDrawable) profile.getDrawable()).getBitmap();
                                                                               dialog.show();
                                                                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                                                                image.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);

                                                                                encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

                                                                                gender = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
                                                                                manglik = ((RadioButton)findViewById(rgManglik.getCheckedRadioButtonId())).getText().toString();

                                                                                idcardno = etCardNo.getText().toString().trim();
                                                                                name = etFullname.getText().toString().trim();
                                                                                fname = etFathername.getText().toString().trim();
                                                                                heigh = etHeight.getText().toString().trim();
                                                                                birthP = etBplace.getText().toString().trim();
                                                                                birthD = etBdate.getText().toString().trim();
                                                                                birthT = etBtime.getText().toString().trim();
                                                                                address = etAddress.getText().toString().trim();
                                                                                otherEduc = etOtherEduc.getText().toString().trim();
                                                                                mobile1 = etMobile1.getText().toString().trim();
                                                                                mobile2 = etMobile2.getText().toString().trim();

                                                                                city = citiesSP.getSelectedItem().toString();
                                                                                state = stateSP.getSelectedItem().toString();
                                                                                caste = castSP.getSelectedItem().toString();
                                                                                educ = educationSP.getSelectedItem().toString();
                                                                                marital = marital_statusSP.getSelectedItem().toString();
                                                                                profession = occupationSP.getSelectedItem().toString();
                                                                                idcardsp = idcardSP.getSelectedItem().toString();



                                                                                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                                                                                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                                                                                if(networkInfo != null && networkInfo.isConnected()==true )
                                                                                {
                                                                                  insertLocation();
                                                                                    //new DataPost().execute();

                                                                                }
                                                                                else
                                                                                {
                                                                                    Toast.makeText(RegisterActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

                                                                                }



                                                                               /* System.out.println(enNumer);
                                                                                System.out.println(name);
                                                                                System.out.println(fname);
                                                                                System.out.println(heigh);
                                                                                System.out.println(birthP);
                                                                                System.out.println(birthD);
                                                                                System.out.println(birthT);
                                                                                System.out.println(address);
                                                                                System.out.println(otherEduc);
                                                                                System.out.println(mobile1);
                                                                                System.out.println(mobile2);
                                                                                System.out.println(city);
                                                                                System.out.println(state);
                                                                                System.out.println(caste);
                                                                                System.out.println(educ);
                                                                                System.out.println(marital);
                                                                                System.out.println(profession);
                                                                                System.out.println(gender);
                                                                                System.out.println(manglik);
                                                                                System.out.println(idcardsp);
                                                                                System.out.println(idcardno);*/

                                                                            }
                                                                            else
                                                                            {
                                                                                Toast.makeText(this, "select image for uploading", Toast.LENGTH_LONG).show();
                                                                            }







                                                                        }

                                                                        else
                                                                        {
                                                                            Toast.makeText(this, "Enter valid id number", Toast.LENGTH_LONG).show();

                                                                        }



                                                                    }

                                                                    else
                                                                    {
                                                                        Toast.makeText(this, "select id proof", Toast.LENGTH_LONG).show();


                                                                    }



                                                                }

                                                                else
                                                                {
                                                                    Toast.makeText(this, "select sub caste", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                            else
                                                            {
                                                                Toast.makeText(this, "Enter valid height", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(this, "Select occupation", Toast.LENGTH_LONG).show();
                                                        }

                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(this, "Select education", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                else
                                                {
                                                    Toast.makeText(this, "Enter valid 2nd mobile", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(this, "Enter valid 1st mobile", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(this, "Enter place of birth", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(this, "Enter time of birth", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(this, "Enter date of birth", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(this, "Select state !!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(this, "Enter valid address !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Select Marital status", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Enter Father/Mother name !!!", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "Enter full name !!!", Toast.LENGTH_LONG).show();
            }
    }
    public void selectImage(View v)
    {
        if (canMakeSmores()) {

            if (isReadStorageAllowed()) {
                //If permission is already having then showing the toast
                Toast.makeText(RegisterActivity.this, "You already have the permission", Toast.LENGTH_LONG).show();
                //Existing the method with return
                return;

            }
            requestStoragePermission();
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Utils.REQCODE);
    }
    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE},STORAGE_PERMISSION_CODE);
    }
    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Utils.REQCODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            profile.setImageURI(selectedImageUri);
        }
    }
    private boolean canMakeSmores(){

        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }
    private boolean isImage() {

        Bitmap image = ((BitmapDrawable) profile.getDrawable()).getBitmap();

        return image != null;

    }

    private void insertLocation(){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        RegisterAPI api = adapter.create(RegisterAPI.class);
        api.location(

                name,fname,gender,marital,address,state,city,manglik,birthD,birthT,birthP,
                mobile1,mobile2,educ,otherEduc,profession,heigh,caste,encodedImage,idcardsp,idcardno,
                new Callback<Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                            Toast.makeText(RegisterActivity.this, ""+output, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String err = error.toString();
                        Toast.makeText(RegisterActivity.this, "Error :"+err, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        System.out.println("error on inbox sms:"+err);
                    }
                }
        );
    }


    public void birthDate(View v)
    {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        etBdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void birthTime(View v)
    {

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        etBtime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


   private class DataPost extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Please wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("fname", fname));
            params.add(new BasicNameValuePair("gender", gender));
            params.add(new BasicNameValuePair("marital", marital));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("state", state));
            params.add(new BasicNameValuePair("city", city));
            params.add(new BasicNameValuePair("manglik", manglik));
            params.add(new BasicNameValuePair("birthD", birthD));
            params.add(new BasicNameValuePair("birthT", birthT));
            params.add(new BasicNameValuePair("birthP", birthP));
            params.add(new BasicNameValuePair("mobile1", mobile1));
            params.add(new BasicNameValuePair("mobile2", mobile2));
            params.add(new BasicNameValuePair("education", educ));
            params.add(new BasicNameValuePair("otherEduc", otherEduc));
            params.add(new BasicNameValuePair("profession", profession));
            params.add(new BasicNameValuePair("height", heigh));
            params.add(new BasicNameValuePair("caste", caste));
            params.add(new BasicNameValuePair("encodedImage", encodedImage));
            params.add(new BasicNameValuePair("cards", idcardsp));
            params.add(new BasicNameValuePair("idcardno", idcardno));

            String urlThnk = "http://agbsnbrahminparichay.com/agbsn/register.php";

            JSONObject json = jsonParser.makeHttpRequest(urlThnk, "POST", params);

            return null;
        }

        protected void onPostExecute(String file_url) {

            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(i);
            finish();

        }
    }

}
