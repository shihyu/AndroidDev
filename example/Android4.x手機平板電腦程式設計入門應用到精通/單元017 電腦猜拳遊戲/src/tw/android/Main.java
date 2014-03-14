package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private TextView txtComPlay, txtResult;
    private Button btnScissors;
    private Button btnStone;
    private Button btnNet;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    private void setupViewComponent() {
        txtComPlay = (TextView)findViewById(R.id.txtComPlay);
        txtResult = (TextView)findViewById(R.id.txtResult);
        btnScissors = (Button)findViewById(R.id.btnScissors);
        btnStone = (Button)findViewById(R.id.btnStone);
        btnNet = (Button)findViewById(R.id.btnNet);

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
				txtComPlay.setText(R.string.playScissors);
				txtResult.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				txtComPlay.setText(R.string.playScissors);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
    	public void onClick(View v) {
			// 決定電腦出拳.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 – 剪刀, 2 – 石頭, 3 – 布.
			if (iComPlay == 1) {
				txtComPlay.setText(R.string.playScissors);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
			}
		}
	};
}