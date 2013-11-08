package com.demo.android.twstation;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrainStation extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setupMap();
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private MapView map;
	private MapController mc;
	private ViewGroup zoom;
	
	private void findViews(){
		map = (MapView) findViewById(R.id.map);
		mc = map.getController();
		zoom = (ViewGroup) findViewById(R.id.zoom);
		zoom.addView(map.getZoomControls());
	}
	
	private MyLocationOverlay mylayer;
	
	private void setupMap(){
		GeoPoint station_taipei = new GeoPoint(
				(int) (25.047192 * 1E6),
				(int) (121.516981 * 1000000)
		);
		//map.setSatellite(true);
		map.setTraffic(true);
		mc.setZoom(17); 
		//mc.animateTo(station_taipei);
		List<Overlay> overlays = map.getOverlays();
		Drawable pin=getResources().getDrawable(android.R.drawable.btn_star_big_on);
		pin.setBounds(-pin.getMinimumWidth(), -pin.getMinimumHeight(), 0, 0);
		/*Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.w0309);
		LandMark mark = new LandMark(bmp, station_taipei);
		ml.add(mark);*/
		mylayer = new MyLocationOverlay(this, map);
		mylayer.runOnFirstFix(
				new Runnable() { public void run() {
		            mc.animateTo(mylayer.getMyLocation());
				}}
			);
		LandMarkOverlay marklayer = new LandMarkOverlay(pin);
		/*LandMark mark = new LandMark(bmp, overlay.getMyLocation());
		ml.add(mark);
		//overlay.enableCompass(); // no effect in emulator 
		overlay.runOnFirstFix(new Runnable() { 
			public void run() { 
				// Zoom in to current location 
				map.setTraffic(true);
				mc.setZoom(17);
				mc.animateTo(overlay.getMyLocation()); 
			} 
		}); */
		//mc.animateTo(myoverlay.getMyLocation()); 
		
		//verlays.add(mylayer);
		//overlays.add(marklayer);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//myoverlay.enableCompass();
		mylayer.enableMyLocation();
	}
	
	@Override
	protected void onStop() {
		mylayer.disableMyLocation();
		super.onStop();
	}
		
	protected static final int MENU_TAIPEI = Menu.FIRST;
	protected static final int MENU_TAICHUNG = Menu.FIRST+1;
	protected static final int MENU_KAOSHONG = Menu.FIRST+2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_TAIPEI, 0, "台北");
		menu.add(0, MENU_TAICHUNG, 0, "台中");
		menu.add(0, MENU_KAOSHONG, 0, "高雄");
		return true;
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
	
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
			case MENU_TAIPEI:
				mc.animateTo(station_taipei);
				break;
			case MENU_TAICHUNG:
				mc.animateTo(station_taichung);
				break;
			case MENU_KAOSHONG:
				mc.animateTo(station_kaoshong);
				break;
		}
		return true;	
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {	
        if (keyCode == KeyEvent.KEYCODE_I) {
            // Zooming In
        	mc.setZoom(Math.min(map.getMaxZoomLevel(), map.getZoomLevel() + 1));
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_O) {
            // Zooming Out
        	mc.setZoom(Math.max(15, map.getZoomLevel() - 1));
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
	
	private class LandMarkOverlay extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> items = new ArrayList<OverlayItem>();
		
		public LandMarkOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			// TODO Auto-generated constructor stub
			items.add(new OverlayItem(
					station_taipei,
					"台北",
					"台北車站"));
			items.add(new OverlayItem(
					station_taichung,
					"台中",
					"台中車站"));
			items.add(new OverlayItem(
					station_kaoshong,
					"高雄",
					"高雄車站"));
			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return items.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return items.size();
		}
		
	    @Override
	    protected boolean onTap(int pIndex) {
	        Toast.makeText(TrainStation.this,
	            "這裡是" + items.get(pIndex).getSnippet(),
	            Toast.LENGTH_LONG).show();  
	        return true;
	    }
	}
}

