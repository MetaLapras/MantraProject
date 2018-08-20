package com.pasistence.mantrafingerprint.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.EmployeeDetails;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Projectdetails;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "mantra_database.db";
    private static final int DB_VERSION = 1;
    private int countCart;
    public static final String TAG = "Database-->";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //Table Worker Details
    public void addToWorkers(WorkerModel workerModel){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO worker_master(" +
                        "worker_id," +
                        "name," +
                        "adharcard_id," +
                        "gender,dob," +
                        "fingerprint1," +
                        "fingerprint2," +
                        "email," +
                        "permanent_address_id," +
                        "current_address_id," +
                        "contact1," +
                        "contact2," +
                        "salary," +
                        "created_at," +
                        "updated_at," +
                        "bank_id," +
                        "project_id," +
                        "activation," +
                        "image_url,permanent_address,current_address,bank_name,holder_name,ifsc_code,account_number,city,pincode" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
        workerModel.getId(),
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
        workerModel.getImageUrl(),
        workerModel.getPermanent_address(),
        workerModel.getCurrent_address(),
        workerModel.getBank_name(),
        workerModel.getHolder_name(),
        workerModel.getIfsc_code(),
        workerModel.getAccount_number(),
        workerModel.getCity(),
        workerModel.getPincode());


        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully");
        Log.e(TAG, workerModel.toString());
    }
    public List<WorkerModel> getAllWorkers(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "id",
                "worker_id",
                "name",
                "adharcard_id",
                "gender",
                "dob",
                "fingerprint1",
                "fingerprint2",
                "email",
                "permanent_address_id",
                "current_address_id",
                "contact1",
                "contact2",
                "salary",
                "created_at",
                "updated_at",
                "bank_id",
                "project_id",
                "activation",
                "image_url",
                "permanent_address",
                "current_address",
                "bank_name",
                "holder_name",
                "ifsc_code",
                "account_number",
                "city",
                "pincode"
        };
        String sqlTable = "worker_master";

        String selectQuery = "SELECT  * FROM  worker_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<WorkerModel> result = new ArrayList<WorkerModel>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerModel workerModel = new WorkerModel();
                workerModel.setId(cursor.getString(cursor.getColumnIndex("id")));
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
                workerModel.setPermanent_address(cursor.getString(cursor.getColumnIndex("permanent_address")));
                workerModel.setCurrent_address(cursor.getString(cursor.getColumnIndex("current_address")));
                workerModel.setBank_name(cursor.getString(cursor.getColumnIndex("bank_name")));
                workerModel.setHolder_name(cursor.getString(cursor.getColumnIndex("holder_name")));
                workerModel.setIfsc_code(cursor.getString(cursor.getColumnIndex("ifsc_code")));
                workerModel.setAccount_number(cursor.getString(cursor.getColumnIndex("account_number")));
                workerModel.setCity(cursor.getString(cursor.getColumnIndex("city")));
                workerModel.setPincode(cursor.getString(cursor.getColumnIndex("pincode")));

                result.add(workerModel);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }
    public void updateToWorkers(WorkerModel workerModel) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE " +
                "worker_master " +
                "SET name = %s, " +
                "adharcard_id = %s," +
                "gender = %s," +
                "dob = %s," +
                "fingerprint1 = %s," +
                "fingerprint2 = %s," +
                "email = %s," +
                "permanent_address_id = %s," +
                "current_address_id = %s," +
                "contact1 = %s," +
                "contact2 = %s," +
                "salary = %s," +
                "created_at = %s," +
                "updated_at = %s," +
                "bank_id = %s," +
                "project_id = %s," +
                "activation = %s," +
                "image_url = %s," +
                "permanent_address = %s," +
                "current_address = %s," +
                "bank_name = %s," +
                "holder_name = %s," +
                "ifsc_code = %s," +
                "account_number = %s," +
                "city = %s," +
                "pincode = %s" +
                /*"WHERE worker_id = %s",*/
                "WHERE id = %d",
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
                workerModel.getImageUrl(),
                workerModel.getPermanent_address(),
                workerModel.getCurrent_address(),
                workerModel.getBank_name(),
                workerModel.getHolder_name(),
                workerModel.getIfsc_code(),
                workerModel.getAccount_number(),
                workerModel.getCity(),
                workerModel.getPincode(),
                workerModel.getId()
                /*workerModel.getWorkerId()/*Add Later on when Webservices*/);

        db.execSQL(query);
    }
    public void updateToWorkersMaster(WorkerModel workerModel) {
        String sqlTable = "worker_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",workerModel.getName());
        values.put("adharcard_id",workerModel.getAdharcardId());
        values.put("gender",workerModel.getGender());
        values.put("dob",workerModel.getDob());
        values.put("fingerprint1",workerModel.getFingerprint1());
        values.put("fingerprint2",workerModel.getFingerprint2());
        values.put("email",workerModel.getEmail());
        values.put("permanent_address_id",workerModel.getPermanentAddressId());
        values.put("current_address_id",workerModel.getCurrentAddressId());
        values.put("contact1",workerModel.getContact1());
        values.put("contact2",workerModel.getContact2());
        values.put("salary",workerModel.getSalary());
        values.put("created_at",workerModel.getCreatedAt());
        values.put("updated_at",workerModel.getUpdatedAt());
        values.put("bank_id",workerModel.getBankId());
        values.put("project_id",workerModel.getProjectId());
        values.put("activation",workerModel.getActivation());
        values.put("image_url",workerModel.getImageUrl());
        values.put("permanent_address",workerModel.getPermanent_address());
        values.put("current_address",workerModel.getCurrent_address());
        values.put("bank_name",workerModel.getBank_name());
        values.put("holder_name",workerModel.getHolder_name());
        values.put("ifsc_code",workerModel.getIfsc_code());
        values.put("account_number",workerModel.getAccount_number());
        values.put("city",workerModel.getCity());
        values.put("pincode",workerModel.getPincode());

                /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "id = ?",
                new String[]{workerModel.getId()});
    }
    public void deleteToWorkers(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM worker_master WHERE id = '%s'",workerId);
        db.execSQL(query);
    }
    public void deleteToWorkers() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM worker_master");
        db.execSQL(query);
      //  db.delete("worker_master",null,null);

    }


    //Table Project Details
    public void addToPorject(Projectdetails projectdetails){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format(
                "INSERT OR REPLACE INTO project_master(" +
                        "project_id," +
                        "project_name," +
                        "location," +
                        "password," +
                        "created_at," +
                        "updated_at," +
                        "activation," +
                        "admin_id," +
                        "employee_id" +
                        ")" +
                        " VALUES('%d','%s','%s','%s','%s','%s','%s','%d','%d');",
                projectdetails.getProject_id(),
                projectdetails.getProject_name(),
                projectdetails.getLocation(),
                projectdetails.getPassword(),
                projectdetails.getCreated_at(),
                projectdetails.getUpdated_at(),
                projectdetails.getActivation(),
                projectdetails.getAdmin_id(),
                projectdetails.getEmployee_id());

        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully");
        Log.e(TAG, projectdetails.toString());
    }
    public List<Projectdetails> getAllProject(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "id",
                "project_id",
                "project_name",
                "location",
                "password",
                "created_at",
                "updated_at",
                "activation",
                "admin_id",
                "employee_id"
        };
        String sqlTable = "project_master";

        String selectQuery = "SELECT  * FROM  project_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Projectdetails> result = new ArrayList<Projectdetails>();
        if(cursor.moveToFirst())
        {
            do {
                Projectdetails projectdetails = new Projectdetails();
                projectdetails.setId(cursor.getInt(cursor.getColumnIndex("id")));
                projectdetails.setProject_id(cursor.getInt(cursor.getColumnIndex("project_id")));
                projectdetails.setProject_name(cursor.getString(cursor.getColumnIndex("project_name")));
                projectdetails.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                projectdetails.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                projectdetails.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                projectdetails.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                projectdetails.setActivation(cursor.getString(cursor.getColumnIndex("activation")));
                projectdetails.setAdmin_id(cursor.getInt(cursor.getColumnIndex("admin_id")));
                projectdetails.setEmployee_id(cursor.getInt(cursor.getColumnIndex("employee_id")));

                result.add(projectdetails);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }
    public void deleteToPorjects() {
        SQLiteDatabase db = getReadableDatabase();
       // String query = String.format("DELETE FROM worker_master WHERE id = '%s'",workerId);
        String query = String.format("DELETE FROM project_master");
        db.execSQL(query);
      //  db.delete("project_master",null,null);
    }


    //Table Employee Details
    public void addToEmployee(EmployeeDetails employeeDetails){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO employee_master(" +
                        "employee_id," +
                        "name," +
                        "email," +
                        "adharcard_id," +
                        "gender," +
                        "dob," +
                        "permanent_address_id," +
                        "current_address_id," +
                        "contact1," +
                        "contact2," +
                        "salary," +
                        "password," +
                        "created_at," +
                        "updated_at" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                employeeDetails.getId(),
                employeeDetails.getName(),
                employeeDetails.getEmail(),
                employeeDetails.getAdharcard_id(),
                employeeDetails.getGender(),
                employeeDetails.getDob(),
                employeeDetails.getPermanent_address_id(),
                employeeDetails.getCurrent_address_id(),
                employeeDetails.getContact1(),
                employeeDetails.getContact2(),
                employeeDetails.getSalary(),
                employeeDetails.getPassword(),
                employeeDetails.getCreated_at(),
                employeeDetails.getUpdated_at()
                );

        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully");
        Log.e(TAG, employeeDetails.toString());
    }
    public List<EmployeeDetails> getAllEmployee(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "employee_id",
                        "name" ,
                        "email" ,
                        "adharcard_id" ,
                        "gender" ,
                        "dob",
                        "permanent_address_id",
                        "current_address_id",
                        "contact1",
                        "contact2",
                        "salary",
                        "password",
                        "created_at" ,
                        "updated_at"
        };
        String sqlTable = "employee_master";

        String selectQuery = "SELECT  * FROM  employee_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<EmployeeDetails> result = new ArrayList<EmployeeDetails>();
        if(cursor.moveToFirst())
        {
            do {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setId(cursor.getInt(cursor.getColumnIndex("employee_id")));
                employeeDetails.setName(cursor.getString(cursor.getColumnIndex("name")));
                employeeDetails.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                employeeDetails.setAdharcard_id(cursor.getString(cursor.getColumnIndex("adharcard_id")));
                employeeDetails.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                employeeDetails.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                employeeDetails.setPermanent_address_id(cursor.getString(cursor.getColumnIndex("permanent_address_id")));
                employeeDetails.setCurrent_address_id(cursor.getString(cursor.getColumnIndex("current_address_id")));
                employeeDetails.setContact1(cursor.getString(cursor.getColumnIndex("contact1")));
                employeeDetails.setContact2(cursor.getString(cursor.getColumnIndex("contact2")));
                employeeDetails.setSalary(cursor.getString(cursor.getColumnIndex("salary")));
                employeeDetails.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                employeeDetails.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                employeeDetails.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));


                result.add(employeeDetails);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }
    public void deleteToEmployee() {
        SQLiteDatabase db = getReadableDatabase();
        // String query = String.format("DELETE FROM worker_master WHERE id = '%s'",workerId);
        String query = String.format("DELETE FROM employee_master");
        db.execSQL(query);
        //  db.delete("project_master",null,null);
    }


}
