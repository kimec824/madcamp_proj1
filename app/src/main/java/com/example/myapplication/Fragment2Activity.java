package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.io.File;

public class Fragment2Activity extends AppCompatActivity {

    //int imageId;
    private int position;
    //IdList idList;
    PathList pathList = PathList.getInstance();
    HashList hashList = HashList.getInstance();
    ImageView image;
    Button previous;
    Button next;
    CheckBox bookmarkCheckbox;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //imageId = extras.getInt("id");
            position = extras.getInt("position");
        }

        //idList = (IdList) getIntent().getSerializableExtra("idList");
        //pathList = (PathList) getIntent().getSerializableExtra("pathList");

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

        //image.setImageResource(idList.get(position));
        Bitmap myBitmap = BitmapFactory.decodeFile(pathList.get(position).getAbsolutePath());
        image.setImageBitmap(myBitmap);

        /*
        image.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                image.setImageResource(idList.get(position + 1));
            }
            public void onSwipeLeft() {
                image.setImageResource(idList.get(position - 1));
            }
            public void onSwipeBottom() {

            }
        });

         */
    }

    class OnClickNext implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (position < pathList.size() - 1) {
                /*
                image.setImageResource(idList.get(position + 1));
                position += 1;

                 */

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
                /*
                image.setImageResource(idList.get(position - 1));
                position -= 1;

                 */

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

