package com.example.myapplication;

import androidx.annotation.Nullable;

public class RingtonePlayingService extends Service{
    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        if(Build.VERSION.SDK_INT>=26)
    }
}
