package com.example.myapplication;

import android.database.Cursor;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;



public class Fragment1 extends Fragment {

    ContactList contacts = ContactList.getInstance();
    ArrayList<String> names = new ArrayList<String>();
    String name;
    String number;
    Cursor cursor;
    View view;

    String[] projection = {
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };
    Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        this.view = view;

        Button button = (Button)view.findViewById(R.id.Addbutton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        cursor = getContext().getContentResolver().query(contactUri, projection, null, null, null);

        getContacts(cursor, names);

        ListView listView;
        listView = (ListView) view.findViewById(R.id.contactList);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                names
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //((MainActivity)getActivity()).replaceFragment(Fragment1ContactInfo.newInstance(position));

                Intent intent = new Intent(getActivity(), Fragment1Activity.class);

                name = contacts.get(position).getName();
                number = contacts.get(position).getNumber();

                intent.putExtra("name", name);
                intent.putExtra("number", number);
                startActivity(intent);
            }
        });


    }

    public void getContacts(Cursor cursor, ArrayList<String> names) {
        String name;
        String number;
        names.clear();
        contacts = ContactList.getInstance();
        contacts.clear();

        if (cursor.moveToFirst()) {
            do {

                name = cursor.getString(1);
                number = cursor.getString(2);

                contacts.add(new Contact(name, number));
                names.add(name);
            } while (cursor.moveToNext());
        }
    }

}
