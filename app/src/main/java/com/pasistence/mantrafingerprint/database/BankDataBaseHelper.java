package com.pasistence.mantrafingerprint.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pasistence.mantrafingerprint.Models.BankAccountModel;

public class BankDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAme = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ BankAccountModel.NewUserInfo.TABLE_NAME+
                    "("+BankAccountModel.NewUserInfo.BANK_NAME+" TEXT,"+BankAccountModel.NewUserInfo.ACCOUNT_HOLDER+" TEXT,"+BankAccountModel.NewUserInfo.ACCOUNT_NUMBER+" TEXT,"+BankAccountModel.NewUserInfo.IFSC_CODE+" TEXT);";
    public BankDataBaseHelper(Context context)
    {
        super(context,DATABASE_NAme,null, DATABASE_VERSION);
        Log.e( "DATABASE OPERATION ","Databasecreated/ opened...." );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATION ","Table created......" );


    }

    public void addInformation(String bankname,String accounthollder,String accountnumber,String ifsccode,SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BankAccountModel.NewUserInfo.BANK_NAME,bankname);
        contentValues.put(BankAccountModel.NewUserInfo.ACCOUNT_HOLDER,accounthollder);
        contentValues.put(BankAccountModel.NewUserInfo.ACCOUNT_NUMBER,accountnumber);
        contentValues.put(BankAccountModel.NewUserInfo.IFSC_CODE,ifsccode);

        db.insert(BankAccountModel.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATION ","One row is Inserted......" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

    }
}
