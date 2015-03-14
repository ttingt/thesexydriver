package com.costbear.android.thesexydriver;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by raykim on 3/14/15.
 */
public class GPSreal extends MainActivity {
    private TextView gpsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentspeed);
        gpsTextView = (TextView) findViewById(R.id.current_speed_textView);
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this .getSystemService(Context.LOCATION_SERVICE);


        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {


                    public void onLocationChanged(Location location) {

                        gpsTextView.setText(
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


}
