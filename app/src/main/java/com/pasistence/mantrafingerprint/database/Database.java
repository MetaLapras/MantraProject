package com.pasistence.mantrafingerprint.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Contactdetails;
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
                        "image_url," +
                        "permanent_address," +
                        "current_address," +
                        "bank_name," +
                        "holder_name," +
                        "ifsc_code," +
                        "account_number," +
                        "city," +
                        "pincode" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
        workerModel.getId(),
        workerModel.getName(),
        workerModel.getAdharcard_id(),
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
    //get all worker details
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
                workerModel.setAdharcard_id(cursor.getString(cursor.getColumnIndex("adharcard_id")));
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
                workerModel.setPermanent_address1(cursor.getString(cursor.getColumnIndex("permanent_address")));
                workerModel.setCurrent_address1(cursor.getString(cursor.getColumnIndex("current_address")));
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
        cursor.close();
        return result;
    }
    public List<WorkerModel> getAllWorkers(String workerID){
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
        Cursor cursor = qb.query(db,sqlSelect,"worker_id = ?",new String[] {workerID},null,null,null);

        final List<WorkerModel> result = new ArrayList<WorkerModel>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerModel workerModel = new WorkerModel();
                workerModel.setId(cursor.getString(cursor.getColumnIndex("id")));
                workerModel.setWorkerId(cursor.getString(cursor.getColumnIndex("worker_id")));
                workerModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                workerModel.setAdharcard_id(cursor.getString(cursor.getColumnIndex("adharcard_id")));
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
                workerModel.setPermanent_address1(cursor.getString(cursor.getColumnIndex("permanent_address")));
                workerModel.setCurrent_address1(cursor.getString(cursor.getColumnIndex("current_address")));
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
   // function get all worker's name
    public List <String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {"name"};
        String sqlTable = "worker_master";

        String selectQuery = "SELECT  * FROM  worker_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<String> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("name")));

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;

    }

    //Function get worker by name
    public List<WorkerModel> getWorkerName(String name) {
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
        //Cursor cursor = qb.query(db,sqlSelect,"name = ?",new String[]{name},null,null,null);
        Cursor cursor = qb.query(db,sqlSelect,"name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        final List<WorkerModel> result = new ArrayList<WorkerModel>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerModel workerModel = new WorkerModel();
                workerModel.setId(cursor.getString(cursor.getColumnIndex("id")));
                workerModel.setWorkerId(cursor.getString(cursor.getColumnIndex("worker_id")));
                workerModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                workerModel.setAdharcard_id(cursor.getString(cursor.getColumnIndex("adharcard_id")));
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
                workerModel.setPermanent_address1(cursor.getString(cursor.getColumnIndex("permanent_address")));
                workerModel.setCurrent_address1(cursor.getString(cursor.getColumnIndex("current_address")));
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
                workerModel.getAdharcard_id(),
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
    // update worker_master by worker id
    public void updateToWorkersMaster(WorkerModel workerModel) {
        String sqlTable = "worker_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",workerModel.getName());
        values.put("adharcard_id",workerModel.getAdharcard_id());
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
        values.put("permanent_address",workerModel.getPermanent_address1());
        values.put("current_address",workerModel.getCurrent_address1());
        values.put("bank_name",workerModel.getBank_name());
        values.put("holder_name",workerModel.getHolder_name());
        values.put("ifsc_code",workerModel.getIfsc_code());
        values.put("account_number",workerModel.getAccount_number());
        values.put("city",workerModel.getCity());
        values.put("pincode",workerModel.getPincode());

                /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{workerModel.getWorkerId()});
    }
    //delete worker by worker id
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


    //Table Worker Address Details

  /*  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`address_id`	INTEGER,
            `contact1`	INTEGER,
            `contact2`	INTEGER,
            `address_line_1`	TEXT,
            `address_line_2`	TEXT,
            `city`	TEXT,
            `pincode`	INTEGER,
            `state`	TEXT,
            `country`	TEXT,
            `created_at`	TEXT,
            `updated_at`	TEXT,
            `worker_id`	TEXT,
            `type`	TEXT,
            `employee_id`	TEXT*/

    // insert into worker Address details
    public void addToAddressDetails(Contactdetails contactdetails) {
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO address_master(" +
                        "address_id," +
                        "contact1," +
                        "contact2," +
                        "address_line_1,"+
                        "address_line_2," +
                        "city," +
                        "pincode," +
                        "state," +
                        "country," +
                        "worker_id," +
                        "type" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                contactdetails.getId(),
                contactdetails.getContact1(),
                contactdetails.getContact2(),
                contactdetails.getAddress_line_1(),
                contactdetails.getAddress_line_2(),
                contactdetails.getCity(),
                contactdetails.getPincode(),
                contactdetails.getState(),
                contactdetails.getCountry(),
                contactdetails.getWorker_id(),
                contactdetails.getType()
        );


        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully into Address Master");
        Log.e(TAG, contactdetails.toString());
    }
    // Delete into worker Address details
    public void deleteToAddressDetails(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM address_master WHERE worker_id = '%s'",workerId);
        db.execSQL(query);
    }
    //Update into worker Address details
    public void updateToAddressMaster(Contactdetails contactdetails) {
        String sqlTable = "address_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("address_id",contactdetails.getId());
        values.put("contact1",contactdetails.getContact1());
        values.put("contact2",contactdetails.getContact2());
        values.put("address_line_1",contactdetails.getAddress_line_1());
        values.put("address_line_2",contactdetails.getAddress_line_2());
        values.put("city",contactdetails.getCity());
        values.put("pincode",contactdetails.getPincode());
        values.put("state",contactdetails.getState());
        values.put("country",contactdetails.getCountry());
        values.put("worker_id",contactdetails.getWorker_id());
        values.put("type",contactdetails.getType());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ? AND address_id =?",
                new String[]{String.valueOf(contactdetails.getWorker_id()),String.valueOf(contactdetails.getId())});
    }
    public List<Contactdetails> getAddressMasterDetails(String workerId,String permanent_address_id) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                        "address_id",
                        "contact1" ,
                        "contact2" ,
                        "address_line_1",
                        "address_line_2" ,
                        "city",
                        "pincode" ,
                        "state" ,
                        "country" ,
                        "worker_id",
                        "type"
        };
        String sqlTable = "address_master";

        String selectQuery = "SELECT  * FROM  address_master ";

        qb.setTables(sqlTable);
        //Cursor cursor = qb.query(db,sqlSelect,"name = ?",new String[]{name},null,null,null);
        Cursor cursor = qb.query(db,sqlSelect,"address_id = ? AND worker_id = ?",new String[]{permanent_address_id,workerId},null,null,null);

        final List<Contactdetails> result = new ArrayList<Contactdetails>();
        if(cursor.moveToFirst())
        {
            do {
                Contactdetails details = new Contactdetails();
                details.setId(cursor.getInt(cursor.getColumnIndex("address_id")));
                details.setContact1(cursor.getString(cursor.getColumnIndex("contact1")));
                details.setContact2(cursor.getString(cursor.getColumnIndex("contact2")));
                details.setAddress_line_1(cursor.getString(cursor.getColumnIndex("address_line_1")));
                details.setAddress_line_2(cursor.getString(cursor.getColumnIndex("address_line_2")));
                details.setCity(cursor.getString(cursor.getColumnIndex("city")));
                details.setPincode(cursor.getString(cursor.getColumnIndex("pincode")));
                details.setState(cursor.getString(cursor.getColumnIndex("state")));
                details.setCountry(cursor.getString(cursor.getColumnIndex("country")));
                details.setWorker_id(cursor.getInt(cursor.getColumnIndex("worker_id")));
                details.setType(cursor.getString(cursor.getColumnIndex("type")));

                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;

    }

    // insert into worker Bank details

    /*`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`account_holder_name`	TEXT,
	`ifsc_code`	NUMERIC,
	`account_no`	TEXT,
	`bank_name`	TEXT,
	`worker_id`	INTEGER,
	`activation`	TEXT,
	`employee_id`	INTEGER*/
    public void addToBankDetails(BankAccount bankAccount) {
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO bank_master(" +
                        "bank_id," +
                        "account_holder_name," +
                        "ifsc_code," +
                        "account_no,"+
                        "bank_name," +
                        "worker_id," +
                        "activation" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s');",
                bankAccount.getId(),
                bankAccount.getAccount_holder_name(),
                bankAccount.getIfsc_code(),
                bankAccount.getAccount_no(),
                bankAccount.getBank_name(),
                bankAccount.getWorker_id(),
                bankAccount.getActivation()
        );


        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully into Bank Master");
        Log.e(TAG, bankAccount.toString());
    }
    // Delete into worker Address details
    public void deleteToBankDetails(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM bank_master WHERE worker_id = '%s'",workerId);
        db.execSQL(query);
    }
    //Update into worker Address details
    public void updateToBankMaster(BankAccount bankAccount) {
        String sqlTable = "bank_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("bank_id",bankAccount.getId());
        values.put("account_holder_name",bankAccount.getAccount_holder_name());
        values.put("ifsc_code",bankAccount.getIfsc_code());
        values.put("account_no",bankAccount.getAccount_no());
        values.put("bank_name",bankAccount.getBank_name());
        values.put("worker_id",bankAccount.getWorker_id());
        values.put("activation",bankAccount.getActivation());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{String.valueOf(bankAccount.getWorker_id())});
    }
    //Get Bank Details
    public List<BankAccount> getBankMasterDetails(String workerId,String bankId) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                        "bank_id" ,
                        "account_holder_name" ,
                        "ifsc_code" ,
                        "account_no",
                        "bank_name" ,
                        "worker_id" ,
                        "activation"
        };
        String sqlTable = "bank_master";

        String selectQuery = "SELECT  * FROM  bank_master ";

        qb.setTables(sqlTable);
        //Cursor cursor = qb.query(db,sqlSelect,"name = ?",new String[]{name},null,null,null);
        Cursor cursor = qb.query(db,sqlSelect,"bank_id = ? AND worker_id = ?",new String[]{bankId,workerId},null,null,null);

        final List<BankAccount> result = new ArrayList<BankAccount>();
        if(cursor.moveToFirst())
        {
            do {
                BankAccount details = new BankAccount();
                details.setId(cursor.getInt(cursor.getColumnIndex("bank_id")));
                details.setBank_name(cursor.getString(cursor.getColumnIndex("bank_name")));
                details.setAccount_holder_name(cursor.getString(cursor.getColumnIndex("account_holder_name")));
                details.setAccount_no(cursor.getString(cursor.getColumnIndex("account_no")));
                details.setWorker_id(cursor.getInt(cursor.getColumnIndex("worker_id")));
                details.setActivation(cursor.getString(cursor.getColumnIndex("activation")));
                details.setIfsc_code(cursor.getString(cursor.getColumnIndex("ifsc_code")));


                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;

    }
    public List<BankAccount> getAllBankDetails(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "bank_id" ,
                "account_holder_name" ,
                "ifsc_code" ,
                "account_no",
                "bank_name" ,
                "worker_id" ,
                "activation"
        };
        String sqlTable = "bank_master";

        String selectQuery = "SELECT  * FROM  bank_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<BankAccount> result = new ArrayList<BankAccount>();
        if(cursor.moveToFirst())
        {
            do {
                BankAccount details = new BankAccount();
                details.setId(cursor.getInt(cursor.getColumnIndex("bank_id")));
                details.setBank_name(cursor.getString(cursor.getColumnIndex("bank_name")));
                details.setAccount_holder_name(cursor.getString(cursor.getColumnIndex("account_holder_name")));
                details.setAccount_no(cursor.getString(cursor.getColumnIndex("account_no")));
                details.setWorker_id(cursor.getInt(cursor.getColumnIndex("worker_id")));
                details.setActivation(cursor.getString(cursor.getColumnIndex("activation")));
                details.setIfsc_code(cursor.getString(cursor.getColumnIndex("ifsc_code")));


                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }


    //Temp Bank Account Details
    public void addToTempBankDetails(BankAccount bankAccount) {
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO Temp_bank_master(" +
                        "bank_id," +
                        "account_holder_name," +
                        "ifsc_code," +
                        "account_no,"+
                        "bank_name," +
                        "worker_id," +
                        "activation" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s');",
                bankAccount.getId(),
                bankAccount.getAccount_holder_name(),
                bankAccount.getIfsc_code(),
                bankAccount.getAccount_no(),
                bankAccount.getBank_name(),
                bankAccount.getWorker_id(),
                bankAccount.getActivation()
        );


        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully into Bank Temp Master");
        Log.e(TAG, bankAccount.toString());
    }
    // Delete into worker Address details
    public void deleteToTempBankDetails(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_bank_master WHERE worker_id = '%s'",workerId);
        db.execSQL(query);
    }
    public void deleteToTempBankDetailsID(String Id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_bank_master WHERE id = '%s'",Id);
        db.execSQL(query);
    }
    //Update into worker Address details
    public void updateToTempBankMaster(BankAccount bankAccount) {
        String sqlTable = "Temp_bank_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("bank_id",bankAccount.getId());
        values.put("account_holder_name",bankAccount.getAccount_holder_name());
        values.put("ifsc_code",bankAccount.getIfsc_code());
        values.put("account_no",bankAccount.getAccount_no());
        values.put("bank_name",bankAccount.getBank_name());
        values.put("worker_id",bankAccount.getWorker_id());
        values.put("activation",bankAccount.getActivation());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{String.valueOf(bankAccount.getWorker_id())});
    }
    public List<BankAccount> getAllTempBankDetails(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "bank_id" ,
                "account_holder_name" ,
                "ifsc_code" ,
                "account_no",
                "bank_name" ,
                "worker_id" ,
                "activation"
        };
        String sqlTable = "Temp_bank_master";

        String selectQuery = "SELECT  * FROM  Temp_bank_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<BankAccount> result = new ArrayList<BankAccount>();
        if(cursor.moveToFirst())
        {
            do {
                BankAccount details = new BankAccount();
                details.setId(cursor.getInt(cursor.getColumnIndex("bank_id")));
                details.setBank_name(cursor.getString(cursor.getColumnIndex("bank_name")));
                details.setAccount_holder_name(cursor.getString(cursor.getColumnIndex("account_holder_name")));
                details.setAccount_no(cursor.getString(cursor.getColumnIndex("account_no")));
                details.setWorker_id(cursor.getInt(cursor.getColumnIndex("worker_id")));
                details.setActivation(cursor.getString(cursor.getColumnIndex("activation")));
                details.setIfsc_code(cursor.getString(cursor.getColumnIndex("ifsc_code")));


                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }


    //Insert into temp_address_master
    // insert into worker Temp Address details
    public void addToTempAddressDetails(Contactdetails contactdetails) {
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO Temp_address_master(" +
                        "address_id," +
                        "contact1," +
                        "contact2," +
                        "address_line_1,"+
                        "address_line_2," +
                        "city," +
                        "pincode," +
                        "state," +
                        "country," +
                        "worker_id," +
                        "type" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                contactdetails.getId(),
                contactdetails.getContact1(),
                contactdetails.getContact2(),
                contactdetails.getAddress_line_1(),
                contactdetails.getAddress_line_2(),
                contactdetails.getCity(),
                contactdetails.getPincode(),
                contactdetails.getState(),
                contactdetails.getCountry(),
                contactdetails.getWorker_id(),
                contactdetails.getType()
        );


        db.execSQL(query);

        Log.e(TAG, "Database inserted Successfully into Address Master");
        Log.e(TAG, contactdetails.toString());
    }
    // Delete into worker Temp Address details
    public void deleteToTempAddressDetails(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_address_master WHERE worker_id = '%s'",workerId);
        db.execSQL(query);
    }
    public void deleteToTempAddressDetailsID(String Id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_address_master WHERE id = '%s'",Id);
        db.execSQL(query);
    }
    //Update into worker Temp Address details
    public void updateToTempAddressMaster(Contactdetails contactdetails) {
        String sqlTable = "Temp_address_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("address_id",contactdetails.getId());
        values.put("contact1",contactdetails.getContact1());
        values.put("contact2",contactdetails.getContact2());
        values.put("address_line_1",contactdetails.getAddress_line_1());
        values.put("address_line_2",contactdetails.getAddress_line_2());
        values.put("city",contactdetails.getCity());
        values.put("pincode",contactdetails.getPincode());
        values.put("state",contactdetails.getState());
        values.put("country",contactdetails.getCountry());
        values.put("worker_id",contactdetails.getWorker_id());
        values.put("type",contactdetails.getType());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{String.valueOf(contactdetails.getWorker_id())});
    }
    public List<Contactdetails> getAllTempAddress(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                        "address_id" ,
                        "contact1" ,
                        "contact2" ,
                        "address_line_1",
                        "address_line_2" ,
                        "city" ,
                        "pincode",
                        "state" ,
                        "country" ,
                        "worker_id",
                        "type"
        };
        String sqlTable = "Temp_address_master";

        String selectQuery = "SELECT  * FROM  Temp_address_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Contactdetails> result = new ArrayList<Contactdetails>();
        if(cursor.moveToFirst())
        {
            do {
                Contactdetails contactdetails = new Contactdetails();

                contactdetails.setId(cursor.getInt(cursor.getColumnIndex("address_id")));
                contactdetails.setContact1(cursor.getString(cursor.getColumnIndex("contact1")));
                contactdetails.setContact2(cursor.getString(cursor.getColumnIndex("contact2")));
                contactdetails.setAddress_line_1(cursor.getString(cursor.getColumnIndex("address_line_1")));
                contactdetails.setAddress_line_2(cursor.getString(cursor.getColumnIndex("address_line_2")));
                contactdetails.setCity(cursor.getString(cursor.getColumnIndex("city")));
                contactdetails.setPincode(cursor.getString(cursor.getColumnIndex("pincode")));
                contactdetails.setState(cursor.getString(cursor.getColumnIndex("state")));
                contactdetails.setCountry(cursor.getString(cursor.getColumnIndex("country")));
                contactdetails.setWorker_id(cursor.getInt(cursor.getColumnIndex("worker_id")));
                contactdetails.setType(cursor.getString(cursor.getColumnIndex("type")));

                result.add(contactdetails);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }

    //add to worker temp
    public void addToTempWorkers(WorkerModel workerModel){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO Temp_worker_master(" +
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
                workerModel.getAdharcard_id(),
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
    // update worker_master by worker id
    public void updateToTempWorkersMaster(WorkerModel workerModel) {
        String sqlTable = "Temp_worker_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",workerModel.getName());
        values.put("adharcard_id",workerModel.getAdharcard_id());
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
        values.put("permanent_address",workerModel.getPermanent_address1());
        values.put("current_address",workerModel.getCurrent_address1());
        values.put("bank_name",workerModel.getBank_name());
        values.put("holder_name",workerModel.getHolder_name());
        values.put("ifsc_code",workerModel.getIfsc_code());
        values.put("account_number",workerModel.getAccount_number());
        values.put("city",workerModel.getCity());
        values.put("pincode",workerModel.getPincode());

        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{workerModel.getWorkerId()});
    }
    //delete worker by worker id
    public void deleteToTempWorkers(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_worker_master WHERE id = '%s'",workerId);
        db.execSQL(query);
    }
    public void deleteToTempWorkersID(String Id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_worker_master WHERE id = '%s'",Id);
        db.execSQL(query);
    }
    //Get all Temp Workers
    public List<WorkerModel> getAllTempWorkers(){
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
        String sqlTable = "Temp_worker_master";

        String selectQuery = "SELECT  * FROM  Temp_worker_master ";

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
                workerModel.setAdharcard_id(cursor.getString(cursor.getColumnIndex("adharcard_id")));
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
                workerModel.setPermanent_address1(cursor.getString(cursor.getColumnIndex("permanent_address")));
                workerModel.setCurrent_address1(cursor.getString(cursor.getColumnIndex("current_address")));
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




    //Temp Attendance Master Table
    /*id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`worker_id`	TEXT,
	`worker_assignment_id`	TEXT,
	`project_id`	TEXT,
	`check_in_date`	TEXT,
	`check_in_time`	TEXT,
	`overtime`	TEXT,
	`fulltime`	TEXT,
	`halfday`	TEXT,
	`check_out_time`	TEXT,
	`wages`	TEXT,
	`created_at`	TEXT,
	`updated_at`	TEXT*/
    public void addToTempAttendance(Attendance attendance){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO Temp_attendance_master(" +
                        "worker_id," +
                        "worker_assignment_id," +
                        "project_id," +
                        "check_in_date," +
                        "check_in_time," +
                        "overtime," +
                        "fulltime," +
                        "halfday," +
                        "check_out_time," +
                        "wages," +
                        "created_at," +
                        "updated_at" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                attendance.getWorkerId(),
                attendance.getWorkerAssignmentId(),
                attendance.getProjectId(),
                attendance.getCheckInDate(),
                attendance.getCheckInTime(),
                attendance.getOverTime(),
                attendance.getFullTime(),
                attendance.getHalfday(),
                attendance.getCheckOutTime(),
                attendance.getWages(),
                attendance.getCreated_at(),
                attendance.getUpdated_at()
                );


        db.execSQL(query);

        Log.e(TAG, "Database Attendance Inserted Successfully");
        Log.e(TAG, attendance.toString());
    }
    // update Attendance Master by worker id
    public void updateToTempAttendance(Attendance attendance) {
        String sqlTable = "Temp_attendance_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("worker_id",attendance.getWorkerId());
        values.put("worker_assignment_id",attendance.getWorkerAssignmentId());
        values.put("project_id",attendance.getProjectId());
        values.put("check_in_date",attendance.getCheckInDate());
        values.put("check_in_time",attendance.getCheckInTime());
        values.put("overtime",attendance.getOverTime());
        values.put("fulltime",attendance.getFullTime());
        values.put("halfday",attendance.getHalfday());
        values.put("check_out_time",attendance.getCheckOutTime());
        values.put("wages",attendance.getWages());
        values.put("created_at",attendance.getCreated_at());
        values.put("updated_at",attendance.getUpdated_at());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{attendance.getWorkerId()});
        Log.e(TAG, "updateToTempAttendance:"+attendance.toString() );
    }
    //delete Attendance Master
    public void deleteToTempAttendance(String workerId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Temp_attendance_master");
        db.execSQL(query);
    }
    public List<Attendance> getallTempAttendace() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                        "worker_id" ,
                        "worker_assignment_id" ,
                        "project_id" ,
                        "check_in_date" ,
                        "check_in_time" ,
                        "overtime" ,
                        "fulltime",
                        "halfday" ,
                        "check_out_time" ,
                        "wages" ,
                        "created_at",
                        "updated_at"
        };
        String sqlTable = "Temp_attendance_master";

        String selectQuery = "SELECT  * FROM  Temp_attendance_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Attendance> result = new ArrayList<Attendance>();
        if(cursor.moveToFirst())
        {
            do {
                Attendance details = new Attendance();
                details.setWorkerId(cursor.getString(cursor.getColumnIndex("worker_id")));
                details.setWorkerAssignmentId(cursor.getString(cursor.getColumnIndex("worker_assignment_id")));
                details.setProjectId(cursor.getString(cursor.getColumnIndex("project_id")));
                details.setCheckInDate(cursor.getString(cursor.getColumnIndex("check_in_date")));
                details.setCheckInTime(cursor.getString(cursor.getColumnIndex("check_in_time")));
                details.setCheckOutTime(cursor.getString(cursor.getColumnIndex("overtime")));
                details.setFullTime(cursor.getString(cursor.getColumnIndex("fulltime")));
                details.setHalfday(cursor.getString(cursor.getColumnIndex("halfday")));
                details.setCheckOutTime(cursor.getString(cursor.getColumnIndex("check_out_time")));
                details.setWages(cursor.getString(cursor.getColumnIndex("wages")));

                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }
    public List<Attendance> getallTempAttendace(String worker_id) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {
                "worker_id" ,
                "worker_assignment_id" ,
                "project_id" ,
                "check_in_date" ,
                "check_in_time" ,
                "overtime" ,
                "fulltime",
                "halfday" ,
                "check_out_time" ,
                "wages" ,
                "created_at",
                "updated_at"
        };
        String sqlTable = "Temp_attendance_master";

        String selectQuery = "SELECT  * FROM  Temp_attendance_master ";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,"worker_id=?",new String[]{worker_id},null,null,null);

        final List<Attendance> result = new ArrayList<Attendance>();
        if(cursor.moveToFirst())
        {
            do {
                Attendance details = new Attendance();
                details.setWorkerId(cursor.getString(cursor.getColumnIndex("worker_id")));
                details.setWorkerAssignmentId(cursor.getString(cursor.getColumnIndex("worker_assignment_id")));
                details.setProjectId(cursor.getString(cursor.getColumnIndex("project_id")));
                details.setCheckInDate(cursor.getString(cursor.getColumnIndex("check_in_date")));
                details.setCheckInTime(cursor.getString(cursor.getColumnIndex("check_in_time")));
                details.setCheckOutTime(cursor.getString(cursor.getColumnIndex("overtime")));
                details.setFullTime(cursor.getString(cursor.getColumnIndex("fulltime")));
                details.setHalfday(cursor.getString(cursor.getColumnIndex("halfday")));
                details.setCheckOutTime(cursor.getString(cursor.getColumnIndex("check_out_time")));
                details.setWages(cursor.getString(cursor.getColumnIndex("wages")));

                result.add(details);

                Log.e(TAG, result.toString() );

            }while (cursor.moveToNext());
        }
        return result;
    }


    //Attendance Table
    public void addToAttendance(Attendance attendance){
        // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT OR REPLACE INTO attendance_master(" +
                        "worker_id," +
                        "worker_assignment_id," +
                        "project_id," +
                        "check_in_date," +
                        "check_in_time," +
                        "overtime," +
                        "fulltime," +
                        "halfday," +
                        "check_out_time," +
                        "wages," +
                        "created_at," +
                        "updated_at" +
                        ")" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                attendance.getId(),
                attendance.getWorkerAssignmentId(),
                attendance.getProjectId(),
                attendance.getCheckInDate(),
                attendance.getCheckInTime(),
                attendance.getOverTime(),
                attendance.getFullTime(),
                attendance.getHalfday(),
                attendance.getCheckOutTime(),
                attendance.getWages(),
                attendance.getCreated_at(),
                attendance.getUpdated_at()
        );


        db.execSQL(query);

        Log.e(TAG, "Database Attendance Inserted Successfully");
        Log.e(TAG, attendance.toString());
    }
    // update Attendance Master by worker id
    public void updateToAttendance(Attendance attendance) {
        String sqlTable = "attendance_master";

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("worker_id",attendance.getWorkerId());
        values.put("worker_assignment_id",attendance.getWorkerAssignmentId());
        values.put("project_id",attendance.getProjectId());
        values.put("check_in_date",attendance.getCheckInDate());
        values.put("check_in_time",attendance.getCheckInTime());
        values.put("overtime",attendance.getOverTime());
        values.put("fulltime",attendance.getFullTime());
        values.put("halfday",attendance.getHalfday());
        values.put("check_out_time",attendance.getCheckOutTime());
        values.put("wages",attendance.getWages());
        values.put("created_at",attendance.getCreated_at());
        values.put("updated_at",attendance.getUpdated_at());


        /*workerModel.getWorkerId()/*Add Later on when Webservices*/

        db.update(sqlTable, values, "worker_id = ?",
                new String[]{attendance.getWorkerId()});
    }
    //delete worker by worker id
    public void deleteToAttendance() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM attendance_master");
        db.execSQL(query);
    }


}
