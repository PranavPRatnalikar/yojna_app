package com.example.iottry2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.location.Location;
public class SearchLabours extends AppCompatActivity {




    ListView lv;
    Spinner sp;
    Button search;
    ArrayList<String> list2 = new ArrayList<>();
    DatabaseReference mRef;
    String path1="cleaner",latLongStrUser="";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_labours);

        sp = (Spinner)findViewById(R.id.spinner1);
//        lv= findViewById(R.id.custom_listview);
        search = findViewById(R.id.btnSearch);

        mRef= FirebaseDatabase.getInstance().getReference("/JOBS").child(path1);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SearchLabours.this, android.R.layout.simple_list_item_1,list2);
//        lv.setAdapter(arrayAdapter);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this,R.array.Requirements, android.R.layout.simple_spinner_item);

        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter2);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = arrayAdapter2.getItem(position).toString();
                path1 = s;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef= FirebaseDatabase.getInstance().getReference("/JOBS").child(path1);
                list2.clear();

                mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        //String str = snapshot.child(path1).getValue(String.class);
//                        for(DataSnapshot dsp : snapshot.getChildren()){
//                            String s="";
//                            if(dsp.getKey().equals("name")) { s = dsp.getValue().toString();
//                                list2.add(s);
//                            }
//                        }
//                        arrayAdapter.notifyDataSetChanged();

                        ///////

                        Map<String, String> userMap = new HashMap<>();
                        String userLocation = "";

                        for (DataSnapshot dsp : snapshot.getChildren()) {
                            if (dsp.getKey().equals("location")) {
                                userLocation = dsp.getValue().toString();
                            } else if (dsp.getKey().equals("name")) {
                                userMap.put("name", dsp.getValue().toString());
                            }
                        }

                        if (!userLocation.isEmpty()) {
                            userMap.put("location", userLocation);
                            float distance = calculateDistance(latLongStrUser, userLocation);
                            userMap.put("distance", String.valueOf(distance));
                            list2.add(userMap.get("name") + " - " + distance + " km");
                        }

                        // Sort the list based on the distance
                        Collections.sort(list2, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                float dist1 = Float.parseFloat(o1.split(" - ")[1].replace(" km", ""));
                                float dist2 = Float.parseFloat(o2.split(" - ")[1].replace(" km", ""));
                                return Float.compare(dist1, dist2);
                            }
                        });

                        arrayAdapter.notifyDataSetChanged();



                    ////
                    }


                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });








    }

    private float calculateDistance(String userLocation, String otherLocation) {
        String[] userLatLong = userLocation.split(",");
        String[] otherLatLong = otherLocation.split(",");

        Location userLoc = new Location("");
        userLoc.setLatitude(Double.parseDouble(userLatLong[0]));
        userLoc.setLongitude(Double.parseDouble(userLatLong[1]));

        Location otherLoc = new Location("");
        otherLoc.setLatitude(Double.parseDouble(otherLatLong[0]));
        otherLoc.setLongitude(Double.parseDouble(otherLatLong[1]));

        return userLoc.distanceTo(otherLoc) / 1000; // Convert meters to kilometers
    }


}