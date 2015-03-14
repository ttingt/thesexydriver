package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 * I have getters for acceleration in a different class - yves
 */
public class BrakePoint {
    private double accelerationVector;
    private AccelerometerTestActivity accelerometer;
    private Date date;
    private float lat;
    private float lng;
    private int breakCount;

    public BrakePoint(AccelerometerTestActivity accelerometer, float lat, float lng) {
        this.accelerometer = accelerometer;
        this.date = new Date();
        this.lat = lat;
        this.lng = lng;
    }

    public Date getDate() {
        return date;
    }
    public float getLat() {
        return lat;
    }
    public float getLng() {
        return lng;
    }
    public void breaks
}
