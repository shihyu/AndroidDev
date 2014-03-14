package tw.android;

import android.content.Context;
import android.widget.Toast;

public class JavaScriptCallFunc {
	Context mContext;

	JavaScriptCallFunc(Context c) {
		mContext = c;
	}

	public void showToastMsg(String s) {
		Toast.makeText(mContext, s, Toast.LENGTH_LONG)
			.show();
	}
}
