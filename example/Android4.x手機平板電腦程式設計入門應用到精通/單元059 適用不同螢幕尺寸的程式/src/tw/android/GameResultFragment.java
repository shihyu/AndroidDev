package tw.android;

import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class GameResultFragment extends Fragment {

	private TextView mEdtCountSet,
					 mEdtCountPlayerWin,
					 mEdtCountComWin,
					 mEdtCountDraw;
	private Button mBtnBackToGame;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.game_result, container, false);
	}

    @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
		mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
    	mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
    	mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);
    	mBtnBackToGame = (Button)getActivity().findViewById(R.id.btnBackToGame);

    	mBtnBackToGame.setOnClickListener(btnBackToGameLin);
    	
    	if (((Main)getActivity()).UITypeFlag == Main.UIType.TWO_FRAMES) {
    		mBtnBackToGame.setVisibility(View.GONE);
    	} else {
    		mBtnBackToGame.setVisibility(View.VISIBLE);
    	}
	}

	private Button.OnClickListener btnBackToGameLin = new Button.OnClickListener() {
		public void onClick(View v) {
			getActivity().findViewById(R.id.frame1).setVisibility(View.VISIBLE);
			getActivity().findViewById(R.id.frame2).setVisibility(View.GONE);
		}
	};

	public void updateGameResult(int iCountSet,
								 int iCountPlayerWin,
								 int iCountComWin,
								 int iCountDraw) {
		mEdtCountSet.setText(new Integer(iCountSet).toString());
		mEdtCountDraw.setText(new Integer(iCountDraw).toString());
		mEdtCountComWin.setText(new Integer(iCountComWin).toString());
		mEdtCountPlayerWin.setText(new Integer(iCountPlayerWin).toString());
	}
}
