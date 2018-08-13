package com.pasistence.mantrafingerprint.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Adapter.WorkerListAdapter;
import com.pasistence.mantrafingerprint.Models.ModelWorker;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.database.DatabaseHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class WorkerDisplayList extends AppCompatActivity {

   /* ListView mlistView;
    ArrayList<ModelWorker> mList;
    WorkerListAdapter mAdapter= null;

    ImageView imageViewIcon;*/
    ListView mlistView;
    ArrayList<WorkerModel> mList;
    WorkerListAdapter mAdapter= null;

    ImageView imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_display_list);

      /* ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Record List");*/





        mlistView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new WorkerListAdapter(this, R.layout.raw, mList);
        mlistView.setAdapter(mAdapter);
/*

        //get all data from SQlite
      Cursor cursor = ge.("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String memberId = cursor.getString(1);
            String adminId = cursor.getString(2);
            String statusId = cursor.getString(3);
            String firstName = cursor.getString(4);
            String middleName = cursor.getString(5);
            String lastName = cursor.getString(6);
            String empId = cursor.getString(7);
            String contactNo = cursor.getString(8);
            String alternatNo = cursor.getString(9);

            String emailId = cursor.getString(10);
            String permanentAddress = cursor.getString(10);
            String currentAddress = cursor.getString(10);
            String fingerprintOne = cursor.getString(10);
            String fingerprintTwo = cursor.getString(10);
            String bankName = cursor.getString(10);
            byte[] image = cursor.getBlob(11);


            //add to list
            mList.add(new WorkerModel(id ,memberId, adminId, adminId, statusId, firstName, middleName, lastName, empId, contactNo, alternatNo,
                    emailId, permanentAddress, currentAddress, fingerprintOne, fingerprintTwo, bankName));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found..", Toast.LENGTH_SHORT).show();
        }
*/

        /*mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mList.get(i).getName();
            }
        });*/
    }


/*        mlistView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new WorkerListAdapter(this, R.layout.raw, mList);
        mlistView.setAdapter(mAdapter);

        //get all data from SQlite
        Cursor cursor = WorkerRegister.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String lastname = cursor.getString(2);
            String aadharnumber = cursor.getString(3);
            String contactnum = cursor.getString(4);
            String alternatenum = cursor.getString(5);
            String age = cursor.getString(6);
            String doj = cursor.getString(7);
            String marritalstatus = cursor.getString(8);
            String gender = cursor.getString(9);

            String phone = cursor.getString(10);
            byte[] image = cursor.getBlob(11);


            //add to list
            mList.add(new ModelWorker(id, name, lastname, aadharnumber, contactnum, alternatenum, age, doj, marritalstatus, gender, phone, image));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found..", Toast.LENGTH_SHORT).show();
        }

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mList.get(i).getName();
            }
        });
    }
   */ /*   mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display option of update and delete

                CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(WorkerDisplayList.this);
                dialog.setTitle("choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       if(i == 0)
                       {
                           //update
                           Cursor c = WorkerRegister.mSQLiteHelper.getData("SELECT id FROM RECORD");
                           ArrayList<Integer>arrID = new ArrayList<Integer>();

                           while (c.moveToNext())
                           {
                               arrID.add(c.getInt(0));
                           }
                           //show update dialog
                           showDiaglogUpdate(WorkerDisplayList.this,arrID.get(position));
                       }
                     if(i==1)
                       {
                          //delete
                           Cursor c = WorkerRegister.mSQLiteHelper.getData("SELECT id FROM RECORD");
                           ArrayList<Integer> arrID = new ArrayList<Integer>();
                           while (c.moveToNext())
                           {
                               arrID.add(c.getInt(0));
                           }

                           showDialogDelete(arrID.get(position));
                       }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(WorkerDisplayList.this);
        dialogDelete.setTitle("Warning");
        dialogDelete.setMessage("Are you want to delete ? ");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try
                {
                   WorkerRegister.mSQLiteHelper.deleteData(idRecord);
                    Toast.makeText(WorkerDisplayList.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Log.e("error",e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDiaglogUpdate(Activity activity,final  int position)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
        final EditText edtName = dialog.findViewById(R.id.edtName);
        final EditText edtLastName = dialog.findViewById(R.id.edtLastName);
        final EditText edtAadharNumber = dialog.findViewById(R.id.edtAadharNumber);
        final EditText edtContactNum = dialog.findViewById(R.id.edt_contact_number);
        final EditText edtAlternateNum = dialog.findViewById(R.id.edt_alternate_number);
        final EditText edtAge = dialog.findViewById(R.id.edtAge);
        final EditText edtDoj = dialog.findViewById(R.id.edt_doj);
        final EditText edtMarritalStatus = dialog.findViewById(R.id.edt_marritalstatus);
        final EditText edtGender = dialog.findViewById(R.id.edt_gender);
        final EditText edtPhone = dialog.findViewById(R.id.edtPhone);
        Button btnUpdate = dialog.findViewById(R.id.btnupdate);

        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        //set height of Dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        //in update dialog click image view to update image
        imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check external storage permission

                        ActivityCompat.requestPermissions(WorkerDisplayList.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                888);
                    }
                });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                           WorkerRegister.mSQLiteHelper.updateData(
                                   edtName.getText().toString().trim(),
                                   edtLastName.getText().toString().trim(),
                                   edtAadharNumber.getText().toString().trim(),
                                   edtContactNum.getText().toString().trim(),
                                   edtAlternateNum.getText().toString().trim(),
                                   edtAge.getText().toString().trim(),
                                   edtDoj.getText().toString().trim(),
                                   edtMarritalStatus.getText().toString().trim(),
                                   edtGender.getText().toString().trim(),
                                   edtPhone.getText().toString().trim(),
                                   WorkerRegister.imageViewToByte(imageViewIcon),
                                   position
                           );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error)
                {
                    Log.e("Update error",error.getMessage());
                }
                updateRecordList();
            }
        });
            }

    private void updateRecordList() {

        //GET ALL DATA from SQlite
        Cursor cursor = WorkerRegister.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String lastname = cursor.getString(2);
            String aadharnumber = cursor.getString(3);
            String contactnum = cursor.getString(4);
            String alternatenum = cursor.getString(5);
            String age = cursor.getString(6);
            String doj = cursor.getString(7);
            String marritalstatus = cursor.getString(8);
            String gender = cursor.getString(9);
            String phone = cursor.getString(10);
            byte[] image = cursor.getBlob(11);

            mList.add(new ModelWorker(id,name,lastname,aadharnumber,contactnum,alternatenum,age,doj,marritalstatus,gender,phone,image));
        }
        mAdapter.notifyDataSetChanged();
    }




    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 888);
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
        if(requestCode == 888 && resultCode == RESULT_OK)
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
                imageViewIcon.setImageURI(resultUri);
            }
            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}





