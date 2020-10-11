package fr.planetes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class EditActivity extends Activity {

    /** identifiant de l'item en cours d'édition */
    private int identifiant;

    // contenu du layout
    private ImageView ivImage;
    private EditText etNom;
    private EditText etDistance;
    private RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mettre en place l'interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        findViews();

        // FIXME aller chercher l'identifiant dans l'intent qui a lancé cette activité. Valeur par défaut = -1. Donc s'il est <0 alors c'est une création ok
        Intent intent = getIntent();
        identifiant = intent.getIntExtra("position",-1);

        // changer le titre de la fenêtre selon qu'on édite ou qu'on crée un item
        if (identifiant < 0) {
            setTitle(R.string.nouveau);
        } else {
            setTitle(R.string.edit);
        }

        // afficher les informations éditables (nom,distance) de la planète
        setItem();
    }

    /**
     * cherche les objets Java correspondant aux vues
     */
    private void findViews() {
        ivImage = (ImageView) findViewById(R.id.item_planete_image);
        etDistance = (EditText) findViewById(R.id.item_planete_distance);
        etNom = (EditText) findViewById(R.id.item_planete_nom);
        rb = findViewById(R.id.ratingBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * Affiche l'item dans les vues de this
     */
    public void setItem() {
        // si l'identifiant est invalide, alors sortir de la fonction (aucun item à afficher)
        if (identifiant < 0) return;

        // récupérer l'item désigné par l'identifiant
        PlanetesApplication app = (PlanetesApplication) getApplicationContext();
        ArrayList<Planete> liste = app.getListe();
        Planete planete = liste.get(identifiant);

        // afficher ses valeurs dans les vues
        etNom.setText(planete.getNom());
        ivImage.setImageResource(planete.getIdImage());
        etDistance.setText(String.valueOf(planete.getDistance()));
        rb.setRating(planete.getHabitabilite());
    }

    /**
     * Affecte les propriétés de l'item avec ce qu'il y a dans les vues
     */
    public void onValider() {
        PlanetesApplication app = (PlanetesApplication) getApplicationContext();
        ArrayList<Planete> liste = app.getListe();
     //   Planete planete = liste.get(identifiant);

        // item concerné par l'activité d'édition
        Planete planete;

        // FIXME si l'identifiant est -1, alors il faut créer l'item, sinon il faut le prendre dans la liste de l'application ok

        planete = new Planete();

        // récupérer les valeurs présentes dans les vues
        planete.setNom(etNom.getText().toString());
        planete.setDistance(Integer.parseInt(etDistance.getText().toString()));
        planete.setHabitabilite(rb.getRating());
        // on ne peut pas màj l'image, il faut faire autrement mais ce n'est pas demandé

        if(identifiant<0){
            liste.add(planete);
        } else {
            liste.set(identifiant,planete);
        }

        // TODO trier la liste sur la distance des planètes ok
        app.sortListe();
    }


    /*** Gestion du menu de la barre d'action en haut à droite ***/

    /**
     * appelée pour créer le menu de l'activité
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    /**
     * appelée quand on clique sur un élément de menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.valider:
            // l'utilisateur a choisi le menu valider : l'activité se termine avec succès
            onValider();
            setResult(RESULT_OK);           // cf onActivityResult dans MainActivity
            finish();
            return true;

        case R.id.annuler:
            // l'utilisateur a choisi le menu annuler : l'activité se termine en échec
            setResult(RESULT_CANCELED);     // cf onActivityResult dans MainActivity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
