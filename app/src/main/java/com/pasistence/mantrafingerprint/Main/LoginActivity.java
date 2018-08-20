package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.dd.processbutton.iml.ActionProcessButton;
import com.pasistence.mantrafingerprint.R;

public class LoginActivity extends AppCompatActivity {
   Context mContext ;
   Button btn_signin;
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
        //btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        btn_signin = findViewById(R.id.btnSignIn);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,DashboardActivity.class);
                startActivity(intent);
                //LoginActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }

        });

    }

}
