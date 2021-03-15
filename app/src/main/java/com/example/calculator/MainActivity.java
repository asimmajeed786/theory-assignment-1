package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText first_value_et,second_value_et;
    Button calculate_btn;
    TextView remainder_tv,result_tv;

    Sum sum;
    Subtract subtract;
    Multiply multiply;
    Divide divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calculate_btn = findViewById(R.id.calculate_btn);
        spinner = findViewById(R.id.spinner);

        first_value_et = findViewById(R.id.first_value_et);
        second_value_et = findViewById(R.id.second_value_et);

        remainder_tv = findViewById(R.id.remainder_tv);
        result_tv = findViewById(R.id.result_tv);


        sum = new Sum();
        subtract = new Subtract();
        multiply = new Multiply();
        divide = new Divide();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(first_value_et.getText().toString().equals("") || second_value_et.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter all values", Toast.LENGTH_SHORT).show();
                }
                else{
                    process(Integer.parseInt(first_value_et.getText().toString()), Integer.parseInt(second_value_et.getText().toString()), (String) spinner.getSelectedItem());
                }
            }
        });
    }


    void process(int first_value, int second_value, String operation ){

            if (operation.equals("add")) {
                sum.calculate(first_value,second_value);
                result_tv.setText("Result : " + (sum.result));
                remainder_tv.setText("");
            }
            else if (operation.equals("subtract")) {
                subtract.calculate(first_value,second_value);
                result_tv.setText("Result : " + (subtract.result));
                remainder_tv.setText("");
            }
            else if (operation.equals("multiply")) {
                multiply.calculate(first_value,second_value);
                result_tv.setText("Result : " + (multiply.result));
                remainder_tv.setText("");
            }
            else if (operation.equals("divide") && second_value != 0) {
                divide.calculate(first_value,second_value);
                result_tv.setText("Result : " + (divide.result));
                remainder_tv.setText("Remainder: " + (divide.remainder));
            }
            else if (operation.equals("divide") && second_value == 0){
                Toast.makeText(this, "Exception Occured ! Cannot divide by 0", Toast.LENGTH_SHORT).show();
            }
        }
    }

