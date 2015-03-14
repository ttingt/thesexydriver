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
    private double[] accelerationData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, 5);

        accelerationTextView = (TextView) findViewById(R.id.acceleration_xyz_textView);
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

        accelerationTextView.setText("X:" + accelerationX+
                "\nY: " + accelerationY +
                "\nZ: " + accelerationZ
                "\nVector of X and Y: "+ accelerationVector); //These are the X,Y,Z accelerations in m/s^2

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
        accelerationVector = Math.sqrt(Math.pow(accelerationX,2) + Math.pow(accelerationY,2));//square x and y
        return accelerationVector;
    }


//    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
//            accelVals = lowPass( event.values, accelVals );
//
//        // use smoothed accelVals here; see this link for a simple compass example:
//        // http://www.codingforandroid.com/2011/01/using-orientation-sensors-simple.html
//    }
//
//    /**
//     * @see http://en.wikipedia.org/wiki/Low-pass_filter#Algorithmic_implementation
//     * @see http://en.wikipedia.org/wiki/Low-pass_filter#Simple_infinite_impulse_response_filter
//     */
//    protected float[] lowPass( float[] input, float[] output ) {
//        if ( output == null ) return input;
//
//        for ( int i=0; i<input.length; i++ ) {
//            output[i] = output[i] + ALPHA * (input[i] - output[i]);
//        }
//        return output;
//    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
