package com.example.testservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button startServiceButton;// 启动服务按钮
	Button shutDownServiceButton;// 关闭服务按钮
	Button startBindServiceButton;// 启动绑定服务按钮
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWidget();
		regiestListener();
	}

	/** 获得组件 */
	public void getWidget() {
		startServiceButton = (Button) findViewById(R.id.startServerButton);
		startBindServiceButton = (Button) findViewById(R.id.startBindServerButton);
		shutDownServiceButton = (Button) findViewById(R.id.sutdownServerButton);
	}

	/** 为按钮添加监听 */
	public void regiestListener() {
		startServiceButton.setOnClickListener(startService);
		shutDownServiceButton.setOnClickListener(shutdownService);
		startBindServiceButton.setOnClickListener(startBinderService);
	}
	
	
	/** 启动服务的事件监听 */
	public Button.OnClickListener startService = new Button.OnClickListener() {
		public void onClick(View view) {
			/** 单击按钮时启动服务 */
			Intent intent = new Intent(MainActivity.this,
					CountService.class);
			startService(intent);
			
			Log.v("MainStadyServics", "start Service");
		}
	};
	/** 关闭服务 */
	public Button.OnClickListener shutdownService = new Button.OnClickListener() {
		public void onClick(View view) {
			/** 单击按钮时启动服务 */
			Intent intent = new Intent(MainActivity.this,
					CountService.class);
			/** 退出Activity是，停止服务 */
			stopService(intent);
			Log.v("MainStadyServics", "shutDown serveice");
		}
	};
	/** 打开绑定服务的Activity */
	public Button.OnClickListener startBinderService = new Button.OnClickListener() {
		public void onClick(View view) {
			/** 单击按钮时启动服务 */
			Intent intent = new Intent(MainActivity.this, UseBrider.class);
			startActivity(intent);
			Log.v("MainStadyServics", "start Binder Service");
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
