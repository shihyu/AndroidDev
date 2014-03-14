package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class Main extends Activity {

	private RatingBar mRatBar;
	private SeekBar mSeekBar;
	private TextView mTxtSeekBar,
					 mTxt1RatBar,
					 mTxt2RatBar;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
        mRatBar = (RatingBar)findViewById(R.id.ratBar);
        mSeekBar = (SeekBar)findViewById(R.id.seekBar);
        mTxtSeekBar = (TextView)findViewById(R.id.txtSeekBar);
        mTxt1RatBar = (TextView)findViewById(R.id.txt1RatBar);
        mTxt2RatBar = (TextView)findViewById(R.id.txt2RatBar);

        mSeekBar.setOnSeekBarChangeListener(seekBarOnChangeLis);
        mRatBar.setOnRatingBarChangeListener(ratBarOnChangeLis);
    }
    
    SeekBar.OnSeekBarChangeListener seekBarOnChangeLis =  new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String s = getString(R.string.resultSeekBar);
            mTxtSeekBar.setText(s + Integer.toString(progress));
        }
        public void onStartTrackingTouch(SeekBar seekBar) {
    		
        }
        public void onStopTrackingTouch(SeekBar seekBar) {
    		
        }
    };
    
    RatingBar.OnRatingBarChangeListener ratBarOnChangeLis =  new RatingBar.OnRatingBarChangeListener()            {
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			// TODO Auto-generated method stub
			String s = getString(R.string.result1RatBar);
			mTxt1RatBar.setText(s + Float.toString(rating));
			s = getString(R.string.result2RatBar);
			mTxt2RatBar.setText(s + Integer.toString(mRatBar.getProgress()));
		}
    };
}