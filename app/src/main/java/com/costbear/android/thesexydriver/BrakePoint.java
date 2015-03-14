package com.costbear.android.thesexydriver;

import java.util.Date;

/**
 * Created by TING on 2015-03-13.
 * I have getters for acceleration in a different class - yves
 */
public class BrakePoint {
    private AccelerationManagerActivity accelerometer;
    private Date date;
    private float lat;
    private float lng;

    private int breakCount = 0;

    public BrakePoint(AccelerationManagerActivity accelerometer, float lat, float lng) {
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

    /**
     * This method should be called every time sensor is changed
     */
    public void brakes() {
        if(accelerometer.getmAccel() < -10) {
            breakCount ++;
        }
    }

    public int getBreakCount() {
        return breakCount;
    }

    /**
     * This method should be called every time sensor is changed
     */
    public double timeForBrake(){
        double startTime = 0;
        double endTime = 0;
        double difference;
        if(accelerometer.getmAccel() < -10) {
            startTime = System.currentTimeMillis();
        }
        if (accelerometer.getmAccel() > 0) {
            endTime = System.currentTimeMillis();
        }
        difference = endTime - startTime;
        return difference;
    }

}
