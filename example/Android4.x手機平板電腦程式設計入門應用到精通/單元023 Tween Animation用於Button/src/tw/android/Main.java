package tw.android;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageSwitcher;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btn = (Button)findViewById(R.id.btn);
/*        Animation anim = AnimationUtils.loadAnimation(Main.this,
		                R.anim.rotate);
        btn.startAnimation(anim);
*/
        ObjectAnimator animBtnRotate = 
    		ObjectAnimator.ofFloat(btn, "rotation", 0, 360);
    	animBtnRotate.setDuration(6000);
    	animBtnRotate.setInterpolator(new CycleInterpolator(1));
    	animBtnRotate.start();
    }
}