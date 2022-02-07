package com.blackstart.mymetronome;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MetronomeService extends Service {

    private static final String TAG = "MetronomeService";

    // Binder given to clients (notice class declaration below)
    private final IBinder mBinder = new MyBinder();
    private int minimumBPM = 30;
    private int maximumBPM = 240;
    private int tickSound;

    private Handler handler;
    private SoundPool soundPool;
    private Timer timer;

    @Override
    public void onCreate(){
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        tickSound = soundPool.load(this, R.raw.mytick, 1);

    }

    /**
     * Class used for the client Binder. The Binder object is responsible for returning an instance
     * of "MyService" to the client.
     */
    public class MyBinder extends Binder {
        MetronomeService getService() {
            // Return this instance of MyService so clients can call public methods
            return MetronomeService.this;
        }
    }

    /**
     * This is how the client gets the IBinder object from the service. It's retrieve by the "ServiceConnection"
     * which you'll see later.
     **/
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public void playSound(int bpm){
        Log.d(TAG, "playSound: started");
        Log.d(TAG, "onLoadComplete: Current bpm is out: " + bpm);

        timer = new Timer();
        Log.d(TAG, "onLoadComplete: Current bpm is: " + bpm);


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                soundPool.play(tickSound, 1,1,1, 0, 1);
            }
        }, 0, (1000 * 60) / bpm);


    }

    public void stopSound(){
        Log.d(TAG, "stopSound: started");
        if (timer != null){
            timer.cancel();
            timer.purge();
        }
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "onTaskRemoved: called.");
        stopSelf();
        if (timer != null && soundPool != null){
            timer.cancel();
            timer.purge();
            soundPool.stop(tickSound);
        }
        soundPool.stop(tickSound);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null && soundPool != null){
            timer.cancel();
            timer.purge();
            soundPool.stop(tickSound);
        }
        Log.d(TAG, "onDestroy: called.");
    }

}
