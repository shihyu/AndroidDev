package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MyWebViewClient extends WebViewClient {

	private Activity mActivity;
	private Button mBtnGoBack,
				   mBtnGoForward,
				   mBtnStop,
				   mBtnReload;
	private WebView mWebView;

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub

		if (Uri.parse(url).getHost().indexOf("google") >= 0) {
			return false;
		}

		Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		mActivity.startActivity(it);
		return true;
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub

		// 手機程式可以在標題列顯示橫式進度列
		// 平板電腦只能顯示環狀等待迴圈
//		mActivity.setProgressBarVisibility(true);
		mActivity.setProgressBarIndeterminateVisibility(true);
		mActivity.setTitle("正在下載網頁...");

		mBtnReload.setEnabled(false);
		mBtnStop.setEnabled(true);

		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub

		// 平板電腦程式 - 隱藏環狀等待迴圈
		mActivity.setProgressBarIndeterminateVisibility(false);
		mActivity.setTitle(R.string.app_name);
		mBtnReload.setEnabled(true);
		mBtnStop.setEnabled(false);

		if (mWebView.canGoBack())
			mBtnGoBack.setEnabled(true);
		else
			mBtnGoBack.setEnabled(false);

		if (mWebView.canGoForward())
			mBtnGoForward.setEnabled(true);
		else
			mBtnGoForward.setEnabled(false);

		super.onPageFinished(view, url);
	}

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		// TODO Auto-generated method stub

		mBtnReload.setEnabled(true);
		mBtnStop.setEnabled(false);

		Toast.makeText(mActivity, "開啟網頁錯誤：" + failingUrl + description, Toast.LENGTH_LONG)
			.show();
		super.onReceivedError(view, errorCode, description, failingUrl);
	}

	public MyWebViewClient setupViewComponent(Activity act,
											  WebView webView,
											  Button btnGoBack, 
									 		  Button btnGoForward,
									 		  Button btnReload,
									 		  Button btnStop) {
		mActivity = act;
		mWebView = webView;
		mBtnGoBack = btnGoBack;
		mBtnGoForward = btnGoForward;
		mBtnReload = btnReload;
		mBtnStop = btnStop;

		return this;
	}
}
