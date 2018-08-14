package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.Database;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.ganfra.materialspinner.MaterialSpinner;

public class WorkerRegistrationActivity extends AppCompatActivity implements View.OnClickListener{

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
        btnLayer4previous.setOnClickListener(this);
        btnLayer3Previous.setOnClickListener(this);
        btnLayer2Previous.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        profileimage.setOnClickListener(this);

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
        edtname                = (MaterialEditText) findViewById(R.id.edt_name);
        edtaadharnum          = (MaterialEditText)findViewById(R.id.edt_aadharCard);
        edtdob                 = (MaterialEditText)findViewById(R.id.edt_dob);
        edtemail               = (MaterialEditText)findViewById(R.id.edt_email);
        spngender              = (MaterialSpinner)findViewById(R.id.spinner_gender);
        imgfingerprint1        = findViewById(R.id.fingerprint1);
        imgfingerprint2        = findViewById(R.id.fingerprint2);
        edtaddressline1       = (MaterialEditText)findViewById(R.id.edt_address1);
        edtaddressline2       = (MaterialEditText)findViewById(R.id.edt_address2);
        edtmobilenum          = (MaterialEditText)findViewById(R.id.edt_contact1);
        edtalternatenum       = (MaterialEditText)findViewById(R.id.edt_contact2);
        edtcity                = (MaterialEditText)findViewById(R.id.edt_city);
        spnstate               = (MaterialSpinner)findViewById(R.id.spn_state);
        edtpincode             = (MaterialEditText)findViewById(R.id.edt_pincode);
        edtholdername         = (MaterialEditText)findViewById(R.id.edt_Bank_Holder_Name);
        edtbankifsccode      = (MaterialEditText)findViewById(R.id.edt_Bank_Ifsc_code);
        edtbankaccountnumber = (MaterialEditText)findViewById(R.id.edt_Bank_Account_Number);
        edtbankname           = (MaterialEditText)findViewById(R.id.edt_Bank_Name);
        profileimage           = (CircleImageView) findViewById(R.id.img_profile_image);

        database = new Database(mContext);

    }



    @Override
    public void onClick(View view) {
        if(view == btnLayer1Next)
        {
            layer1.setVisibility(View.INVISIBLE);
            layer2.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer2Next)
        {
            layer2.setVisibility(View.INVISIBLE);
            layer3.setVisibility(View.VISIBLE);
        }
        if(view == btnLayer3Next)
        {
            layer3.setVisibility(View.INVISIBLE);
            layer4.setVisibility(View.VISIBLE);
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
            Toast.makeText(mContext, "Data Submitted successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(view == profileimage)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                    .setAspectRatio(1,1)  //image will be suqare//
                    .start(this);
        }
    }

    private void WorkerRegistrationBtn() {
        workerModel=new WorkerModel();
            workerModel.setName(edtname.getText().toString());
           // workerModel.setId(edt_Id.getText().toString());
            workerModel.setAdharcardId(edtaadharnum.getText().toString());
            workerModel.setDob(edtdob.getText().toString());
            workerModel.setEmail(edtemail.getText().toString());
          //  workerModel.setGender(spn_gender.getSelectedItem().toString().trim());
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

            database.addToWorkers(workerModel);


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

}
