package com.example.kidal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kidal.rangking.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;

public class SqliteHandler extends SQLiteOpenHelper {

    private static final String TAG = SqliteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "bayi";

    //USER
    public static final String TABLE_USER = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SCORE = "score";
    public static final String KEY_LEVEL = "level";




    public SqliteHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + KEY_ID + " TEXT PRIMARY KEY ,"
                + KEY_NAME + " TEXT,"
                + KEY_SCORE + " INTEGER,"
                + KEY_LEVEL + " TEXT"
                + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(String id_u, String name, Integer score, String level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id_u);
        values.put(KEY_NAME, name);
        values.put(KEY_SCORE, score);
        values.put(KEY_LEVEL, level);

        long id = db.insert(TABLE_USER, null, values);
        db.close();
        Log.d("tes", "New user inserted into sqlite: " + id);
    }

    public Cursor getUser(String level) {
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE level = '" + level + "' ORDER BY score DESC";
//        String selectQuery = Utils
//                .dbGetdata(TABLE_USER, null, null, new String[]{KEY_SCORE, "DESC"});
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
