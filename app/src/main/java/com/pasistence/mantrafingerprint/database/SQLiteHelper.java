package com.pasistence.mantrafingerprint.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    //constructor
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory)
    {
       super(context, name, factory, 1);  //1-version
    }

    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insert data
    public void insertData(String name, String lastname, String aadharnumber, String contactnum,String alternatenum, String age, String doj, String marritalstatus, String gender,String phone, byte[] image)
    {
        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  //where "RECORD" is table name in database we will create in WorkerRegister Activity

        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();
         statement.bindString(1,name);
        statement.bindString(2,lastname);
        statement.bindString(3,aadharnumber);
        statement.bindString(4,contactnum);
        statement.bindString(5,alternatenum);
        statement.bindString(6,age);
        statement.bindString(7,doj);
        statement.bindString(8,marritalstatus);
        statement.bindString(9,gender);
        statement.bindString(10,phone);
        statement.bindBlob(11,image);

        statement.executeInsert();

    }

    //update  data
    public void updateData(String name, String lastname, String aadharnumber, String contactnum, String alternatenum, String age, String doj, String marritalstatus, String gender, String phone, byte[] image, int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        //query to update record

        String sql = "UPDATE RECORD SET name=?, lastname=?, aadharnumber=?, contactnum=?, alternatenum=?, age=?, doj=?, marritalstatus=?, gender=?, phone=?, image=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,name);
        statement.bindString(2,lastname);
        statement.bindString(3,aadharnumber);
        statement.bindString(4,contactnum);
        statement.bindString(5,alternatenum);
        statement.bindString(6,age);
        statement.bindString(7,doj);
        statement.bindString(8,marritalstatus);
        statement.bindString(9,gender);
        statement.bindString(10,phone);
        statement.bindBlob(11,image);

        statement.bindDouble(11,(double)id);

        statement.execute();
        database.close();
    }

    //deleteData
    public void deleteData(int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData (String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
