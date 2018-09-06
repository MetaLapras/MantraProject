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
    List<WorkerModel> workerList ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    IMyAPI mService;
   MannualAttendencePOJO mannualAttendencePOJO;
    Database database;


    public MannualAttendenceAdapter(Activity activity, List<WorkerModel> workerList) {
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
        final WorkerModel workers = workerList.get(position);
        holder.MtxtWorkerName.setText("Name :- " + workers.getName().toString());
        holder.MtxtWorkerId.setText("Worker ID :- " + workers.getAdharcard_id().toString());
       // holder.txtWorkerGender.setText("Gender :- " + workers.getGender().toString());
        holder.MtxtWorkerNumber.setText("Mobile No :- " + workers.getContact1().toString());
       // holder.txtWorkerNumber2.setText("Alternate No :- " + workers.getContact2().toString());*/

        workerid = workers.getWorkerId();
       /* perAddId = workers.getPermanentAddressId();
        curAddId = workers.getCurrentAddressId();
        bankId = workers.getBankId();*/
        //init service
        mService = Common.getApi();

        workers.setId(workerList.get(position).getId());

        //   holder.circleImageViewPhoto.setImageURI(Uri.parse(workers.getImageUrl().toString()));
        if(workers.getImageUrl().toString().contains("images/workers")){
            String Url = Common.BASE_URL+ workers.getImageUrl().toString();
            Glide.with(mContext)
                    .load(Url) // image url
                    .into(holder.McircleImageViewPhoto) ; // imageview object
        }else {
            Glide.with(mContext)
                    .load(workers.getImageUrl().toString())
                    .into(holder.McircleImageViewPhoto);
        }


        holder.chkChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                mannualAttendencePOJO=new MannualAttendencePOJO();
               /* mannualAttendencePOJO.setName(MtxtWorkerName.getText().toString());
                // workerModel.setId(edt_Id.getText().toString());
                mannualAttendencePOJO.setWorker_id(MtxtWorkerId.getText().toString());
                mannualAttendencePOJO.setNumber(MtxtWorkerNumber.getText().toString());
*/
                  //  database.addToMannualAttendance(mannualAttendencePOJO);


=======
>>>>>>> a2732ab67fe52175a66760a721141978d5cd4c8b
            }
        });

    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

}
