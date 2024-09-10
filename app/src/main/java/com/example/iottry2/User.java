package com.example.iottry2;

public class User {
    private String name;
    private String email;
//    private String password;
    private String location;
    private String reqSpinner;

    private String latLong;







    public User() {
    }

    public User(String name,String email,String location,String reqSpinner,String latLong) {
        this.location=location;
        this.name = name;
        this.email=email;
//        this.password=password;
        this.reqSpinner = reqSpinner;
        this.latLong = latLong;

    }
    public String getReqSpinner() {
        return reqSpinner;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public String getPassword() {
//        return password;
//    }

    public String getLocation() {
        return location;
    }
    public String getLatLong(){return latLong;}
}
