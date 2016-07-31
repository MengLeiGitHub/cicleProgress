package com.barrage;

import com.example.cicleprogrossbar.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ToBarrageAdapter extends BarrageAdapter {
	 
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	LayoutInflater  flater;
	public ToBarrageAdapter(Context context){
		   mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
			options = new DisplayImageOptions.Builder().cacheInMemory()
					.cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();
			flater=LayoutInflater.from(context);
	}
	
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
 		dataChange.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		current=arg0;
		View v = flater.inflate(R.layout.barrage_item, null);
		RoundImageView riv = (RoundImageView) v.findViewById(R.id.iv);
		mImageLoader.displayImage("http://p3.pccoo.cn/weixin/20160322/2016032217403022340241s.png",
				riv, options);
		
		TextView tv = (TextView) v.findViewById(R.id.tv);
		tv.setText("text:"+arg0);
			/*LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 5, 5, 0);
		
		container.addView(v, lp);*/
		 
		return v;
	}
	
	int i =20;
	
	
	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}
	
	
	@Override
	public void register(DataChangeInterface change) {
		// TODO Auto-generated method stub
		dataChange=change;
	}
	int current;

	@Override
	public int getCurrentNum() {
		// TODO Auto-generated method stub
		return current;
	}


	@Override
	public void notifyDataSetInvalidated() {
		// TODO Auto-generated method stub
		dataChange.notifyDataSetInvalidated();
	}


	@Override
	public void AnimPause() {
		// TODO Auto-generated method stub
		dataChange.AnimPause();
	}


	@Override
	public void AnimStart() {
		// TODO Auto-generated method stub
		dataChange.AnimStart();
	}

}
