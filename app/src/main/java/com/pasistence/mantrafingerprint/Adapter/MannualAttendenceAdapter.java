package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
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
import com.pasistence.mantrafingerprint.Main.MannualAttendence;
import com.pasistence.mantrafingerprint.Main.ShowDetailsActivity;
import com.pasistence.mantrafingerprint.Main.WorkerDisplayList;
import com.pasistence.mantrafingerprint.Main.WorkerUpdateActivity;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIDeleteResponse;
import com.pasistence.mantrafingerprint.Models.MannualAttendencePOJO;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.ViewHolder.MannualAttendenceHolder;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MannualAttendenceAdapter extends RecyclerView.Adapter<MannualAttendenceHolder>{

    Context mContext;
    Activity activity;
    List<MannualAttendencePOJO> workerList ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    IMyAPI mService;


    public MannualAttendenceAdapter(Activity activity, List<MannualAttendencePOJO> workerList) {
        this.activity = activity;
        this.workerList = workerList;
    }

    @NonNull
    @Override
    public MannualAttendenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mannual_attendence_templete,parent,false);
        mContext = activity;
        return new MannualAttendenceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MannualAttendenceHolder holder, final int position) {
        final MannualAttendencePOJO workers = workerList.get(position);
        holder.MtxtWorkerName.setText("Name :- " + workers.getName().toString());
        holder.MtxtWorkerId.setText("Worker ID :- " + workers.getWorker_id().toString());
       // holder.txtWorkerGender.setText("Gender :- " + workers.getGender().toString());
        holder.MtxtWorkerNumber.setText("Mobile No :- " + workers.getNumber().toString());
       // holder.txtWorkerNumber2.setText("Alternate No :- " + workers.getContact2().toString());*/

        workerid = workers.getWorker_id();
       /* perAddId = workers.getPermanentAddressId();
        curAddId = workers.getCurrentAddressId();
        bankId = workers.getBankId();*/
        //init service
        mService = Common.getApi();

        workers.setId(workerList.get(position).getId());

        //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));
        /*if(workers.getImage().toString().contains("images/workers")){
            String Url = Common.BASE_URL+ workers.getImage().toString();
            Glide.with(mContext)
                    .load(Url) // image url
                    .into(holder.McircleImageViewPhoto) ; // imageview object
        }else {
            Glide.with(mContext)
                    .load(workers.getImage().toString())
                    .into(holder.McircleImageViewPhoto);
        }*/



        //  Picasso.get().load(workers.getImageUrl().toString()).into(holder.circleImageViewPhoto);


/*
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, workers.toString() );

                Intent UpdateWokerIntent = new Intent(mContext, WorkerUpdateActivity.class);
                UpdateWokerIntent.putExtra("type","edit");
                UpdateWokerIntent.putExtra("id",workers.getWorkerId());
                UpdateWokerIntent.putExtra("workers",workers);
                mContext.startActivity(UpdateWokerIntent);

                activity.finish();

                // Toast.makeText(mContext,workerList.get(position).getWorkerId().toString()+"EDIT", Toast.LENGTH_SHORT).show();

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("Delete Worker Details !");
                alertDialogBuilder.setMessage("Are you sure want to delete the Worker " + workers.getName())
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                mService.deleteWorkerDetails(
                                        workerid,
                                        PreferenceUtils.getEmployee_id(mContext),
                                        PreferenceUtils.getProject_id(mContext),
                                        bankId,
                                        perAddId,
                                        curAddId
                                ).enqueue(new Callback<APIDeleteResponse>() {
                                    @Override
                                    public void onResponse(Call<APIDeleteResponse> call, Response<APIDeleteResponse> response) {
                                        APIDeleteResponse result = response.body();
                                        if (result.isError()) {
                                            Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                            Log.e("-->", result.getError_msg());
                                        } else {
                                            Log.e("-->", result.toString());

                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<APIDeleteResponse> call, Throwable t) {

                                    }
                                });
                                new Database(mContext).deleteToWorkers(workers.getId());
                                Toast.makeText(mContext,workers.getId()+"Delete", Toast.LENGTH_SHORT).show();
                                activity.finish();
                                activity.startActivity(new Intent(mContext, WorkerDisplayList.class));

                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }
        });*/
        holder.MannualbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gettting Details Activity
                Intent workerDetails = new Intent(activity, MannualAttendence.class);
                workerDetails.putExtra("id",workers.getId());
                workerDetails.putExtra("workers",workers);
                activity.startActivity(workerDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

}
