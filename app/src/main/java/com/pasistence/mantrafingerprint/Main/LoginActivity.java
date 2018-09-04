package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.CurrentAddress;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.EmployeeDetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.PermanentAddress;
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
   PermanentAddress permanentAddress;
   CurrentAddress currentAddress;
   BankAccount bankAccount;
   Database database;
   public String curAddress;
   public String perAddress;


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


        //model init
        projectdetails = new Projectdetails();
        employeeDetails = new EmployeeDetails();
        //permanentAddress = new PermanentAddress();
        //currentAddress = new CurrentAddress();
        //bankAccount = new BankAccount();



        edtProjectName.setText("mantra1");
        edtEmployeeName.setText("1");
        edtPassword.setText("123");


        btn_signin = findViewById(R.id.btnSignIn);


   /*     btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext,DashboardActivity.class);
                startActivity(intent);
                //LoginActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }

        });*/


        //Init Service
        mService = Common.getApi();

        btn_signin.setOnClickListener(this);
                /*Intent intent= new Intent(mContext,DashboardActivity.class);
                startActivity(intent);*/
        database = new Database(mContext);
    }
    @Override
    public void onClick(View view) {
        if (Common.isConnectedToInterNet(mContext)) {

        }else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("No Internet Connection! Please Check your Internet Connection...");
                    alertDialogBuilder.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    arg0.dismiss();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
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
    try {

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

                            PreferenceUtils.setEmployee_id(mContext, String.valueOf(employeeDetails.getId()));
                            PreferenceUtils.setProject_id(mContext, String.valueOf(projectdetails.getProject_id()));
                            PreferenceUtils.setSignIn(LoginActivity.this,true);

                            database.deleteToEmployee();
                            database.addToEmployee(employeeDetails);

                            //database.deleteToWorkers();
                            if(projectdetails.getWorker_list() != null){
                                database.deleteToWorkers();
                                database.deleteToAddressDetails();
                                database.deleteToBankDetails();

                                for(WorkerModel worker : projectdetails.getWorker_list())
                                {
                                    // workerList = worker;
                                    Log.e("wrk",worker.toString());

                                    permanentAddress = new PermanentAddress();
                                    currentAddress = new CurrentAddress();
                                    Contactdetails contactdetails = new Contactdetails();
                                    bankAccount = new BankAccount();

                                    if(worker.getCurrent_address() != null){
                                        currentAddress = worker.getCurrent_address();
                                        contactdetails.setId(currentAddress.getId());
                                        contactdetails.setContact1(currentAddress.getContact1());
                                        contactdetails.setContact2(currentAddress.getContact2());
                                        contactdetails.setAddress_line_1(currentAddress.getAddress_line_1());
                                        contactdetails.setAddress_line_2(currentAddress.getAddress_line_2());
                                        contactdetails.setCity(currentAddress.getCity());
                                        contactdetails.setPincode(currentAddress.getPincode());
                                        contactdetails.setState(currentAddress.getState());
                                        contactdetails.setCountry(currentAddress.getCountry());
                                        contactdetails.setType(currentAddress.getType());

                                        database.addToAddressDetails(contactdetails);

                                        worker.setContact1(currentAddress.getContact1());
                                        worker.setContact2(currentAddress.getContact2());
                                        worker.setCity(currentAddress.getCity());
                                        worker.setPincode(currentAddress.getPincode());
                                        curAddress = currentAddress.getAddress_line_1() + " " + currentAddress.getAddress_line_2() + " " +
                                                currentAddress.getPincode() + " " + currentAddress.getCity();

                                        Log.e("currentAddress",currentAddress.toString());
                                    }
                                    if(worker.getPermanent_address() != null){
                                        permanentAddress = worker.getPermanent_address();

                                        contactdetails.setId(permanentAddress.getId());
                                        contactdetails.setContact1(permanentAddress.getContact1());

                                        contactdetails.setContact2(permanentAddress.getContact2());
                                        contactdetails.setAddress_line_1(permanentAddress.getAddress_line_1());
                                        contactdetails.setAddress_line_2(permanentAddress.getAddress_line_2());
                                        contactdetails.setCity(permanentAddress.getCity());
                                        contactdetails.setPincode(permanentAddress.getPincode());
                                        contactdetails.setState(permanentAddress.getState());
                                        contactdetails.setCountry(permanentAddress.getCountry());
                                        contactdetails.setType(permanentAddress.getType());

                                        database.addToAddressDetails(contactdetails);

                                        worker.setContact1(permanentAddress.getContact1());
                                        worker.setContact2(permanentAddress.getContact2());
                                        worker.setCity(permanentAddress.getCity());
                                        worker.setPincode(permanentAddress.getPincode());

                                        perAddress = permanentAddress.getAddress_line_1() + " " + permanentAddress.getAddress_line_2() + " " +
                                                permanentAddress.getPincode() + " " + permanentAddress.getCity();


                                        Log.e("permanentAddress",permanentAddress.toString());
                                    }
                                    if( worker.getBank_account() != null){
                                        bankAccount = worker.getBank_account();
                                        database.addToBankDetails(bankAccount);

                                        worker.setBank_name(bankAccount.getBank_name());
                                        worker.setHolder_name(bankAccount.getAccount_holder_name());
                                        worker.setIfsc_code(bankAccount.getIfsc_code());
                                        worker.setAccount_number(bankAccount.getAccount_no());

                                        Log.e("bankAccount",bankAccount.toString() );
                                    }

                                    worker.setCurrent_address1(curAddress);
                                    worker.setPermanent_address1(perAddress);

                                    database.addToWorkers(worker);
                                }
                            }

                            dialog.dismiss();
                            Intent intent= new Intent(mContext,DashboardActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiProjectResponse> call, Throwable t) {
                        Toast.makeText(mContext, "Connection Failed !", Toast.LENGTH_SHORT).show();
                        Log.e("error",t.getMessage());
                        t.printStackTrace();

                        dialog.dismiss();
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }
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
