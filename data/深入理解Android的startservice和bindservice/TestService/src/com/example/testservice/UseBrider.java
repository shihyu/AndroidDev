package com.example.testservice;

/**引入包*/
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/** 通过bindService和unBindSerivce的方式启动和结束服务 */
public class UseBrider extends Activity {
	/** 参数设置 */
	CountService countService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new UseBriderFace(this));
		
		Intent intent = new Intent(UseBrider.this, CountService.class);
		/** 进入Activity开始服务 */
		bindService(intent, conn, Context.BIND_AUTO_CREATE);

	}

	
	private ServiceConnection conn = new ServiceConnection() {
		/** 获取服务对象时的操作 */
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			countService = ((CountService.ServiceBinder) service).getService();

		}

		/** 无法获取到服务对象时的操作 */
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			countService = null;
		}

	};

	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(conn);
		Log.v("MainStadyServics", "out");
	}
}
