package com.example.iottry2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class profileFragment extends Fragment {

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name = view.findViewById(R.id.tvname);
        TextView email = view.findViewById(R.id.tvemail);

        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String n = sharedPreferences.getString(KEY_NAME,null);
        String e = sharedPreferences.getString(KEY_EMAIL,null);

        if(n!=null || e!=null)
        {
            name.setText(n);
            email.setText(e);
        }

        return view;
    }
}