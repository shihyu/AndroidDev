package tw.android;

import java.util.Calendar;

import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

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

		// 更新App Widget的畫面必須執行以下步驟:
		// 1. 取得 AppWidgetManager
		//    AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
		// 2. 呼叫在MyAppWidgetProvider中的自訂方法
		//    MyAppWidgetProvider.updateAppWidget(AppWidgetConfigAct.this, appWidgetMan, mAppWidgetId);
/*		RemoteViews viewAppWidget = new RemoteViews(getPackageName(), R.layout.main);
		viewAppWidget.setImageViewResource(R.id.imgAppWidget, R.drawable.icon2);
		AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
		appWidgetMan.updateAppWidget(mAppWidgetId, viewAppWidget);
*/
		Intent itAppWidgetConfigResult = new Intent();
		itAppWidgetConfigResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, itAppWidgetConfigResult);

		finish();
	}
}
