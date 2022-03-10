package com.blackstart.mymetronome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements CounterHandler.CounterListener {
    private static final String TAG = "MainActivity";


    private int currentBPM = 90;
    private boolean AutoIncrement = false;
    private boolean AutoDecrement = false;
    private int minimumBPM = 30;
    private int maximumBPM = 240;

    TextView bpmNumber;
    AppCompatToggleButton startStopButton;
    Toolbar toolbar;

    private MetronomeService mService;
    private Boolean mIsBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyMetronome);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RudimentListFragment fragment = new RudimentListFragment();
            transaction.replace(R.id.fragmentContainerView, fragment);
            transaction.commit();

        }

        bpmNumber = findViewById(R.id.bpmNumber);
        startStopButton = findViewById(R.id.startStopButton);
        FloatingActionButton minusButton = (FloatingActionButton) findViewById(R.id.minusButton);
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.plusButton);
        toolbar = findViewById(R.id.rudimentToolbar);

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

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentBPM = Integer.parseInt(editable.toString());
                if (mIsBound) {
                    mService.stopSound();
                    updateBPM(currentBPM);
                }
                Log.d(TAG, "onCreate: currentBPM is " + currentBPM);
            }
        });

        startStopButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: Toggle is enabled");
                    if (!mIsBound) {
                        startMetronomeService();
                    } else {
                        updateBPM(currentBPM);
                    }
                } else {
                    Log.d(TAG, "onCheckedChanged: Toggle is disabled");
                    if (mIsBound) {
                        stopMetronomeService();
                    }
                }
            }
        });

        Log.d(TAG, "onCreate: ends");
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iBinder) {
            Log.d(TAG, "ServiceConnection: connected to service.");
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            MetronomeService.MyBinder binder = (MetronomeService.MyBinder) iBinder;
            mService = binder.getService();
            mIsBound = true;

            updateBPM(currentBPM);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG, "ServiceConnection: disconnected from service.");
            mIsBound = false;
        }
    };


    private void startMetronomeService() {
        Intent serviceIntent = new Intent(this, MetronomeService.class);
        startService(serviceIntent);

        bindService();
    }

    private void bindService() {
        Intent serviceBindIntent = new Intent(this, MetronomeService.class);
        bindService(serviceBindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void stopMetronomeService() {
        if (mIsBound) {
            unbindService(serviceConnection);
            this.stopService(new Intent(this, MetronomeService.class));
            mIsBound = false;
        }
    }

    private void updateBPM(int bpm) {
        mService.playSound(bpm);
    }

    public void updateToolbar(String name) {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setTitle(name);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                RudimentListFragment fragment = new RudimentListFragment();
                transaction.replace(R.id.fragmentContainerView, fragment);
                transaction.commit();
                toolbar.setNavigationIcon(null);
                toolbar.setTitle("Rudiments");
            }
        });
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