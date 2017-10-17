package com.example.android.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    double number1;
    double number2;
    boolean input1entered = false;
    char operation;
    @BindView(R.id.input3)
    TextView input3;
    @BindView(R.id.input2)
    TextView input2;
    @BindView(R.id.input1)
    TextView input1;
    TextView[] number = new TextView[10];
    boolean isOperationEntered;
    @BindView(R.id.sign1)
    TextView sign1;
    @BindView(R.id.sign2)
    TextView sign2;

    @OnClick(R.id.AC)
    public void reset() {
        input1.setText("");
        input2.setText("");
        input3.setText("0");
        input1entered = false;
        isOperationEntered = false;
        sign2.setText("");
        sign1.setText("");
    }

    @OnClick(R.id.backspace)
    public void backspace() {
        if (input1entered) {
            if (input2.getText().toString().length() == 1)
                input2.setText("");
            else if (input2.getText().toString().length() > 1)
                input2.setText(input2.getText().toString().substring(0, input2.getText().toString().length() - 1));
        } else if (isOperationEntered) {
            isOperationEntered = false;
            sign2.setText("");
        } else {
            if (input3.getText().length() == 1)
                input3.setText("0");
            else
                input3.setText(input3.getText().toString().substring(0, input3.getText().toString().length() - 1));
        }
    }

    @OnClick(R.id.dot)
    public void dot() {
        if (input1entered) {
            if (input2.getText().toString().length() == 0)
                input2.setText("0.");
            else {
                double num = getValue(input2);
                if (num == (int) num)
                    input2.setText(input2.getText() + ".");
            }
            setAnswer();
        } else {
            if (input3.getText().toString().length() == 0)
                input3.setText("0.");
            else {
                double num = getValue(input3);
                if (num == (int) num)
                    input3.setText(input3.getText() + ".");
            }
        }
    }

    private double getValue(TextView view) {
        double num = 0;
        try {
            num = Double.parseDouble(view.getText().toString());
        } catch (NumberFormatException e) {

        }
        return num;
    }

    @OnClick(R.id.percentage)
    public void percentage() {
        if (input1entered) {
            input2.setText(getValue(input2) / 100 + "");
            setAnswer();
        } else
            input3.setText(getValue(input3) / 100 + "");
    }

    @OnClick(R.id.plus)
    public void plus() {
        operation = '+';
        sign2.setText("+");
        isOperationEntered = true;
    }

    @OnClick(R.id.minus)
    public void minus() {
        operation = '-';
        sign2.setText("-");
        isOperationEntered = true;
    }

    @OnClick(R.id.multiply)
    public void multiply() {
        operation = '*';
        sign2.setText("*");
        isOperationEntered = true;
    }

    @OnClick(R.id.divide)
    public void divide() {
        operation = '/';
        sign2.setText("/");
        isOperationEntered = true;
    }

    @OnClick(R.id.equal)
    public void equal() {
        if (input1entered)
            setAnswer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        for (int i = 0; i < 10; i++) {
            final int I = i;
            number[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (input1entered) {
                        input2.setText(input2.getText().toString() + I);
                        setAnswer();
                    } else if (isOperationEntered) {
                        input1.setText(input3.getText().toString());
                        input2.setText("" + I);
                        setAnswer();
                        input1entered = true;
                        sign1.setText(sign2.getText());
                        sign2.setText("=");
                    } else {
                        if (input3.getText().toString().equals("0"))
                            input3.setText("" + I);
                        else
                            input3.setText(input3.getText().toString() + I);

                    }
                }
            });
        }
    }

    private void setAnswer() {
        number1 = getValue(input1);
        number2 = getValue(input2);
        double ans = getAnswer();
        if (ans == (int) ans)
            input3.setText((int) ans + "");
        else
            input3.setText(ans + "");
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
