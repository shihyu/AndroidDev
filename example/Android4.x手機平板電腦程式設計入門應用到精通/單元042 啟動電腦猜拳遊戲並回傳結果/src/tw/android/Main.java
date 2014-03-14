package tw.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

	private Button mBtnExecGame;
	private TextView mTxtResult;
	final private int LAUNCH_GAME = 0;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
    	mTxtResult = (TextView)findViewById(R.id.txtResult);
    	mBtnExecGame = (Button)findViewById(R.id.btnExecGame);
		mBtnExecGame.setOnClickListener(btnExecGameOnClkLis);
    }

    private Button.OnClickListener btnExecGameOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(Main.this, Game.class);
			startActivityForResult(it, LAUNCH_GAME);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent  data) {
    	if (requestCode != LAUNCH_GAME)
    		return;
    	
    	switch (resultCode) {
    	case RESULT_OK:
    		Bundle bundle = data.getExtras();
    		
    		int iCountSet = bundle.getInt("KEY_COUNT_SET");
    		int iCountPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
    		int iCountComWin = bundle.getInt("KEY_COUNT_COM_WIN");
    		int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");
    		
    		String s = "遊戲結果：你總共玩了" + iCountSet +
    				   "局, 贏了" + iCountPlayerWin +
    				   "局, 輸了" + iCountComWin +
    				   "局, 平手" + iCountDraw + "局";
    		mTxtResult.setText(s);
    		
    		break;
    	case RESULT_CANCELED:
    		mTxtResult.setText("你選擇取消遊戲。");
    	}
    	
    }
}