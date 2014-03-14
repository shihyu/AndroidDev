package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button btnDoSug;
	private Spinner spnSex;
    private EditText edtAge;
    private TextView txtResult;

    private String sSex;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
        btnDoSug = (Button)findViewById(R.id.btnDoSug);
        spnSex = (Spinner)findViewById(R.id.spnSex);
        edtAge = (EditText)findViewById(R.id.edtAge);
        txtResult = (TextView)findViewById(R.id.txtResult);

        ArrayAdapter<CharSequence> adapSexList = ArrayAdapter.createFromResource(
				this, R.array.spnSexList, R.layout.spinner_layout);
        spnSex.setAdapter(adapSexList);

        // 設定元件的事件listener
        btnDoSug.setOnClickListener(btnDoSugOnClick);
        spnSex.setOnItemSelectedListener(spnSexItemSelLis);
    }

    private Spinner.OnItemSelectedListener spnSexItemSelLis =
    	new Spinner.OnItemSelectedListener () {
    		public void onItemSelected(AdapterView parent,
    									View v,
    									int position,
    									long id) {
    			sSex = parent.getSelectedItem().toString();
    		}
    		public void onNothingSelected(AdapterView parent) {
    		}
    };

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
    	public void onClick(View v) {
    		// 按下按鈕後要執行的程式碼
    		int iAge = Integer.parseInt(edtAge.getText().toString());
    			
			String strSug = getString(R.string.sugResult);
			if (sSex.equals(getString(R.string.sexMale)))
				if (iAge < 28)
					strSug += getString(R.string.sugNotHurry);
				else if (iAge > 33)
					strSug += getString(R.string.sugGetMarried);
				else
					strSug += getString(R.string.sugFindCouple);
			else
				if (iAge < 25)
					strSug += getString(R.string.sugNotHurry);
				else if (iAge > 30)
					strSug += getString(R.string.sugGetMarried);
				else
					strSug += getString(R.string.sugFindCouple);
    			
    		txtResult.setText(strSug);
    	}
    };
}