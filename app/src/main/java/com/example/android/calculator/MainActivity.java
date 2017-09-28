package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    double input1;
    double input2;
    double output;
    boolean isLong = true;
    boolean input1entered = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
