package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static boolean bookmark = false;
    Context context;
    String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
    SharedPreferences mPrefs;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
            else{
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);;

        context = getApplicationContext();

        checkPermission(context, permissions);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void checkPermission(Context context, String[] permissions) {
        Boolean b = true;

        for (int i=0; i <permissions.length; i++) {
            b = (ActivityCompat.checkSelfPermission(context, permissions[i]) == PackageManager.PERMISSION_GRANTED) && b;
        }

        if (!b) {
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GalleryAdapter galleryAdapter = new GalleryAdapter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("main activity pausing");

        HashList hashList = HashList.getInstance();
        ArrayList<Integer> bookmarkList = hashList.getList();

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        JSONArray a = new JSONArray();

        for (int i = 0; i < bookmarkList.size(); i++) {
            a.put(bookmarkList.get(i));
        }
        if (!bookmarkList.isEmpty()) {
            editor.putString("key", a.toString());
        } else {
            editor.putString("key", null);
        }
        editor.apply();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager, fragment).commit();
    }
}