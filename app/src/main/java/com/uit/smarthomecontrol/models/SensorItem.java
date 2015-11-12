package com.uit.smarthomecontrol.models;

/**
 * Created by tensh on 11/10/2015.
 */
public class SensorItem {
    private String sensorName;
    private String id;
    private String pinGPIO;
    private String keySpeech;
    private String stateCurrent;
    private boolean hasSensor;
    private String pinSensor;

    private SensorItem() {
    }

    public SensorItem(String _id, String _name, String _pinGPIO, String _keySpeech, String _state, boolean _hasSensor, String _pinSensor){
        this.setId(_id);
        this.setSensorName(_name);
        this.setKeySpeech(_keySpeech);
        this.setHasSensor(_hasSensor);
        this.setPinGPIO(_pinGPIO);
        this.setStateCurrent(_state);
        this.setPinSensor(_pinSensor);
    }

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
}

