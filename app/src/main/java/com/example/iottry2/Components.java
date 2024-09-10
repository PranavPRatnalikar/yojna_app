package com.example.iottry2;

import android.content.Intent;

public class Components {
   // private boolean led1,led2;
    private Integer humidity;

    public Components(){

    }

    public Components(boolean led1, boolean led2, Integer humidity) {
//        this.led1 = led1;
//        this.led2 = led2;
        this.humidity = humidity;
    }

//    public boolean isLed1() {
//        return led1;
//    }
//
//    public boolean isLed2() {
//        return led2;
//    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
