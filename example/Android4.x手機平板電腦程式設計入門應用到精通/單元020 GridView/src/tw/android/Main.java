package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class Main extends Activity {

private GridView grdView;
	
	private Integer[] thumbImgArr = {
            R.drawable.img01th, R.drawable.img02th, R.drawable.img03th,
            R.drawable.img04th, R.drawable.img05th, R.drawable.img06th,
            R.drawable.img07th, R.drawable.img08th, R.drawable.img09th,
            R.drawable.img10th, R.drawable.img11th, R.drawable.img12th,
            R.drawable.img13th, R.drawable.img14th, R.drawable.img15th};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // 從資源類別R中取得介面元件並設定相關屬性
        grdView = (GridView)findViewById(R.id.grdView);
    	
        ImageAdapter imgAdap = new ImageAdapter(this);
        imgAdap.setImageArray(thumbImgArr);
        grdView.setAdapter(imgAdap);
	}
}