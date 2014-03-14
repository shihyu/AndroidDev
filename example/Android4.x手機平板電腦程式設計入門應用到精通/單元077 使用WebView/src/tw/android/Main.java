package tw.android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {

	private Button mBtnOpenUrl;
	private EditText mEdtUrl;
	private WebView mWebView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViewComponent();
    }

	private void setupViewComponent() {
		mBtnOpenUrl = (Button)findViewById(R.id.btnOpenUrl);
		mEdtUrl = (EditText)findViewById(R.id.edtUrl);
		mWebView = (WebView)findViewById(R.id.webView);

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		mBtnOpenUrl.setOnClickListener(btnClickLisOpenUrl);
    }

    private Button.OnClickListener btnClickLisOpenUrl = new Button.OnClickListener() {
		public void onClick(View v) {
			mWebView.loadUrl(mEdtUrl.getText().toString());
		}
	};
}