package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class Game extends Activity {

	private final String LOG_TAG = "activity lifecycle";

	private TextView txtResult;
	private ImageView imgComPlay;
    private ImageButton btnScissors,
    					btnStone,
    					btnNet;

    private int miCountSet = 0,
				miCountPlayerWin = 0,
				miCountComWin = 0,
				miCountDraw = 0;

    private Button btnOK, btnCancel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "Game.onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        setupViewComponent();
    }

    @Override
	protected void onDestroy() {
    	Log.d(LOG_TAG, "Game.onDestroy()");

		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
    	Log.d(LOG_TAG, "Game.onPause()");

		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
    	Log.d(LOG_TAG, "Game.onRestart()");

		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
    	Log.d(LOG_TAG, "Game.onResume()");

		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
    	Log.d(LOG_TAG, "Game.onStart()");

		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
    	Log.d(LOG_TAG, "Game.onStop()");

		// TODO Auto-generated method stub
		super.onStop();
	}

	private void setupViewComponent() {
    	imgComPlay = (ImageView)findViewById(R.id.imgComPlay);
//    	txtResult = (TextView)findViewById(R.id.txtResult);
    	btnScissors = (ImageButton)findViewById(R.id.btnScissors);
    	btnStone = (ImageButton)findViewById(R.id.btnStone);
    	btnNet = (ImageButton)findViewById(R.id.btnNet);

        btnScissors.setOnClickListener(btnScissorsLin);
        btnStone.setOnClickListener(btnStoneLin);
        btnNet.setOnClickListener(btnNetLin);

        btnOK = (Button)findViewById(R.id.btnOK);
    	btnCancel = (Button)findViewById(R.id.btnCancel);
    	btnOK.setOnClickListener(btnOKLis);
    	btnCancel.setOnClickListener(btnCancelLis);
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
//				txtResult.setText(getString(R.string.result) +
//				  			getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
					.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountComWin++;
//				txtResult.setText(getString(R.string.result) +
//							getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
					.show();
			}
				else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountPlayerWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
					.show();
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
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountDraw++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountComWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
				.show();
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
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountPlayerWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountDraw++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
		}
	};

	private Button.OnClickListener btnOKLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent();
			
			Bundle bundle = new Bundle();
			bundle.putInt("KEY_COUNT_SET", miCountSet);
			bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
			bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
			bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
			it.putExtras(bundle);
			
			setResult(RESULT_OK, it);
			finish();
		}
	};

	private Button.OnClickListener btnCancelLis = new Button.OnClickListener() {
		public void onClick(View v) {
			setResult(RESULT_CANCELED);
			finish();
		}
	};
}
