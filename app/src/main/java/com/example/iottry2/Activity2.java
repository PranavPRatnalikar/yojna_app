package com.example.iottry2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView tvname = findViewById(R.id.nameTV);

        String n = sharedPreferences.getString(KEY_NAME,null);

        if(n!=null){
            tvname.setText(n);
        }


    }
}