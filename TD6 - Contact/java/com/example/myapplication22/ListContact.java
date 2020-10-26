package com.example.myapplication22;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListContact extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private transient boolean edit;
    private transient List<Contact> list = new ArrayList<>();
    private transient ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        edit = intent.getBooleanExtra("edit",false);
        setContentView(R.layout.contact_list);

        ContactDB_DAO contactDB_dao = new ContactDB_DAO(this);

        ListView table = findViewById(R.id.list);
        this.list = contactDB_dao.getAllContacts();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        table.setAdapter(arrayAdapter);
        table.setOnItemClickListener(this);
        table.setOnItemLongClickListener(this);
        /*for(Contact contact : list){
            TableRow row = new TableRow(this);
            TextView tNom = new TextView(this);
            tNom.setText(contact.getNom() + " -> ");
            row.addView(tNom);
            TextView tNumTel = new TextView(this);
            tNumTel.setText(contact.getNumTelephone());
            row.addView(tNumTel);
            table.add(tNom);
        }*/

// création d'une nouvelle TableRow
        contactDB_dao.close();


    }

    private void updateList(){
        ContactDB_DAO contactDB_dao = new ContactDB_DAO(this);
        list.clear();
        list.addAll(contactDB_dao.getAllContacts());
        contactDB_dao.close();
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            updateList();
           // arrayAdapter.notifyDataSetChanged();
            return;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = list.get(position);
        if(edit){
            Intent intent = new Intent(this,EditContact.class);
            intent.putExtra("name",contact.getNom());
            intent.putExtra("num",contact.getNumTelephone());
            intent.putExtra("id",contact.get_id());
            intent.putExtra("edit",edit);
            startActivityForResult(intent,RESULT_CANCELED);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("tag","ok");
        new AlertDialog.Builder(this)
                .setTitle("Supprimer ce comtact ?")
                .setMessage("Voulez vous supprimer ce contact ?")
                .setPositiveButton("Oui",(dialog, which) -> {
                   Contact contact = this.list.get(position);
                   ContactDB_DAO contactDB_dao = new ContactDB_DAO(this);
                   contactDB_dao.deleteContact(contact);
                   contactDB_dao.close();
                   updateList();
                })
                .setNegativeButton("non",null)
                .show();

        return true;
    }
}
