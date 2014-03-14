package tw.android;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ProgBarDemo extends Activity {

	private Handler mHandler = new Handler();

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prog_bar_demo);
        
        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
       	final ProgressBar proBar = (ProgressBar)findViewById(R.id.proBar2);
       	
       	new Thread(new Runnable() {
               public void run() {
               	Calendar begin = Calendar.getInstance();
   		    	do {
   		    		Calendar now = Calendar.getInstance();
   		    		final int iDiffSec = 
   		    				60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) + 
   		    				now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);
   		    	
   		    		if (iDiffSec * 2 > 100) {
   		    			mHandler.post(new Runnable() {
   			                public void run() {
   			                	proBar.setProgress(100);
   			                }
   			            });
   		    			
   		    			break;
   		    		}
   		    		
   		    		mHandler.post(new Runnable() {
   		                public void run() {
   		                	proBar.setProgress(iDiffSec * 2);
   		                }
   		            });
   		    		
   		    		if (iDiffSec * 4 < 100)
   		    			mHandler.post(new Runnable() {
   			            public void run() {
   			                	proBar.setSecondaryProgress(iDiffSec * 4);
   			                }
   			            });
   		    		else
   		    			mHandler.post(new Runnable() {
   			                public void run() {
   			                	proBar.setSecondaryProgress(100);
   			                }
   			            });
   		    	} while (true);
               }
           }).start();
    }
}
