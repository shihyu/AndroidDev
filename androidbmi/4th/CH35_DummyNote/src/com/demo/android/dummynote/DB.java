package com.demo.android.dummynote;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOTE = "note";
    public static final String KEY_CREATED = "created";
    public static final String KEY_MODIFIED = "modified";

    private static final String DATABASE_TABLE = "notes";

	private static class DatabaseHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "notes.db";
	    private static final int DATABASE_VERSION = 2;
	    
	    private static final String DATABASE_CREATE =
	        "CREATE TABLE "+ DATABASE_TABLE +"("
	            + KEY_ROWID + " INTEGER PRIMARY KEY,"
	            + KEY_NOTE + " note TEXT NOT NULL,"
	            + KEY_CREATED + " TIMESTAMP,"
	            + KEY_MODIFIED + " TIMESTAMP"
	        +");";
	    
		public DatabaseHelper(Context context) {
//			super(context, name, factory, version);
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
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
		}

    }
	
	private Context mContext = null;
	private DatabaseHelper dbHelper ;
	private SQLiteDatabase db;

	/** Constructor */
	public DB(Context context) {
	    this.mContext = context;
	}

	public DB open () throws SQLException {
	    dbHelper = new DatabaseHelper(mContext);
	    db = dbHelper.getWritableDatabase();
	    return this;
	}

	public void close() {
	    dbHelper.close();
	}
	
	public Cursor getAll() {
	    return db.rawQuery("SELECT * FROM "+DATABASE_TABLE, null);
	}
}
