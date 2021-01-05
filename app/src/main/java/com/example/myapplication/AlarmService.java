package com.example.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlarmService extends IntentService {
    String TAG = "TAG+Service";

    public AlarmService() {
        super("AlarmService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent i = new Intent(getApplicationContext(), AlarmActivity.class);
        Log.d(TAG, "AlarmService");
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        //return super.onStartCommand(intent,flags,startId);
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Intent i=new Intent(getApplicationContext(), AlarmActivity.class);
        Log.d(TAG,"AlarmService");
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return super.onStartCommand(intent,flags,startId);
    }
}*/
}