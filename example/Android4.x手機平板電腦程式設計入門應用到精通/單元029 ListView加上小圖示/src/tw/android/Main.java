package tw.android;

import java.util.*;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Main extends ListActivity {
	
	private TextView mTxtResult;
	List<Map<String, Object>> mList;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }
    
    private void setupViewComponent() {
        mTxtResult = (TextView)findViewById(R.id.txtResult);

        String[] listFromResource = getResources().getStringArray(R.array.weekday);

        mList = new ArrayList<Map<String,Object>>();

        for (int i = 0; i < listFromResource.length; i++) {
	        Map<String, Object> item = new HashMap<String, Object>();   
	        item.put("imgView", android.R.drawable.ic_menu_my_calendar);   
	        item.put("txtView", listFromResource[i]);   
	        mList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, mList,   
        		R.layout.list_item,   
        		new String[] { "imgView", "txtView" },    
        		new int[] { R.id.imgView ,R.id.txtView });

        setListAdapter(adapter);

        ListView listview = getListView();
        listview.setTextFilterEnabled(true);
        listview.setOnItemClickListener(listviewOnItemClkLis);
    }

    AdapterView.OnItemClickListener listviewOnItemClkLis = new AdapterView.OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	        String s =((TextView)view.findViewById(R.id.txtView)).getText().toString();
	        mTxtResult.setText(s);
	    }
    };
}