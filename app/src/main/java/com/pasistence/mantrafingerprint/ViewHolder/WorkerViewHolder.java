package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorkerViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView circleImageViewPhoto;
    public TextView txtWorkerName,txtWorkerId,txtWorkerNumber,txtWorkerGender,txtWorkerNumber2;
    public Button btnEdit,btnDelete,btnDetails;

    public WorkerViewHolder(View itemView) {
        super(itemView);

        circleImageViewPhoto    = (CircleImageView)itemView.findViewById(R.id.worker_photo);
        txtWorkerGender         = (TextView)itemView.findViewById(R.id.txt_worker_gender);
        txtWorkerId             = (TextView)itemView.findViewById(R.id.txt_worker_id);
        txtWorkerName           = (TextView)itemView.findViewById(R.id.txt_worker_name);
        txtWorkerNumber         = (TextView)itemView.findViewById(R.id.txt_worker_number);
        txtWorkerNumber2        = (TextView)itemView.findViewById(R.id.txt_worker_number2);

        btnEdit                 = (Button)itemView.findViewById(R.id.btn_worker_edit);
        btnDelete               = (Button)itemView.findViewById(R.id.btn_worker_Delete);
        btnDetails              = (Button)itemView.findViewById(R.id.btn_worker_Details);
    }
}
