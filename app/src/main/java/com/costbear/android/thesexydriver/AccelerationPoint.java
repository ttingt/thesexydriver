package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 */
public class AccelerationPoint {
    private double speed;
    private Date date;
    private double lat;
    private double lng;

    public AccelerationPoint(double speed, double lat, double lng) {
        this.speed = speed;
        this.date = new Date();
        this.lat = lat;
        this.lng = lng;
    }

    public double getSpeed() { return speed; }
    public Date getDate() { return date; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
}
