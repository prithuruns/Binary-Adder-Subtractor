package com.bunkman.prithvi.binaryadderandsubtractor_working;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import java.util.Arrays;


public class MainActivity extends Activity implements OnClickListener { //don't forget to add unimplemented methods when you implement something

    Button plus;
    Button minus;
    Button one;
    Button zero;
    Button equals;
    Button refresh;
    Button delete;
    TextView answer;
    TextView topBox;
    TextView bottomBox;

    String s;
    String t;
    //char char1[]=new char[100];
    //char char2[]=new char[100];
    long i1 = 0;    //integer 1
    long i2 = 0;    //integer 2
    int i1Length = 0;
    int i2Length = 0;
    int plusMinus = 0;    //plus=0, minus=1
    int beginningOfText = 1; //whether plus or minus has been used
    int equalSet = 1;    //"equal to" check
    int l;        // counter
    int temp1;
    int initialEqual = 0;
    int numb1[] = new int[20];       //number 1
    int numb2[] = new int[20];       //number 2
    void deleteElements(int numb[]) {
        Arrays.fill(numb, '\0');
        numb[0] = 0;
    }

    int[] convertToArr(long numb, int length) {
        int i = 0;
        int numb3[] = new int[20];       //number 1
        while (numb != 0) {
            temp1 = (int) (numb % 10);
            numb = numb / 10;
            numb3[i] = temp1;
            i++;
        }
        for (; i < length; i++) {
            numb3[i] = 0;
        }
        return numb3;
    }

    int alignNumber(TextView tb) {
        int y = (tb.getText().toString().length() % 5);
        if (y != 0) {
            for (int i = 0; i < 4 - y; i++) {
                s = tb.getText().toString();
                tb.setText("0" + s);
            }
        }
        return tb.getText().toString().length() - tb.getText().toString().length() / 5;
    }

    int[] arraySetTextReduce(int[] numb) {
        for (int u = 0; u < 3; u++) {
            int y = 0;
            for (int q = 0; q < 4; q++) {
                if (numb[i1Length - q - 1] == 0) {
                    y++;
                }
            }
            if ((y == 4) && (i1Length != 4)) {
                i1Length = i1Length - 4;
            }
        }
        return numb;
    }

    void deletion(TextView tb) {
        int l;
        t = tb.getText().toString();
        t = t.replace(" ", "");
        l = t.length();
        if (l > 1) {
            l = l - 1;
            t = t.substring(0, l);
            tb.setText(t);
            spaceCheck(tb);
        } else if (l == 1) {
            tb.setText(String.valueOf(0));
            beginningOfText = 1;
        }
    }

    void initialise() {
        i1 = 0;
        topBox.setText(Long.toBinaryString(i1));
        i2 = 0;
        bottomBox.setText(Long.toBinaryString(i2));
        i1Length = 0;
        i2Length = 0;
        deleteElements(numb1);
        deleteElements(numb2);
        equalSet = 1;
        beginningOfText = 1;
        plusMinus = 0;
        temp1 = 0;
        answer.setText(Long.toBinaryString(i1));
        initialEqual = 0;
    }

