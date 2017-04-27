package com.example.oranitbu296.buddycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addpet extends AppCompatActivity {
    DatabasePets mHelper;
    SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet);

        mHelper = new DatabasePets(this);
        mDb = mHelper.getWritableDatabase();

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editAge = (EditText)findViewById(R.id.editAge);
        final EditText editSex = (EditText)findViewById(R.id.editSex);
        final EditText editBlood = (EditText)findViewById(R.id.editBlood);
        final EditText editBreed = (EditText)findViewById(R.id.editBreed);

        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                String sex = editSex.getText().toString();
                String blood = editBlood.getText().toString();
                String breed = editBreed.getText().toString();

                if (name.length() != 0 && age.length() != 0 && sex.length() != 0 && blood.length() != 0 && breed.length() != 0) {

                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePets.TABLE_NAME
                            + " WHERE " + DatabasePets.COL_NAME + "='" + name + "'"
                            + " AND " + DatabasePets.COL_AGE + "='" + age + "'"
                            + " AND " + DatabasePets.COL_SEX + "='" + sex + "'"
                            + " AND " + DatabasePets.COL_BLOOD + "='" + blood + "'"
                            + " AND " + DatabasePets.COL_BREED + "='" + breed + "'",null);

                    if (mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabasePets.TABLE_NAME + " ("
                                + DatabasePets.COL_NAME + ", " + DatabasePets.COL_AGE
                                + ", " + DatabasePets.COL_SEX + "," + DatabasePets.COL_BLOOD + "," + DatabasePets.COL_BREED + ") VALUES ('" + name
                                + "', '" + age + "', '" + sex + "','" + blood + "','" + breed + "');");

                        editName.setText("");
                        editAge.setText("");
                        editSex.setText("");
                        editBlood.setText("");
                        editBreed.setText("");

                        Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลสัตว์เลี้ยงเรียบร้อยแล้ว"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "คุณมีข้อมูลสัตว์เลี้ยงนี้อยู่แล้ว"
                                , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลสัตว์เลี้ยงให้ครบทุกช่อง"
                            , Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}

