package com.example.myapplication;

import android.app.TimePickerDialog;
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

public class Alarm_add extends Fragment {
    int alarmHour;
    int alarmMinute;

    public static Alarm_add newInstance() {
        Bundle args = new Bundle();

        Alarm_add alarm_add = new Alarm_add();
        alarm_add.setArguments(args);
        return alarm_add;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.alarm_add, container, false);
        super.onViewCreated(view,savedInstanceState);
        Button add_button;
        add_button = (Button)view.findViewById(R.id.addalarmButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity().getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        alarmHour = hourOfDay;
                        alarmMinute = minute;
                        //setAlarm();
                    }
                }, alarmHour, alarmMinute, false);
                Intent intent = new Intent(getContext(), Fragment3.class);
            }
        });
        return view;
    }
}
