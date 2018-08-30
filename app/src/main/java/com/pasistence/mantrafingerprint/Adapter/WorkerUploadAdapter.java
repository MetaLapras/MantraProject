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
import com.pasistence.mantrafingerprint.Main.ShowDetailsActivity;
import com.pasistence.mantrafingerprint.Main.WorkerDisplayList;
import com.pasistence.mantrafingerprint.Main.WorkerUpdateActivity;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIDeleteResponse;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerUploadAdapter  extends RecyclerView.Adapter<WorkerViewHolder>{

    Context mContext;
    Activity activity;
    List<WorkerModel> workerList ;
    public static String TAG = "adaper -->";
    String workeruploadid,uploadperAddId,uploadcurAddId,uploadbankId;
    IMyAPI mService;


    public WorkerUploadAdapter(Activity activity, List<WorkerModel> workerList) {
        this.activity = activity;
        this.workerList = workerList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_details_upload_template,parent,false);
        mContext = activity;
        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, final int position) {
        final WorkerModel workers = workerList.get(position);
        holder.txtWorkerName.setText("Name :- " + workers.getName().toString());
        holder.txtWorkerId.setText("Worker ID :- " + workers.getAdharcard_id().toString());
        holder.txtWorkerGender.setText("Gender :- " + workers.getGender().toString());
        holder.txtWorkerNumber.setText("Mobile No :- " + workers.getContact1().toString());
        holder.txtWorkerNumber2.setText("Alternate No :- " + workers.getContact2().toString());

        workeruploadid = workers.getWorkerId();
        uploadperAddId = workers.getPermanentAddressId();
        uploadcurAddId = workers.getCurrentAddressId();
        uploadbankId = workers.getBankId();
        //init service
        mService = Common.getApi();

        workers.setId(workerList.get(position).getId());

        //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));

        Glide.with(mContext)
                .load(workers.getImageUrl().toString())
                .into(holder.circleImageViewPhoto);

        //  Picasso.get().load(workers.getImageUrl().toString()).into(holder.circleImageViewPhoto);



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

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                LayoutInflater layoutInflater = activity.getLayoutInflater();
                View changePwdLayout = layoutInflater.inflate(R.layout.transfer_dialog,null);
                alertDialog.setTitle("Delete Worker Details !");
                alertDialog.setMessage("Are you sure want to delete the Worker " + workers.getName());

                alertDialog.setView(changePwdLayout);

                //set Buttons
                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mService.deleteWorkerDetails(
                                workeruploadid,
                                PreferenceUtils.getEmployee_id(mContext),
                                PreferenceUtils.getProject_id(mContext),
                                uploadbankId,
                                uploadperAddId,
                                uploadcurAddId
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
                        dialogInterface.dismiss();
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        notifyDataSetChanged();
                    }
                });

                alertDialog.show();


            }
        });
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gettting Details Activity
                Intent workerDetails = new Intent(activity, ShowDetailsActivity.class);
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
