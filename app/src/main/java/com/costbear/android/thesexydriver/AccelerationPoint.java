package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 */
public class AccelerationPoint {
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    private Date date;
    private float lat;
    private float lng;

    public AccelerationPoint(double accelerationX, double accelerationY, double accelerationZ, float lat, float lng) {
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        this.accelerationZ = accelerationZ;
        this.date = new Date();
        this.lat = lat;
        this.lng = lng;
    }

    public Date getDate() { return date; }
    public float getLat() { return lat; }
    public float getLng() { return lng; }
}
