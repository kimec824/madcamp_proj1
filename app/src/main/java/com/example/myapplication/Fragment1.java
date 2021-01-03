package com.example.myapplication;

//import com.example.myapplication.Contact;

import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class Fragment1 extends Fragment {

    public static Fragment1 newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }


    public String get_json(){
        String json = "";
        try{
            InputStream is = getContext().getAssets().open("contact.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private void jsonParsing (String json, ArrayList<Contact> contacts)  {

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray contactArray = jsonObject.getJSONArray("phoneBook");


            for (int i = 0; i < contactArray.length(); i++) {
                JSONObject contactObj = contactArray.getJSONObject(i);

                Contact contact = new Contact();

                contact.setName(contactObj.getString("name"));
                contact.setNumber(contactObj.getString("number"));;
                contact.setRelationship(contactObj.getString("relationship"));
                contact.setMemo(contactObj.getString("memo"));

                contacts.add(contact);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String[] getNameList(ArrayList<Contact> contacts) {

        int length = contacts.size();
        String[] nameList = new String[length];
        for (int i = 0; i < length; i++) {
            Contact contact = contacts.get(i);
            String name = contact.getName();
            nameList[i] = name;
        }

        return nameList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        jsonParsing(get_json(), contacts);

        String[] names = getNameList(contacts);




        String[] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };


        Cursor clsCursor = getContext().getContentResolver().query (
                ContactsContract.Contacts.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER +"=1",
                null, null
        );

        while (clsCursor.moveToNext()) {
            Log.d("ID", "사용자 ID: " + clsCursor.getString(0));
            Log.d("Name", "사용자 이름: " + clsCursor.getString(1));
        }








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

                intent.putExtra("position", position);
                startActivity(intent);

/*
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), Fragment1Activity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "You clicked on the second item", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "You clicked on the third item", Toast.LENGTH_SHORT).show();
                }


 */

            }
        });
        Button button = (Button)view.findViewById(R.id.Addbutton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
