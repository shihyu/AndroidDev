package tw.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {

	// 儲存OpenGL物件用到的頂點座標和顏色
	private FloatBuffer mVertBuf,
					 mVertColorBuf;
	
	// 儲存OpenGL物件的頂點在mVertBuf中的索引
	private ShortBuffer mIndexBuf;
	
	// OpenGL物件用到的頂點座標，在程式中會將它們設定給mVertBuf
	// 每一列是一個頂點的xyz座標
	private float[] m3DObjVert = {
		-0.5f, -0.5f, 0.5f,
		0.5f, -0.5f, 0.5f,
		0f, -0.5f, -0.5f,
		0f, 0.5f, 0f
	};
	
	// OpenGL物件用到的頂點的顏色，在程式中會將它們設定給mVertColorBuf
	// 每一列是一個頂點的顏色，顏色值的順序為rgba，a是alpha值
	private float[] m3DObjVertColor = {
			1.0f, 1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f, 1.0f,
			0.0f, 1.0f, 1.0f, 1.0f,
			1.0f, 1.0f, 0.5f, 1.0f
	};
	
	// OpenGL物件的頂點在mVertBuf中的索引，每一列代表一個triangle
	// 在程式中會將它們設定給mIndexBuf
	private short[] m3DObjVertIndex = {
		0, 2, 1,
		3, 2, 0,
		3, 1, 2,
		1, 3, 0
	};
	
	// 最遠方的底色
	private float backColorR = 0.3f,
				  backColorG = 0.3f,
				  backColorB = 0.3f,
				  backColorA = 1.0f;

	private float mfRotaAng = 0f;

	private void setup() {
		// 建立OpenGL專用的vertex buffer
		ByteBuffer vertBuf = ByteBuffer.allocateDirect(
				4 * m3DObjVert.length);
		vertBuf.order(ByteOrder.nativeOrder());
		mVertBuf = vertBuf.asFloatBuffer();
		
		// 建立OpenGL專用的vertex color buffer
		ByteBuffer vertColorBuf = ByteBuffer.allocateDirect(
				4 * m3DObjVertColor.length);
		vertColorBuf.order(ByteOrder.nativeOrder());
		mVertColorBuf = vertColorBuf.asFloatBuffer();
		
		// 建立OpenGL專用的index buffer
		ByteBuffer indexBuf = ByteBuffer.allocateDirect(
				2 * m3DObjVertIndex.length);
		indexBuf.order(ByteOrder.nativeOrder());
		mIndexBuf = indexBuf.asShortBuffer();

		mVertBuf.put(m3DObjVert);
		mVertColorBuf.put(m3DObjVertColor);
		mIndexBuf.put(m3DObjVertIndex);
		
		mVertBuf.position(0);
		mVertColorBuf.position(0);
		mIndexBuf.position(0);
	}

	public MyGLSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		setRenderer(this);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub

		// 清除場景填上背景顏色，並且清除z-buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
				   GL10.GL_DEPTH_BUFFER_BIT);
		
		// 設定物件用到的頂點座標
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertBuf);
		
		// 設定物件用到的頂點的顏色
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, mVertColorBuf);

		gl.glLoadIdentity();
		
		// 將物件沿指定的軸移動
		gl.glTranslatef(0f, 0f, -4f);

		// 將物件沿指定的軸轉動
		gl.glRotatef(mfRotaAng, 0f, 1f, 0f);
		mfRotaAng += 1f;

		// 設定物件頂點的索引並繪製物件
		gl.glDrawElements(GL10.GL_TRIANGLES, m3DObjVertIndex.length,
						  GL10.GL_UNSIGNED_SHORT, mIndexBuf);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

		// 設定透視投影參數
		// 設定最近和最遠的可視範圍和左右視角
		// 上下可視範圍由左右視角和螢幕的寬高比來計算
		final float fNEAREST = .01f,
					fFAREST = 100f,
					fVIEW_ANGLE = 45f;
		gl.glMatrixMode(GL10.GL_PROJECTION); // 切換到投影矩陣模式
		float fViewWidth = fNEAREST * (float) Math.tan(Math.toRadians(fVIEW_ANGLE) / 2);
		float aspectRatio = (float)width / (float)height;
		gl.glFrustumf(-fViewWidth, fViewWidth,
					  -fViewWidth / aspectRatio, fViewWidth / aspectRatio,
					  fNEAREST, fFAREST);
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// 切換到原來模式

		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

		// 設定3D場景的背景顏色，也就是clipping wall的顏色
		gl.glClearColor(backColorR, backColorG, backColorB, backColorA);

		// 設定OpenGL的功能
		gl.glEnable(GL10.GL_DEPTH_TEST);	// 物體遠近的遮蔽效果
		gl.glEnable(GL10.GL_CULL_FACE);	// 區分Triangle的正反面
		gl.glFrontFace(GL10.GL_CCW);	// 逆時針頂點順序為正面
		gl.glCullFace(GL10.GL_BACK);	// 反面的Triangle不顯示
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);	// 使用頂點陣列
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);	// 使用顏色陣列

		setup();
	}

}
