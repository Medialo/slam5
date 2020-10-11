package fr.planetes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class PlanetesApplication extends Application
{
    /**
     * contient la liste des items gérés par l'application
     * NB: surtout ne pas réaffecter cette variable, car l'adaptateur n'aurait plus la bonne référence
     */
    private ArrayList<Planete> liste;

    public PlanetesApplication()
    {
        // première et dernière affectation de la variable
        liste = new ArrayList<>();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        init();
    }

    public void init()
    {
        liste.clear();

        //      // initialiser les données
        //      final Planete[] initdata = {
        //              new Planete("Mercure",   48, R.drawable.mercure),
        //              new Planete("Vénus",    108, R.drawable.venus),
        //              new Planete("Terre",    150, R.drawable.terre),
        //              new Planete("Mars",     227, R.drawable.mars),
        //              new Planete("Jupiter",  778, R.drawable.jupiter),
        //              new Planete("Saturne", 1421, R.drawable.saturn),
        //              new Planete("Uranus",  2876, R.drawable.uranus),
        //              new Planete("Neptune", 4503, R.drawable.neptune)
        //      };
        //      for (Planete planete: initdata) {
        //          liste.add(planete);
        //      }

        // accès aux ressources
        Resources res = getResources();
        final String[] noms = res.getStringArray(R.array.noms);
        final int[] distances = res.getIntArray(R.array.distances);
        final int[] habitabilites =  res.getIntArray(R.array.habitabilite);
        System.out.println(habitabilites.length);
       final float[] floatList = new float[habitabilites.length];
        for (int i = 0; i < habitabilites.length; i++){
            floatList[i] = Float.valueOf(habitabilites[i]);
        }

        TypedArray images = res.obtainTypedArray(R.array.idimages);

        // recopie dans le ArrayList
        for (int i=0; i<noms.length; ++i) {
            liste.add(new Planete(noms[i], distances[i], images.getResourceId(i, 0),floatList[i]));
        }
        images.recycle();

        // tri de la liste
        sortListe();
    }


    /**
     * retourne la liste de l'application
     * @return liste des planètes
     */
    public ArrayList<Planete> getListe()
    {
        return liste;
    }

    /**
     * classe la liste par ordre croissant des distances des planètes
     */
    public void sortListe()
    {
        Collections.sort(liste, new Comparator<Planete>() {
            @Override
            public int compare(Planete planete1, Planete planete2)
            {
                Integer d1 = planete1.getDistance();
                Integer d2 = planete2.getDistance();
                return d1.compareTo(d2);
            }
        });
    }
}
