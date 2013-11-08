package com.demo.android.bmi;

import junit.framework.Assert;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class BmiTests extends ActivityUnitTestCase<Bmi> {

	public BmiTests() {
		super(Bmi.class);
		// TODO Auto-generated constructor stub
	}
	
	private Intent mStartIntent;

	@Override  
    protected void setUp() throws Exception {
		super.setUp();
		mStartIntent = new Intent(Intent.ACTION_MAIN);
	}
	
    @MediumTest
    public void testPreconditions() { 
        startActivity(mStartIntent, null, null);  

        Assert.assertNotNull(getActivity());
    }
}
