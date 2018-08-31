package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorkerUploadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CircleImageView uploadcircleImageViewPhoto;
    public TextView uploadWorkerName,uploadWorkerId,uploadWorkerNumber,uploadWorkerGender,uploadWorkerNumber2;
    public Button btnsubmit;
    ItemClickListener itemClickListener;

    public WorkerUploadHolder(View itemView) {
        super(itemView);

        uploadcircleImageViewPhoto    = (CircleImageView)itemView.findViewById(R.id.workerupload_photo);
        uploadWorkerGender         = (TextView)itemView.findViewById(R.id.txt_workerupload_gender);
        uploadWorkerId             = (TextView)itemView.findViewById(R.id.txt_workerupload_id);
        uploadWorkerName           = (TextView)itemView.findViewById(R.id.txt_workerupload_name);
        uploadWorkerNumber         = (TextView)itemView.findViewById(R.id.txt_workerupload_number);
        uploadWorkerNumber2        = (TextView)itemView.findViewById(R.id.txt_workerupload_number2);

        btnsubmit                 = (Button)itemView.findViewById(R.id.btn_submit_upload);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
