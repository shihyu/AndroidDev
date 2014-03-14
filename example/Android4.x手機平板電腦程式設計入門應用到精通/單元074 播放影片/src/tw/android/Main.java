package tw.android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class Main extends Activity 
	implements MediaPlayer.OnErrorListener,
			   MediaPlayer.OnCompletionListener {

	private VideoView mVideoView;

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

		mVideoView.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		mVideoView.stopPlayback();
		super.onPause();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub

		Toast.makeText(Main.this, "¼½©ñ§¹²¦¡I", Toast.LENGTH_LONG)
			.show();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		Toast.makeText(Main.this, "°õ¦æ¿ù»~¡I", Toast.LENGTH_LONG)
			.show();

		return true;
	}

	private void setupViewComponent() {
		mVideoView = (VideoView)findViewById(R.id.videoView);
		MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);

		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
    	mVideoView.setVideoURI(uri);
	}
}