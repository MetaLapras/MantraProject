package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.R;

public class SplashScreen_Activity extends AppCompatActivity  {
    Context mContext;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Initialisation
        mInit();

        if (PreferenceUtils.getSignIn(this)) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen_Activity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                    //        layout.setVisibility(View.VISIBLE);
                }
            }, 1500);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen_Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1500);
        }
    }

    private void mInit() {
        mContext = SplashScreen_Activity.this;
    }


}
