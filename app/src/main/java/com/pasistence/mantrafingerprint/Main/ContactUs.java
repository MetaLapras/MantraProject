package com.pasistence.mantrafingerprint.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.pasistence.mantrafingerprint.R;

public class ContactUs extends AppCompatActivity {
     EditText contactName,contactEmail,contactNumber,contactMsg;
     Button sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mInit();
    }

    private void mInit() {
        contactName     = (EditText) findViewById(R.id.txt_Name);
        contactEmail    = (EditText)findViewById(R.id.edt_email);
        contactNumber   = (EditText)findViewById(R.id.edt_contactNumber);
        contactMsg      = (EditText)findViewById(R.id.edt_msg);

        sendbtn         = (Button)findViewById(R.id.contact_btn);


    }
}
