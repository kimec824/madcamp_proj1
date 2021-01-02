package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Contact;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Fragment1Activity extends AppCompatActivity {

    private int position;

    private void setPosition(int i) {this.position = i;}
    private int getPosition() {return this.position;}

    public String get_json(){
        String json = "";
        try{
            InputStream is = this.getAssets().open("contact.json");
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

            JSONArray contactArray = jsonObject.getJSONArray("contact");


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

    private String[] getNumberList(ArrayList<Contact> contacts) {

        int length = contacts.size();
        String[] nameList = new String[length];
        for (int i = 0; i < length; i++) {
            Contact contact = contacts.get(i);
            String name = contact.getNumber();
            nameList[i] = name;
        }

        return nameList;
    }

    private String[] getRelationshipList(ArrayList<Contact> contacts) {

        int length = contacts.size();
        String[] nameList = new String[length];
        for (int i = 0; i < length; i++) {
            Contact contact = contacts.get(i);
            String name = contact.getRelationship();
            nameList[i] = name;
        }

        return nameList;
    }

    private String[] getMemoList(ArrayList<Contact> contacts) {

        int length = contacts.size();
        String[] nameList = new String[length];
        for (int i = 0; i < length; i++) {
            Contact contact = contacts.get(i);
            String name = contact.getMemo();
            nameList[i] = name;
        }

        return nameList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int i = extras.getInt("position");
            this.setPosition(i);
        }

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        jsonParsing(get_json(), contacts);

        String[] names = getNameList(contacts);
        String[] numbers = getNumberList(contacts);
        String[] relationships = getRelationshipList(contacts);
        String[] memos = getMemoList(contacts);

        String name = names[this.getPosition()];
        String number = numbers[this.getPosition()];
        String relationship = relationships[this.getPosition()];
        String memo = memos[this.getPosition()];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment1);

        TextView nameTV = (TextView)findViewById(R.id.name);
        TextView numberTV = (TextView)findViewById(R.id.number);
        TextView relationshipTV = (TextView)findViewById(R.id.relationship);
        TextView memoTV = (TextView)findViewById(R.id.memo);

        nameTV.setText(name);
        numberTV.setText(number);
        relationshipTV.setText(relationship);
        memoTV.setText(memo);



    }
}