package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private static final String TAG = "Bmi";
//	private static final String TAG = Main.class.getSimpleName();
//	public static final String PREF = "BMI_PREF";
//    public static final String PREF_HEIGHT = "BMI_HEIGHT";
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG,"onCreate");
		setContentView(R.layout.main);

		findViews();
//		restorePrefs();
		setListensers();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v(TAG,"onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG,"onPause");
//		SharedPreferences settings = getSharedPreferences(PREF, 0);
//		Editor editor = settings.edit();
//		editor.putString(PREF_HEIGHT, field_height.getText().toString());
//		editor.commit();
		
//        settings.edit()
//            .putString(PREF_HEIGHT, field_height.getText().toString())
//            .commit();
		
		Pref.setHeight(this, field_height.getText().toString());
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v(TAG,"onReStart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG,"onResume");
		restorePrefs();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v(TAG,"onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v(TAG,"onStop");
	}

	private Button button_calc;
	private EditText field_height;
	private EditText field_weight;
	private TextView view_result;
	private TextView view_suggest;

	private void findViews() {
		if(Debug.On) Log.d(TAG, "find Views");
		button_calc = (Button) findViewById(R.id.submit);
	    field_height = (EditText) findViewById(R.id.height);
	    field_weight = (EditText) findViewById(R.id.weight);
	    view_result = (TextView) findViewById(R.id.result);
	    view_suggest = (TextView) findViewById(R.id.suggest);
	}

	// Restore preferences
    private void restorePrefs() {
//        SharedPreferences settings = getSharedPreferences(PREF, 0);
//        String pref_height = settings.getString(PREF_HEIGHT, "");
    	String pref_height = Pref.getHeight(this);
    	if(! "".equals(pref_height)) {
            field_height.setText(pref_height);
            field_weight.requestFocus();
        }
    }
    
	//Listen for button clicks
	private void setListensers() {
		if(Debug.On) Log.d(TAG, "set Listensers");
		button_calc.setOnClickListener(calcBMI);
	}
	
	private Button.OnClickListener calcBMI = new OnClickListener() {
		public void onClick(View v) {
			new BmiCalcTask().execute();
//			Double height = Double
//			.parseDouble(field_height.getText().toString()) / 100;
//			Double weight = Double
//					.parseDouble(field_weight.getText().toString());
			
//			DecimalFormat nf = new DecimalFormat("0.00");
//			try {
//				Double height = Double
//						.parseDouble(field_height.getText().toString()) / 100;
//				Double weight = Double
//						.parseDouble(field_weight.getText().toString());
				
//				double BMI = weight / (height * height);				
				//Present result
//				view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
//	
//				// Give health advice
//				if (BMI > 25) {
//					view_suggest.setText(R.string.advice_heavy);
//				} else if (BMI < 20) {
//					view_suggest.setText(R.string.advice_light);
//				} else {
//					view_suggest.setText(R.string.advice_average);
//				}
				
//				openOptionsDialog();
//			} catch(Exception err) {
//				Log.e(TAG, "error: " + err.toString());
//				Toast toast = Toast.makeText(Main.this, R.string.input_error, Toast.LENGTH_SHORT);
//				toast.show();
//			}
		}
	};
	
	
	private class BmiCalcTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog Dialog = new ProgressDialog(Main.this);
		Double BMI;
		Double height;
		Double weight;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Dialog.setMessage("calc...");
			Dialog.show();
			
			height = Double
			.parseDouble(field_height.getText().toString()) / 100;
			weight = Double
					.parseDouble(field_weight.getText().toString());
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			BMI = weight / (height * height);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			// TODO Auto-generated method stub
			Dialog.dismiss();
			
			DecimalFormat nf = new DecimalFormat("0.00");
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
		
	}
	
	private void openOptionsDialog() {
//		Toast.makeText(Main.this, "BMI 計算器", Toast.LENGTH_SHORT).show();
		
		
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
//			            Uri uri = Uri.parse("http://android.gasolin.idv.tw/");
//			        	Uri uri = Uri.parse("geo:25.047192, 121.516981");
//			        	Uri uri = Uri.parse("tel:12345678");
			        	Uri uri = Uri.parse(getString(R.string.homepage_uri));
			        	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			            startActivity(intent);
			        }
			    })
		    .show();
		
		}
	
	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_QUIT = Menu.FIRST+1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // TODO Auto-generated method stub
//	    menu.add(0, MENU_ABOUT, 0, "關於...");
//	    menu.add(0, MENU_QUIT, 0, "結束");
//		menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(R.drawable.help_browser);
//	    menu.add(0, MENU_QUIT, 0, "結束").setIcon(R.drawable.emblem_unreadable);
		menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(android.R.drawable.ic_menu_help);
        menu.add(0, MENU_QUIT, 0, "結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
	    // TODO Auto-generated method stub
	    switch(item.getItemId()) {
	        case MENU_ABOUT:
//	             openOptionsDialog();
	        	 Intent intent = new Intent(Intent.ACTION_VIEW);
	        	 intent.setClass(Main.this, Pref.class);
	             startActivity(intent);
	             break;
	        case MENU_QUIT:
	             finish();
	             break;
	    }
	    return super.onOptionsItemSelected(item);
	}
}