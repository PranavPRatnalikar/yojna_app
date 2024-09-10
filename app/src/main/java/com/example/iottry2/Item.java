package com.example.iottry2;
public class Item {
    private String name;
    private String email;
//    private String location;

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
}
