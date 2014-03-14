package tw.android;

import java.io.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

private static final String FILE_NAME = "file io.txt";
	
	private EditText mEdtIn,
	 				 mEdtFileContent;

	private Button mBtnAdd,
				   mBtnRead,
				   mBtnClear;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }

    private void setupViewComponent() {
    	mEdtIn = (EditText)findViewById(R.id.edtIn);
		mEdtFileContent = (EditText)findViewById(R.id.edtFileContent);

		mBtnAdd = (Button)findViewById(R.id.btnAdd);
		mBtnRead = (Button)findViewById(R.id.btnRead);
		mBtnClear = (Button)findViewById(R.id.btnClear);

		mBtnAdd.setOnClickListener(onClickBtnAdd);
		mBtnRead.setOnClickListener(onClickBtnRead);
		mBtnClear.setOnClickListener(onClickBtnClear);
    }

    private Button.OnClickListener onClickBtnAdd = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		       
	          FileOutputStream fileOut = null;
	          BufferedOutputStream bufFileOut = null;
	        
			try {
				fileOut = openFileOutput(FILE_NAME, MODE_APPEND);
				bufFileOut = new BufferedOutputStream(fileOut);
				bufFileOut.write(mEdtIn.getText().toString().getBytes());
				bufFileOut.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    };

    private Button.OnClickListener onClickBtnRead = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

	          FileInputStream fileIn = null;
	          BufferedInputStream bufFileIn = null;

			try {
				fileIn = openFileInput("file io.txt");
				bufFileIn = new BufferedInputStream(fileIn);
				
				byte[] bufBytes = new byte[10];
				
				mEdtFileContent.setText("");
				
				do {
					int c = bufFileIn.read(bufBytes);
					
					if (c == -1)
						break;
					else
						mEdtFileContent.append(new String(bufBytes), 0, c);
				} while (true);
				
				bufFileIn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    };

    private Button.OnClickListener onClickBtnClear = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		       
	          FileOutputStream fileOut = null;
	        
			try {
				fileOut = openFileOutput(FILE_NAME, MODE_PRIVATE);
				fileOut.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    };
}