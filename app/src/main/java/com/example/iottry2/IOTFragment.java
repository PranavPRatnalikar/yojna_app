package com.example.iottry2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class IOTFragment extends Fragment {

    ListView myListview;
    ArrayList<String> list = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView humidity,temperature,heartrate,gasppm;
    Button getData;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_i_o_t, container, false);

        humidity=view.findViewById(R.id.tvHumidity);
        temperature = view.findViewById(R.id.tvTemp);
        heartrate = view.findViewById(R.id.tvHeartrate);
        gasppm= view.findViewById(R.id.tvGasppm);
        getData=view.findViewById(R.id.btn);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("/IOT");


        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromFirebase();

            }
        });

        return view;
    }
    private void getDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sHumidity = snapshot.child("/Humidity").getValue(String.class);
                String sTemp = snapshot.child("Temperature").getValue(String.class);
                String sHeartrate = snapshot.child("HeartRate").getValue(String.class);
                String sGasppm = snapshot.child("Gasppm").getValue(String.class);

                //Something
                humidity.setText(sHumidity + "%");
                temperature.setText(sTemp + "°C");
                heartrate.setText(sHeartrate + " BPM");
                gasppm.setText(sGasppm + " PPM");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "FAILED", Toast.LENGTH_SHORT).show();

            }
        });
    }
}