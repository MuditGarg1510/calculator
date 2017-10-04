package com.example.android.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    double number1;
    double number2;
    @BindView(R.id.dot)
    TextView dot;
    boolean input1entered = false;
    char operation;
    @BindView(R.id.input3)
    TextView input3;
    @BindView(R.id.input2)
    TextView input2;
    @BindView(R.id.input1)
    TextView input1;
    TextView[] number = new TextView[10];
    ImageView backspace;
    TextView reset;
    TextView divide;
    ImageView multiply;
    TextView minus;
    TextView plus;
    TextView equalTo;
    boolean isOperationEntered;
    TextView sign1;
    TextView sign2;
    @OnClick(R.id.AC)
    public void reset(){
        input1.setText("");
        input2.setText("");
        input3.setText("");
    }
    @OnClick(R.id.backspace)
    public void backspace() {
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

    @OnClick(R.id.dot)
    public void dot() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
    }

    private void setAnswer() {
        try {
            number1 = Double.parseDouble(input1.getText().toString());
            number2 = Double.parseDouble(input2.getText().toString());
        } catch (NumberFormatException e) {
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
