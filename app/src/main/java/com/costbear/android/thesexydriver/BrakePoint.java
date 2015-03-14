package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 * I have getters for acceleration in a different class - yves
 */
public class BrakePoint {
    private double accel;
//    private Date date;
//    private float lat;
//    private float lng;

    public BrakePoint(double accel) {
        this.accel = accel;
//        this.date = new Date();
//        this.lat = lat;
//        this.lng = lng;
    }


    public double getAccel() { return accel; }
//    public Date getDate() {
//        return date;
//    }
//    public float getLat() {
//        return lat;
//    }
//    public float getLng() {
//        return lng;
//    }

}