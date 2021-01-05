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

        System.out.println("frag 3 on createview");

        View view = inflater.inflate(R.layout.fragment3_layout, container, false);



        Button invokeActivity = (Button) view.findViewById(R.id.stopWatch);

        invokeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Stopwatch.class);
                startActivity(intent);
            }
        });





        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("frag 3 on pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("frag 3 on resume");


    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("frag 3 on start");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("frag 3 on stop")
                ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("frag 3 on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("frag 3 on destroy");
    }
}
