package tw.android;

import java.util.Calendar;

import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;

public class AppWidgetConfigAct extends Activity {

	int mAppWidgetId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent itIn = getIntent();
		Bundle extras = itIn.getExtras();
		if (extras != null) {
		     mAppWidgetId = extras.getInt(
		             AppWidgetManager.EXTRA_APPWIDGET_ID,
		             AppWidgetManager.INVALID_APPWIDGET_ID);
		}

	    if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
	    	    finish();
		}
		 
		Intent itOut = new Intent("tw.android.MY_OWN_WIDGET_UPDATE");
		PendingIntent penIt = PendingIntent.getBroadcast(this, 0, itOut, 0);
		AlarmManager alarmMan = (AlarmManager)getSystemService(ALARM_SERVICE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 5);
		alarmMan.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 20*1000, penIt);
		
		MyAppWidget.SaveAlarmManager(alarmMan, penIt);

		Intent itAppWidgetConfigResult = new Intent();
		itAppWidgetConfigResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, itAppWidgetConfigResult);
		
		finish();
	}
}
