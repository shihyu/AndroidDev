package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MeasureAppWindowSize extends Activity {

	final Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measure_app_window_size);

		// 必須用post Runnable物件的方式啟動主程式，這個
		// 測試程式視窗大小的 Activity才會繼續完成建立view的工作。
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent it = new Intent(
						MeasureAppWindowSize.this, Main.class);
				startActivity(it);
				finish();
			}				
		});
	}

}
