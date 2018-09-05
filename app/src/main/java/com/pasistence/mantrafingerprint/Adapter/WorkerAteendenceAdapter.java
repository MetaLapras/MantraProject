package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerAttendenceHolder;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerUploadHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.util.List;

public class WorkerAteendenceAdapter extends RecyclerView.Adapter<WorkerAttendenceHolder>{

    Context mContext;
    Activity activity;
    List<Attendance> attendanceList ;
    List<WorkerModel> workerList ;

    public static String TAG = "adaper -->";
    String workerAttendenceid,attendenceperAddId,attendencecurAddId,attendencebankId;
    IMyAPI mService;


    public WorkerAteendenceAdapter(Activity activity, List<Attendance> workerList) {
        this.activity = activity;
        this.attendanceList = workerList;
    }

    @NonNull
    @Override
    public WorkerAttendenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_upload_details_attendence,parent,false);
        mContext = activity;
        return new WorkerAttendenceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerAttendenceHolder holder, final int position) {
        final Attendance attendance = attendanceList.get(position);

        workerList =  new Database(mContext).getAllWorkers(attendanceList.get(position).getWorkerId());
            for( WorkerModel workerModel : workerList){
                holder.attendenceWorkerName.setText("Name :- " + workerModel.getName().toString());
                holder.attendenceWorkerId.setText("Worker ID :- " + workerModel.getAdharcard_id().toString());
                holder.attendenceWorkerGender.setText("Gender :- " + workerModel.getGender().toString());
                holder.attendenceWorkerNumber.setText("Mobile No :- " + workerModel.getContact1().toString());
                holder.attendenceWorkerNumber2.setText("Alternate No :- " + workerModel.getContact2().toString());


                workerAttendenceid = workerModel.getWorkerId();
                attendenceperAddId = workerModel.getPermanentAddressId();
                attendencecurAddId = workerModel.getCurrentAddressId();
                attendencebankId = workerModel.getBankId();
                //init service
                mService = Common.getApi();

                attendance.setId(workerModel.getId());

                //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));

       /*         Glide.with(mContext)
                        .load(workerModel.getImageUrl().toString())
                        .into(holder.attendencecircleImageViewPhoto);*/


                if(workerModel.getImageUrl().toString().contains("images/workers")){
                    String Url = Common.BASE_URL+ workerModel.getImageUrl().toString();
                    Glide.with(mContext)
                            .load(Url) // image url
                            .into(holder.attendencecircleImageViewPhoto) ; // imageview object
                }else {
                    Glide.with(mContext)
                            .load(workerModel.getImageUrl().toString())
                            .into(holder.attendencecircleImageViewPhoto);
                }

            }


        //  Picasso.get().load(workers.getImageUrl().toString()).into(holder.circleImageViewPhoto);
        holder.btnCheckInTime.setText(attendanceList.get(position).getCheckInTime());
        holder.btnCheckOutTime.setText(attendanceList.get(position).getCheckOutTime());

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

}
