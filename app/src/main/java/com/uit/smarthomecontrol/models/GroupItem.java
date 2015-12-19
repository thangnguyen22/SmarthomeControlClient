package com.uit.smarthomecontrol.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tensh on 12/10/2015.
 */
public class GroupItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String groupName;
    private String keyword;
    private String state;
    private ArrayList<SensorItem> sensorItems;

    public GroupItem (String _id, String _groupName, String _keyword, String _state, ArrayList<SensorItem> _sensorItems){
        this.setId(_id);
        this.setGroupName(_groupName);
        this.setKeyword(_keyword);
        this.setSensorItems(_sensorItems);
        this.setState(_state);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<SensorItem> getSensorItems() {
        return sensorItems;
    }

    public void setSensorItems(ArrayList<SensorItem> sensorItems) {
        this.sensorItems = sensorItems;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
