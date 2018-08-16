package com.pasistence.mantrafingerprint.Main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.FingerPrintMatching.MFS100Mantra;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.ganfra.materialspinner.MaterialSpinner;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG ="reg -->";
    Button btnLayer1Next,btnLayer2Next,btnLayer3Next,btnLayer2Previous,btnLayer3Previous,btnLayer4previous,btnSubmit;
    Context mContext;
    View layer1,layer2,layer3,layer4;
    WorkerModel workerModel;
    MaterialEditText edtname,edtaadharnum,edtdob,edtemail,edtaddressline1,edtaddressline2,edtmobilenum,edtalternatenum,edtcity,edtpincode,edtholdername,
    edtbankifsccode,edtbankaccountnumber,edtbankname;
    CircleImageView profileimage;
    ImageView imgfingerprint1,imgfingerprint2;
    MaterialSpinner spngender,spnstate;
    String ImagePath;
    Database database;

    private int mYear, mMonth, mDay;
    String type,id ;

    MFS100Mantra mfs100Mantra;

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
                setWorkerDetails();
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

        btnLayer1Next = (Button)findViewById(R.id.btn_layer1_next);
        btnLayer2Next = (Button)findViewById(R.id.btn_layer2_next);
        btnLayer3Next = (Button)findViewById(R.id.btn_layer3_next);
        btnLayer4previous = (Button)findViewById(R.id.btn_layer4_previous);
        btnLayer3Previous = (Button)findViewById(R.id.btn_layer3_previous);
        btnLayer2Previous = (Button)findViewById(R.id.btn_layer2_previous);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

         layer1  = findViewById(R.id.layer1);
         layer2 = findViewById(R.id.layer2);
         layer3 = findViewById(R.id.layer3);
         layer4 = findViewById(R.id.layer4);

         //initialsing the Component
        edtname                 = (MaterialEditText) findViewById(R.id.edt_name);
        edtaadharnum            = (MaterialEditText)findViewById(R.id.edt_aadharCard);
        edtdob                  = (MaterialEditText)findViewById(R.id.edt_dob);
        edtemail                = (MaterialEditText)findViewById(R.id.edt_email);
        spngender               = (MaterialSpinner)findViewById(R.id.spinner_gender);
        imgfingerprint1         = (ImageView) findViewById(R.id.fingerprint1);
        imgfingerprint2         = (ImageView) findViewById(R.id.fingerprint2);
        edtaddressline1         = (MaterialEditText)findViewById(R.id.edt_address1);
        edtaddressline2         = (MaterialEditText)findViewById(R.id.edt_address2);
        edtmobilenum            = (MaterialEditText)findViewById(R.id.edt_contact1);
        edtalternatenum         = (MaterialEditText)findViewById(R.id.edt_contact2);
        edtcity                 = (MaterialEditText)findViewById(R.id.edt_city);
        spnstate                = (MaterialSpinner)findViewById(R.id.spn_state);
        edtpincode              = (MaterialEditText)findViewById(R.id.edt_pincode);
        edtholdername           = (MaterialEditText)findViewById(R.id.edt_Bank_Holder_Name);
        edtbankifsccode         = (MaterialEditText)findViewById(R.id.edt_Bank_Ifsc_code);
        edtbankaccountnumber    = (MaterialEditText)findViewById(R.id.edt_Bank_Account_Number);
        edtbankname             = (MaterialEditText)findViewById(R.id.edt_Bank_Name);
        profileimage            = (CircleImageView) findViewById(R.id.img_profile_image);

        database = new Database(mContext);
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
            }
            else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }
        }
        if(view == btnLayer3Next)
        {
            if(!validationChekLayer3())
            {
                layer3.setVisibility(View.INVISIBLE);
                layer4.setVisibility(View.VISIBLE);
            } else
            {
                Toast.makeText(mContext,"something is missing",Toast.LENGTH_LONG).show();
            }
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

            mfs100Mantra = new MFS100Mantra(WorkerRegistrationActivity.this,imgfingerprint1);
            mfs100Mantra.onStart();
            mfs100Mantra.startCapturing();
            workerModel.setFingerprint1(mfs100Mantra.getScanFingerprint());
            Log.e(TAG, mfs100Mantra.getScanFingerprint().toString());
            mfs100Mantra.onStop();
        }
    }

    private void WorkerRegistrationBtn() {
        workerModel=new WorkerModel();


            workerModel.setName(edtname.getText().toString());
           // workerModel.setId(edt_Id.getText().toString());
            workerModel.setAdharcardId(edtaadharnum.getText().toString());
            workerModel.setDob(edtdob.getText().toString());
            workerModel.setEmail(edtemail.getText().toString());
            workerModel.setGender(spngender.getSelectedItem().toString().trim());
            workerModel.setPermanent_address(edtaddressline1.getText().toString());
            workerModel.setCurrent_address(edtaddressline2.getText().toString());
            workerModel.setContact1(edtmobilenum.getText().toString());
            workerModel.setContact2(edtalternatenum.getText().toString());
            workerModel.setCity(edtcity.getText().toString());
            workerModel.setPincode(edtpincode.getText().toString());
            workerModel.setHolder_name(edtholdername.getText().toString());
            workerModel.setIfsc_code(edtbankifsccode.getText().toString());
            workerModel.setAccount_number(edtbankaccountnumber.getText().toString());
            workerModel.setBank_name(edtbankname.getText().toString());
            workerModel.setImageUrl(getImagePath());
            workerModel.setId(id);


            try{
                if(type.equals("edit"))
                {
                    database.updateToWorkersMaster(workerModel);
                    Toast.makeText(mContext, "Worker Updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else if(type.equals("register"))
                {
                    database.addToWorkers(workerModel);
                    Toast.makeText(mContext, "Worker Registred successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }catch (NullPointerException e)
            {
                e.printStackTrace();
                Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
    }

    private void setWorkerDetails() {

        workerModel = (WorkerModel) getIntent().getSerializableExtra("workers");

        edtname.setText(workerModel.getName());
        // workerModel.setId(edt_Id.setText();
        edtaadharnum.setText(workerModel.getAdharcardId());
        edtdob.setText(workerModel.getDob());
        edtemail.setText(workerModel.getEmail());
        getGender(workerModel.getGender());
        edtaddressline1.setText(workerModel.getCurrent_address());
        edtaddressline2.setText(workerModel.getPermanent_address());
        edtmobilenum.setText(workerModel.getContact1());
        edtalternatenum.setText(workerModel.getContact2());
        edtcity.setText(workerModel.getCity());
        edtpincode.setText(workerModel.getPincode());
        edtholdername.setText(workerModel.getHolder_name());
        edtbankifsccode.setText(workerModel.getIfsc_code());
        edtbankaccountnumber.setText(workerModel.getAccount_number());
        edtbankname.setText(workerModel.getBank_name());
        profileimage.setImageURI(Uri.parse(workerModel.getImageUrl()));
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
                profileimage.setImageURI(resultUri);
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
        if(spngender.getSelectedItemPosition()==0){
            spngender.setError("Please Select Gender * ");
            focusView=spngender;
            cancel=true;
        }

        /*if(!isEnroll1){
            imageViewFingerPrint1.setColorFilter(getResources().getColor(R.color.red));
            cancel=true;
        }
        if(!isEnroll2){
            imageViewFingerPrint3.setColorFilter(getResources().getColor(R.color.red));
            cancel=true;
        }*/

     /*   switch (type) {
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
    }

    //validation for Bank details
    public boolean validationChekLayer3() {
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

}
