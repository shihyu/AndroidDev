package tw.android;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Main extends Activity 
	implements OnClickListener{

	private Button mBtnAddToMediaStore,
				   mBtnStart, mBtnPause,
				   mBtnStop, mBtnSetRepeat,
				   mBtnCancelRepeat, mBtnGoto;

	private EditText mEdtGoto;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

	private void setupViewComponent() {
		mBtnStart = (Button)findViewById(R.id.btnStart);
		mBtnPause = (Button)findViewById(R.id.btnPause);
		mBtnStop = (Button)findViewById(R.id.btnStop);
		mBtnSetRepeat = (Button)findViewById(R.id.btnSetRepeat);
		mBtnCancelRepeat = (Button)findViewById(R.id.btnCancelRepeat);
		mBtnGoto = (Button)findViewById(R.id.btnGoto);
		mBtnAddToMediaStore = (Button)findViewById(R.id.btnAddToMediaStore);
		mEdtGoto = (EditText)findViewById(R.id.edtGoto);

		mBtnStart.setOnClickListener(this);
		mBtnPause.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
		mBtnSetRepeat.setOnClickListener(this);
		mBtnCancelRepeat.setOnClickListener(this);
		mBtnGoto.setOnClickListener(this);
    	mBtnAddToMediaStore.setOnClickListener(btnAddToMediaStoreLis);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent it;

		switch(v.getId()) {
		case R.id.btnStart:
			it = new Intent(Main.this, MediaPlayerService.class);
			it.setAction(MediaPlayerService.ACTION_PLAY);
			startService(it);
			break;
		case R.id.btnPause:
			it = new Intent(Main.this, MediaPlayerService.class);
			it.setAction(MediaPlayerService.ACTION_PAUSE);
			startService(it);
			break;
		case R.id.btnStop:
			it = new Intent(Main.this, MediaPlayerService.class);
			stopService(it);
			break;
		case R.id.btnSetRepeat:
			it = new Intent(Main.this, MediaPlayerService.class);
			it.setAction(MediaPlayerService.ACTION_SET_REPEAT);
			startService(it);
			break;
		case R.id.btnCancelRepeat:
			it = new Intent(Main.this, MediaPlayerService.class);
			it.setAction(MediaPlayerService.ACTION_CANCEL_REPEAT);
			startService(it);
			break;
		case R.id.btnGoto:
			if (mEdtGoto.getText().toString().equals("")) {
				Toast.makeText(Main.this, 
					"請先輸入要播放的位置（以秒為單位）", 
					Toast.LENGTH_LONG)
					.show();
				break;
			}

			int seconds = Integer.parseInt(mEdtGoto.getText().toString());

			it = new Intent(Main.this, MediaPlayerService.class);
			it.setAction(MediaPlayerService.ACTION_GOTO);
			it.putExtra("GOTO_POSITION_SECONDS", seconds);
			startService(it);
			break;
		}
	}

    private Button.OnClickListener btnAddToMediaStoreLis = new Button.OnClickListener() {
		public void onClick(View v) {
			ContentValues val = new ContentValues();   
	        val.put(MediaColumns.TITLE, "my mp3");   
	        val.put(MediaColumns.MIME_TYPE, "audio/mp3");   
	        val.put(MediaColumns.DATA, "/sdcard/song.mp3");   
	        ContentResolver contRes = getContentResolver();   
	        Uri newUri = contRes.insert(
	        		android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
	        		val);   
	        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));			
		}
	};
}