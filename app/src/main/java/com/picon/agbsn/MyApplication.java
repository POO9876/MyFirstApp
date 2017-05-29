package com.picon.agbsn;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyApplication extends Application {

    DatabaseHandler db = new DatabaseHandler(this);
    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.WARN);

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .autoPromptLocation(true)
                .init();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

     class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String customKey;

            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
            Log.i("OneSignalExample", "Message: " + result.notification.payload.title);
            Log.i("OneSignalExample", "content: " + result.notification.payload.body);

            String title = result.notification.payload.title;
            String content = result.notification.payload.body;

            Log.d("message:",title+content);


            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Calendar calobj = Calendar.getInstance();

            //tv.setText("Message \n"+title+"\n"+content+"\n"+df.format(calobj.getTime()));

            db.Add_Message(new Message(title,content,df.format(calobj.getTime())));

           Intent notificationIntent = new Intent(mInstance, NotificationActivity.class);

            // set intent so it does not start a new activity

           /* notificationIntent.putExtra("title",title);
            notificationIntent.putExtra("content",content);*/
            startActivity(notificationIntent);

        }
    }
}