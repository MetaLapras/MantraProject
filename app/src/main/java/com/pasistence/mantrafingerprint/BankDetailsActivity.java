package com.pasistence.mantrafingerprint;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.database.BankDataBaseHelper;

public class BankDetailsActivity extends AppCompatActivity {
     EditText bankname,accountholder,accountnumber,ifsccode;
     Button btnsubmit;
     Context context = this;
     SQLiteDatabase sqLiteDatabase;
     BankDataBaseHelper bankDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        bankname = findViewById(R.id.bank_name);
        accountholder =findViewById(R.id.account_name);
        accountnumber = findViewById(R.id.account_number);
        ifsccode = findViewById(R.id.ifsc_code);
        btnsubmit = findViewById(R.id.submit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String Bankame = bankname.getText().toString();
                String AccountName = accountholder.getText().toString();
                String AccountNumber = accountnumber.getText().toString();
                String IfscCode = ifsccode.getText().toString();

                bankDataBaseHelper = new BankDataBaseHelper(context);
                sqLiteDatabase = bankDataBaseHelper.getWritableDatabase();
                bankDataBaseHelper.addInformation(Bankame,AccountName,AccountNumber,IfscCode,sqLiteDatabase);
                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show();
                bankDataBaseHelper.close();

            }
        });
    }
}
