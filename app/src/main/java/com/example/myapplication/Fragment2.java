package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.myapplication.MainActivity.bookmark;

public class Fragment2 extends Fragment{

    GridView gridView;
    FloatingActionButton bookmarkButton;
    GalleryAdapter galleryAdapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        this.view = view;

        /*
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        FloatingActionButton bookmarkButton = (FloatingActionButton) view.findViewById(R.id.bookmarkButton);
        GalleryAdapter galleryAdapter = new GalleryAdapter(this.getActivity());
        
         */

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        gridView = (GridView) view.findViewById(R.id.gridView);
        bookmarkButton = (FloatingActionButton) view.findViewById(R.id.bookmarkButton);
        galleryAdapter = new GalleryAdapter(this.getActivity());
        gridView.setAdapter(galleryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Fragment2Activity.class);
                //intent.putExtra("id", id);
                intent.putExtra("position", position);
                //intent.putExtra("idList", galleryAdapter.idList);
                //intent.putExtra("pathList", galleryAdapter.pathList);
                getActivity().startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmark = !bookmark;
                refresh();
            }
        });


    }

    private void refresh() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }


}

/*
class IdList implements Serializable {
    ArrayList<Integer> list;

    public IdList() {
        list = new ArrayList<Integer>();
    }

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public int get(int i) {
        return list.get(i);
    }
}

*/


