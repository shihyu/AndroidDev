package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Report extends Activity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        findViews();
        showResults();
        setListensers();
    }
	
	private Button button_back;
    private TextView view_result;
    private TextView view_suggest;
    
    private void findViews() {
        button_back = (Button) findViewById(R.id.report_back);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
    }

    //Listen for button clicks
    private void setListensers() {
        button_back.setOnClickListener(backMain);
    }
    
    private Button.OnClickListener backMain = new Button.OnClickListener() {
        public void onClick(View v) {
            // Close this Activity
            Report.this.finish();
        }
    };
    
    private void showResults() {
        DecimalFormat nf = new DecimalFormat("0.00");

        Bundle bunde = this.getIntent().getExtras();
        double height = Double.parseDouble(bunde.getString("KEY_HEIGHT"))/100;
        double weight = Double.parseDouble(bunde.getString("KEY_WEIGHT"));
        double BMI = weight / (height * height);
        view_result.setText(getString(R.string.bmi_result) +nf.format(BMI));

        //Give health advice
        if(BMI>25) {
            view_suggest.setText(R.string.advice_heavy);
        } else if(BMI<20) {
            view_suggest.setText(R.string.advice_light);
        } else {
            view_suggest.setText(R.string.advice_average);
        }

    }
}
