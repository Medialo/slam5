package com.example.td8;

import java.util.LinkedList;


import android.app.Application;
import android.graphics.Color;

import com.example.td8.figures.Figure;
import com.example.td8.figures.Rectangle;

public class DessinApplication extends Application
{
    /**
     * contient la liste des figures, voir DessinView.java
     */
    private LinkedList<Figure> figures;

    public DessinApplication()
    {
        //super();
        // première et dernière affectation de la variable
        figures = new LinkedList<>();


        // TODO supprimer toutes ces lignes qui initialisent le dessin ok
      /*  for (int i=0; i<10; i++) {
            int color = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
            Figure rect = new Rectangle(color);
            rect.setReference((float)Math.random()*600+50, (float)Math.random()*6100+50);
            rect.setCoin((float)Math.random()*1000+50, (float)Math.random()*600+50);
            figures.add(rect);

        }*/

    }

    /**
     * retourne la liste de l'application
     * @return liste des figures
     */
    public LinkedList<Figure> getFigures()
    {
        return figures;
    }
}
