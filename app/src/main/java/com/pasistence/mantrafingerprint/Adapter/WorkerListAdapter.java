package com.pasistence.mantrafingerprint.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;

import java.util.ArrayList;

public class WorkerListAdapter extends RecyclerView.Adapter<WorkerViewHolder> {

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
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
