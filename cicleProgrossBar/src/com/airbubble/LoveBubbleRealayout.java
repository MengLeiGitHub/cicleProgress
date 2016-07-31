package com.airbubble;

import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cicleprogrossbar.R;

public class LoveBubbleRealayout extends RelativeLayout{

	private  int  width,height;
	 private Interpolator[] interpolators = {new AccelerateDecelerateInterpolator()//加速减速
     , new AccelerateInterpolator()//加速
     , new LinearInterpolator()
     , new DecelerateInterpolator()//减速
};
	
	
 	public LoveBubbleRealayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
 
	}
 	  @Override
 	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
 	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
 	       height = getMeasuredHeight();
 	       width = getMeasuredWidth();
 	    }

	//绘制孩子的
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.dispatchDraw(canvas);
	}
	
	int i=0;
	@SuppressLint("NewApi")
	public  void  addLove(){
		final ImageView  image=new ImageView(getContext());
		int  drawable[]={R.drawable.love1,R.drawable.love2,R.drawable.love3,R.drawable.love4};
		Random random=new Random();
		int drawIndex=random.nextInt(4);
		image.setImageResource(drawable[drawIndex]);
 		addView(image);
 		AnimatorSet animationSet=getAnimationset(image);
 		animationSet.addListener(new AnimatorListenerAdapter() {
  			@Override
			public void onAnimationEnd(Animator arg0) {
				// TODO Auto-generated method stub
  				removeView(image);
 			}
 		});
 	 	animationSet.start();
		
	}

	@SuppressLint("NewApi")
	private AnimatorSet getAnimationset(final View v) {
		// TODO Auto-generated method stub
		ObjectAnimator  objectAnimator=ObjectAnimator.ofFloat(v,"alpha",0.2f,1f);
		ObjectAnimator  scaleX=ObjectAnimator.ofFloat(v, "scaleX",0.2f,1f);
		ObjectAnimator  scaleY=ObjectAnimator.ofFloat(v, "scaleY",0.2f,1f);
		
		AnimatorSet start=new AnimatorSet();
		start.playTogether(objectAnimator,scaleX,scaleY);
		start.setDuration(1000);
		start.setTarget(v);
		
		
		ValueAnimator  value=getLoveTypeEvaluator(v);
		AnimatorSet  end=new AnimatorSet();
		end.playSequentially(start,value);
 
		return end;
	}
	@SuppressLint("NewApi")
	private  ValueAnimator getLoveTypeEvaluator(final View v){
		Random random=new Random();
		PointF start=new PointF(0,random.nextInt(height));
		v.setX(start.x);
		v.setY(start.y);
		 
  		PointF point2=new PointF(random.nextInt(width/2),random.nextInt(height/5)+height/5*2);
		PointF point3=new PointF(random.nextInt(width/2)+width/2,random.nextInt(height/5)+height/5*3);
		PointF end=new PointF(width,random.nextInt(height));
		 
		
 		LoveTypeEvaluator  love=new LoveTypeEvaluator(point2,point3);
  		ValueAnimator  value=ValueAnimator.ofObject(love, start,end); 
 		value.setTarget(v);
		value.setDuration(3000);
		
		value.setInterpolator(interpolators[random.nextInt(interpolators.length)]);
		value.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
			   PointF pointf =(PointF) arg0.getAnimatedValue();
			   v.setX(pointf.x);
			   v.setY(pointf.y);
               v.setAlpha(1 - arg0.getAnimatedFraction());//getAnimatedFraction
			}
		});
		
		return value;
	}
	
	
	 
	

}
