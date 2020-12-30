package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1ContactInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1ContactInfo extends Fragment {

    private int position;


    public Fragment1ContactInfo() {
        // Required empty public constructor
    }

    public static Fragment1ContactInfo newInstance(int position) {
        
        Bundle args = new Bundle();
        
        Fragment1ContactInfo fragment = new Fragment1ContactInfo();
        fragment.setArguments(args);

        fragment.setPosition(position);

        return fragment;
    }

    private void setPosition(int position) {
        this.position = position;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_fragment1_contact_info, container, false);
        Button button1 = (Button)view.findViewById(R.id.button);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(Fragment1.newInstance());
            }
        });

        return view;
    }
}