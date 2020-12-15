package com.example.td8.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;


@SuppressWarnings("WeakerAccess")
public abstract class Figure
{
    // coordonnées des coins
    protected float x1,y1, x2,y2;

    // couleur tirée aléatoirement lors de sa création
    protected Paint paint;

    // indiquent si les coordonnées ont été fournies ou pas
    protected boolean init1, init2;

    /**
     * représente le type d'une figure (pour la méthode creer)
     */
    public enum Type {
        // TODO ajouter les symboles LIGNE et ELLIPSE ok
        RECTANGLE,
        LIGNE,
        ELLIPSE,
    }


    /**
     * retourne une nouvelle figure, selon le type indiqué
     * @param type de la figure à créer
     * @param color couleur de la nouvelle figure
     */
    public static Figure creer(Type type, int color)
    {
        switch (type) {
            // TODO ajouter ligne et cercle ok
            case RECTANGLE: return new Rectangle(color);
            case LIGNE: return new Ligne(color);
            case ELLIPSE: return new Ellipse(color);
        }
        return null;
    }


    /**
     * initialise la figure
     */
    protected Figure()
    {
        // aucun des deux coins n'a été défini
        init1 = false;
        init2 = false;
    }


    /**
     * retourne true si la figure n'est pas entièrement définie : les deux coins positionnés
     * @return true si init1 est faux ou init2 est faux
     */
    protected boolean incomplet()
    {
        return !init1 || !init2;
    }


    /**
     * positionne le centre ou le point de départ de la figure
     * @param x va dans x1
     * @param y va dans y1
     */
    public void setReference(float x, float y)
    {
        x1 = x;
        y1 = y;
        init1 = true;
    }

    /**
     * positionne le coin ou le point d'arrivée de la figure
     * @param x va dans x2
     * @param y va dans y2
     */
    public void setCoin(float x, float y)
    {
        x2 = x;
        y2 = y;
        init2 = true;
    }


    /**
     * retourne la couleur de dessin de cette figure
     * @return Paint de la figure
     */
    public Paint getPaint()
    {
        return paint;
    }

    /**
     * change la couleur de dessin de cette figure
     * param Paint de la figure
     */
    public void setPaint(Paint paint)
    {
        this.paint = paint;
    }

    /**
     * Cette méthode dessine la figure sur le canvas
     * seulement si init1 et init2 sont vrais
     * Cette méthode est spécifique du type exact de la figure
     * @param canvas
     */
    abstract public void draw(Canvas canvas);


    @NonNull
    @Override
    public String toString() {
        return x1 + " " + x2 +" " +y1 +" " +y2 +" " + paint.getColor();
    }
}
