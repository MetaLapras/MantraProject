package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;


public class CustomeWorkerAttendenceHolder extends RecyclerView.ViewHolder {

    public TextView txtdate,txtwages,txtcheckinTime,txtcheckOutTime;

    public CustomeWorkerAttendenceHolder(View itemView) {
        super(itemView);


        txtdate        = (TextView)itemView.findViewById(R.id.txt_date_attendence);
        txtwages            = (TextView)itemView.findViewById(R.id.txt_wages);
        txtcheckinTime          = (TextView)itemView.findViewById(R.id.checkinTime_txt);
        txtcheckOutTime        = (TextView)itemView.findViewById(R.id.checkOutTime_txt);


    }
}
