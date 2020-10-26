package com.example.myapplication22;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactDB_DAO{


    private static final String TABLE_CONTACTS = "contacts";
    public static final String COLONNE_ID = "id";
    public static final int COLONNE_ID_ID = 0;
    public static final String COLONNE_NOM = "name";
    public static final int COLONNE_NOM_ID = 1;
    public static final String COLONNE_PHONE_NUMBER = "phone_number";
    public static final int COLONNE_PHONE_NUMBER_ID = 2;
    private static final String REQUETE_CREATION_TABLE = "create table "
            + TABLE_CONTACTS + " (" + COLONNE_ID
            + " integer primary key autoincrement, " + COLONNE_NOM
            + " text not null, " + COLONNE_PHONE_NUMBER + " text not null);";

    private SQLiteDatabase maBaseDonnees;
    private DataBaseHelper baseHelper;

    public ContactDB_DAO(Context ctx) {
        baseHelper = new DataBaseHelper(ctx);
        open();
    }
    public SQLiteDatabase open() {
        maBaseDonnees = baseHelper.getWritableDatabase();
        return maBaseDonnees;
    }
    public void close() {
        maBaseDonnees.close();
    }
    /**
     * Récupère une planète en fonction de son nom.
     * @param nom
     * Le nom de la planète à retourner.
     * @return La planète dont le nom est égale au paramètre 'nom'.
     */
    public Contact getContact(String nom) {
        Cursor c = maBaseDonnees.query(TABLE_CONTACTS, new String[] {
                        COLONNE_ID, COLONNE_NOM, COLONNE_PHONE_NUMBER}, null, null, null,
                COLONNE_NOM + " LIKE " + nom, null);
        return cursorToContact(c);
    }


    private Contact cursorToContact(Cursor c) {
// Si la requête ne renvoie pas de résultat
        if (c.getCount() == 0)
            return null;
        Contact retContact = new Contact();
// Extraction des valeurs depuis le curseur
        retContact.set_id(c.getInt(COLONNE_ID_ID));
        retContact.setNom(c.getString(COLONNE_NOM_ID));
        retContact.setNumTelephone(c.getString(COLONNE_PHONE_NUMBER_ID));
// Ferme le curseur pour libérer les ressources
        c.close();
        return retContact;
    }


    /**
     * Retourne toutes les planètes de la base de données.
     *
     * @return Un ArrayList<Planete> contenant toutes les planètes de la BD
     */
    public ArrayList<Contact> getAllContacts() {
        Cursor c = maBaseDonnees.query(TABLE_CONTACTS, new String[] {
                        COLONNE_ID, COLONNE_NOM, COLONNE_PHONE_NUMBER}, null, null, null,
                null, null);
        return cursorToContacts(c);
    }


    private ArrayList<Contact> cursorToContacts(Cursor c) {
// Si la requête ne renvoie pas de résultat
        if (c.getCount() == 0)
            return new ArrayList<Contact>(0);
        ArrayList<Contact> retContact = new ArrayList<Contact>(c.getCount());
        c.moveToFirst();
        do {
            Contact contact = new Contact();
            contact.set_id(c.getInt(COLONNE_ID_ID));
            contact.setNom(c.getString(COLONNE_NOM_ID));
            contact.setNumTelephone(c.getString(COLONNE_PHONE_NUMBER_ID));
            retContact.add(contact);
        } while (c.moveToNext());
// Ferme le curseur pour libérer les ressources
        c.close();
        return retContact;
    }





    /**
     * Insère une planète dans la table des planètes.
     *
     * @param contact
     * La planète à insérer.
     */
    public long insertContact(Contact contact) {
        ContentValues valeurs = new ContentValues();
        valeurs.put(COLONNE_NOM, contact.getNom());
        valeurs.put(COLONNE_PHONE_NUMBER, contact.getNumTelephone());
        return maBaseDonnees.insert(TABLE_CONTACTS, null, valeurs);
    }
    public void videLaBase() {
// Dans notre cas, nous supprimons la base et les données pour en
// créer une nouvelle ensuite. Vous pouvez créer une logique de mise
// à jour propre à votre base permettant de garder les données à la
// place.
        maBaseDonnees.execSQL("drop table " + TABLE_CONTACTS + ";");
// Création de la nouvelle structure.
        maBaseDonnees.execSQL(REQUETE_CREATION_TABLE);
    }

    public void updateContact(Contact contact){
        ContentValues values = new ContentValues();
        values.put(COLONNE_NOM, contact.getNom());
        values.put(COLONNE_PHONE_NUMBER, contact.getNumTelephone());
        maBaseDonnees.update(TABLE_CONTACTS, values, COLONNE_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }

    public void deleteContact(Contact contact) {

        maBaseDonnees.delete(TABLE_CONTACTS, COLONNE_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });

    }

}























