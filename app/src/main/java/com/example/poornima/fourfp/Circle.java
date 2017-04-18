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
public class Circle extends View {

    int x, y, rad, style;

    public Circle(Context context, int x, int y, int rad, int style) {
        super(context);
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.style = style ;
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

        paintin.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, rad, paintin);
        paintout.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, rad, paintout);


        //canvas.drawCircle(rnd.nextInt(getWidth()), rnd.nextInt(getHeight()), rnd.nextInt(getWidth() / 3 + 5), paint);
    }
}
