package com.uit.smarthomecontrol.models;

/**
 * Created by tensh on 11/17/2015.
 */
public class Alarm {
    private String time;
    private boolean state;
    public Alarm(String name, boolean state){
        this.time = name;
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
