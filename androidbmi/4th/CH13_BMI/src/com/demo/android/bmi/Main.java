package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViews();
		setListensers();
	}
	
	private Button button_calc;
	private EditText field_height;
	private EditText field_weight;
	private TextView view_result;
	private TextView view_suggest;

	private void findViews() {
		button_calc = (Button) findViewById(R.id.submit);
	    field_height = (EditText) findViewById(R.id.height);
	    field_weight = (EditText) findViewById(R.id.weight);
	    view_result = (TextView) findViewById(R.id.result);
	    view_suggest = (TextView) findViewById(R.id.suggest);
	}

	//Listen for button clicks
	private void setListensers() {
		button_calc.setOnClickListener(calcBMI);
	}
	
	private Button.OnClickListener calcBMI = new OnClickListener() {
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");
			try {
				double height = Double
						.parseDouble(field_height.getText().toString()) / 100;
				double weight = Double
						.parseDouble(field_weight.getText().toString());
				double BMI = weight / (height * height);
	
				//Present result
				view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
	
				// Give health advice
				if (BMI > 25) {
					view_suggest.setText(R.string.advice_heavy);
				} else if (BMI < 20) {
					view_suggest.setText(R.string.advice_light);
				} else {
					view_suggest.setText(R.string.advice_average);
				}
				
				openOptionsDialog();
			} catch(Exception obj) {
				Toast toast = Toast.makeText(Main.this, R.string.input_error, Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};
	
	private void openOptionsDialog() {
		Toast.makeText(Main.this, "BMI 計算器", Toast.LENGTH_SHORT).show();
		
		/*
//		private void openOptionsDialog() {
//			AlertDialog.Builder builder = new  AlertDialog.Builder(Main.this);
//			    builder.setTitle("關於 Android BMI");
//			    builder.setMessage("Android BMI Calc");
//			    builder.show();
//			}
		    new AlertDialog.Builder(Main.this)
//		    .setTitle("關於 Android BMI")
//		    .setMessage("Android BMI Calc")
		    .setTitle(R.string.about_title)
	        .setMessage(R.string.about_msg)
	        .setPositiveButton("確認",
			    new DialogInterface.OnClickListener(){
			        public void onClick(
			            DialogInterface dialoginterface, int i){
			        }
			    })
		    .show();
		*/
		}
}