package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {
	private Button btnAddAutoCompleteText,
				   btnClrAutoCompleteText;
	private AutoCompleteTextView autCompTextView;

	private ArrayAdapter<String> adapAutoCompText;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
        btnAddAutoCompleteText = (Button)findViewById(R.id.btnAddAutoCompleteText);
        btnClrAutoCompleteText = (Button)findViewById(R.id.btnClrAutoCompleteText);
        autCompTextView = (AutoCompleteTextView)findViewById(R.id.autCompTextView);
    	
        adapAutoCompText = new ArrayAdapter<String>(
        		this, android.R.layout.simple_dropdown_item_1line);
    	
        autCompTextView.setAdapter(adapAutoCompText);
    	
        // 設定button元件的事件listener
        btnAddAutoCompleteText.setOnClickListener(btnAddAutoCompleteTextOnClickLis);
        btnClrAutoCompleteText.setOnClickListener(btnClrAutoCompleteTextOnClickLis);
    }
    
    private Button.OnClickListener btnAddAutoCompleteTextOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			String s = autCompTextView.getText().toString();
			adapAutoCompText.add(s);
			autCompTextView.setText("");
		}
	};
	
    private Button.OnClickListener btnClrAutoCompleteTextOnClickLis = new Button.OnClickListener() {
		public void onClick(View v) {
			adapAutoCompText.clear();
		}
	};
}