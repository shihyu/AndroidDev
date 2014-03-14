package tw.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends ListActivity {
	
	private TextView mTxtResult;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        
        ArrayAdapter<CharSequence> adapWeekday = ArrayAdapter.createFromResource(
				this, R.array.weekday, android.R.layout.simple_list_item_1);
        setListAdapter(adapWeekday);
        
        ListView listview = getListView();
        listview.setTextFilterEnabled(true);

        listview.setOnItemClickListener(listviewOnItemClkLis);
    }

    AdapterView.OnItemClickListener listviewOnItemClkLis = new AdapterView.OnItemClickListener() {
		@Override
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
			// TODO Auto-generated method stub
			mTxtResult.setText(((TextView) view).getText());
		}
	};
}