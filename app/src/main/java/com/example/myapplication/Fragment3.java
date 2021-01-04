package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.os.Build.VERSION_CODES.M;
import static androidx.core.content.ContextCompat.getSystemService;

public class Fragment3 extends Fragment {
    //AlarmManager alarm_manager;//알람매니저
    //TimePicker alarm_timepicker;//타임피커
    Button add_button;
    int alarmHour;
    int alarmMinute;
    Calendar alarmCalendar;
    Context context;

    public static Fragment3 newInstance() {

        Bundle args = new Bundle();

        Fragment3 fragment = new Fragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        super.onViewCreated(view,savedInstanceState);
        //AlarmManager alarm_manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //alarm_timepicker = view.findViewById(R.id.timePicker);
        add_button = (Button)view.findViewById(R.id.alarmButton);
        //final Intent my_intent=new Intent(getActivity(),Alarm_Reciever.class);//알람 리시버버
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getContext(), Alarm_add.class);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        alarmHour = hourOfDay;
                        alarmMinute = minute;
                        setAlarm();
                    }
                }, alarmHour, alarmMinute, false);
                timePickerDialog.show();
            }
        });
        return view;
    }

    void setAlarm() {
        alarmCalendar = Calendar.getInstance();
        alarmCalendar.setTimeInMillis(System.currentTimeMillis());
        alarmCalendar.set(Calendar.HOUR_OF_DAY,alarmHour);
        alarmCalendar.set(Calendar.MINUTE,alarmMinute);
        alarmCalendar.set(Calendar.SECOND,0);
        //TimePickerDialog에서 설정한 시간을 알람시간으로 설정
        if(alarmCalendar.before(Calendar.getInstance())) alarmCalendar.add(Calendar.DATE,1);
        Intent alarmIntent=new Intent(getView().getContext(),Alarm_Receiver.class);////////////////////////////////
        AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);//////////////////////////////////해결
        alarmIntent.setAction(Alarm_Receiver.ACTION_RESTART_SERVICE);
        PendingIntent alarmCallPendingIntent=PendingIntent.getBroadcast(getActivity(),0,alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);////////////////////////////해결

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,alarmCalendar.getTimeInMillis(),alarmCallPendingIntent);
        else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarmCalendar.getTimeInMillis(),alarmCallPendingIntent);
    }
}