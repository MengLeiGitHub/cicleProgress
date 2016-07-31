package com.singleConstumdialog;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class DragView extends ListView{
	WindowManager mWindowManager;
	Vibrator   mVibrator;
	public DragView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public DragView(Context context,AttributeSet a) {
		super(context,a);
		// TODO Auto-generated constructor stub]
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);  

		init();
	}
	
	

	private void init() {
		// TODO Auto-generated method stub
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);  

		
		/*this.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				createView(arg1);
				arg1.setVisibility(INVISIBLE)				;
				isTouchMove=true;
				return false;
			}

		});*/
	}
	WindowManager.LayoutParams ll;
	ImageView mDragImageView;
	boolean   isTouchMove;
	@SuppressLint("NewApi")
	private void createView(View arg1) {
		// TODO Auto-generated method stub
		arg1.setDrawingCacheEnabled(true);
		Bitmap nbitmap=Bitmap.createBitmap(arg1.getDrawingCache());
		ll=new WindowManager.LayoutParams(nbitmap.getWidth(), nbitmap.getHeight());
		ll.format = PixelFormat.TRANSLUCENT; //图片之外的其他地方透明  
		ll.gravity = Gravity.TOP | Gravity.LEFT;  

		ll.alpha = 0.55f; //透明度  
		ll.x=(int) arg1.getX()-100;
		ll.y=(int) arg1.getY()-100;
 		ll. flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL 
 		          | WindowManager.LayoutParams. FLAG_NOT_FOCUSABLE 
 		          | WindowManager.LayoutParams. FLAG_SHOW_WHEN_LOCKED
                  | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE ; 
 		ll.width=nbitmap.getWidth();
 		ll.height=nbitmap.getHeight();

 		mDragImageView = new ImageView(getContext());    
        mDragImageView.setImageBitmap(nbitmap);    
        mDragImageView.setLayoutParams(ll);
		Toast.makeText(getContext(), mDragImageView.getWidth()+"  "+mDragImageView.getHeight(),Toast.LENGTH_SHORT).show();

        
        mVibrator.vibrate(100);
        arg1.setVisibility(INVISIBLE);
 		arg1.destroyDrawingCache();
 		mWindowManager.addView(mDragImageView, ll);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:

			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();
		//	if(isTouchMove)	
			moveView(mDragImageView, moveX, moveY);

			break;
		case MotionEvent.ACTION_DOWN:
			int  postion=pointToPosition((int)ev.getX(), (int)ev.getY());
			View child=getChildAt(postion);
			if(child!=null)
			createView(child);
 			break;

		default:
			break;
		}

		return super.onTouchEvent(ev);
	}
	
	
	
	void moveView(View view,int x,int y){
 		ll.x=x;
		ll.y=y;
		mWindowManager.updateViewLayout(view, ll);
		
 	}
	

}
