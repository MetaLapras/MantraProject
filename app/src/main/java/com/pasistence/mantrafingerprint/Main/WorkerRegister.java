package com.pasistence.mantrafingerprint.Main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.SQLiteHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class WorkerRegister extends AppCompatActivity {


    private static final int REQUEST_CODE_GALLERY =999 ;
    EditText mEdtName,mEdtLastName, mEdtaadharNumber,mEdtContactNum,mEdtAlternateNum,mEdtAge,mEdtDoj,mEdtMarritalStatus,mEdtGender, mEdtPhone;
    Button mBtnadd, mBtnList;
    ImageView mImageView;

    public static SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_register);
        mInit();
       /*ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("New Record");
        */



       //creating database
        mSQLiteHelper = new SQLiteHelper(this,"RECORDDB.sqlite",null);

        //creating table in adatabase
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, lastname VARCHAR,aadharnumber VARCHAR, contactnum VARCHAR, alternatenum VARCHAR, age VARCHAR, doj VARCHAR, marritalstatus VARCHAR, gender varchar, phone VARCHAR, image BLOB)");



        //select image by on imageView click

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //read external stroage permission to select image from gallery
              /*  ActivityCompat.requestPermissions(WorkerRegister.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/

                ActivityCompat.requestPermissions(WorkerRegister.this,new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_GALLERY);

                    }

        });

        //add record to the Sqlite
        mBtnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mSQLiteHelper.insertData(mEdtName.getText().toString().trim(),
                            mEdtLastName.getText().toString().trim(),
                            mEdtaadharNumber.getText().toString().trim(),
                            mEdtContactNum.getText().toString().trim(),
                            mEdtAlternateNum.getText().toString().trim(),
                            mEdtAge.getText().toString().trim(),
                            mEdtDoj.getText().toString().trim(),
                            mEdtMarritalStatus.getText().toString().trim(),
                            mEdtGender.getText().toString().trim(),
                            mEdtPhone.getText().toString().trim(),
                            imageViewToByte(mImageView));

                    Toast.makeText(WorkerRegister.this, "Added successfully....", Toast.LENGTH_SHORT).show();
                    //reset views
                    mEdtName.setText("");
                    mEdtLastName.setText("");
                    mEdtaadharNumber.setText("");
                    mEdtContactNum.setText("");
                    mEdtAlternateNum.setText("");
                    mEdtAge.setText("");
                    mEdtMarritalStatus.setText("");
                    mEdtGender.setText("");
                    mEdtPhone.setText("");
                    mImageView.setImageResource(R.drawable.images);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        //show record list
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerRegister.this,WorkerDisplayList.class));

            }
        });

    }

    private void mInit() {
        mEdtName = findViewById(R.id.edt_name);
        mEdtLastName = findViewById(R.id.last_name);
        mEdtaadharNumber = findViewById(R.id.addhar_number);
        mEdtContactNum = findViewById(R.id.edt_contact_number);
        mEdtAlternateNum = findViewById(R.id.edt_alternate_number);
        mEdtAge = findViewById( R.id.edt_age);
        mEdtDoj = findViewById(R.id.edt_doj);
        mEdtMarritalStatus = findViewById(R.id.edt_marritalstatus);
        mEdtGender = findViewById(R.id.edt_gender);
        mEdtPhone = findViewById(R.id.edt_phone);

        mBtnadd = findViewById(R.id.btn_submit);
        mBtnList = findViewById(R.id.btn_list);

        mImageView = findViewById(R.id.image_view);


    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        /*if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(this, "Dont have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }*/

         if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(this, "Dont have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK)
        {
           Uri imageUri = data.getData();
           CropImage.activity(imageUri)
                  .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                  .setAspectRatio(1,1)  //image will be suqare//
                  .start(this);
            }

            if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK)
                {
                    Uri resultUri = result.getUri();
                    //set image choosed from gallery to image view
                    mImageView.setImageURI(resultUri);
                }
                else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
                {
                    Exception error = result.getError();
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
