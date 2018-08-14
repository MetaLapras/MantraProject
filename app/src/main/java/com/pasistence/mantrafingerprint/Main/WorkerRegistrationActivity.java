package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.rengwuxian.materialedittext.MaterialEditText;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLayer1Next,btnLayer2Next,btnLayer3Next,btnLayer2Previous,btnLayer3Previous,btnLayer4previous,btnSubmit;
    Context mContext;
    View layer1,layer2,layer3,layer4;
    WorkerModel workerModel;
    MaterialEditText edt_Id,edt_name,edt_aadhar_num,edt_dob,edt_email,edt_address_line1,edt_address_line2,edt_mobile_num,edt_alternate_num,edt_city,edt_pincode,edt_holder_name,
    edt_bank_ifsc_code,edt_bank_account_number,edt_bank_name;
    ImageView profile_image,img_fingerprint1,img_fingerprint2;
    Spinner spn_gender,spn_state;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);

        mInit();
        mOnclick();

    }

    private void mOnclick() {
        btnLayer1Next.setOnClickListener(this);
        btnLayer2Next.setOnClickListener(this);
        btnLayer3Next.setOnClickListener(this);
        btnLayer4previous.setOnClickListener(this);
        btnLayer3Previous.setOnClickListener(this);
        btnLayer2Previous.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }

    private void mInit() {

        mContext = WorkerRegistrationActivity.this;

        btnLayer1Next = (Button)findViewById(R.id.btn_layer1_next);
        btnLayer2Next = (Button)findViewById(R.id.btn_layer2_next);
        btnLayer3Next = (Button)findViewById(R.id.btn_layer3_next);
        btnLayer4previous = (Button)findViewById(R.id.btn_layer4_previous);
        btnLayer3Previous = (Button)findViewById(R.id.btn_layer3_previous);
        btnLayer2Previous = (Button)findViewById(R.id.btn_layer2_previous);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

         layer1  = findViewById(R.id.layer1);
         layer2 = findViewById(R.id.layer2);
         layer3 = findViewById(R.id.layer3);
         layer4 = findViewById(R.id.layer4);

         //initialsing the Component
        edt_name = findViewById(R.id.edt_name);
        edt_aadhar_num = findViewById(R.id.edt_aadharCard);
        edt_dob = findViewById(R.id.edt_dob);
        edt_email = findViewById(R.id.edt_email);
        spn_gender = findViewById(R.id.spinner_gender);
        img_fingerprint1= findViewById(R.id.fingerprint1);
        img_fingerprint2 = findViewById(R.id.fingerprint2);
        edt_address_line1 = findViewById(R.id.edt_address1);
        edt_address_line2 = findViewById(R.id.edt_address2);
        edt_mobile_num = findViewById(R.id.edt_contact_number);
        edt_alternate_num = findViewById(R.id.edt_alternate_number);
        edt_city = findViewById(R.id.edt_city);
        spn_state = findViewById(R.id.spn_state);
        edt_pincode = findViewById(R.id.edt_pincode);
        edt_holder_name = findViewById(R.id.edt_Bank_Holder_Name);
        edt_bank_ifsc_code = findViewById(R.id.edt_Bank_Ifsc_code);
        edt_bank_account_number = findViewById(R.id.edt_Bank_Account_Number);
        edt_bank_name = findViewById(R.id.edt_Bank_Name);
        profile_image = findViewById(R.id.profile_image);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLayer1Next)
        {
            layer1.setVisibility(View.INVISIBLE);
            layer2.setVisibility(View.VISIBLE);



        }
        if(view == btnLayer2Next)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer3Next)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer4.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer4previous)
        {
            layer4.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer3Previous)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer2.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer2Previous)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer1.setVisibility(View.VISIBLE);
        }
        if(view == btnSubmit)
        {
            WorkerRegistrationBtn();
            Toast.makeText(mContext, "Data Submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void WorkerRegistrationBtn() {

    }

}
