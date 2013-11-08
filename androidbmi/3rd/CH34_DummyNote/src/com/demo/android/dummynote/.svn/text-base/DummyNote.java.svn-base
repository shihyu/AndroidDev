package com.demo.android.dummynote;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
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
}