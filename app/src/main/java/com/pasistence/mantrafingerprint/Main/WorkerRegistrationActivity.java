package com.pasistence.mantrafingerprint.Main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;

import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.FingerPrintMatching.MFS100Mantra;
import com.pasistence.mantrafingerprint.Interface.UploadCallBack;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIContactResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerPersonalResponse;
<<<<<<< HEAD
import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
=======
import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.CurrentAddress;
>>>>>>> 560dd8109b19429d24536cc72b795fd6aa23e5b8
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
<<<<<<< HEAD
=======
import fr.ganfra.materialspinner.MaterialSpinner;
import okhttp3.MultipartBody;
>>>>>>> 560dd8109b19429d24536cc72b795fd6aa23e5b8
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener, UploadCallBack {

    public static final String TAG ="reg -->";
    Button btnLayer1Next,btnLayer2Next,btnLayer3Next,btnLayer2Previous,btnLayer3Previous,btnLayer4previous,btnSubmit;
    Context mContext;
    View layer1,layer2,layer3,layer4;
    WorkerModel workerModel;
    Contactdetails contactdetails;
<<<<<<< HEAD
    BankAccount bankAccount;
=======
>>>>>>> 560dd8109b19429d24536cc72b795fd6aa23e5b8
    MaterialEditText edtname,edtaadharnum,edtdob,edtemail,edtaddressline1,edtaddressline2,edtmobilenum,edtalternatenum,edtcity,edtpincode,edtholdername,
    edtbankifsccode,edtbankaccountnumber,edtbankname;
    CircleImageView profileimage;
    ImageView imgfingerprint1,imgfingerprint2;
   // MaterialSpinner spngender,spnstate;
    Spinner spngender,spnstate;
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

       try{

           if(getIntent()!= null)
           {
               type = (String)getIntent().getStringExtra("type");
               id = (String)getIntent().getStringExtra("id");
               Log.e(TAG, "type"+type+" "+"id"+id );

               if(!type.equals("register"))
               {
                   setWorkerDetails();
               }
                //setWorkerDetails();
           }
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    private void mOnclick() {
        btnLayer1Next.setOnClickListener(this);
        btnLayer2Next.setOnClickListener(this);
        btnLayer3Next.setOnClickListener(this);
        btnLayer4previous.setOnClickListener(this);
        btnLayer3Previous.setOnClickListener(this);
        btnLayer2Previous.setOnClickListener(this);
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
        btnLayer4previous       = (Button)findViewById(R.id.btn_layer4_previous);
        btnLayer3Previous       = (Button)findViewById(R.id.btn_layer3_previous);
        btnLayer2Previous       = (Button)findViewById(R.id.btn_layer2_previous);
        btnSubmit               = (Button)findViewById(R.id.btn_submit);

         layer1                 = findViewById(R.id.layer1);
         layer2                 = findViewById(R.id.layer2);
         layer3                 = findViewById(R.id.layer3);
         layer4                 = findViewById(R.id.layer4);

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


        //spngender             = (MaterialSpinner)findViewById(R.id.spinner_gender);
        spngender               = (Spinner)findViewById(R.id.spinner_gender);
        //spnstate              = (MaterialSpinner)findViewById(R.id.spn_state);
        spnstate                = (Spinner)findViewById(R.id.spn_state);


        //Init Common
        common = new Common();

        //Init Database
        database = new Database(mContext);

        //Init Mantra100 for Fingerprint
        mfs100Mantra = new MFS100Mantra(WorkerRegistrationActivity.this);
        mfs100Mantra.onStart();

        //init Api
        mService = Common.getApi();
    }
    @Override
    public void onClick(View view) {
        if(view == btnLayer1Next)
        {
            layer1.setVisibility(View.INVISIBLE);
            layer2.setVisibility(View.VISIBLE);
            mfs100Mantra.onStop();

            onWorkerRegistration();
          /*  if(!validationCheckLayer1())
            {
                layer1.setVisibility(View.INVISIBLE);
                layer2.setVisibility(View.VISIBLE);
                mfs100Mantra.onStop();

            }else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }*/
        }
        if(view == btnLayer2Next)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
           // onWorkerContactRegistration();
        }
            /*if (!validationCheckLayer2())
            {
                layer2.setVisibility(View.INVISIBLE);
                layer3.setVisibility(View.VISIBLE);
            }
            else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }*/
        if(view == btnLayer3Next)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer4.setVisibility(View.VISIBLE);
            /*if(!validationChekLayer3())
            {
                layer3.setVisibility(View.INVISIBLE);
                layer4.setVisibility(View.VISIBLE);
            } else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }*/
        }
        if(view == btnLayer4previous)
        {
            layer4.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer3Previous)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer2.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer2Previous)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer1.setVisibility(View.VISIBLE);
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

    private void onWorkerContactRegistration() {
        contactdetails=new Contactdetails();

       contactdetails.setAddress_line_1(edtaddressline1.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        contactdetails.setAddress_line_2(edtaddressline2.getText().toString());
        contactdetails.setContact1(Integer.parseInt(edtmobilenum.getText().toString()));
        contactdetails.setContact2(Integer.parseInt(edtalternatenum.getText().toString()));
        contactdetails.setCity(edtcity.getText().toString());
        contactdetails.setCountry(spnstate.getSelectedItem().toString().trim());
        contactdetails.setPincode(edtpincode.getText().toString());

        try
        {
            final AlertDialog dialog = new SpotsDialog(mContext);
            dialog.show();
            dialog.setMessage("Load Contact Details...");
            dialog.setCancelable(false);

            mService.insertcontactdetails(
                   contactdetails.getContact1(),
                   contactdetails.getContact2(),
                    contactdetails.getAddress_line_1().toString(),
                    contactdetails.getAddress_line_2().toString(),
                    contactdetails.getCity().toString(),
                    Integer.parseInt(contactdetails.getPincode()),
                    contactdetails.getState().toString(),
                    contactdetails.getCountry().toString(),
                    PreferenceUtils.getWorker_id(mContext).toString(),
                    contactdetails.getType().toString(),
                    PreferenceUtils.getEmployee_id(mContext).toString())
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

                                //database.deleteToPorjects();
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

    private void onWorkerRegistration() {
        workerModel=new WorkerModel();

        workerModel.setName(edtname.getText().toString());
        // workerModel.setId(edt_Id.getText().toString());
        workerModel.setAdharcard_id(edtaadharnum.getText().toString());
        workerModel.setDob(edtdob.getText().toString());
        workerModel.setEmail(edtemail.getText().toString());
        workerModel.setGender(spngender.getSelectedItem().toString().trim());
        workerModel.setSalary("500");

        finger = mfs100Mantra.getList();
        if(finger.size()<=0)
        {
            workerModel.setFingerprint1("");
            workerModel.setFingerprint2("");

        }else {
            workerModel.setFingerprint1(finger.get(0).toString());
            workerModel.setFingerprint2(finger.get(1).toString());
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

                                //database.deleteToPorjects();
                                //database.addToPorject(projectdetails);
                                }

                           // workerModel = result.getWorkerModel();
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

    private void WorkerRegistrationBtn() {

        workerModel=new WorkerModel();

            workerModel.setName(edtname.getText().toString());
           // workerModel.setId(edt_Id.getText().toString());
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
            workerModel.setId(id);
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
                if(type.equals("edit"))
                {
                    database.updateToWorkersMaster(workerModel);
                    Toast.makeText(mContext, "Worker Updated successfully", Toast.LENGTH_SHORT).show();
                    mfs100Mantra.onDestroy();
                    finish();
                }else if(type.equals("register"))
                {

                    onImageUpload(workerModel.getImageUrl().toString());
                    database.addToWorkers(workerModel);
                    Toast.makeText(mContext, "Worker Registred successfully", Toast.LENGTH_SHORT).show();
                    mfs100Mantra.onDestroy();
                    finish();
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
                                if(result.isError())
                                {
                                    Toast.makeText(mContext, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                    Log.e("-->",result.getError_msg() );
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Log.e("-->", result.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<APIWorkerImageResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
            }
        }).start();



    }

    private void setWorkerDetails() {
        workerModel=new WorkerModel();

        workerModel = (WorkerModel) getIntent().getSerializableExtra("workers");

        edtname.setText(workerModel.getName());
        // workerModel.setId(edt_Id.setText();
        edtaadharnum.setText(workerModel.getAdharcard_id());
        edtdob.setText(workerModel.getDob());
        edtemail.setText(workerModel.getEmail());
        //getGender(workerModel.getGender());
        edtaddressline1.setText(workerModel.getCurrent_address1());
        edtaddressline2.setText(workerModel.getPermanent_address1());
        edtmobilenum.setText(workerModel.getContact1());
        edtalternatenum.setText(workerModel.getContact2());
        edtcity.setText(workerModel.getCity());
        edtpincode.setText(workerModel.getPincode());
        edtholdername.setText(workerModel.getHolder_name());
        edtbankifsccode.setText(workerModel.getIfsc_code());
        edtbankaccountnumber.setText(workerModel.getAccount_number());
        edtbankname.setText(workerModel.getBank_name());
       // profileimage.setImageURI(Uri.parse(workerModel.getImageUrl().toString()));

        Glide.with(mContext)
                .load(workerModel.getImageUrl().toString())
                .into(profileimage);
        setImagePath(workerModel.getImageUrl());
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
   /* public boolean validationCheckLayer1(){

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
        if(spngender.getSelectedItemPosition()==0){
            spngender.setError("Please Select Gender * ");
            focusView=spngender;
            cancel=true;
        }

        *//*if(!isEnroll1){
            imageViewFingerPrint1.setColorFilter(getResources().getColor(R.color.red));
            cancel=true;
        }
        if(!isEnroll2){
            imageViewFingerPrint3.setColorFilter(getResources().getColor(R.color.red));
            cancel=true;
        }*//*

     *//*   switch (type) {
            case"edit":
                break;
            case "register":
                if (getImagePath() == "" || getImagePath() == null) {
                    imageViewFingerPrint2.setBackgroundColor(getResources().getColor(R.color.red));
                    cancel = true;
                }
                break;

        }*//*

        return cancel;
    }*/

    //validation for custome contact detail
    /*public boolean validationCheckLayer2(){

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtaddressline2.getText())){
            edtaddressline2.setError("Please enter Name * ");
            focusView=edtaddressline2;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtmobilenum.getText())){
            edtmobilenum.setError("Please enter Last Name * ");
            focusView=edtmobilenum;
            cancel=true;
        }
        if (TextUtils.isEmpty(edtcity.getText())){
            edtcity.setError("Please enter Last Name * ");
            focusView=edtcity;
            cancel=true;
        }
        return cancel;
    }*/

    //validation for Bank details
   /* public boolean validationChekLayer3() {
        boolean cancle = false;
        View focusView = null;
        if(TextUtils.isEmpty(edtholdername.getText()))
        {
            edtholdername.setError("Please select Account Holder name * ");
            focusView = edtholdername;
            cancle = true;
        }

        if(TextUtils.isEmpty(edtbankifsccode.getText()))
        {
            edtbankifsccode.setError("Please select IFSC Code * ");
            focusView = edtbankifsccode;
            cancle = true;
        }
        if(TextUtils.isEmpty(edtbankaccountnumber.getText()))
        {
            edtbankaccountnumber.setError("Please select Bank Account Number * ");
            focusView = edtbankaccountnumber;
            cancle = true;
        }
        if(TextUtils.isEmpty(edtbankname.getText()))
        {
            edtbankname.setError("Please select Bank Name * ");
            focusView = edtbankname;
            cancle = true;
        }
            return cancle;
    }*/

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
}
