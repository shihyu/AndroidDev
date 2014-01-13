package com.demo.android.bmi;

import java.net.URISyntaxException;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setListeners();
    }

    private Button calcbutton;
    private EditText fieldheight;
    private EditText fieldweight;
    private TextView view_result;
    private TextView view_suggest;
    
    private void findViews(){
    	calcbutton = (Button)findViewById(R.id.submit);
    	fieldheight = (EditText)findViewById(R.id.height);
        fieldweight = (EditText)findViewById(R.id.weight);
        view_result = (TextView)findViewById(R.id.result);
        view_suggest = (TextView)findViewById(R.id.suggest);
    }

    //Listen for button clicks
    private void setListeners(){
    	calcbutton.setOnClickListener(calcBMI);
    }
    
    private Button.OnClickListener calcBMI = new Button.OnClickListener() { 
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            try {
            double height = Double.parseDouble(fieldheight.getText().toString())/100;
            double weight = Double.parseDouble(fieldweight.getText().toString());
            double BMI = weight / (height * height);
            //Present result
            view_result.setText(getText(R.string.bmi_result)+nf.format(BMI));

            //Give health advice
            view_suggest = (TextView)findViewById(R.id.suggest);
            if(BMI>25) {
            	view_suggest.setText(R.string.advice_heavy);
            } else if(BMI<20) {
            	view_suggest.setText(R.string.advice_light);
            } else {
            	view_suggest.setText(R.string.advice_average);
            }
            openOptionsDialog();
            }catch(Exception obj) {
            	  Toast.makeText(Bmi.this, R.string.input_error, Toast.LENGTH_SHORT).show();
            }
        }
    };
    
//    public void openOptionsDialog(View target){
//    	new AlertDialog.Builder(Bmi.this)
//        .setTitle("關於 Android BMI")
//        .setMessage("Android BMI Calc")
//        .show();
//    }
    
    private void openOptionsDialog() {
//    	Toast.makeText(Bmi.this, "BMI 計算器", Toast.LENGTH_SHORT).show();
    	
        new AlertDialog.Builder(Bmi.this)
        .setTitle(R.string.about_title)
        .setMessage(R.string.about_msg)
        .setPositiveButton("確認",
        new DialogInterface.OnClickListener(){
            public void onClick(
                DialogInterface dialoginterface, int i){
            }
        })
       .setNegativeButton(R.string.homepage_label,
    	new DialogInterface.OnClickListener(){
        public void onClick(
          DialogInterface dialoginterface, int i){
        	//Home Page
            Uri uri = Uri.parse("http://sites.google.com/site/gasodroid/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        	//MAP
//        	Uri uri = Uri.parse("geo:25.047192, 121.516981");
//        	final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        	//DIAL
//            Uri uri = Uri.parse("tel:12345678");  
//            Intent intent = new Intent(Intent.ACTION_DIAL, uri);  
            startActivity(intent);
        }
        })
        .show();
    }
    
}