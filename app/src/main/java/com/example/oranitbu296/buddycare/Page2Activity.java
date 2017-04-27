package com.example.oranitbu296.buddycare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Page2Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        DatabasePets mHelper = new DatabasePets(this);
        SQLiteDatabase mDb = mHelper.getWritableDatabase();
        mHelper.close();
        mDb.close();


        Button buttonView = (Button)findViewById(R.id.button2);
        buttonView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Viewpet.class);
                startActivity(intent);
            }
        });

        Button buttonAdd = (Button)findViewById(R.id.button3);
        buttonAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Addpet.class);
                startActivity(intent);
            }
        });

        Button buttonEdit = (Button)findViewById(R.id.button4);
        buttonEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Editpet.class);
                startActivity(intent);
            }
        });
    }
}