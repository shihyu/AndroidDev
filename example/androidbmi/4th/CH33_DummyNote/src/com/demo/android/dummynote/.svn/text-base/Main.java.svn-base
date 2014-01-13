package com.demo.android.dummynote;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class Main extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));
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
    
    private void setAdapter() {
//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    note_array);
//        setListAdapter(adapter);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                note_array));
    }
}