package com.picon.agbsn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openChoudhadiya(View v)
    {
        Intent i = new Intent(this,ChogariyaActivity.class);
        startActivity(i);
    }
    public void openPandit(View v)
    {
        Intent i = new Intent(this,PanditList.class);
        startActivity(i);
    }
    public void openPoojan(View v)
    {
        Intent i = new Intent(this,PoojaList.class);
        startActivity(i);
    }
    public void searchHotel(View v)
    {
        Intent i = new Intent(this,HotelList.class);
        startActivity(i);
    }
    public void openKundali(View v)
    {
        Intent i = new Intent(this,KundaliActivity.class);
        startActivity(i);
    }
    public void openGeet(View v)
    {
        Intent i = new Intent(this,GeetActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
