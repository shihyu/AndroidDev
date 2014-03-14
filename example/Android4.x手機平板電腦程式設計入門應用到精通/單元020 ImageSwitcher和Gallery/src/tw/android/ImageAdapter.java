package tw.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
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
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageView v = new ImageView(cont);

        v.setImageResource(imgArr[position]);
        v.setAdjustViewBounds(true);
        v.setLayoutParams(new Gallery.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        return v;
	}
}
