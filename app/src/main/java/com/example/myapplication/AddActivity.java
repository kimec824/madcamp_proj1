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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.gson.Gson;


    public class AddActivity extends Fragment {
        Contactlist contacts;//live data, view model 사용하려고 추가


        EditText name;
        EditText phonenumber;
        EditText relationship;
        EditText memo;
        Button button;
        public static AddActivity newInstance() {

            Bundle args = new Bundle();

            AddActivity addActivity = new AddActivity();
            addActivity.setArguments(args);
            return addActivity;
        }
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.add_fragment, container, false);
            super.onViewCreated(view, savedInstanceState);
            Contact contact = new Contact();

            contacts = new ViewModelProvider(requireActivity()).get(Contactlist.class);//live data, view model 사용 위해 추가

            name = (EditText) getView().findViewById(R.id.Editname);
            phonenumber = (EditText) getView().findViewById(R.id.Editphonenumber);
            relationship = (EditText) getView().findViewById(R.id.Editrelationship);
            memo = (EditText) getView().findViewById(R.id.Editmemo);

            button = (Button) getView().findViewById(R.id.Confirmbutton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (name.getText().toString() == "") {
                        Toast.makeText(getContext(), "Please enter 'name'.", Toast.LENGTH_LONG).show();
                    }
                    if (phonenumber.getText().toString() == "") {
                        Toast.makeText(getContext(), "Please enter 'number'.", Toast.LENGTH_LONG).show();
                    }
                    contact.setName(name.getText().toString());
                    contact.setNumber(phonenumber.getText().toString());
                    contact.setRelationship(relationship.getText().toString());
                    contact.setMemo(memo.getText().toString());
                    //System.out.println(contact.getName()); contact 객체에 제대로 들어가나 확인
                    contacts.addContacts(contact);
                    ((MainActivity) getActivity()).replaceFragment(Fragment1.newInstance());
                }
            });
            return view;
        }
    }

