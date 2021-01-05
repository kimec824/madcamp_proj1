package com.example.myapplication;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Fragment1Activity extends AppCompatActivity {
    String name;
    String number;
    ContactList contacts = ContactList.getInstance();
    EditText nameView;
    EditText numberView;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            System.out.println(name);
            number = extras.getString("number");
            System.out.println(number);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment1);

        nameView = (EditText) findViewById(R.id.name);
        numberView = (EditText) findViewById(R.id.number);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        nameView.setText(name);
        numberView.setText(number);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(name);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (!(name.equals(nameView.getText().toString()) && number.equals(numberView.getText().toString()))) {
            deleteContact(name);

            CreateContact cc = new CreateContact();
            cc.createContact(nameView, numberView, getApplicationContext());

            Toast.makeText(getApplicationContext(), "Contact modified", Toast.LENGTH_SHORT).show();
        }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

    }

    public void deleteContact(String name) {
        ContentResolver cr = getContentResolver();
        String where = ContactsContract.Data.DISPLAY_NAME + " = ? ";
        String[] params = new String[] {name};

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(where, params)
                .build());
        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}