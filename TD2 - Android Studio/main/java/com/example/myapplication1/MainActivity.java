package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int v = 54;
        int n = v / 9 + 3;
        int f = factorielle(n);
        Log.i("TAG", n + "! = " + f);

        @SuppressLint("ResourceType") TextView texte = findViewById(R.mipmap.ic_launcher);
        texte.setText("Ça marche");

        @SuppressLint("WrongViewCast") Button bouton = (Button) findViewById(R.id.texte);
        bouton.setText("Ça marche");



    }

    private int factorielle(int n) {
        int r;
        if (n > 1) {
            int fnm1 = factorielle(n - 1);
            r = n * fnm1;
        } else {
            r = 1;
        }
        return r;
    }

}