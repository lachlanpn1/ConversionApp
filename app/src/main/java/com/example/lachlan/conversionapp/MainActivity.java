package com.example.lachlan.conversionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private enum Converter {
        Temperature, Distance
    }

    private View.OnClickListener distanceConverterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showConverter(Converter.Distance);
        }
    };

    private View.OnClickListener tempConverterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showConverter(Converter.Temperature);
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }
    
    private void initialiseUI() {
        Button button_distanceConverter;
        Button button_tempConverter;

        button_distanceConverter = findViewById(R.id.Button_distanceConverter);
        button_tempConverter = findViewById(R.id.Button_temperatureConverter);
        
        button_distanceConverter.setOnClickListener(distanceConverterOnClickListener);
        button_tempConverter.setOnClickListener(tempConverterOnClickListener);
    }

    private void showConverter(Converter converter)
    {
        switch(converter)
        {
            case Temperature:
                Intent tempConverterIntent = new Intent(this, TemperatureActivity.class);
                startActivity(tempConverterIntent);
                break;
            case Distance:
                Intent distConverterIntent = new Intent(this, DistanceActivity.class);
                startActivity(distConverterIntent);
        }
    }
    
}
