package com.demo.android.mylocation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class Main extends Activity implements LocationListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    private LocationManager mgr;
    private String best;
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LocationManager status = (LocationManager)(this.getSystemService(Context.LOCATION_SERVICE));
		
		if(status.isProviderEnabled(LocationManager.GPS_PROVIDER)||status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Criteria criteria = new Criteria();
		    best = mgr.getBestProvider(criteria, true);
//		    mgr.requestLocationUpdates("gps", 60000, 1, this);
//		    Location location = mgr.getLastKnownLocation("gps");
		    mgr.requestLocationUpdates(best, 60000, 1, this);
		    Location location = mgr.getLastKnownLocation(best);
	        if (location != null) {
		        // pop up
		        StringBuffer msg = new StringBuffer();
		        msg.append("Latitude: ");
		        msg.append(Double.toString(location.getLatitude()));
		        msg.append(", Longitude: ");
		        msg.append(Double.toString(location.getLongitude()));
		        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	        } else {
	        	Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show();
		    }
		} else {
			new AlertDialog.Builder(Main.this)
	       	.setTitle("No Location Service")
	       	.setMessage("Please enable location service before use this app")
	       	.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			   	public void onClick(DialogInterface dialog, int whichButton) {  
			   	    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			   	}
	       	})
	       	.show();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mgr.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}