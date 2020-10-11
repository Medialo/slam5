package fr.planetes;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class PlaneteAdapter extends ArrayAdapter<Planete>
{
    public PlaneteAdapter(Context context, List<Planete> planetes)
    {
        super(context, 0, planetes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // créer ou récupérer un PlaneteView

        PlaneteView planeteView = (PlaneteView) convertView;
        if (planeteView == null) {
            planeteView = PlaneteView.create(parent);
        }
        // TODO modifier la couleur de fond de l'item selon la parité de position
        if(position%2==0)
            planeteView.setBackgroundColor(Color.LTGRAY);

        // affecter les vues avec les valeurs de l'item n°position
        planeteView.setItem(super.getItem(position));
        return planeteView;

    }
}
