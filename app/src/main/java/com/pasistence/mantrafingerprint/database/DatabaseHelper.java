package com.pasistence.mantrafingerprint.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.pasistence.mantrafingerprint.Models.WorkerList;
import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "database-->";
    private static final String DATABASE_NAME = "database.db";
    private static final int DB_VER=1;

    //Member Table
    public static final String MEMBERS_TABLE = "member_table_master";
    public static final String MEMBER_COl_1 = "id";
    public static final String MEMBER_COL_2 = "memberid";
    public static final String MEMBER_COL_3 = "adminid";
    public static final String MEMBER_COL_4 = "statusid";
    public static final String MEMBER_COL_5 = "firstname";
    public static final String MEMBER_COL_6 = "middlename";
    public static final String MEMBER_COL_7 = "lastname";
    public static final String MEMBER_COL_8 = "empId";
    public static final String MEMBER_COL_9 = "contactno";
    public static final String MEMBER_COL_10 = "alternatno";
    public static final String MEMBER_COL_11 = "dob";
    public static final String MEMBER_COL_12 = "emailid";
    public static final String MEMBER_COL_13 = "permanentaddress";
    public static final String MEMBER_COL_14 = "currentaddress";
    public static final String MEMBER_COL_15 = "fingerprint1";
    public static final String MEMBER_COL_16 = "fingerprint2";
   public static final String MEMBER_COL_17 = "bankname";
   /* public static final String MEMBER_COL_18 = "accountholder";
    public static final String MEMBER_COL_19 = "accountnumber";
    public static final String MEMBER_COL_20 = "ifsccode";
    public static final String MEMBER_COL_21 = "status";
    public static final String MEMBER_COL_22 = "created_at";
    public static final String MEMBER_COL_23 = "updated_at";
    public static final String MEMBER_COL_24 = "photourl";*/

    public static final String CREATE_TABLE_MEMBER =
            "CREATE TABLE " + MEMBERS_TABLE + "("
                    + MEMBER_COl_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MEMBER_COL_2 + " TEXT,"
                    + MEMBER_COL_3 + " TEXT,"
                    + MEMBER_COL_4 + " TEXT,"
                    + MEMBER_COL_5 + " TEXT,"
                    + MEMBER_COL_6 + " TEXT,"
                    + MEMBER_COL_7 + " TEXT,"
                    + MEMBER_COL_8 + " TEXT,"
                    + MEMBER_COL_9 + " TEXT,"
                    + MEMBER_COL_10 + " TEXT,"
                    + MEMBER_COL_11 + " TEXT,"
                    + MEMBER_COL_12 + " TEXT,"
                    + MEMBER_COL_13 + " TEXT,"
                    + MEMBER_COL_14 + " TEXT,"
                    + MEMBER_COL_15 + " TEXT,"
                    + MEMBER_COL_16 + " TEXT,"
                    + MEMBER_COL_17 + " TEXT"
                    /*+ MEMBER_COL_18 + " TEXT,"
                    + MEMBER_COL_19 + " TEXT,"
                    + MEMBER_COL_20 + " TEXT,"
                    + MEMBER_COL_21 + " TEXT,"
                    + MEMBER_COL_22 + " TEXT,"
                    + MEMBER_COL_23 + " TEXT,"
                    + MEMBER_COL_24 + " TEXT"*/
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VER);
        Log.e(TAG, "Database Created" );
    }


    //Function get All Worker List
    public List<WorkerList> getWorkerList() {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "firstname", "empId", "contactno"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<WorkerList> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
           do {
               WorkerList workerList = new WorkerList();
               workerList.setId(cursor.getInt(cursor.getColumnIndex("id")));
               workerList.setFirstName(cursor.getString(cursor.getColumnIndex("firstname")));
               workerList.setEmpId(cursor.getString(cursor.getColumnIndex("empId")));
               workerList.setContactNo(cursor.getString(cursor.getColumnIndex("contactno")));

               result.add(workerList);
           }while (cursor.moveToNext());
        }
        return result;
    }



    //Function get all Workers Name
    public List<String> getNames()
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"firstname"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("firstname")));
            }while (cursor.moveToNext());
        }
        return result;
    }


    //Function get Worker List By Name
    public List<WorkerList> getWorkerListByName(String firstname)
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id","firstname","empId","contactno"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);

        //This will like Query : Select * from WorkerList where Name LIKE %pattern&
        //if you want to get extract name , just change
        //Cursor cursor = qb.query(db,sqlSelect,"Name = ?",new String[]{firstname},null,null,null);
        // return db.rawQuery("select rowid _id,name, age,time from employeedetailnew", null);
         Cursor cursor = qb.query(db,sqlSelect,"firstname LIKE ?",new String[]{"%"+firstname+"%"},null,null,null);
        List<WorkerList> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerList workerList = new WorkerList();
                workerList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                workerList.setFirstName(cursor.getString(cursor.getColumnIndex("firstname")));
                workerList.setEmpId(cursor.getString(cursor.getColumnIndex("empId")));
                workerList.setContactNo(cursor.getString(cursor.getColumnIndex("contactno")));

                result.add(workerList);
            }while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables
        db.execSQL(CREATE_TABLE_MEMBER);
        Log.e(TAG, "Member Table Created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MEMBERS_TABLE);
        Log.e(TAG, "Member Table Deleted" );
    }

    //Members
    public boolean insertMemberData(WorkerModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBER_COL_2, member.getMemberId());
        values.put(MEMBER_COL_3, member.getAdminId());
        values.put(MEMBER_COL_4, member.getStatusId());
        values.put(MEMBER_COL_5, member.getFirstName());
        values.put(MEMBER_COL_6, member.getMiddleName());
        values.put(MEMBER_COL_7, member.getLastName());
        values.put(MEMBER_COL_8, member.getEmpId());
        values.put(MEMBER_COL_9, member.getContactNo());
        values.put(MEMBER_COL_10, member.getAlternatNo());
        values.put(MEMBER_COL_11, member.getDateOfBirth());
        values.put(MEMBER_COL_12, member.getEmailId());
        values.put(MEMBER_COL_13, member.getPermanentAddress());
        values.put(MEMBER_COL_14, member.getCurrentAddress());
        values.put(MEMBER_COL_15, member.getFingerprintOne());
        values.put(MEMBER_COL_16, member.getFingerprintTwo());
        values.put(MEMBER_COL_17, member.getBankName());
        /*values.put(MEMBER_COL_18, member.getAccountHolder());
        values.put(MEMBER_COL_19, member.getAccountNumber());
        values.put(MEMBER_COL_20, member.getIFSCCode());
        values.put(MEMBER_COL_21, member.getStatus());
        values.put(MEMBER_COL_22, member.getCreated_at());
        values.put(MEMBER_COL_23, member.getUpdated_at());
        values.put(MEMBER_COL_24, member.getPhotourl());*/

        long result = db.insert(MEMBERS_TABLE, null, values);

        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

   /* //Function get All Worker List
    public List<WorkerList> getWorkerList() {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "firstname", "empId", "contactno"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<WorkerList> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
           do {
               WorkerList workerList = new WorkerList();
               //workerList.setId(cursor.getInt(cursor.getColumnIndex("id")));
               workerList.setFirstName(cursor.getString(cursor.getColumnIndex("firstname")));
               workerList.setEmpId(cursor.getString(cursor.getColumnIndex("empId")));
               workerList.setContactNo(cursor.getString(cursor.getColumnIndex("contactno")));

               result.add(workerList);
           }while (cursor.moveToNext());
        }
        return result;
    }

    //Function get all Workers Name
    public List<String> getNames()
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"firstname"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("firstname")));
            }while (cursor.moveToNext());
        }
       return result;
    }

    //Function get Worker List By Name
    public List<WorkerList> getWorkerListByName(String firstname)
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id","firstname","empId","contactno"};
        String MEMBERS_TABLE = "member_table_master";

        qb.setTables(MEMBERS_TABLE);

        //This will like Query : Select * from WorkerList where Name LIKE %pattern&
        //if you want to get extract name , just change
        Cursor cursor = qb.query(db,sqlSelect,"Name = ?",new String[]{firstname},null,null,null);
       // return db.rawQuery("select rowid _id,name, age,time from employeedetailnew", null);
       // Cursor cursor = qb.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+firstname+"%"},null,null,null);
        List<WorkerList> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                WorkerList workerList = new WorkerList();
                workerList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                workerList.setFirstName(cursor.getString(cursor.getColumnIndex("firstname")));
                workerList.setEmpId(cursor.getString(cursor.getColumnIndex("empId")));
                workerList.setContactNo(cursor.getString(cursor.getColumnIndex("contactno")));

                result.add(workerList);
            }while (cursor.moveToNext());
        }
        return result;
    }
*/


    /*public List<MemberPOJO> getSerachMember(String str) {
        List<MemberPOJO> members = new ArrayList<MemberPOJO>();
        String selectQuery = "SELECT  * FROM " + MEMBERS_TABLE + " WHERE " + MEMBER_COL_4 + " LIKE '" + str + "%'"
                + " OR " + MEMBER_COL_6 + " LIKE '" + str + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                MemberPOJO member = new MemberPOJO();
                member.setId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
                member.setUserId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
                member.setFirstName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_4)));
                member.setMiddleName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_5)));
                member.setLastName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_6)));
                member.setGender(cursor.getString(cursor.getColumnIndex(MEMBER_COL_7)));
                member.setStatus(cursor.getString(cursor.getColumnIndex(MEMBER_COL_8)));
                member.setMaritalStatus(cursor.getString(cursor.getColumnIndex(MEMBER_COL_9)));
                member.setDob(cursor.getString(cursor.getColumnIndex(MEMBER_COL_10)));
                member.setHomePhone(cursor.getString(cursor.getColumnIndex(MEMBER_COL_11)));
                member.setMobilePhone(cursor.getString(cursor.getColumnIndex(MEMBER_COL_12)));
                member.setWorkPhone(cursor.getString(cursor.getColumnIndex(MEMBER_COL_13)));
                member.setEmail(cursor.getString(cursor.getColumnIndex(MEMBER_COL_14)));
                member.setAddress(cursor.getString(cursor.getColumnIndex(MEMBER_COL_15)));
                member.setNotes(cursor.getString(cursor.getColumnIndex(MEMBER_COL_16)));
                member.setPhotoURL(cursor.getString(cursor.getColumnIndex(MEMBER_COL_17)));
                member.setPhotoLocalPath(cursor.getString(cursor.getColumnIndex(MEMBER_COL_18)));
                member.setFingerPrint(cursor.getString(cursor.getColumnIndex(MEMBER_COL_19)));
                member.setFingerPrint1(cursor.getString(cursor.getColumnIndex(MEMBER_COL_20)));
                member.setRollNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_21)));
                member.setCreateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_22)));
                member.setUpdateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_23)));

                members.add(member);
            } while (cursor.moveToNext());
        }
        db.close();
        return members;
    }*/
    public List<WorkerModel> getAllMembers(WorkerModel workerModel) {
        List<WorkerModel> members = new ArrayList<WorkerModel>();

        String selectQuery = "SELECT  * FROM " + MEMBERS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()) {
            do {

                WorkerModel member = new WorkerModel();
                member.setMemberId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
                member.setAdminId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
                member.setStatusId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_4)));
                member.setFirstName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_5)));
                member.setMiddleName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_6)));
                member.setLastName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_7)));
                member.setEmpId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_8)));
                member.setContactNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_9)));
                member.setAlternatNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_10)));
                member.setDateOfBirth(cursor.getString(cursor.getColumnIndex(MEMBER_COL_11)));
                member.setEmailId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_12)));
                member.setPermanentAddress(cursor.getString(cursor.getColumnIndex(MEMBER_COL_13)));
                member.setCurrentAddress(cursor.getString(cursor.getColumnIndex(MEMBER_COL_14)));
                member.setFingerprintOne(cursor.getString(cursor.getColumnIndex(MEMBER_COL_15)));
                member.setFingerprintTwo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_16)));
                member.setBankName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_17)));
              /* member.setFingerprintTwo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_18)));
                member.setBankName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_19)));
                member.setFingerPrint1(cursor.getString(cursor.getColumnIndex(MEMBER_COL_20)));
                member.setRollNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_21)));
                member.setCreateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_22)));
                member.setUpdateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_23)));
*/
                members.add(member);
            } while (cursor.moveToNext());
        }
        db.close();
        return members;
    }
    public WorkerModel getMemberDetails(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + MEMBERS_TABLE + " WHERE " + MEMBER_COL_2 + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        WorkerModel member = new WorkerModel();
        member.setMemberId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
        member.setAdminId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
        member.setStatusId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_4)));
        member.setFirstName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_5)));
        member.setMiddleName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_6)));
        member.setLastName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_7)));
        member.setEmpId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_8)));
        member.setContactNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_9)));
        member.setAlternatNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_10)));
        member.setDateOfBirth(cursor.getString(cursor.getColumnIndex(MEMBER_COL_11)));
        member.setEmailId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_12)));
        member.setPermanentAddress(cursor.getString(cursor.getColumnIndex(MEMBER_COL_13)));
        member.setCurrentAddress(cursor.getString(cursor.getColumnIndex(MEMBER_COL_14)));
        member.setFingerprintOne(cursor.getString(cursor.getColumnIndex(MEMBER_COL_15)));
        member.setFingerprintTwo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_16)));
        member.setBankName(cursor.getString(cursor.getColumnIndex(MEMBER_COL_17)));
       /* member.setPhotoLocalPath(cursor.getString(cursor.getColumnIndex(MEMBER_COL_18)));
        member.setFingerPrint(cursor.getString(cursor.getColumnIndex(MEMBER_COL_19)));
        member.setFingerPrint1(cursor.getString(cursor.getColumnIndex(MEMBER_COL_20)));
        member.setRollNo(cursor.getString(cursor.getColumnIndex(MEMBER_COL_21)));
        member.setCreateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_22)));
        member.setUpdateAt(cursor.getString(cursor.getColumnIndex(MEMBER_COL_23)));*/

        return member;
    }
    /*public void deleteAllMembers() {
        try {
            String selectQuery = "DELETE FROM " + MEMBERS_TABLE;
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(MEMBERS_TABLE, null, null);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Member Delete-->", ex.toString());
        }
    }
    public void updateMemberData(MemberPOJO member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBER_COL_3, member.getUserId());
        values.put(MEMBER_COL_4, member.getFirstName());
        values.put(MEMBER_COL_5, member.getMiddleName());
        values.put(MEMBER_COL_6, member.getLastName());
        values.put(MEMBER_COL_7, member.getGender());
        values.put(MEMBER_COL_8, member.getStatus());
        values.put(MEMBER_COL_9, member.getMaritalStatus());
        values.put(MEMBER_COL_10, member.getDob());
        values.put(MEMBER_COL_11, member.getHomePhone());
        values.put(MEMBER_COL_12, member.getMobilePhone());
        values.put(MEMBER_COL_13, member.getWorkPhone());
        values.put(MEMBER_COL_14, member.getEmail());
        values.put(MEMBER_COL_15, member.getAddress());
        values.put(MEMBER_COL_16, member.getNotes());
        values.put(MEMBER_COL_17, member.getPhotoURL());
        values.put(MEMBER_COL_18, member.getPhotoLocalPath());
        values.put(MEMBER_COL_19, member.getFingerPrint());
        values.put(MEMBER_COL_20, member.getFingerPrint1());
        values.put(MEMBER_COL_21, member.getRollNo());
        values.put(MEMBER_COL_22, member.getCreateAt());
        values.put(MEMBER_COL_23, member.getUpdateAt());

        db.update(MEMBERS_TABLE, values, MEMBER_COL_2 + " = ? ",
                new String[]{member.getId()});
    }*/
}
