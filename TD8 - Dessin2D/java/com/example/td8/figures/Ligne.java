package com.example.td8.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ligne extends Figure {


    public Ligne(int color) {

        paint = new Paint();
        paint.setColor(color);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x2,y2,x1,y1,paint);
    }


}
