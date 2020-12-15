package com.example.td8.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ellipse extends Figure{



    public Ellipse(int color) {

        paint = new Paint();
        paint.setColor(color);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x1, y1, (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)), paint);
    }


}
