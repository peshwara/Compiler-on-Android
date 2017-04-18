package com.example.poornima.fourfp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

/**
 * Created by Poornima on 5/18/2016.
 */
public class Rectangle extends View {

    int x1, y1, x2, y2, style;

    public Rectangle (Context context, int x1, int y1, int x2, int y2, int style) {
        super(context);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.style = style;
    }

    Paint paintin = new Paint();
    Paint paintout = new Paint();

    public void onDraw(Canvas canvas) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        if(style == 1){
            paintin.setColor(Color.BLUE);
            paintout.setColor(Color.RED);
        }else if(style == 2){

            paintin.setColor(Color.YELLOW);
            paintout.setColor(Color.BLACK);
        }
        else if(style == 3){

            paintin.setColor(Color.MAGENTA);
            paintout.setColor(Color.GREEN);
        }

        //paint.setColor(color);
        //paint.setStyle(Paint.Style.FILL);

        paintin.setStyle(Paint.Style.FILL);
        canvas.drawRect(x1, y1, x2, y2, paintin);
        paintout.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x1, y1, x2, y2, paintout);


        //canvas.drawRect(x1, y1, x2, y2, paint);

        //canvas.drawCircle(rnd.nextInt(getWidth()), rnd.nextInt(getHeight()), rnd.nextInt(getWidth() / 3 + 5), paint);
    }
}
