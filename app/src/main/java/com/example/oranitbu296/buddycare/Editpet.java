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
import android.widget.Toast;

public class Editpet extends Activity {
    DatabasePets mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listPets;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpet);
    }

    public void onResume() {
        super.onResume();

        mHelper = new DatabasePets(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePets.TABLE_NAME, null);

        listPets = (ListView)findViewById(R.id.listStudent);
        listPets.setAdapter(updateListView());
        listPets.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_NAME));
                String age = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_AGE));
                String sex = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_SEX));
                String blood = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BLOOD));
                String breed = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BREED));

                Intent intent = new Intent(getApplicationContext(), UpdatePets.class);
                intent.putExtra("NAME", name);
                intent.putExtra("AGE", age);
                intent.putExtra("SEX", sex);
                intent.putExtra("BLOOD", blood);
                intent.putExtra("BREED", breed);
                startActivity(intent);
            }
        });

        listPets.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                AlertDialog.Builder builder = new AlertDialog.Builder(Editpet.this);
                builder.setTitle("ลบข้อมูลสัตว์เลี้ยง");
                builder.setMessage("คุณต้องการลบสัตว์เลี้ยงใช่หรือไม่?");
                builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_NAME));
                        String age = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_AGE));
                        String sex = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_SEX));
                        String blood = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BLOOD));
                        String breed = mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_BREED));


                        mDb.execSQL("DELETE FROM " + DatabasePets.TABLE_NAME
                                + " WHERE " + DatabasePets.COL_NAME + "='" + name + "'"
                                + " AND " + DatabasePets.COL_AGE + "='" + age + "'"
                                + " AND " + DatabasePets.COL_SEX + "='" + sex + "'"
                                + " AND " + DatabasePets.COL_BLOOD + "='" + blood + "'"
                                + " AND " + DatabasePets.COL_BREED + "='" + breed + "';");

                        mCursor.requery();

                        listPets.setAdapter(updateListView());

                        Toast.makeText(getApplicationContext(),"ลบข้อมูลสัตว์เลี้ยงเรียบร้อย"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                return true;
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }

    public ArrayAdapter<String> updateListView() {
        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast()){
            arr_list.add("ชื่อ : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_NAME)) + "\n"
                    + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_AGE)) + "\n"
                    + "โรงเรียน : " + mCursor.getString(mCursor.getColumnIndex(DatabasePets.COL_SEX)));
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.mylist, arr_list);
        return adapterDir;
    }
}