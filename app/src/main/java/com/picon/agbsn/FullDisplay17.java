package com.picon.agbsn;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class FullDisplay17 extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    String name, urls, entry_no, year, gender;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private static String TAG = "PermissionDemo";
    private static final int RECORD_REQUEST_CODE = 101;

    TextView entrynTxt, fathernameTxt, mobile1Txt, mobile2Txt, dobTxt, dopTxt, dotTxt, stateTxt, cityTxt, casteTxt, maritalTxt, manglikTxt, otherTxt, educationTxt, occupationTxt, addressTxt, heightTxt;
    String fathername, mobile1, mobile2, dob, dop, dot, state, city, caste, marital, manglik, other, education, occupation, address, height;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fulldisplay17);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        year = i.getStringExtra("year");
        gender = i.getStringExtra("gender");
        urls = i.getStringExtra("url");
        entry_no = i.getStringExtra("entry_no");
        id = i.getIntExtra("id", 0);


        //2017


        fathername = i.getStringExtra("fathername");
        address = i.getStringExtra("address");
        city = i.getStringExtra("city");
        state = i.getStringExtra("state");
        dob = i.getStringExtra("dob");
        dot = i.getStringExtra("dot");
        dop = i.getStringExtra("dop");
        marital = i.getStringExtra("marital");
        manglik = i.getStringExtra("manglik");
        caste = i.getStringExtra("caste");
        height = i.getStringExtra("height");
        mobile1 = i.getStringExtra("mobile1");
        mobile2 = i.getStringExtra("mobile2");
        education = i.getStringExtra("education");
        other = i.getStringExtra("other");
        occupation = i.getStringExtra("occupation");



        entrynTxt = (TextView) findViewById(R.id.entryn);
        fathernameTxt = (TextView) findViewById(R.id.fatherCard);
        mobile1Txt = (TextView) findViewById(R.id.mob1Card);
        mobile2Txt = (TextView) findViewById(R.id.mob2Card);
        dobTxt = (TextView) findViewById(R.id.dobCard);
        dopTxt = (TextView) findViewById(R.id.tobCard);
        dotTxt = (TextView) findViewById(R.id.pobCard);
        stateTxt = (TextView) findViewById(R.id.stateCard);
        cityTxt = (TextView) findViewById(R.id.cityCard);
        casteTxt = (TextView) findViewById(R.id.castCard);
        maritalTxt = (TextView) findViewById(R.id.maritalCard);
        manglikTxt = (TextView) findViewById(R.id.manglikCard);
        otherTxt = (TextView) findViewById(R.id.otherCard);
        educationTxt = (TextView) findViewById(R.id.mainEducationCard);
        occupationTxt = (TextView) findViewById(R.id.professionCard);
        addressTxt = (TextView) findViewById(R.id.addresCard);
        heightTxt = (TextView) findViewById(R.id.heightCard);


        Log.d("msg", entry_no + gender + year + id);

        mContext = getApplicationContext();
        mActivity = FullDisplay17.this;

        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        ImageView toolbarImage = (ImageView) findViewById(R.id.image_view);
        picassoLoader(this, toolbarImage, urls);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle(name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");
            makeRequest();
        }

        mobile1Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = mobile1Txt.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + text));
                if (ActivityCompat.checkSelfPermission(FullDisplay17.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

        mobile2Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = mobile2Txt.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + text));
                if (ActivityCompat.checkSelfPermission(FullDisplay17.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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



        entrynTxt.setText("Entry No. :"+entry_no);
        fathernameTxt.setText("Father Name :  "+fathername);
        addressTxt.setText("Address :  "+address);
        stateTxt.setText("State :  "+state);
        cityTxt.setText("City :  "+city);
        mobile1Txt.setText(mobile1);
        mobile2Txt.setText(mobile2);
        dobTxt.setText("Date of Birth :  "+dob);
        dopTxt.setText("Place of Birth :  "+dop);
        dotTxt.setText("Time of Birth :  "+dot);
        casteTxt.setText("Caste :  "+caste);
        maritalTxt.setText("Marital Status :  "+marital);
        manglikTxt.setText("Manglik :  "+manglik);
        otherTxt.setText("Other Education :  "+other);
        educationTxt.setText("Education :  "+education);
        occupationTxt.setText("Occupation :  "+occupation);
        heightTxt.setText("Height :  "+height);


       /* toolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, "_", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                RECORD_REQUEST_CODE);
    }

    public void picassoLoader(Context context, ImageView imageView, String url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ac)
                .error(R.drawable.ac)
                .into(imageView);
    }


}