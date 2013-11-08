package com.demo.android.bmi.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.demo.android.bmi.Main;

public class MainTest extends ActivityInstrumentationTestCase2<Main> {

  private Instrumentation mInstrumentation;
  private Main mActivity;
  private EditText mHeightInput;
  private Button mSubmitBtn;
  private String resourceString;
    
  public MainTest(String name) {
	super(Main.class);
	setName(name);
  }

  protected void setUp() throws Exception {
    super.setUp();
    mActivity = getActivity();
    //for testText
	mSubmitBtn = (Button) mActivity.findViewById(com.demo.android.bmi.R.id.submit);
	resourceString = mActivity.getString(com.demo.android.bmi.R.string.bmi_btn);
    
	//for testInput
	setActivityInitialTouchMode(false);
	mInstrumentation = getInstrumentation();
	mHeightInput = (EditText) mActivity.findViewById(com.demo.android.bmi.R.id.height);
  }

  public void testPreconditions() {
	assertNotNull(mSubmitBtn);
	assertNotNull(mHeightInput);
  }
  
  public void testText() {
//	assertTrue(1+1==2);
	assertEquals(resourceString,(String)mSubmitBtn.getText());
  }
  
  public void testInput() {
	mActivity.runOnUiThread(
	  new Runnable() {
		public void run() {
		  mHeightInput.setText("");
		  mHeightInput.requestFocus();
	  }	
	});
	mInstrumentation.waitForIdleSync();
	sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_0);
	assertEquals(mHeightInput.getText().toString(), "170");
  }
  
  public void testStateDestroy() {
    mActivity.runOnUiThread(
	  new Runnable() {
		public void run() {
		  mHeightInput.setText("");
		  mHeightInput.requestFocus();
		}	
	  });
	mInstrumentation.waitForIdleSync();
	sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_8, KeyEvent.KEYCODE_6);
	
	mActivity.finish();
	mActivity = this.getActivity();
		
	assertEquals(mHeightInput.getText().toString(), "186");
  }
  
}