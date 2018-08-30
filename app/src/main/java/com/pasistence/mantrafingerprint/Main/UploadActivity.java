package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.R;

import dmax.dialog.SpotsDialog;

public class UploadActivity extends AppCompatActivity  {
    Button btnWorkerAll,btnWorkerDetails, btnWorkerAllAttendence,btnWorkerDetailsAttendence,btnWorkerAllmannual,btnWorkerDetailsMannual;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mInit();
    }

    private void mInit() {
        mContext                   = UploadActivity.this;

        btnWorkerAll               = (Button)findViewById(R.id.worker_upload_all);
        btnWorkerDetails           = (Button)findViewById(R.id.worker_upload_details);
        btnWorkerAllAttendence     = (Button)findViewById(R.id.worker_attendence_upload_all);
        btnWorkerDetailsAttendence = (Button)findViewById(R.id.worker_attendence_upload_details);
        btnWorkerAllmannual        = (Button)findViewById(R.id.worker_uploadAll_amnualattendence);
        btnWorkerDetailsMannual    = (Button)findViewById(R.id.worker_uploadDetails_mannualAttendence);

        btnWorkerAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Worker Upload..... ", Toast.LENGTH_SHORT).show();
                final AlertDialog dialog = new SpotsDialog(mContext);
                dialog.show();
                dialog.setMessage("Please Wait....");
                dialog.setCancelable(false);
            }
        });

        btnWorkerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadActivity.this,UploadWorkerDetailsActivity.class);
                startActivity(intent);
            }
        });

        btnWorkerAllAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Worker Attendence Upload..... ", Toast.LENGTH_SHORT).show();
                final AlertDialog dialog = new SpotsDialog(mContext);
                dialog.show();
                dialog.setMessage("Please Wait....");
                dialog.setCancelable(false);
            }
        });

        btnWorkerDetailsAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,MatchingActivity.class);
                startActivity(intent);
            }
        });

    }

}
