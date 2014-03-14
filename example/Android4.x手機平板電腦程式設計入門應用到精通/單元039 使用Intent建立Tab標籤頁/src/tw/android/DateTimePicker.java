package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimePicker extends Activity {

	private DatePicker mDatePik;
	private TimePicker mTimePik;
	private TextView mTxtResult;
	private Button mBtnOK;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_picker);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件
        mDatePik = (DatePicker)findViewById(R.id.datePik);
        mTimePik = (TimePicker)findViewById(R.id.timePik);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnOK = (Button)findViewById(R.id.btnOK);

        mBtnOK.setOnClickListener(btnDoOKOnClick);
    }
    
    private Button.OnClickListener btnDoOKOnClick = new Button.OnClickListener() {
		public void onClick(View v) {
			String s = getString(R.string.result);
			mTxtResult.setText(s + mDatePik.getYear() + "年" +
								  (mDatePik.getMonth()+1) + "月 " +
								  mDatePik.getDayOfMonth() + "日" +
								  mTimePik.getCurrentHour() + "點" +
								  mTimePik.getCurrentMinute() + "分");
		}
	};
}
