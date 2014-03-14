package tw.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcaseReceiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String sender = intent.getStringExtra("sender_name");
		Toast.makeText(context, "BroadcastReceiver1收到" + sender + "發送的Broadcase訊息", 
				Toast.LENGTH_LONG).show();
	}

}
