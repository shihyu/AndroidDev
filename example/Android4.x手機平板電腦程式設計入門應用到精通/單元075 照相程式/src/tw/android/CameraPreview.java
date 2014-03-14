package tw.android;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class CameraPreview extends SurfaceView 
	implements SurfaceHolder.Callback {

	private Camera mCamera;
	private SurfaceHolder mSurfHolder;
	private Activity mActivity;

	public CameraPreview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

        mSurfHolder = getHolder();
        mSurfHolder.addCallback(this);
        mSurfHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void set(Activity activity, Camera camera) {
		mActivity = activity;
		mCamera = camera;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		try {
			mCamera.setPreviewDisplay(mSurfHolder);

			Camera.CameraInfo camInfo = new Camera.CameraInfo();
			Camera.getCameraInfo(0, camInfo);

			int rotation = mActivity.getWindowManager().getDefaultDisplay().getRotation();
			int degrees = 0;
			switch (rotation) {
			case Surface.ROTATION_0:
				degrees = 0; break;
			case Surface.ROTATION_90:
				degrees = 90; break;
			case Surface.ROTATION_180:
				degrees = 180; break;
			case Surface.ROTATION_270:
				degrees = 270; break;
			}

			int result;
			result = (camInfo.orientation - degrees + 360) % 360;
			mCamera.setDisplayOrientation(result);
			
			mCamera.startPreview();
			
			Camera.Parameters camParas = mCamera.getParameters();
			if (camParas.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_AUTO) || 
				camParas.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_MACRO))
				mCamera.autoFocus(onCamAutoFocus);
			else
				Toast.makeText(getContext(), "照相機不支援自動對焦！", Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(getContext(), "照相機啟始錯誤！", Toast.LENGTH_LONG)
				.show();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	Camera.AutoFocusCallback onCamAutoFocus = new Camera.AutoFocusCallback() {

		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			// TODO Auto-generated method stub
			Toast.makeText(getContext(), "自動對焦！", Toast.LENGTH_SHORT)
				.show();
		}
		
	};
}
