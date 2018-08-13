package com.pasistence.mantrafingerprint;

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

import com.pasistence.mantrafingerprint.database.SQLiteHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class WorkerRegister extends AppCompatActivity {


    private static final int REQUEST_CODE_GALLERY =999 ;
    EditText mEdtName, mEdtAge, mEdtPhone;
    Button mBtnadd, mBtnList;
    ImageView mImageView;

    public static SQLiteHelper mSQLiteHelper;
    static final String EXTRA_GIGAWATTS = "com.theartofdev.edmodo.cropper.CropImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_register);

       /*ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("New Record");
*/
        mEdtName = findViewById(R.id.edtName);
      //  mEdtAge = findViewById( R.id.edtage);
        mEdtPhone = findViewById(R.id.edtPhone);

        mBtnadd = findViewById(R.id.btn_submit);
        mBtnList = findViewById(R.id.btn_list);

        mImageView = findViewById(R.id.imageView);

       //creating database
        mSQLiteHelper = new SQLiteHelper(this,"RECORDDB.sqlite",null);

        //creating table in adatabase
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age VARCHAR, phone VARCHAR, image BLOB)");



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
                try{
                   /* mSQLiteHelper.insertData(mEdtName.getText().toString().trim(),mEdtAge.getText().toString().trim(), mEdtPhone.getText().toString().trim(),imageViewToByte(mImageView));

                    Toast.makeText(WorkerRegister.this, "Added successfully....", Toast.LENGTH_SHORT).show();
                    //reset views
                    mEdtName.setText("");
                    mEdtAge.setText("");
                    mEdtPhone.setText("");
                    mImageView.setImageResource(R.drawable.images);*/
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            public byte[] imageViewToByte(ImageView image) {
                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
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
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
               /* Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //cameraIntent.setType("image/*");
                startActivityForResult(cameraIntent, REQUEST_CODE_GALLERY);*/


          /*     //for Camera Intent
               Intent i = new Intent(WorkerRegister.this,com.theartofdev.edmodo.cropper.CropImage.class);
               startActivityForResult(i,REQUEST_CODE_GALLERY);
               */
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
                        .setAspectRatio(1,1)  //image will be suqare//
                        .start(this);
            }
            else
            {
                Toast.makeText(this, "Dont have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            //return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // Uri imageUri = data.getData();
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
