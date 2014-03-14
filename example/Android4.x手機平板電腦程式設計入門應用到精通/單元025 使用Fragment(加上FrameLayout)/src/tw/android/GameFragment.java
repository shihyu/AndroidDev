package tw.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameFragment extends Fragment {

	private Button mBtnScissors,
				   mBtnStone,
				   mBtnNet;
    private TextView mTxtComPlay,
	 				 mTxtResult;
    private TextView mEdtCountSet,
					 mEdtCountPlayerWin,
					 mEdtCountComWin,
					 mEdtCountDraw;
    private int miCountSet = 0,
				miCountPlayerWin = 0,
				miCountComWin = 0,
				miCountDraw = 0;

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
    	mTxtComPlay = (TextView)getView().findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)getView().findViewById(R.id.txtResult);
    	mBtnScissors = (Button)getView().findViewById(R.id.btnScissors);
    	mBtnStone = (Button)getView().findViewById(R.id.btnStone);
    	mBtnNet = (Button)getView().findViewById(R.id.btnNet);

    	mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
    	mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
    	mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
    	mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);

    	mBtnScissors.setOnClickListener(btnScissorsLin);
    	mBtnStone.setOnClickListener(btnStoneLin);
    	mBtnNet.setOnClickListener(btnNetLin);
    }

	private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// Decide computer play.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			mEdtCountSet.setText(new Integer(miCountSet).toString());

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
				miCountDraw++;
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			mEdtCountSet.setText(new Integer(miCountSet).toString());

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;
			mEdtCountSet.setText(new Integer(miCountSet).toString());

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
			}
		}
	};
}
