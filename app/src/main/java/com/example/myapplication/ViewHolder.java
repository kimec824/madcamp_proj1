package com.example.myapplication;


import android.view.View;
import android.widget.ImageView;

class ViewHolder {

    ImageView imageView;

    ViewHolder(View view) {
        imageView = (ImageView) view.findViewById(R.id.image);
    }

}


