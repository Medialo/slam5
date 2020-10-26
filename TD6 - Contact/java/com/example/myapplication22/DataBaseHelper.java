package com.example.myapplication22;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";


    private static final String REQUETE_CREATION_TABLE = "create table "
            + TABLE_CONTACTS + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
            + " text not null, " + KEY_PH_NO + " text not null);";

   public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE " + TABLE_CONTACTS + ";");
        onCreate(db);
    }

    /**
     * Insère un contact dans la table des contacts.
     */
    public long insertContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(KEY_NAME, contact.getNom());
        valeurs.put(KEY_PH_NO, contact.getNumTelephone());
        return db.insert(TABLE_CONTACTS, null, valeurs);
    }



    @Deprecated
    public Contact getContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.query(TABLE_CONTACTS,null,null,null,null,null,null);
        return null;
    }




    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS,null,null,null,null,null,null);
        if(!cursor.moveToFirst()){
            return list;
        }
        do {
            list.add(new Contact(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        } while (cursor.moveToNext());
        return list;
    }
}
