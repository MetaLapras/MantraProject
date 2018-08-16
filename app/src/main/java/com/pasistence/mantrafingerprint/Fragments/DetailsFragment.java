package com.pasistence.mantrafingerprint.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CircleImageView circleImageView;
    TextView txtPersonalName,
            txtPersonalMobileNum,
            txtAdharNumber,
            txtGender,txtPermamanentAddress,
            txtCurrentAddress,txtCity,txtPincode,txtBankName,
            txtHolderName,txtAccoountNumber,txtIfscCode;
            Context mcomtext;
            ArrayList<String> fragmentdetails;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public DetailsFragment() {
        // Required empty public constructor
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
        return view;
    }

    private void mInit(View view) {
        circleImageView = (CircleImageView)view.findViewById(R.id.circular_imgView);
        txtPersonalName = view.findViewById(R.id.txtName);
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

    }

}
