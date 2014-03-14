package tw.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class GameResultFragment2 extends Fragment {

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

		GameFragment frag = (GameFragment)getFragmentManager().findFragmentById(R.id.fragGame);
		frag.mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
		frag.mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
    	frag.mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
    	frag.mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);

    	getActivity().findViewById(R.id.frameLayout).setVisibility(View.VISIBLE);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		getActivity().findViewById(R.id.frameLayout).setVisibility(View.GONE);
	}
}
