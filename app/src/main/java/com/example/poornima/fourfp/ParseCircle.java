package com.example.poornima.fourfp;

/**
 * Created by Poornima on 5/18/2016.
 */
import java.util.HashMap;


public class ParseCircle {

    static String input;
    static HashMap<String,Integer> hm = new HashMap<>();
    static int flag = 0;
    static int xyr[] = new int[4];
    static String error = "";

    public ParseCircle(HashMap hm1, String in){
        this.input = in;
        hm = hm1;
    }

    //public static void main(String [] args){
    public void circletest(){
        //String input ="Circle 50 a 100";
        String[] sa= input.split(" +");
        for (int i = 0; i < sa.length; i++) {
            System.out.println("sa at "+i+" = "+ sa[i]);
        }
        if(!(sa.length == 5)){
            error = "invalid circle statement";
        }


        for (int i = 1; i < 5; i++) {

            // Test if next string is a integer
            boolean isInteger = isInteger(sa[i]);
            if (isInteger) {
                System.out.println(sa[i]+" is an integer");
                xyr[i-1] = Integer.parseInt(sa[i]);
                flag++;
            } else {
                //System.out.println(sa[i]+" is not an integer");
                //error = sa[i]+" is not an integer";
                //Check hash map
                if(hm.containsKey(sa[i])){
                    //xyr[i-1]=hm.get(sa[i]);
                    xyr[i-1]=hm.get(sa[i]);
                    flag++;

                } else {
                    System.out.println("Variable not declared");
                    error = "Variable "+sa[i]+" not declared";
                }

            }



        }



        if(flag == 4){
            for (int i = 0; i < xyr.length; i++) {
                System.out.println("int at "+i+" = "+ xyr[i]);
            }
        }


    }

    public int getflag(){
        return flag;
    }

    public int[] getxyr(){
        return xyr;
    }


    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);

            // s is a valid integer

            isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
            // s is not an integer
        }

        return isValidInteger;
    }


}
