    package com.example.myapplication;

import android.content.Context;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


    public class AddActivity extends AppCompatActivity{
        EditText name;
        EditText phonenumber;
        EditText relationship;
        EditText memo;
        Button button;
        //ArrayList<Contact> finalContacts1;


        protected void onCreate(Bundle savedInstanceState, Intent intent) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_fragment);
            Intent i = getIntent();
            Contactjson contacts = (Contactjson) i.getSerializableExtra("key");
            Contact contact = new Contact();
            //ArrayList<Contact> contacts = new ArrayList<Contact>();
            //contacts = intent.getParcelableArrayListExtra("key");
            UseGSONapitoConvertJavaOBJtoJASONstring makejson = new UseGSONapitoConvertJavaOBJtoJASONstring();
            button = (Button) findViewById(R.id.Confirmbutton);

            name = (EditText) findViewById(R.id.Editname);
            phonenumber = (EditText) findViewById(R.id.Editphonenumber);
            relationship = (EditText) findViewById(R.id.Editrelationship);
            memo = (EditText) findViewById(R.id.Editmemo);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (name.getText().toString() == "") {
                        Toast.makeText(getApplicationContext(), "Please enter 'name'.", Toast.LENGTH_LONG).show();
                    }
                    if (phonenumber.getText().toString() == "") {
                        Toast.makeText(getApplicationContext(), "Please enter 'number'.", Toast.LENGTH_LONG).show();
                    }
                    contact.setName(name.getText().toString());
                    contact.setNumber(phonenumber.getText().toString());
                    contact.setRelationship(relationship.getText().toString());
                    contact.setMemo(memo.getText().toString());
                    contacts.addContact(contact);
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    //add activity에서 main activity 열 때 contacts list 보내야 함.
                    intent2.putExtra("key", contacts);
                    startActivity(intent2);
                }
            });

           // public void openMainActivity () {

            //}
        }
    }