    int[] add(int[] a, int[] b) {
        int i;
        int carry = 0;
        for (i = 0; i < i1Length; i++) {
            a[i] = a[i] + b[i] + carry;
            switch (a[i]) {
                case 0:
                    carry = 0;
                    break;
                case 1:
                    carry = 0;
                    break;
                case 2:
                    carry = 1;
                    a[i] = 0;
                    break;
                case 3:
                    carry = 1;
                    a[i] = 1;
                    break;
            }
        }
        if (plusMinus == 0) {
            if (carry == 1) {
                if (i1Length == 16) {
                    topBox.setText("MaTh ErRoR");
                    bottomBox.setText("MaTh ErRoR");
                    answer.setText("MaTh ErRoR");

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 2s = 2000ms
                            initialise();
                        }
                    }, 2500);
                } else {
                    i1Length = i1Length + 4;
                    a[i] = 1;
                }
            }
        }
        return a;
    }

    int[] twoSComp(int[] numb) {
        int i;
        for (i = 0; i < i1Length; i++) {
            if (numb[i] == 1) {
                numb[i] = 0;
            } else if (numb[i] == 0) {
                numb[i] = 1;
            }
        }
        int carry = 1;
        for (i = 0; i < i1Length; i++) {
            numb[i] = numb[i] + carry;
            switch (numb[i]) {
                case 0:
                    carry = 0;
                    break;
                case 1:
                    carry = 0;
                    break;
                case 2:
                    carry = 1;
                    numb[i] = 0;
                    break;

            }
        }
        return numb;
    }

    void mathError(TextView box) {
        if (box.getText().toString().length() > 19) {

            topBox.setText("MaTh ErRoR");
            bottomBox.setText("MaTh ErRoR");
            answer.setText("MaTh ErRoR");

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 2.5s = 2500ms
                    initialise();
                }
            }, 2500);
        }
    }

    void spaceCheck(TextView box) {
        String y;
        String y1;
        t = box.getText().toString();
        t = t.replace(" ", "");
        int tLength = t.length();
        if (tLength >= 5) {
            y = t.substring(0, tLength - 4);
            y = y.concat(" ");
            y = y.concat(t.substring(tLength - 4, tLength));
            tLength++;

            if (tLength >= 10) {
                y1 = y;
                y = y.substring(0, tLength - 9);
                y = y.concat(" ");
                y = y.concat(y1.substring(tLength - 9, tLength));
                tLength++;

                if (tLength >= 15) {
                    y1 = y;
                    y = y.substring(0, tLength - 14);
                    y = y.concat(" ");
                    y = y.concat(y1.substring(tLength - 14, tLength));

                }
            }
            box.setText(y);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // set content view sets the view

        new SimpleEULA(this).show();

        answer = (TextView) findViewById(R.id.answer);
        topBox = (TextView) findViewById(R.id.topBox);
        bottomBox = (TextView) findViewById(R.id.bottomBox);
        plus = (Button) findViewById(R.id.plus);    // identified the button, now need to set a listener to do something when the button is clicked
        plus.setOnClickListener(this);
        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(this);
        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this);
        equals = (Button) findViewById(R.id.equal);
        equals.setOnClickListener(this);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);
        initialise();

    }

    @Override
    public void onClick(View butt) {

        int id = butt.getId();
        if (id == R.id.plus) {

            if (initialEqual == 1) {
                topBox.setText(answer.getText().toString());
            }
            plusMinus = 0;
            beginningOfText = 1;
            equalSet = 0;

        } else if (id == R.id.minus) {
            if (initialEqual == 1) {
                topBox.setText(answer.getText().toString());
            }
            plusMinus = 1;
            beginningOfText = 1;
            equalSet = 0;

        } else if (id == R.id.one) {
            if (equalSet == 1) {
                if (beginningOfText == 0) {
                    s = topBox.getText().toString();
                    topBox.setText(s + "1");
                } else {
                    beginningOfText = 0;
                    topBox.setText("1");
                }
                spaceCheck(topBox);
                mathError(topBox);

            } else {
                if (beginningOfText == 0) {
                    s = bottomBox.getText().toString();
                    bottomBox.setText(s + "1");
                } else {
                    beginningOfText = 0;
                    bottomBox.setText("1");
                }
                spaceCheck(bottomBox);
                mathError(bottomBox);
            }
            initialEqual = 0;
        } else if (id == R.id.zero) {
            if (equalSet == 1) {
                if (beginningOfText == 0) {
                    s = topBox.getText().toString();
                    topBox.setText(s + "0");

                } else {
                    beginningOfText = 0;
                    topBox.setText("0");
                }
                spaceCheck(topBox);
                mathError(topBox);
            } else {
                if (beginningOfText == 0) {
                    s = bottomBox.getText().toString();
                    bottomBox.setText(s + "0");

                } else {
                    beginningOfText = 0;
                    bottomBox.setText("0");
                }
                spaceCheck(bottomBox);
                mathError(bottomBox);
            }
            initialEqual = 0;
        } else if (id == R.id.equal) {
            if (initialEqual == 1) {
                topBox.setText(answer.getText().toString());

            }
            initialEqual = 1;
            equalSet = 1;

            i1Length = alignNumber(topBox);
            t = topBox.getText().toString();
            t = t.replace(" ", "");
            i1 = Long.parseLong(t);
            numb1 = convertToArr(i1, i1Length);

            i2Length = alignNumber(bottomBox);
            t = (bottomBox.getText().toString());
            t = t.replace(" ", "");
            i2 = Long.parseLong(t);
            numb2 = convertToArr(i2, i2Length);

            if (i1Length != i2Length) {
                while (i2Length > i1Length) {
                    for (l = 0; l < 4; l++) {
                        numb1[i1Length++] = 0;
                    }
                }

                while (i1Length > i2Length) {
                    for (l = 0; l < 4; l++) {
                        numb2[i2Length++] = 0;
                    }
                }
            }

            answer.setText("");
            switch (plusMinus) {
                case 0:
                    numb1 = add(numb1, numb2);
                    for (int m = 0; m < i1Length; m = m + 4) {          //to set the output..

                        for (int l = 0; l < 4; l++) {

                            t = answer.getText().toString();
                            answer.setText(String.valueOf(numb1[m + l]) + t);
                        }
                        t = answer.getText().toString();
                        answer.setText(" " + t);
                    }
                    break;

                case 1:
                    if (i1 > i2) {
                        numb2 = twoSComp(numb2);
                        numb1 = add(numb1, numb2);
                        numb1 = arraySetTextReduce(numb1);
                        for (int m = 0; m < i1Length; m = m + 4) {          //to set the output..


                            for (int l = 0; l < 4; l++) {

                                t = answer.getText().toString();
                                answer.setText(String.valueOf(numb1[m + l]) + t);
                            }
                            t = answer.getText().toString();
                            answer.setText(" " + t);
                        }

                    } else if (i1 < i2) {
                        numb1 = twoSComp(numb1);
                        numb1 = add(numb1, numb2);
                        numb1 = arraySetTextReduce(numb1);

                        numb1[i1Length] = 1;

                        for (int m = 0; m < i1Length + 1; m = m + 4)      //to set the output..
                        {
                            for (int l = 0; l < 4; l++) {
                                t = answer.getText().toString();
                                answer.setText(String.valueOf(numb1[m + l]) + t);
                            }
                            t = answer.getText().toString();
                            answer.setText(" " + t);
                        }
                    } else if (i1 == i2) {
                        t = Integer.toString(0);
                        answer.setText(t);
                    }
                    break;
            }
            beginningOfText = 1;
        } else if (id == R.id.refresh) {
            initialise();
        } else if (id == R.id.delete) {
            if (equalSet == 1) {

                deletion(topBox);
            } else {
                deletion(bottomBox);
            }
        }
    }
}