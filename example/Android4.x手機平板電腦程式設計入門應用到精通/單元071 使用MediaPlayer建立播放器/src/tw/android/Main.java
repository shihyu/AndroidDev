package tw.android;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.*;

public class Main extends Activity 
	implements MediaPlayer.OnPreparedListener,
			   MediaPlayer.OnErrorListener,
			   MediaPlayer.OnCompletionListener {

	private ImageButton mBtnMediaPlayPause,
						mBtnMediaStop,
						mBtnMediaGoto;

	private ToggleButton mBtnMediaRepeat;

	private EditText mEdtMediaGoto;

	// 程式使用的MediaPlayer物件
	private MediaPlayer mMediaPlayer = null;

	// 用來記錄是否MediaPlayer物件需要執行prepareAsync()
	private boolean	mBoolIsInitial = true;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		mMediaPlayer = new MediaPlayer();

		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song);

		try {
			mMediaPlayer.setDataSource(this, uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(Main.this, "指定的播放檔錯誤！", Toast.LENGTH_LONG)
				.show();
		}

		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.setOnErrorListener(this);
		mMediaPlayer.setOnCompletionListener(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		mMediaPlayer.release();
		mMediaPlayer = null;
	}

	private void setupViewComponent() {
    	mBtnMediaPlayPause = (ImageButton)findViewById(R.id.btnMediaPlayPause);
    	mBtnMediaStop = (ImageButton)findViewById(R.id.btnMediaStop);
    	mBtnMediaRepeat = (ToggleButton)findViewById(R.id.btnMediaRepeat);
    	mBtnMediaGoto = (ImageButton)findViewById(R.id.btnMediaGoto);
    	mEdtMediaGoto = (EditText)findViewById(R.id.edtMediaGoto);

    	mBtnMediaPlayPause.setOnClickListener(btnMediaPlayPauseLis);
    	mBtnMediaStop.setOnClickListener(btnMediaStopLis);
    	mBtnMediaRepeat.setOnClickListener(btnMediaRepeatLis);
    	mBtnMediaGoto.setOnClickListener(btnMediaGotoLis);
    }

    private Button.OnClickListener btnMediaPlayPauseLis = new Button.OnClickListener() {
		public void onClick(View v) {
			if (mMediaPlayer.isPlaying()) {
				mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
				mMediaPlayer.pause();
			}
			else {
				mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_pause);

				if (mBoolIsInitial) {
					mMediaPlayer.prepareAsync();
					mBoolIsInitial = false;
				}
				else
					mMediaPlayer.start();
			}			
		}
	};

    private Button.OnClickListener btnMediaStopLis = new Button.OnClickListener() {
		public void onClick(View v) {
			mMediaPlayer.stop();
			
			// 停止播放後必須再執行 prepareAsync() 
			// 或 prepare() 才能重新播放。
			mBoolIsInitial = true;
			mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
		}
	};

    private Button.OnClickListener btnMediaRepeatLis = new Button.OnClickListener() {
		public void onClick(View v) {
			if (((ToggleButton)v).isChecked())
				mMediaPlayer.setLooping(true);
			else
				mMediaPlayer.setLooping(false);
		}
	};

    private Button.OnClickListener btnMediaGotoLis = new Button.OnClickListener() {
		public void onClick(View v) {
			if (mEdtMediaGoto.getText().toString().equals("")) {
				Toast.makeText(Main.this, 
					"請先輸入要播放的位置（以秒為單位）", 
					Toast.LENGTH_LONG)
					.show();
				return;
			}

			int seconds = Integer.parseInt(mEdtMediaGoto.getText().toString());
			mMediaPlayer.seekTo(seconds * 1000); // 以毫秒（千分之一秒）為單位
		}
	};

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.seekTo(0);
		mp.start();

		Toast.makeText(Main.this, "開始播放", Toast.LENGTH_LONG)
			.show();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		mp.release();
		mp = null;

		Toast.makeText(Main.this, "發生錯誤，停止播放", Toast.LENGTH_LONG)
			.show();

		return true;
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
	}

}