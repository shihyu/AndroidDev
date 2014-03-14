package tw.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button mBtnAlertDlg,
				   mBtnAlertDlgBld;
	private TextView mTxtResult;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
		mBtnAlertDlg = (Button)findViewById(R.id.btnAlertDlg);
		mBtnAlertDlgBld = (Button)findViewById(R.id.btnAlertDlgBld);
		mTxtResult = (TextView)findViewById(R.id.txtResult);

		mBtnAlertDlg.setOnClickListener(btnAlertDlgOnClkLis);
		mBtnAlertDlgBld.setOnClickListener(btnAlertDlgBldOnClkLis);
    }

    private Button.OnClickListener btnAlertDlgOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			mTxtResult.setText("");
			
			MyAlertDialog myAltDlg = new MyAlertDialog(Main.this);
			
			myAltDlg.setTitle("AlertDialog");
			myAltDlg.setMessage("AlertDialog的使用方式是要自己建立一個class來繼承它");
			myAltDlg.setIcon(android.R.drawable.ic_dialog_info);
			myAltDlg.setCancelable(false);
			myAltDlg.setButton(DialogInterface.BUTTON_POSITIVE, "是", altDlgOnClkPosiBtnLis);
			myAltDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "否", altDlgOnClkNegaBtnLis);
			myAltDlg.setButton(DialogInterface.BUTTON_NEUTRAL, "取消", altDlgOnClkNeutBtnLis);
			myAltDlg.show();			
		}
	};
	
	private  DialogInterface.OnClickListener altDlgOnClkPosiBtnLis = new  DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			mTxtResult.setText("你啟動了AlertDialog而且按下了\"是\"按鈕");
		}
	};

	private  DialogInterface.OnClickListener altDlgOnClkNegaBtnLis = new  DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			mTxtResult.setText("你啟動了AlertDialog而且按下了\"否\"按鈕");
		}
	};

	private  DialogInterface.OnClickListener altDlgOnClkNeutBtnLis = new  DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			mTxtResult.setText("你啟動了AlertDialog而且按下了\"取消\"按鈕");
		}
	};

    private Button.OnClickListener btnAlertDlgBldOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			mTxtResult.setText("");
			
			AlertDialog.Builder altDlgBldr = new AlertDialog.Builder(Main.this);
			
			altDlgBldr.setTitle("AlertDialog");
			altDlgBldr.setMessage("由AlertDialog.Builder產生");
			altDlgBldr.setIcon(android.R.drawable.ic_dialog_info);
			altDlgBldr.setCancelable(false);
			altDlgBldr.setPositiveButton("是",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mTxtResult.setText("你啟動了AlertDialogBuilder而且按下了\"是\"按鈕");
					}
				});
			altDlgBldr.setNegativeButton("否",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mTxtResult.setText("你啟動了AlertDialogBuilder而且按下了\"否\"按鈕");
					}
				});
			altDlgBldr.setNeutralButton("取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mTxtResult.setText("你啟動了AlertDialogBuilder而且按下了\"取消\"按鈕");
					}
				});
			altDlgBldr.show();			
		}
	};
}