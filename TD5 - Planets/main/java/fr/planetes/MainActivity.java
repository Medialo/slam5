package fr.planetes;

/**
 * Biblio : http://www.bignerdranch.com/blog/customizing-android-listview-rows-subclassing/
 */

import java.util.ArrayList;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnItemClickListener
{
    private static final String TAG = "planetes";

    private ArrayAdapter<Planete> adapter;

    // TODO définir un RequestCode pour indiquer qu'on édite l'item
    // private static final int RQ_CODE_EDITION = ...


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // mettre en place l'interface
        super.onCreate(savedInstanceState);


        setContentView(R.layout.main_activity);

        // liste des éléments, elle vient de l'application
        PlanetesApplication app = (PlanetesApplication) getApplicationContext();
        ArrayList<Planete> liste = app.getListe();

        // adaptateur pour la liste

        adapter = new PlaneteAdapter(this,liste);
        adapter.notifyDataSetChanged();
        /*adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                liste);*/

        // associer la liste affichée et l'adaptateur
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(adapter);

        // rajouter un écouteur pour les clics sur les items
        lv.setOnItemClickListener(this);

        // mode de sélection des items
        lv.setItemsCanFocus(true);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


    /**
     * Cette méthode est appelée quand on revient dans cette activité après avoir appelé une
     * autre par startActivityForResult(intent, requestCode).
     * @param requestCode : celui qui avait été passé à startActivityForResult
     * @param resultCode : RESULT_OK (l'uti a validé l'autre activité) ou RESULT_CANCELED (l'uti a fait back ou annuler), voir aussi setResult(resultCode)
     * @param data : un intent qui serait fourni par l'activité appelée
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        Log.i(TAG, "MainActivity.onActivityResult("+requestCode+", "+resultCode+", ...)");
        if (resultCode == RESULT_CANCELED) return;

        // prévenir l'adaptateur que la liste a changé

        adapter.notifyDataSetChanged();
        //adapter.setNotifyOnChange(true);
    }


    /*** Gestion des clics sur les éléments ***/

    /**
     * appelée quand on clique sur l'un des items de la liste
     * @param position : le n° de l'item dans la liste
     * @param id : identifiant de la vue dans la liste
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        // exemple 1 : afficher un message
        Toast.makeText(this, "onItemClick: position=" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position",position);
        startActivityForResult(intent, RESULT_CANCELED);

        //      // exemple 2 : modifier l'item sélectionné
        //      Planete item = adapter.getItem(position);
        //      item.setNom("planète morte");
        //      item.setIdImage(R.drawable.morte);
        //      adapter.notifyDataSetChanged();


        //      // exemple 3 : supprimer l'item sélectionné à l'aide de l'adaptateur
        //        adapter.remove(adapter.getItem(position));


        // TODO lancer l'activité d'édition sur l'item sélectionné okup
        // créer un intent
        // lui rajouter un extra appelé identifiant et valant position
        // lancer EditActivity en attendant son résultat avec le RequestCode = RQ_CODE_EDITION
    }


    /*** Gestion du menu de la barre d'action en haut à droite ***/

    /**
     * appelée pour créer le menu de l'activité
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * appelée quand on clique sur un élément de menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // selon l'item de menu qui est sélectionné, appeler la méthode concernée
        switch (item.getItemId()) {
        case R.id.init:
            // l'utilisateur a choisi le menu init : réinitialiser la liste
            onMenuInit();
            return true;

        case R.id.nouveau:
            // l'utilisateur a choisi le menu nouveau : ajouter un nouvel élément dans la liste
            onMenuNouveau();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * menu init => ré-initialiser la liste
     */
    private void onMenuInit()
    {
        // récupérer l'application et effacer la liste
        PlanetesApplication app = (PlanetesApplication) getApplicationContext();
        app.init();

        // prévenir l'adaptateur que la liste a changé
        adapter.notifyDataSetChanged();
    }


    /**
     * menu nouveau => rajouter un élément dans la liste
     */
    private void onMenuNouveau()
    {
        // FIXME : supprimer la ligne suivante et aussi la méthode creerRandomItem() plus bas ok
        //creerRandomItem();
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent,RESULT_CANCELED);
        // TODO lancer l'activité d'édition en mode création (identifiant absent ou -1) ok
        // créer un intent
        // NE PAS METTRE D'EXTRA appelé identifiant dans cet intent, OU ALORS le mettre à -1 !
        // lancer EditActivity en attendant son résultat, RequestCode = RQ_CODE_EDITION
        adapter.notifyDataSetChanged();
    }


    /**
     * rajoute un élément aléatoire dans la liste
     * NB: c'est seulement pour faire un exemple ! cette fonction est à supprimer si on lance l'édition correctement, voir onMenuNouveau
     */
   /* private void creerRandomItem()
    {
        // récupérer la liste
        PlanetesApplication app = (PlanetesApplication) getApplicationContext();
        ArrayList<Planete> liste = app.getListe();

        // créer un nouvel élément
        Random r = new Random();
        Planete nouvelle = new Planete("2e chance pour les humains", r.nextInt(40)+130, R.drawable.terre);

        // le mettre n'importe où dans la liste
        int pos = r.nextInt(liste.size()+1);
        liste.add(pos, nouvelle);

        // prévenir l'adaptateur que la liste a changé
        adapter.notifyDataSetChanged();
    }*/
}
