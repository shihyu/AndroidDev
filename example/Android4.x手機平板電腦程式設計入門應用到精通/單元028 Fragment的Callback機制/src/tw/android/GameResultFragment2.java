package tw.android;

import tw.android.GameFragment.GameResultType;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class GameResultFragment2 extends Fragment {

	private TextView mEdtCountSet,
					 mEdtCountPlayerWin,
					 mEdtCountComWin,
					 mEdtCountDraw;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.game_result2, container, false);
	}

    @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
		mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
    	mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
    	mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);

    	getActivity().findViewById(R.id.frameLayout).setVisibility(View.VISIBLE);
    	((Main)getActivity()).mGameResultType = GameResultType.TYPE_2;
    	((Main)getActivity()).fragResult = this;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		getActivity().findViewById(R.id.frameLayout).setVisibility(View.GONE);
	}

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
