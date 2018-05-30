package android.zgy.meichang.mySQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class myOrderDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "myTest.db";
    public static final String TABLE_NAME_JIN_MEI = "JinMeiOrders";
    public static final String TABLE_NAME_CHU_MEI = "ChuMeiOrders";
    public static final String TABLE_NAME_ZHI_CHU = "ZhiChuOrders";


    public myOrderDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table Orders(Id integer primary key, CustomName text, OrderPrice integer, Country text);
        //[id] integer PRIMARY KEY AUTOINCREMENT
        String sql = "create table if not exists " + TABLE_NAME_JIN_MEI + " (Id integer primary key, content text, danjia text, date text)";
        sqLiteDatabase.execSQL(sql);
        String sql1 = "create table if not exists " + TABLE_NAME_CHU_MEI + " (Id integer primary key, content text, danjia text, date text)";
        sqLiteDatabase.execSQL(sql1);
        String sql2 = "create table if not exists " + TABLE_NAME_ZHI_CHU + " (Id integer primary key, content text, danjia text, date text)";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_JIN_MEI;
        sqLiteDatabase.execSQL(sql);
        String sql1 = "DROP TABLE IF EXISTS " + TABLE_NAME_CHU_MEI;
        sqLiteDatabase.execSQL(sql1);
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME_ZHI_CHU;
        sqLiteDatabase.execSQL(sql2);
        onCreate(sqLiteDatabase);
    }
}
