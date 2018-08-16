package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.FingerPrintMatching.MFS100Mantra;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchingActivity extends AppCompatActivity {

    Context mContext;
    TextView txtProjectName,txtEmployeeId,txtDate,txtTime,txtWorkerName,txtWorkerId;
    ImageView imgfinger;
    CircleImageView CircularImage;
    Button statrMatchingbtn;
    RadioButton RInTime,ROutTime,RHalfDay;
    MFS100Mantra mfs100Mantra;
    WorkerModel workerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        mInit();
        statrMatchingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfs100Mantra = new MFS100Mantra(MatchingActivity.this,imgfinger,workerModel);
                mfs100Mantra.onStart();
                mfs100Mantra.startMatching();

            }
        });
    }

    private void mInit() {
        mContext = MatchingActivity.this;
        txtProjectName = findViewById(R.id.txt_ProjectName);
        txtEmployeeId = findViewById(R.id.txt_matching_EmpId);
        txtDate= findViewById(R.id.txt_Date);
        txtTime = findViewById(R.id.txt_time);
        txtWorkerName = findViewById(R.id.txt_matchingWorkerName);
        txtWorkerId  = findViewById(R.id.txt_matchingWorkerId);

        imgfinger = findViewById(R.id.matching_fingerPrint);
        CircularImage = findViewById(R.id.matching_ProfileImg);

        statrMatchingbtn = findViewById(R.id.matchingBtn);

        RInTime = findViewById(R.id.radio_In_Time);
        ROutTime = findViewById(R.id.radio_Out_Time);
        RHalfDay = findViewById(R.id.radio_Half_Day);
    }
}
