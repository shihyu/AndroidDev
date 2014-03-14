package tw.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

public class Main extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);    
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menuItemPlayBackgroundMusic:
			Intent it = new Intent(Main.this, MediaPlayService.class);
    		startService(it);
			break;
		case R.id.menuItemStopBackgroundMusic:
			it = new Intent(Main.this, MediaPlayService.class);
    		stopService(it);
			break;
		case R.id.menuItemAbout:
			new AlertDialog.Builder(Main.this)
				.setTitle("關於這個程式")
				.setMessage("選單範例程式")
				.setCancelable(false)
				.setIcon(android.R.drawable.star_big_on)
				.setPositiveButton("確定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub							
						}
					})
				.show();
			
			break;
		case R.id.menuItemExit:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}