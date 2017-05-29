package com.picon.agbsn;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

public class Search2016 extends AppCompatActivity {

    ProgressDialog dialog = null;
    Spinner marital_statusSP, stateSP, citiesSP, occupationSP, educationSP;
    RadioGroup rgGender, rgMangal;

    String enteryNo, gender, manglik, city, state, profess, marital, educa, name, stat;
    EditText enterno, etname;
    public static final String ROOT_URL = "http://agbsnbrahminparichay.com/";

    ArrayAdapter<String> adaptermaritalstatus, adaptercities, adaptereducation, adapteroccupation, adapterstate;

    String[] chennai_district = {"All","Chennai"};
    String[] gujrat_district = { "All", "Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha", "Botad", "Bharuch", "Bhavnagar", "Chhota Udaipur", "Dahod", "Devbhoomi Dwarka", "Gandhinagar", "Gandhidham", "Ghatlodiya", "Gir Somnath", "Godhra", "Jamnagar", "Junagadh", "Junagadh", "Kapadwanj", "Kheda", "Kutch", "Morvi", "Nadiad", "Navsari", "Panchmahal", "Porbandar", "Rajkot", "Surat", "Surendranagar", "Vadodara", "Veraval"};
    String[] kolkata_district = {"All","Kolkata"};
    String[] maharashtra_district = {"All","Ahmednagar", "Akola", "Amravati", "Aurangabad", "Beed", "Bhandara", "Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia", "Hingoli", "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai City", "Mumbai Suburban", "Nagpur", "Nanded", "Nandurbar", "Nashik", "Osmanabad", "Parbhani", "Pune", "Raigad", "Ratnagiri", "Sangli", "Satara", "Sindhudurg", "Solapur", "Thane", "Wardha", "Washim", "Yavatmal"};
    String[] mp_district = {"All","Agarmalwa", "Alirajpur", "Anuppur", "Ashoknagar", "Balaghat", "Barwani", "Betul", "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh", "Datia", "Dewas", "Dhar", "Dindori", "Guna", "Gwalior", "Harda", "Hoshangabad", "Indore", "Jabalpur", "Jhabua", "Katni", "Khandwa", "Khargone", "Mandla", "Mandsaur", "Morena", "Narsinghpur", "Neemuch", "Panna", "Raisen", "Rajgarh", "Ratlam", "Rewa", "Sagar", "Satna", "Sehore", "Seoni", "Singrauli", "Shahdol", "Shajapur", "Sheopur", "Shivpuri", "Sidhi", "Tikamgarh", "Ujjain", "Umaria", "Vidisha"};
    String[] rajasthan_district = {"All","Ajmer", "Alwar", "Banswara", "Baran", "Barmer", "Bharatpur", "Bhilwara", "Bikaner", "Bundi", "Chittorgarh", "Churu", "Dausa", "Dholpur", "Dungarpur", "Hanumangarh", "Jaipur", "Jaisalmer", "Jalor", "Jhalawar", "Jhunjhunu", "Jodhpur", "Karauli", "Kota", "Nagaur", "Pali", "Pratapgarh", "Rajsamand", "Sawai Madhopur", "Sikar", "Sirohi", "Sri Ganganagar", "Tonk", "Udaipur", "Rajasthan"};
    String[] up_district = {"All","Agra", "\tAligarh", "Allahabad", "Ambedkar Nagar", "Amethi", "Amroha", "Auraiya", "Azamgarh", "Baghpat", "Bahraich", "Ballia", "Balrampur", "Banda", "Barabanki", "Bareilly", "Basti", "Bijnor", "Budaun", "Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Faizabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gautam Buddha Nagar", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur", "Hamirpur", "Hardoi", "Hathras (Mahamaya Nagar)", "Jalaun", "Jaunpur", "Jhansi", "Jyotiba Phule Nagar", "Kannauj", "Kanpur Dehat (Ramabai Nagar)", "Kanpur Nagar", "Kanshiram Nagar", "Kaushambi", "Kheri", "Kushinagar", "Lalitpur", "Lucknow", "Maharajganj", "Mahoba", "Mainpuri", "Mathura", "Mau", "Meerut", "Mirzapur", "Moradabad", "Muzaffarnagar", "Panchsheel Nagar district (Hapur)", "Pilibhit", "Pratapgarh", "Raebareli", "Rampur", "Sant Kabir Nagar", "Sant Ravidas Nagar", "Shahjahanpur", "Shamli", "Shravasti", "Siddharthnagar", "Sitapur", "Sonbhadra", "Sultanpur", "Unnao", "Varanasi"};
    String[] delhi_district = {"All","North West Delhi", "South Delhi", " West Delhi", "South West Delhi", "North East Delhi", "East Delhi", "North Delhi", "Central Delhi", "New Delhi", "Shahdara", "South East Delhi"};


    String[] marital_status = {"-Select Marital Status-", "All","NEVER MARRIED", "DEVORCEE", "WIDOW", "WIDORE"};
    String[] educ = {"-Select Education-", "All","GRADUATE", "MASTER", "CA, CS, ICWA", "DOCTOR", "IT  ENGINEER", "BE (All)", "BE COMPUTER", "PHD", "NON GRADUATE", "MBA", "BE ELECTRICAL", "PERA MEDICAL", "MCA", "B ED, M ED", "LLB", "OTHERS"};
    String[] profession = {"-Select Occupation-", "All","BUSINESS", "SERVICE", "GOVT JOB", "TEACHER", "SERVICE PROVIDER", "AGRICULTERIST", "ADVOCATE", "BANK JOB", "SELF EMPLOYED", "MEDICAL STORE", "TRANSPORT", "COACHING", "PROPERTY DEAL", "MANAGER", "SCHOOL", "BUEAUTY PARLOUR", "LECTURER", "OTHERS"};

    String[] statewise = {"-Select State-", "All", "MP", "UP", "RAJASTHAN", "MAHARASTRA", "GUJRAT", "DELHI", "CHHENAI", "KOLKOTA"};

   // String[] sub_cast = {"Select Sub Cast", "ADHYA GAUD", "GOUR", "GUJAR GOUR", "KANYKUBJ", "SHRIGOUR", "JIJOTIYA", "SARYUPARY", "PALIWAL", "KHANDELWAL", "SANADHYA", "AUDICHYA", "ALL OTHER"};

    CheckBox cb;
    LinearLayout aLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2016);


