package com.example.myapplication;

import java.util.ArrayList;

public class ContactList {

    private static ContactList contactListInstance = null;
    private static ArrayList<Contact> contactList = new ArrayList<Contact>();

    private ContactList() {

    }

    public synchronized static ContactList getInstance() {
        if (contactListInstance == null) {
            contactListInstance = new ContactList();
        }
        return contactListInstance;
    }

    public void add(Contact contact) {
        contactList.add(contact);
    }

    public Contact get(int position) {
        return contactList.get(position);
    }

    public void clear() {
        contactList.clear();
    }

    public boolean exists(String name) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
