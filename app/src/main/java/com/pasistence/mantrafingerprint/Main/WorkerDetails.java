package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.Models.WorkerList;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;

import java.util.ArrayList;

public class WorkerDetails extends AppCompatActivity {

    TextView textViewName, textViewId, textViewAge, textViewGender, textViewStatus, textViewMaritalStatus,
            textViewEmail, textViewMobilePhone, textViewHomePhone, textViewWorkPhone, textViewAddress;
    ImageView profileImageView, editImageView;

    WorkerModel memberDetails = new WorkerModel();
    ArrayList<WorkerList> list = new ArrayList<WorkerList>();
    DatabaseHelper dataBaseHelper;
    RelativeLayout removeLayout;


    Context context, mContext;// = MemberDetailsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        context = WorkerDetails.this;
        mContext = WorkerDetails.this;

        dataBaseHelper = new DatabaseHelper(this);


    }
}