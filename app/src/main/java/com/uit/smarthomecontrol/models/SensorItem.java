package com.uit.smarthomecontrol.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tensh on 11/10/2015.
 */
public class SensorItem implements Parcelable {
    private String sensorName;
    private String id;
    private String pinGPIO;
    private String keySpeech;
    private String stateCurrent;
    private boolean hasSensor;
    private String pinSensor;
    private String roomName;

    private SensorItem() {
    }

    public SensorItem(String _id, String _name, String _pinGPIO, String _keySpeech, String _state, boolean _hasSensor, String _pinSensor, String _roomName){
        this.setId(_id);
        this.setSensorName(_name);
        this.setKeySpeech(_keySpeech);
        this.setHasSensor(_hasSensor);
        this.setPinGPIO(_pinGPIO);
        this.setStateCurrent(_state);
        this.setPinSensor(_pinSensor);
        this.setRoomName(_roomName);
    }

    protected SensorItem(Parcel in) {
        sensorName = in.readString();
        id = in.readString();
        pinGPIO = in.readString();
        keySpeech = in.readString();
        stateCurrent = in.readString();
        hasSensor = in.readByte() != 0;
        pinSensor = in.readString();
    }

    public static final Creator<SensorItem> CREATOR = new Creator<SensorItem>() {
        @Override
        public SensorItem createFromParcel(Parcel in) {
            return new SensorItem(in);
        }

        @Override
        public SensorItem[] newArray(int size) {
            return new SensorItem[size];
        }
    };

    public static SensorItem createSensorItem() {
        return new SensorItem();
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPinGPIO() {
        return pinGPIO;
    }

    public void setPinGPIO(String pinGPIO) {
        this.pinGPIO = pinGPIO;
    }

    public String getKeySpeech() {
        return keySpeech;
    }

    public void setKeySpeech(String keySpeech) {
        this.keySpeech = keySpeech;
    }

    public String getStateCurrent() {
        return stateCurrent;
    }

    public void setStateCurrent(String stateCurrent) {
        this.stateCurrent = stateCurrent;
    }

    public boolean isHasSensor() {
        return hasSensor;
    }

    public void setHasSensor(boolean hasSensor) {
        this.hasSensor = hasSensor;
    }

    public String getPinSensor() {
        return pinSensor;
    }

    public void setPinSensor(String pinSensor) {
        this.pinSensor = pinSensor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sensorName);
        dest.writeString(id);
        dest.writeString(pinGPIO);
        dest.writeString(keySpeech);
        dest.writeString(stateCurrent);
        dest.writeByte((byte) (hasSensor ? 1 : 0));
        dest.writeString(pinSensor);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

