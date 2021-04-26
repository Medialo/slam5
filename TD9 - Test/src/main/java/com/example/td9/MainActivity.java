package com.example.td9;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    // variable qui représente le convertisseur d'unités
    private final Convertisseur mConvertisseur = new Convertisseur();

    // vues de l'interface
    private EditText etEntree;
    private TextView tvSortie;
    private Spinner spChoixConversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mettre en place l'interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        // remplir le spinner
        String[] conversions = getResources().getStringArray(R.array.conversions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conversions);
        spChoixConversion.setAdapter(adapter);

        // définir un écouteur : choix d'un item => configuration du convertisseur
        spChoixConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // demander cette conversion au convertisseur
                mConvertisseur.setCodeConversion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    /**
     * récupère les objets Java correspondant aux vues de l'interface
     */
    void findViews() {
        etEntree = findViewById(R.id.entree);
        tvSortie = findViewById(R.id.sortie);
        spChoixConversion = findViewById(R.id.conversion);
    }


    /**
     * retourne la valeur qui est actuellement saisie dans le EditText d'entrée
     *
     * @return valeur d'entrée ou NaN en cas d'erreur
     */
    double getEntree() throws NumberFormatException {
        // texte saisi par l'utilisateur
        String texte = etEntree.getText().toString();
        // analyse en double
        return Double.parseDouble(texte);
    }


    /**
     * affiche le nombre dans le TextView pour le résultat
     *
     * @param nombre valeur à afficher
     */
    void putSortie(double nombre) {
        // affichage du nombre
        tvSortie.setText(String.format(Locale.FRANCE, "%.3f", nombre));
    }

    void putSortieError() {
        // affichage du message d'erreur
        tvSortie.setText(R.string.erreur_saisie);
    }


    /**
     * appelée quand on clique le bouton Convertir
     */
    public void onConvertir(View view) {
        try {
            // récupérer l'entrée
            double entree = getEntree();

            // effectuer la conversion
            double resultat = mConvertisseur.convertir(entree);

            // afficher le résultat
            putSortie(resultat);

        } catch (Exception e) {
            putSortieError();
        }
    }


    /**
     * sert uniquement pour les tests d'intégration avec Mockito
     */
    @SuppressWarnings("unused")
    Convertisseur getConvertisseur() {
        return this.mConvertisseur;
    }
}
