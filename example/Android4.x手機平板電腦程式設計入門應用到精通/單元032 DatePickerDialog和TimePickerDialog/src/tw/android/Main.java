package tw.android;

import java.util.Calendar;

import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button mBtnTimePicDlg,
				   mBtnDatePicDlg;

	private TextView mTxtResult;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
		mBtnTimePicDlg = (Button)findViewById(R.id.btnTimePicDlg);
		mBtnDatePicDlg = (Button)findViewById(R.id.btnDatePicDlg);
		mTxtResult = (TextView)findViewById(R.id.txtResult);

		mBtnTimePicDlg.setOnClickListener(btnTimePicDlgOnClkLis);
		mBtnDatePicDlg.setOnClickListener(btnDatePicDlgOnClkLis);
    }
	
    private Button.OnClickListener btnDatePicDlgOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			mTxtResult.setText("");

			Calendar now = Calendar.getInstance();

			DatePickerDialog datePicDlg = new DatePickerDialog(Main.this,
							datePicDlgOnDateSelLis,
							now.get(Calendar.YEAR),
							now.get(Calendar.MONTH),
							now.get(Calendar.DAY_OF_MONTH));
			datePicDlg.setTitle("選擇日期");
			datePicDlg.setMessage("請選擇適合您的日期");
			datePicDlg.setIcon(android.R.drawable.ic_dialog_info);
			datePicDlg.setCancelable(false);
			datePicDlg.show();
		}
	};
	
	private DatePickerDialog.OnDateSetListener datePicDlgOnDateSelLis = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mTxtResult.setText("你設定的日期是" +
							Integer.toString(year) + "年" +
							Integer.toString(monthOfYear + 1) + "月" +
							Integer.toString(dayOfMonth) + "日");
		}
	};

    private Button.OnClickListener btnTimePicDlgOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			mTxtResult.setText("");

			Calendar now = Calendar.getInstance();

			TimePickerDialog timePicDlg = new TimePickerDialog(Main.this,
							timePicDlgOnTimeSelLis,
							now.get(Calendar.HOUR_OF_DAY),
							now.get(Calendar.MINUTE),
							true);
			timePicDlg.setTitle("選擇時間");
			timePicDlg.setMessage("請選擇適合您的時間");
			timePicDlg.setIcon(android.R.drawable.ic_dialog_info);
			timePicDlg.setCancelable(false);
			timePicDlg.show();
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePicDlgOnTimeSelLis = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet (TimePicker  view, int hourOfDay, int minute) {
			mTxtResult.setText("你設定的時間是" +
							Integer.toString(hourOfDay) + "點" +
							Integer.toString(minute) + "分");
		}
	};
}