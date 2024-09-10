package com.example.iottry2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class loginPage extends AppCompatActivity {

    private Button reg;
    private EditText email,password;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        reg = findViewById(R.id.btnReg);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etpassword);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(areAllFieldsFilled()) {
                    Intent intent = new Intent(loginPage.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(loginPage.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private boolean areAllFieldsFilled() {
        return !TextUtils.isEmpty(email.getText().toString().trim()) &&
                !TextUtils.isEmpty(password.getText().toString().trim());
    }
}