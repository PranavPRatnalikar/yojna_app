package com.example.iottry2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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

public class IotPage extends AppCompatActivity {

    ListView myListview;
    ArrayList<String> list = new ArrayList<>();

    DatabaseReference mRef;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


 //   private DatabaseReference mDatabase;

    // Switch led1,led2;
//     private TextView;
//     humidity,temp,heartrate, gasPpm;
     Button getData;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot_page);

        //myListview = (ListView)findViewById(R.id.listview1);
//        humidity=findViewById(R.id.tvHumidity);
//        gasPpm = findViewById(R.id.tvGasppm);
//        temp = findViewById(R.id.tvTemp);
//        heartrate = findViewById(R.id.tvHeartrate);
        getData=findViewById(R.id.btn);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("/IOT");

       // mRef = FirebaseDatabase.getInstance().getReference("/Users/user2");

//        ArrayAdapter<String> myArrayadapter = new ArrayAdapter<>(IotPage.this, android.R.layout.simple_list_item_1,list);
//        myListview.setAdapter(myArrayadapter);

//        mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                String str = snapshot.getValue(String.class);
//                list.add(str);
//                myArrayadapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                myArrayadapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });




       // mDatabase = FirebaseDatabase.getInstance().getReference("/Components");

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })

getData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getDataFromFirebase();

    }
});
    }

    private void getDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value = snapshot.child("Connections").getValue(String.class);


//                String sHumidity = snapshot.child("/Humidity").getValue(String.class);
//                String sTemp = snapshot.child("Temperature").getValue(String.class);
//                String sHeartrate = snapshot.child("HeartRate").getValue(String.class);
//                String sGasppm = snapshot.child("Gasppm").getValue(String.class);






//                humidity.setText(value);
//                 humidity.setText(sHumidity + "%");
//                 temp.setText(sTemp + " °C");
//                 heartrate.setText(sHeartrate + " BPM");
//                 gasPpm.setText(sGasppm + " PPM");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(IotPage.this, "FAILED", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void connect(View view){
        
        

       // connectFirebase();
    }

//    private void connectFirebase() {
//        Boolean s1 = led1.isChecked();
//        Boolean s2 = led2.isChecked();
//
//       // User user = new User();
//
////        int humi = 0;
////        humidity.setText(humi);
//        //mDatabase.child(user.getUserID()).child("led").setValue(s1);
//
//        mDatabase.child("/Led").setValue(s1);
//         mDatabase.child("/Led2").setValue(s2);
//        String s = mDatabase.child("humi").getKey();
//        humidity.setText(s);
//      //  Components components = new Components(s1,s2,humi);
//
//    }



}