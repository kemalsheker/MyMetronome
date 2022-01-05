package com.blackstart.mymetronome;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements CounterHandler.CounterListener {
    private static final String TAG = "MainActivity";


    private int currentBPM = 90;
    private boolean AutoIncrement = false;
    private boolean AutoDecrement = false;

    TextView bpmNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bpmNumber = findViewById(R.id.bpmNumber);
        FloatingActionButton minusButton = (FloatingActionButton) findViewById(R.id.minusButton);
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.plusButton);


        new CounterHandler.Builder()
                .incrementalView(plusButton)
                .decrementalView(minusButton)
                .minRange(30) // cant go any less than 30
                .maxRange(240) // cant go any further than 240
                .startNumber(90)
                .isCycle(true) // 49,50,-50,-49 and so on
                .counterDelay(200) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();

        Log.d(TAG, "onCreate: ends");
    }


    @Override
    public void onIncrement(View view, int number) {
        bpmNumber.setText(String.valueOf(number));
    }

    @Override
    public void onDecrement(View view, int number) {
        bpmNumber.setText(String.valueOf(number));
    }


}