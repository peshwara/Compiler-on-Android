package com.example.poornima.fourfp;

/**
 * Created by Poornima on 5/18/2016.
 */

import java.util.*;
import java.util.regex.*;

public class Parse {




    public Parse(String in){
        this.input1 = in.trim();
    }


    //======================================================================

    static String input1;
    static int[] retxyr = new int[4];
    static int[] retxy = new int[5];
    static String stType = "";
    static String errorMsg ="";



    static String r1 = "(\\s*)"; // For all spaces
    static String r2 = "(\\w+)"; // For all words [a-zA-Z_0-9]
    static String r3 = "\\(";
    static String r4 = "\\)";
    static String r5 = ";";
    static String r6 = "\\+";
    static String r7 = "-";
    static String r8 = "\\*";
    static String r9 = "\\/";
    static String r10 = "=";
    static String or = "|";
    static String any = ".*";

    public String getstType(){
        return stType;
    }

    public int[] getretxyr(){
        return retxyr;
    }

    public int[] getretxy(){
        return retxy;
    }

    public void resethm(){
        hm.clear();
    }

    public String getErrorMsg(){
        return errorMsg;
    }


    public static boolean parenthesisValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('[', ']');
        map.put('(', ')');
        map.put('{', '}');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (map.keySet().contains(curr)) {
                stack.push(curr);
            } else if (map.values().contains(curr)) {
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    //=================================================================

    public static int evaluate2(String exp) {
        Stack<String> ops  = new Stack<String>();
        Stack<Integer> vals = new Stack<Integer>();
        int count =0;
        while (count < exp.length()) {

            String s = String.valueOf(exp.charAt(count));
            if      (s.equals("("))               ;
            else if (s.equals("+"))    ops.push(s);
            else if (s.equals("-"))    ops.push(s);
            else if (s.equals("*"))    ops.push(s);
            else if (s.equals("/"))    ops.push(s);
            else if (s.equals(")")) {
                if(!ops.isEmpty()){
                    String op = ops.pop();
                    int v = vals.pop();
                    if      (op.equals("+"))    v = vals.pop() + v;
                    else if (op.equals("-"))    v = vals.pop() - v;
                    else if (op.equals("*"))    v = vals.pop() * v;
                    else if (op.equals("/"))    v = vals.pop() / v;
                    vals.push(v);
                }}
            else vals.push(Integer.parseInt(s));
            count++;
        }
        return (vals.pop());
    }





    //======================================================================

    // (\s*)(\w+)(\s*)((\w+)|\(|;|\+|-|\*|\/)(\s*)(=|;|(\w+))(\s*)((\w+)|;)(\s*)(;|(\w+))(\s*)(\w*)
    //public static void main(String [] args){

        public void parsestr(){
        //Parse reg = new Parse("circle x 9 9");
        //String input1;// = "x= ((3+5));";

        //		String []inputs={input1,input2};
        //String input1 =  "abdj";
        boolean parenthVal = parenthesisValid(input1);
        if (!parenthVal){
            System.out.println("Error..!!!!");
            return;
            //System.exit(0); // Give out proper error code
        }

        // For testing..!!!!!=========================

        hm.put("x", 3);
        hm.put("y", 5);


        //=============================================

        Pattern p = Pattern.compile(
                "^"+													// Start of the string
                        r1 + 													//
                        r2 + 													// int _ circle _ rectangle _ z-var    int
                        r1 + 													//
                        any);													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input1);

        if (m.matches()) {

            System.out.println("gp1: " + m.group(1));
            System.out.println("gp2: " + m.group(2));
            //firstWord = m.group(2);
            System.out.println("gp3: " + m.group(3));
        } else{
            System.out.println("No match");
        }


        // Now take group 2 , i.e the first Word
        if (m.group(2).equals("int")){
            intFunction(input1);
            stType = "int";
        }else if(m.group(2).toLowerCase().equals("circle")){
             circleFunction(input1);
            stType = "circle";
        }else if(m.group(2).toLowerCase().equals("rect")){
            rectangleFunction(input1);
            stType = "rect";
        }else{
            variableFunction(input1);
        }
        System.out.println("End of execution..!!!!");



    }

