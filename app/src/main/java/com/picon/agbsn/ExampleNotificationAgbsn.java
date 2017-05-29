package com.picon.agbsn;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class ExampleNotificationAgbsn extends Application {


    Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = ExampleNotificationAgbsn.this.getApplicationContext();

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.WARN);

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .autoPromptLocation(true)
                .init();





    }



    public class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
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

            Intent notificationIntent = new Intent(context, NotificationActivity.class);
            // set intent so it does not start a new activity

            startActivity(notificationIntent);

           /* notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
           // Intent i = new Intent(this,NotificationActivity.class);*/


        }
    }
}