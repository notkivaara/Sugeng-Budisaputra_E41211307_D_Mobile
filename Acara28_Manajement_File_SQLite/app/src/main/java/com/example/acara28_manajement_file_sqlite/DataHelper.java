package com.example.acara28_manajement_file_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
//        TODO auto generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db){
//        TODO Auto-generated method stub
        String sql = "create table biodata(no integer primarykey, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata (no,nama,tgl,jk,alamat) VALUES ('1','Putra','2003-04-18','Laki-laki','Situbondo');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0,int arg1, int arg2){
//        TODO Auto-generated method stub
    }
}
