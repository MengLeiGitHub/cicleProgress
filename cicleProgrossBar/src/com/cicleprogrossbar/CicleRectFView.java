package com.cicleprogrossbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

 public class CicleRectFView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mRotate;
    private Matrix mMatrix = new Matrix();
    private Shader mShader;

    public CicleRectFView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        setFocusable(true);
        setFocusableInTouchMode(true);
        float x = 160;
        float y = 100;
        mShader = new SweepGradient(x, y, new int[] {0xFF09F68C,
                                              0xFFB0F44B,
                                              0xFFE8DD30,
                                              0xFFF1CA2E,
                                              0xFFFF902F,
                                              0xFFFF6433}, null);
        mPaint.setShader(mShader);
        mPaint.setStyle(Style.STROKE);
        PathEffect effect = new DashPathEffect(new float[] { 2, 3, 2,3}, 1);
        mPaint.setPathEffect(effect);
        mPaint.setStrokeWidth(40);
    }
    public void getArc(Canvas canvas,float o_x,float o_y,float r,
            float startangel,float endangel,Paint paint){
        RectF rect = new RectF(o_x - r, o_y - r, o_x + r, o_y + r);
        Path path = new Path();
        path.moveTo(o_x,o_y);
        path.lineTo((float)(o_x+r*Math.cos(startangel*Math.PI/180))
                  , (float)(o_y+r*Math.sin(startangel*Math.PI/180)));
        path.lineTo((float)(o_x+r*Math.cos(endangel*Math.PI/180))
                  , (float)(o_y+r*Math.sin(endangel*Math.PI/180)));
        path.addArc(rect, startangel, endangel-startangel);
        canvas.clipPath( path,Op.UNION);
        canvas.drawCircle(o_x, o_y, r, paint);
    }
    @Override 
    protected void onDraw(Canvas canvas) {
        Paint paint = mPaint;
        float x = 160;
        float y = 100;

        canvas.drawColor(Color.WHITE);

        mMatrix.setRotate(mRotate, x, y);
        mShader.setLocalMatrix(mMatrix);
        mRotate += 3;
        if (mRotate >= 360) {
            mRotate = 0;
        }
        invalidate();
        getArc(canvas,x,y,80,135,405,paint);
    }
}