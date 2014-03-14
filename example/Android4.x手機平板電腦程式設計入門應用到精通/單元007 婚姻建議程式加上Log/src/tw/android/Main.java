package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button btnDoSug;
    private EditText edtSex, edtAge;
    private TextView txtResult;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 從資源類別R中取得介面元件
        btnDoSug = (Button)findViewById(R.id.btnDoSug);
        edtSex = (EditText)findViewById(R.id.edtSex);
        edtAge = (EditText)findViewById(R.id.edtAge);
        txtResult = (TextView)findViewById(R.id.txtResult);

   	    // 設定button元件的事件listener
   	    btnDoSug.setOnClickListener(btnDoSugOnClick);
    }

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
    	public void onClick(View v) {
    		// 按下按鈕後要執行的程式碼
    		String strSex = edtSex.getText().toString();
    		int iAge = Integer.parseInt(edtAge.getText().toString());
    			
    		String strSug = "結果：";
    		if (strSex.equals("男"))
    			if (iAge < 28) {
    				strSug += "不急";
    				Log.d("MarriSug", "man, don't hurry");
    			}
    			else if (iAge > 33) {
    				strSug += "趕快結婚";
    				Log.d("MarriSug", "man, hurry to get married!");
    			}
    			else {
    				strSug += "開始找對象";
    				Log.d("MarriSug", "man, start to find girlfriend!");
    			}
    		else
    			if (iAge < 25) {
    				strSug += "不急";
    				Log.d("MarriSug", "woman, don't hurry!");
    			}
    			else if (iAge > 30) {
    				strSug += "趕快結婚";
    				Log.d("MarriSug", "woman, hurry to get married!");
    			}
    			else {
    				strSug += "開始找對象";
    				Log.d("MarriSug", "woman, start to find boyfriend!");
    			}
    			
    		txtResult.setText(strSug);
    	}
    };
}