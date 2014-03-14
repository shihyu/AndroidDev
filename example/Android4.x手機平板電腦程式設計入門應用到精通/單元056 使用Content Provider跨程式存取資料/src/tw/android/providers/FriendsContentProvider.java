package tw.android.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class FriendsContentProvider extends ContentProvider {

	private static final String AUTHORITY =
			"tw.android.providers.FriendsContentProvider";
	
	private static final String DB_FILE = "friends.db",
								DB_TABLE = "friends";
	
	private static final int URI_ROOT = 0,
							 DB_TABLE_FRIENDS = 1;
	
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ AUTHORITY + "/" + DB_TABLE);
	
	private static final UriMatcher sUriMatcher = new UriMatcher(URI_ROOT);
	
	static {
		sUriMatcher.addURI(AUTHORITY, DB_TABLE, DB_TABLE_FRIENDS);
	}

	private SQLiteDatabase mFriDb;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		if (sUriMatcher.match(uri) != DB_TABLE_FRIENDS) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
        
		long rowId = mFriDb.insert(DB_TABLE, null, values);
		
        Uri insertedRowUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
        
        getContext().getContentResolver().notifyChange(insertedRowUri, null);
		
		return insertedRowUri;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		FriendDbHelper friDbHp = new FriendDbHelper(
        		getContext(), DB_FILE,
        		null, 1);
        
        friDbHp.sCreateTableCommand = "CREATE TABLE " + DB_TABLE + "(" +
				"_id INTEGER PRIMARY KEY," +
				"name TEXT NOT NULL," +
				"sex TEXT," +
				"address TEXT);";
        
        mFriDb = friDbHp.getWritableDatabase();

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		if (sUriMatcher.match(uri) != DB_TABLE_FRIENDS) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
        
		Cursor c = mFriDb.query(true, DB_TABLE, projection, selection,
					null, null, null, null, null);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
