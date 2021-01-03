package com.example.myapplication;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


class PathList implements Serializable {
    private static PathList pathListInstance = null;
    private static ArrayList<File> pList = new ArrayList<File>();

    private PathList() {}

    public synchronized static PathList getInstance() {
        if (pathListInstance == null) {
            pathListInstance = (PathList) new PathList();
        }
        return pathListInstance;
    }

    public synchronized void clear() {
        pList.clear();
    }

    public synchronized void add(File f) {
        pList.add(f);
    }

    public int size() {
        return pList.size();
    }

    public File get(int i) {
        return pList.get(i);
    }
}

