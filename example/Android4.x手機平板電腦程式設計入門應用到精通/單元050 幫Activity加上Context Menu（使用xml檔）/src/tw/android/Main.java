package tw.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends Activity {

	 LinearLayout mLinLayout;
	 TextView mTxtView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mLinLayout = (LinearLayout) findViewById(R.id.linLayout);
        registerForContextMenu(mLinLayout);
        mTxtView = (TextView)findViewById(R.id.txtView);
        registerForContextMenu(mTxtView);
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
				.setMessage("Context Menu 範例程式")
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

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onOptionsItemSelected(item);
		
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	
		if (v == mLinLayout) {
			if (menu.size() == 0) {
				MenuInflater inflater = getMenuInflater();
				inflater.inflate(R.menu.linear_layout_context_menu, menu);    
			}
		}
		else if (v == mTxtView) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.text_view_context_menu, menu);    
		}
	}
}