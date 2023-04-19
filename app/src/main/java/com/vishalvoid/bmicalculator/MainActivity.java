package com.vishalvoid.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // fields or InnerClass Variable.
    private TextView resultText;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListner();

    }

    // to find views
    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);

    }

    ;

    // to control events on button Click.
    private void setupButtonClickListner() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              double bmi =   calculateBmi();
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age >= 18){
                    displayResult(bmi);
                }else{
                    displayGuidance(bmi);
                }
            }
        });
    }



    ;

    // logic to calculate the Bmi
    private double calculateBmi() {
        String ageText = ageEditText.getText().toString();
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        // converting String Values to Integer.

        int age = Integer.parseInt(ageText);
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        // converted height in meters.
        double heightInMeters = totalInches * 0.0254;

        // Calculation for Bmi.
      return  weight / (heightInMeters * heightInMeters);


    }

    private void displayResult(double bmi){
        // formatting the decimal Value.
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        // writing conditions.
        String fullResultString;
        if (bmi < 18.5) {
            // underweight
            fullResultString = bmiTextResult + " - You are a Under-weight.";
        } else if (bmi > 25) {
            // overweight
            fullResultString = bmiTextResult + " - You are a Over-weight";
        } else {
            // healthy
            fullResultString = bmiTextResult + " - You are a Healthy-weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {

        // formatting the decimal Value.
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        // writing conditions.
        String fullResultString;

        if(maleButton.isChecked()){
            // Display Boy Guidance.
            fullResultString = bmiTextResult + "As you are under 18, Consult with your Doctor for the healthy range for Boy.";
        }else if(femaleButton.isChecked()){
            // Display Girl Guidance.
            fullResultString = bmiTextResult + "As you are under 18, Consult with your Doctor for the healthy range for Girl.";
        }else{
            // display General Guidance.
            fullResultString = "Kindly Choose either male or female to get Your desired result.";
        }

        resultText.setText(fullResultString);

    }


}