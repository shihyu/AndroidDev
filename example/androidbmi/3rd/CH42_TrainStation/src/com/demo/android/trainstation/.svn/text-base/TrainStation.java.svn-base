package com.demo.android.trainstation;

import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class TrainStation extends MapActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        MapView map = new MapView(this, "0vUBBiZoYQvqTl2CvMeeh-prsmDARpwWKtHottQ");
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
    private ViewGroup zoom;
    
    private void findViews() {
            map = (MapView) findViewById(R.id.map);
            controller = map.getController();
            
//            zoom = (ViewGroup) findViewById(R.id.zoom);
//            zoom.addView(map.getZoomControls());
    }

    private MyLocationOverlay mylayer;
    
    private void setupMap() {
//            GeoPoint station_taipei = new GeoPoint(
//                            (int) (25.047192 * 1000000),
//                            (int) (121.516981 * 1000000)
//            );
//            map.setTraffic(true);
//            map.setBuiltInZoomControls(true);
//            controller.setZoom(17);
//            controller.animateTo(station_taipei);
    	List<Overlay> overlays = map.getOverlays();
        mylayer = new MyLocationOverlay(this, map);
        mylayer.runOnFirstFix(new Runnable() {
            public void run() {
                // Zoom in to current location
                map.setTraffic(true);
                controller.setZoom(17);
                controller.animateTo(mylayer.getMyLocation());
            }
        });
        overlays.add(mylayer);
    }
    
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mylayer.enableMyLocation();
	}

    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mylayer.disableMyLocation();
	}

	protected static final int MENU_TAIPEI = Menu.FIRST;
    protected static final int MENU_TAICHUNG = Menu.FIRST+1;
    protected static final int MENU_KAOSHONG = Menu.FIRST+2;
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // TODO Auto-generated method stub
            menu.add(0, MENU_TAIPEI, 0, "台北");
            menu.add(0, MENU_TAICHUNG, 0, "台中");
            menu.add(0, MENU_KAOSHONG, 0, "高雄");
            return super.onCreateOptionsMenu(menu);
    }

    GeoPoint station_taipei = new GeoPoint(
                (int) (25.047192 * 1000000),
                    (int) (121.516981 * 1000000)
            );
    GeoPoint station_taichung = new GeoPoint(
                (int) (24.136895 * 1000000),
                    (int) (120.684975 * 1000000)
            );
    GeoPoint station_kaoshong = new GeoPoint(
                (int) (22.639359 * 1000000),
                    (int) (120.302628 * 1000000)
            );

    public boolean onOptionsItemSelected(MenuItem item) {
            // TODO Auto-generated method stub
            super.onOptionsItemSelected(item);
            switch(item.getItemId()) {
                    case MENU_TAIPEI:
                            controller.animateTo(station_taipei);
                            break;
                    case MENU_TAICHUNG:
                            controller.animateTo(station_taichung);
                            break;
                    case MENU_KAOSHONG:
                            controller.animateTo(station_kaoshong);
                            break;
            }
            return super.onOptionsItemSelected(item);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_I) {
            // Zooming In
            controller.setZoom(map.getZoomLevel() + 1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_O) {
            // Zooming Out
            controller.setZoom(map.getZoomLevel() - 1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_S) {
            // Switch to satellite view
            map.setSatellite(true) ;
            map.setTraffic(false);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_T) {
            // Switch on traffic overlays
            map.setSatellite(false) ;
            map.setTraffic(true);
            return true;
        }
        return false;
    }
}