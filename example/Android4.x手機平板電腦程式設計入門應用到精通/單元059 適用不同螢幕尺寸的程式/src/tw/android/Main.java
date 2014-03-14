package tw.android;

import android.app.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.*;

public class Main extends Activity implements GameFragment.CallbackInterface {

	private GameFragment fragGame;
	private GameResultFragment fragGameResult;

	private boolean bUISettedOK = false;

	enum UIType {
		ONE_FRAME, TWO_FRAMES;
	}

	public UIType UITypeFlag;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		// 取得裝置螢幕的大小的分類
		switch (getResources().getConfiguration().screenLayout & 
				Configuration.SCREENLAYOUT_SIZE_MASK) {
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			UITypeFlag = UIType.ONE_FRAME;
			break;
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			UITypeFlag = UIType.ONE_FRAME;
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			UITypeFlag = UIType.ONE_FRAME;
			break;
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			UITypeFlag = UIType.TWO_FRAMES;
			break;
		}

		fragGame = new GameFragment();
        fragGameResult = new GameResultFragment();
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		FragmentTransaction fragTran = getFragmentManager().beginTransaction();
		fragTran.replace(R.id.frame1, fragGame, "Game");
        fragTran.replace(R.id.frame2, fragGameResult, "Game Result");
        fragTran.commit();

        if (bUISettedOK == false) {
			bUISettedOK = true;

			switch (UITypeFlag) {
			case ONE_FRAME:
				findViewById(R.id.frame1).setVisibility(View.VISIBLE);
				findViewById(R.id.frame2).setVisibility(View.GONE);
				break;
			case TWO_FRAMES:
				findViewById(R.id.frame1).setVisibility(View.VISIBLE);
				findViewById(R.id.frame2).setVisibility(View.VISIBLE);
				break;
			}
		}

		super.onResume();
	}

	@Override
	public void updateGameResult(int iCountSet, int iCountPlayerWin,
			int iCountComWin, int iCountDraw) {
		// TODO Auto-generated method stub

		if (findViewById(R.id.frame2).isShown()) {
			fragGameResult.updateGameResult(iCountSet, iCountPlayerWin,
						iCountComWin, iCountDraw);
		}

	}
}