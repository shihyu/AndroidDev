package tw.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameFragment extends Fragment {

	// 所屬的 Activity 必須實作以下介面中的Callback方法
	public interface CallbackInterface {
		public void updateGameResult(int iCountSet,
									 int iCountPlayerWin,
									 int iCountComWin,
									 int iCountDraw);
	};

	private CallbackInterface mCallback;
	private Button mBtnScissors,
				   mBtnStone,
				   mBtnNet,
				   mBtnShowResult;

    private TextView mTxtComPlay,
	 				 mTxtResult;

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

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

		try {
			mCallback = (CallbackInterface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + 
					"must implement GameFragment.CallbackInterface.");
		}
	}

	private void setupViewComponent() {
    	mTxtComPlay = (TextView)getView().findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)getView().findViewById(R.id.txtResult);
    	mBtnScissors = (Button)getView().findViewById(R.id.btnScissors);
    	mBtnStone = (Button)getView().findViewById(R.id.btnStone);
    	mBtnNet = (Button)getView().findViewById(R.id.btnNet);
    	mBtnShowResult = (Button)getView().findViewById(R.id.btnShowResult);

    	mBtnScissors.setOnClickListener(btnScissorsLin);
    	mBtnStone.setOnClickListener(btnStoneLin);
    	mBtnNet.setOnClickListener(btnNetLin);

    	mBtnShowResult.setOnClickListener(btnShowResultLin);

    	if (((Main)getActivity()).UITypeFlag == Main.UIType.TWO_FRAMES) {
    		mBtnShowResult.setVisibility(View.GONE);
    	} else {
    		mBtnShowResult.setVisibility(View.VISIBLE);
    	}
	}

	private Button.OnClickListener btnShowResultLin = new Button.OnClickListener() {
		public void onClick(View v) {
			getActivity().findViewById(R.id.frame1).setVisibility(View.GONE);
			getActivity().findViewById(R.id.frame2).setVisibility(View.VISIBLE);

			mCallback.updateGameResult(miCountSet, miCountPlayerWin, 
					miCountComWin, miCountDraw);
		}
	};

	private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// Decide computer play.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
				miCountDraw++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}

			mCallback.updateGameResult(miCountSet, miCountPlayerWin, 
					miCountComWin, miCountDraw);
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			
			mCallback.updateGameResult(miCountSet, miCountPlayerWin, 
					miCountComWin, miCountDraw);
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
			}
			
			mCallback.updateGameResult(miCountSet, miCountPlayerWin, 
					miCountComWin, miCountDraw);
		}
	};
}
