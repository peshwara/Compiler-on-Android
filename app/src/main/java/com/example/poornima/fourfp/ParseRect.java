package com.example.poornima.fourfp;

import java.util.HashMap;

/**
 * Created by Poornima on 5/18/2016.
 */


public class ParseRect {

    static String input;
    static HashMap<String,Integer> hm = new HashMap<>();
    static int flag = 0;
    static int xy[] = new int[5];

    public ParseRect(HashMap hm1, String in){
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


        for (int i = 1; i < 6; i++) {


            // Test if next string is a integer

            boolean isInteger = isInteger(sa[i]);
            if (isInteger) {
                System.out.println(sa[i]+" is an integer");
                xy[i-1] = Integer.parseInt(sa[i]);
                flag++;
            } else {
                System.out.println(sa[i]+" is not an integer");
                //Check hash map
                if(hm.containsKey(sa[i])){
                    xy[i-1]=hm.get(sa[i]);
                    flag++;

                } else {
                    System.out.println("Variable not declared");
                }

            }



        }



        if(flag == 5){
            for (int i = 0; i < xy.length; i++) {
                System.out.println("int at "+i+" = "+ xy[i]);
            }
        }


    }

    public int getflag(){
        return flag;
    }

    public int[] getxy(){
        return xy;
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
