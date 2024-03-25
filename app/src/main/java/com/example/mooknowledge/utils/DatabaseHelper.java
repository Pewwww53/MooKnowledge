package com.example.mooknowledge.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="mooknowledge3";

    //FLASCARDS
    private static final String TABLE_NAME="flascards";
    private static final String DATABASE_FRONT="front";
    private static final String DATABASE_FRONT_SIZE="front_size";
    private static final String DATABASE_BACK="back";
    private static final String DATABASE_BACK_SIZE="back_size";

    //QUIZ RECORDS
    private static final String QUIZ_TABLE_NAME="records";
    private static final String QUIZ_DATE="date";
    private static final String QUIZ_SCORE="score";
    private static final String QUIZ_STATE="state";
    private static final String QUIZ_MODE="mode";

    private static final String create = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+DATABASE_FRONT+" TEXT, "+DATABASE_FRONT_SIZE+" TEXT, "+DATABASE_BACK+" TEXT, "+DATABASE_BACK_SIZE+" TEXT)";
    private static final String createQuiz =
            "CREATE TABLE "+QUIZ_TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    QUIZ_DATE+" TEXT," +
                    QUIZ_STATE+" TEXT," +
                    QUIZ_SCORE+" TEXT," +
                    QUIZ_MODE+" TEXT)";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
        db.execSQL(createQuiz);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+QUIZ_TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String front, String frontSize, String back, String backSize){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_FRONT, front);
        values.put(DATABASE_FRONT_SIZE, frontSize);
        values.put(DATABASE_BACK, back);
        values.put(DATABASE_BACK_SIZE, backSize);

        return db.insert(TABLE_NAME, null, values);
    }

    public long insertQuizData(String date, String state, String score, String mode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUIZ_DATE, date);
        values.put(QUIZ_STATE, state);
        values.put(QUIZ_SCORE, score);
        values.put(QUIZ_MODE, mode);

        return db.insert(QUIZ_TABLE_NAME, null, values);
    }

    public void deleteData(long id){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME , "ID=?" , new String[]{String.valueOf(id)});
    }

    public Cursor getData(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";
        return db.rawQuery(query, null);
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
    }

    public Cursor getQuizAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + QUIZ_TABLE_NAME , null);
    }

    public void updateData(long id, String front, float fontSize, String back, float backSize){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATABASE_FRONT, front);
        contentValues.put(DATABASE_FRONT_SIZE, fontSize);
        contentValues.put(DATABASE_BACK, back);
        contentValues.put(DATABASE_BACK_SIZE, backSize);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{String.valueOf(id)});
    }

    public Integer deleteAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME , null , null);
    }

    public Integer deleteQuizAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(QUIZ_TABLE_NAME , null , null);
    }
}