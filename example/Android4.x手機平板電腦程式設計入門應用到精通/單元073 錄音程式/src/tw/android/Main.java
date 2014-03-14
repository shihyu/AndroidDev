package tw.android;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends Activity
	implements MediaPlayer.OnPreparedListener,
			   MediaPlayer.OnErrorListener,
			   MediaPlayer.OnCompletionListener {

	private final String mFileName = "my_recorded_audio.3gp";

	private Button mBtnAudioRecoOnOff,
				   mBtnPlayAudioOnOff;

	private boolean mBoolRecording = false,
					mBoolPlaying = false;

	private MediaRecorder mRecorder = null;

	private MediaPlayer mPlayer = null;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

	private void setupViewComponent() {
    	mBtnAudioRecoOnOff = (Button)findViewById(R.id.btnAudioRecoOnOff);
    	mBtnPlayAudioOnOff = (Button)findViewById(R.id.btnPlayAudioOnOff);

    	mBtnAudioRecoOnOff.setOnClickListener(btnClickLisAudioRecoOnOff);
    	mBtnPlayAudioOnOff.setOnClickListener(btnClickLisPlayAudioOnOff);
	}

    private Button.OnClickListener btnClickLisAudioRecoOnOff = new Button.OnClickListener() {
		public void onClick(View v) {
			if (mBoolRecording) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;

				mBoolRecording = false;
				mBtnAudioRecoOnOff.setText("開始錄音");
			} else {
				mRecorder = new MediaRecorder();
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mRecorder.setOutputFile(
						Environment.getExternalStorageDirectory().getAbsolutePath() + 
						"/" + mFileName);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				
				try {
					mRecorder.prepare();
				} catch (Exception e) {
					Toast.makeText(Main.this, "MediaRecorder 錯誤!", Toast.LENGTH_LONG)
						.show();
				}
				
				mRecorder.start();

				mBoolRecording = true;
				mBtnAudioRecoOnOff.setText("停止錄音");
			}
		}
	};

    private Button.OnClickListener btnClickLisPlayAudioOnOff = new Button.OnClickListener() {
		public void onClick(View v) {
			if (mBoolRecording) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;

				mBoolRecording = false;
				mBtnAudioRecoOnOff.setText("開始錄音");
			}
			
			if (mBoolPlaying) {
				mPlayer.stop();
				mPlayer.release();
				mPlayer = null;

				mBoolPlaying = false;
				mBtnPlayAudioOnOff.setText("開始撥放");
			} else {
				mPlayer = new MediaPlayer();
				
				try {
					mPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + 
							"/" + mFileName);
				} catch (Exception e) {
					Toast.makeText(Main.this, "MediaPlayer 錯誤!", Toast.LENGTH_LONG)
						.show();
				}

				mPlayer.setOnPreparedListener(Main.this);
				mPlayer.setOnErrorListener(Main.this);
				mPlayer.setOnCompletionListener(Main.this);

				mPlayer.prepareAsync();

				mBoolPlaying = true;
				mBtnPlayAudioOnOff.setText("停止撥放");
			}
		}
	};

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub

		mPlayer.release();
		mPlayer = null;

		mBoolPlaying = false;
		mBtnPlayAudioOnOff.setText("開始撥放");

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		mPlayer.release();
		mPlayer = null;

		Toast.makeText(Main.this, "MediaPlayer錯誤!", Toast.LENGTH_LONG)
			.show();

		return true;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub

		mPlayer.setVolume(1.0f, 1.0f);
		mPlayer.start();

		Toast.makeText(Main.this, "開始撥放...", Toast.LENGTH_LONG)
			.show();
	}
}