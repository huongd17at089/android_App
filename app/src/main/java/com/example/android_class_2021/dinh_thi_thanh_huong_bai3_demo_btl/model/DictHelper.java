package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class DictHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "oder.db";
    private static final int DATABASE_VERSION = 1;
    private static DictHelper instance;
    private SQLiteDatabase db;

    public DictHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DictHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DictHelper(context.getApplicationContext());
        }
        return instance;
    }

    final String SQL_CREATE_DICT_TABLE = "CREATE TABLE " +
            Contract.DictItemsTable.TABLE_NAME + "( " +
            Contract.DictItemsTable._ID + " INTEGER PRIMARY KEY, " +
            Contract.DictItemsTable.COLUMN_WORD + " TEXT, " +
            Contract.DictItemsTable.COLUMN_CONTENT + " TEXT " +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(SQL_CREATE_DICT_TABLE);
        fillDictTable();
    }



    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void fillDictTable() {
    }

    public long addWord(DictItem word) {
        SQLiteDatabase db = getWritableDatabase();
        return insertWord(word);
    }

    private long insertWord(DictItem word) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.DictItemsTable.COLUMN_WORD, word.getWord());
        cv.put(Contract.DictItemsTable.COLUMN_CONTENT, word.getContent());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(Contract.DictItemsTable.TABLE_NAME, null, cv);

    }

    public ArrayList<DictItem> getAllWord(){
        ArrayList<DictItem> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.DictItemsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                DictItem word = new DictItem();
                word.setId(c.getInt(c.getColumnIndex(Contract.DictItemsTable._ID)));
                word.setWord(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_WORD)));
                word.setContent(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_CONTENT)));
                ls.add(word);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }

    public ArrayList<DictItem> getAllWordByKey(String key){
        ArrayList<DictItem> ls = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.TopicTable.TABLE_NAME +
                " WHERE " + Contract.DictItemsTable.COLUMN_WORD + " LIKE '%" + key + "%'"  , null);
        if (c.moveToFirst()) {
            do {
                DictItem word = new DictItem();
                word.setId(c.getInt(c.getColumnIndex(Contract.DictItemsTable._ID)));
                word.setWord(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_WORD)));
                word.setContent(c.getString(c.getColumnIndex(Contract.DictItemsTable.COLUMN_CONTENT)));
                ls.add(word);
            } while (c.moveToNext());
        }
        c.close();
        return ls;
    }
}
