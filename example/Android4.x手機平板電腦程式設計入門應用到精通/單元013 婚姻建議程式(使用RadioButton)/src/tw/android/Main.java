package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button btnDoSug;
    private RadioGroup radGSex, radGAge;
    private RadioButton radBtnAgeRng1, radBtnAgeRng2, radBtnAgeRng3;
    private TextView txtResult;

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
        radGSex = (RadioGroup)findViewById(R.id.radGSex);
        radGAge = (RadioGroup)findViewById(R.id.radGAge);
        radBtnAgeRng1 = (RadioButton)findViewById(R.id.radBtnAgeRng1);
        radBtnAgeRng2 = (RadioButton)findViewById(R.id.radBtnAgeRng2);
        radBtnAgeRng3 = (RadioButton)findViewById(R.id.radBtnAgeRng3);
        txtResult = (TextView)findViewById(R.id.txtResult);
    	
        // 設定事件listener
        btnDoSug.setOnClickListener(btnDoSugOnClick);
        radGSex.setOnCheckedChangeListener(radGSexOnCheChanLis);
    }

    private RadioGroup.OnCheckedChangeListener radGSexOnCheChanLis = new RadioGroup.OnCheckedChangeListener () {
    	public void onCheckedChanged(RadioGroup group, int checkedId)
    	{
    	    if (checkedId == R.id.radMale) {
    		    radBtnAgeRng1.setText(getString(R.string.maleAgeRng1));
    			radBtnAgeRng2.setText(getString(R.string.maleAgeRng2));
    			radBtnAgeRng3.setText(getString(R.string.maleAgeRng3));
    		}
    		else if (checkedId == R.id.radFemale) {
    			radBtnAgeRng1.setText(getString(R.string.femaleAgeRng1));
    			radBtnAgeRng2.setText(getString(R.string.femaleAgeRng2));
    			radBtnAgeRng3.setText(getString(R.string.femaleAgeRng3));
    		}
    	}
    };

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
    	public void onClick(View v) {
    		// 按下按鈕後要執行的程式碼
			int iCheckedRadBtn = radGSex.getCheckedRadioButtonId();
			
			String strSug = getString(R.string.sugResult);
			switch (iCheckedRadBtn)
			{
			case R.id.radMale:
				switch (radGAge.getCheckedRadioButtonId())
				{
				case R.id.radBtnAgeRng1:
					strSug += getString(R.string.sugNotHurry);
					break;
				case R.id.radBtnAgeRng3:
					strSug += getString(R.string.sugGetMarried);
					break;
				default:
					strSug += getString(R.string.sugFindCouple);
				}
				break;
			case R.id.radFemale:
				switch (radGAge.getCheckedRadioButtonId())
				{
				case R.id.radBtnAgeRng1:
					strSug += getString(R.string.sugNotHurry);
					break;
				case R.id.radBtnAgeRng3:
					strSug += getString(R.string.sugGetMarried);
					break;
				default:
					strSug += getString(R.string.sugFindCouple);
				}
				break;
			}
    			
    		txtResult.setText(strSug);
    	}
    };
}