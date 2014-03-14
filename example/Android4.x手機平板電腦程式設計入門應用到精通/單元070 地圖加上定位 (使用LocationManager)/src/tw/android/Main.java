package tw.android;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main extends MapActivity implements LocationListener {

	private LocationManager mLocationMgr;
	private String mBestLocationProv;

	private static String[][] locations = {
		{"台灣大學", "25.019943,121.542353"},
        {"清華大學", "24.795621,120.998153"},
        {"交通大學", "24.791704,121.003341"},
        {"成功大學", "23.000875,120.218017"}};

	private Spinner mSpnLocation;
	private MapView mMapView;
	private MapController mMapCtrl;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();

        setMapLocation();

        mLocationMgr = (LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria c = new Criteria();
        mBestLocationProv = mLocationMgr.getBestProvider(c, true);
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mLocationMgr.requestLocationUpdates(mBestLocationProv, 60000, 1, this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		mLocationMgr.removeUpdates(this);
		super.onStop();
	}

	private void setupViewComponent() {
        mSpnLocation = (Spinner) this.findViewById(R.id.spnLocation);
        mMapView = (MapView) findViewById(R.id.map);

        mMapCtrl = mMapView.getController();
    	mMapCtrl.setZoom(18);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < locations.length; i++)
        	adapter.add(locations[i][0]);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnLocation.setAdapter(adapter);
        mSpnLocation.setOnItemSelectedListener(mSpnLocationOnItemSelLis);
    }

    private OnItemSelectedListener mSpnLocationOnItemSelLis = new OnItemSelectedListener() {
 		@Override
		public void onItemSelected(AdapterView parent, View v, int position, long id) {
			// TODO Auto-generated method stub

 			setMapLocation();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
    };

    public void setMapLocation() {
    	int iSelect = mSpnLocation.getSelectedItemPosition();
    	String[] sLocation = locations[iSelect][1].split(",");
    	double dLat = Double.parseDouble(sLocation[0]);	// 南北緯
    	double dLon = Double.parseDouble(sLocation[1]);	// 東西經
    	GeoPoint gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
    	mMapCtrl.animateTo(gp);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		mMapCtrl.animateTo(new GeoPoint(
				(int)(location.getLatitude() * 1e6),
				(int)(location.getLongitude() * 1e6)));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		mLocationMgr.removeUpdates(this);
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		mLocationMgr.requestLocationUpdates(mBestLocationProv, 60000, 1, this);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Criteria c = new Criteria();
		mBestLocationProv = mLocationMgr.getBestProvider(c, true);
	}
}