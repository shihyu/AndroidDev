package com.demo.android.dummynote;

import java.util.Date;

import android.content.ContentValues;
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

    String[] strCols = new String[] {        KEY_ROWID,        KEY_NOTE,        KEY_CREATED,        KEY_MODIFIED    };	
//	public Cursor getAll() {
//	    return db.rawQuery("SELECT * FROM "+DATABASE_TABLE, null);
//	}

    // get all entries    public Cursor getAll() {        return db.query(DATABASE_TABLE, //Which table to Select            //strCols,// Which columns to return            new String[] {KEY_ROWID, KEY_NOTE, KEY_CREATED},
            null, // WHERE clause            null, // WHERE arguments            null, // GROUP BY clause            null, // HAVING clause            KEY_MODIFIED+" DESC" //Order-by clause        );    }
    
    // add an entry    public long create(String Note) {        Date now = new Date();        ContentValues args = new ContentValues();        args.put(KEY_NOTE, Note);        args.put(KEY_CREATED, now.getTime());
        args.put(KEY_MODIFIED, now.getTime());            return db.insert(DATABASE_TABLE, null, args);    }
    
    //remove an entry
    public boolean delete(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    //query single entry
    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(true,
                DATABASE_TABLE,
                new String[] {KEY_ROWID, KEY_NOTE, KEY_CREATED, KEY_MODIFIED},
                KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //update
    public boolean update(long rowId, String note) {
    	Date now = new Date();
        ContentValues args = new ContentValues();
        args.put(KEY_NOTE, note);
        args.put(KEY_MODIFIED, now.getTime());

        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
