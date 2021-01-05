package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddActivity extends AppCompatActivity {
    EditText nameText;
    String name = "";
    EditText phonenumberText;
    //EditText relationship;
    //EditText memo;
    Button button;
    ContactList contacts = ContactList.getInstance();
    CreateContact cc = new CreateContact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fragment);

        nameText= (EditText)findViewById(R.id.Editname);
        phonenumberText= (EditText)findViewById(R.id.Editphonenumber);
        //relationship= (EditText)findViewById(R.id.Editrelationship);
        //memo= (EditText)findViewById(R.id.Editmemo);

        button = (Button)findViewById(R.id.Confirmbutton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nameText.getText() == null) {
                    Toast.makeText(getApplicationContext(), "The name field does not exist.", Toast.LENGTH_SHORT).show();
                } else {
                    name = nameText.getText().toString().trim();

                    if (contacts.exists(name)) {
                        Toast.makeText(getApplicationContext(), "The name " + name + " already exists in the contact.", Toast.LENGTH_SHORT).show();
                    } else if (name.equals("")) {
                        Toast.makeText(getApplicationContext(), "The name field does not exist.", Toast.LENGTH_SHORT).show();
                    } else {
                        cc.createContact(nameText, phonenumberText, getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        getApplicationContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }
            }
        });
    }

}

