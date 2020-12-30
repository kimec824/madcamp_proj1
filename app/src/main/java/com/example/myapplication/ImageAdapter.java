package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<Object> list = new ArrayList<Object>();

    public ImageAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void addItem(Object image) {
        list.add(image);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
