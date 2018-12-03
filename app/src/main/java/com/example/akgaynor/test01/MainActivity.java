package com.example.akgaynor.test01;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String button_value = "";
    private String previousButton = "";
    private String inputDisplay = "";
    private String inputValue = "";
    private TextView inputTextView;
    private TextView outputTextView;
    private ArrayList<String> inputSequence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        else{
            View v = getWindow().getDecorView();

            // Hide the status bar
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            v.setSystemUiVisibility(uiOptions);
        }

        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.textview_input);
        outputTextView = findViewById(R.id.textview_output);
    }

    public void onNumberClick(View v){

        switch (v.getId()){
            case R.id.button_0:
                button_value = "0";
                break;

            case R.id.button_1:
                button_value = "1";
                break;

            case R.id.button_2:
                button_value = "2";
                break;

            case R.id.button_3:
                button_value = "3";
                break;

            case R.id.button_4:
                button_value = "4";
                break;

            case R.id.button_5:
                button_value = "5";
                break;

            case R.id.button_6:
                button_value = "6";
                break;

            case R.id.button_7:
                button_value = "7";
                break;

            case R.id.button_8:
                button_value = "8";
                break;

            case R.id.button_9:
                button_value = "9";
                break;

            case R.id.button_plus:
                if (inputTextView.getText().toString() != "" && inputSequence != null && inputTextView.getText().toString() != null)
                    button_value = "+";
                break;

            case R.id.button_equals:
                if (inputTextView.getText().toString() != "")
                    button_value = "=";

                if (!inputSequence.isEmpty() && inputSequence != null && previousButton != "+"){
                    button_value = "=";
                    inputSequence.add(inputValue);
                    calculate();
                    inputValue = "";
                }
                break;
        }

        if (previousButton == "+" && button_value == "+") {
            inputTextView.setText("");
            outputTextView.setText("");
            previousButton = "";
            inputDisplay = "";
            inputValue = "";
            inputSequence.clear();
        }
        else {
            if (button_value != "=" && button_value != ""){
                previousButton = button_value;

                inputDisplay += button_value;

                if (button_value != "+"){
                    inputValue += button_value;
                }
                else {
                    inputSequence.add(inputValue);
                    inputValue = "";
                }

                inputTextView.setText(inputDisplay);
            }
        }
        button_value = "";
    }

    public void calculate(){
        int sum = 0;

        for (String number : inputSequence){
            sum += Integer.parseInt(number);
        }

        outputTextView.setText(String.valueOf(sum));
        inputDisplay = "";
        inputSequence.clear();
    }
}