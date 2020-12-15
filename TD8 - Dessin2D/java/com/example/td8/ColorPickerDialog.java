package com.example.td8;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.EventListener;


/**
 * Cette classe affiche un dialogue pour choisir une couleur.
 * Il faut appeler son constructeur avec un écouteur OnColorChangedListener
 * qui sera appelé à la fermeture du dialogue, avec la couleur choisie en
 * paramètres. Il faut aussi fournir la couleur initiale.
 */
public class ColorPickerDialog extends DialogFragment
{
    /**
     * listener pour les choix de couleur
     */
    public interface OnColorChangedListener extends EventListener
    {
        /** signale que la couleur a été choisie */
        void onColorChanged(int color);
    }


    /** écouteur à prévenir pour le choix d'une couleur */
    private OnColorChangedListener mListener;

    /** couleur initialement proposée */
    private int mInitialColor;

    /**
     * constructeur par défaut
     */
    public ColorPickerDialog()
    {
        mListener = null;
        mInitialColor = Color.RED;
    }

    /**
     * Classe pour gérer un dialogue de sélection de couleur
     * @param listener à prévenir quand la couleur a été choisie
     * Note: il va y avoir un warning sur ce constructeur, il faut l'ignorer
     */
    public ColorPickerDialog(OnColorChangedListener listener)
    {
        mListener = listener;
        mInitialColor = Color.RED;
    }

    /**
     * Classe pour gérer un dialogue de sélection de couleur
     * @param listener à prévenir quand la couleur a été choisie
     * @param initialColor couleur de départ
     * Note: il va y avoir un warning sur ce constructeur, il faut l'ignorer
     */
    public ColorPickerDialog(OnColorChangedListener listener, int initialColor)
    {
        mListener = listener;
        mInitialColor = initialColor;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        Context ctx = getActivity();
        final ColorPickerView colorPickerView = new ColorPickerView(ctx,mInitialColor);
        Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Choisissez une couleur !");
        builder.setNegativeButton(android.R.string.no,null);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mListener.onColorChanged(colorPickerView.getColor());


            }
        });

        builder.setView(colorPickerView);
        // TODO compléter le dialogue


        return builder.create();
    }



    /**
     * Cette classe est la vue spécialisée qui affiche les curseurs
     * pour choisir la couleur. Elle est insérée dans un ColorPickerDialog.
     */
    private static class ColorPickerView extends LinearLayout
    {
        /// échantillon de couleur
        private View ivCouleur;

        /// couleur choisie par l'utilisateur avec les curseurs
        private int mCouleur;

        private EventListener listener = new OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {

            }
        };

        /// constructeur
        ColorPickerView(Context context, int initialColor)
        {
            // initialisation
            super(context);
            mCouleur = initialColor;

            // mettre en place le layout
            inflate(getContext(), R.layout.colorpickerview, this);

            // récupérer les contrôles
            SeekBar sbRouge = findViewById(R.id.sbRouge);
            SeekBar sbVert = findViewById(R.id.sbVert);
            SeekBar sbBleu = findViewById(R.id.sbBleu);
            SeekBar sbAlpha = findViewById(R.id.sbAlpha);
            // TODO et les autres ... ok

            // mettre la couleur initiale dans les différents SeekBar
            sbRouge.setProgress(Color.red(initialColor));
            sbVert.setProgress(Color.green(initialColor));
            sbBleu.setProgress(Color.blue(initialColor));
            sbAlpha.setProgress(Color.alpha(initialColor));
            // TODO et les autres ... ok

            // récupère la vue qui affiche l'échantillon de couleur et change sa couleur
            ivCouleur = findViewById(R.id.ivCouleur);
            ivCouleur.setBackgroundColor(initialColor);
            // attacher des listeners pour changer la couleur
            sbRouge.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                {
                    Log.i("tag","couleur changer rouge");
                    // changer seulement la composante rouge de mCouleur
                    mCouleur = Color.argb(Color.alpha(mCouleur), progress, Color.green(mCouleur), Color.blue(mCouleur));
                    // modifier la couleur de l'échantillon de couleur
                    ivCouleur.setBackgroundColor(mCouleur);
                }
            });




            sbBleu.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                {
                    // changer seulement la composante rouge de mCouleur
                    mCouleur = Color.argb(Color.alpha(mCouleur), Color.red(mCouleur), Color.green(mCouleur),progress);
                    // modifier la couleur de l'échantillon de couleur
                    ivCouleur.setBackgroundColor(mCouleur);
                }
            });


            sbVert.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                {
                    // changer seulement la composante rouge de mCouleur
                    mCouleur = Color.argb(Color.alpha(mCouleur), Color.red(mCouleur), progress, Color.blue(mCouleur));
                    // modifier la couleur de l'échantillon de couleur
                    ivCouleur.setBackgroundColor(mCouleur);
                }
            });

            sbAlpha.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // changer seulement la composante rouge de mCouleur
                    mCouleur = Color.argb(progress, Color.red(mCouleur), Color.green(mCouleur), Color.blue(mCouleur));
                    // modifier la couleur de l'échantillon de couleur
                    ivCouleur.setBackgroundColor(mCouleur);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            // TODO rendre les trois autres SeekBar actifs, comme sbRouge mais chacun sur sa composante ok
        }

        /**
         * retourne la couleur actuelle
         * @return
         */
        public int getColor()
        {
            return mCouleur;
        }
    }
}
