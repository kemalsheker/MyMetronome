package com.blackstart.mymetronome;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CounterHandler.CounterListener {
    private static final String TAG = "MainActivity";


    private int currentBPM = 90;
    private boolean AutoIncrement = false;
    private boolean AutoDecrement = false;
    private int minimumBPM = 30;
    private int maximumBPM = 240;


    TextView bpmNumber;
    SeekBar metronome;
    AppCompatToggleButton startStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bpmNumber = findViewById(R.id.bpmNumber);
        metronome = findViewById(R.id.metronome);
        startStopButton = findViewById(R.id.startStopButton);
        metronome.setClickable(false);
        metronome.setEnabled(false);
        FloatingActionButton minusButton = (FloatingActionButton) findViewById(R.id.minusButton);
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.plusButton);



        new CounterHandler.Builder()
                .incrementalView(plusButton)
                .decrementalView(minusButton)
                .minRange(minimumBPM) // cant go any less than 30
                .maxRange(maximumBPM) // cant go any further than 240
                .startNumber(90)
                .isCycle(true) // 49,50,-50,-49 and so on
                .counterDelay(200) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();

        bpmNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentBPM = Integer.parseInt(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentBPM = Integer.parseInt(charSequence.toString());
                Log.d(TAG, "onCreate: currentBPM is " + currentBPM);
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



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