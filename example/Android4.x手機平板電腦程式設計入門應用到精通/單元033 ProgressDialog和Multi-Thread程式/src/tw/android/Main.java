package tw.android;

import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	private Button mBtnProgDlg;
    private Handler mHandler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
		mBtnProgDlg = (Button)findViewById(R.id.btnProgDlg);
		mBtnProgDlg.setOnClickListener(btnProgDlgOnClkLis);
    }

    private Button.OnClickListener btnProgDlgOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			final ProgressDialog progDlg = new ProgressDialog(Main.this);
			progDlg.setTitle("請稍等");
			progDlg.setMessage("執行中...");
			progDlg.setIcon(android.R.drawable.ic_dialog_info);
			progDlg.setCancelable(false);
			progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progDlg.setMax(100);
			progDlg.show();
			
			new Thread(new Runnable() {
				 public void run() {
				   	Calendar begin = Calendar.getInstance();
				    	do {
				    		Calendar now = Calendar.getInstance();
				    		final int iDiffSec = 60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) +
				    						now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);
				
				    		if (iDiffSec * 2 > 100) {
				    			mHandler.post(new Runnable() {
					                public void run() {
					                	progDlg.setProgress(100);
					                }
					            });
				    			
				    			break;
				    		}
				    		
				    		mHandler.post(new Runnable() {
				                public void run() {
				                	progDlg.setProgress(iDiffSec * 2);
				                }
				            });
				    		
				    		if (iDiffSec * 4 < 100)
				    			mHandler.post(new Runnable() {
					                public void run() {
					                	progDlg.setSecondaryProgress(iDiffSec * 4);
					                }
					            });
				    		else
				    			mHandler.post(new Runnable() {
					                public void run() {
					                	progDlg.setSecondaryProgress(100);
					                }
					            });
				    	} while (true);
				    	
				    	progDlg.cancel();
				   }
			}).start();
		}
	};
}