    package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



    public class Contactjson implements Serializable {
        public ArrayList<Contact> contacts = new ArrayList<Contact>();
        public Contactjson(){

        }

        //기본 연락처
        public static void main( ArrayList contacts) {
            JSONObject one = null;
            one.put("name","조범석");one.put("number","01020535403");one.put("relationship","나");one.put("memo","");
            JSONObject two = null;
            two.put("name","조우열");two.put("number","01062105403");two.put("relationship","가족");two.put("memo","");
            JSONObject three = null;
            three.put("name","최윤정");three.put("number","01041004826");three.put("relationship","가족");three.put("memo","");
            contacts.add(one);
            contacts.add(two);
            contacts.add(three);
        }
        //get
        public Contact getContact(int i) {
            return (Contact) contacts.get(i);
        }
        //add
        public void addContact(Contact contact) {
            contacts.add(contact);
        }
    }