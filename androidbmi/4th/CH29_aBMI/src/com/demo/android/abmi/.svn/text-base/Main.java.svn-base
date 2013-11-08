package com.demo.android.abmi;

import java.text.DecimalFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private static final String TAG = Main.class.getSimpleName();
	
	static final String[] feets= new String[] {
	    "2 Feet",
	    "3 Feet",
	    "4 Feet",
	    "5 Feet",
	    "6 Feet",
	    "7 Feet",
	    "8 Feet"
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = Locale.TRADITIONAL_CHINESE;
        DisplayMetrics dm = res.getDisplayMetrics();
        res.updateConfiguration(conf, dm);
        
        setContentView(R.layout.main);
        findViews();
        setListensers();
    }
    
    private Button button_calc;
    private EditText field_feet;
    private EditText field_inch;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    private void findViews() {
        Log.d(TAG, "find Views");
        button_calc = (Button) findViewById(R.id.submit);
        field_feet = (EditText) findViewById(R.id.feet);
        field_inch = (EditText) findViewById(R.id.inch);
        field_weight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        
//        ArrayAdapter<String> adapter_feet = new ArrayAdapter<String>(this,
//        	    android.R.layout.simple_spinner_item,
//        	    feets);
//        adapter_feet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter_feet = ArrayAdapter.createFromResource(
                this, R.array.feets, 
                android.R.layout.simple_spinner_item);
        adapter_feet.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter_inch = ArrayAdapter.createFromResource(
                this, R.array.inches, 
                android.R.layout.simple_spinner_item);
        adapter_inch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    //Listen for button clicks
    private void setListensers() {
        Log.d(TAG, "set Listensers");
        button_calc.setOnClickListener(calcUsBMI);
    }
    
    private Button.OnClickListener calcUsBMI = new Button.OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            try {
                double height = (Double.parseDouble(field_feet.getText().toString())*12+Double.parseDouble(field_inch.getText().toString()))*2.54/100;
                double weight = Double.parseDouble(field_weight.getText().toString())*0.45359;
                double BMI = weight / (height * height);
                //Present result
                 view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

                //Give health advice
                if(BMI > 27) {
                    view_suggest.setText(R.string.advice_fat);
                } else if(BMI > 25) {
                     view_suggest.setText(R.string.advice_heavy);
                } else if(BMI < 20) {
                     view_suggest.setText(R.string.advice_light);
                } else {
                     view_suggest.setText(R.string.advice_average);
                }
            } catch(Exception obj) {
                Toast.makeText(Main.this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };
    
    protected static final int MENU_ABOUT = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.d(TAG, "open Menu");
        menu.add(0, MENU_ABOUT, 0, R.string.about_label);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Log.d(TAG, "select Menu Item");
        switch(item.getItemId()) {
            case MENU_ABOUT:
                openOptionsDialog();
                break;
        }
        return true;
    }

    private void openOptionsDialog() {
        Log.d(TAG, "open Dialog");
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
}