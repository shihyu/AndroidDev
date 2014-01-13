package com.demo.android.dummynote;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));
        registerForContextMenu(getListView());
        setAdapter();
    }
    
    private String[] note_array = {
    		/*
            "gasolin",
            "crota",
            "louk",
            "magicion"
            */
    };
    
    private DB mDbHelper;
    private Cursor mNotesCursor;

    private void setAdapter() {
//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    note_array);
//        setListAdapter(adapter);

//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                note_array));
    	
    	mDbHelper = new DB(this);
        mDbHelper.open();
        
//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                note_array);
//        setListAdapter(adapter);
        
        fillData();
    }
    
    private void fillData() {
        mNotesCursor = mDbHelper.getAll();
        startManagingCursor(mNotesCursor);

        String[] from_column = new String[]{DB.KEY_NOTE};
        int[] to_layout = new int[]{android.R.id.text1};

        // Now create a simple cursor adapter
        SimpleCursorAdapter adapter =
                    new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from_column, to_layout);
        setListAdapter(adapter);
    }
    
    private static final int ACTIVITY_EDIT=1;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, NoteEdit.class);
        intent.putExtra(DB.KEY_ROWID, id);
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        if(resultCode == RESULT_OK){
//            if(requestCode == ACTIVITY_EDIT){
            	fillData();
//           	}
//        }
    }
    
    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0, MENU_INSERT, 0, "新增記事");
        menu.add(0, MENU_DELETE, 0, "刪除記事");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()) {
        case MENU_INSERT:
//            String noteName = "Note " + mNoteNumber++;
//            mDbHelper.create(noteName);
//            fillData();
        	Intent intent = new Intent(Intent.ACTION_INSERT);
        	intent.setClass(Main.this, NoteEdit.class);
        	startActivity(intent);
        case MENU_DELETE:
            mDbHelper.delete(getListView().getSelectedItemId());
            fillData();
        }

        return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info;
		info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) { 
			case MENU_DELETE:
				mDbHelper.delete(info.id);
	            fillData();
	            break;
			}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, MENU_DELETE, 0,  "刪除記事");
	    menu.setHeaderTitle("要怎麼處理這筆項目？");
	}
    
    
}