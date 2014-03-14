package tw.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	private Button mBtnStartMyService,
				   mBtnStopMyService,
				   mBtnBindMyService,
				   mBtnUnbindMyService,
				   mBtnCallMyServiceMethod;

	private MyService mMyServ = null;
	
    private final String LOG_TAG = "service demo";
    
    private ServiceConnection mServConn = new ServiceConnection() {
		@Override
 		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d(LOG_TAG, "onServiceConnected() " + name.getClassName());
			mMyServ = ((MyService.LocalBinder)service).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(LOG_TAG, "onServiceDisconnected()" + name.getClassName());
		}
    };

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mBtnStartMyService = (Button) findViewById(R.id.btnStartMyService);
        mBtnStopMyService = (Button) findViewById(R.id.btnStopMyService);
        mBtnBindMyService = (Button) findViewById(R.id.btnBindMyService);
        mBtnUnbindMyService = (Button) findViewById(R.id.btnUnbindMyService);
        mBtnCallMyServiceMethod = (Button) findViewById(R.id.btnCallMyServiceMethod);
		
		mBtnStartMyService.setOnClickListener(btnStartMyServiceOnClkLis);
		mBtnStopMyService.setOnClickListener(btnStopMyServiceOnClkLis);
		mBtnBindMyService.setOnClickListener(btnBindMyServiceOnClkLis);
		mBtnUnbindMyService.setOnClickListener(btnUnbindMyServiceOnClkLis);
		mBtnCallMyServiceMethod.setOnClickListener(btnCallMyServiceMethodOnClkLis);
    }
    
    private OnClickListener btnStartMyServiceOnClkLis = new OnClickListener() {
    	public void onClick(View v) {
    		mMyServ = null;
    		Intent it = new Intent(Main.this, MyService.class);
    		startService(it);
    	}
    };
    
    private OnClickListener btnStopMyServiceOnClkLis = new OnClickListener() {
    	public void onClick(View v) {
    		mMyServ = null;
    		Intent it = new Intent(Main.this, MyService.class);
    		stopService(it);
    	}
    };
   
    private OnClickListener btnBindMyServiceOnClkLis = new OnClickListener() {
    	public void onClick(View v) {
    		mMyServ = null;
    		Intent it = new Intent(Main.this, MyService.class);
    		bindService(it, mServConn, BIND_AUTO_CREATE);
    	}
    };
    
    private OnClickListener btnUnbindMyServiceOnClkLis = new OnClickListener() {
    	public void onClick(View v) {
    		mMyServ = null;
    		unbindService(mServConn);
    	}
    };
	
    private OnClickListener btnCallMyServiceMethodOnClkLis = new OnClickListener() {
    	public void onClick(View v) {
    		if (mMyServ != null)
    			mMyServ.myMethod();
    	}
    };
}