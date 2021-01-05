package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.myapplication.MainActivity.bookmark;

public class Fragment2 extends Fragment{

    GridView gridView;
    FloatingActionButton bookmarkButton;
    GalleryAdapter galleryAdapter;
    private View view;
    HashList hashList = HashList.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        this.view = view;

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
                intent.putExtra("position", position);
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


