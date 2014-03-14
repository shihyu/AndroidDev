package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

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
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountComWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountPlayerWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
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
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountDraw++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountComWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
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
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountPlayerWin++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountDraw++;
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
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
}