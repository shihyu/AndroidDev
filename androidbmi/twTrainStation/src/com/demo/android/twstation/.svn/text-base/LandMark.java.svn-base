package com.demo.android.twstation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class LandMark extends Overlay {
	private final Bitmap bmp;
	private final GeoPoint gpoint;

	public LandMark(Bitmap bmp, GeoPoint gp) {
	    this.bmp = bmp;
	    this.gpoint = gp;
	  }
	 
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	    Projection pro = mapView.getProjection();//Map Projection
	    Point p = pro.toPixels(gpoint, null);    //pixel
	    canvas.drawBitmap(bmp, p.x, p.y, null);  //draw icon
	  }

}
