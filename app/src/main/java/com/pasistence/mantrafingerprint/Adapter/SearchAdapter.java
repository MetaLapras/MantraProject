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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.Models.WorkerList;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Main.WorkerDetails;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView name, number1, number2, gender,Id;
    public CircleImageView img;
    private ItemClickListener itemClickListener;
    public Button transferBtn;
    public  LinearLayout layoutToAdd;

    public SearchViewHolder(View itemView) {


        super(itemView);
        Id       = (TextView)itemView.findViewById(R.id.txt_workerTransfer_id) ;
        name     = (TextView) itemView.findViewById(R.id.txt_workerTransfer_name);
        number1  = (TextView) itemView.findViewById(R.id.txt_workerTransfer_number);
        number2  = (TextView) itemView.findViewById(R.id.txt_workerTransfer_number2);
        gender   = (TextView) itemView.findViewById(R.id.txt_workerTransfer_gender);
        img = (CircleImageView)itemView.findViewById(R.id.workerTransfer_photo);
        transferBtn = (Button)itemView.findViewById(R.id.btn_workerTransfer);
        layoutToAdd = (LinearLayout)itemView.findViewById(R.id.transfer_linear_layout) ;



        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public  void  setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    /*transferBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.transfer_dialog, null);
            layoutToAdd.addView(view);


        }
    });*/

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;

    }
}



    public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

       // private final List<String> suggestList;
        private Context context;
        private List<WorkerModel> workerModels;
        Activity activity;


        public SearchAdapter(Activity activity,Context context, List<WorkerModel> workerModels) {
            this.activity = activity;
            this.context = context;
            this.workerModels = workerModels;
        }



        @NonNull
        @Override
        public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.custome_transfer_template, parent, false);
            return new SearchViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
            holder.Id.setText("Worker Id : "+workerModels.get(position).getAdharcard_id());
            holder.name.setText("Name : "+workerModels.get(position).getName());
            holder.number1.setText("Contact no : "+workerModels.get(position).getContact1());
            holder.number2.setText("alternate no : "+workerModels.get(position).getContact2());
            holder.gender.setText("gender : "+workerModels.get(position).getGender());
            /*Glide.with(context)
                    .load(workerModels.get(position).getImageUrl().toString())
                    .into(holder.img);*/

            if(workerModels.get(position).getImageUrl().toString().contains("images/workers")){
                String Url = Common.BASE_URL+ workerModels.get(position).getImageUrl().toString();
                Glide.with(context)
                        .load(Url) // image url
                        .into(holder.img) ; // imageview object
            }else {
                Glide.with(context)
                        .load(workerModels.get(position).getImageUrl().toString())
                        .into(holder.img);
            }




            holder.transferBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Common.isConnectedToInterNet(activity)) {
                        showTransferDialog();
                    }else {
                        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(activity);
                        alertDialogBuilder.setMessage("No Internet Connection! Please Check your Internet Connection...");
                        alertDialogBuilder.setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                    }
                                });

                        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                }
            });
        }

        private void showTransferDialog() {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View changePwdLayout = layoutInflater.inflate(R.layout.transfer_dialog,null);

            alertDialog.setView(changePwdLayout);

            alertDialog.show();


        }

        @Override
        public int getItemCount() {
            return workerModels.size();
        }
    }


   /* public TextView text_firstname,text_empid,text_contactnum;
    public SearchViewHolder(View itemView) {
        super(itemView);

        text_firstname = (TextView)itemView.findViewById(R.id.text_first_name);
        text_empid = (TextView)itemView.findViewById(R.id.text_empId);
        text_contactnum = (TextView)itemView.findViewById(R.id.text_first_name);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<WorkerList> workerLists;

    public SearchAdapter(Context context, List<WorkerList> workerLists) {
        this.context = context;
        this.workerLists = workerLists;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_item_member_list,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.text_firstname.setText(workerLists.get(position).getFirstName());
        holder.text_empid.setText(workerLists.get(position).getEmpId());
        holder.text_contactnum.setText(workerLists.get(position).getContactNo());

    }

    @Override
    public int getItemCount() {
        return workerLists.size();
    }
}*/


