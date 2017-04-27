package com.example.oranitbu296.buddycare;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePets extends Activity {
    DatabasePets mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    String name, age, sex, blood, breed ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pets);

        name = getIntent().getExtras().getString("NAME");
        age = getIntent().getExtras().getString("AGE");
        sex = getIntent().getExtras().getString("SEX");
        blood = getIntent().getExtras().getString("BLOOD");
        breed = getIntent().getExtras().getString("BREED");

        mHelper = new DatabasePets(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabasePets.TABLE_NAME
                + " WHERE " + DatabasePets.COL_NAME + "='" + name + "'"
                + " AND " + DatabasePets.COL_AGE  + "='" + age + "'"
                + " AND " + DatabasePets.COL_SEX  + "='" + sex + "'"
                + " AND " + DatabasePets.COL_BLOOD  + "='" + blood + "'"
                + " AND " + DatabasePets.COL_BREED + "='" + breed  + "'", null);

        final EditText editName = (EditText)findViewById(R.id.editName);
        editName.setText(name);
        final EditText editAge = (EditText)findViewById(R.id.editAge);
        editAge.setText(age);
        final EditText editSex = (EditText)findViewById(R.id.editSex);
        editSex.setText(sex);
        final EditText editBlood = (EditText)findViewById(R.id.editBlood);
        editBlood.setText(blood);
        final EditText editBreed = (EditText)findViewById(R.id.editBreed);
        editBreed.setText(breed);

        Button buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String nameUpdate = editName.getText().toString();
                String ageUpdate = editAge.getText().toString();
                String sexUpdate = editSex.getText().toString();
                String bloodUpdate = editBlood.getText().toString();
                String breedUpdate = editBreed.getText().toString();

                if(name.length() != 0 && age.length() != 0 && sex.length() != 0&& blood.length() != 0 && breed.length() != 0 )
                {
                    mDb.execSQL("UPDATE " + DatabasePets.TABLE_NAME  + " SET "
                            + DatabasePets.COL_NAME + "='" + nameUpdate + "', "
                            + DatabasePets.COL_AGE + "='" + ageUpdate + "', "
                            + DatabasePets.COL_SEX + "='" + sexUpdate + "', "
                            + DatabasePets.COL_BLOOD + "='" + bloodUpdate + "', "
                            + DatabasePets.COL_BREED + "='" + breedUpdate
                            + "' WHERE " + DatabasePets.COL_NAME + "='" + name + "'"
                            + " AND " + DatabasePets.COL_AGE + "='" + age + "'"
                            + " AND " + DatabasePets.COL_SEX + "='" + sex + "'"
                            + " AND " + DatabasePets.COL_BLOOD + "='" + blood + "'"
                            + " AND " + DatabasePets.COL_BREED + "='" + breed + "';");

                    Toast.makeText(getApplicationContext(), "แก้ไขข้อมูลสัตว์เลี้ยงเรียบร้อยแล้ว"
                            , Toast.LENGTH_SHORT).show();

                    finish();

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