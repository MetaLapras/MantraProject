package com.pasistence.mantrafingerprint.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;

import java.util.ArrayList;

public class WorkerListAdapter extends RecyclerView.Adapter<WorkerViewHolder>{

    Context mContext;
    ArrayList<WorkerModel> workerList ;


    public WorkerListAdapter(Context mContext, ArrayList<WorkerModel> workerList) {
        this.mContext = mContext;
        this.workerList = workerList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_worker_template,parent,false);
        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, final int position) {
        final WorkerModel workers = workerList.get(position);
        holder.txtWorkerName.setText("Name :- " + workers.getName().toString());
        holder.txtWorkerId.setText("Worker ID :- " + workers.getAdharcardId().toString());
        holder.txtWorkerGender.setText("Gender :- " + workers.getGender().toString());
        holder.txtWorkerNumber.setText("Mobile No :- " + workers.getContact1().toString());
        holder.txtWorkerNumber2.setText("Alternate No :- " + workers.getContact2().toString());

     //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));



        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,workerList.get(position).getWorkerId().toString()+"EDIT", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,workerList.get(position).getWorkerId().toString()+"Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gettting Details Activity

            }
        });

    }

    @Override
    public int getItemCount() {
       return workerList.size();
    }

}