    private static void variableFunction(String input) {
        // TODO Auto-generated method stub
        Pattern p = Pattern.compile(
                "^"+													// Start of the string
                        r1 + 													//
                        r2 + 													// int _ circle _ rectangle _ z-var    int
                        r1 + 													//
                        "("+any+")");													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            System.out.println(m.group(2));
            System.out.println(m.group(4));
            if(hm.containsKey(m.group(2))){
                System.out.println("Updating value of "+m.group(2));
                intFunction2(m.group(4), m.group(2));
            }else{
                System.out.println("Error..!!!");
                errorMsg = "Error";
                return;
            }
        }else{
            System.out.println("Error..!!!");
            errorMsg = "Error";
            return;
        }

    }

    private static void rectangleFunction(String input1) {
        // TODO Auto-generated method stub
        System.out.println("Called Rectangle");


        ParseRect parseRect = new ParseRect( hm, input1);
        parseRect.circletest();
        if(parseRect.getflag()==5){
            //return parseCircle.getxyr();
            retxy = parseRect.getxy();
        }


    }

    private static void circleFunction(String input1) {
        // TODO Auto-generated method stub
        System.out.println("Called Circle");


        ParseCircle parseCircle = new ParseCircle( hm, input1);
        parseCircle.circletest();
        if(parseCircle.getflag()==4){
            //return parseCircle.getxyr();
            retxyr = parseCircle.getxyr();
        }


        //return new int[0];
    }

    static HashMap<String,Integer> hm = new HashMap<>();
    private static void intFunction(String input1) {
        // TODO Auto-generated method stub

        Pattern p = Pattern.compile(
                "^"+													// Start of the string
                        r1 + 													//
                        r2 + 													// int _  int
                        r1 + 													//
                        "("+any+")");													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input1);
        String firstWord = null;
        if (m.matches()) {

            firstWord = m.group(4);
            System.out.println("gp4: " + m.group(4));
        } else{
            System.out.println("No match");
            errorMsg = "Error";
        }
        String[] splits = firstWord.split(r5+or+r10);
        //System.out.println(splits[0]);
        //System.out.println(splits[0].trim());
        if(splits[0].trim().matches(any+r2+"(\\s+)"+r2+any)){
            System.out.println("Error..!!!");
            return;
        }

        if(hm.containsKey(splits[0])){
            System.out.println("Error..!!!");
            return;
        }else{
            hm.put(splits[0].replaceAll(" ", ""), null);
            System.out.println("Added \""+splits[0].replaceAll(" ", "")+"\" into table");
            //errorMsg = "Error";
        }
        String word = m.group(4).replaceFirst(splits[0].replaceAll(" ", ""), "");
        System.out.println(word);
        intFunction2(word.trim(),splits[0].replaceAll(" ", ""));
    }

    private static void intFunction2(String input,String LHSvariable) {
        // TODO Auto-generated method stub
        char sign = input.charAt(0);
        if(sign == '='){
            System.out.println("intFn2: "+input.replaceFirst("=", ""));
            equalsto(input.replaceFirst("=", ""),LHSvariable);
        }else if(sign == ';'){

        }else{
            System.out.println("Error..!!!");
            errorMsg = "Error";
            return;
        }



    }

    private static void equalsto(String input,String LHSvariable) {
        // TODO Auto-generated method stub
        Pattern p = Pattern.compile(
                "^"+													// Start of the string
                        r1 + 													//
                        r2 + 													// int _ circle _ rectangle _ z-var    int
                        r1 + 													//
                        "("+any+")");													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            //System.out.println(m.group(2));
        }else{
            if(input.matches(any+r3+any)){
                brackets(input,LHSvariable);
            } else
                System.out.println("Error..!!!");
            return;
        }

        if (m.group(2).matches("[0-9]+")&& m.group(4).matches(r1+r5+r1)){
            //System.out.println("numbers only");
            hm.put(LHSvariable, Integer.valueOf(m.group(2)));

        } else{
            System.out.println("variable calculation");
            varCalc(input,LHSvariable,0);
        }

    }

    private static void brackets(String input, String LHSvariable) {
        // TODO Auto-generated method stub

        System.out.println("Brackets");

        // Getting a Set of Key-value pairs
        Set entrySet = hm.entrySet();

        // Obtaining an iterator for the entry set
        Iterator it = entrySet.iterator();

        // Iterate through HashMap entries(Key-Value pairs)
        System.out.println("HashMap Key-Value Pairs : ");
        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            input = input.replaceAll(String.valueOf(me.getKey()), String.valueOf(me.getValue()));

        }
        System.out.println("Input is: "+input);

        Pattern p = Pattern.compile(
                "("+any+")"+													// Start of the string
                        "("+r5 + 													//
                        "+)" 													//
        );													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            String finalExp = m.group(1);
            //	finalExp=finalExp.replaceAll("\\(", " \\( ");
            finalExp=finalExp.replaceAll(" ", "");
            finalExp = "("+finalExp+")";
            int LHSInt = evaluate2(finalExp);
            System.out.println(LHSInt);
            hm.put(LHSvariable, LHSInt);

        }else{
            System.out.println("Error..!!!");
            return;
        }


    }

    private static void varCalc(String input, String LHSvariable,int result) {
        // TODO Auto-generated method stub
        System.out.println("Input is: "+input);
        System.out.println("LHS: "+LHSvariable);
        System.out.println("res: "+result);

        // Getting a Set of Key-value pairs
        Set entrySet = hm.entrySet();

        // Obtaining an iterator for the entry set
        Iterator it = entrySet.iterator();

        // Iterate through HashMap entries(Key-Value pairs)
        System.out.println("HashMap Key-Value Pairs : ");
        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            input = input.replaceAll(String.valueOf(me.getKey()), String.valueOf(me.getValue()));

        }
        System.out.println("Input is: "+input);

        Pattern p = Pattern.compile(
                "("+any+")"+													// Start of the string
                        "("+r5 + 													//
                        "+)" 													//
        );													//Any match later on
        System.out.println(p);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            System.out.println(m.group(1));
            //System.out.println(m.group(2));

            //System.out.println(evaluate(m.group(1).replace(" ", "")));
            String finalExp = m.group(1);
            //finalExp=finalExp.replaceAll("\\(", " \\( ");
            finalExp=finalExp.replaceAll(" ", "");
            finalExp = "("+finalExp+")";
            System.out.println("finalINt:"+finalExp);
            int LHSInt = evaluate2(finalExp);
            System.out.println(LHSInt);
            hm.put(LHSvariable, LHSInt);
        }else{
            System.out.println("Error..!!!");
            return;
        }

    }



}
