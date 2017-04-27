package com.example.oranitbu296.buddycare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabasePets extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyPet";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "Pet";

    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_SEX = "sex";
    public static final String COL_BLOOD = "bloodtype";
    public static final String COL_BREED = "Breed";


    public DatabasePets(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL
                ("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_AGE + " TEXT, " + COL_SEX + " TEXT," + COL_BLOOD +"TEXT," + COL_BREED + "TEXT);");
        db.execSQL
                ("INSERT INTO " + TABLE_NAME + " (" + COL_NAME + ", " + COL_AGE + "," + COL_SEX + "," + COL_BLOOD + ", "
                + COL_BREED + ") VALUES ('Sleeping'" + ", 'For Less', 'Android School');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
