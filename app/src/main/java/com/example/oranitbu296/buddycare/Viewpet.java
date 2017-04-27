package com.example.oranitbu296.buddycare;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Viewpet extends Activity {
    DatabasePets mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listStudent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpet);

        mHelper = new DatabasePets(this);
        mDb = mHelper.getReadableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePets.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("Pet's\tname : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_NAME))
                    + "\t\t" + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_AGE))
                    + "\nSex : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_SEX))
                    + "\nBloon : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BLOOD))
                    + "\nBreed : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BREED))

            );
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.mylist, arr_list);

        listStudent = (ListView)findViewById(R.id.listStudent);
        listStudent.setAdapter(adapterDir);
        listStudent.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_NAME));
                String age = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_AGE));
                String sex = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_SEX));
                String blood = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BLOOD));
                String breed = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BREED));

                AlertDialog.Builder builder = new AlertDialog.Builder(Viewpet.this);
                builder.setTitle("Pet's\tData");
                builder.setMessage("Pet's\tName : " + name + "\n\nAge : " + age + "\n\nSex : " + sex + "\n\nBlood : " + blood + "\n\nBreed : " + breed);
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}