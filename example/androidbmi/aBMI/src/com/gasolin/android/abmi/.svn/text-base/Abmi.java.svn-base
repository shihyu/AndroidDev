package com.gasolin.android.abmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//aBMI is for British system, gBMI is for metric system
public class Abmi extends Activity {
    private static final String TAG = "aBmi";
    public static final String PREF = "BMI_PREF";
    public static final String PREF_FEET = "BMI_Feet";
    public static final String PREF_INCH = "BMI_Inch";
    public static final String PREF_HEIGHT = "PREF_HEIGHT";
	public static final String PREF_WEIGHT = "PREF_WEIGHT";

    /*
    static final String[] feets= new String[] {
        "2 Feet",
        "3 Feet",
        "4 Feet",
        "5 Feet",
        "6 Feet",
        "7 Feet",
        "8 Feet"
    };
    */

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //force locale
        /*
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = Locale.TRADITIONAL_CHINESE;
        DisplayMetrics dm = res.getDisplayMetrics();
        res.updateConfiguration(conf, dm);
        */

        setContentView(R.layout.main);
        findViews();
        setListensers();
        restorePrefs();
        /*
        ArrayAdapter<String> adapter_feet = new ArrayAdapter<String>(
        		this,
        	    android.R.layout.simple_spinner_item,
        	    feets);
        adapter_feet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        */
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
/*
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		field_weight.setText(savedInstanceState.getString("WEIGHT")+"1");   
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("WEIGHT", field_weight.getText().toString());
	}
*/

	// Restore preferences
    private void restorePrefs() {
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        //set field
        Integer pref_feet = settings.getInt(PREF_FEET, 5);
        field_feet.setSelection(pref_feet);
        field_inch.requestFocus();
        
        Integer pref_inch = settings.getInt(PREF_INCH, 0);
        field_inch.setSelection(pref_inch);
        field_weight.requestFocus();
    }

    
    @Override
	protected void onPause() {  
		// TODO Auto-generated method stub
		super.onPause();
		// Save user preferences. 
        SharedPreferences settings = getSharedPreferences(PREF, 3);
        settings.edit()
        .putInt(PREF_FEET, field_feet.getSelectedItemPosition())
        .putInt(PREF_INCH, field_inch.getSelectedItemPosition())
        .commit();
	}


	private Button button_calc;
    //private EditText field_feet;
    //private EditText field_inch;
    private Spinner field_feet;
    private Spinner field_inch;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    private void findViews() {
        Log.d(TAG, "find Views");
        button_calc = (Button) findViewById(R.id.submit);
        //field_feet = (EditText) findViewById(R.id.feet);
        //field_inch = (EditText) findViewById(R.id.inch);
        field_feet = (Spinner) findViewById(R.id.feet);
        field_inch = (Spinner) findViewById(R.id.inch);
        field_weight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        
        ArrayAdapter<CharSequence> adapter_feet = ArrayAdapter.createFromResource(
                this, R.array.feets, android.R.layout.simple_spinner_item);
        adapter_feet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        field_feet.setAdapter(adapter_feet);
        
        ArrayAdapter<CharSequence> adapter_inch = ArrayAdapter.createFromResource(
                this, R.array.inches, android.R.layout.simple_spinner_item);
        adapter_inch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        field_inch.setAdapter(adapter_inch);
        
    }

    //Listen for button clicks
    private void setListensers() {
        Log.d(TAG, "set Listensers");
        field_feet.setOnItemSelectedListener(getFeet);
        field_inch.setOnItemSelectedListener(getInch);
        button_calc.setOnClickListener(calcUsBMI);
    }

    private int feet;
    private int inch;

