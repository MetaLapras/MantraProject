package com.pasistence.mantrafingerprint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen_Activity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Initialisation
        mInit();
    }

    private void mInit() {
        mContext = SplashScreen_Activity.this;
    }
}
