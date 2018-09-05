package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MannualAttendenceHolder extends RecyclerView.ViewHolder {

    public CircleImageView McircleImageViewPhoto;
    public TextView MtxtWorkerName,MtxtWorkerId,MtxtWorkerNumber,txtWorkerGender,txtWorkerNumber2;
    public Button btnEdit,btnDelete,MannualbtnSubmit;

    public MannualAttendenceHolder(View itemView) {
        super(itemView);

        McircleImageViewPhoto    = (CircleImageView)itemView.findViewById(R.id.mannualAttendence_photo);
       // txtWorkerGender         = (TextView)itemView.findViewById(R.id.txt_worker_gender);
        MtxtWorkerId             = (TextView)itemView.findViewById(R.id.txt_mannualAttendence_id);
        MtxtWorkerName           = (TextView)itemView.findViewById(R.id.txt_mannuaAttendendence_name);
        MtxtWorkerNumber         = (TextView)itemView.findViewById(R.id.txt_mannualAttendence_number);
        //txtWorkerNumber2        = (TextView)itemView.findViewById(R.id.txt_worker_number2);

       /* btnEdit                 = (Button)itemView.findViewById(R.id.btn_worker_edit);
        btnDelete               = (Button)itemView.findViewById(R.id.btn_worker_Delete);*/
        MannualbtnSubmit              = (Button)itemView.findViewById(R.id.MannualAttendence_submit);
    }
}

