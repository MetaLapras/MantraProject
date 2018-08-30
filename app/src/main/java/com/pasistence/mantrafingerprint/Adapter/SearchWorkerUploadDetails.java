package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class SearchWorkerUploadDetailsHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView uploadname, uploadnumber1, uploadnumber2, uploadgender,uploadId;
    public CircleImageView iuploadmg;
    private ItemClickListener itemClickListener;
    public LinearLayout layoutupload;

    public SearchWorkerUploadDetailsHolder(View itemView) {


        super(itemView);
        uploadId       = (TextView)itemView.findViewById(R.id.txt_workerupload_id) ;
        uploadname     = (TextView) itemView.findViewById(R.id.txt_workerupload_name);
        uploadnumber1  = (TextView) itemView.findViewById(R.id.txt_workerupload_number);
        uploadnumber2  = (TextView) itemView.findViewById(R.id.txt_workerupload_number2);
        uploadgender   = (TextView) itemView.findViewById(R.id.txt_workerupload_gender);
        iuploadmg = (CircleImageView)itemView.findViewById(R.id.workerupload_photo);
        layoutupload = (LinearLayout)itemView.findViewById(R.id.workerupload_linear_layout) ;



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



public class SearchWorkerUploadDetails extends RecyclerView.Adapter<SearchWorkerUploadDetailsHolder> {

    // private final List<String> suggestList;
    private Context context;
    private List<WorkerModel> workerModels;
    Activity activity;


    public SearchWorkerUploadDetails(Activity activity,Context context, List<WorkerModel> workerModels) {
        this.activity = activity;
        this.context = context;
        this.workerModels = workerModels;
    }



    @NonNull
    @Override
    public SearchWorkerUploadDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.worker_details_upload_template, parent, false);
        return new SearchWorkerUploadDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchWorkerUploadDetailsHolder holder, int position) {

        holder.uploadId.setText("Worker Id : "+workerModels.get(position).getAdharcard_id());
        holder.uploadname.setText("Name : "+workerModels.get(position).getName());
        holder.uploadnumber1.setText("Contact no : "+workerModels.get(position).getContact1());
        holder.uploadnumber2.setText("alternate no : "+workerModels.get(position).getContact2());
        holder.uploadgender
                .setText("gender : "+workerModels.get(position).getGender());
        Glide.with(context)
                .load(workerModels.get(position).getImageUrl().toString())
                .into(holder.iuploadmg);

       /* holder.transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTransferDialog();

            }
        });*/


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Context context = view.getContext();

                    /*    Intent intent = new Intent(context, WorkerDetails.class);
                        context.startActivity(intent);*/

                    Toast.makeText(context, "Long Click : ", Toast.LENGTH_SHORT).show();
                }

                else
                    Toast.makeText(context, "  "+workerModels.get(position), Toast.LENGTH_SHORT).show();

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

