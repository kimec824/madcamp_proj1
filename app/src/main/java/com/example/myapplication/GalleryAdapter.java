package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.bookmark;

public class GalleryAdapter extends BaseAdapter {

    PathList pathList = PathList.getInstance();
    File dcimFolder;
    File downloadFolder;
    File pictureFolder;
    File cameraFolder;
    Context context;

    GalleryAdapter(Context context) {

        this.context = context;
        GalleryAdapterHelper galleryAdapterHelper = new GalleryAdapterHelper(bookmark, context);

        dcimFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM");
        downloadFolder = new File(Environment.getExternalStorageDirectory() + "/Download");
        pictureFolder = new File(Environment.getExternalStorageDirectory() + "/Pictures");
        cameraFolder = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");

        galleryAdapterHelper.append(dcimFolder);
        galleryAdapterHelper.append(downloadFolder);
        galleryAdapterHelper.append(pictureFolder);
        galleryAdapterHelper.append(cameraFolder);
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

        View row = convertView;
        ViewHolder holder = null;

        //if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.gallery_item, parent, false);

            holder = new ViewHolder (row);

            row.setTag(holder);

       // } else {
        //    holder = (ViewHolder) row.getTag();
        //}

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
    SharedPreferences mPrefs;

    Boolean bookmark;
    File[] filePathList;
    int i;

    GalleryAdapterHelper (Boolean b, Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = mPrefs.getString("key", null);
        ArrayList<Integer> bookmarkList = new ArrayList<Integer>();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String hash = a.optString(i);
                    bookmarkList.add((Integer) Integer.parseInt(hash));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        hashList.setList(bookmarkList);

        System.out.println(bookmarkList);

        bookmark = b;
        pathList.clear();

    }

    public PathList append(File folder) {
        if (folder.exists() && !bookmark) {

            filePathList = folder.listFiles();
            File file;

            for (i = 0; i < filePathList.length; i++ ) {
                file = filePathList[i];
                //if (file.isDirectory()) {
                //    append(file);
                //} else {
                    if (!((file.toString().contains(".thumbnails")) && (file.toString().contains(".dat"))) && !file.isDirectory() &&
                            (file.toString().toLowerCase().contains(".jpg") || file.toString().toLowerCase().contains(".png") ||
                                    file.toString().toLowerCase().contains(".jpeg") || file.toString().toLowerCase().contains(".bmp")))
                        pathList.add(file);
                //}  
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



