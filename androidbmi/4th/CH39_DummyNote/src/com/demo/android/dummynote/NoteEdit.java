package com.demo.android.dummynote;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEdit extends Activity {
	private DB mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DB(this);
        mDbHelper.open();
        setContentView(R.layout.note_edit);
        findViews();
        showViews(savedInstanceState);
    }

    private EditText field_note;
    private Button button_confirm;

    private void findViews() {
        field_note = (EditText) findViewById(R.id.note);
        button_confirm = (Button) findViewById(R.id.confirm);
    }

    private Long mRowId;

    private void showViews(Bundle savedInstanceState) {
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
//            if (extras != null){
//                mRowId =  extras.getLong(DB.KEY_ROWID);
//            }else{
//                mRowId = null;
//            }
            mRowId = extras != null ? extras.getLong(DB.KEY_ROWID) : null;
        }

        populateFields();

        button_confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	if (mRowId == null) {
            		mDbHelper.create(field_note.getText().toString());
            	} else {
	                mDbHelper.update(mRowId, field_note.getText().toString());
	                setResult(RESULT_OK);
            	}
                finish();
            }

        });
    }

    private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.get(mRowId);
            startManagingCursor(note);

            field_note.setText(note.getString(
                    note.getColumnIndexOrThrow(DB.KEY_NOTE)
                ));
        }
    }
}
