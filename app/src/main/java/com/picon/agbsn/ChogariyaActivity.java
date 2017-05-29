package com.picon.agbsn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChogariyaActivity extends AppCompatActivity {

   TextView textDate;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16;
    Calendar c;
    SimpleDateFormat df,sfWeek,sfMonth,sfTime,sfYear,sfDate;
    String formattedDate,week,time,month,year,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chogariya);


         c = Calendar.getInstance();

        textDate = (TextView)findViewById(R.id.date);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv8 = (TextView)findViewById(R.id.tv8);
        tv9 = (TextView)findViewById(R.id.tv9);
        tv10 = (TextView)findViewById(R.id.tv10);
        tv11 = (TextView)findViewById(R.id.tv11);
        tv12 = (TextView)findViewById(R.id.tv12);
        tv13 = (TextView)findViewById(R.id.tv13);
        tv14 = (TextView)findViewById(R.id.tv14);
        tv15 = (TextView)findViewById(R.id.tv15);
        tv16 = (TextView)findViewById(R.id.tv16);


        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        sfWeek= new SimpleDateFormat("E");


        week = sfWeek.format(c.getTime());

        textDate.setText(formattedDate+","+week);

        if(week.equalsIgnoreCase("mon"))
        {

            //day
            tv1.setText("अमृत");
            tv3.setText("काल");
            tv5.setText("शुभ");
            tv7.setText("रोग");
            tv9.setText("उद्वेग");
            tv11.setText("चर");
            tv13.setText("लाभ");
            tv15.setText("अमृत");

            //night
            tv2.setText("चर");
            tv4.setText("रोग");
            tv6.setText("काल");
            tv8.setText("लाभ");
            tv10.setText("उद्वेग");
            tv12.setText("शुभ");
            tv14.setText("अमृत");
            tv16.setText("चर");



        }
        else if(week.equalsIgnoreCase("tue"))
        {

            //day
            tv1.setText("रोग");
            tv3.setText("उद्वेग");
            tv5.setText("चर");
            tv7.setText("लाभ");
            tv9.setText("अमृत");
            tv11.setText("काल");
            tv13.setText("शुभ");
            tv15.setText("रोग");

            //night
            tv2.setText("काल");
            tv4.setText("लाभ");
            tv6.setText("उद्वेग");
            tv8.setText("शुभ");
            tv10.setText("अमृत");
            tv12.setText("चर");
            tv14.setText("रोग");
            tv16.setText("काल");

        }

        else if(week.equalsIgnoreCase("wed"))
        {

            //day
            tv1.setText("लाभ");
            tv3.setText("अमृत");
            tv5.setText("काल");
            tv7.setText("शुभ");
            tv9.setText("रोग");
            tv11.setText("उद्वेग");
            tv13.setText("चर");
            tv15.setText("लाभ");

            //night
            tv2.setText("उद्वेग");
            tv4.setText("शुभ");
            tv6.setText("अमृत");
            tv8.setText("चर");
            tv10.setText("रोग");
            tv12.setText("काल");
            tv14.setText("लाभ");
            tv16.setText("उद्वेग");
        }
        else if(week.equalsIgnoreCase("thu"))
        {

            //day
            tv1.setText("शुभ");
            tv3.setText("रोग");
            tv5.setText("उद्वेग");
            tv7.setText("चर");
            tv9.setText("लाभ");
            tv11.setText("अमृत");
            tv13.setText("काल");
            tv15.setText("शुभ");

            //night
            tv2.setText("अमृत");
            tv4.setText("चर");
            tv6.setText("रोग");
            tv8.setText("काल");
            tv10.setText("लाभ");
            tv12.setText("उद्वेग");
            tv14.setText("शुभ");
            tv16.setText("अमृत");
        }
        else if(week.equalsIgnoreCase("fri"))
        {

            //day
            tv1.setText("चर");
            tv3.setText("लाभ");
            tv5.setText("अमृत");
            tv7.setText("काल");
            tv9.setText("शुभ");
            tv11.setText("रोग");
            tv13.setText("उद्वेग");
            tv15.setText("चर");

            //night
            tv2.setText("रोग");
            tv4.setText("काल");
            tv6.setText("लाभ");
            tv8.setText("उद्वेग");
            tv10.setText("शुभ");
            tv12.setText("अमृत");
            tv14.setText("चर");
            tv16.setText("रोग");
        }
        else if(week.equalsIgnoreCase("sat"))
        {

            //day
            tv1.setText("काल");
            tv3.setText("शुभ");
            tv5.setText("रोग");
            tv7.setText("उद्वेग");
            tv9.setText("चर");
            tv11.setText("लाभ");
            tv13.setText("अमृत");
            tv15.setText("काल");

            //night
            tv2.setText("लाभ");
            tv4.setText("उद्वेग");
            tv6.setText("शुभ");
            tv8.setText("अमृत");
            tv10.setText("चर");
            tv12.setText("रोग");
            tv14.setText("काल");
            tv16.setText("लाभ");
        }
        else if(week.equalsIgnoreCase("sun"))
        {

            //day
            tv1.setText("उद्वेग");
            tv3.setText("चर");
            tv5.setText("लाभ");
            tv7.setText("अमृत");
            tv9.setText("काल");
            tv11.setText("शुभ");
            tv13.setText("रोग");
            tv15.setText("उद्वेग");

            //night
            tv2.setText("शुभ");
            tv4.setText("अमृत");
            tv6.setText("चर");
            tv8.setText("रोग");
            tv10.setText("काल");
            tv12.setText("लाभ");
            tv14.setText("उद्वेग");
            tv16.setText("शुभ");
        }



    }
    public void prevDate(View v)
    {
        c.add(Calendar.DATE, -1);
        formattedDate = df.format(c.getTime());

        week = sfWeek.format(c.getTime());


        Log.v("PREVIOUS DATE : ", formattedDate);
        textDate.setText(formattedDate+","+week);


        if(week.equalsIgnoreCase("mon"))
        {

            //day
            tv1.setText("अमृत");
            tv3.setText("काल");
            tv5.setText("शुभ");
            tv7.setText("रोग");
            tv9.setText("उद्वेग");
            tv11.setText("चर");
            tv13.setText("लाभ");
            tv15.setText("अमृत");

            //night
            tv2.setText("चर");
            tv4.setText("रोग");
            tv6.setText("काल");
            tv8.setText("लाभ");
            tv10.setText("उद्वेग");
            tv12.setText("शुभ");
            tv14.setText("अमृत");
            tv16.setText("चर");



        }
        else if(week.equalsIgnoreCase("tue"))
        {

            //day
            tv1.setText("रोग");
            tv3.setText("उद्वेग");
            tv5.setText("चर");
            tv7.setText("लाभ");
            tv9.setText("अमृत");
            tv11.setText("काल");
            tv13.setText("शुभ");
            tv15.setText("रोग");

            //night
            tv2.setText("काल");
            tv4.setText("लाभ");
            tv6.setText("उद्वेग");
            tv8.setText("शुभ");
            tv10.setText("अमृत");
            tv12.setText("चर");
            tv14.setText("रोग");
            tv16.setText("काल");

        }

        else if(week.equalsIgnoreCase("wed"))
        {

            //day
            tv1.setText("लाभ");
            tv3.setText("अमृत");
            tv5.setText("काल");
            tv7.setText("शुभ");
            tv9.setText("रोग");
            tv11.setText("उद्वेग");
            tv13.setText("चर");
            tv15.setText("लाभ");

            //night
            tv2.setText("उद्वेग");
            tv4.setText("शुभ");
            tv6.setText("अमृत");
            tv8.setText("चर");
            tv10.setText("रोग");
            tv12.setText("काल");
            tv14.setText("लाभ");
            tv16.setText("उद्वेग");
        }
        else if(week.equalsIgnoreCase("thu"))
        {

            //day
            tv1.setText("शुभ");
            tv3.setText("रोग");
            tv5.setText("उद्वेग");
            tv7.setText("चर");
            tv9.setText("लाभ");
            tv11.setText("अमृत");
            tv13.setText("काल");
            tv15.setText("शुभ");

            //night
            tv2.setText("अमृत");
            tv4.setText("चर");
            tv6.setText("रोग");
            tv8.setText("काल");
            tv10.setText("लाभ");
            tv12.setText("उद्वेग");
            tv14.setText("शुभ");
            tv16.setText("अमृत");
        }
        else if(week.equalsIgnoreCase("fri"))
        {

            //day
            tv1.setText("चर");
            tv3.setText("लाभ");
            tv5.setText("अमृत");
            tv7.setText("काल");
            tv9.setText("शुभ");
            tv11.setText("रोग");
            tv13.setText("उद्वेग");
            tv15.setText("चर");

            //night
            tv2.setText("रोग");
            tv4.setText("काल");
            tv6.setText("लाभ");
            tv8.setText("उद्वेग");
            tv10.setText("शुभ");
            tv12.setText("अमृत");
            tv14.setText("चर");
            tv16.setText("रोग");
        }
        else if(week.equalsIgnoreCase("sat"))
        {

            //day
            tv1.setText("काल");
            tv3.setText("शुभ");
            tv5.setText("रोग");
            tv7.setText("उद्वेग");
            tv9.setText("चर");
            tv11.setText("लाभ");
            tv13.setText("अमृत");
            tv15.setText("काल");

            //night
            tv2.setText("लाभ");
            tv4.setText("उद्वेग");
            tv6.setText("शुभ");
            tv8.setText("अमृत");
            tv10.setText("चर");
            tv12.setText("रोग");
            tv14.setText("काल");
            tv16.setText("लाभ");
        }
        else if(week.equalsIgnoreCase("sun"))
        {

            //day
            tv1.setText("उद्वेग");
            tv3.setText("चर");
            tv5.setText("लाभ");
            tv7.setText("अमृत");
            tv9.setText("काल");
            tv11.setText("शुभ");
            tv13.setText("रोग");
            tv15.setText("उद्वेग");

            //night
            tv2.setText("शुभ");
            tv4.setText("अमृत");
            tv6.setText("चर");
            tv8.setText("रोग");
            tv10.setText("काल");
            tv12.setText("लाभ");
            tv14.setText("उद्वेग");
            tv16.setText("शुभ");
        }



    }

    public void nextDate(View v)
    {

        c.add(Calendar.DATE, 1);
        formattedDate = df.format(c.getTime());

        week = sfWeek.format(c.getTime());

        Log.v("PREVIOUS DATE : ", formattedDate);
        textDate.setText(formattedDate+","+week);
        if(week.equalsIgnoreCase("mon"))
        {

            //day
            tv1.setText("अमृत");
            tv3.setText("काल");
            tv5.setText("शुभ");
            tv7.setText("रोग");
            tv9.setText("उद्वेग");
            tv11.setText("चर");
            tv13.setText("लाभ");
            tv15.setText("अमृत");

            //night
            tv2.setText("चर");
            tv4.setText("रोग");
            tv6.setText("काल");
            tv8.setText("लाभ");
            tv10.setText("उद्वेग");
            tv12.setText("शुभ");
            tv14.setText("अमृत");
            tv16.setText("चर");



        }
        else if(week.equalsIgnoreCase("tue"))
        {

            //day
            tv1.setText("रोग");
            tv3.setText("उद्वेग");
            tv5.setText("चर");
            tv7.setText("लाभ");
            tv9.setText("अमृत");
            tv11.setText("काल");
            tv13.setText("शुभ");
            tv15.setText("रोग");

            //night
            tv2.setText("काल");
            tv4.setText("लाभ");
            tv6.setText("उद्वेग");
            tv8.setText("शुभ");
            tv10.setText("अमृत");
            tv12.setText("चर");
            tv14.setText("रोग");
            tv16.setText("काल");

        }

        else if(week.equalsIgnoreCase("wed"))
        {

            //day
            tv1.setText("लाभ");
            tv3.setText("अमृत");
            tv5.setText("काल");
            tv7.setText("शुभ");
            tv9.setText("रोग");
            tv11.setText("उद्वेग");
            tv13.setText("चर");
            tv15.setText("लाभ");

            //night
            tv2.setText("उद्वेग");
            tv4.setText("शुभ");
            tv6.setText("अमृत");
            tv8.setText("चर");
            tv10.setText("रोग");
            tv12.setText("काल");
            tv14.setText("लाभ");
            tv16.setText("उद्वेग");
        }
        else if(week.equalsIgnoreCase("thu"))
        {

            //day
            tv1.setText("शुभ");
            tv3.setText("रोग");
            tv5.setText("उद्वेग");
            tv7.setText("चर");
            tv9.setText("लाभ");
            tv11.setText("अमृत");
            tv13.setText("काल");
            tv15.setText("शुभ");

            //night
            tv2.setText("अमृत");
            tv4.setText("चर");
            tv6.setText("रोग");
            tv8.setText("काल");
            tv10.setText("लाभ");
            tv12.setText("उद्वेग");
            tv14.setText("शुभ");
            tv16.setText("अमृत");
        }
        else if(week.equalsIgnoreCase("fri"))
        {

            //day
            tv1.setText("चर");
            tv3.setText("लाभ");
            tv5.setText("अमृत");
            tv7.setText("काल");
            tv9.setText("शुभ");
            tv11.setText("रोग");
            tv13.setText("उद्वेग");
            tv15.setText("चर");

            //night
            tv2.setText("रोग");
            tv4.setText("काल");
            tv6.setText("लाभ");
            tv8.setText("उद्वेग");
            tv10.setText("शुभ");
            tv12.setText("अमृत");
            tv14.setText("चर");
            tv16.setText("रोग");
        }
        else if(week.equalsIgnoreCase("sat"))
        {

            //day
            tv1.setText("काल");
            tv3.setText("शुभ");
            tv5.setText("रोग");
            tv7.setText("उद्वेग");
            tv9.setText("चर");
            tv11.setText("लाभ");
            tv13.setText("अमृत");
            tv15.setText("काल");

            //night
            tv2.setText("लाभ");
            tv4.setText("उद्वेग");
            tv6.setText("शुभ");
            tv8.setText("अमृत");
            tv10.setText("चर");
            tv12.setText("रोग");
            tv14.setText("काल");
            tv16.setText("लाभ");
        }
        else if(week.equalsIgnoreCase("sun"))
        {

            //day
            tv1.setText("उद्वेग");
            tv3.setText("चर");
            tv5.setText("लाभ");
            tv7.setText("अमृत");
            tv9.setText("काल");
            tv11.setText("शुभ");
            tv13.setText("रोग");
            tv15.setText("उद्वेग");

            //night
            tv2.setText("शुभ");
            tv4.setText("अमृत");
            tv6.setText("चर");
            tv8.setText("रोग");
            tv10.setText("काल");
            tv12.setText("लाभ");
            tv14.setText("उद्वेग");
            tv16.setText("शुभ");
        }




    }


}
