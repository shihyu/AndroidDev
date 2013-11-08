package com.demo.android.trainstation;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;

public class Main extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        MapView map = new MapView(this, "0vUBBiZoYQvqxxLi0pgax3xP_ccG6zB-WhI4U5Q");
//        setContentView(map);
        findViews();
        setupMap();
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private MapView map;
    private MapController controller;

    private void findViews() {
            map = (MapView) findViewById(R.id.map);
            controller = map.getController();
    }

    private void setupMap() {
            GeoPoint station_taipei = new GeoPoint(
                            (int) (25.047192 * 1000000),
                            (int) (121.516981 * 1000000)
            );
            map.setTraffic(false);
            map.setBuiltInZoomControls(true);
            controller.setZoom(17);
            controller.animateTo(station_taipei);
    }
}