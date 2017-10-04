package com.example.android.calculator;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double number1;
    double number2;
    boolean input1entered = false;
    char operation;
    TextView[] number = new TextView[10];
    ImageView backspace;
    TextView reset;
    TextView divide;
    ImageView multiply;
    TextView minus;
    TextView plus;
    TextView equalTo;
    TextView input3;
    TextView input2;
    TextView input1;
    boolean isOperationEntered;
    TextView dot;
    TextView sign1;
    TextView sign2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = (TextView) findViewById(R.id.dot);
        sign1 = (TextView) findViewById(R.id.sign1);
        sign2 = (TextView) findViewById(R.id.sign2);
        number[0] = (TextView) findViewById(R.id.number0);
        number[1] = (TextView) findViewById(R.id.number1);
        number[2] = (TextView) findViewById(R.id.number2);
        number[3] = (TextView) findViewById(R.id.number3);
        number[4] = (TextView) findViewById(R.id.number4);
        number[5] = (TextView) findViewById(R.id.number5);
        number[6] = (TextView) findViewById(R.id.number6);
        number[7] = (TextView) findViewById(R.id.number7);
        number[8] = (TextView) findViewById(R.id.number8);
        number[9] = (TextView) findViewById(R.id.number9);
        input3 = (TextView) findViewById(R.id.input3);
        input2 = (TextView) findViewById(R.id.input2);
        input1 = (TextView) findViewById(R.id.input1);
        backspace = (ImageView) findViewById(R.id.backspace);
        for (int i = 0; i < 10; i++) {
            final int I = i;
            number[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!input1entered)
                        input3.setText(input3.getText().toString() + I);
                    else {
                        input2.setText(input2.getText().toString() + I);
                        setAnswer();
                    }
                }
            });
        }
        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.minus);
        multiply = (ImageView) findViewById(R.id.multiply);
        equalTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1entered)
                    setAnswer();
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = '/';
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = '*';
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = '+';
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = '-';
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1entered) {
                    if (input2.getText().toString().length() == 0)
                        input2.setText("0.");
                    else
                        input2.setText(input2.getText() + ".");
                    setAnswer();
                } else {
                    if (input3.getText().toString().length() == 0)
                        input3.setText("0.");
                    else
                        input3.setText(input3.getText() + ".");
                }
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1entered) {
                    if (input2.getText().toString().length() == 1)
                        input2.setText("");
                    else if (input2.getText().toString().length() > 1)
                        input2.setText(input2.getText().toString().substring(0, input2.getText().toString().length() - 1));
                } else if (isOperationEntered) {
                    if (input3.getText().toString().length() == 1)
                        input3.setText("");
                    else if (input3.getText().toString().length() > 1)
                        input3.setText(input3.getText().toString().substring(0, input3.getText().toString().length() - 1));
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input1.setText("");
                input2.setText("");
                input3.setText("");

            }
        });


    }

    private void setAnswer() {
        try {
            number1 = Double.parseDouble(input1.getText().toString());
            number2 = Double.parseDouble(input2.getText().toString());
        } catch (NumberFormatException) {
            input3.setText("ERR");
            return;
        }
        input3.setText(getAnswer() + "");
    }

    private double getAnswer() {
        if (operation == '+')
            return number1 + number2;
        if (operation == '-')
            return number1 - number2;
        if (operation == '*')
            return number1 * number2;
        if (operation == '/')
            return number1 / number2;
        else return 0;
    }
}
