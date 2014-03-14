package tw.android;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity {
	
	private ImageView mImgRollingDice;
	private TextView mTxtDiceResult;
	private Button mBtnRollDice;

	private Handler handler=new Handler() {

        public void handleMessage(Message msg) {
        	super.handleMessage(msg);

        	int iRand = (int)(Math.random()*6 + 1);

    		String s = getString(R.string.diceResult);
    		mTxtDiceResult.setText(s + iRand);
    		switch (iRand) {
    		case 1:
    			mImgRollingDice.setImageResource(R.drawable.dice01);
    			break;
    		case 2:
    			mImgRollingDice.setImageResource(R.drawable.dice02);
    			break;
    		case 3:
    			mImgRollingDice.setImageResource(R.drawable.dice03);
    			break;
    		case 4:
    			mImgRollingDice.setImageResource(R.drawable.dice04);
    			break;
    		case 5:
    			mImgRollingDice.setImageResource(R.drawable.dice05);
    			break;
    		case 6:
    			mImgRollingDice.setImageResource(R.drawable.dice06);
    			break;
    		}
        }
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
    	mImgRollingDice = (ImageView)findViewById(R.id.imgRollingDice);
    	mTxtDiceResult = (TextView)findViewById(R.id.txtDiceResult);
    	mBtnRollDice = (Button)findViewById(R.id.btnRollDice);

    	mBtnRollDice.setOnClickListener(btnRollDiceOnClickLis);
    }
	
    private Button.OnClickListener btnRollDiceOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {

			String s = getString(R.string.diceResult);
			mTxtDiceResult.setText(s);

			Resources res = getResources();
			final AnimationDrawable animDraw = (AnimationDrawable)res.getDrawable(R.drawable.anim_drawable);
			mImgRollingDice.setImageDrawable(animDraw);
			animDraw.start();

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					animDraw.stop();
					handler.sendMessage(handler.obtainMessage());
				}
			}
			).start();
		}
	};
}