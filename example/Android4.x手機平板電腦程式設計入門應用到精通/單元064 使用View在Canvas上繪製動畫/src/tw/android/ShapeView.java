package tw.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class ShapeView extends View implements Runnable {

	private Paint mPaintForeColor,
		  		  mPaintBackColor;
	private static final int INT_STROCK_THICK = 2;
	private int mIntXMaxLen, mIntYMaxLen,
				mIntXCent, mIntYCent,
				mIntXCurLen, mIntYCurLen,
				mIntSign;
	
	private Handler mHandler = new Handler();

	public ShapeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		setFocusable(true);
		
		mPaintForeColor = new Paint();
		mPaintForeColor.setAntiAlias(true);
		mPaintForeColor.setColor(Color.CYAN);

		mPaintBackColor = new Paint();
		mPaintBackColor.setAntiAlias(true);
		mPaintBackColor.setColor(Color.BLACK);
		
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Log.d("TEST_VIEW", "onDraw()");
		
		canvas.drawOval(new RectF(mIntXCent - mIntXCurLen,
								  mIntYCent - mIntYCurLen,
								  mIntXCent + mIntXCurLen,
								  mIntYCent + mIntYCurLen), 
				mPaintForeColor);
		canvas.drawOval(new RectF(mIntXCent - mIntXCurLen + INT_STROCK_THICK,
								  mIntYCent - mIntYCurLen + INT_STROCK_THICK,
								  mIntXCent + mIntXCurLen - INT_STROCK_THICK,
								  mIntYCent + mIntYCurLen - INT_STROCK_THICK), 
				mPaintBackColor);
		
		if (mIntXCurLen + mIntSign * INT_STROCK_THICK < 0 ||
			mIntYCurLen + mIntSign * INT_STROCK_THICK < 0) {
			mIntSign = 1;
		}
		else if (mIntXCurLen + mIntSign * INT_STROCK_THICK > mIntXMaxLen ||
			mIntYCurLen + mIntSign * INT_STROCK_THICK > mIntYMaxLen) {
			mIntSign = -1;
		}

		mIntXCurLen += mIntSign * INT_STROCK_THICK;
		mIntYCurLen += mIntSign * INT_STROCK_THICK;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		mIntXMaxLen = w / 2 - 10;
		mIntYMaxLen = h / 2 - 10;
		mIntXCent = w / 2;
		mIntYCent = h / 2;
		
		mIntXCurLen = mIntXMaxLen;
		mIntYCurLen = mIntYMaxLen;
		mIntSign = -1;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 10000; i++) {
			Log.d("TEST_VIEW", "run() " + i);
			mHandler.post(new Runnable() {
				public void run() {
					Log.d("TEST_VIEW", "invalidate()");
					invalidate();
				}
			});
		}
	}

}
