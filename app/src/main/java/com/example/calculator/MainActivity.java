package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int a, b;
    private boolean isOperationClick;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);
    }

    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if(text.equals("AC")){
            textView.setText("0");
            a = 0;
            b = 0;
            operation = "";
        }else if(textView.getText().toString().equals("0") || isOperationClick){
            textView.setText(text);
        }else{
            textView.append(text);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_minus) {
            a = Integer.parseInt(textView.getText().toString());
            operation = "-";
        } else if (view.getId() == R.id.btn_plus) {
            a = Integer.parseInt(textView.getText().toString());
            operation = "+";
        } else if (view.getId() == R.id.btn_multiplicationt) {
            a = Integer.parseInt(textView.getText().toString());
            operation = "*";
        } else if (view.getId() == R.id.btn_division) {
            a = Integer.parseInt(textView.getText().toString());
            operation = "/";
        }

        if (view.getId() == R.id.btn_equal) {
            b = Integer.parseInt(textView.getText().toString());
            int result = 0;

            if (operation.equals("-")) {
                result = a - b;
            } else if (operation.equals("+")) {
                result = a + b;
            } else if (operation.equals("*")) {
                result = a * b;
            } else if (operation.equals("/")) {
                if (b != 0) {
                    result = a / b;
                } else {
                    textView.setText("Error");
                    return;
                }
            } else {
                textView.setText("Error");
                return;
            }
            textView.setText(String.valueOf(result));
        }
        isOperationClick = true;
    }
}