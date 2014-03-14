package tw.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MarriSugFragment extends Fragment {

	private Button btnDoSug;
    private RadioGroup radGSex, radGAge;
    private RadioButton radBtnAgeRng1, radBtnAgeRng2, radBtnAgeRng3;
    private TextView txtResult;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
   		return inflater.inflate(R.layout.marri_sug, container, false);
	}

    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
        btnDoSug = (Button)getView().findViewById(R.id.btnDoSug);
        radGSex = (RadioGroup)getView().findViewById(R.id.radGSex);
        radGAge = (RadioGroup)getView().findViewById(R.id.radGAge);
        radBtnAgeRng1 = (RadioButton)getView().findViewById(R.id.radBtnAgeRng1);
        radBtnAgeRng2 = (RadioButton)getView().findViewById(R.id.radBtnAgeRng2);
        radBtnAgeRng3 = (RadioButton)getView().findViewById(R.id.radBtnAgeRng3);
        txtResult = (TextView)getView().findViewById(R.id.txtResult);
    	
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
