package com.example.myapplication;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class Fragment3 extends Fragment{
    AlarmManager alarm_manager;//알람매니저
    TimePicker alarm_timepicker;//타임피커

    public static Fragment3 newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment3 fragment = new Fragment3();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment3_layout, container, false);
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm_timepicker = view.findViewById(R.id.timePicker);

        final Intent my_intent=new Intent(getActivity(),Alarm_Reciever.class);//알람 리시버버
        return view;
    }
}
