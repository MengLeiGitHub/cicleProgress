package com.example.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
 
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

 

public class WaterWaveView  extends View{

    private Handler mHandler;
    private long c = 0L;
    private boolean mStarted = false;
    private final float f = 0.033F;
    private int mAlpha = 31;
    private final int mColor = Color.GREEN;
    private float mAmplitude = 10.0F; // Õñ·ù
    private final Paint mPaint = new Paint();
    private float mWateLevel = 0.5F;
    private Path mPath;
    
    private int mScreenWidth;
    private int mScreenHeight;

    public WaterWaveView(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public WaterWaveView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    public void startWave() {
        if (!mStarted) {
            this.c = 0L;
            mStarted= true;
            this.mHandler.sendEmptyMessage(0);
        }
    }

    private void init(Context context) {
        mPaint.setStrokeWidth(1.0F);
        mPaint.setColor(mColor);
        mPaint.setAlpha(mAlpha);
        mPath = new Path();
   
        mHandler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 0) {
                    invalidate();
                    if (mStarted) {
                        mHandler.sendEmptyMessageDelayed(0, 60L);
                    }
                }
            }
        };
    }
  
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        if ((!mStarted) || (width == 0) || (height == 0)) {
            canvas.drawRect(0.0F, height / 2, width, height, mPaint);
            return;
        }
        if (this.c >= 8388607L) {
            this.c = 0L;
        }
        this.c = (1L + this.c);
        float f1 = height * (1.0F - mWateLevel);
        int top = (int) (f1 + mAmplitude);
        mPath.reset();
        mPath.addCircle(mScreenWidth/2,mScreenWidth/2, mScreenWidth/2, Path.Direction.CCW);
       canvas.clipPath(mPath, Region.Op.REPLACE);
        canvas.drawRect(0.0F, top, width, height, mPaint);
     //   canvas.drawPath(mPath, mPaint);
        
     /*   RectF rectF = new RectF(0.0F, top + 100, width, height);
        canvas.drawArc(rectF, 0, 180, true, mPaint);*/
        int n = (int) (f1 - this.mAmplitude
                * Math.sin(Math.PI * (2.0F * (0.0F + this.c * width * this.f)) / width));
        int startX = 0;
        int stopX = 0;
        while (stopX < width) {
            int startY = (int) (f1 - mAmplitude
                    * Math.sin(Math.PI * (2.0F * (stopX + this.c * width * this.f))
                            / width));
            canvas.drawLine(startX, n, stopX, startY, mPaint);
            canvas.drawLine(stopX, startY, stopX, top, mPaint);
            int i4 = stopX + 1;
            startX = stopX;
            stopX = i4;
            n = startY;
        }
        canvas.restore();
    }

    

    public void setAmplitude(float amplitued) {
        mAmplitude = amplitued;
    }

    public void setWaterAlpha(float alpha) {
        this.mAlpha = ((int) (255.0F * alpha));
        mPaint.setAlpha(this.mAlpha);
    }

    public void setWaterLevel(float paramFloat) {
        mWateLevel = paramFloat;
    }
    
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	     mScreenWidth = getMeasuredWidth();
	     mScreenHeight = getMeasuredHeight();
	 
	}
    
}
