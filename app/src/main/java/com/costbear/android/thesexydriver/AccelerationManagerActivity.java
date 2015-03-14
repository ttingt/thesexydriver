package com.costbear.android.thesexydriver;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

/**
 * This file is still being implemented
 * @author yves
 * @version 1.0
 */
public class AccelerationManagerActivity extends ActionBarActivity implements SensorEventListener{

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private TextView speedingRatingTextView;
    private TextView brakingRatingTextView;
    private TextView ratingTextView;
    private TextView ratingMsgTextView;
    private Button stop;

    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    private BrakePoint brakePoint;

    private double mAccel; //acceleration apart from gravity
    private double mAccelCurrent; //acceleration including gravity
    private double mAccelLast; //last acceleration including gravity

    private double latitude;
    private double longitude;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_view);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        speedingRatingTextView = (TextView) findViewById(R.id.speedingrating);
        brakingRatingTextView = (TextView) findViewById(R.id.brakingrating);
        ratingTextView = (TextView) findViewById(R.id.rating);
        ratingMsgTextView = (TextView) findViewById(R.id.ratingmsg);
        brakePoint = new BrakePoint(this,0,0);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        LocationManager locationManager = (LocationManager) this .getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {


            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                speedingRatingTextView.setText(
                        "Current speed:" + location.getSpeed());

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {

            }

            public void onProviderDisabled(String provider) {

            }

        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
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
            brakePoint.brakes();
            brakingRatingTextView.setText("Brake Rating " + brakeRating() +brakePoint.getBreakCount()); //These are the X,Y,Z accelerations in m/s^2
    }

    public double getmAccel() {
        return mAccel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer_test, menu);
        return true;
    }

    public int brakeRating() {
        int n = 0;
        int ratingSoFar = 10;
        if (brakePoint.getBreakCount() % 10 == 0) {
            n = brakePoint.getBreakCount()/10;
            ratingSoFar -= n;
        }
        return ratingSoFar;
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public int speedRating() {
        return 0;
    }

}
