package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
	    setListensers();
	}

	private Button button_calc;
	private EditText num_height;
	private EditText num_weight;
	private TextView show_result;
	private TextView show_suggest;
	
	private void initViews() {
	  button_calc = (Button) findViewById(R.id.submit);
	  num_height = (EditText) findViewById(R.id.height);
	  num_weight = (EditText) findViewById(R.id.weight);
	  show_result = (TextView) findViewById(R.id.result);
	  show_suggest = (TextView) findViewById(R.id.suggest);
	}

	// Listen for button clicks
	private void setListensers() {
	    button_calc.setOnClickListener(calcBMI);
    }
	
	private Button.OnClickListener calcBMI = new Button.OnClickListener() { 
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            double height = Double.parseDouble(num_height.getText().toString()) / 100;
            double weight = Double.parseDouble(num_weight.getText().toString());
            double BMI = weight / (height * height);

            // Present result
            show_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // Give health advice
            if (BMI > 25) {
            	show_suggest.setText(R.string.advice_heavy);
            } else if (BMI < 20) {
            	show_suggest.setText(R.string.advice_light);
            } else {
            	show_suggest.setText(R.string.advice_average);
            }
        }
    };
    
//    protected static final int MENU_SETTINGS = Menu.FIRST;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
