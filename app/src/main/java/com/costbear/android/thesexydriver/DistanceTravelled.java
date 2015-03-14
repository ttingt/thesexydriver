package com.costbear.android.thesexydriver;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Toast;

/**
 * Created by raykim on 3/14/15.
 */
public class DistanceTravelled extends MainActivity {
    LocationManager mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    LocationListener mLocListener = new MyLocationListener();
    mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,mLocListener);

    public class MyLocationListener implements LocationListener {
        stopButton.setOnClickListener(new

        OnClickListener() {
            @Override
            public void onClick (View v){
                // instantiate the location manager, note you will need to request permissions in your manifest
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // get the last know location from your location manager.
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                // now get the lat/lon from the location and do something with it.
                nowDoSomethingWith(location.getLatitude(), location.getLongitude());
            }
        }

        );
    }
}
