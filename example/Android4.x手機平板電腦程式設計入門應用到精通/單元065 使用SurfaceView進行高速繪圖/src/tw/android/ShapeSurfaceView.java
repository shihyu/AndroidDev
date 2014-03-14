package tw.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShapeSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private Paint mPaintForeColor,
				  mPaintBackColor;
	private static final int INT_STROCK_THICK = 2;
	private int mIntXMaxLen, mIntYMaxLen,
			    mIntXCent, mIntYCent,
			    mIntXCurLen, mIntYCurLen,
			    mIntSign;
	
	private SurfaceHolder mSurfHold;

	public ShapeSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		mSurfHold = getHolder();
		mSurfHold.addCallback(this);
		
		setFocusable(true);
		
		mPaintForeColor = new Paint();
		mPaintForeColor.setAntiAlias(true);
		mPaintForeColor.setColor(Color.CYAN);

		mPaintBackColor = new Paint();
		mPaintBackColor.setAntiAlias(true);
		mPaintBackColor.setColor(Color.BLACK);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);

		Log.d("TEST_SURFACEVIEW", "draw()");
		
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
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 500; i++) {
			Log.d("TEST_SURFACEVIEW", "run() " + i);

			Canvas c = null;
			try {
				c = mSurfHold.lockCanvas();
				synchronized(mSurfHold) {
					draw(c);
				}
			}
			finally {
				if (c != null)
					mSurfHold.unlockCanvasAndPost(c);
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		mIntXMaxLen = width / 2 - 10;
		mIntYMaxLen = height / 2 - 10;
		mIntXCent = width / 2;
		mIntYCent = height / 2;
		
		mIntXCurLen = mIntXMaxLen;
		mIntYCurLen = mIntYMaxLen;
		mIntSign = -1;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
