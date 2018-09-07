package com.example.lachlan.conversionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        initialiseUI(savedInstanceState);
    }

    private static final double INCHES_TO_CM = 2.54;
    private static final double FEET_TO_CM = 30.48;
    private static final double MILES_TO_CM = 160934.0;

    private static final double INCHES_TO_M = 0.0254;
    private static final double FEET_TO_M = 0.3048;
    private static final double MILES_TO_M = 1609.34;

    private TextView inchesResult, feetResult, milesResult;

    private EditText inchesInput, feetInput, milesInput;

    private CheckBox metresCheckBox;

    DecimalFormat df = new DecimalFormat("###.###");

    private void initialiseUI(Bundle savedInstanceState) {
        Button btnConvert = findViewById(R.id.convert_button);
        btnConvert.setOnClickListener(btnConvertListener);

        inchesResult = findViewById(R.id.inchesResult_textView);
        feetResult = findViewById(R.id.feetResult_textView);
        milesResult = findViewById(R.id.milesResult_textView);

        inchesInput = findViewById(R.id.inches_editText);
        feetInput = findViewById(R.id.feet_editText);
        milesInput = findViewById(R.id.miles_editText);

        metresCheckBox = findViewById(R.id.metres_chkBox);

        restoreState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        String[] results = new String[3];
        if (!inchesResult.getText().toString().equals(""))
            results[0] = inchesResult.getText().toString();
        else
            results[0] = "";

        if(!feetResult.getText().toString().equals(""))
            results[1] = feetResult.getText().toString();
        else
            results[1] = "";

        if(!milesResult.getText().toString().equals(""))
            results[2] = milesResult.getText().toString();
        else
            results[2] = "";

        String[] inputs = new String[3];
        inputs[0] = inchesInput.getText().toString();
        inputs[1] = feetInput.getText().toString();
        inputs[2] = milesInput.getText().toString();

        outState.putStringArray("RESULTS",results);
        outState.putStringArray("INPUTS",inputs);
        outState.putBoolean("METRES",metresCheckBox.isChecked());

        super.onSaveInstanceState(outState);
    }

    private void restoreState(Bundle inState)
    {
        if (inState == null) { return; }
        String[] results = inState.getStringArray("RESULTS");
        String[] inputs = inState.getStringArray("INPUTS");
        boolean metres = inState.getBoolean("METRES");

        inchesResult.setText(results[0]);
        feetResult.setText(results[1]);
        milesResult.setText(results[2]);

        inchesInput.setText(inputs[0]);
        feetInput.setText(inputs[1]);
        milesInput.setText(inputs[2]);

        metresCheckBox.setChecked(metres);



    }

    private View.OnClickListener btnConvertListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(metresCheckBox.isChecked())
            {
                convertToMetres();
            } else {
                convertToCentimetres();
            }
        }
    };

    private void convertToCentimetres()
    {
        if(!(inchesInput.getText().toString().equals(""))) {
            String inputString = inchesInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * INCHES_TO_CM) + " cm";

            inchesResult.setText(result);
        }

        if(!(feetInput.getText().toString().equals(""))) {
            String inputString = feetInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * FEET_TO_CM) + " cm";

            feetResult.setText(result);
        }

        if(!(milesInput.getText().toString().equals(""))) {
            String inputString = milesInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * MILES_TO_CM) + " cm";
            // String result = Double.toString(inputNum * MILES_TO_CM);

            milesResult.setText(result);
        }
    }

    private void convertToMetres()
    {
        if(!(inchesInput.getText().toString().equals(""))) {
            String inputString = inchesInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * INCHES_TO_M) + " m";

            inchesResult.setText(result);
        }

        if(!(feetInput.getText().toString().equals(""))) {
            String inputString = feetInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * FEET_TO_M) + " m";

            feetResult.setText(result);
        }

        if(!(milesInput.getText().toString().equals(""))) {
            String inputString = milesInput.getText().toString();
            double inputNum = Double.parseDouble(inputString);

            String result = "= " + df.format(inputNum * MILES_TO_M) + " m";

            milesResult.setText(result);
        }
    }
}
