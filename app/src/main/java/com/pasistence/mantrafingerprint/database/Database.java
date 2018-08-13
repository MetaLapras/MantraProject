package com.pasistence.mantrafingerprint.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "foodorderingdb.db";
    private static final int DB_VERSION = 1;
    private int countCart;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //insert into Worker table
    public void addToWorkers(WorkerModel workerModel){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO worker_master(worker_id,name,adharcard_id,gender,dob,fingerprint1,fingerprint2,email,permanent_address_id,current_address_id,contact1,contact2,salary,created_at,updated_at,bank_id,project_id,activation,image_url)" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
        workerModel.getWorkerId(),
        workerModel.getName(),
        workerModel.getAdharcardId(),
        workerModel.getGender(),
        workerModel.getDob(),
        workerModel.getFingerprint1(),
        workerModel.getFingerprint2(),
        workerModel.getEmail(),
        workerModel.getPermanentAddressId(),
        workerModel.getCurrentAddressId(),
        workerModel.getContact1(),
        workerModel.getContact2(),
        workerModel.getSalary(),
        workerModel.getCreatedAt(),
        workerModel.getUpdatedAt(),
        workerModel.getBankId(),
        workerModel.getProjectId(),
        workerModel.getActivation(),
        workerModel.getImageUrl());

        db.execSQL(query);
    }
    public List<WorkerModel> getAllWorkers(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {"id","worker_id","name","adharcard_id","gender","dob","fingerprint1","fingerprint2","email","permanent_address_id","current_address_id","contact1","contact2","salary","created_at","updated_at","bank_id","project_id","activation","image_url"};
        String sqlTable = "worker_master";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<WorkerModel> result = new ArrayList<WorkerModel>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerModel workerModel = new WorkerModel();
                workerModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                workerModel.setWorkerId(cursor.getString(cursor.getColumnIndex("worker_id")));
                workerModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                workerModel.setAdharcardId(cursor.getString(cursor.getColumnIndex("adharcard_id")));
                workerModel.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                workerModel.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                workerModel.setFingerprint1(cursor.getString(cursor.getColumnIndex("fingerprint1")));
                workerModel.setFingerprint2(cursor.getString(cursor.getColumnIndex("fingerprint2")));
                workerModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                workerModel.setPermanentAddressId(cursor.getString(cursor.getColumnIndex("permanent_address_id")));
                workerModel.setCurrentAddressId(cursor.getString(cursor.getColumnIndex("current_address_id")));
                workerModel.setContact1(cursor.getString(cursor.getColumnIndex("contact1")));
                workerModel.setContact2(cursor.getString(cursor.getColumnIndex("contact2")));
                workerModel.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                workerModel.setCreatedAt(cursor.getString(cursor.getColumnIndex("created_at")));
                workerModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex("updated_at")));
                workerModel.setBankId(cursor.getString(cursor.getColumnIndex("bank_id")));
                workerModel.setProjectId(cursor.getString(cursor.getColumnIndex("project_id")));
                workerModel.setActivation(cursor.getString(cursor.getColumnIndex("activation")));
                workerModel.setImageUrl(cursor.getString(cursor.getColumnIndex("image_url")));


                result.add(workerModel);

            }while (cursor.moveToNext());
        }
        return result;
    }

}
