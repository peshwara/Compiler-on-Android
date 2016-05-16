package com.example.kartikbholla.fourfp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Scanner;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public String syntax; //input string will be taken here
   // Scanner sc = new Scanner(System.in);
    //int i = sc.nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null)
            actionBar.hide();

        final Button run = (Button) findViewById(R.id.run);
        final EditText input = (EditText) findViewById(R.id.input);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == run) {
                    syntax = input.getText().toString();
                    //syntax.length();
                    Parse(syntax, syntax.length());
                }
            }


        });


        //int i = Integer.parseInt(myText.getText())

    }
    public static void Parse(String syntax, int strlen){
        //int index;
        String lexem = null;
        char letter;
        //int newIndex;
        int count = 0;
        //boolean space = false;
        String input = syntax;
        Scanner s = new Scanner(input).useDelimiter("\\s* \\s*");
        System.out.println(s.next());
        System.out.println(s.next());
        System.out.println(s.next());
        System.out.println(s.next());
        s.close();

/*
       for (int j=0; j<= strlen; j++){
            if(syntax.charAt(j) == ' '||syntax.charAt(j) == '\t'){
                count ++;
                if (count == 1){
                    vectorize(lexem);
                }

                // space = true;
            }

            else if(!(syntax.charAt(j) == ' '||syntax.charAt(j) == '\t')) {
                letter = syntax.charAt(j);
                lexem = lexem + letter;
                count = 0;

                //space = false;
                // if(space == false);
                // space=0;
                // index = j;// non space character index
                // newIndex = ParseV2(syntax, strlen, index);
            }
            else if (syntax.charAt(j) == '#'){
                do {
                    j++;
                }while(syntax.charAt(j) == '\n');
            }
        }

    }   // end of parse
    public static void vectorize(String lexem){
        Vector<String> lexemVector = null;
        lexemVector.add(lexem);*/
    }   // end of vectorize


}
