package tw.android;

import android.animation.*;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.*;
import android.widget.*;

public class Main extends Activity {

	private LinearLayout mLinLay;
	private TextView mTxt;
	private Button mBtnFallAnim,
				   mBtnAlphaAnim,
				   mBtnRotateAnim;
	private float y, yEnd;
	private boolean mIsFallingFirst = true;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void setupViewComponent() {
    	mLinLay = (LinearLayout)findViewById(R.id.linLay);
    	mTxt = (TextView)findViewById(R.id.txt);
    	mBtnFallAnim = (Button)findViewById(R.id.btnFallAnim);
    	mBtnAlphaAnim = (Button)findViewById(R.id.btnAlphaAnim);
    	mBtnRotateAnim = (Button)findViewById(R.id.btnRotateAnim);

    	mBtnFallAnim.setOnClickListener(btnFallAnimOnClickLis);
    	mBtnAlphaAnim.setOnClickListener(btnAlphaAnimOnClickLis);
    	mBtnRotateAnim.setOnClickListener(btnRotateAnimOnClickLis);
    }

    private Button.OnClickListener btnRotateAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
	    	ObjectAnimator animTxtRotate = 
	    		ObjectAnimator.ofFloat(mTxt, "rotation", 0, 360);
	    	animTxtRotate.setDuration(3000);
	    	animTxtRotate.setRepeatCount(1);
	    	animTxtRotate.setRepeatMode(ObjectAnimator.REVERSE);
	    	animTxtRotate.setInterpolator(new  AccelerateDecelerateInterpolator());
	    	animTxtRotate.start();
		}
	};

    private Button.OnClickListener btnAlphaAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
	    	ObjectAnimator animTxtAlpha = 
	    		ObjectAnimator.ofFloat(mTxt, "alpha", 1, 0);
	    	animTxtAlpha.setDuration(2000);
	    	animTxtAlpha.setRepeatCount(1);
	    	animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
	    	animTxtAlpha.setInterpolator(new  LinearInterpolator());
	    	animTxtAlpha.start();
		}
	};

    private Button.OnClickListener btnFallAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			if (mIsFallingFirst) {
				// ­pºâ±¼¸¨ªºy®y¼Ð
		    	y = mTxt.getY();
		    	yEnd = mLinLay.getHeight() - mTxt.getHeight();

		    	mIsFallingFirst = false;
			}
			
			ObjectAnimator animTxtFalling = 
	    		ObjectAnimator.ofFloat(mTxt, "y", y, yEnd);
	    	animTxtFalling.setDuration(2000);
	    	animTxtFalling.setRepeatCount(ObjectAnimator.INFINITE);
	    	animTxtFalling.setInterpolator(new  BounceInterpolator());
	    	animTxtFalling.start();
		}
	};
}