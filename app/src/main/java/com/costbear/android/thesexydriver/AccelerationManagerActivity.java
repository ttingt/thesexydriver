package com.costbear.android.thesexydriver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This file is still being implemented
 * @author yves
 * @version 1.0
 */
public class AccelerationManagerActivity extends Activity implements SensorEventListener{

    private Sensor accelerometer;
    private SensorManager sensorManager;

    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;

    private int speedPtsCount;
    private List<AccelerationPoint> accelPts;
    private int speedRatingSoFar;
    private int brakePtsCount;
    private List<BrakePoint> brakePts;
    private int brakeRatingSoFar;

    private double mAccel; //acceleration apart from gravity
    private double mAccelCurrent; //acceleration including gravity
    private double mAccelLast; //last acceleration including gravity

    private ImageButton stopButton;

    private List<Location> locations;

    String provider = LocationManager.GPS_PROVIDER;
    List<Location> locs;

    TextView carInfo, driveSafe;

    public static double sumAccel;
    private int n;

    public static double sumDistance;

    public static double avgAccel;

    Car car;

    int year;
    String make;
    String model;
    int cylinders;
    String transmission;
    double fuelConsumption;
    int emissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driving_layout);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        brakePtsCount = 0;
        speedPtsCount = 0;
        speedRatingSoFar = 0;
        brakeRatingSoFar = 0;
        locs = new ArrayList<Location>();
        accelPts = new ArrayList<AccelerationPoint>();
        brakePts = new ArrayList<BrakePoint>();

        locations = new ArrayList<Location>();


        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        final LocationManager locationManager = (LocationManager) this .getSystemService(Context.LOCATION_SERVICE);

        sumAccel = 0;
        sumDistance =0;
        n = 0;

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        carInfo = (TextView) findViewById(R.id.carInfo);
        driveSafe = (TextView) findViewById(R.id.driveSafe);
        driveSafe.setTypeface(face);
        carInfo.setTypeface(face);


        //INTENTS EXTRAS FROM PROFILE ACTIVITY

        year = getIntent().getIntExtra("YEAR", 2000);
        make = getIntent().getStringExtra("MAKE");
        model = getIntent().getStringExtra("MODEL");
        cylinders = getIntent().getIntExtra("CYLINDERS", 4);
        transmission = getIntent().getStringExtra("TRANSMISSION");
        fuelConsumption = getIntent().getDoubleExtra("FUELCONSUMPTION", 0);
        emissions = getIntent().getIntExtra("EMISSIONS", 0);

        System.out.println(year + make + model + cylinders + transmission + fuelConsumption + emissions);

        carInfo.setText("The car you have selected is: "
                + year + " " + make + " " + model + " "
                + "Cylinders: " + cylinders +". Transmission: " + transmission);

        car = new Car(year, make, model, cylinders, transmission, fuelConsumption, emissions);


        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setSpeedRequired(true);
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        locationManager.getBestProvider(criteria, true);

        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {

            locations.add(location);
            AccelerationPoint newpt = new AccelerationPoint(location.getSpeed(), location.getLatitude(), location.getLongitude());
            updateSpeedRatingSoFar(newpt);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {

            }

            public void onProviderDisabled(String provider) {

            }

        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

        stopButton = (ImageButton) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopMeasurements();

                avgAccel();

                double tripConsumption = car.fuelConsumed();
                double tripEmissions = car.co2Emitted();

                for (int i = 1; i < locations.size(); i++) {
                    sumDistance += locations.get(i).distanceTo(locations.get(i-1));
                }

                Intent i = new Intent(AccelerationManagerActivity.this, SummaryActivity.class);
                i.putExtra("brakePtsCount", brakePtsCount);
                i.putExtra("brakeRatingSoFar", brakeRatingSoFar);
                i.putExtra("speedPtsCount", speedPtsCount);
                i.putExtra("speedRatingSoFar", speedRatingSoFar);
                i.putExtra("AverageAccelerometer", Math.abs(sumAccel / n));
                i.putExtra("SumDistance", sumDistance); //in m

                i.putExtra("fuelConsumed", tripConsumption);
                i.putExtra("co2Emitted", tripEmissions);

                startActivity(i);
                finish();
            }



        });
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
            BrakePoint bp = new BrakePoint(mAccel);
            updateBrakeRatingSoFar(bp);
            sumAccel += mAccel;
            n++;

    }

//   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer_test, menu);
        return true;
    }

   public int updateBrakeRatingSoFar(BrakePoint bp) {
        if (bp.getAccel() >-1) return brakeRatingSoFar;

        brakePts.add(bp);
        brakePtsCount++;

        int addFactor = (int) -bp.getAccel()*5;

        brakeRatingSoFar += addFactor;
        return brakeRatingSoFar;
    }

    public int updateSpeedRatingSoFar(AccelerationPoint ap) {
        accelPts.add(ap);
        speedPtsCount++;

        int addFactor = 0;

        if (ap.getSpeed()> 0 && ap.getSpeed()<50) {
            addFactor = 10;
        }
        if (ap.getSpeed()> 50 && ap.getSpeed()<60) {
            addFactor = 7;
        }
        if (ap.getSpeed()> 60 && ap.getSpeed()<80) {
            addFactor = 4;
        }
        if (ap.getSpeed()>= 80) {
            addFactor = 1;
        }

        speedRatingSoFar += addFactor;
        return speedRatingSoFar;
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

   public void stopMeasurements() {
       sensorManager.unregisterListener(this);
   }

   public void displaySummaryPage() {
       Intent i = new Intent(AccelerationManagerActivity.this, SummaryActivity.class);
       i.putExtra("brakePtsCount", brakePtsCount);
       i.putExtra("brakeRatingSoFar", brakeRatingSoFar);
       i.putExtra("speedPtsCount", speedPtsCount);
       i.putExtra("speedRatingSoFar", speedRatingSoFar);
       i.putExtra("AverageAccelerometer", sumAccel/n);
       i.putExtra("SumDistance", sumDistance/1000); //in kM

       startActivity(i);
       finish();
   }

    public void avgAccel() {
        avgAccel =  sumAccel / n;
    }


}
