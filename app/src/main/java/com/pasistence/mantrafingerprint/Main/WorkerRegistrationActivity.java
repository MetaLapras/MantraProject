package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;

import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.FingerPrintMatching.MFS100Mantra;
import com.pasistence.mantrafingerprint.Interface.UploadCallBack;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIBankResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIContactResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerImageResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerPersonalResponse;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.CurrentAddress;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.Remote.ProgressRequestBody;
import com.pasistence.mantrafingerprint.database.Database;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;


import fr.ganfra.materialspinner.MaterialSpinner;
import okhttp3.MultipartBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener, UploadCallBack {

    public static final String TAG ="reg -->";
    Button btnLayer1Next,btnLayer2Next,btnLayer3Next,btnLayer4Next,btnLayer2Skip,btnLayer3Skip,btnLayer4Skip,btnSubmit;
    Context mContext;
    TextView lblMessage;
    View layer1,layer2,layer3,layer4,layer5;
    WorkerModel workerModel;
    Contactdetails contactdetails;
    private boolean isEnroll1=false;
    private boolean isEnroll2=false;
    CheckBox chk_isPermanent;
    String per_address_id,curr_address_id,bank_id;

    BankAccount bankAccount;

    MaterialEditText edtname,edtaadharnum,edtdob,edtemail,edtaddressline1,
            edtaddressline2,edtmobilenum,edtalternatenum,edtcity,edtpincode,
            edtholdername,edtbankifsccode,edtbankaccountnumber,edtbankname,
            edtcurrentaddress1,edtcurrentaddress2,edtcurrentcity,edtcurrentstate,
            edtcurrentpincode,edtSalary;
    CircleImageView profileimage;
    ImageView imgfingerprint1,imgfingerprint2;
   // MaterialSpinner spngender,spnstate;
    Spinner spngender,spnstate,spncurrentstate;
    String ImagePath;
    Database database;
    IMyAPI mService;

    ProgressDialog dialog;


    private int mYear, mMonth, mDay;
    String type,id ;
    ArrayList<String> finger;

    MFS100Mantra mfs100Mantra;
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);

        mInit();
        mOnclick();

    }

    private void mOnclick() {
        btnLayer1Next.setOnClickListener(this);
        btnLayer2Next.setOnClickListener(this);
        btnLayer3Next.setOnClickListener(this);
        btnLayer4Next.setOnClickListener(this);
        btnLayer4Skip.setOnClickListener(this);
        btnLayer3Skip.setOnClickListener(this);
        btnLayer2Skip.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        profileimage.setOnClickListener(this);
        edtdob.setOnClickListener(this);
        imgfingerprint1.setOnClickListener(this);
        imgfingerprint2.setOnClickListener(this);
    }

    private void mInit() {

        mContext = WorkerRegistrationActivity.this;


        btnLayer1Next           = (Button)findViewById(R.id.btn_layer1_next);
        btnLayer2Next           = (Button)findViewById(R.id.btn_layer2_next);
        btnLayer3Next           = (Button)findViewById(R.id.btn_layer3_next);
        btnLayer4Next           = (Button)findViewById(R.id.btn_layer4_next);
        btnLayer4Skip           = (Button)findViewById(R.id.btn_layer4_previous);
        btnLayer3Skip           = (Button)findViewById(R.id.btn_layer3_previous);
        btnLayer2Skip           = (Button)findViewById(R.id.btn_layer2_previous);
        btnSubmit               = (Button)findViewById(R.id.btn_submit);

         layer1                 = findViewById(R.id.layer1);
         layer2                 = findViewById(R.id.layer2);
         layer3                 = findViewById(R.id.layer3);
         layer4                 = findViewById(R.id.layer4);
         layer5                 =findViewById(R.id.layer5);

         //initialsing the Component
        edtname                 = (MaterialEditText) findViewById(R.id.edt_name);
        edtaadharnum            = (MaterialEditText)findViewById(R.id.edt_aadharCard);
        edtdob                  = (MaterialEditText)findViewById(R.id.edt_dob);
        edtemail                = (MaterialEditText)findViewById(R.id.edt_email);
        imgfingerprint1         = (ImageView) findViewById(R.id.fingerprint1);
        imgfingerprint2         = (ImageView) findViewById(R.id.fingerprint2);
        edtaddressline1         = (MaterialEditText)findViewById(R.id.edt_address1);
        edtaddressline2         = (MaterialEditText)findViewById(R.id.edt_address2);
        edtmobilenum            = (MaterialEditText)findViewById(R.id.edt_contact1);
        edtalternatenum         = (MaterialEditText)findViewById(R.id.edt_contact2);
        edtcity                 = (MaterialEditText)findViewById(R.id.edt_city);
        edtpincode              = (MaterialEditText)findViewById(R.id.edt_pincode);
        edtholdername           = (MaterialEditText)findViewById(R.id.edt_Bank_Holder_Name);
        edtbankifsccode         = (MaterialEditText)findViewById(R.id.edt_Bank_Ifsc_code);
        edtbankaccountnumber    = (MaterialEditText)findViewById(R.id.edt_Bank_Account_Number);
        edtbankname             = (MaterialEditText)findViewById(R.id.edt_Bank_Name);
        profileimage            = (CircleImageView) findViewById(R.id.img_profile_image);
        edtSalary               = (MaterialEditText) findViewById(R.id.edt_salary);

        //init the current address
        edtcurrentaddress1      = (MaterialEditText)findViewById(R.id.edt_currentaddress1);
        edtcurrentaddress2      = (MaterialEditText)findViewById(R.id.edt_currentaddress2);
        edtcurrentcity          = (MaterialEditText)findViewById(R.id.edt_currentcity);
        edtcurrentpincode       = (MaterialEditText)findViewById(R.id.edt_currentpincode);
        spncurrentstate         = (Spinner)findViewById(R.id.spn_currentstate) ;

        //init Check Box
        chk_isPermanent         = (CheckBox)findViewById(R.id.chk_type);


        //spngender             = (MaterialSpinner)findViewById(R.id.spinner_gender);
        spngender               = (Spinner)findViewById(R.id.spinner_gender);
        //spnstate              = (MaterialSpinner)findViewById(R.id.spn_state);
        spnstate                = (Spinner)findViewById(R.id.spn_state);

        //Textview Message
        lblMessage              = (TextView)findViewById(R.id.txt_message);

        //Init Common
        common = new Common();

        //Init Database
        database = new Database(mContext);

        //Init Mantra100 for Fingerprint
        mfs100Mantra = new MFS100Mantra(WorkerRegistrationActivity.this,lblMessage);
        mfs100Mantra.onStart();

        //init Api
        mService = Common.getApi();
    }

    @Override
    public void onClick(View view) {
        if(view == btnLayer1Next)
        {
             if(!validationCheckLayer1())
            {
                layer1.setVisibility(View.INVISIBLE);
                layer2.setVisibility(View.VISIBLE);
                mfs100Mantra.onStop();
                if(Common.isConnectedToInterNet(mContext)){
                    // Insert into Server Side
                    onWorkerRegistration();
                }else{
                    //Save on offline
                    Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
                }
            }else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }
        }
        if(view == btnLayer2Next)
        {
            if (!validationCheckLayer2())
            {
                layer2.setVisibility(View.INVISIBLE);
                layer3.setVisibility(View.VISIBLE);
                if(Common.isConnectedToInterNet(mContext))
                {
                    //Online
                    onWorkerContactRegistration();
                }else{
                    //Save on offline
                    onOfflineWorkerContact1();
                    Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }
        }

        if(view == btnLayer3Next)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer4.setVisibility(View.VISIBLE);

            if(Common.isConnectedToInterNet(mContext)){
                //save for online
                onWorkerCurrentContactRegistration();
            }else{
                //save for Offline
                onOfflineWorkerContact2();
                Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
            }
        }

        if(view == btnLayer4Next)
        {
            layer4.setVisibility(View.INVISIBLE);
            layer5.setVisibility(View.VISIBLE);

            if(Common.isConnectedToInterNet(mContext)){
                //Save for Online
                onBankDetailsRegistration();
            }else{
                //save for Offline
                onOfflineWorkerBankDetails();
                Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnLayer2Skip)
        {

            layer2.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
            if(Common.isConnectedToInterNet(mContext))
            {
                //Save on online
                onWorkerContactRegistration();
            }else{
                //Save in offline
                onOfflineWorkerContact1();
                Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnLayer3Skip)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer4.setVisibility(View.VISIBLE);

            if(Common.isConnectedToInterNet(mContext)){
                //Save on online
                onWorkerCurrentContactRegistration();
            }else{
                //save for Offline
                onOfflineWorkerContact2();
                Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnLayer4Skip)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer1.setVisibility(View.VISIBLE);

            if(Common.isConnectedToInterNet(mContext)){
                //Save for Online
                onBankDetailsRegistration();
            }else{
                //save for Offline
                onOfflineWorkerBankDetails();
                Toast.makeText(mContext, "Data has been Saved Offline", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnSubmit)
        {
            WorkerRegistrationBtn();
        }
        if(view == profileimage)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                    .setAspectRatio(1,1)  //image will be suqare//
                    .start(this);
        }
        if(view == edtdob)
        {
            dateDialog();
        }
        if(view == imgfingerprint1){

            mfs100Mantra.startCapturing(imgfingerprint1);
            // workerModel.setFingerprint1(mfs100Mantra.getScanFingerprint());
            if(mfs100Mantra.getScanFingerprint()!= null)
            {
                Log.e(TAG, mfs100Mantra.getScanFingerprint().toString());
            }
            //  mfs100Mantra.onStop();
        }
        if(view == imgfingerprint2){
            mfs100Mantra.startCapturing(imgfingerprint2);
            if(mfs100Mantra.getScanFingerprint()!= null)
            {
                Log.e(TAG, mfs100Mantra.getScanFingerprint().toString());
            }
            // workerModel.setFingerprint1(mfs100Mantra.getScanFingerprint());
            // Log.e(TAG, mfs100Mantra.getScanFingerprint().toString());
            //  mfs100Mantra.onStop();
        }
    }


   /*--------------------------------------------Online---------------------------------------------*/

    private void onWorkerRegistration() {
        workerModel=new WorkerModel();

        workerModel.setName(edtname.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        workerModel.setAdharcard_id(edtaadharnum.getText().toString());
        workerModel.setDob(edtdob.getText().toString());
        workerModel.setEmail(edtemail.getText().toString());
        workerModel.setGender(spngender.getSelectedItem().toString().trim());
        workerModel.setSalary(edtSalary.getText().toString());

        finger = mfs100Mantra.getList();

        if(finger.size()<=0)
        {
            workerModel.setFingerprint1("");
            workerModel.setFingerprint2("");

        }else {
            workerModel.setFingerprint1(finger.get(0).toString());
            isEnroll1 = true;
            workerModel.setFingerprint2(finger.get(1).toString());
            isEnroll2 = true;
        }
        try
        {
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Personal Details...");
            dialog.setCancelable(false);

            mService.workerRegistration(
                    workerModel.getName().toString(),
                    workerModel.getGender().toString(),
                    workerModel.getDob().toString(),
                    workerModel.getFingerprint1().toString(),
                    workerModel.getFingerprint2().toString(),
                    workerModel.getEmail().toString(),
                    PreferenceUtils.getProject_id(mContext).toString(),
                    workerModel.getSalary().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString(),
                    workerModel.getAdharcard_id().toString())
            /*mService.workerRegistration(
                    "dfsdfgdsg",
                    "sdfsd",
                   "sdfsdf",
                  "sadasd",
                    "fgdfg",
                   "dfsd",
                    "1",
                    "545",
                   "2",
                    "1234852")*/
                    .enqueue(new Callback<APIWorkerPersonalResponse>() {
                        @Override
                        public void onResponse(Call<APIWorkerPersonalResponse> call, Response<APIWorkerPersonalResponse> response) {
                            APIWorkerPersonalResponse result = response.body();
                          if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                              //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getWorkerModel().toString() );

                                workerModel = (WorkerModel) result.getWorkerModel();
                                Log.e("personal Details",workerModel.toString());

                                PreferenceUtils.setWorker_id(mContext,workerModel.getId());

                                //Save data into worker_Master SQLite
                                database.addToWorkers(workerModel);

                                }
                                dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIWorkerPersonalResponse> call, Throwable t) {
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

    private void onWorkerContactRegistration() {
        contactdetails=new Contactdetails();

        contactdetails.setAddress_line_1(edtaddressline1.getText().toString());
        contactdetails.setAddress_line_2(edtaddressline2.getText().toString());

        if(edtmobilenum.getText().toString().equals("")||edtmobilenum.getText().equals(null)){
            contactdetails.setContact1("0");
        }else {
            contactdetails.setContact1(edtmobilenum.getText().toString());
        }
        if(edtalternatenum.getText().toString().equals("")||edtalternatenum.getText().equals(null)){
            contactdetails.setContact2("0");
        }else {
            contactdetails.setContact2(edtalternatenum.getText().toString());
        }
        if(edtpincode.getText().toString().equals("")||edtpincode.getText().equals(null)){
            contactdetails.setPincode("0");
        }else {
            contactdetails.setPincode(edtpincode.getText().toString());
        }

        if(chk_isPermanent.isChecked())
        {
            edtcurrentaddress1.setText(edtaddressline1.getText());
            edtcurrentaddress2.setText(edtaddressline2.getText());
            edtcurrentcity.setText(edtcity.getText());
            edtcurrentpincode.setText(edtpincode.getText());
            contactdetails.setType("both");
            spncurrentstate.setSelection(getIndex(spncurrentstate, spnstate.getSelectedItem().toString().trim()));
        }else
        {
            contactdetails.setType("permanent");
        }


       // contactdetails.setContact1(Integer.parseInt(edtmobilenum.getText().toString()));
       // contactdetails.setContact2(Integer.parseInt(edtalternatenum.getText().toString()));
        contactdetails.setCity(edtcity.getText().toString());
        contactdetails.setState(spnstate.getSelectedItem().toString().trim());
       // contactdetails.setPincode(Integer.parseInt(edtpincode.getText().toString()));

        try
        {
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Contact Details...");
            dialog.setCancelable(false);

            mService.insertcontactdetails(
                    contactdetails.getContact1().toString(),
                    contactdetails.getContact2().toString(),
                    contactdetails.getAddress_line_1().toString(),
                    contactdetails.getAddress_line_2().toString(),
                    contactdetails.getCity().toString(),
                    contactdetails.getPincode().toString(),
                    contactdetails.getState().toString(),
                    "India",
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    contactdetails.getType().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString())

                    .enqueue(new Callback<APIContactResponse>() {
                        @Override
                        public void onResponse(Call<APIContactResponse> call, Response<APIContactResponse> response) {
                            APIContactResponse result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getContactdetails().toString() );

                                contactdetails = (Contactdetails) result.getContactdetails();
                                Log.e("Contact Details",contactdetails.toString());

                                per_address_id = String.valueOf(contactdetails.getId());

                                database.addToAddressDetails(contactdetails);
                                //database.addToPorject(projectdetails);
                            }

                            // workerModel = result.getWorkerModel();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIContactResponse> call, Throwable t) {
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

    private void onWorkerCurrentContactRegistration() {

        contactdetails=new Contactdetails();

        contactdetails.setAddress_line_1(edtcurrentaddress1.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        contactdetails.setAddress_line_2(edtcurrentaddress2.getText().toString());
        contactdetails.setContact1(edtmobilenum.getText().toString());
        contactdetails.setContact2(edtalternatenum.getText().toString());
        contactdetails.setCity(edtcurrentcity.getText().toString());
        contactdetails.setState(spncurrentstate.getSelectedItem().toString().trim());

        if(edtpincode.getText().toString().equals("")||edtpincode.getText().equals(null)){
               contactdetails.setPincode("0");
        }else {
            contactdetails.setPincode(edtcurrentpincode.getText().toString());
        }

        try
        {
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Contact Details...");
            dialog.setCancelable(false);

            mService.insertcontactdetails(
                    contactdetails.getContact1().toString(),
                    contactdetails.getContact2().toString(),
                    contactdetails.getAddress_line_1().toString(),
                    contactdetails.getAddress_line_2().toString(),
                    contactdetails.getCity().toString(),
                    contactdetails.getPincode().toString(),
                    contactdetails.getState().toString(),
                    "India",
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    "current",
                    PreferenceUtils.getEmployee_id(mContext).toString())
                    .enqueue(new Callback<APIContactResponse>() {
                        @Override
                        public void onResponse(Call<APIContactResponse> call, Response<APIContactResponse> response) {
                            APIContactResponse result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getContactdetails().toString() );
                                contactdetails = (Contactdetails) result.getContactdetails();
                                Log.e("Contact Details",contactdetails.toString());

                                curr_address_id = String.valueOf(contactdetails.getId());

                                database.addToAddressDetails(contactdetails);

                            }

                            // workerModel = result.getWorkerModel();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIContactResponse> call, Throwable t) {
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

    private void onBankDetailsRegistration() {
        bankAccount=new BankAccount();

        bankAccount.setAccount_holder_name(edtholdername.getText().toString());
        bankAccount.setAccount_no(edtbankaccountnumber.getText().toString());
        bankAccount.setBank_name(edtbankname.getText().toString());
        bankAccount.setIfsc_code(edtbankifsccode.getText().toString());

        try
        {
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Bank Details...");
            dialog.setCancelable(false);

            mService.insertbankdetails(
                    bankAccount.getAccount_holder_name().toString(),
                    bankAccount.getIfsc_code().toString(),
                    bankAccount.getAccount_no().toString(),
                    bankAccount.getBank_name().toString(),
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    "activate",
                    PreferenceUtils.getEmployee_id(mContext).toString())
                    .enqueue(new Callback<APIBankResponse>() {
                        @Override
                        public void onResponse(Call<APIBankResponse> call, Response<APIBankResponse> response) {
                            APIBankResponse  result = response.body();
                            if(result.isError())
                            {
                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.getError_msg() );
                                dialog.dismiss();
                            }else{
                                //  Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.e("-->",result.toString() );
                                bankAccount = (BankAccount) result.getBankdetails();
                                Log.e("Bank Details",bankAccount.toString());
                                bank_id = String.valueOf(bankAccount.getId());
                                database.addToBankDetails(bankAccount);
                            }

                            // workerModel = result.getWorkerModel();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIBankResponse> call, Throwable t) {
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

    private void WorkerRegistrationBtn() {

        workerModel=new WorkerModel();

            workerModel.setName(edtname.getText().toString());
            workerModel.setWorkerId(PreferenceUtils.getWorker_id(mContext).toString());
            workerModel.setAdharcard_id(edtaadharnum.getText().toString());
            workerModel.setDob(edtdob.getText().toString());
            workerModel.setEmail(edtemail.getText().toString());
            workerModel.setGender(spngender.getSelectedItem().toString().trim());
            workerModel.setPermanent_address1(edtaddressline1.getText().toString());
            workerModel.setCurrent_address1(edtaddressline2.getText().toString());
            workerModel.setContact1(edtmobilenum.getText().toString());
            workerModel.setContact2(edtalternatenum.getText().toString());
            workerModel.setCity(edtcity.getText().toString());
            workerModel.setPincode(edtpincode.getText().toString());
            workerModel.setHolder_name(edtholdername.getText().toString());
            workerModel.setIfsc_code(edtbankifsccode.getText().toString());
            workerModel.setAccount_number(edtbankaccountnumber.getText().toString());
            workerModel.setBank_name(edtbankname.getText().toString());
            workerModel.setPermanentAddressId(per_address_id);
            workerModel.setCurrentAddressId(curr_address_id);
            workerModel.setBankId(bank_id);
            workerModel.setActivation("activate");

            try{
                if(!getImagePath().equals(null))
                {
                    workerModel.setImageUrl(getImagePath());
                }else {
                    workerModel.setImageUrl("");
                }
            }catch (Exception e){
                e.printStackTrace();
                workerModel.setImageUrl("");
            }
            finger = mfs100Mantra.getList();


            if(finger.size()<=0)
            {
                workerModel.setFingerprint1("");
                workerModel.setFingerprint2("");

            }else {
                workerModel.setFingerprint1(finger.get(0).toString());
                workerModel.setFingerprint2(finger.get(1).toString());
            }

            try{
                if(Common.isConnectedToInterNet(mContext)){
                    //Upload image into the server into the constant table
                    onImageUpload(workerModel.getImageUrl().toString());
                    database.updateToWorkersMaster(workerModel);
                    //Toast.makeText(mContext, "Worker Updated successfully", Toast.LENGTH_SHORT).show();
                    mfs100Mantra.onDestroy();
                }else{
                    //Offline save all data into the temp table
                    database.addToTempWorkers(workerModel);
                    Toast.makeText(mContext, "Worker Registred successfully", Toast.LENGTH_SHORT).show();
                    mfs100Mantra.onDestroy();
                }

            }catch (NullPointerException e)
            {
                e.printStackTrace();
                Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
    }

    private void onImageUpload(String imageUri) {

        dialog = new ProgressDialog(mContext);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Uploading All Data....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();


        File file = new File(imageUri);
        ProgressRequestBody requestBody = new ProgressRequestBody(file,this);

        final MultipartBody.Part body = MultipartBody.Part.createFormData("uploadfile",file.getName(),requestBody);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mService.imageupload(body)
                        .enqueue(new Callback<APIWorkerImageResponse>() {
                            @Override
                            public void onResponse(Call<APIWorkerImageResponse> call, Response<APIWorkerImageResponse> response) {
                                APIWorkerImageResponse result = response.body();
                                Log.e("-->", result.toString());

                                /*if(result.isError()) {
                                    Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                    Log.e("-->",result.getError_msg() );
                                    dialog.dismiss();
                                }else {
                                    dialog.dismiss();
                                   // Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Log.e("-->", result.toString());

                                    mService.getWorkerDetails(
                                            result.getImageURL().toString(),
                                            PreferenceUtils.getWorker_id(mContext).toString(),
                                            PreferenceUtils.getEmployee_id(mContext).toString()
                                    ).enqueue(new Callback<WorkerModel>() {
                                        @Override
                                        public void onResponse(Call<WorkerModel> call, Response<WorkerModel> response) {
                                            WorkerModel result = response.body();
                                            if(result.isError())
                                            {
                                                Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                                Log.e("-->",result.getError_msg());
                                            }else {
                                                Toast.makeText(mContext, "Worker Registred Successfully", Toast.LENGTH_SHORT).show();
                                                Log.e("-->", result.toString());
                                                finish();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<WorkerModel> call, Throwable t) {
                                            t.printStackTrace();
                                        }
                                    });
                                }*/
                            }

                            @Override
                            public void onFailure(Call<APIWorkerImageResponse> call, Throwable t) {
                                t.printStackTrace();
                                dialog.dismiss();
                            }
                        });
            }
        }).start();
    }

    /*--------------------------------------------Offline---------------------------------------------*/

    private void onOfflineWorkerRegistration() {

        workerModel=new WorkerModel();

        workerModel.setName(edtname.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        workerModel.setAdharcard_id(edtaadharnum.getText().toString());
        workerModel.setDob(edtdob.getText().toString());
        workerModel.setEmail(edtemail.getText().toString());
        workerModel.setGender(spngender.getSelectedItem().toString().trim());
        workerModel.setSalary(edtSalary.getText().toString());

        finger = mfs100Mantra.getList();

        if(finger.size()<=0)
        {
            workerModel.setFingerprint1("");
            workerModel.setFingerprint2("");

        }else {
            workerModel.setFingerprint1(finger.get(0).toString());
            isEnroll1 = true;
            workerModel.setFingerprint2(finger.get(1).toString());
            isEnroll2 = true;
        }
        try
        {
            database.addToTempWorkers(workerModel);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void onOfflineWorkerContact1() {
        //Save offline Data in worker Permanent contact details
        contactdetails=new Contactdetails();

        contactdetails.setAddress_line_1(edtaddressline1.getText().toString());
        contactdetails.setAddress_line_2(edtaddressline2.getText().toString());

        if(edtmobilenum.getText().toString().equals("")||edtmobilenum.getText().equals(null)){
            contactdetails.setContact1("0");
        }else {
            contactdetails.setContact1(edtmobilenum.getText().toString());
        }
        if(edtalternatenum.getText().toString().equals("")||edtalternatenum.getText().equals(null)){
            contactdetails.setContact2("0");
        }else {
            contactdetails.setContact2(edtalternatenum.getText().toString());
        }
        if(edtpincode.getText().toString().equals("")||edtpincode.getText().equals(null)){
            contactdetails.setPincode("0");
        }else {
            contactdetails.setPincode(edtpincode.getText().toString());
        }

        if(chk_isPermanent.isChecked())
        {
            edtcurrentaddress1.setText(edtaddressline1.getText());
            edtcurrentaddress2.setText(edtaddressline2.getText());
            edtcurrentcity.setText(edtcity.getText());
            edtcurrentpincode.setText(edtpincode.getText());
            contactdetails.setType("both");
            spncurrentstate.setSelection(getIndex(spncurrentstate, spnstate.getSelectedItem().toString().trim()));
        }else
        {
            contactdetails.setType("permanent");
        }


        // contactdetails.setContact1(Integer.parseInt(edtmobilenum.getText().toString()));
        // contactdetails.setContact2(Integer.parseInt(edtalternatenum.getText().toString()));
        contactdetails.setCity(edtcity.getText().toString());
        contactdetails.setState(spnstate.getSelectedItem().toString().trim());
        // contactdetails.setPincode(Integer.parseInt(edtpincode.getText().toString()));

        database.addToTempAddressDetails(contactdetails);//Add to Temp address details 1

    }

    private void onOfflineWorkerContact2() {
        //Save offline Data in worker Current contact details
        contactdetails=new Contactdetails();

        contactdetails.setAddress_line_1(edtcurrentaddress1.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        contactdetails.setAddress_line_2(edtcurrentaddress2.getText().toString());
        // contactdetails.setContact1(Integer.parseInt(edtmobilenum.getText().toString()));
        // contactdetails.setContact2(Integer.parseInt(edtalternatenum.getText().toString()));
        contactdetails.setCity(edtcurrentcity.getText().toString());
        contactdetails.setState(spncurrentstate.getSelectedItem().toString().trim());

        if(edtpincode.getText().equals("")||edtpincode.getText().equals(null)){
            contactdetails.setPincode("0");
        }else {
            contactdetails.setPincode(edtcurrentpincode.getText().toString());
        }

        database.addToTempAddressDetails(contactdetails); //Add to Temp Contact 2
    }

    private void onOfflineWorkerBankDetails() {
        //Save offline Data in worker Bank details
        bankAccount=new BankAccount();

        bankAccount.setAccount_holder_name(edtholdername.getText().toString());
        bankAccount.setAccount_no(edtbankaccountnumber.getText().toString());
        bankAccount.setBank_name(edtbankname.getText().toString());
        bankAccount.setIfsc_code(edtbankifsccode.getText().toString());
        //bankAccount.setWorker_id(Integer.parseInt(PreferenceUtils.getWorker_id(mContext).toString()));
        bankAccount.setActivation("activate");

        database.addToTempBankDetails(bankAccount);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Uri imageUri = data.getData();
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK)
            {
                Uri resultUri = result.getUri();
                //set image choosed from gallery to image view
               // profileimage.setImageURI(resultUri);
                Glide.with(mContext)
                        .load(resultUri.getPath())
                        .into(profileimage);
                setImagePath(resultUri.getPath());

            }
            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public String getImagePath() {
        workerModel.setImageUrl(ImagePath);
        return ImagePath;
    }
    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    //validation for custome personal details
    public boolean validationCheckLayer1(){

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtname.getText())){
            edtname.setError("Please enter Name * ");
            focusView=edtname;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtaadharnum.getText())){
            edtaadharnum.setError("Please enter Last Name * ");
            focusView=edtaadharnum;
            cancel=true;
        }
     /*   if(!isEnroll1){
            imgfingerprint1.setColorFilter(Color.RED);
            cancel=true;
        }
        if(!isEnroll2){
            imgfingerprint2.setColorFilter(Color.RED);
            cancel=true;
        }*/

   /*  switch (type) {
            case"edit":
                break;
            case "register":
                if (getImagePath() == "" || getImagePath() == null) {
                    imageViewFingerPrint2.setBackgroundColor(getResources().getColor(R.color.red));
                    cancel = true;
                }
                break;
        }*/

        return cancel;
    }

    //validation for custome contact detail
    public boolean validationCheckLayer2(){

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtmobilenum.getText())){
            edtmobilenum.setError("Please enter Last Name * ");
            focusView=edtmobilenum;
            cancel=true;
        }
        return cancel;
    }

    private void dateDialog(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                       // edtdob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        edtdob.setText(dayOfMonth  + "/" + (monthOfYear + 1) + "/" + year );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void getGender(String str) {
        List<String> l = Arrays.asList(getResources().getStringArray(R.array.array_gender));
        for (int i=0; i<l.size();i++){
            if(l.get(i).toLowerCase().equals(str.toLowerCase())){
                spngender.setSelection(i+1);
            }
        }
    }
    @Override
    public void onProgressUpdate(int percetage) {
        dialog.setProgress(percetage);
    }
    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mfs100Mantra.onDestroy();
    }
}
