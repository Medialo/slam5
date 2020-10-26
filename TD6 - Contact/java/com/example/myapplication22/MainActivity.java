package com.example.myapplication22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    private transient ContactDB_DAO contactDB_dao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DataBaseHelper leHelper = new DataBaseHelper(this);
        contactDB_dao = new ContactDB_DAO(this);
        //SQLiteDatabase maBaseDonnees = leHelper.getWritableDatabase();

        Contact contact = new Contact(42,"Brelet","0637291267");
        contactDB_dao.insertContact(contact);
        Button button = (Button) findViewById(R.id.btn_menu_1);
        Button button4 = (Button) findViewById(R.id.btn_menu_4);
        Button button2 = (Button) findViewById(R.id.btn_menu_2);
        Button button3 = (Button) findViewById(R.id.btn_menu_3);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("TAGTGATGAG","item");
    }

    @Override
    public void onClick(View v) {

        Log.i("TAGTAGTAG","btn");
        switch (v.getId()){
            case R.id.btn_menu_1:
               Intent i = new Intent(this, ListContact.class);
                i.putExtra("edit",false);
                startActivity(i);
                break;
            case R.id.btn_menu_2:
                Intent i2 = new Intent(this, EditContact.class);
                i2.putExtra("edit",false);
                startActivity(i2);
                break;
            case R.id.btn_menu_3:
                Intent ii = new Intent(this, ListContact.class);
                ii.putExtra("edit",true);
                startActivity(ii);
                break;
            case R.id.btn_menu_4:
                contactDB_dao.videLaBase();
                break;
        }
    }
}