package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 */
public class BrakePoint {
    private int measurement; //accelerometer measurement
    private Date date;
    private float lat;
    private float lng;

    public BrakePoint(int measurement, float lat, float lng) {
        this.measurement = measurement;
        this.date = new Date();
        this.lat = lat;
        this.lng = lng;
    }

    public int getMeasurement() { return measurement; }
    public Date getDate() { return date; }
    public float getLat() { return lat; }
    public float getLng() { return lng; }
}
