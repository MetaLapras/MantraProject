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
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.ViewHolder.CustomeWorkerAttendenceHolder;
import com.pasistence.mantrafingerprint.ViewHolder.WorkerViewHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomeWorkerAttendenceAdapter extends RecyclerView.Adapter<CustomeWorkerAttendenceHolder>{

    Context mContext;
    Activity activity;
    List<Attendance> attendances ;
    public static String TAG = "adaper -->";
    public CustomeWorkerAttendenceAdapter(Activity activity, List<Attendance> attendances) {
        this.activity = activity;
        this.attendances = attendances;
    }

    @NonNull
    @Override
    public CustomeWorkerAttendenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_worker_attendence_template,parent,false);
        mContext = activity;
        return new CustomeWorkerAttendenceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeWorkerAttendenceHolder holder, final int position) {
        final Attendance workers = attendances.get(position);
        holder.txtdate.setText(workers.getCheckInDate().toString());
      //  holder.txtwages.setText( workers.getWages().toString());
        holder.txtcheckinTime.setText(workers.getCheckInTime().toString());
        holder.txtcheckOutTime.setText( workers.getCheckOutTime().toString());

        Locale locale = new Locale("en","IN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
      //txtTotal.setText(fmt.format(total));
        try{
            holder.txtwages.setText(fmt.format(Integer.parseInt(workers.getWages().toString()))+"");
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }

}
