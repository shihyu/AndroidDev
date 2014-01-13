package com.demo.android.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends Activity {
	public static final String PREF = "BMI_PREF";
	public static final String PREF_HEIGHT = "BMI_Height";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        restorePrefs();
        setListensers();
    }
    
    private Button button_calc;
    private EditText field_height;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;
    
    private void findViews() {
    	button_calc = (Button)findViewById(R.id.submit);
    	field_height = (EditText)findViewById(R.id.height);
    	field_weight = (EditText)findViewById(R.id.weight);
    	view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        //not pop virtual input keyboard
        //field_height.setInputType(InputType.TYPE_NULL); 
    }
    
    //Listen for button clicks
    private void setListensers() {
    	button_calc.setOnClickListener(calcBMI);
    }

    private OnClickListener calcBMI = new OnClickListener() {
        public void onClick(View v) {
        	/*
            DecimalFormat nf = new DecimalFormat("0.00");
            try {    
	            double height = Double.parseDouble(field_height.getText().toString())/100;
	            double weight = Double.parseDouble(field_weight.getText().toString());
	            double BMI = weight / (height * height);
	
	            //Present result
	            view_result.setText(getString(R.string.bmi_result) +nf.format(BMI));
	
	            //Give health advice
	            view_suggest = (TextView)findViewById(R.id.suggest);
	            if(BMI>25) {
	            	view_suggest.setText(R.string.advice_heavy);
	            }else if(BMI<20) {
	            	view_suggest.setText(R.string.advice_light);
	            }else {
	            	view_suggest.setText(R.string.advice_average);
	            }
	            openOptionsDialog();
        	} catch(Exception obj) {
        		Toast.makeText(Bmi.this, R.string.input_error, Toast.LENGTH_SHORT).show();
        		Uri uri = Uri.parse(getString(R.string.homepage_uri));
        	}
        	*/
                //Switch to report page
                Intent intent = new Intent();
                intent.setClass(Bmi.this, Report.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEY_HEIGHT", field_height.getText().toString());
                bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
        }
        
        private void openOptionsDialog() {
        	Toast.makeText(Bmi.this, "BMI 計算器", Toast.LENGTH_SHORT).show();
            /*
            new AlertDialog.Builder(Bmi.this)
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

    };

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	    //menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(R.drawable.help_browser);
	    //menu.add(0, MENU_Quit, 0, "結束").setIcon(R.drawable.emblem_unreadable);
		menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, MENU_Quit, 0, "結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	private void openOptionsDialog() {
	    new AlertDialog.Builder(Bmi.this)
	    .setTitle("關於 Android BMI")
	    .setMessage("Android BMI Calc")
	    .setPositiveButton(R.string.ok_label,
	        new DialogInterface.OnClickListener(){
	    	public void onClick(
	    	    DialogInterface dialoginterface, int i){
            }
        })
        .setNegativeButton(R.string.homepage_label,
            new DialogInterface.OnClickListener(){
            public void onClick(
                DialogInterface dialoginterface, int i){
                //go to url
                Uri uri = Uri.parse("http://sites.google.com/site/gasodroid/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
    	})
	    .show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
        case MENU_ABOUT:
             openOptionsDialog();
             break;
        case MENU_Quit:
             finish();
             break;
		}
		return super.onOptionsItemSelected(item);
	}

	// Restore preferences
    private void restorePrefs() {
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        String pref_height = settings.getString(PREF_HEIGHT, "");
        if(! "".equals(pref_height)) {
            field_height.setText(pref_height);
            field_weight.requestFocus();
        }
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// Save user preferences. use Editor object to make changes.
        SharedPreferences settings = getSharedPreferences(PREF, 0);
            settings.edit()
                .putString(PREF_HEIGHT, field_height.getText().toString())
                .commit();
	}

    
}