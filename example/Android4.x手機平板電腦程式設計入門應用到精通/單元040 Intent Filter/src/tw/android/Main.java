package tw.android;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	private Button mBtnBrowseWWW,
					mBtnEditImg,
					mBtnViewImg;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		setupViewComponent();
	}

	private void setupViewComponent() {
		mBtnBrowseWWW = (Button)findViewById(R.id.btnBrowseWWW);
		mBtnEditImg = (Button)findViewById(R.id.btnEditImg);
		mBtnViewImg = (Button)findViewById(R.id.btnViewImg);
			   
		mBtnBrowseWWW.setOnClickListener(btnBrowseWWWOnClickLis);
		mBtnEditImg.setOnClickListener(btnEditImgOnClickLis);
		mBtnViewImg.setOnClickListener(btnViewImgOnClickLis);
	}

	private Button.OnClickListener btnBrowseWWWOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("http://developer.android.com/");
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
		}
	};

	private Button.OnClickListener btnEditImgOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent(Intent.ACTION_EDIT);
			File file = new File("/sdcard/image.png");  
			it.setDataAndType(Uri.fromFile(file), "image/*"); 
			startActivity(it);
		}
	};

	private Button.OnClickListener btnViewImgOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent(Intent.ACTION_VIEW);
			File file = new File("/sdcard/image.png");  
			it.setDataAndType(Uri.fromFile(file), "image/*"); 
			startActivity(it);
		}
	};
}