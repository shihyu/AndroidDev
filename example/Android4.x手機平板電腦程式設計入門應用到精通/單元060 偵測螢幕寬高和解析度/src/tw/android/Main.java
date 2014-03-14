package tw.android;

import android.app.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.*;

public class Main extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 取得裝置螢幕的寬和高
        Display defDisp = getWindowManager().getDefaultDisplay();
        int dispWidth = defDisp.getWidth();
        int dispHeight = defDisp.getHeight();
        String s;
        if (dispWidth > dispHeight)
        	s = "橫向螢幕";
        else if (dispWidth < dispHeight)
        	s = "直式螢幕";
        else
        	s = "正方形螢幕";
        Toast.makeText(Main.this, s + System.getProperty("line.separator") + "w = " + dispWidth + " h = " + dispHeight, 
        		Toast.LENGTH_LONG)
			.show();

        // 顯示程式可用區域的寬和高
        Toast.makeText(Main.this, "appw = " + MyLayout.appWindowWidth + " apph = " + MyLayout.appWindowHeight, 
        		Toast.LENGTH_LONG)
			.show();

		// 取得裝置螢幕的實際解析度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 也可以利用dm.heightPixels和dm.widthPixels取得螢幕的寬和高
		Toast.makeText(Main.this, "水平dpi = " + dm.xdpi + " 垂直dpi = " + dm.ydpi, 
        		Toast.LENGTH_LONG)
			.show();

		// 取得裝置螢幕的解析度分類
        int screenSizeClass = dm.densityDpi;
		switch(screenSizeClass){
		case DisplayMetrics.DENSITY_LOW:
			Toast.makeText(Main.this, "low density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			Toast.makeText(Main.this, "medium density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_HIGH:
			Toast.makeText(Main.this, "high density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			Toast.makeText(Main.this, "x-high density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		}
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		Intent it = new Intent(this, MeasureAppWindowSize.class);
		startActivity(it);
		finish();
	}
}