package com.uit.smarthomecontrol.models;

import java.util.ArrayList;

/**
 * Created by tensh on 11/10/2015.
 */
public class RoomItem {
    private String id;
    private String roomName;

    private RoomItem() {
    }

    public RoomItem(String _id, String _roomName){
        this.setId(_id);
        this.setRoomName(_roomName);
    }
    public static RoomItem newRoomItem() {
        return new RoomItem();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
