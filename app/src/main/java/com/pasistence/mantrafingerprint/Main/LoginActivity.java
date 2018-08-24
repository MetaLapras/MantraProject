package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.EmployeeDetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Projectdetails;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.database.Database;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
            implements View.OnClickListener {
   Context mContext ;
   Button btn_signin;
   ActionProcessButton btnSignIn;
   EditText edtProjectName,edtEmployeeName,edtPassword;
   IMyAPI mService;
   Projectdetails projectdetails;
   EmployeeDetails employeeDetails;
   Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialisation
        mInit();
    }

    private void mInit() {
        mContext            = LoginActivity.this;
        edtProjectName      = (EditText)findViewById(R.id.edt_project_name);
        edtEmployeeName     = (EditText)findViewById(R.id.edt_employee_id);
        edtPassword         = (EditText)findViewById(R.id.edt_password);
        btn_signin          = (Button) findViewById(R.id.btnSignIn);
        //btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);


        edtProjectName.setText("mantra1");
        edtEmployeeName.setText("1");
        edtPassword.setText("123");


        btn_signin = findViewById(R.id.btnSignIn);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,DashboardActivity.class);
                startActivity(intent);
                //LoginActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }

        });


        //Init Service
        mService = Common.getApi();

        btn_signin.setOnClickListener(this);
                /*Intent intent= new Intent(mContext,DashboardActivity.class);
                startActivity(intent);*/
        database = new Database(mContext);
    }
    @Override
    public void onClick(View view) {
        if (!validationCheck())
        {
            authenticatUser(edtProjectName.getText().toString(),edtEmployeeName.getText().toString(),edtPassword.getText().toString());
        }
        else
        {
            Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
        }
    }

    //Check user Authentication of User
    private void authenticatUser(final String projectname, String employeeId, String password) {

        final AlertDialog dialog = new SpotsDialog(mContext);
        dialog.show();
        dialog.setMessage("Loading Data");
        dialog.setCancelable(false);

        mService.loginUser(employeeId,projectname,password)
                .enqueue(new Callback<ApiProjectResponse>() {
                    @Override
                    public void onResponse(Call<ApiProjectResponse> call, Response<ApiProjectResponse> response) {
                        ApiProjectResponse result = response.body();
                        if(result.isError())
                        {
                            Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                            Log.e("-->",result.getError_msg() );
                            dialog.dismiss();
                        }else
                        {
                            Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                            Log.e("-->",result.getProjectdetails().toString() );

                            projectdetails = result.getProjectdetails();
                            Log.e("prj",projectdetails.toString());

                            database.deleteToPorjects();
                            database.addToPorject(projectdetails);

                            employeeDetails = projectdetails.getEmployee_details();
                            Log.e("emp",employeeDetails.toString() );
                            database.deleteToEmployee();
                            database.addToEmployee(employeeDetails);

                            database.deleteToWorkers();
                            for(WorkerModel worker : projectdetails.getWorker_list())
                            {
                               // workerList = worker;
                                Log.e("wrk",worker.toString() );
                                database.addToWorkers(worker);
                            }

                            dialog.dismiss();
                            Intent intent= new Intent(mContext,DashboardActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiProjectResponse> call, Throwable t) {
                        Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    //validation for Login details
    public boolean validationCheck() {
        boolean cancle = false;
        View focusView = null;
        if(TextUtils.isEmpty(edtProjectName.getText()))
        {
            edtProjectName.setError("Please Enter Project Name * ");
            focusView = edtProjectName;
            cancle = true;
        }

        if(TextUtils.isEmpty(edtEmployeeName.getText()))
        {
            edtEmployeeName.setError("Please Enter Employee Id * ");
            focusView = edtEmployeeName;
            cancle = true;
        }
        if(TextUtils.isEmpty(edtPassword.getText()))
        {
            edtPassword.setError("Please Enter Password * ");
            focusView = edtPassword;
            cancle = true;
        }
        return cancle;
    }
}
