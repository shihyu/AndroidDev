package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class GameResult extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_result);
        
		setupViewComponent();
		showResult();
	}
    
    private EditText mEdtCountSet,
                     mEdtCountPlayerWin,
                     mEdtCountComWin,
                     mEdtCountDraw;
    private Button btnBackToGame;
    
    private void setupViewComponent() {
        mEdtCountSet = (EditText)findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)findViewById(R.id.edtCountDraw);
        btnBackToGame = (Button)findViewById(R.id.btnBackToGame);
    	
        btnBackToGame.setOnClickListener(btnBackToGameLis);
    }

    private Button.OnClickListener btnBackToGameLis = new Button.OnClickListener() {
		public void onClick(View v) {
			GameResult.this.finish();
		}
	};
	
	private void showResult() {
		// 從 bundle 中取出資料
		Bundle bundle = getIntent().getExtras();
		
		int iCountSet = bundle.getInt("KEY_COUNT_SET");
		int iCountPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
		int iCountComWin = bundle.getInt("KEY_COUNT_COM_WIN");
		int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");
		
		mEdtCountSet.setText(Integer.toString(iCountSet));
		mEdtCountPlayerWin.setText(Integer.toString(iCountPlayerWin));
		mEdtCountComWin.setText(Integer.toString(iCountComWin));
		mEdtCountDraw.setText(Integer.toString(iCountDraw));
	}
}
