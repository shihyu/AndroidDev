package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyImageAct extends Activity {

	private TextView mTxtResult;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_image_act);
        
		setupViewComponent();
		showResult();
    }
    
    private void setupViewComponent() {
    	mTxtResult = (TextView)findViewById(R.id.txtResult);
    }
	
	private void showResult() {
        Intent it = getIntent();
        String sAct = it.getAction();
        String sScheme = it.getScheme();
        if (sScheme.equals("http")) {
        	String s = "接收到的Intent物件要求\"開啟網頁\"" + it.getData().toString();
        	mTxtResult.setText(s);
        }
        else if (sScheme.equals("tel")) {
        	String s = "接收到的Intent物件要求\"撥打電話\"" + it.getData().toString();
        	mTxtResult.setText(s);
        }
        else if (sScheme.equals("file")) {
        	if (sAct.equals("android.intent.action.VIEW")) {
	        	String s = "接收到的Intent物件要求\"檢視\"" + it.getData().toString();
	        	mTxtResult.setText(s);
	        }
	        else if (sAct.equals("android.intent.action.EDIT")) {
	        	String s = "接收到的Intent物件要求\"編輯\"" + it.getData().toString();
	        	mTxtResult.setText(s);
	        }
	    }
	}
}
