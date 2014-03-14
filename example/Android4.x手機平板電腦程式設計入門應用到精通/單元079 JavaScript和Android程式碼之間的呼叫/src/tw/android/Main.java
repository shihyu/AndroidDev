package tw.android;

import java.net.URLDecoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class Main extends Activity 
	implements OnClickListener {

	private Button mBtnLoadHtml,
				   mBtnShowImage,
				   mBtnBuildHtml;
	private WebView mWebView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

	private void setupViewComponent() {
		mBtnLoadHtml = (Button)findViewById(R.id.btnLoadHtml);
		mBtnShowImage = (Button)findViewById(R.id.btnShowImage);
		mBtnBuildHtml = (Button)findViewById(R.id.btnBuildHtml);
		mWebView = (WebView)findViewById(R.id.webView);

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		mWebView.addJavascriptInterface(new JavaScriptCallFunc(this), "Android");

		mBtnLoadHtml.setOnClickListener(this);
		mBtnShowImage.setOnClickListener(this);
		mBtnBuildHtml.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnLoadHtml:
			mWebView.loadUrl("file:///android_asset/my_web_page.html");
			break;
		case R.id.btnShowImage:
			mWebView.loadUrl("javascript:showImage()");
			break;
		case R.id.btnBuildHtml:
			String sHtml = null;
			try {
				sHtml = URLDecoder.decode(
						"<html>" + 
						"<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + 
						"<body>這是由程式碼建立的網頁。</body>" +
						"</html>", "utf-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mWebView.loadData(sHtml, "text/html", "utf-8");
			break;
		}
	}
}