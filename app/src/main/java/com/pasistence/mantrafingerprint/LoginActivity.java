package com.pasistence.mantrafingerprint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dd.processbutton.iml.ActionProcessButton;

public class LoginActivity extends AppCompatActivity {
   Context mContext ;

   ActionProcessButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialisation
        mInit();
    }

    private void mInit() {
        mContext = LoginActivity.this;
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
    }
}