    private Spinner.OnItemSelectedListener getFeet = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
                feet = parent.getSelectedItemPosition()+2;
        }
        public void onNothingSelected(AdapterView parent) {
        }
    };

    private Spinner.OnItemSelectedListener getInch = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
                inch = parent.getSelectedItemPosition()+1;
        }
        public void onNothingSelected(AdapterView parent) {

        }
    };

    private Button.OnClickListener calcUsBMI = new Button.OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            try {
            	/*
            	double height = (Double.parseDouble(field_feet.getText().toString())*12+Double.parseDouble(field_inch.getText().toString()))*2.54/100;
            	*/
                double height = (feet*12+inch)*2.54/100;
                double weight = Double.parseDouble(field_weight.getText().toString())*0.45359;
                double BMI = weight / (height * height);
                //Present result
//                view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
                view_result.setText(nf.format(BMI));

                //Give health advice
                if(BMI > 27) {
                    view_suggest.setText(R.string.advice_fat);
                    view_result.setTextColor(Color.RED);
                } else if(BMI > 25) {
                     view_suggest.setText(R.string.advice_heavy);
                     view_result.setTextColor(Color.YELLOW);
                } else if(BMI < 20) {
                     view_suggest.setText(R.string.advice_light);
                     view_result.setTextColor(Color.YELLOW);
                } else {
                     view_suggest.setText(R.string.advice_average);
                     view_result.setTextColor(Color.GREEN);
                }
            } catch(Exception obj) {
                Toast.makeText(Abmi.this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_SWITCH = Menu.FIRST+1;
    protected static final int MENU_BMR = Menu.FIRST+2;
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
    	Log.d(TAG, "open Menu");
    	menu.add(0, MENU_SWITCH, 0, R.string.switch_label).setIcon(android.R.drawable.ic_menu_set_as);
    	menu.add(0, MENU_BMR, 0, R.string.bmr_label).setIcon(android.R.drawable.ic_menu_view);
    	menu.add(0, MENU_ABOUT, 0, R.string.about_label).setIcon(android.R.drawable.ic_menu_info_details);
        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.d(TAG, "select Menu Item");
        switch(item.getItemId()) {
            case MENU_ABOUT:
                openOptionsDialog();
                break;
            case MENU_SWITCH:
                openBMI();
                break;
            case MENU_BMR:
                openBMR();
                break;
        }
		return super.onOptionsItemSelected(item);
	}

	private void openOptionsDialog() {
        Log.d(TAG, "open Dialog");
        new AlertDialog.Builder(this)
            .setTitle(R.string.about_title)
            .setMessage(R.string.about_msg)
            .setPositiveButton(R.string.ok_label,
                new DialogInterface.OnClickListener(){
                    public void onClick(
                        DialogInterface dialoginterface, int i){
                        }
             })
             .show();
    }

	private void openBMI() {
        Log.d(TAG, "open Dialog");
        Intent intent_bmi = new Intent();
        intent_bmi.setClassName("com.gasolin.android.gbmi", "com.gasolin.android.gbmi.Gbmi");
        intent_bmi.setAction(Intent.ACTION_MAIN);
//        intent_bmi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent_bmi.addCategory(Intent.CATEGORY_LAUNCHER);
        try{
    		startActivityForResult(intent_bmi,0);
    	}catch(ActivityNotFoundException e)
    	{
	        new AlertDialog.Builder(this)
	            .setTitle("Haven't install cm/kg calculator.")
	            .setMessage("Want to install BMI Calculator for Metric system(gBMI) from Android Market?")
	            .setPositiveButton(R.string.yes_label,
	                new DialogInterface.OnClickListener(){
		            	public void onClick(
								DialogInterface dialoginterface, int i){
			        	    Uri uri = Uri.parse("market://search?q=pname:com.gasolin.android.gbmi");
			        	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			        	    startActivity(intent);
						}
	             })
	             .setNegativeButton(R.string.no_label, 
                    				new DialogInterface.OnClickListener(){
            							public void onClick(
            							DialogInterface dialoginterface, int i){
            					}
	             })
	             .show();
    	}
    }
	
	private void openBMR() {
	      Intent intent_bmi = new Intent();
	      intent_bmi.setClassName("com.gasolin.android.abmr", "com.gasolin.android.abmr.Abmr");
//	      intent_bmi.putExtra(PREF_HEIGHT, field_height.getText().toString());
//	      intent_bmi.putExtra(PREF_WEIGHT, field_weight.getText().toString());
	      intent_bmi.setAction(Intent.ACTION_MAIN);
	      intent_bmi.addCategory(Intent.CATEGORY_LAUNCHER);
	      try{
	  		startActivityForResult(intent_bmi,0);
	  	}catch(ActivityNotFoundException e)
	  	{
		        new AlertDialog.Builder(this)
		            .setTitle("Haven't install BMR calculator.")
		            .setMessage("Want to install BMR Calculator for british system(aBMR) from Android Market?")
		            .setPositiveButton(R.string.yes_label,
		                new DialogInterface.OnClickListener(){
			            	public void onClick(
									DialogInterface dialoginterface, int i){
				        	    Uri uri = Uri.parse("market://search?q=pname:com.gasolin.android.abmr");
				        	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				        	    startActivity(intent);
							}
		             })
		             .setNegativeButton(R.string.no_label, 
	                  				new DialogInterface.OnClickListener(){
	          							public void onClick(
	          							DialogInterface dialoginterface, int i){
	          					}
		             })
		             .show();
	  	}
	  }
}