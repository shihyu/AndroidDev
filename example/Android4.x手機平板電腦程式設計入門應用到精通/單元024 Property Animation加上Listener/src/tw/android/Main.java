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
	private Button mBtnBackColorAnim,
				   mBtnFallAnim,
				   mBtnMoveAnim,
				   mBtnAlphaAnim,
				   mBtnRotateAnim,
				   mBtnScaleAnim;
	private float y, yEnd;
	private boolean mIsFallingFirst = true;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
    	mLinLay = (LinearLayout)findViewById(R.id.linLay);
    	mTxt = (TextView)findViewById(R.id.txt);
    	mBtnBackColorAnim = (Button)findViewById(R.id.btnBackColorAnim);
    	mBtnFallAnim = (Button)findViewById(R.id.btnFallAnim);
    	mBtnMoveAnim = (Button)findViewById(R.id.btnMoveAnim);
    	mBtnAlphaAnim = (Button)findViewById(R.id.btnAlphaAnim);
    	mBtnRotateAnim = (Button)findViewById(R.id.btnRotateAnim);
    	mBtnScaleAnim = (Button)findViewById(R.id.btnScaleAnim);

    	mBtnBackColorAnim.setOnClickListener(btnBackColorAnimOnClickLis);
    	mBtnFallAnim.setOnClickListener(btnFallAnimOnClickLis);
    	mBtnMoveAnim.setOnClickListener(btnMoveAnimOnClickLis);
    	mBtnAlphaAnim.setOnClickListener(btnAlphaAnimOnClickLis);
    	mBtnRotateAnim.setOnClickListener(btnRotateAnimOnClickLis);
    	mBtnScaleAnim.setOnClickListener(btnScaleAnimOnClickLis);
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
				// 計算掉落的y座標
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

    private Button.OnClickListener btnScaleAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
	    	ValueAnimator animTxtScale = 
	    		ValueAnimator.ofInt(0, 50);
	    	animTxtScale.setDuration(4000);
	    	animTxtScale.setRepeatCount(1);
	    	animTxtScale.setRepeatMode(ObjectAnimator.REVERSE);
	    	animTxtScale.setInterpolator(new LinearInterpolator());
	    	animTxtScale.addUpdateListener(new AnimatorUpdateListener(){
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					int val = (Integer)animation.getAnimatedValue();
					mTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15+val);
				}
			});
	    	animTxtScale.start();
		}
	};

    private Button.OnClickListener btnMoveAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			// 取得整個螢幕的寬度和高度
//			int iScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
//			int iScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
	    	
	    	float x, xEnd1, xEnd2;
	    	
	    	x = mTxt.getX();
	    	xEnd1 = 0;
	    	xEnd2 = mLinLay.getWidth() - mTxt.getWidth();
	    	
	    	ObjectAnimator animTxtMove1 = 
	    		ObjectAnimator.ofFloat(mTxt, "x", x, xEnd1);
	    	animTxtMove1.setDuration(2000);
	    	animTxtMove1.setInterpolator(new  AccelerateDecelerateInterpolator());
	    	
	    	ObjectAnimator animTxtMove2 = 
	    		ObjectAnimator.ofFloat(mTxt, "x", xEnd1, xEnd2);
	    	animTxtMove2.setDuration(3000);
	    	animTxtMove2.setInterpolator(new  AccelerateDecelerateInterpolator());
	    	
	    	ObjectAnimator animTxtMove3 = 
	    		ObjectAnimator.ofFloat(mTxt, "x", xEnd2, x);
	    	animTxtMove3.setDuration(2000);
	    	animTxtMove3.setInterpolator(new  AccelerateDecelerateInterpolator());

	    	AnimatorSet animTxtMove = new AnimatorSet();
	    	animTxtMove.playSequentially(animTxtMove1, animTxtMove2, animTxtMove3);
	    	animTxtMove.start();
		}
	};

    private Button.OnClickListener btnBackColorAnimOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			// 取得整個螢幕的寬度和高度
//			int iScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
//			int iScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
	    	
	    	int iBackColorRedVal, iBackColorRedEnd;
	    	final int iBackColor = ((ColorDrawable)(mLinLay.getBackground())).getColor();
	    	iBackColorRedVal = (iBackColor & 0x00FF0000) >> 16;

	    	if (iBackColorRedVal > 127)
	    		iBackColorRedEnd = 0;
	    	else
	    		iBackColorRedEnd = 255;

	    	ValueAnimator animScreenBackColor = 
	    		ValueAnimator.ofInt(iBackColorRedVal, iBackColorRedEnd);
	    	animScreenBackColor.setDuration(3000);
	    	animScreenBackColor.setInterpolator(new LinearInterpolator());
	    	animScreenBackColor.start();
	    	animScreenBackColor.addUpdateListener(new AnimatorUpdateListener(){
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					int val = (Integer)animation.getAnimatedValue();
					mLinLay.setBackgroundColor(iBackColor & 0xFF00FFFF | val << 16);
				}
			});
		}
	};
}