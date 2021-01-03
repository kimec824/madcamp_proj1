package com.example.myapplication;


import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Contactlist extends ViewModel {
    ArrayList<Contact> contacts = new ArrayList<>();
    //MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();

    public void main(){
        /*JSONObject one = null;
        one.put("name","조범석");one.put("number","01020535403");one.put("relationship","나");one.put("memo","");
        JSONObject two = null;
        two.put("name","조우열");two.put("number","01062105403");two.put("relationship","가족");two.put("memo","");
        JSONObject three = null;
        three.put("name","최윤정");three.put("number","01041004826");three.put("relationship","가족");three.put("memo","");*/
        Contact one=new Contact();
        one.setName("조범석");one.setNumber("01020535403");one.setRelationship("나");one.setMemo("");
        Contact two=new Contact();
        two.setName("조우열");two.setNumber("01062105403");two.setRelationship("가족");two.setMemo("");
        Contact three=new Contact();
        three.setName("최윤정");three.setNumber("01041004826");three.setRelationship("가족");three.setMemo("");
        contacts.add(one);
        contacts.add(two);
        contacts.add(three);
    }

    public void addContacts(Contact contact){
        contacts.add(contact);
    }
    public Contact getContacts(int i){
        return getContacts(i);
    }
}
