package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Interface.UploadCallBack;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerPersonalResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.Remote.ProgressRequestBody;
import com.pasistence.mantrafingerprint.database.Database;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG ="upload-->" ;
    Button btnWorkerAll,btnWorkerDetails, btnWorkerAllAttendence,btnWorkerDetailsAttendence,btnWorkerAllmannual,btnWorkerDetailsMannual;
    Context mContext;
    ProgressDialog dialog;
    public UploadCallBack listner ;
    Database database;
    IMyAPI mService;
    List<WorkerModel>workerList = new ArrayList<WorkerModel>();
    List<BankAccount>bankList = new ArrayList<BankAccount>();
    List<Contactdetails>contactList = new ArrayList<Contactdetails>();
    List<Attendance> attendanceList = new ArrayList<Attendance>();
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

        dialog=new ProgressDialog(this);
        database = new Database(mContext);
        mService = Common.getApi();

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
            //Toast.makeText(mContext, "Worker Upload..... ", Toast.LENGTH_SHORT).show();
            if(Common.isConnectedToInterNet(mContext)){
                showWorkerUploadDialogue();
            }else{
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

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

            showAttendanceDialogue();
        }
        if(view == btnWorkerDetailsAttendence ){
            Intent intent = new Intent(mContext,UploadWorkerAttendence.class);
            startActivity(intent);
        }
    }

    private void showAttendanceDialogue() {

        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Uploading All Data....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();

     /*   final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        };
        t.start();*/
        attendanceList = database.getallTempAttendace();
        uploadAllAttendanceData(attendanceList);
    }


    private void showWorkerUploadDialogue() {

        /*dialog = new ProgressDialog(mContext);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Uploading All Data....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();*/

        workerList = database.getAllTempWorkers();
        bankList = database.getAllTempBankDetails();
        contactList = database.getAllTempAddress();

        Log.e(TAG,workerList.toString() );
        Log.e(TAG,bankList.toString() );
        Log.e(TAG,contactList.toString() );

            // Insert into Server Side
            onWorkerDetailsUpload(workerList);
            onAddressUpload(contactList);
            onBankUpload(bankList);
            onImageUplod(workerList);

    }
    private void onWorkerDetailsUpload(List<WorkerModel> workerList) {
        //JSONArray jsArray = new JSONArray(workerList);
      /*  Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(workerList);
        String stringToPost = new Gson().toJson(workerList);
        String joint = "worker :";
        Log.e(TAG, stringToPost.toString() );
        System.out.println(json);*/



    }
    private void onAddressUpload(List<Contactdetails> contactList) {

    }
    private void onBankUpload(List<BankAccount> bankList) {

    }
    private void onImageUplod(List<WorkerModel> workerList) {

    }

    private void uploadAllAttendanceData(List<Attendance> attendanceList) {

    }


}
