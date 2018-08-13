package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.rengwuxian.materialedittext.MaterialEditText;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLayer1Next,btnLayer2Next,btnLayer3Next,btnLayer2Previous,btnLayer3Previous,btnLayer4previous,btnSubmit;
    Context mContext;
    View layer1,layer2,layer3,layer4;
    MaterialEditText edtName,edtAadharCard,edtDob,edtEmail;
    Database database;
    public static final String TAG = "wrokerReg-->";


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

        //Material Spinners
        edtName = (MaterialEditText)findViewById(R.id.edt_name);
        edtAadharCard = (MaterialEditText)findViewById(R.id.edt_aadharCard);
        edtDob = (MaterialEditText)findViewById(R.id.edt_dob);
        edtEmail = (MaterialEditText)findViewById(R.id.edt_email);

        database = new Database(mContext);

    }

    @Override
    public void onClick(View view) {
        if(view == btnLayer1Next)
        {
           /* WorkerModel workerModel = new WorkerModel("101",
                    edtName.getText().toString(),
                    edtAadharCard.getText().toString(),
                    "male",
                    edtDob.getText().toString(),
                    "",
                    "",
                    edtEmail.getText().toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "");

            database.addToWorkers(workerModel);

            Log.e(TAG, workerModel.toString() );*/


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
            Toast.makeText(mContext, "Data Submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
