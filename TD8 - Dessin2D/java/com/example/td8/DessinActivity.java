package com.example.td8;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.td8.figures.Figure;


public class DessinActivity extends Activity
{
    /// représente la zone de dessin
    DessinView dessin;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        if (dessin == null)
            dessin = new DessinView(this,null);

        //LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        //   dessin.setLayoutParams(linLayoutParam);
        setContentView(dessin);
        // TODO mettre en place le layout qui affiche un DessinView sur toute la surface de l'écran ok

    }


    /*** Gestion du menu d'option ***/

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dessin_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.menu_rectangle:
                dessin.setTypeFigure(Figure.Type.RECTANGLE);
                return true;
            case R.id.menu_ligne:
                dessin.setTypeFigure(Figure.Type.LIGNE);
                return true;
            case R.id.menu_cercle:
                dessin.setTypeFigure(Figure.Type.ELLIPSE);
                return true;
            // TODO ajouter les autres types de figures : lignes et cercles ok
            case R.id.menu_undo:
                dessin.undo();
                return true;
            case R.id.menu_palette:
                ColorPickerDialog.OnColorChangedListener listener = new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int color) {
                        dessin.setColor(color);
                    }
                };
                ColorPickerDialog dialog = new ColorPickerDialog(listener,dessin.getColor());
                dialog.show(getFragmentManager(),"tag");
                /*Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.colorpickerview);
                dialog.show();*/
                // TODO faire afficher un dialogue de sélection de couleur
                return true;
            case R.id.menu_swap:
                dessin.swap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
