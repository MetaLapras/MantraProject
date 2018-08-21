package com.pasistence.mantrafingerprint.Main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.FontsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
    RadioGroup radioGroup;
    RadioButton RInTime,ROutTime,RHalfDay;
    MFS100Mantra mfs100Mantra;
    WorkerModel workerModel;
    ScrollView linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        mInit();
        mfs100Mantra = new MFS100Mantra(MatchingActivity.this,imgfinger,workerModel);
        mfs100Mantra.onStart();
        statrMatchingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mfs100Mantra.startMatching();
            }
        });
    }

    private void mInit() {
        mContext = MatchingActivity.this;
        txtProjectName              = (TextView) findViewById(R.id.txt_ProjectName);
        txtEmployeeId               = (TextView)findViewById(R.id.txt_matching_EmpId);
        txtDate                     = (TextView)findViewById(R.id.txt_Date);
        txtTime                     = (TextView)findViewById(R.id.txt_time);
        txtWorkerName               = (TextView)findViewById(R.id.txt_matchingWorkerName);
        txtWorkerId                 = (TextView)findViewById(R.id.txt_matchingWorkerId);

        imgfinger                   = (ImageView)findViewById(R.id.matching_fingerPrint);
        CircularImage               = (CircleImageView) findViewById(R.id.matching_ProfileImg);

        statrMatchingbtn            = (Button) findViewById(R.id.matchingBtn);
        linearLayout                = (ScrollView) findViewById(R.id.Matching_Layout);


        radioGroup                  = (RadioGroup) findViewById(R.id.radio_group);
        RInTime                     = (RadioButton) findViewById(R.id.radio_In_Time);
        ROutTime                    = (RadioButton) findViewById(R.id.radio_Out_Time);
        RHalfDay                    = (RadioButton) findViewById(R.id.radio_Half_Day);

        RInTime.setSelected(true);

       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if(RInTime.isChecked())
               {
                 //linearLayout.setBackgroundColor(Color.GREEN);
                   linearLayout.setBackgroundResource(R.drawable.gradient_rintime_worker);
               }
               if(ROutTime.isChecked())
               {
                //linearLayout.setBackground(getDrawable(R.drawable.gradient_routtime_worker));
                   linearLayout.setBackgroundResource(R.drawable.gradient_routtime_worker);

               }
               if(RHalfDay.isChecked())
               {
                      // linearLayout.setBackground(getDrawable(R.drawable.gradient_rhalfday_worker));
                   linearLayout.setBackgroundResource(R.drawable.gradient_rhalfday_worker);
               }
           }
       });
    }
}
