package com.singleConstumdialog;

 
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class guaguale extends View {
	RectF rectF;
	Paint paint;
	Path path;
	int width;
	int height;
	public guaguale(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private void init() {
 		width = getMeasuredWidth();
		height = getMeasuredHeight();
 		rectF = new RectF(5, 5, width - 5, height - 5);
		paint = new Paint();
 		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		path=new Path();
	}
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	//	Matrix matrix=new Matrix();
  	//	Bitmap bitmap=Bitmap.createBitmap(width, height, Config.RGB_565);
  		canvas.drawText("123123123", 0, height/2, paint);
  		paint.setColor(Color.RED);
		canvas.drawPaint(paint);
		paint.setAlpha(0);
		  // mPaint.setAlpha(0);  
		paint.setXfermode(new    PorterDuffXfermode(Mode.DST_IN));  
 		paint.setAntiAlias(true);  
		paint.setDither(true);  
		paint.setStyle(Paint.Style.STROKE);  
        paint.setStrokeJoin(Paint.Join.ROUND); // Ç°Ô²½Ç  
        paint.setStrokeCap(Paint.Cap.ROUND); // ºóÔ²½Ç  
        paint.setStrokeWidth(5); // ±Ê¿í  
 		
 		
		canvas.drawPath(path, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
 			path.lineTo(event.getX(), event.getY());
			invalidate();

  			break;
		case MotionEvent.ACTION_DOWN:
			
			path.moveTo(event.getX(), event.getY());
			
			break;

		default:
			break;
		}
		
		
		
		
		return super.onTouchEvent(event);
	}
	
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		init();
	}

}
