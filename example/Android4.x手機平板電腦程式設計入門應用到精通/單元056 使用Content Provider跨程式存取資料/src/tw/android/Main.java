package tw.android;

import tw.android.providers.FriendsContentProvider;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private static ContentResolver mContRes;

	private EditText mEdtName,
					 mEdtSex,
					 mEdtAddr,
					 mEdtList;

	private Button mBtnAdd,
				  mBtnQuery,
				  mBtnList;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();

        mContRes = getContentResolver();
    }

    private void setupViewComponent() {
    	mEdtName = (EditText)findViewById(R.id.edtName);
		mEdtSex = (EditText)findViewById(R.id.edtSex);
		mEdtAddr = (EditText)findViewById(R.id.edtAddr);
		mEdtList = (EditText)findViewById(R.id.edtList);

		mBtnAdd = (Button)findViewById(R.id.btnAdd);
		mBtnQuery = (Button)findViewById(R.id.btnQuery);
		mBtnList = (Button)findViewById(R.id.btnList);

		mBtnAdd.setOnClickListener(onClickBtnAdd);
		mBtnQuery.setOnClickListener(onClickBtnQuery);
		mBtnList.setOnClickListener(onClickBtnList);
    }

    private Button.OnClickListener onClickBtnAdd = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	        
	        ContentValues newRow = new ContentValues();
	        newRow.put("name", mEdtName.getText().toString());
	        newRow.put("sex", mEdtSex.getText().toString());
	        newRow.put("address", mEdtAddr.getText().toString());
	        mContRes.insert(FriendsContentProvider.CONTENT_URI, newRow);
		}
    };

    private Button.OnClickListener onClickBtnQuery = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Cursor c = null;

			String[] projection = new String[]{"name", "sex", "address"};

			if (mEdtName.getText().toString().isEmpty() == false) {
				c = mContRes.query(FriendsContentProvider.CONTENT_URI, projection,
						"name=" + "\"" + mEdtName.getText().toString() + "\"",
						null, null);
			}
			else if (mEdtSex.getText().toString().isEmpty() == false) {
				c = mContRes.query(FriendsContentProvider.CONTENT_URI, projection,
						"sex=" + "\"" + mEdtSex.getText().toString() + "\"",
						null, null);
			}
			else if (mEdtAddr.getText().toString().isEmpty() == false) {
				c = mContRes.query(FriendsContentProvider.CONTENT_URI, projection,
						"address=" + "\"" + mEdtAddr.getText().toString() + "\"",
						null, null);
			}

			if (c == null)
				return;

			if (c.getCount() == 0) {
				mEdtList.setText("");
				Toast.makeText(Main.this, "沒有這筆資料", Toast.LENGTH_LONG)
					.show();
			}
			else {
				c.moveToFirst();
				mEdtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));
				
				while (c.moveToNext())
					mEdtList.append("\n" + c.getString(0) + c.getString(1)  + c.getString(2));
			}
		}
    };

    private Button.OnClickListener onClickBtnList = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String[] projection = new String[]{"name", "sex", "address"};
			
			Cursor c = mContRes.query(FriendsContentProvider.CONTENT_URI, projection,
					null, null, null);
			
			if (c == null)
				return;

			if (c.getCount() == 0) {
				mEdtList.setText("");
				Toast.makeText(Main.this, "沒有資料", Toast.LENGTH_LONG)
					.show();
			}
			else {
				c.moveToFirst();
				mEdtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));
				
				while (c.moveToNext())
					mEdtList.append("\n" + c.getString(0) + c.getString(1)  + c.getString(2));
			}
		}
    };

}