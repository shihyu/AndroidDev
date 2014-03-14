package tw.android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

public class Main extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ActionBar actBar = getActionBar();
        actBar.setDisplayShowTitleEnabled(false);
        actBar.setDisplayUseLogoEnabled(true);
        actBar.setBackgroundDrawable(new ColorDrawable(0xFF505050));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		
		// 利用程式碼的方式設定 Action Item
//		MenuItem menuItem = menu.findItem(R.id.menuItemExit);
//		menuItem.setShowAsAction(
//				MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		// 設定 action views
		Spinner spnRegion = (Spinner) menu.findItem(R.id.menuItemRegion).getActionView().findViewById(R.id.spnRegion);
		ArrayAdapter<CharSequence> adapRegionList = ArrayAdapter.createFromResource(
				this, R.array.spnRegionList, android.R.layout.simple_spinner_item);
        spnRegion.setAdapter(adapRegionList);
        spnRegion.setOnItemSelectedListener(spnRegionItemSelLis);
        
        SearchView searchView = (SearchView) menu.findItem(R.id.menuItemSearch).getActionView();
        searchView.setOnQueryTextListener(searchViewOnQueryTextLis);
		
		return super.onCreateOptionsMenu(menu);
	}

	private SearchView.OnQueryTextListener searchViewOnQueryTextLis = new SearchView.OnQueryTextListener() {

		@Override
		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onQueryTextSubmit(String query) {
			// TODO Auto-generated method stub
			Toast.makeText(Main.this, query, Toast.LENGTH_LONG).show();
			
			return true;
		}
	};

	private Spinner.OnItemSelectedListener spnRegionItemSelLis =
    	new Spinner.OnItemSelectedListener () {
    		public void onItemSelected(AdapterView parent,
    									View v,
    									int position,
    									long id) {
    			Toast.makeText(Main.this, parent.getSelectedItem().toString(), 
    					Toast.LENGTH_LONG).show();
    		}
    		public void onNothingSelected(AdapterView parent) {
    		}
    };

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
		case android.R.id.home:
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
		case R.id.menuItemRegion:
			new AlertDialog.Builder(Main.this)
				.setTitle("選擇地區")
				.setMessage("這是選擇地區對話盒")
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
		case R.id.menuItemSearch:
			new AlertDialog.Builder(Main.this)
				.setTitle("搜尋")
				.setMessage("這是搜尋對話盒")
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
		}

		return super.onOptionsItemSelected(item);
	}
}