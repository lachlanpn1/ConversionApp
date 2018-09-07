package com.example.lachlan.conversionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TemperatureActivity extends AppCompatActivity {

    private TextView outputTextView;
    private EditText inputEditText;
    private Spinner inputSpinner, outputSpinner;

    private enum Unit {
        CELSIUS, FAHRENHEIT, KELVIN
    }gx43

    DecimalFormat df = new DecimalFormat("###.###");

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                convertTemp();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        initialiseUI(savedInstanceState);
    }

    private void initialiseUI(Bundle savedInstanceState)
    {
        inputEditText = findViewById(R.id.input_editText);
        outputTextView = findViewById(R.id.output_textView);
        inputSpinner = findViewById(R.id.input_spinner);
        outputSpinner = findViewById(R.id.output_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.temperature_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);

        inputEditText.addTextChangedListener(tw);

        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertTemp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        outputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertTemp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void convertTemp()
    {
        double input;
        Unit inputUnit = getUnit(inputSpinner);
        Unit outputUnit = getUnit(outputSpinner);

        String inputString = inputEditText.getText().toString();
        if (!inputString.equals(""))
        {
            input = Double.parseDouble(inputString);
        } else {
            input = 0.0;
        }


        double result;

        switch(outputUnit)
        {
            case CELSIUS:
                result = toCelsius(input,inputUnit);
                break;
            case FAHRENHEIT:
                result = toFahrenheit(input,inputUnit);
                break;
            default:
                result = toKelvin(input,inputUnit);
                break;
        }

        String outputString = df.format(result);
        outputTextView.setText(outputString);
    }

    private double toCelsius(double temp, Unit unit)
    {
        switch(unit)
        {
            case FAHRENHEIT:
                return (temp - 32) * (5 / 9);
            case KELVIN:
                return (temp - 273.15);
            default:
                return temp;
        }
    }

    private double toFahrenheit(double temp, Unit unit)
    {
        switch(unit)
        {
            case CELSIUS:
                return temp * (9/5) + 32;
            case KELVIN:
                return temp * (9/5) - 459.67;
            default:
                return temp;
        }
    }

    private double toKelvin(double temp, Unit unit)
    {
        switch(unit)
        {
            case FAHRENHEIT:
                return (temp + 459.67) * (5/9);
            case CELSIUS:
                return temp + 273.15;
            default:
                return temp;
        }
    }

    private Unit getUnit(Spinner aSpinner)
    {
        String item = aSpinner.getSelectedItem().toString();
        switch(item)
        {
            case "C° Celsius":
                return Unit.CELSIUS;
            case "F° Fahrenheit":
                return Unit.FAHRENHEIT;
            default:
                return Unit.KELVIN;

        }
    }
}
