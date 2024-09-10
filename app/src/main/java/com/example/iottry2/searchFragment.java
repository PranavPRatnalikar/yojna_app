package com.example.iottry2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class searchFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    List<Item> itemList = new ArrayList<>();

    ArrayList<String> list2 = new ArrayList<>();
    DatabaseReference mRef;
    String path1="Cleaner";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ///

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Spinner sp = view.findViewById(R.id.spinner1);
        Button search = view.findViewById(R.id.btnSearch);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Requirements, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter2);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                path1 = arrayAdapter2.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ///
        // Inflate the layout for this fragment
//        View view =  inflater.inflate(R.layout.fragment_search, container, false);
//
//
//
//        Spinner sp = (Spinner)view.findViewById(R.id.spinner1);
//        ListView lv= view.findViewById(R.id.custom_listview);
//        Button search = view.findViewById(R.id.btnSearch);
//
//         mRef= FirebaseDatabase.getInstance().getReference("/JOBS").child(path1);
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,list2);
//        lv.setAdapter(arrayAdapter);
//
//        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(),R.array.Requirements, android.R.layout.simple_spinner_item);
//
//        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(arrayAdapter2);
//
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String s = arrayAdapter2.getItem(position).toString();
//                path1 = s;
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///
                mRef = FirebaseDatabase.getInstance().getReference("/JOBS").child(path1);
                itemList.clear();

                mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String name = snapshot.child("name").getValue(String.class);
                        String email = snapshot.child("phone").getValue(String.class);
//                        String location = snapshot.child("location").getValue(String.class);
                        Item item = new Item(name, email);
                        itemList.add(item);
                        itemAdapter.notifyDataSetChanged();
                    }
                ///
//                mRef= FirebaseDatabase.getInstance().getReference("/JOBS").child(path1);
//                list2.clear();
//
//                mRef.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                        //String str = snapshot.child(path1).getValue(String.class);
//                        for(DataSnapshot dsp : snapshot.getChildren()){
//                            String s="";
//                            if(dsp.getKey().equals("name")) { s = dsp.getValue().toString();
//                                list2.add(s);
//                            }
//                        }
//                        arrayAdapter.notifyDataSetChanged();
//                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        itemAdapter.notifyDataSetChanged();

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


        return view;
    }
}