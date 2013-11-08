package com.demo.android.mylocation;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation extends Activity implements LocationListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        updateStat();
    }

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
    private LocationManager mgr;
    String best;
    
    private void updateStat() {
        mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Location location = mgr.getLastKnownLocation("gps");
        Criteria criteria = new Criteria();
        best = mgr.getBestProvider(criteria, true);
        Location location = mgr.getLastKnownLocation(best);
        
        // pop up
        StringBuffer msg = new StringBuffer();
        msg.append("Latitude: ");
        msg.append(Double.toString(location.getLatitude()));
        msg.append(", Longitude: ");
        msg.append(Double.toString(location.getLongitude()));
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mgr.requestLocationUpdates(best, 60000, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mgr.removeUpdates(this);
    }

}