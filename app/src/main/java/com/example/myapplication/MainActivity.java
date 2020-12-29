package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.ui.main.SectionsPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



        //ArrayList<Contact> contacts = new ArrayList<Contact>();

        //jsonParsing(get_json(), contacts);


        //FloatingActionButton fab = findViewById(R.id.fab);
/*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

 */
    }
/*
    public String get_json(){
        String json = "";
        try{
            InputStream is = getAssets().open("contact.json");
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

    public class Contact {
        private class Name {
            private String firstName;
            private String lastName;
        }
        private Name name;
        private String number;
        private class Work {
            private String companyName;
            private String companyAddress;
        }
        private Work work;
        private String relationship;
        private String memo;
        public String getFirstName() {
            return name.firstName;
        }
        public void setFirstName(String firstName) {
            this.name.firstName = firstName;
        }
        public String getLastName() {
            return name.lastName;
        }
        public void setLastName(String lastName) {
            this.name.lastName = lastName;
        }
        public String getNumber() {
            return number;
        }
        public void setNumber(String number) {
            this.number = number;
        }
        public String getCompanyName() {
            return work.companyName;
        }
        public void setCompanyName(String work) {
            this.work.companyName = work;
        }
        public String getCompanyAddress() {
            return work.companyAddress;
        }
        public void setCompanyAddress(String workAddress) {
            this.work.companyAddress = workAddress;
        }
        public String getRelationship() {
            return relationship;
        }
        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }
        public String getMemo() {
            return memo;
        }
        public void setMemo(String memo) {
            this.memo = memo;
        }
    }

    private void jsonParsing (String json, ArrayList<Contact> contacts)  {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray contactArray = jsonObject.getJSONArray("연락처");

            for (int i = 0; i < contactArray.length(); i++) {
                JSONObject contactObj = contactArray.getJSONObject(i);

                Contact contact = new Contact();

                contact.setFirstName(contactObj.getJSONObject("이름").getString("이름"));
                contact.setLastName(contactObj.getJSONObject("이름").getString("성"));
                contact.setNumber(contactObj.getString("전화번호"));
                contact.setCompanyName(contactObj.getJSONObject("직장").getString("이름"));
                contact.setCompanyAddress(contactObj.getJSONObject("직장").getString("주소"));
                contact.setRelationship(contactObj.getString("관계"));
                contact.setMemo(contactObj.getString("메모"));

                contacts.add(contact);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

 */
}