package tw.android;

import java.util.Calendar;

import android.os.Handler;
import android.widget.ProgressBar;

public class DoLengthyWork extends Thread {

	private Handler mHandler;
	private ProgressBar mProBar;
	
	public void run () {
		Calendar begin = Calendar.getInstance();
    	do {
    		Calendar now = Calendar.getInstance();
    		final int iDiffSec = 60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) +
    						now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);
    	
    		if (iDiffSec * 2 > 100) {
    			mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setProgress(100);
	                }
	            });
    			
    			break;
    		}
    		
    		mHandler.post(new Runnable() {
                public void run() {
                	mProBar.setProgress(iDiffSec * 2);
                }
            });
    		
    		if (iDiffSec * 4 < 100)
    			mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setSecondaryProgress(iDiffSec * 4);
	                }
	            });
    		else
    			mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setSecondaryProgress(100);
	                }
	            });
    	} while (true);
	}
	
	void setProgressBar(ProgressBar proBar) {
		mProBar = proBar;
	}
	
	void setHandler(Handler h) {
		mHandler = h;
	}
}
