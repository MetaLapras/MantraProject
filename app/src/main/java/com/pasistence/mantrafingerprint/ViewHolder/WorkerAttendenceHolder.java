package com.pasistence.mantrafingerprint.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorkerAttendenceHolder extends RecyclerView.ViewHolder {

    public CircleImageView attendencecircleImageViewPhoto;
    public TextView attendenceWorkerName,attendenceWorkerId,attendenceWorkerNumber,attendenceWorkerGender,attendenceWorkerNumber2;
    public Button btnsubmit;

    public WorkerAttendenceHolder(View itemView) {
        super(itemView);

        attendencecircleImageViewPhoto    = (CircleImageView)itemView.findViewById(R.id.workerattendence_photo);
        attendenceWorkerGender         = (TextView)itemView.findViewById(R.id.txt_workerattendence_gender);
        attendenceWorkerId             = (TextView)itemView.findViewById(R.id.txt_workerattendence_id);
        attendenceWorkerName           = (TextView)itemView.findViewById(R.id.txt_workerattendence_name);
        attendenceWorkerNumber         = (TextView)itemView.findViewById(R.id.txt_workerattendence_number);
        attendenceWorkerNumber2        = (TextView)itemView.findViewById(R.id.txt_workerattendence_number2);

        btnsubmit                 = (Button)itemView.findViewById(R.id.btn_submit_upload);


    }
}

