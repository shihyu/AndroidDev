package tw.android;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Main extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        TabHost tabHost = getTabHost();

        Intent it = new Intent();
        it.setClass(Main.this, DateTimePicker.class);
        TabSpec spec=tabHost.newTabSpec("tab1");
        spec.setContent(it);
        spec.setIndicator("日期和時間",
    			getResources().getDrawable(android.R.drawable.ic_lock_idle_alarm));
        tabHost.addTab(spec);

        it = new Intent();
        it.setClass(Main.this, ProgBarDemo.class);
        spec=tabHost.newTabSpec("tab2");
        spec.setIndicator("ProgressBar",
    			getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        spec.setContent(it);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

    	// 設定tab標籤的字體大小
    	TabWidget tabWidget = (TabWidget)tabHost.findViewById(android.R.id.tabs);
    	View tabView = tabWidget.getChildTabViewAt(0);
    	TextView tab = (TextView)tabView.findViewById(android.R.id.title);
    	tab.setTextSize(20);
    	tabView = tabWidget.getChildTabViewAt(1);
    	tab = (TextView)tabView.findViewById(android.R.id.title);
    	tab.setTextSize(20);
    }
}