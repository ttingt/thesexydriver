package com.costbear.android.thesexydriver;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This file is still being implemented
 * @author yves
 * @version 1.0
 */
public class AccelerometerTestActivity extends ActionBarActivity implements SensorEventListener{

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private TextView accelerationTextView;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    private double accelerationVector;
    private BrakePoint breakPoint;

    private double mAccel; //acceleration apart from gravity
    private double mAccelCurrent; //acceleration including gravity
    private double mAccelLast; //last acceleration including gravity
    private float[] accelVals;
    private float alpha = 0.5f;
    long lastSaved = System.currentTimeMillis();
    static int ACCE_FILTER_DATA_MIN_TIME = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        accelerationTextView = (TextView) findViewById(R.id.acceleration_xyz_textView);
        breakPoint = new BrakePoint(this,0,0);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            accelerationX = event.values[0];
            accelerationY = event.values[1];
            accelerationZ = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = Math.sqrt(Math.pow(accelerationX, 2) + Math.pow(accelerationY, 2) +
                    Math.pow(accelerationZ, 2));
            double delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; //perform a low-cut filter
            breakPoint.brakes();


            accelerationTextView.setText("X:" + accelerationX +
                    "\nY: " + accelerationY +
                    "\nZ: " + accelerationZ +
                    "\nmAccel: " + mAccel +
                    "\nBreakCount" + breakPoint.getBreakCount()); //These are the X,Y,Z accelerations in m/s^2
    }

    public double getmAccel() {
        return mAccel;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public double getAccelerationZ() {
        return accelerationZ;
    }

    public double getAccelerationVector() {
        accelerationVector = Math.sqrt(Math.pow(accelerationX,2) + Math.pow(accelerationY,2) +
                Math.pow(accelerationZ,2));//square x and y and z
        return accelerationVector;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
