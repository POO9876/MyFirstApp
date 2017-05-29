package com.picon.agbsn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class FullDisplay16 extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    String name,urls,entry_no,year,gender;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    TextView entrynTxt,fathernameTxt,mobile1Txt,mobile2Txt,dobTxt,dopTxt,dotTxt,stateTxt,cityTxt,casteTxt,maritalTxt,manglikTxt,otherTxt,educationTxt,occupationTxt,addressTxt,heightTxt;
    String fathername,mobile1,mobile2,dob,dop,dot,state,city,caste,marital,manglik,other,education,occupation,address,height;

 int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fulldisplay16);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        year = i.getStringExtra("year");
        gender = i.getStringExtra("gender");
        urls = i.getStringExtra("url");
        entry_no = i.getStringExtra("entry_no");
        id = i.getIntExtra("id",0);



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


        entrynTxt = (TextView)findViewById(R.id.entryn);
        fathernameTxt = (TextView)findViewById(R.id.fatherCard);
        mobile1Txt= (TextView)findViewById(R.id.mob1Card);
        mobile2Txt= (TextView)findViewById(R.id.mob2Card);
        dobTxt= (TextView)findViewById(R.id.dobCard);
        dopTxt= (TextView)findViewById(R.id.tobCard);
        dotTxt= (TextView)findViewById(R.id.pobCard);
        stateTxt= (TextView)findViewById(R.id.stateCard);
        cityTxt= (TextView)findViewById(R.id.cityCard);
        casteTxt= (TextView)findViewById(R.id.castCard);
        maritalTxt= (TextView)findViewById(R.id.maritalCard);
        manglikTxt= (TextView)findViewById(R.id.manglikCard);
        otherTxt= (TextView)findViewById(R.id.otherCard);
        educationTxt= (TextView)findViewById(R.id.mainEducationCard);
        occupationTxt= (TextView)findViewById(R.id.professionCard);
        addressTxt= (TextView)findViewById(R.id.addresCard);
        heightTxt= (TextView)findViewById(R.id.heightCard);


        Log.d("msg",entry_no+gender+year+id);


        mContext = getApplicationContext();
        mActivity = FullDisplay16.this;

        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        ImageView toolbarImage = (ImageView) findViewById(R.id.image_view);
        picassoLoader(this, toolbarImage, urls);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle(name);



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