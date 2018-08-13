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
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MemberRegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE_GALLERY = 999;
    EditText edt_firstName, edt_middleName, edt_lastName, edt_workerId, edt_contactnumber,
            edt_alternatingnumber, edt_date_of_birth, edt_emailId, edt_PermanentAddress, edt_currentAddress, edt_bank_Name, edt_account_holder;

    Button btn_submit;
    ImageView img_photo, img_fingerprint_one, img_fingerprint_two;

    Context mcontext;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);

        nInit();

    }

    private void nInit() {

        mcontext = MemberRegisterActivity.this;

        edt_firstName = findViewById(R.id.first_name);
        edt_middleName = findViewById(R.id.middle_name);
        edt_lastName = findViewById(R.id.last_name);
        edt_workerId = findViewById(R.id.addhar_number);
        edt_contactnumber = findViewById(R.id.contact_number);
        edt_alternatingnumber = findViewById(R.id.alternate_number);
        edt_date_of_birth = findViewById(R.id.dob);
        edt_emailId = findViewById(R.id.email);
        edt_PermanentAddress = findViewById(R.id.permnent_address);
        edt_currentAddress = findViewById(R.id.current_address);
        edt_bank_Name = findViewById(R.id.bank_name);
        edt_account_holder = findViewById(R.id.account_name);

        btn_submit = findViewById(R.id.btn_submit);

        img_photo = findViewById(R.id.imageView2);
        img_fingerprint_one = findViewById(R.id.fingerprint1);
        img_fingerprint_two = findViewById(R.id.fingerprint2);

        btn_submit.setOnClickListener(this);
        img_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                        .setAspectRatio(1, 1)  //image will be suqare//
                        .start(MemberRegisterActivity.this);

                               /* ActivityCompat.requestPermissions(MemberRegisterActivity.this, new String[]{Manifest.permission.CAMERA},
                                        REQUEST_CODE_GALLERY);*/
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == btn_submit) {
            WorkerRegistrationBtn();
            Toast.makeText(mcontext, "Data Saved Sucessfully...", Toast.LENGTH_SHORT).show();
        }
    }

    private void WorkerRegistrationBtn() {

    }


/*

        public static byte[] imageViewToByte(ImageView image) {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


                if (requestCode == REQUEST_CODE_GALLERY) {
                        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                //gallery intent
                                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
                        } else {
                                Toast.makeText(this, "Dont have permission to access file location", Toast.LENGTH_SHORT).show();
                        }
                        return;
                }

        }*/

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

                if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
                        Uri imageUri = data.getData();
                        CropImage.activity(imageUri)
                                .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                                .setAspectRatio(1, 1)  //image will be suqare//
                                .start(this);
                }

                if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                        CropImage.ActivityResult result = CropImage.getActivityResult(data);
                        if (resultCode == RESULT_OK) {
                                Uri resultUri = result.getUri();
                                //set image choosed from gallery to image view
                                img_photo.setImageURI(resultUri);
                        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                                Exception error = result.getError();
                        }
                }

                super.onActivityResult(requestCode, resultCode, data);
        }
}
