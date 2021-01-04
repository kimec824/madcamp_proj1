package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm_Receiver extends BroadcastReceiver {
    Context context;
    public static final String ACTION_RESTART_SERVICE = "Restart";

    @Override
    public void onReceive(Context context, Intent intent) {
        //this.context=context;
        //String get_your_string=intent.getExtras().getString("state");
        //Intent service_intent=new Intent(context,RingtonePlayingService.class);

        //service_intent.putExtra("state",get_your_string);


        if(intent.getAction().equals(ACTION_RESTART_SERVICE)){
            Intent in=new Intent(context,AlarmService.class);
            context.startService(in);
        }
        /*this.context = context;
        String get_your_string = intent.getExtras().getString("state");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        service_intent.putExtra("state", get_your_string);
*/
    }
}
