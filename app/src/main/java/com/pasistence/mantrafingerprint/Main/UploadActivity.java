package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Interface.UploadCallBack;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener ,UploadCallBack{
    Button btnWorkerAll,btnWorkerDetails, btnWorkerAllAttendence,btnWorkerDetailsAttendence,btnWorkerAllmannual,btnWorkerDetailsMannual;
    Context mContext;
    ProgressDialog dialog;
    public UploadCallBack listner ;
    Database database;
    List<WorkerModel>workerList = new ArrayList<WorkerModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mInit();
        mOnClick();
    }

    private void mInit() {
        mContext                   = UploadActivity.this;

        btnWorkerAll               = (Button)findViewById(R.id.worker_upload_all);
        btnWorkerDetails           = (Button)findViewById(R.id.worker_upload_details);
        btnWorkerAllAttendence     = (Button)findViewById(R.id.worker_attendence_upload_all);
        btnWorkerDetailsAttendence = (Button)findViewById(R.id.worker_attendence_upload_details);
        btnWorkerAllmannual        = (Button)findViewById(R.id.worker_uploadAll_amnualattendence);
        btnWorkerDetailsMannual    = (Button)findViewById(R.id.worker_uploadDetails_mannualAttendence);

        database = new Database(mContext);
    }

    private void mOnClick() {

        btnWorkerAll.setOnClickListener(this);
        btnWorkerDetails.setOnClickListener(this);
        btnWorkerAllAttendence.setOnClickListener(this);
        btnWorkerDetailsAttendence.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnWorkerAll ){
            Toast.makeText(mContext, "Worker Upload..... ", Toast.LENGTH_SHORT).show();
            showWorkerUploadDialogue();
        }
        if(view == btnWorkerDetails ){
            Intent intent = new Intent(UploadActivity.this,UploadWorkerDetailsActivity.class);
            startActivity(intent);
        }
        if(view == btnWorkerAllAttendence ){
            Toast.makeText(mContext, "Worker Attendence Upload..... ", Toast.LENGTH_SHORT).show();
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Please Wait....");
            dialog.setCancelable(false);
        }
        if(view == btnWorkerDetailsAttendence ){
            Intent intent = new Intent(mContext,MatchingActivity.class);
            startActivity(intent);
        }
    }

    private void showWorkerUploadDialogue() {

        dialog = new ProgressDialog(mContext);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Uploading All Data....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        //workerList = database.get

    }

    @Override
    public void onProgressUpdate(int percetage) {
        dialog.setProgress(percetage);
    }

    private class ProgressUpdater implements Runnable {
        private long size;

        public ProgressUpdater(long size) {
            this.size = size;
        }


        @Override
        public void run() {
            listner.onProgressUpdate((int)(100*size-1/size));
        }
    }
}
