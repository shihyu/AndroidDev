package com.xzy;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * …Ë÷√
 * @author –§‘Û‘∆
 *
 */
public class SettingActivity extends PreferenceActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
	}
}
