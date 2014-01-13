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
import android.widget.Toast;

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
    
//    private String[] note_array = {
//            "gasolin",
//            "crota",
//            "louk",
//            "magicion"
//    };

    private DB mDbHelper;
    private Cursor mNotesCursor;
    
    private void setAdapter() {
    	mDbHelper = new DB(this);
        mDbHelper.open();
        fillData();
//    	ListAdapter adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    note_array);
//        setListAdapter(adapter);
    }
    
    private void fillData() {
        mNotesCursor = mDbHelper.getAll();
        startManagingCursor(mNotesCursor);

//        String[] from = new String[]{"note"};
        String[] from = new String[]{DB.KEY_NOTE};
        int[] to = new int[]{android.R.id.text1};

        // Now create a simple cursor adapter
        SimpleCursorAdapter adapter =
                    new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to);
        setListAdapter(adapter);
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
            String noteName = "Note " + mNoteNumber++;
            mDbHelper.create(noteName);
            fillData();
        case MENU_DELETE:
            mDbHelper.delete(getListView().getSelectedItemId());
            fillData();
        }

        return super.onOptionsItemSelected(item);
    }
    
    private static final int ACTIVITY_EDIT=0x1001;

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
        //fillData();
        if(resultCode == RESULT_OK){
        	if(requestCode == ACTIVITY_EDIT){
        		fillData();
        	}
        }
    }
}