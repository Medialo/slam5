package com.example.myapplication22;

import androidx.annotation.NonNull;

public class Contact {

    private int _id;
    private String nom;
    private String numTelephone;

    public Contact() {
    }

    public Contact(int _id, String nom, String numTelephone) {
        this._id = _id;
        this.nom = nom;
        this.numTelephone = numTelephone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    @NonNull
    @Override
    public String toString() {
        return this.get_id() + " "  + this.getNom()+ " " + this.getNumTelephone();
    }
}
