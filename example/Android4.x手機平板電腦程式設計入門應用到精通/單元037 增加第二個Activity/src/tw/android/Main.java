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
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
		mBtnExecGame = (Button)findViewById(R.id.btnExecGame);
		mBtnExecGame.setOnClickListener(btnExecGameOnClkLis);
    }

    private Button.OnClickListener btnExecGameOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(Main.this, Game.class);
			startActivity(it);	
		}
	};
}