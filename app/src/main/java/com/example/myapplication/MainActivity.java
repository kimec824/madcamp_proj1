package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.ui.main.SectionsPagerAdapter;



public class MainActivity extends AppCompatActivity {
    Contactjson contacts=new Contactjson();

  /*  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

            }
            else{
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }*/



    //@Override
    protected void onCreate(Bundle savedInstanceState, Intent intent) {
       /* if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },1);

        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();//add activity로부터 contactjson 객체 받기
        contacts = (Contactjson) i.getSerializableExtra("key");
        //Fragment1 frag = Fragment1.newInstance();
        //getSupportFragmentManager().beginTransaction().add(R.id.view_pager, frag).commit();



        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.view_pager, Fragment1.newInstance()).commit();




        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);




    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.fragment1);
        if(!fragment.getClass().toString().equals(Fragment1.getTag()))//이동할 fragment가 fragment1인 경우
        {
        //Fragment1으로 넘어갈 때만 contacts넘겨줘야함
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragment = Fragment1.newInstance(contacts);
        fragmentTransaction.replace(R.id.view_pager, fragment,).commit();
        }
        else{//이동할 fragment가 fragment1이 아닌 경우
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.view_pager, fragment,).commit();
        }
    }


}