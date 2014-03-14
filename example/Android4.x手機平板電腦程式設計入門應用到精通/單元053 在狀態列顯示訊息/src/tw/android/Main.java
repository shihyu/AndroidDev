package tw.android;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private static final int NOTI_ID = 100;

	private TextView txtResult;
	private ImageView imgComPlay;
    private ImageButton btnScissors,
    					btnStone,
    					btnNet;

    private int miCountSet = 0,
				miCountPlayerWin = 0,
				miCountComWin = 0,
				miCountDraw = 0;

    private Button btnShowResult;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

    	((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
   	    	.cancel(NOTI_ID);

   	    super.onDestroy();
	}

	private void setupViewComponent() {
    	imgComPlay = (ImageView)findViewById(R.id.imgComPlay);
    	txtResult = (TextView)findViewById(R.id.txtResult);
    	btnScissors = (ImageButton)findViewById(R.id.btnScissors);
    	btnStone = (ImageButton)findViewById(R.id.btnStone);
    	btnNet = (ImageButton)findViewById(R.id.btnNet);
    	btnShowResult = (Button)findViewById(R.id.btnShowResult);

        btnScissors.setOnClickListener(btnScissorsLin);
        btnStone.setOnClickListener(btnStoneLin);
        btnNet.setOnClickListener(btnNetLin);
    	btnShowResult.setOnClickListener(btnShowResultLis);
    }

    private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountDraw++;
				txtResult.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
				showNotification("已經平手" + Integer.toString(miCountDraw) + "局");
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountComWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				showNotification("已經輸" + Integer.toString(miCountComWin) + "局");
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountPlayerWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				showNotification("已經贏" + Integer.toString(miCountPlayerWin) + "局");
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountPlayerWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				showNotification("已經贏" + Integer.toString(miCountPlayerWin) + "局");
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountDraw++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				showNotification("已經平手" + Integer.toString(miCountDraw) + "局");
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountComWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				showNotification("已經輸" + Integer.toString(miCountComWin) + "局");
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
    	public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountComWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				showNotification("已經輸" + Integer.toString(miCountComWin) + "局");
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountPlayerWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				showNotification("已經贏" + Integer.toString(miCountPlayerWin) + "局");
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountDraw++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				showNotification("已經平手" + Integer.toString(miCountDraw) + "局");
			}
		}
	};
	
	private Button.OnClickListener btnShowResultLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(Main.this, GameResult.class);
			
			Bundle bundle = new Bundle();
			bundle.putInt("KEY_COUNT_SET", miCountSet);
			bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
			bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
			bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
			it.putExtras(bundle);
			
			startActivity(it);
		}
	};

	private void showNotification(String s) {
	    Notification noti = new Notification(
	            android.R.drawable.btn_star_big_on,
	            s,
	            System.currentTimeMillis());

	    Intent it = new Intent();
	    it.setClass(this, GameResult.class);
	    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    Bundle bundle = new Bundle();
	    bundle.putInt("KEY_COUNT_SET", miCountSet);
	    bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
	    bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
	    bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
	    it.putExtras(bundle);

	    PendingIntent penIt = PendingIntent.getActivity(
	            this, 0, it,
	            PendingIntent.FLAG_UPDATE_CURRENT);

	    noti.setLatestEventInfo(this, "遊戲結果", s, penIt);
			
	    NotificationManager notiMgr =
	         (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    notiMgr.cancel(NOTI_ID);
	    notiMgr.notify(NOTI_ID, noti);
	}
}