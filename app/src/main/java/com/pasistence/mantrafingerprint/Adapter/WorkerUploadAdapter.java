package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.Interface.UploadCallBack;
import com.pasistence.mantrafingerprint.Main.DashboardActivity;
import com.pasistence.mantrafingerprint.Main.LoginActivity;
import com.pasistence.mantrafingerprint.Main.ShowDetailsActivity;
import com.pasistence.mantrafingerprint.Main.WorkerDisplayList;
import com.pasistence.mantrafingerprint.Main.WorkerUpdateActivity;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIBankResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIContactResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIDeleteResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerImageResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerPersonalResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.Remote.ProgressRequestBody;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerUploadHolder;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerUploadAdapter  extends RecyclerView.Adapter<WorkerUploadHolder> implements UploadCallBack {

    Context mContext;
    Activity activity;
    List<WorkerModel> workerList ;
    public static String TAG = "adaper -->";
    String workeruploadid,uploadperAddId,uploadcurAddId,uploadbankId;
    IMyAPI mService;
    Database database;
    String per_address_id,curr_address_id,bank_id;
    ProgressDialog dialog;


    // List<WorkerModel>workerList = new ArrayList<WorkerModel>();
    List<BankAccount>bankList = new ArrayList<BankAccount>();
    List<Contactdetails>contactList = new ArrayList<Contactdetails>();
    List<Attendance> attendanceList = new ArrayList<Attendance>();


    public WorkerUploadAdapter(Activity activity, List<WorkerModel> workerList) {
        this.activity = activity;
        this.workerList = workerList;
    }

    @NonNull
    @Override
    public WorkerUploadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_details_upload_template,parent,false);
        mContext = activity;
        database = new Database(mContext);
        //init service
        mService = Common.getApi();
        return new WorkerUploadHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerUploadHolder holder, final int position) {
        final WorkerModel workers = workerList.get(position);
        holder.uploadWorkerName.setText("Name :- " + workers.getName().toString());
        holder.uploadWorkerId.setText("Worker ID :- " + workers.getAdharcard_id().toString());
        holder.uploadWorkerGender.setText("Gender :- " + workers.getGender().toString());
        holder.uploadWorkerNumber.setText("Mobile No :- " + workers.getContact1().toString());
        holder.uploadWorkerNumber2.setText("Alternate No :- " + workers.getContact2().toString());

        workeruploadid = workers.getWorkerId();
        uploadperAddId = workers.getPermanentAddressId();
        uploadcurAddId = workers.getCurrentAddressId();
        uploadbankId = workers.getBankId();

        workers.setId(workerList.get(position).getId());

        //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));

        Glide.with(mContext)
                .load(workers.getImageUrl().toString())
                .into(holder.uploadcircleImageViewPhoto);

        //workerList = new Database(mContext).getAllTempWorkers();
       // bankList = new Database(mContext).getAllTempBankDetails();
       // contactList = new Database(mContext).getAllTempAddress();


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Code Later
                if(Common.isConnectedToInterNet(mContext)){
                    onWorkerRegistration(workerList.get(position),position);
                }else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                    alertDialogBuilder.setMessage("No Internet Connection! Please Check your Internet Connection...")
                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    private void onWorkerRegistration(WorkerModel workerModel, final int position) {
        try
        {
            final android.app.AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Personal Details...");
            dialog.setCancelable(false);

            mService.workerRegistration(
                    workerModel.getName().toString(),
                    workerModel.getGender().toString(),
                    workerModel.getDob().toString(),
                    workerModel.getFingerprint1().toString(),
                    workerModel.getFingerprint2().toString(),
                    workerModel.getEmail().toString(),
                    PreferenceUtils.getProject_id(mContext).toString(),
                    workerModel.getSalary().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString(),
                    workerModel.getAdharcard_id().toString())
                    /*mService.workerRegistration(
                            "dfsdfgdsg",
                            "sdfsd",
                           "sdfsdf",
                          "sadasd",
                            "fgdfg",
                           "dfsd",
                            "1",
                            "545",
                           "2",
                            "1234852")*/
                    .enqueue(new Callback<APIWorkerPersonalResponse>() {
                        @Override
                        public void onResponse(Call<APIWorkerPersonalResponse> call, Response<APIWorkerPersonalResponse> response) {
                            APIWorkerPersonalResponse result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getWorkerModel().toString() );

                                WorkerModel worker = new WorkerModel();
                                worker = (WorkerModel) result.getWorkerModel();
                                Log.e("personal Details",worker.toString());

                                PreferenceUtils.setWorker_id(mContext,worker.getId());

                                //Save data into worker_Master SQLite
                                database.addToWorkers(worker);
                                contactList = new Database(mContext).getAllTempAddress();
                                onWorkerContactRegistration(contactList.get(position),position);
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIWorkerPersonalResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                            Log.e("error",t.getMessage());
                            t.printStackTrace();

                            dialog.dismiss();

                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void onWorkerContactRegistration(final Contactdetails contactdetails, final int position) {

        try
        {
            final android.app.AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Contact Details...");
            dialog.setCancelable(false);

            mService.insertcontactdetails(
                    contactdetails.getContact1().toString(),
                    contactdetails.getContact2().toString(),
                    contactdetails.getAddress_line_1().toString(),
                    contactdetails.getAddress_line_2().toString(),
                    contactdetails.getCity().toString(),
                    contactdetails.getPincode().toString(),
                    contactdetails.getState().toString(),
                    "India",
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    contactdetails.getType().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString())

                    .enqueue(new Callback<APIContactResponse>() {
                        @Override
                        public void onResponse(Call<APIContactResponse> call, Response<APIContactResponse> response) {
                            APIContactResponse result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getContactdetails().toString() );

                                Contactdetails contact = new Contactdetails();
                                contact = (Contactdetails) result.getContactdetails();
                                Log.e("Contact Details",contact.toString());

                                per_address_id = String.valueOf(contact.getId());

                                database.addToAddressDetails(contact);
                                database.deleteToTempAddressDetailsID(String.valueOf(contactdetails.getId()));

                                contactList = new Database(mContext).getAllTempAddress();
                                onWorkerCurrentContactRegistration(contactList.get(position+1),position);

                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIContactResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                            Log.e("error",t.getMessage());
                            t.printStackTrace();

                            dialog.dismiss();

                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();

        }


    }

    private void onWorkerCurrentContactRegistration(final Contactdetails contactdetails, final int position) {
        try
        {
            final android.app.AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Contact Details...");
            dialog.setCancelable(false);

            mService.insertcontactdetails(
                    contactdetails.getContact1().toString(),
                    contactdetails.getContact2().toString(),
                    contactdetails.getAddress_line_1().toString(),
                    contactdetails.getAddress_line_2().toString(),
                    contactdetails.getCity().toString(),
                    contactdetails.getPincode().toString(),
                    contactdetails.getState().toString(),
                    "India",
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    contactdetails.getType().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString())
                    .enqueue(new Callback<APIContactResponse>() {
                        @Override
                        public void onResponse(Call<APIContactResponse> call, Response<APIContactResponse> response) {
                            APIContactResponse result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getContactdetails().toString());

                                Contactdetails contact = new Contactdetails();
                                contact = (Contactdetails) result.getContactdetails();
                                Log.e("Contact Details",contact.toString());

                                curr_address_id = String.valueOf(contact.getId());

                                database.addToAddressDetails(contact);
                                database.deleteToTempAddressDetailsID(String.valueOf(contactdetails.getId()));
                                bankList = new Database(mContext).getAllTempBankDetails();
                                onBankDetailsRegistration(bankList.get(position),position);
                            }

                            // workerModel = result.getWorkerModel();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIContactResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                            Log.e("error",t.getMessage());
                            t.printStackTrace();

                            dialog.dismiss();

                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();

        }


    }

    private void onBankDetailsRegistration(final BankAccount bankAccount, final int position) {
        try
        {
            final android.app.AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Bank Details...");
            dialog.setCancelable(false);

            mService.insertbankdetails(
                    bankAccount.getAccount_holder_name().toString(),
                    bankAccount.getIfsc_code().toString(),
                    bankAccount.getAccount_no().toString(),
                    bankAccount.getBank_name().toString(),
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    "activate",
                    PreferenceUtils.getEmployee_id(mContext).toString())
                    .enqueue(new Callback<APIBankResponse>() {
                        @Override
                        public void onResponse(Call<APIBankResponse> call, Response<APIBankResponse> response) {
                            APIBankResponse  result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.toString() );
                                BankAccount bank = new BankAccount();
                                bank = (BankAccount) result.getBankdetails();
                                Log.e("Bank Details",bank.toString());
                                bank_id = String.valueOf(bank.getId());
                                database.addToBankDetails(bank);
                                database.deleteToTempBankDetailsID(String.valueOf(bankAccount.getId()));
                                List<WorkerModel> workerList = new Database(mContext).getAllTempWorkers();
                                WorkerRegistrationBtn(workerList.get(position),position);

                            }

                            // workerModel = result.getWorkerModel();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIBankResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                            Log.e("error",t.getMessage());
                            t.printStackTrace();

                            dialog.dismiss();

                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void WorkerRegistrationBtn(WorkerModel worker, int position) {
        WorkerModel workerModel = new WorkerModel();

        workerModel.setName(worker.getName());
        workerModel.setWorkerId(PreferenceUtils.getWorker_id(mContext).toString());
        workerModel.setAdharcard_id(worker.getAdharcard_id());
        workerModel.setDob(worker.getDob());
        workerModel.setEmail(worker.getEmail());
        workerModel.setGender(worker.getGender());
        workerModel.setPermanent_address1(worker.getPermanent_address1());
        workerModel.setCurrent_address1(worker.getCurrent_address1());
        workerModel.setContact1(worker.getContact1());
        workerModel.setContact2(worker.getContact2());
        workerModel.setCity(worker.getCity());
        workerModel.setPincode(worker.getPincode());
        workerModel.setHolder_name(worker.getHolder_name());
        workerModel.setIfsc_code(worker.getIfsc_code());
        workerModel.setAccount_number(worker.getAccount_number());
        workerModel.setBank_name(worker.getBank_name());
        workerModel.setPermanentAddressId(per_address_id);
        workerModel.setCurrentAddressId(curr_address_id);
        workerModel.setBankId(bank_id);
        workerModel.setActivation("activate");
        workerModel.setImageUrl(worker.getImageUrl());
        workerModel.setFingerprint1(worker.getFingerprint1());
        workerModel.setFingerprint2(worker.getFingerprint2());

        try{
                onImageUpload(workerModel.getImageUrl().toString());
                database.updateToWorkersMaster(workerModel);
                database.deleteToTempWorkersID(worker.getId());
                notifyDataSetChanged();

        }catch (NullPointerException e)
        {
            e.printStackTrace();
            Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void onImageUpload(String imageUri) {

        dialog = new ProgressDialog(mContext);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Uploading All Data....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();


        File file = new File(imageUri);
        Log.e(TAG, file.toString() );
        ProgressRequestBody requestBody = new ProgressRequestBody(file,this);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("uploadfile",file.getName(),requestBody);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mService.imageupload(body)
                        .enqueue(new Callback<APIWorkerImageResponse>() {
                            @Override
                            public void onResponse(Call<APIWorkerImageResponse> call, Response<APIWorkerImageResponse> response) {
                                try{
                                    APIWorkerImageResponse result = response.body();
                                    Log.e("-->",  response.body().toString());

                                    if(result.isError()) {
                                        Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                        Log.e("-->",result.getError_msg() );
                                        dialog.dismiss();
                                    }else {
                                        dialog.dismiss();
                                        // Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Log.e("-->", result.toString());

                                        mService.getWorkerDetails(
                                                result.getImageURL().toString(),
                                                PreferenceUtils.getWorker_id(mContext).toString(),
                                                PreferenceUtils.getEmployee_id(mContext).toString()
                                        ).enqueue(new Callback<WorkerModel>() {
                                            @Override
                                            public void onResponse(Call<WorkerModel> call, Response<WorkerModel> response) {
                                                WorkerModel result = response.body();
                                                if(result.isError())
                                                {
                                                    Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                                    Log.e("-->",result.getError_msg());
                                                }else {
                                                    Toast.makeText(mContext, "Worker Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                                    Log.e("-->", result.toString());
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<WorkerModel> call, Throwable t) {
                                                t.printStackTrace();
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    dialog.dismiss();
                                }

                            }

                            @Override
                            public void onFailure(Call<APIWorkerImageResponse> call, Throwable t) {
                                t.printStackTrace();
                                dialog.dismiss();
                            }
                        });
            }
        }).start();
    }

    @Override
    public void onProgressUpdate(int percetage) {
        dialog.setProgress(percetage);
    }
}
