package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MannualAttendenceHolder extends RecyclerView.ViewHolder {

    public CircleImageView McircleImageViewPhoto;
    public TextView MtxtWorkerName,MtxtWorkerId,MtxtWorkerNumber,txtWorkerGender,txtWorkerNumber2;
    public Button btnEdit,btnDelete,MannualbtnSubmit;
    public CheckBox chkChecked;

    public MannualAttendenceHolder(View itemView) {
        super(itemView);

        McircleImageViewPhoto    = (CircleImageView)itemView.findViewById(R.id.mannualAttendence_photo);
        MtxtWorkerId             = (TextView)itemView.findViewById(R.id.txt_mannualAttendence_id);
        MtxtWorkerName           = (TextView)itemView.findViewById(R.id.txt_mannuaAttendendence_name);
        MtxtWorkerNumber         = (TextView)itemView.findViewById(R.id.txt_mannualAttendence_number);
        chkChecked               = (CheckBox)itemView.findViewById(R.id.chk_Check);
    }
}

