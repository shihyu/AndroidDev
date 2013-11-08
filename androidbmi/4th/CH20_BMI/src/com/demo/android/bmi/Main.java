package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
//	private static final String TAG = "Bmi";
	private static final String TAG = Main.class.getSimpleName();
	
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
		if(Debug.On) Log.d(TAG, "find Views");
		button_calc = (Button) findViewById(R.id.submit);
	    field_height = (EditText) findViewById(R.id.height);
	    field_weight = (EditText) findViewById(R.id.weight);
	    view_result = (TextView) findViewById(R.id.result);
	    view_suggest = (TextView) findViewById(R.id.suggest);
	}

	//Listen for button clicks
	private void setListensers() {
		if(Debug.On) Log.d(TAG, "set Listensers");
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
				
//				openOptionsDialog();
			} catch(Exception err) {
				Log.e(TAG, "error: " + err.toString());
				Toast toast = Toast.makeText(Main.this, R.string.input_error, Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	};
	
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
	             openOptionsDialog();
	             break;
	        case MENU_QUIT:
	             finish();
	             break;
	    }
	    return super.onOptionsItemSelected(item);
	}
}