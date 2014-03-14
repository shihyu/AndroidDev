package tw.android;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MapItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private OverlayItem mOverlayItem;

	Context mContext;

	public MapItemizedOverlay(Drawable defaultMarker, Context context) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub
		
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlayItem;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, false);
	}

	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		// TODO Auto-generated method stub
//		OverlayItem item = mOverlayItems.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
//		dialog.setTitle(item.getTitle());
//		dialog.setMessage(item.getSnippet());
		dialog.setTitle(mOverlayItem.getTitle());
		dialog.setMessage(mOverlayItem.getSnippet());
		dialog.show();
//		return super.onTap(index);
		return super.onTap(0);
	}

	public void addOverlay(OverlayItem overlayItem) {
//		mOverlayItems.add(overlayItem);
       mOverlayItem = overlayItem;
		populate();
	}
}
