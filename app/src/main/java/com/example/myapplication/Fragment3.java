package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.os.Build.VERSION_CODES.M;
import static androidx.core.content.ContextCompat.getSystemService;

public class Fragment3 extends Fragment {


    public static Fragment3 newInstance() {

        Bundle args = new Bundle();

        Fragment3 fragment = new Fragment3();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getActivity(), Stopwatch.class);
        startActivity(intent);

        return null;
    }

}
