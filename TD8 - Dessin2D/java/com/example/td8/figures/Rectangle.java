package com.example.td8.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Cette classe gère le type de figure rectangle
 */
@SuppressWarnings("WeakerAccess")
public class Rectangle extends Figure
{
    /**
     * crée un rectangle de la couleur indiquée
     * @param color couleur du rectangle
     */
    public Rectangle(int color)
    {
        // initialisation
        super();

        // création de la peinture (décommenter pour des effets différents)
        paint = new Paint();
        paint.setColor(color);

        // qualité du dessin (tramage et/ou antialiasing)
        //paint.setDither(true);
        //paint.setAntiAlias(true);

        // pour définir la largeur des lignes
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeJoin(Paint.Join.ROUND);
        //paint.setStrokeCap(Paint.Cap.ROUND);
        //paint.setStrokeWidth(8);
    }

    /**
     * Cette méthode dessine ce rectangle sur le canvas
     * @param canvas zone de dessin
     */
    @Override
    public void draw(Canvas canvas)
    {
        // ne rien dessiner si l'un des coins n'est pas défini
        if (incomplet()) return;

        // méthode drawRect en tenant compte des cas possibles pour les coordonnées
        canvas.drawRect(Math.min(x1,x2), Math.min(y1,y2), Math.max(x1,x2), Math.max(y1,y2), paint);
    }
}
