package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm_Receiver extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent){
        this.context = context;
        String get_your_string=intent.getExtras().getString("state");

        Intent service_intent = new Intent(context,RingtonePlayingService.class);

        service_intent.putExtra("state",get_your_string);

        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.0){
            this.context.startForegroundService(service_intent);
        }else{
            this.context.startService(service_intent);
        }
    }
}
