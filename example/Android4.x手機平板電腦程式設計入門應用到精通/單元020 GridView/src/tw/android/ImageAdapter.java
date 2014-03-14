package tw.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	private Context cont;
	private Integer[] imgArr;
	
	public ImageAdapter(Context c) {
        cont = c;
    }
	
	public void setImageArray(Integer[] imgArr) {
		this.imgArr = imgArr;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgArr.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView v;
		
		if (convertView == null) {
			v = new ImageView(cont);
			v.setLayoutParams(new GridView.LayoutParams(90, 90));
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
			v.setPadding(5, 5, 5, 5);
		}
		else
			v = (ImageView) convertView;

        v.setImageResource(imgArr[position]);
        
        return v;
	}
}
