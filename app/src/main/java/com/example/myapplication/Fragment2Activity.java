package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.File;

public class Fragment2Activity extends AppCompatActivity {

    private int position;
    PathList pathList = PathList.getInstance();
    HashList hashList = HashList.getInstance();
    ImageView image;
    Button previous;
    Button next;
    CheckBox bookmarkCheckbox;
    SharedPreferences mPrefs;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);

        image = (ImageView) findViewById(R.id.imgview);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        bookmarkCheckbox = (CheckBox) findViewById(R.id.bookmarkCheckbox);

        if (hashList.contains(pathList.get(position))) {
            bookmarkCheckbox.setChecked(true);
        } else {
            bookmarkCheckbox.setChecked(false);
        }

        previous.setOnClickListener(new OnClickPrev());
        next.setOnClickListener(new OnClickNext());
        bookmarkCheckbox.setOnCheckedChangeListener(new onClickCheckbox());

        Bitmap myBitmap = BitmapFactory.decodeFile(pathList.get(position).getAbsolutePath());
        image.setImageBitmap(myBitmap);
    }

    class OnClickNext implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (position < pathList.size() - 1) {
                Bitmap myBitmap = BitmapFactory.decodeFile(pathList.get(position + 1).getAbsolutePath());
                image.setImageBitmap(myBitmap);
                position += 1;


                if (hashList.contains(pathList.get(position))) {
                    bookmarkCheckbox.setChecked(true);
                } else {
                    bookmarkCheckbox.setChecked(false);
                }
            }
        }
    }

    class OnClickPrev implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (position > 0) {
                Bitmap myBitmap = BitmapFactory.decodeFile(pathList.get(position - 1).getAbsolutePath());
                image.setImageBitmap(myBitmap);
                position -= 1;


                if (hashList.contains(pathList.get(position))) {
                    bookmarkCheckbox.setChecked(true);
                } else {
                    bookmarkCheckbox.setChecked(false);
                }
            }

        }
    }

    class onClickCheckbox implements CompoundButton.OnCheckedChangeListener {

        File f;
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            if (isChecked && !hashList.contains(pathList.get(position))) {
                hashList.add(pathList.get(position));
            } else if (!isChecked && hashList.contains(pathList.get(position))) {
                hashList.delete(pathList.get(position));
            }
        }
    }

}

