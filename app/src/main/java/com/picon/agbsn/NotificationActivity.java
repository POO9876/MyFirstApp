package com.picon.agbsn;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    String content,title;
    WebView web;
    Message cnt;
    ListView Contact_listview;
    Contact_Adapter cAdapter;
    TextView tv;
    DatabaseHandler db;
    ArrayList<Message> message_data = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        Contact_listview = (ListView) findViewById(R.id.list);
        Contact_listview.setItemsCanFocus(false);
        Contact_listview.setDividerHeight(3);
        db = new DatabaseHandler(this);
      /*  web = (WebView) findViewById(R.id.webView);
        web.setBackgroundColor(Color.TRANSPARENT); //for gif without background
        web.loadUrl("file:///android_asset/htmls/name.html");*/

        ArrayList<Message> contact_array_from_db = db.Get_Contacts();

        for (int i = 0; i < contact_array_from_db.size(); i++) {

            int tidno = contact_array_from_db.get(i).get_id();
            String name = contact_array_from_db.get(i).get_title();
            String mobile = contact_array_from_db.get(i).get_message();
            String email = contact_array_from_db.get(i).get_time();
            cnt = new Message();
            cnt.set_id(tidno);
            cnt.set_title(name);
            cnt.set_message(email);
            cnt.set_time(mobile);

            message_data.add(cnt);

        }

        cAdapter = new Contact_Adapter(NotificationActivity.this, R.layout.listview_row,message_data);
        Contact_listview.setAdapter(cAdapter);


        Contact_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

                Log.v("long clicked", "pos: " + (pos+Integer.parseInt("1")));


           message_data.remove(pos); //where pos is position of item I click
                TextView tv  = (TextView)arg1.findViewById(R.id.notification_id);
                int g = Integer.parseInt(tv.getText().toString());
                Log.v("textview ", "pos: " + id+" postion"+pos);
               db.Delete_Contact(g);
                Toast.makeText(NotificationActivity.this, "Message deleted", Toast.LENGTH_SHORT).show();
                cAdapter.notifyDataSetChanged();
                return true;
            }
        });



    }

    public class Contact_Adapter extends ArrayAdapter<Message> {
        Activity activity;
        int layoutResourceId;
        Message user;
        ArrayList<Message> data = new ArrayList<Message>();

        public Contact_Adapter(Activity act, int layoutResourceId,ArrayList<Message> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.id = (TextView) row.findViewById(R.id.notification_id);
                holder.title = (TextView) row.findViewById(R.id.user_name_txt);
                holder.message = (TextView) row.findViewById(R.id.user_email_txt);
                holder.time = (TextView) row.findViewById(R.id.user_mob_txt);
                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);
            holder.id.setText(String.valueOf(user.get_id()));
            holder.title.setText(user.get_title());
            holder.message.setText(user.get_message());
            holder.time.setText(user.get_time());

            return row;

        }

        class UserHolder {
            TextView title;
            TextView message;
            TextView time;
            TextView id;
        }

    }

}