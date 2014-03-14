package tw.android;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main extends MapActivity {

	private static String[][] locations = {
		{"台灣大學", "25.019943,121.542353"},
        {"清華大學", "24.795621,120.998153"},
        {"交通大學", "24.791704,121.003341"},
        {"成功大學", "23.000875,120.218017"}};

	private static String[] mapType = {
		"街道圖",
	 	"衛星圖"};

	public enum BasePoint {
		TOP_LEFT, TOP_CENTER, TOP_RIGHT,
		MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT,
		BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
	}

	private Spinner mSpnLocation,
					mSpnMapType;
	private MapView mMapView;
	private MapController mMapCtrl;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();

        setMapLocation();
    }
    
    private void setupViewComponent() {
        mSpnLocation = (Spinner) this.findViewById(R.id.spnLocation);
        mSpnMapType = (Spinner) this.findViewById(R.id.spnMapType);
        mMapView = (MapView) findViewById(R.id.map);

        mMapCtrl = mMapView.getController();
        mMapView.setBuiltInZoomControls(true);
    	mMapCtrl.setZoom(18);

    	List<Overlay> mapOverlays = mMapView.getOverlays();

        MapItemizedOverlay mapItemizedOverlay;
        OverlayItem overlayitem;
        Drawable drawable;
        GeoPoint gp;
        double dLat, dLon;
        String[] sLocation;
        
        // 第一個地標
        drawable = getResources().getDrawable(R.drawable.map_mark01);
        setDrawableBounds(drawable, BasePoint.MIDDLE_CENTER);
        drawable.setAlpha(100);		// 0(全透明)~255
        mapItemizedOverlay = new MapItemizedOverlay(drawable, this);
        sLocation = locations[0][1].split(",");
        dLat = Double.parseDouble(sLocation[0]);	// 南北緯
        dLon = Double.parseDouble(sLocation[1]);	// 東西經
        gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
        overlayitem = new OverlayItem(gp, locations[0][0], locations[0][0]);
    	mapItemizedOverlay.addOverlay(overlayitem);
    	mapOverlays.add(mapItemizedOverlay);

    	// 第二個地標
        drawable = getResources().getDrawable(R.drawable.map_mark02);
        setDrawableBounds(drawable, BasePoint.MIDDLE_CENTER);
        drawable.setAlpha(100);		// 0(全透明)~255
        mapItemizedOverlay = new MapItemizedOverlay(drawable, this);
        sLocation = locations[1][1].split(",");
    	dLat = Double.parseDouble(sLocation[0]);	// 南北緯
    	dLon = Double.parseDouble(sLocation[1]);	// 東西經
    	gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
    	overlayitem = new OverlayItem(gp, locations[1][0], locations[1][0]);
    	mapItemizedOverlay.addOverlay(overlayitem);
    	mapOverlays.add(mapItemizedOverlay);
        
        // 第三個地標
        drawable = getResources().getDrawable(R.drawable.map_mark03);
        setDrawableBounds(drawable, BasePoint.MIDDLE_CENTER);
        drawable.setAlpha(100);		// 0(全透明)~255
        mapItemizedOverlay = new MapItemizedOverlay(drawable, this);
        sLocation = locations[2][1].split(",");
    	dLat = Double.parseDouble(sLocation[0]);	// 南北緯
    	dLon = Double.parseDouble(sLocation[1]);	// 東西經
    	gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
    	overlayitem = new OverlayItem(gp, locations[2][0], locations[2][0]);
    	mapItemizedOverlay.addOverlay(overlayitem);
    	mapOverlays.add(mapItemizedOverlay);
        
        // 第四個地標
        drawable = getResources().getDrawable(R.drawable.map_mark04);
        setDrawableBounds(drawable, BasePoint.MIDDLE_CENTER);
        drawable.setAlpha(100);		// 0(全透明)~255
        mapItemizedOverlay = new MapItemizedOverlay(drawable, this);
        sLocation = locations[3][1].split(",");
    	dLat = Double.parseDouble(sLocation[0]);	// 南北緯
    	dLon = Double.parseDouble(sLocation[1]);	// 東西經
    	gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
    	overlayitem = new OverlayItem(gp, locations[3][0], locations[3][0]);
    	mapItemizedOverlay.addOverlay(overlayitem);
    	mapOverlays.add(mapItemizedOverlay);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < locations.length; i++)
        	adapter.add(locations[i][0]);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnLocation.setAdapter(adapter);
        mSpnLocation.setOnItemSelectedListener(mSpnLocationOnItemSelLis);

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < mapType.length; i++)
        	adapter.add(mapType[i]);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnMapType.setAdapter(adapter);
        mSpnMapType.setOnItemSelectedListener(mSpnMapTypeOnItemSelLis);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		int nextZoom, zoom;
		switch (keyCode) {
		case KeyEvent.KEYCODE_I:
			nextZoom = mMapView.getZoomLevel() + 1;
			zoom = nextZoom > mMapView.getMaxZoomLevel() ?
					mMapView.getMaxZoomLevel() : nextZoom;
			mMapCtrl.setZoom(zoom);
			break;
		case KeyEvent.KEYCODE_O:
			nextZoom = mMapView.getZoomLevel() - 1;
			zoom = nextZoom < 1 ?
					1 : nextZoom;
			mMapCtrl.setZoom(zoom);
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

	private OnItemSelectedListener mSpnMapTypeOnItemSelLis = new OnItemSelectedListener() {
 		@Override
		public void onItemSelected(AdapterView parent, View v, int position, long id) {
			// TODO Auto-generated method stub

 			switch (position) {
 			case 0:
 				mMapView.setSatellite(false);
 				break;
 			case 1:
 				mMapView.setSatellite(true);
 				break;
 			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
    };

    private void setDrawableBounds(Drawable d, BasePoint bp) {
		switch (bp) {
		case TOP_LEFT:
			d.setBounds(0,
						0,
						d.getMinimumWidth(),
						d.getMinimumHeight());
			break;
		case TOP_CENTER:
			d.setBounds(-d.getMinimumWidth()/2,
						0,
						d.getMinimumWidth()/2,
						d.getMinimumHeight());
			break;
		case TOP_RIGHT:
			d.setBounds(-d.getMinimumWidth(),
						0,
						0,
						d.getMinimumHeight());
			break;
		case MIDDLE_LEFT:
			d.setBounds(0,
						-d.getMinimumHeight()/2,
						d.getMinimumWidth()/2,
						d.getMinimumHeight()/2);
			break;
		case MIDDLE_CENTER:
			d.setBounds(-d.getMinimumWidth()/2,
						-d.getMinimumHeight()/2,
						d.getMinimumWidth()/2,
						d.getMinimumHeight()/2);
			break;
		case MIDDLE_RIGHT:
			d.setBounds(-d.getMinimumWidth(),
						-d.getMinimumHeight()/2,
						0,
						d.getMinimumHeight()/2);
			break;
		case BOTTOM_LEFT:
			d.setBounds(0,
						-d.getMinimumHeight(),
						d.getMinimumWidth(),
						0);
			break;
		case BOTTOM_CENTER:
			d.setBounds(-d.getMinimumWidth()/2,
						-d.getMinimumHeight(),
						d.getMinimumWidth()/2,
						0);
			break;
		case BOTTOM_RIGHT:
			d.setBounds(-d.getMinimumWidth(),
						-d.getMinimumHeight(),
						0,
						0);
			break;
		}
	}
}