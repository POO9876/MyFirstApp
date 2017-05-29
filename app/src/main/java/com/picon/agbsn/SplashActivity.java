package com.picon.agbsn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.onesignal.OneSignal;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                    @Override
                    public void idsAvailable(String userId, String registrationId) {
                        String text = "OneSignal UserID:\n" + userId + "\n\n";

                        if (registrationId != null)
                            text += "Google Registration Id:\n" + registrationId;
                        else
                            text += "Google Registration Id:\nCould not subscribe for push";

                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        }, SPLASH_TIME_OUT);
    }

}
