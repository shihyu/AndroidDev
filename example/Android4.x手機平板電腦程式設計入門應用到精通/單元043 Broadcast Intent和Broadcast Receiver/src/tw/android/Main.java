package tw.android;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	private Button mBtnRegReceiver,
				   mBtnUnregReceiver,
				   mBtnSendBroadcase1,
				   mBtnSendBroadcase2;

	private MyBroadcaseReceiver2 mMyReceiver2;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    private void setupViewComponent() {
    	mBtnRegReceiver = (Button)findViewById(R.id.btnRegReceiver);
		mBtnUnregReceiver = (Button)findViewById(R.id.btnUnregReceiver);
    	mBtnSendBroadcase1 = (Button)findViewById(R.id.btnSendBroadcase1);
		mBtnSendBroadcase2 = (Button)findViewById(R.id.btnSendBroadcase2);
				   
    	mBtnRegReceiver.setOnClickListener(btnRegReceiverOnClickLis);
		mBtnUnregReceiver.setOnClickListener(btnUnregReceiverOnClickLis);
    	mBtnSendBroadcase1.setOnClickListener(btnSendBroadcase1OnClickLis);
		mBtnSendBroadcase2.setOnClickListener(btnSendBroadcase2OnClickLis);
    }

    private Button.OnClickListener btnRegReceiverOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			IntentFilter itFilter = new IntentFilter("tw.android.MY_BROADCAST2");
			mMyReceiver2 = new MyBroadcaseReceiver2();
			registerReceiver(mMyReceiver2, itFilter);
		}
	};

    private Button.OnClickListener btnUnregReceiverOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			unregisterReceiver(mMyReceiver2);	
	     }
	};

    private Button.OnClickListener btnSendBroadcase1OnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent("tw.android.MY_BROADCAST1");
			it.putExtra("sender_name", "主程式");
			sendBroadcast(it);
		}
	};

    private Button.OnClickListener btnSendBroadcase2OnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent("tw.android.MY_BROADCAST2");
			it.putExtra("sender_name", "主程式");
			sendBroadcast(it);
		}
	};
}