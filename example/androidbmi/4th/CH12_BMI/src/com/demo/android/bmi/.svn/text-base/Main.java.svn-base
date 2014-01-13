package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
		}
	};
}