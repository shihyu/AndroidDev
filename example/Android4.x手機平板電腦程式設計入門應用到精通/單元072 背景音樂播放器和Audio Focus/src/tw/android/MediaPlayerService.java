package tw.android;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore.MediaColumns;
import android.widget.Toast;

public class MediaPlayerService extends Service 
	implements MediaPlayer.OnPreparedListener,
			   MediaPlayer.OnErrorListener,
			   MediaPlayer.OnCompletionListener,
			   AudioManager.OnAudioFocusChangeListener {

	public static final String 
		ACTION_PLAY = "tw.android.mediaplayer.action.PLAY",
		ACTION_PAUSE = "tw.android.mediaplayer.action.PAUSE",
		ACTION_SET_REPEAT = "tw.android.mediaplayer.action.SET_REPEAT",
		ACTION_CANCEL_REPEAT = "tw.android.mediaplayer.action.CANCEL_REPEAT",
		ACTION_GOTO = "tw.android.mediaplayer.action.GOTO";

	// 程式使用的MediaPlayer物件
	private MediaPlayer mMediaPlayer = null;

	// 用來記錄是否MediaPlayer物件需要執行prepareAsync()
	private boolean	mBoolIsInitial = true,
					mBoolAudioFileFound = false;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		// 第一種播放檔來源：程式專案的 res/raw 資料夾
//		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song);

		// 第二種播放檔來源：sdcard
//		File file = new File("/sdcard/song.mp3");  
//		Uri uri = Uri.fromFile(file);

		// 第三種播放檔來源：網址 http，這種方式要設定 stream type
//		Uri uri = Uri.parse("http://.../song.mp3");
//		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		// 第四種播放檔來源：從 Android 系統的資料庫中取得播放檔
		// MediaStore 是用來指定影像、音訊或影片型態的資料
		// 這種方式要設定 stream type，
		// 這個播放檔必須先主本程式的"加入 mp3 檔"按鈕加入
		ContentResolver contRes = getContentResolver();
		String[] columns = {
				MediaColumns.TITLE, 
				MediaColumns._ID};
		Cursor c = contRes.query(
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
				columns, null, null, null);

		Uri uri = null;
		if (c == null) {
			Toast.makeText(MediaPlayerService.this, "Content Resolver 錯誤！", Toast.LENGTH_LONG)
				.show();
			return;
		}
		else if (!c.moveToFirst()) {
			Toast.makeText(MediaPlayerService.this, "資料庫中沒有資料！", Toast.LENGTH_LONG)
				.show();
			return;
		}
		else {
			do {
			    String title = c.getString(c.getColumnIndex(MediaColumns.TITLE));
			    if (title.equals("my mp3")) {
			    	mBoolAudioFileFound = true;
			    	break;
			    }
			} while(c.moveToNext());

			if (! mBoolAudioFileFound) {
				Toast.makeText(MediaPlayerService.this, "找不到指定的 mp3 檔！", Toast.LENGTH_LONG)
					.show();
				return;
			}

			int idColumn = c.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
			long id = c.getLong(idColumn);
			uri = ContentUris.withAppendedId(
					android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
		}

		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		try {
			mMediaPlayer.setDataSource(this, uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(MediaPlayerService.this, "指定的播放檔錯誤！", Toast.LENGTH_LONG)
				.show();
		}

		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.setOnErrorListener(this);
		mMediaPlayer.setOnCompletionListener(this);

		// 設定 Media Player 在背景執行時，讓 CPU 維持運轉
		// 如果播放的是來自網路的 streaming audio，
		// 還要設定網路維持運作
		// 只能在實體設備上使用，模擬器執行時會產生錯誤
//		mMediaPlayer.setWakeMode(getApplicationContext(), 
//				PowerManager.PARTIAL_WAKE_LOCK);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (mBoolAudioFileFound) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		
		stopForeground(true);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		if (! mBoolAudioFileFound) {
			stopSelf();
			return super.onStartCommand(intent, flags, startId);
		}

		if (intent.getAction().equals(ACTION_PLAY))
			if (mBoolIsInitial) {
				mMediaPlayer.prepareAsync();
				mBoolIsInitial = false;
			}
			else
				mMediaPlayer.start();
		else if (intent.getAction().equals(ACTION_PAUSE))
			mMediaPlayer.pause();
		else if (intent.getAction().equals(ACTION_SET_REPEAT))
			mMediaPlayer.setLooping(true);
		else if (intent.getAction().equals(ACTION_CANCEL_REPEAT))
			mMediaPlayer.setLooping(false);
		else if (intent.getAction().equals(ACTION_GOTO)) {
			int seconds = intent.getIntExtra("GOTO_POSITION_SECONDS", 0);
			mMediaPlayer.seekTo(seconds * 1000); // 以毫秒（千分之一秒）為單位
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub

		// 是否取得 audio focus
		AudioManager audioMgr = 
			(AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int r = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
		if (r != AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
			mp.setVolume(0.1f, 0.1f);	// 降低音量

		mp.start();

		Intent it = new Intent(getApplicationContext(), Main.class);
		PendingIntent pendIt = PendingIntent.getActivity(
				getApplicationContext(), 0, it, 
				PendingIntent.FLAG_UPDATE_CURRENT);
		Notification noti = new Notification(
				android.R.drawable.ic_media_play, 
				"背景音樂播放中...",
				System.currentTimeMillis());
		noti.flags |= Notification.FLAG_ONGOING_EVENT;
		noti.setLatestEventInfo(getApplicationContext(), 
				"音樂播放程式", "背景音樂播放中...", pendIt);
		startForeground(1, noti);

		Toast.makeText(MediaPlayerService.this, "開始播放", Toast.LENGTH_LONG)
		.show();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		mp.release();
		mp = null;

		Toast.makeText(MediaPlayerService.this, "發生錯誤，停止播放", Toast.LENGTH_LONG)
			.show();

		return true;
	}

	@Override
	public void onAudioFocusChange(int focusChange) {
		// TODO Auto-generated method stub
		
		if (mMediaPlayer == null)
			return;

		switch (focusChange) {
		case AudioManager.AUDIOFOCUS_GAIN:
			// 程式取得聲音播放權
			mMediaPlayer.setVolume(0.8f, 0.8f);
			mMediaPlayer.start();				
			break;
		case AudioManager.AUDIOFOCUS_LOSS:
			// 程式尚失聲音播放權，而且時間可能很久
			stopSelf();		// 結束這個Service
			break;
		case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
			// 程式尚失聲音播放權，但預期很快就會再取得
			if (mMediaPlayer.isPlaying())
				mMediaPlayer.pause();
			break;
		case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
			// 程式尚失聲音播放權，但是可以用很小的音量繼續播放
			if (mMediaPlayer.isPlaying())
				mMediaPlayer.setVolume(0.1f, 0.1f);
			break;
		}
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		mMediaPlayer.release();
		mMediaPlayer = null;
		
		stopForeground(true);

		mBoolIsInitial = true;
	}
}
