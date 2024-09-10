package com.example.iottry2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

//    private TextView addressTextView;
    public String latLongStrUser;
    private double lat2=0;
    private double long2=0;



    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    //    private DatabaseReference mDatabase;
    private DatabaseReference mRef;
    private EditText name,email,locationet;
    private Button submitBtn,loginPg;
    private Spinner req;
    TextView tv;
    private String lat1="18";
    private String long1="73";

    RadioButton btnGetLoc;

    String reqSelected="Not Selected";
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        FirebaseApp.initializeApp(this);


//        tv=findViewById(R.id.tvTry1);

        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);
//        password=findViewById(R.id.etPassword);
        submitBtn=findViewById(R.id.BtnSubmit);
        locationet = findViewById(R.id.etlocAddress);
        loginPg= findViewById(R.id.btnTOloginpg);
        btnGetLoc=findViewById(R.id.radioBtn);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location lastLocation = locationResult.getLastLocation();
                updateLocationUI(lastLocation);
            }
        };

        requestLocationUpdates();

//        String address = getAddressFromLocation(this, lat2, long2);
//        if (address != null) {
//            locationet.setText(address);
//        } else {
//            locationet.setText("Address not found");
//        }





        req  = (Spinner) findViewById(R.id.spReq);
        mRef = FirebaseDatabase.getInstance().getReference("Customers");

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);



        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnGetLoc.setActivated(true);
                //location.setText(lat1+','+long1);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.Requirements, android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        req.setAdapter(adapter);


        req.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reqSelected= adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String str1 = snapshot.child("JOBS").child(reqSelected).child("a1").child("location").getValue(String.class); ///////////*****IMP
//                tv.setText(str1);
//
//            }
//6
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        loginPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,loginPage.class);
                startActivity(intent);

            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(areAllFieldsFilled()) {
                    Toast.makeText(MainActivity.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                    sendData(v);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name.getText().toString());
                    editor.putString(KEY_EMAIL, email.getText().toString());
                    editor.apply();


                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private boolean areAllFieldsFilled() {
        return !TextUtils.isEmpty(name.getText().toString().trim()) &&
                !TextUtils.isEmpty(email.getText().toString().trim());
    }

    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Update location every 10 seconds

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
            return;
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



//    private void updateLocationUI(Location location) {
//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            lat2=latitude;
//            long2=longitude;
//
//            String latitudeStr = String.valueOf(latitude);
//            String longitudeStr = String.valueOf(longitude);
//            lat1=latitudeStr;
//            long1=longitudeStr;
//            String locationStr = latitudeStr+" , "+longitudeStr;
//            //String locationStr = "Latitude: " + latitudeStr + "\nLongitude: " + longitudeStr;
//           if(btnGetLoc.isActivated()) locationet.setText(locationStr);
//
//        } else {
//            locationet.setText("Location unavailable");
//        }
//    }
private void updateLocationUI(Location location) {
    if (location != null) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        lat2 = latitude;
        long2 = longitude;

        String latitudeStr = String.valueOf(latitude);
        String longitudeStr = String.valueOf(longitude);
        lat1 = latitudeStr;
        long1 = longitudeStr;
        String locationStr = latitudeStr + " , " + longitudeStr;
        if (btnGetLoc.isActivated()){
//            locationet.setText(locationStr);

            latLongStrUser = locationStr;


            String address = getAddressFromLocation(MainActivity.this, latitude, longitude);
            locationStr = locationStr+"  "+address;
            if (address != null) {
                locationet.setText(locationStr);
            } else {
                locationet.setText("Address not found");
            }


        }

        // Get address from location

    } else {
        locationet.setText("Location unavailable");
    }
}

    //
    private String getAddressFromLocation(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                StringBuilder addressStringBuilder = new StringBuilder();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressStringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                return addressStringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error getting address", Toast.LENGTH_SHORT).show();
        }
        return null;
    }






    public void sendData(View view){ writeNewUser();}

    public void writeNewUser(){
        User user = new User(name.getText().toString(),email.getText().toString(),locationet.getText().toString(),reqSelected,latLongStrUser);


        // mDatabase.child("User").child(user.getEmail()).setValue(user);
//        mDatabase.child("Customres").child(reqSelected).child(user.getEmail()).setValue(user);
        mRef.child(reqSelected).child(user.getEmail()).setValue(user);
    }



}

///////////////////////////////////////////////////////////////////////////////////////
//
//public class MainActivity extends AppCompatActivity {
//
////    private DatabaseReference mDatabase;
//    private DatabaseReference mRef;
//    private EditText name,email,location;
//    private Button submitBtn,loginPg;
//    private Spinner req;
//    TextView tv;
//    private String lat1="18.5318636";
//    private String long1="73.8671354";
//
//    RadioButton btnGetLoc;
//
//    String reqSelected="Not Selected";
//    SharedPreferences sharedPreferences;
//
//    private static final String SHARED_PREF_NAME="mypref";
//    private static final String KEY_NAME="name";
//    private static final String KEY_EMAIL="email";
//
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        FirebaseApp.initializeApp(this);
//
//        tv=findViewById(R.id.tvTry1);
//
//        name=findViewById(R.id.etName);
//        email=findViewById(R.id.etEmail);
////        password=findViewById(R.id.etPassword);
//        submitBtn=findViewById(R.id.BtnSubmit);
//        location = findViewById(R.id.etuserID);
//        loginPg= findViewById(R.id.btnTOloginpg);
//        btnGetLoc=findViewById(R.id.radioBtn);
//
//
//
//         req  = (Spinner) findViewById(R.id.spReq);
//         mRef = FirebaseDatabase.getInstance().getReference("Customers");
//
//        //mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//
//        btnGetLoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                location.setText(lat1+','+long1);
//            }
//        });
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this,R.array.Requirements, android.R.layout.simple_spinner_item
//        );
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        req.setAdapter(adapter);
//
//
//        req.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                reqSelected= adapter.getItem(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
////        mDatabase.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String str1 = snapshot.child("JOBS").child(reqSelected).child("a1").child("location").getValue(String.class); ///////////*****IMP
////                tv.setText(str1);
////
////            }
////6
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
//
//        loginPg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,loginPage.class);
//                startActivity(intent);
//
//            }
//        });
//
//
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
//                sendData(v);
//                SharedPreferences.Editor editor=sharedPreferences.edit();
//                editor.putString(KEY_NAME,name.getText().toString());
//                editor.putString(KEY_EMAIL,email.getText().toString());
//                editor.apply();
//
//
//
//                Intent intent = new Intent(MainActivity.this,HomePage.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//    }
//
//
//
//    public void sendData(View view){ writeNewUser();}
//
//    public void writeNewUser(){
//        User user = new User(name.getText().toString(),email.getText().toString(),location.getText().toString(),reqSelected);
//
//
//       // mDatabase.child("User").child(user.getEmail()).setValue(user);
////        mDatabase.child("Customres").child(reqSelected).child(user.getEmail()).setValue(user);
//        mRef.child(reqSelected).child(user.getEmail()).setValue(user);
//    }
//
//
//
//}