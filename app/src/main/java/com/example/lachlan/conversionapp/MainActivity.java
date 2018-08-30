package com.example.lachlan.conversionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_distanceConverter;
    private Button button_tempConverter;

    private View.OnClickListener distanceConverterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener tempConverterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }
    
    private void initialiseUI() {
        button_distanceConverter = findViewById(R.id.Button_distanceConverter);
        button_tempConverter = findViewById(R.id.Button_temperatureConverter);
        
        button_distanceConverter.setOnClickListener(distanceConverterOnClickListener);
        button_tempConverter.setOnClickListener(tempConverterOnClickListener);
    }
    
}
