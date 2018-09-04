package com.pasistence.mantrafingerprint.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Common.Common;
import com.pasistence.mantrafingerprint.Main.WorkerRegistrationActivity;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.rengwuxian.materialedittext.MaterialEditText;


import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CircleImageView circleImageView;
    ImageView imgPersonal,imgAddress,imgBank;
    TextView txtPersonalName,
            txtPersonalMobileNum,
            txtAdharNumber,
            txtGender,txtPermamanentAddress,
            txtCurrentAddress,txtCity,txtPincode,txtBankName,
            txtHolderName,txtAccoountNumber,txtIfscCode;

           WorkerModel workerModel;

           Context mcontext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public DetailsFragment(WorkerModel workerModel) {
        this.workerModel= workerModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_details, container, false);
        mInit(view);
        mSetDetails();
        return view;
    }
    private void mInit(View view) {
        circleImageView = (CircleImageView)view.findViewById(R.id.circular_imgView);
        txtPersonalName = view.findViewById(R.id.txt_Name);
        txtPersonalMobileNum = view.findViewById(R.id.txt_mobileNum);
        txtAdharNumber = view.findViewById(R.id.txt_aadharNum);
        txtGender = view.findViewById(R.id.txt_gender);

        txtPermamanentAddress = view.findViewById(R.id.txt_PermanentAddress);
        txtCurrentAddress = view.findViewById(R.id.txt_CurrentAddress);
        txtCity = view.findViewById(R.id.txt_city);
        txtPincode = view.findViewById(R.id.txt_pincode);

        txtBankName = view.findViewById(R.id.txt_bankName);
        txtHolderName = view.findViewById(R.id.txt_BankHolderName);
        txtAccoountNumber = view.findViewById(R.id.txt_AccountNum);
        txtIfscCode = view.findViewById(R.id.txt_ifsc);

        imgPersonal = view.findViewById(R.id.edt_personal_details);
        imgAddress = view.findViewById(R.id.edt_address_details);
        imgBank =view.findViewById(R.id.edt_bank_details);


        imgPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Personal Details", Toast.LENGTH_SHORT).show();
                showAlertDialog();
            }
        });

        imgAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Address Details", Toast.LENGTH_SHORT).show();
                showAlertDialog1();
            }
        });

        imgBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Bank Details", Toast.LENGTH_SHORT).show();
                showAlertDialog2();
            }
        });
    }


    private void mSetDetails() {
        //circleImageView.setImageResource(workerModel.getImageUrl());
        txtPersonalName.setText(workerModel.getName());
        txtPersonalMobileNum.setText(workerModel.getContact1());
        txtAdharNumber.setText(workerModel.adharcard_id);
        txtGender.setText(workerModel.getGender());

        txtPermamanentAddress.setText(workerModel.getPermanent_address1());
        txtCurrentAddress.setText(workerModel.getCurrent_address1());
        txtCity.setText(workerModel.getCity());
        txtPincode.setText(workerModel.getPincode());

        txtBankName.setText(workerModel.getBank_name());
        txtHolderName.setText(workerModel.getHolder_name());
        txtAccoountNumber.setText(workerModel.getAccount_number());
        txtIfscCode.setText(workerModel.getIfsc_code());

        Glide.with(getActivity())
                .load(workerModel.getImageUrl().toString())
                .into(circleImageView);


       /* if(workerModel.getImageUrl().toString().contains("images/workers")){
            String Url = Common.BASE_URL+ workerModel.getImageUrl().toString();
            Glide.with(getActivity())
                    .load(Url) // image url
                    .into(circleImageView) ; // imageview object
        }else {
            Glide.with(getActivity())
                    .load(workerModel.getImageUrl().toString())
                    .into(circleImageView);
        }*/
    }

    private void showAlertDialog() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
       /* alertDialog.setTitle("One More Step !...");
        alertDialog.setMessage("Enter Your Address");*/

        //EditText In Java
       /* final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //Add edittext to Alert Dialog*/

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_personal_details,null);

        final MaterialEditText edtComment;
        //edtAddress = (MaterialEditText)view.findViewById(R.id.edt_order_address);


        //Radio Button

//        RadioButton rdShiptoAddress = (RadioButton)view.findViewById(R.id.rd_shipthisaddress);
//        RadioButton rdHomeAddress = (RadioButton)view.findViewById(R.id.rd_homeaddress);


        //Get Address from Place Autocomplet Fragment


        alertDialog.setView(view);
       // alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
       /* alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create Request to Firebase

                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //Remove Fragment

            }
        });*/
        alertDialog.show();
    }
    private void showAlertDialog1() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
       /* alertDialog.setTitle("One More Step !...");
        alertDialog.setMessage("Enter Your Address");*/

        //EditText In Java
       /* final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //Add edittext to Alert Dialog*/

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_contact_details,null);

        final MaterialEditText edtComment;
        //edtAddress = (MaterialEditText)view.findViewById(R.id.edt_order_address);


        //Radio Button

//        RadioButton rdShiptoAddress = (RadioButton)view.findViewById(R.id.rd_shipthisaddress);
//        RadioButton rdHomeAddress = (RadioButton)view.findViewById(R.id.rd_homeaddress);


        //Get Address from Place Autocomplet Fragment


        alertDialog.setView(view);
        // alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
       /* alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create Request to Firebase

                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //Remove Fragment

            }
        });*/
        alertDialog.show();
    }
    private void showAlertDialog2() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        /*alertDialog.setTitle("One More Step !...");
        alertDialog.setMessage("Enter Your Address");*/

        //EditText In Java
       /* final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //Add edittext to Alert Dialog*/

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custome_bank_details,null);

        final MaterialEditText edtComment;
        //edtAddress = (MaterialEditText)view.findViewById(R.id.edt_order_address);


        //Radio Button

//        RadioButton rdShiptoAddress = (RadioButton)view.findViewById(R.id.rd_shipthisaddress);
//        RadioButton rdHomeAddress = (RadioButton)view.findViewById(R.id.rd_homeaddress);


        //Get Address from Place Autocomplet Fragment


        alertDialog.setView(view);
        // alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
       /* alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create Request to Firebase

                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //Remove Fragment

            }
        });*/
        alertDialog.show();
    }


}
