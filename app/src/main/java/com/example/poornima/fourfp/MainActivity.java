package com.example.poornima.fourfp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public String syntax;
    static int[] xyr = new int[4];
    static int[] xy = new int[5];
    public String errormsg;
    static int reset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Button run = (Button) findViewById(R.id.run);
        final Button clear = (Button) findViewById(R.id.clear);
        final EditText input = (EditText) findViewById(R.id.input);
        final RelativeLayout r1 = (RelativeLayout) findViewById(R.id.drawspace);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == run) {



                    textView.setText("");
                    syntax = input.getText().toString();
                    if (syntax.contains("#")) {


                        syntax = syntax.substring(0, syntax.indexOf("#"));
                    }
                    if (!(syntax.endsWith(";"))) {
                        System.out.println("error");
                        errormsg = "terminator ; missing ";
                        textView.setText("terminator ; missing!!");
                    } else{
                        syntax = syntax.replace(";", "");


                    //r1.removeAllViews();
                    //parse.sethm();

                    Parse parse = new Parse(syntax);
                        if(reset==1){
                            parse.resethm();
                            reset=0;
                        }

                    parse.parsestr();
                    if (parse.getstType() == "circle") {
                        xyr = parse.getretxyr();
                        Circle circle = new Circle(v.getContext(), xyr[0], xyr[1], xyr[2], xyr[3]);

                        //Circle circle = new Circle(v.getContext(), 100, 100, 50);
                        r1.addView(circle);
                    }

                    if (parse.getstType() == "rect") {
                        xy = parse.getretxy();
                        Rectangle rect = new Rectangle(v.getContext(), xy[0], xy[1], xy[2], xy[3], xy[4]);
                        r1.addView(rect);
                    }
                    //Rectangle rect = new Rectangle(v.getContext(), 100, 150, 180, 190);
                    //r1.addView(rect);
                        errormsg = parse.getErrorMsg();


                }


                }

                //if(v.clear)
            }


        });

        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                r1.removeAllViews();
                reset = 1;




            }
        });






    }

    public void DrawCircle(String circle){
    //    Circle circle = new Circle(v.getContext());

  //      Shape shape = shapeFactory.getShape(v.getContext(), "STAR");
//        r1.addView(shape);

    }





}
