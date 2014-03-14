package tw.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class Main extends Activity {

	private static final int MENU_MUSIC = Menu.FIRST,
	   MENU_PLAY_MUSIC = Menu.FIRST + 1,
	   MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
	   MENU_ABOUT = Menu.FIRST + 3,
	   MENU_EXIT = Menu.FIRST + 4;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		SubMenu subMenu = menu.addSubMenu(0, MENU_MUSIC, 0, "背景音樂")
		.setIcon(android.R.drawable.ic_media_ff);
		subMenu.add(0, MENU_PLAY_MUSIC, 0, "播放背景音樂");
		subMenu.add(0, MENU_STOP_PLAYING_MUSIC, 1, "停止播放背景音樂");
		menu.add(0, MENU_ABOUT, 1, "關於這個程式...")
			.setIcon(android.R.drawable.ic_dialog_info);
		menu.add(0, MENU_EXIT, 2, "結束")
			.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_PLAY_MUSIC:
			Intent it = new Intent(Main.this, MediaPlayService.class);
    		startService(it);
			break;
		case MENU_STOP_PLAYING_MUSIC:
			it = new Intent(Main.this, MediaPlayService.class);
    		stopService(it);
			break;
		case MENU_ABOUT:
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
		case MENU_EXIT:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}