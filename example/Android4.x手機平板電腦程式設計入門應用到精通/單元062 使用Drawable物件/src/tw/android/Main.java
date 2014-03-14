package tw.android;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Main extends Activity {

	private ImageView mImgView1,
					  mImgView2,
					  mImgView3;
	private Button mBtnStart;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }

    private void setupViewComponent() {
		mImgView1 = (ImageView)findViewById(R.id.imgView1);
		mImgView2 = (ImageView)findViewById(R.id.imgView2);
		mImgView3 = (ImageView)findViewById(R.id.imgView3);
		
  	    mBtnStart = (Button)findViewById(R.id.btnStart);
  	    mBtnStart.setOnClickListener(btnStartOnClick);
    }

	private Button.OnClickListener btnStartOnClick = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
		   // TODO Auto-generated method stub
		   Resources res = getResources();
		
		   Drawable drawImg = res.getDrawable(R.drawable.img01);
		   mImgView1.setBackgroundDrawable(drawImg);
		
		   TransitionDrawable drawTran = (TransitionDrawable)res.getDrawable(R.drawable.tran_drawable);
		   mImgView2.setImageDrawable(drawTran);
		   drawTran.startTransition(5000);
		
		   GradientDrawable gradShape = new GradientDrawable();
		   gradShape.setShape(GradientDrawable.OVAL);
		   gradShape.setColor(0xffffff00);
		   mImgView3.setBackgroundDrawable(gradShape);
		}
	};
}