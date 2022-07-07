package com.example.myapplication;

//package com.demo.easylearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="registr.db";
    public static final String TABLE_NAME ="registerCl";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="Email";

    public static final String TABLENAME ="registerDr";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE registerCl (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT,Email TEXT)" );
        sqLiteDatabase.execSQL("CREATE TABLE registerDr (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT,Email TEXT)");
    }

        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLENAME);
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password,String function,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("Email",Email);
        if(function == "Doctor"){
            long res = db.insert("registerDr",null,contentValues);
            db.close();
            return  res;
        }
        else{

            long res = db.insert("registerCl",null,contentValues);
            db.close();
            return  res;
        }
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("registerCl",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        if (count == 0) {
            cursor = db.query("registerDr", columns, selection, selectionArgs, null, null, null);
            count = cursor.getCount();
        }
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }
    public int funcUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("registerCl",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count >0) {
            return 1;
        }
        else
            return  0;

    }
}