        cb = (CheckBox) findViewById(R.id.asearch);

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


        adaptermaritalstatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, marital_status);
        adaptereducation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, educ);
        adapteroccupation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profession);
        adapterstate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statewise);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        stateSP.setAdapter(adapterstate);
        marital_statusSP.setAdapter(adaptermaritalstatus);
        occupationSP.setAdapter(adapteroccupation);
        educationSP.setAdapter(adaptereducation);


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    aLayout.setVisibility(View.VISIBLE);
                } else {
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
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, new String[]{"-Select District-"});
                        citiesSP.setAdapter(adaptercities);
                        break;


                    case 1:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, new String[]{"All"});
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 2:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, mp_district);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 3:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, up_district);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 4:

                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, rajasthan_district);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 5:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, maharashtra_district);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 6:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, gujrat_district);
                        citiesSP.setAdapter(adaptercities);
                        break;

                    case 7:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, delhi_district);
                        citiesSP.setAdapter(adaptercities);
                        break;
                    case 8:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, chennai_district);
                        citiesSP.setAdapter(adaptercities);
                        break;
                    case 9:
                        adaptercities = new ArrayAdapter<String>(Search2016.this, android.R.layout.simple_spinner_dropdown_item, kolkata_district);
                        citiesSP.setAdapter(adaptercities);
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                    Intent i = new Intent(Search2016.this, ProfileActivity16.class);
                    i.putExtra("enteryNo", enteryNo);
                    i.putExtra("gender",  gender);
                    i.putExtra("year",  "2016");

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

                                        System.out.println(name+manglik+marital+state+city+educa+profess);
                                        Intent i = new Intent(Search2016.this, ProfileActivity16.class);
                                        i.putExtra("enteryNo", enteryNo);
                                        i.putExtra("gender",  gender);
                                        i.putExtra("year",  "2016");
                                        i.putExtra("manglik",  manglik);
                                        i.putExtra("marital",  marital);
                                        i.putExtra("city",  city);
                                        i.putExtra("state",  state);
                                        i.putExtra("profess",  profess);
                                        i.putExtra("educa",  educa);
                                        i.putExtra("name",  name);
                                        startActivity(i);
                                        finish();


                                    }
                                    else
                                    {
                                        Toast.makeText(this, "select district for searching !!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Search2016.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }



    }


}
