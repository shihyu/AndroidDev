package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Game extends Activity {

	private TextView txtResult;
	private ImageView imgComPlay;
    private ImageButton btnScissors,
    					btnStone,
    					btnNet;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        setupViewComponent();
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
    }

    private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
//				txtResult.setText(getString(R.string.result) +
//				  			getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
					.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
//				txtResult.setText(getString(R.string.result) +
//							getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
					.show();
			}
				else {
				imgComPlay.setImageResource(R.drawable.net);
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
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
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
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
		}
	};
}
