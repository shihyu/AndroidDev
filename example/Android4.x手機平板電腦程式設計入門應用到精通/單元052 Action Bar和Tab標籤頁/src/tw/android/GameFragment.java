package tw.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class GameFragment extends Fragment {

	private TextView txtComPlay, txtResult;
    private Button btnScissors;
    private Button btnStone;
    private Button btnNet;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.game, container, false);
	}

    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

        setupViewComponent();
    }

    private void setupViewComponent() {
        txtComPlay = (TextView)getView().findViewById(R.id.txtComPlay);
        txtResult = (TextView)getView().findViewById(R.id.txtResult);
        btnScissors = (Button)getView().findViewById(R.id.btnScissors);
        btnStone = (Button)getView().findViewById(R.id.btnStone);
        btnNet = (Button)getView().findViewById(R.id.btnNet);

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
