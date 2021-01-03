package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

public class AddActivity extends AppCompatActivity {
    EditText name;
    EditText phonenumber;
    EditText relationship;
    EditText memo;
    Button button;
    Contact contact = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fragment);
        Contact contact=new Contact();
        button=(Button)findViewById(R.id.Confirmbutton);

        name= (EditText)findViewById(R.id.Editname);
        phonenumber= (EditText)findViewById(R.id.Editphonenumber);
        relationship= (EditText)findViewById(R.id.Editrelationship);
        memo= (EditText)findViewById(R.id.Editmemo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name 말고 Editname 써줘도 되지 않나?
                //TODO: 에러케이스 알림 출력
                contact.setName(name.getText().toString());
                contact.setNumber(phonenumber.getText().toString());
                contact.setRelationship(relationship.getText().toString());
                contact.setMemo(memo.getText().toString());
                System.out.println(contact.getName());
                //TODO: contact.json 파일에 json 들어갈 수 있도록 구현
                // UseGSONapitoConvertJavaOBJtoJASONstring.main(contact);
                //Toast myToast=Toast.makeText(this.getApplicationContext(), "end input contact", Toast.LENGTH_SHORT);
                //myToast.show();
                openMainActivity();
            }
        });

    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}