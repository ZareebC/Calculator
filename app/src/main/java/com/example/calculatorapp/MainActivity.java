package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button bAdd;
    Button bSub;
    Button bMult;
    Button bDiv;
    Button bClear;
    Button bEqual;
    Button bExpo;
    Button bCos;
    Button bSin;
    Button bTan;
    Button bDeg;
    Button bBack;
    HorizontalScrollView scroll;
    TextView output;
    String temp;
    String delim = "+*/-^°";
    String nums;
    double newNum;
    double rad;
    int count;
    int test;
    double test2;
    ArrayList<String> list;
    ArrayList<String> newList;




        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bAdd = findViewById(R.id.buttonPlus);
        bSub = findViewById(R.id.buttonSub);
        bMult = findViewById(R.id.buttonMult);
        bDiv = findViewById(R.id.buttonDiv);
        bClear = findViewById(R.id.buttonC);
        bEqual = findViewById(R.id.buttonE);
        bExpo = findViewById(R.id.buttonExpo);
        bCos = findViewById(R.id.buttonCOS);
        bSin = findViewById(R.id.buttonSIN);
        bTan = findViewById(R.id.buttonTAN);
        bDeg = findViewById(R.id.buttonDegree);
        bBack = findViewById(R.id.buttonBack);
        output = findViewById(R.id.textView2);
        output.setEnabled(true);
        scroll = findViewById(R.id.SCROLLER_ID);
        temp = "";
        list = new ArrayList<>();
        newList = new ArrayList<>();
        nums = "0123456789";
        count = 0;
        test = 0;
        test2 = 0;

        //Log.d("Test", (String) b0.getText());
    }

        public void onClick (View v) {
            try {
                if (!((Button) v).getText().equals("C") && !((Button) v).getText().equals("=") && !((Button) v).getText().equals("Del")) {
                    if(newList.size() > 0 && nums.contains(((Button) v).getText()) && temp.equals(newList.get(0))){
                        String inputR = (String)((Button)v).getText();
                        temp = inputR;
                        output.setText(temp);
                        newList.clear();
                    }
                    String input = (String) ((Button) v).getText();
                    if(input.equals("cos") && isNumeric(temp)){
                        temp = "cos"+temp+"°";
                        output.setText(temp);
                        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }
                    else if(input.equals("sin") && isNumeric(temp)){
                        temp = "sin"+temp+"°";
                        output.setText(temp);
                        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }
                    else if(input.equals("tan") && isNumeric(temp)){
                        temp = "tan"+temp+"°";
                        output.setText(temp);
                        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }
                    else {
                        temp += input;
                        output.setText(temp);
                        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }

                }
                if(temp.length()>0) {
                    if (((Button) v).getText().equals("Del")) {
                        if(temp.length()<3){
                            temp = temp.substring(0, temp.length() - 1);
                            output.setText(temp);
                            scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                        }

                            else if (!(temp.substring(temp.length() - 3).equals("cos")) && !(temp.substring(temp.length() - 3).equals("sin")) && !(temp.substring(temp.length() - 3).equals("tan"))) {
                                temp = temp.substring(0, temp.length() - 1);
                                output.setText(temp);
                                scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                            }

                        else {
                                temp = temp.substring(0, temp.length() - 3);
                                output.setText(temp);
                                scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                        }
                    }
                }
                if (((Button) v).getText().equals("C")) {
                    System.out.println("test");
                    temp = "";
                    list.clear();
                    output.setText(temp);
                    scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
                if (((Button) v).getText().equals("=")) {

                    StringTokenizer token = new StringTokenizer(temp, delim, true);
                    while (token.hasMoreTokens()) {
                        String num = token.nextToken();
                        list.add(num);
                        System.out.print(num);
                    }
                    while (list.size() > 1) {
                        for(int check = 0; check < list.size()-1; check++){
                            if(delim.contains(list.get(check)) && delim.contains(list.get(check+1))){
                                output.setText("Error");
                            }
                        }
                        for(int i = 0; i < list.size(); i++){
                            if(list.get(i).contains("cos")){
                                rad = Math.toRadians((Double.parseDouble(list.get(i).substring(3))));
                                newNum = Math.cos(rad);
                                list.add(i, "" +newNum);
                                list.subList(i+1, i+3).clear();
                            }
                            else if(list.get(i).contains("sin")){
                                rad = Math.toRadians((Double.parseDouble(list.get(i).substring(3))));
                                newNum = Math.sin(rad);
                                list.add(i, "" +newNum);
                                list.subList(i+1, i+3).clear();
                            }
                            else if(list.get(i).contains("tan")){
                                if((Double.parseDouble(list.get(i).substring(3))) != ((Math.PI)/2) && (Double.parseDouble(list.get(i).substring(3))) != ((Math.PI)/2)) {
                                    rad = Math.toRadians((Double.parseDouble(list.get(i).substring(3))));
                                    newNum = Math.tan(rad);
                                    list.add(i, "" + newNum);
                                    list.subList(i + 1, i + 3).clear();
                                }
                            }
                        }

                        for(int i = 0; i < list.size(); i++){
                            if(list.get(i).equals("^")){
                                newNum = Math.pow((Double.parseDouble(list.get(i - 1))), (Double.parseDouble(list.get(i + 1))));
                                list.add(i - 1, "" + newNum);
                                list.subList(i, i + 3).clear();
                            }
                        }
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).equals("/") || list.get(i).equals("*")) {
                                if (list.get(i).equals("/")) {
                                    if(list.get(i+1).equals("0")) {
                                        output.setText("Error");
                                        list.clear();
                                    }
                                    else{
                                    newNum = (Double.parseDouble(list.get(i - 1))) / (Double.parseDouble(list.get(i + 1)));
                                    list.add(i - 1, "" + newNum);
                                    list.subList(i, i + 3).clear();
                                    if (list.size() > 1)
                                        i = 0;
                                    }
                                }
                                else if (list.get(i).equals("*")) {
                                    newNum = (Double.parseDouble(list.get(i - 1))) * (Double.parseDouble(list.get(i + 1)));
                                    list.add(i - 1, "" + newNum);
                                    list.subList(i, i + 3).clear();
                                    if (list.size() > 1)
                                        i = 0;
                                }
                            }
                        }
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).equals("+") || list.get(i).equals("-")) {
                                if (list.get(i).equals("+")) {
                                    newNum = (Double.parseDouble(list.get(i - 1))) + (Double.parseDouble(list.get(i + 1)));
                                    list.add(i - 1, "" + newNum);
                                    list.subList(i, i + 3).clear();
                                    if (list.size() > 1)
                                        i = 0;
                                } else if (list.get(i).equals("-")) {
                                    //if(nums.contains(list.get(i-1))) {
                                        newNum = (Double.parseDouble(list.get(i - 1))) - (Double.parseDouble(list.get(i + 1)));
                                        list.add(i - 1, "" + newNum);
                                        list.subList(i, i + 3).clear();
                                        if (list.size() > 1)
                                            i = 0;
                                    //}
                                    /*else{
                                        newNum =  -(Double.parseDouble(list.get(i + 1)));
                                        list.set(i, "" + newNum);
                                        list.subList(i+1, i+2).clear();
                                        if(list.size()>1)
                                            i=0;
                                    }*/
                                }
                            }
                        }

                    }
                    /*test = Integer.parseInt(temp);
                    test2 = Double.parseDouble(temp);
                    if(test == test2) {
                        output.setText(test);
                        newList.add(temp);
                        list.clear();
                        count++;
                    }
                    else{ */
                        temp = list.get(0);
                        output.setText(list.get(0));
                        newList.add(temp);
                        list.clear();
                        count++;
                    //}
                }

            } catch (Exception e) {
                output.setText("Error");
            }
        }
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

