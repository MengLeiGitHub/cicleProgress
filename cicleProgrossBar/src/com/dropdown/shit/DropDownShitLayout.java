package com.dropdown.shit;

import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cicleprogrossbar.R;

public class DropDownShitLayout extends RelativeLayout{

	int width,height;
	public DropDownShitLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	Random random=new Random();
	@SuppressLint("NewApi")
	public void addShit(){
		int shit[]={R.drawable.shit1,R.drawable.shit2,R.drawable.shit3,R.drawable.shit4,R.drawable.shit5};
		final ImageView image=new ImageView(getContext());
		image.setImageResource(shit[random.nextInt(shit.length)]);
 		addView(image);

		AnimatorSet set=getAnimatorSet(image);
		set.start();
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				removeView(image);
			}
		}); 
 	}
	  @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	       height = getMeasuredHeight();
	       width = getMeasuredWidth();
	    }
	@SuppressLint("NewApi")
	private AnimatorSet  getAnimatorSet(final View view){
		PointF  start=new PointF(random.nextInt(width),random.nextInt(height/7));
		view.setX(start.x);
		view.setY(start.y);
		
		PointF  end=new PointF(start.x,height);
		
		ObjectAnimator  objectAnimator=ObjectAnimator.ofFloat(view,"alpha",0.2f,1f);
		ObjectAnimator  scaleX=ObjectAnimator.ofFloat(view, "scaleX",0.2f,1f);
		ObjectAnimator  scaleY=ObjectAnimator.ofFloat(view, "scaleY",0.2f,1f);
		
		AnimatorSet startAnim=new AnimatorSet();
		startAnim.playTogether(objectAnimator,scaleX,scaleY);
		startAnim.setDuration(1000);
		startAnim.setTarget(view);
		
		
		DropDownTypeEvaluator dropdown=new DropDownTypeEvaluator();
  		ObjectAnimator   dropdownAnimator=ObjectAnimator.ofObject(view, "y", dropdown, start,end);
 		dropdownAnimator.setDuration(2000);
 		//dropdownAnimator.setInterpolator(new AccelerateInterpolator());
 		dropdownAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				PointF p=(PointF) arg0.getAnimatedValue();
				view.setY(p.y);
 			}
		});
 		
 		 AnimatorSet endAnim=new AnimatorSet();
 		 endAnim.playSequentially(startAnim,dropdownAnimator);
 		 
 		return endAnim;
	}
	
	
	
	
	

}
