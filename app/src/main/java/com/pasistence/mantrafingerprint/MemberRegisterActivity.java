package com.pasistence.mantrafingerprint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;

//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MemberRegisterActivity extends AppCompatActivity implements View.OnClickListener{

        EditText edt_firstName,edt_middleName,edt_lastName,edt_workerId,edt_contactnumber,
             edt_alternatingnumber,edt_date_of_birth,edt_emailId,edt_PermanentAddress,edt_currentAddress,edt_bank_Name,edt_account_holder;

        Button btn_submit;
        ImageView img_photo,img_fingerprint_one,img_fingerprint_two;

        Context mcontext;
        DatabaseHelper databaseHelper ;

        @Override
         protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);

        nInit();

        }

         private void nInit() {

        mcontext = MemberRegisterActivity.this;

        edt_firstName = findViewById(R.id.first_name);
        edt_middleName = findViewById(R.id.middle_name);
        edt_lastName = findViewById(R.id.last_name);
        edt_workerId = findViewById(R.id.addhar_number);
        edt_contactnumber = findViewById(R.id.contact_number);
        edt_alternatingnumber = findViewById(R.id.alternate_number);
        edt_date_of_birth = findViewById(R.id.dob);
        edt_emailId = findViewById(R.id.email);
        edt_PermanentAddress = findViewById(R.id.permnent_address);
        edt_currentAddress = findViewById(R.id.current_address);
        edt_bank_Name = findViewById(R.id.bank_name);
        edt_account_holder = findViewById(R.id.account_name);

        btn_submit = findViewById(R.id.btn_submit);

        img_photo = findViewById(R.id.imageView2);
        img_fingerprint_one = findViewById(R.id.fingerprint1);
        img_fingerprint_two = findViewById(R.id.fingerprint2);

        btn_submit.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
        if(view == btn_submit) {
            WorkerRegistrationBtn();
            Toast.makeText(mcontext, "Data Saved Sucessfully...", Toast.LENGTH_SHORT).show();
        }
        }

        private void WorkerRegistrationBtn() {

        //databaseHelper = new DatabaseHelper(mcontext);

  /*      WorkerModel workerModel = new WorkerModel();
        workerModel.setFirstName(edt_firstName.getText().toString());
        workerModel.setLastName(edt_lastName.getText().toString());
        workerModel.setEmpId(edt_workerId.getText().toString());
        workerModel.setContactNo(edt_contactnumber.getText().toString());
        workerModel.setAlternatNo(edt_alternatingnumber.getText().toString());
        workerModel.setDateOfBirth(edt_date_of_birth.getText().toString());
        workerModel.setEmpId(edt_emailId.getText().toString());
        workerModel.setPermanentAddress(edt_PermanentAddress.getText().toString());
        workerModel.setCurrentAddress(edt_currentAddress.getText().toString());
        workerModel.setBankName(edt_bank_Name.getText().toString());
        workerModel.setAccountHolder(edt_account_holder.getText().toString());



        databaseHelper.insertMemberData(workerModel);*/



    }


}
