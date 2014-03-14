package com.example.testservice;
/**引入包*/
	import android.view.View;
	import android.content.Context;
	import android.graphics.Canvas;
	import android.graphics.Color;
	import android.graphics.Paint;
	
	
	public class UseBriderFace extends View{
			/**创建参数*/
		public UseBriderFace(Context context){
			super(context);
		}
		public void onDraw(Canvas canvas){
			canvas.drawColor(Color.WHITE);//画白色背景
				/**绘制文字*/
			Paint textPaint = new Paint();
			textPaint.setColor(Color.RED);
			textPaint.setTextSize(30);
			canvas.drawText("使用绑定服务", 10, 30, textPaint);
			textPaint.setColor(Color.GREEN);
			textPaint.setTextSize(18);
			canvas.drawText("使用绑定服务后，这个Activity关闭后", 20, 60, textPaint);
			canvas.drawText("绑定的服务也会关闭", 5, 80, textPaint);
			
		}
	}
