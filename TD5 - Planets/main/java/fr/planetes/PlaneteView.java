package fr.planetes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Cette classe permet d'afficher un item dans la liste. Voir res/layout/item_planete.xml
 * Elle hérite de RelativeLayout mais ça peut être changé en LinearLayout.
 */
public class PlaneteView extends RelativeLayout
{
    /**
     * Cette méthode de classe est appelée par le PlaneteAdapter pour remplir la liste. Elle
     * crée une instance de PlaneteView. On n'utilise pas le constructeur car il demande des paramètres qu'on n'a pas.
     * @param parent : disposition à laquelle doit appartenir le PlaneteView retourné
     * TODO @param position : position de cet item dans la liste, on se base sur la parité pour changer de layout
     * @return un nouveau PlaneteView
     */
    public static PlaneteView create(ViewGroup parent)
    {
        // "gonfleur de disposition", c'est à dire créateur d'interface d'après un fichier XML
        LayoutInflater li = LayoutInflater.from(parent.getContext());

        // expanser le layout de l'item
        PlaneteView itemView = (PlaneteView) li.inflate(R.layout.item_planete, parent, false);

        // récupérer les objets java correspondant aux vues du layout
        itemView.findViews();
        return itemView;
    }


    //// variables et méthodes d'instances ////

    // vues du layout item_planete.xml
    private TextView tvNom;
    private TextView tvDistance;
    private ImageView ivImage;


    /**
     * cherche les objets Java correspondant aux vues du layout
     */
    private void findViews()
    {
        tvNom      = (TextView)  findViewById(R.id.item_planete_nom);
        tvDistance = (TextView)  findViewById(R.id.item_planete_distance);
        ivImage    = (ImageView) findViewById(R.id.item_planete_image);
    }


    /**
     * Affiche l'item dans les vues de this
     * @param planete : item dont il faut mettre les valeurs dans les vues
     */
    public void setItem(final Planete planete)
    {
        tvNom.setText(planete.getNom());
        tvDistance.setText(Integer.toString(planete.getDistance()));
        ivImage.setImageResource(planete.getIdImage());
    }


    /** constructeur */
    public PlaneteView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /** constructeur */
    public PlaneteView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
}
