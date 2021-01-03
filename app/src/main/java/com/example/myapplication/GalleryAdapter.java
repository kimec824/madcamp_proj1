package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import static com.example.myapplication.MainActivity.bookmark;

public class GalleryAdapter extends BaseAdapter {

    //IdList list = new IdList();
    PathList pathList = PathList.getInstance();
    File dcimFolder;
    File downloadFolder;
    File pictureFolder;
    Context context;

    GalleryAdapter(Context context) {

        this.context = context;
        //Class<R.drawable> drawable = R.drawable.class;

        //Resources res = context.getResources();

        GalleryAdapterHelper galleryAdapterHelper = new GalleryAdapterHelper(bookmark);

        dcimFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM");
        downloadFolder = new File(Environment.getExternalStorageDirectory() + "/Download");
        pictureFolder = new File(Environment.getExternalStorageDirectory() + "/Pictures");

        System.out.println(pictureFolder.exists());

        /*
        pathList = galleryAdapterHelper.append(dcimFolder);
        galleryAdapterHelper = new GalleryAdapterHelper(pathList);
        pathList = galleryAdapterHelper.append(downloadFolder);

         */

        galleryAdapterHelper.append(dcimFolder);
        galleryAdapterHelper.append(downloadFolder);
        galleryAdapterHelper.append(pictureFolder);

        /*
        try {
            Field field;
            for (int i = 1; i <= 60; i++) {
                field = drawable.getField("img"+i);
                list.add(field.getInt(null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
    }

    @Override
    public int getCount() {
        return pathList.size();
    }

    @Override
    public Object getItem(int position) {
        return pathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //The procedure taken by the below 2 lines are very expensive, and we do not want it to be done multiple times unnecessarily.
        //The second parameter of this method, 'convertView', tells us if we are creating this view for the first time or not.
        //If it is being created for the first time, convertView is null.
        //Otherwise, it is 'recycled'.

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.gallery_item, parent, false);
            //Now the variable 'row' holds the entire layout defined in gallery_item.xml.

            holder = new ViewHolder (row);
            //Now the variable 'holder' holds an ImageView defined under 'row' (the layout in gallery_item.xml).
            //Here, the reason why we have to implement a separate class 'ViewHolder' becomes clear;
            // the findViewById() method is very expensive and we only want it to be done once.

            row.setTag(holder);
            //setTag() is a special method of the View class. It can save an object to be used next time when it needs to be recycled.

        } else {
            holder = (ViewHolder) row.getTag();
        }


        //Bitmap myBitmap = BitmapFactory.decodeFile(pathList.get(position).getAbsolutePath());
        //holder.imageView.setImageBitmap(myBitmap);

        loadImage(context, pathList, position, holder.imageView);

        row.setLayoutParams(new ViewGroup.LayoutParams(320,320));

        return row;
    }

    public void loadImage(Context context, PathList pathList, int position, ImageView imageView) {
        Glide.with(context)
                .load(Uri.fromFile(pathList.get(position)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .into(imageView);
    }
}


class GalleryAdapterHelper {

    PathList pathList = PathList.getInstance();
    HashList hashList = HashList.getInstance();
    Boolean bookmark;
    File[] filePathList;
    int i;

    GalleryAdapterHelper (Boolean b) {
        bookmark = b;
        pathList.clear();
    }

    public PathList append(File folder) {
        if (folder.exists() && !bookmark) {
            filePathList = folder.listFiles();

            for (i = 0; i < filePathList.length; i++ ) {
                if (! filePathList[i].toString().contains(".thumbnails"))
                    pathList.add(filePathList[i]);
            }
        } else if (folder.exists() && bookmark) {
            filePathList = folder.listFiles();

            for (i = 0; i < filePathList.length; i++ ) {
                if (hashList.contains(filePathList[i])) {
                    pathList.add(filePathList[i]);
                }
            }

        }
        return pathList;
    }
}



