package com.example.myapplication22;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditContact extends AppCompatActivity implements View.OnClickListener {

    private transient boolean edit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_edit);
        Intent intent = getIntent();
        Button btn = findViewById(R.id.btn_edit);
        btn.setOnClickListener(this);
        edit = intent.getBooleanExtra("edit",false);
        if(edit){
            EditText editTextName = findViewById(R.id.edittxt_edit_name);
            EditText editTextNum = findViewById(R.id.editxt_edit_num);
            editTextName.setText(intent.getStringExtra("name"));
            editTextNum.setText(intent.getStringExtra("num"));

        } else {

        }


    }

    @Override
    public void onClick(View v) {
        EditText editTextName = findViewById(R.id.edittxt_edit_name);
        EditText editTextNum = findViewById(R.id.editxt_edit_num);
        String name = editTextName.getText().toString();
        String num = editTextNum.getText().toString();
        ContactDB_DAO contactDB_dao = new ContactDB_DAO(this);
        int id = getIntent().getIntExtra("id",-1);
        Contact contact = new Contact(id,name,num);;
        if(edit){
            contactDB_dao.updateContact(contact);
            contactDB_dao.close();
            setResult(2);
        } else {
            contactDB_dao.insertContact(contact);
            setResult(3);
        }
        contactDB_dao.close();
        finish();
    }
}
