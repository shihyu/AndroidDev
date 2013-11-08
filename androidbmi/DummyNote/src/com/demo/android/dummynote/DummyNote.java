package com.demo.android.dummynote;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DummyNote extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));

        setAdapter();    
    }
    
    /*private String[] note_array = {
    		"gasolin",
    		"crota",
    		"louk",
    		"magicion"
    };*/
    

    private NotesDbAdapter mDbHelper;
    private Cursor mNotesCursor;
    
    private void setAdapter(){
    	mDbHelper = new NotesDbAdapter(this);
    	mDbHelper.open();
    	fillData();
    }
    
    private void fillData(){
    	mNotesCursor = mDbHelper.getall();
    	startManagingCursor(mNotesCursor);
    	
    	// Create an array to specify the fields we want to display in the list (only TITLE)
        //String[] note_array = new String[]{NotesDbAdapter.KEY_NOTE};
    	String[] from = new String[]{NotesDbAdapter.KEY_NOTE};
    	
        // and an array of the fields we want to bind those fields to (in this case just text1)
        //int[] to = new int[]{R.id.text1};
    	int[] to = new int[]{android.R.id.text1};
    	
    	/*ListAdapter adapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_list_item_1,
    			note_array);*/
    	// Now create a simple cursor adapter and set it to display
        //SimpleCursorAdapter adapter = 
        //	    new SimpleCursorAdapter(this, R.layout.notes_row, mNotesCursor, from, to);
        SimpleCursorAdapter adapter = 
    	    new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to);
    	setListAdapter(adapter);    	
    }
    
    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_INSERT, 0, "新增記事");
        menu.add(0, MENU_DELETE, 0,  "刪除記事");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    //public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	switch(item.getItemId()) {
        case MENU_INSERT:
        	String noteName = "Note " + mNoteNumber++;
        	mDbHelper.create(noteName);
        	fillData();
            return true;
        case MENU_DELETE:
            mDbHelper.delete(getListView().getSelectedItemId());
            fillData();
            return true;
        }
       
        return super.onOptionsItemSelected(item);
    }
    
    private static final int ACTIVITY_EDIT=0x1001;
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, NoteEdit.class);
        intent.putExtra(NotesDbAdapter.KEY_ROWID, id);
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, 
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
    
}