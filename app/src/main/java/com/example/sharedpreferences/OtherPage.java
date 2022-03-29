package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OtherPage extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        TextView t1,t2;
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.email);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String name = sp.getString("name","");
        String email = sp.getString("email","");

        t1.setText(name);
        t2.setText(email);


    }
}
