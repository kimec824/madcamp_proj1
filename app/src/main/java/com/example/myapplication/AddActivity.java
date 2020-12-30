    package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


    public class AddActivity extends AppCompatActivity{
        EditText name;
        String shared = "file";
        Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_fragment);
            button=(Button)findViewById(R.id.Confirmbutton);
            name= (EditText)findViewById(R.id.Editname);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMainActivity();
                }
            });
        }

        public void openMainActivity() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

        /*
        @Override
        protected void onStart() {
            super.onStart();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void OnClick(View view) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

         */
    }