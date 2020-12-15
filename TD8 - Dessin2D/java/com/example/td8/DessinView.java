package com.example.td8;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.td8.figures.Figure;
import com.example.td8.figures.Figure.Type;


public class DessinView extends View
{
    private static final String TAG = "dessin";

    // type de figure courant (prochain à être dessiné)
    private Figure.Type typefigure = Type.RECTANGLE;

    // couleur courante (pour la prochaine figure)
    private int color = Color.HSVToColor(new float[] { (float) (Math.random()*360.0f), 1.0f, 1.0f });

    // représente l'ensemble des figures, vient de l'application sous-jacente
    private LinkedList<Figure> figures;


    /**
     * constructeur : récupère la liste de l'application et se met en écouteur des touchers
     * @param context
     * @param attrs
     */
    public DessinView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // liste des éléments, elle vient de l'application
        DessinApplication app = (DessinApplication) context.getApplicationContext();
        figures = app.getFigures();
    }


    /**
     * appelée à chaque fois qu'il y a un invalidate
     * @param canvas à dessiner
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        // TODO supprimer les 5 lignes suivantes une fois qu'on a compris le principe
        /*
        Paint paint = new Paint();
        for (int i=0; i<50; i++) {
            paint.setColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
            canvas.drawCircle((float)Math.random()*600+50, (float)Math.random()*600+50, (float)Math.random()*80+20, paint);
        }
*/
        // TODO dessiner toutes les figures de la liste sur le canvas
        for (Figure figure : figures){
           Log.i("tag",figure.toString());
           figure.draw(canvas);
        }

    }


    /**
     * appelée quand l'utilisateur touche l'écran
     */
    private Figure figure;
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // coordonnées actuelles du touché
        float x = event.getX();
        float y = event.getY();

        // couleur aléatoire, TODO enlever ça quand il y a le sélecteur de couleur fixme



        // TODO gérer les touchers, bougers... pour dessiner des figures



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                figure = Figure.creer(typefigure,color);
                Log.i("xy down",""+event.getX() + " " +event.getY());
                figure.setReference(x,y);

                return true;
            case MotionEvent.ACTION_MOVE:
              //  Log.i("xy",""+event.getX() + " " +event.getY());
                if (figure != null)
                    figure.setCoin(x,y);
                return true;
            case MotionEvent.ACTION_UP:
               // Log.i("xy up",""+event.getX() + " " +event.getY());
                if(figure != null)
                    figures.add(figure);
                performClick();

                // demander de redessiner la vue => appel à onDraw

                invalidate();
                return true;
        }

        // TODO ce sera à intégrer dans le switch, dans le cas ACTION_UP ok


        return true;
    }


    @Override
    public boolean performClick()
    {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();

        // Handle the action for the custom click here
        return true;
    }


    /**
     * définit le type de la prochaine figure à dessiner
     * @param type
     */
    public void setTypeFigure(Type type)
    {
        typefigure = type;
    }


    /**
     * supprime la figure qui est devant
     */
    public void undo()
    {
        // TODO enlever la dernière figure (s'il y en a au moins une) ok
        if(figures.size() >=1){
            figures.removeLast();
        }
        // tout redessiner
        invalidate();

    }


    /**
     * supprime toutes les figures
     */
    public void clear()
    {
        figures.clear();
        invalidate();
    }


    /**
     * fait passer la dernière figure devant
     */
    public void swap()
    {
        // TODO aller chercher la première figure de la liste (la plus ancienne) et la faire passer devant ok
        if(figures.size() >= 2){
            Figure figure = figures.getFirst();
            figures.removeFirst();
            figures.add(0,figures.getLast());
            figures.removeLast();
            figures.add(figure);
        }
        // tout redessiner
        invalidate();
    }


    /**
     * retourne la couleur courante
     * @return
     */
    public int getColor()
    {
        return color;
    }


    /**
     * change la couleur de la prochaine figure dessinée
     * @param color
     */
    public void setColor(int color)
    {
        this.color = color;
    }


    /**
     * enregistre l'image du dessin dans le fichier indiqué (carte SD externe)
     * @param filename
     */
    public void save(String filename)
    {
        Log.i(TAG, "SAVE "+filename);

        setDrawingCacheEnabled(true);

        layout(0, 0, getWidth(), getHeight());
        Log.i(TAG, "w="+getWidth()+" h="+getHeight());

        buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(getDrawingCache());

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {}
        }
    }
}
