package tw.android;

import tw.android.GameFragment.GameResultType;
import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity implements GameFragment.CallbackInterface {
    
	private final static String TAG = "Result";
	private int mTagCount = 0;
	public GameFragment.GameResultType mGameResultType;
	public Fragment fragResult;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public void updateGameResult(int iCountSet, int iCountPlayerWin,
			int iCountComWin, int iCountDraw) {
		// TODO Auto-generated method stub

		if (findViewById(R.id.frameLayout).isShown()) {
	        switch (mGameResultType) {
			case TYPE_1:
				((GameResultFragment)fragResult).updateGameResult(iCountSet, iCountPlayerWin,
						iCountComWin, iCountDraw);
				break;
			case TYPE_2:
				((GameResultFragment2)fragResult).updateGameResult(iCountSet, iCountPlayerWin,
						iCountComWin, iCountDraw);
				break;
			}
		}

	}

	@Override
	public void enableGameResult(GameResultType type) {
		// TODO Auto-generated method stub

		FragmentTransaction fragTran;
		String sFragTag;

		switch (type) {
		case TYPE_1:
			GameResultFragment frag = new GameResultFragment();
	        fragTran = getFragmentManager().beginTransaction();
	        mTagCount++;
	        sFragTag = TAG + new Integer(mTagCount).toString();
	        fragTran.replace(R.id.frameLayout, frag, sFragTag);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();
	        break;
		case TYPE_2:
			GameResultFragment2 frag2 = new GameResultFragment2();
	        fragTran = getFragmentManager().beginTransaction();
	        mTagCount++;
	        sFragTag = TAG + new Integer(mTagCount).toString();
	        fragTran.replace(R.id.frameLayout, frag2, sFragTag);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();
	        break;
		case TURN_OFF:
	        FragmentManager fragMgr = getFragmentManager();
	        sFragTag = TAG + new Integer(mTagCount).toString();
	    	Fragment fragGameResult = (Fragment)fragMgr.findFragmentByTag(sFragTag);
    		fragTran = fragMgr.beginTransaction();
	        fragTran.remove(fragGameResult);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();
			break;
		}
	}
}