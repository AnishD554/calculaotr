package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultDisplay;

    private String currentInput= " ";

    private String operator = " ";

    private double firstOperand = Double.NaN;

    private double secondOperand;

    private Button btnClear,btnDivide, btnMultiply, btnDelete, btnMinus, btnPlus, btnEquals, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btnDecimal;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultDisplay = findViewById(R.id.resultDisplay);

        setNumberButtonListeners();
        setOperatorButtonListeners();

        btnClear = findViewById(R.id.btnClear);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDelete = findViewById(R.id.btnDelete);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnEquals = findViewById(R.id.btnEquals);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDecimal = findViewById(R.id.btnDecimal);
    }


        ///ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        /// Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        /// v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        /// return insets;
///        });









        private void setNumberButtonListeners() {
            int[] numberButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                    R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                    R.id.btn8, R.id.btn9, R.id.btnDecimal
            };

            View.OnClickListener listener = view -> {
                Button button = (Button) view;
                currentInput += button.getText().toString();
                resultDisplay.setText(currentInput);

            };


            for ( int id: numberButtonIds){
                findViewById(id). setOnClickListener(listener);
            }
        }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btnPlus).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.btnDivide).setOnClickListener(view -> setOperator("/"));
        findViewById(R.id.btnEquals).setOnClickListener(view -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(view -> clearInput());
        findViewById(R.id.btnDelete).setOnClickListener(view -> backspaceInput());
    }

    private void setOperator(String operator) {
        if (!Double.isNaN(firstOperand)) {
            calculateResult();
        }
        firstOperand = Double.parseDouble(currentInput);
        this.operator=operator;
        currentInput= "";
    }


    private void calculateResult() {

        if (!Double.isNaN(firstOperand) && !currentInput.isEmpty()) {
            secondOperand = Double.parseDouble(currentInput);
            double result;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;

                case "-":
                    result = firstOperand - secondOperand;
                    break;

                case "*":
                    result = firstOperand * secondOperand;
                    break;

                case "/":
                    result = firstOperand / secondOperand;
                    break;

                default:
                    return;

            }

            resultDisplay.setText(String.valueOf(result));
            firstOperand= Double.NaN;
            currentInput= "";
        }




    }



    private void backspaceInput() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            resultDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    private void clearInput() {
        currentInput = "";
        operator= "";
        firstOperand= Double.NaN;
        resultDisplay.setText("0");;

    }



}
