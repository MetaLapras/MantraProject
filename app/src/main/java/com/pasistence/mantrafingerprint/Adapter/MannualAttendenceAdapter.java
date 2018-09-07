package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Interface.ItemClickListener1;
import com.pasistence.mantrafingerprint.Main.MannualAttendence;
import com.pasistence.mantrafingerprint.Models.MannualAttendencePOJO;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.ViewHolder.MannualAttendenceHolder;
import com.pasistence.mantrafingerprint.database.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MannualAttendenceAdapter extends RecyclerView.Adapter<MannualAttendenceHolder>{

    Context mContext;
    Activity activity;
    List<WorkerModel> workerList ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    IMyAPI mService;
   MannualAttendencePOJO mannualAttendencePOJO;
    Database database;

    public ArrayList<MannualAttendencePOJO> mannualList = new ArrayList<MannualAttendencePOJO>();
    public ArrayList<WorkerModel> checkedmannual = new ArrayList<WorkerModel>();


    public MannualAttendenceAdapter(Activity activity, List<WorkerModel> workerList) {
        this.activity = activity;
        this.workerList = workerList;
    }

   /* public MannualAttendenceAdapter(Context mContext, Activity activity, List<WorkerModel> workerList, String workerid, String perAddId, String curAddId, String bankId, IMyAPI mService, MannualAttendencePOJO mannualAttendencePOJO, Database database, ArrayList<MannualAttendencePOJO> mannualpojo) {
        this.mContext = mContext;
        this.activity = activity;
        this.workerList = workerList;
        this.workerid = workerid;
        this.perAddId = perAddId;
        this.curAddId = curAddId;
        this.bankId = bankId;
        this.mService = mService;
        this.mannualAttendencePOJO = mannualAttendencePOJO;
        this.database = database;
        this.mannualpojo = mannualpojo;
    }*/

   /* public MannualAttendenceAdapter(Context mContext) {

    }*/
/*

    public MannualAttendenceAdapter(MannualAttendence mannualAttendence, ArrayList<MannualAttendencePOJO> mannual) {
    }
*/

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


       holder.setItemClickListener(new ItemClickListener1(){
               @Override
               public void onItemClick(View v, int pos) {
                   CheckBox chk = (CheckBox) v;

                   //checked if it is checked or not
                   if(chk.isChecked())
                   {
                   // checkedmannual.add(workerList.get(pos));
                       MannualAttendencePOJO mannual = new MannualAttendencePOJO();

                       mannual.setName(workerList.get(pos).getName());
                       mannual.setNumber(workerList.get(pos).getContact1());
                       mannual.setWorker_id(workerList.get(pos).getWorkerId());
                       // mannual.setWorker_assignment_id(workerList.get(pos).getName());
                       // mannual.setApproval(workerList.get(pos).getName());
                       mannual.setWages(workerList.get(pos).getSalary());
                       mannual.setCheck_in_date(mCurrentate());
                       mannual.setCheck_in_time(getCurrentTime());
                       mannual.setChecktype("Manual");
                       mannual.setApproval("waiting");
                       mannual.setProject_id(PreferenceUtils.getProject_id(mContext));

                    mannualList.add(mannual);
                   }else if(!chk.isChecked())
                   {
                      // new Database(mContext).deleteToMannualAttendance();
                   // mannualList.remove(workerList.get(pos));
                       mannualList.remove(mannualList.get(pos));
                   }

               }
           }

       );

               // mannualAttendencePOJO=new MannualAttendencePOJO();
               /* mannualAttendencePOJO.setName(MtxtWorkerName.getText().toString());
                // workerModel.setId(edt_Id.getText().toString());
                mannualAttendencePOJO.setWorker_id(MtxtWorkerId.getText().toString());
                mannualAttendencePOJO.setNumber(MtxtWorkerNumber.getText().toString());
*/
                  //  database.addToMannualAttendance(mannualAttendencePOJO);



            }




    @Override
    public int getItemCount() {
        return workerList.size();
       // return mannualpojo.size();
    }

    private String mCurrentate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat(" dd/ MM / yyyy ");
        String strDate = "" + mdformat.format(calendar.getTime());
        return strDate;
    }

    public String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

}
