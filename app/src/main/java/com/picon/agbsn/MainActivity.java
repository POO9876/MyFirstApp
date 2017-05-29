package com.picon.agbsn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.viewpagerindicator.CirclePageIndicator;

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
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    TextView tv;
    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_NEWS = "news";
    private static final String TAG_NEWSDATA = "news";

    private static final Integer[] IMAGES= {R.drawable.images1,R.drawable.images3,R.drawable.images4,R.drawable.image};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv = (TextView)findViewById(R.id.tvScroll);
        tv.setSelected(true);
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setSingleLine(true);
       //toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setSubtitle("आद्य गौड़ ब्राह्मण सेवा न्यास इंदौर");
       setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
           getData();
            AppUpdater appUpdater = new AppUpdater(this)
                .setTitleOnUpdateAvailable("Update available")
                 .setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
                .setContentOnUpdateAvailable("Check out the latest version available of my app!")
                .setTitleOnUpdateNotAvailable("Update not available")
                .setContentOnUpdateNotAvailable("No update available. Check for updates again later!")
                .setButtonUpdate("Update now?")
                .showEvery(1500)
                .setButtonDismiss("Maybe later")
                .setButtonDoNotShowAgain("Huh, not interested")
                .setIcon(R.drawable.ic_update);
            appUpdater.start();

        }
        else
        {
            Toast.makeText(MainActivity.this, "Internet Not Available", Toast.LENGTH_LONG).show();

        }

    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (isTaskRoot()){

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_dialog_alert)
                    .setTitle("Close")
                    .setMessage("Do you want to close AGBSN app ? ")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          //  mediaPlayer.stop();
                          //  status=false;
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notification) {

            Intent i = new Intent(this,NotificationActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_bank) {

            Intent i = new Intent(this,BankDetail.class);
            startActivity(i);

        }
        else if (id == R.id.nav_share) {

            sharePost();

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.picon.agbsn"));
            startActivity(intent);

        }

        else if (id == R.id.nav_feedback) {

            Intent i = new Intent(this,Feedback.class);
            startActivity(i);
        }

        else if (id == R.id.nav_about) {

            Intent i = new Intent(this,AboutUs.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void goRegister(View v)
    {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void goOther(View v)
    {
        Intent i = new Intent(this,Home.class);
        startActivity(i);
    }

    public void disp5(View view)
    {
        final  String item[]= {"2016","2017","Quick Search"};

        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle("-卐- Select year -卐-")
                .setSingleChoiceItems(item,-1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                     switch (i) {

                         case 0:

                             Intent ii = new Intent(MainActivity.this,Search2016.class);
                             startActivity(ii);
                                break;

                         case 1:
                             Intent iii = new Intent(MainActivity.this,Search2017.class);
                             startActivity(iii);

                             break;

                         case 2:

                             Intent iiii = new Intent(MainActivity.this,RefreshActivity.class);
                             startActivity(iiii);


                             break;

                     }

                    }
                })
                .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                })

                .setCancelable(true)
                .create();
        ad.show();

        final Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);
    }
      public void sharePost() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/search?q=com.com.picon.agbsn");
        startActivity(Intent.createChooser(shareIntent, "How do you want to share ?"));

    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_NEWS);

           for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(0);
                String newsdata = c.getString(TAG_NEWSDATA);
                Log.d("news",newsdata);
                tv.setText(newsdata);

          }

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
                progressDialog = ProgressDialog.show(MainActivity.this, "Loading Data", "Please wait...",false,false);
            }



            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://agbsnbrahminparichay.com/agbsn/notifications/newsubmit.php");

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
