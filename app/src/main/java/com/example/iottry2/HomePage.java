package com.example.iottry2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.iottry2.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {

    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new homeFragment());

        binding.bottomNav.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new homeFragment());
                    break;
                case R.id.search:
                    replaceFragment(new searchFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new profileFragment());
                    break;
                case R.id.IOT:
                    replaceFragment(new IOTFragment());
                    break;


            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}