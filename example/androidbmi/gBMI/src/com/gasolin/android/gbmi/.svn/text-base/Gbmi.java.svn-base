package com.gasolin.android.gbmi;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gbmi extends Activity {
	public static final String PREF = "BMI_PREF";
	public static final String PREF_HEIGHT = "PREF_HEIGHT";
	public static final String PREF_WEIGHT = "PREF_WEIGHT";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = Locale.TRADITIONAL_CHINESE;
//        res.updateConfiguration(conf, dm);
        
        setContentView(R.layout.main);
        findViews();
        setListensers();

        restorePrefs(savedInstanceState);
    }
    
    private Button button_calc;
    private EditText field_height;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    private void findViews()
    {
    	button_calc = (Button) findViewById(R.id.submit);
    	field_height = (EditText) findViewById(R.id.height);
    	field_weight = (EditText) findViewById(R.id.weight);
    	view_result = (TextView) findViewById(R.id.result);
    	view_suggest = (TextView) findViewById(R.id.suggest);
    }

    // Restore preferences
    private void restorePrefs(Bundle savedInstanceState)
    {
    	SharedPreferences settings = getSharedPreferences(PREF, 0);
    	String pref_height = settings.getString(PREF_HEIGHT, "");
    	String pref_weight="";
//    	if (savedInstanceState != null){
//    		String pref_height = savedInstanceState.getString(PREF_HEIGHT);
//    		String pref_weight = savedInstanceState.getString(PREF_WEIGHT);
//    		
    	//support intent
    	Bundle bundle = this.getIntent().getExtras();
    	if (bundle!=null){
    		String temp = bundle.getString(PREF_HEIGHT);
    		if (temp!=null)
    			pref_height = bundle.getString(PREF_HEIGHT);
    		temp = bundle.getString(PREF_WEIGHT);
    		if (temp!=null)
    			pref_weight = bundle.getString(PREF_WEIGHT);
	    	Log.e("OOOO", "height "+pref_weight+" weight "+pref_weight);
    	}
    	
    	
    		if(!pref_height.equals(""))
	    	{
	    		field_height.setText(pref_height);
	    		field_weight.requestFocus();
	    	}
    		if(pref_weight!=null)
	    	{
	    		field_weight.setText(pref_weight);
	    	}
//    	}
    }
    
    //Listen for button clicks
    private void setListensers() {
    	button_calc.setOnClickListener(calcBMI);
    }
    
    private Button.OnClickListener calcBMI = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.00");
            try{
	            double height = Double.parseDouble(field_height.getText().toString())/100;
	            double weight = Double.parseDouble(field_weight.getText().toString());
	            double BMI = weight / (height * height);
	            //Present result 
//	            view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
	            view_result.setText(nf.format(BMI));
	 
	            //Give health advice 
	            if(BMI>27){	            	
//	                NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//	                
//	                Notification barmsg = new Notification(android.R.drawable.ic_notification_overlay, 
//	                		"fat ass",
//	                		System.currentTimeMillis()
//	                		);
	                //barmsg.tickerText = "fat ass";
	                /*
	                barmsg.defaults = Notification.DEFAULT_ALL;
	                */
//	                Uri uri = Uri.parse("http://123.45");
//					Intent intents = new Intent(Intent.ACTION_VIEW, uri);
//					PendingIntent fatintent = PendingIntent.getActivity(Gbmi.this, 0, intents, 0);
//					
//	                barmsg.setLatestEventInfo(Gbmi.this, "¦º­D¤l", "¸Ó´îªÎ°Õ", fatintent);
//	                
//	                barManager.notify(0, barmsg);
	                
	            	view_suggest.setText(R.string.advice_fat);
	            	view_result.setTextColor(Color.RED);
	            }else if(BMI>25){
	                view_suggest.setText(R.string.advice_heavy);
	                view_result.setTextColor(Color.YELLOW);
	            }else if(BMI<20){
	                view_suggest.setText(R.string.advice_light);
	                view_result.setTextColor(Color.YELLOW);
	            }else{
	                view_suggest.setText(R.string.advice_average);
	                view_result.setTextColor(Color.GREEN);
	            }
	            
	            //Support PICK
	            String action = getIntent().getAction();
	            if(Intent.ACTION_GET_CONTENT.equals(action)){
	            	Intent intent = new Intent();
	            	Bundle bundle = new Bundle();
	            	bundle.putString("BMI", nf.format(BMI));
	            	bundle.putString("WEIGHT", nf.format(weight));
	            	intent.putExtras(bundle);
	            	setResult(RESULT_OK, intent);
	                finish();
	            }
            }
            catch(Exception err)
            {
            	Toast.makeText(Gbmi.this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };
    
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_SWITCH = Menu.FIRST+1;
    protected static final int MENU_BMR = Menu.FIRST+2;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_SWITCH, 0, R.string.switch_label).setIcon(android.R.drawable.ic_menu_set_as);
		menu.add(0, MENU_BMR, 0, R.string.bmr_label).setIcon(android.R.drawable.ic_menu_view);
		menu.add(0, MENU_ABOUT, 0, R.string.about_label).setIcon(android.R.drawable.ic_menu_info_details);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
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
		return true;
	}

	private void openOptionsDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.about_title)//.setView(view)
		.setMessage(R.string.about_msg)
		.setPositiveButton(R.string.ok_label,
				new DialogInterface.OnClickListener(){
					public void onClick(
							DialogInterface dialoginterface, int i){
					}
				})
		.show();
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		// TODO Auto-generated method stub
		SharedPreferences settings = getSharedPreferences(PREF, 0);
		settings.edit()
			.putString(PREF_HEIGHT, field_height.getText().toString())
			.commit();
	}

	private void openBMI() {
//        Log.d(TAG, "open Dialog"); 
        Intent intent_bmi = new Intent();
        intent_bmi.setClassName("com.gasolin.android.abmi", "com.gasolin.android.abmi.Abmi");
        intent_bmi.setAction(Intent.ACTION_MAIN);
        intent_bmi.addCategory(Intent.CATEGORY_LAUNCHER);
        try{
    		startActivityForResult(intent_bmi,0);
    	}catch(ActivityNotFoundException e)
    	{
	        new AlertDialog.Builder(this)
	            .setTitle("Haven't install inch/lb calculator.")
	            .setMessage("Want to install BMI Calculator for British system(aBMI) from Android Market?")
	            .setPositiveButton(R.string.yes_label,
	                new DialogInterface.OnClickListener(){
		            	public void onClick(
								DialogInterface dialoginterface, int i){
			        	    Uri uri = Uri.parse("market://search?q=pname:com.gasolin.android.abmi");
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
	      intent_bmi.setClassName("com.gasolin.android.gbmr", "com.gasolin.android.gbmr.Gbmr");
	      
	      Bundle bundle = new Bundle();
	      bundle.putString(PREF_HEIGHT, field_height.getText().toString());
	      bundle.putString(PREF_WEIGHT, field_weight.getText().toString());
	      intent_bmi.putExtras(bundle);
//	      intent_bmi.putExtra(PREF_HEIGHT, field_height.getText().toString());
//	      intent_bmi.putExtra(PREF_WEIGHT, field_weight.getText().toString());
//	      intent_bmi.setAction(Intent.ACTION_MAIN);
//	      intent_bmi.addCategory(Intent.CATEGORY_LAUNCHER);
	      try{
	  		startActivityForResult(intent_bmi,0);
	  	}catch(ActivityNotFoundException e)
	  	{
		        new AlertDialog.Builder(this)
		            .setTitle("Haven't install BMR calculator.")
		            .setMessage("Want to install BMR Calculator for metric system(gBMR) from Android Market?")
		            .setPositiveButton(R.string.yes_label,
		                new DialogInterface.OnClickListener(){
			            	public void onClick(
									DialogInterface dialoginterface, int i){
				        	    Uri uri = Uri.parse("market://search?q=pname:com.gasolin.android.gbmr");
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