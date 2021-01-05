package com.example.myapplication;

import java.io.File;
import java.util.ArrayList;

public class HashList {
    private static HashList hashListInstance = null;
    private static ArrayList<Integer> hashList = new ArrayList<Integer>();
    int hashCode;

    private HashList() {}

    public synchronized static HashList getInstance() {
        if (hashListInstance == null) {
            hashListInstance = new HashList();
        }
        return hashListInstance;
    }

    public synchronized void add(File f) {
        hashCode = f.hashCode();
        hashList.add((Integer) hashCode);
    }

    public synchronized void delete(File f) {
        hashList.remove(hashList.indexOf(f.hashCode()));
    }

    public boolean contains(File f) {
        hashCode = f.hashCode();
        return hashList.contains(hashCode);
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println(hashList.get(i));
        }
    }

    public int size() {return hashList.size();}
}

/*

public class HashList {
    int hashCode;
    ArrayList<Integer> hashList = new ArrayList<Integer>();

    public synchronized void add(File f) {
        hashCode = f.hashCode();
        hashList.add((Integer) hashCode);
    }

    public synchronized void delete(File f) {
        hashList.remove(hashList.indexOf(f.hashCode()));
    }

    public boolean contains(File f) {
        hashCode = f.hashCode();
        return hashList.contains(hashCode);
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println(hashList.get(i));
        }
    }

    public int size() {return hashList.size();}
}
 */