package com.demo.android.dummynote;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDbAdapter {

    private static final String DATABASE_NAME = "notes.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_TABLE = "notes";
	
	private static final String DATABASE_CREATE =
    "create table notes("
        +"_id INTEGER PRIMARY KEY,"
	    +"note TEXT NOT NULL,"
	    +"created INTEGER"
	+");";
    
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP IF TABLE EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}	
	}
	
	private Context mCtx = null;
	private DatabaseHelper dbHelper ;
	private SQLiteDatabase db;
	
	/** Constructor */
	public NotesDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public NotesDbAdapter open () throws SQLException {
		dbHelper = new DatabaseHelper(mCtx);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
    public static final String KEY_ROWID = "_id";
	public static final String KEY_NOTE = "note";
	public static final String KEY_CREATED = "created";
	
	//CRUD interface
	//create
	public long create(String Note)
	{
		Date now = new Date();
		ContentValues args = new ContentValues();
		args.put(KEY_NOTE, Note);
		args.put(KEY_CREATED, now.getTime());
		
		return db.insert(DATABASE_TABLE, null, args);
	}

	//retrieve
	public Cursor getall() {
		try {
			//return db.rawQuery("SELECT * FROM notes", null);
			Cursor mCursor = db.rawQuery("SELECT * FROM notes", null);
			if (mCursor != null) {
				if (!mCursor.moveToFirst()){
					return null;
				}
			}
			return mCursor;
			/*return db.query(DATABASE_TABLE,
				new String[] {KEY_ROWID, KEY_NOTE, KEY_CREATED},
				null, null, null, null, null);*/
		}
		catch (Exception e) {
			//Log.e("crud", e.getMessage());
			return null;
		}
	}
	
	public Cursor get(long rowId) throws SQLException {
		Cursor mCursor = db.query(true,
				DATABASE_TABLE,
				new String[] {KEY_ROWID, KEY_NOTE, KEY_CREATED},
				KEY_ROWID + "=" + rowId,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//update
	public boolean update(long rowId, String note) {
		ContentValues args = new ContentValues();
		args.put(KEY_NOTE, note);
		
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

	//delete
	public boolean delete(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
