package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Main extends Activity implements ViewFactory {

	private ImageSwitcher imgSwi;
	private Gallery gal;

	private Main thisCont = this;

    private Integer[] imgArr = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08};
	
	private Integer[] thumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
    	imgSwi = (ImageSwitcher) findViewById(R.id.imgSwi);
    	imgSwi.setFactory(this);	// 必須implements ViewSwitcher.ViewFactory
    	imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
    	imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

    	ImageAdapter imgAdap = new ImageAdapter(this);
    	imgAdap.setImageArray(thumbImgArr);
    	
        gal = (Gallery) findViewById(R.id.gal);
        gal.setAdapter(imgAdap);
        gal.setOnItemSelectedListener(adaViewItemSelLis);
    }

    private AdapterView.OnItemSelectedListener adaViewItemSelLis =
    	new AdapterView.OnItemSelectedListener () {
		public void onItemSelected(AdapterView parent,
									View v,
									int position,
									long id) {
			switch ((int)(Math.random()*3 + 1)) {
			case 1:
				imgSwi.setInAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_alpha_in));
				imgSwi.setOutAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_alpha_out));
				break;
			case 2:
				imgSwi.setInAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_trans_in));
				imgSwi.setOutAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_trans_out));
				break;
			case 3:
				imgSwi.setInAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_scale_rotate_in));
				imgSwi.setOutAnimation(AnimationUtils.loadAnimation(thisCont,
		                R.anim.anim_scale_rotate_out));
				break;
			}

			imgSwi.setImageResource(imgArr[position]);
		}
		public void onNothingSelected(AdapterView parent) {
		}
    };

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        return v;

	}
}