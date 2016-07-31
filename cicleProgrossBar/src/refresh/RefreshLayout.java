package refresh;

import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cicleprogrossbar.R;

public class RefreshLayout extends LinearLayout{
	private AdapterView<?> centerView;
	private View   head=null,bottom=null;
	private int height,width,center_bottom;
	private int multiple=5;
	private Point centerview_point=new Point();
	private boolean  isFristRefrsh=true;
	private boolean  isScrollAnimor;
	
	
	public RefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 head=inflate(getContext(), R.layout.head, null);
		 //View
		//this.head=head.findViewById(R.id.image);
		 LayoutParams l=new LayoutParams(LayoutParams.FILL_PARENT, 0);
		addView(head,0,l);
		/*TextView t=(TextView) getChildAt(0).findViewById(R.id.head);
		t.setText("head");*/
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		height=getMeasuredHeight();
		width=getMeasuredWidth();
		 
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int y=(int) ev.getRawY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:
			if(isScrollAnimor)return false;
 			int deltaY = y - mLastMotionY;
   			if(deltaY>0)HeadView(deltaY);
  			else if(deltaY<0)   bottomView(deltaY) ;
		    break;
  		case MotionEvent.ACTION_UP:
   			fingerUp(y - mLastMotionY);
   			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		
		
		 
		return  false;
	}
	
	
	
	



	int mLastMotionY;
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if(isScrollAnimor)return true;
		int y =   (int) ev.getRawY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			initCenterViewValues();
  			mLastMotionY = (int) y;
			break;
		case MotionEvent.ACTION_MOVE:
			// deltaY > 0 是向下,< 0是向上运
			int deltaY =  y - mLastMotionY;
			return CheckThisViewCanScroll(deltaY);
			
   		case MotionEvent.ACTION_UP:
   			
   			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		 
		return false;
	}
	
	
 	
	/**
	 * 获取中间view的Y值
	 */
	@SuppressLint("NewApi")
	private void initCenterViewValues() {
		// TODO Auto-generated method stub
		if(isFristRefrsh){
			View v=getChildAt(1);
 			centerview_point.x=(int) v.getX();
			centerview_point.y=(int) v.getY();
			center_bottom=v.getBottom();
			isFristRefrsh=false;
		}
	}
	@SuppressLint("NewApi")
	private boolean  CheckThisViewCanScroll(int deltaY){
		View v=getChildAt(1);
		
		if(v==null)return false;
		if(v instanceof AdapterView<?>){
			centerView=(AdapterView<?>) v;
			int VisiblechildCount=centerView.getChildCount();
			int TotalCount=centerView.getCount();

  			if(VisiblechildCount==0)return true;
   			int index=centerView.getFirstVisiblePosition();
			View firstVisibleView=centerView.getChildAt(index);
			
 			// deltaY > 0 是向下,< 0是向上运
			if(deltaY>0){
				if(firstVisibleView.getTop()==0)return true;
			}else{
				int  lastindex=centerView.getLastVisiblePosition();
				View LastVisibleView=centerView.getChildAt(VisiblechildCount-1);
				int centerviewBottom=centerView.getBottom();
				int lastbottom=LastVisibleView.getBottom();
 				if(lastbottom<=centerviewBottom&&lastindex==TotalCount-1)return true;
 			}
		 }else{
			 Toast.makeText(getContext(), "请替换中间的view", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
		return false;
	}
	
	
	
	@SuppressLint("NewApi")
	private  void  HeadView(float rawY){
		//Log.e("height",height+"");
		if(rawY<height/15) return ;
		setCenterViewValue(rawY);
 		//if(rawY/10f>8)return;
 		LayoutParams l=(LayoutParams) head.getLayoutParams();
 		l.height=(int) (rawY/multiple);
  	    head.setLayoutParams(l);
  		/*head.setScaleX(rawY/10f);
 		head.setScaleY(rawY/10f);*/
 	}
	
	private void bottomView(int deltaY) {
		// TODO Auto-generated method stub
		if(-1*deltaY<height/15) return ;
		setCenterViewBottom(center_bottom+deltaY);
		if(deltaY/multiple*-1>dp2px)return ;
 		LayoutParams lp=(LayoutParams) bottom.getLayoutParams();
 		lp.height=deltaY/multiple*-1;
 		bottom.setLayoutParams(lp);
	}
	
	@SuppressLint("NewApi")
	private void  setCenterViewValue(float rawY){
		/*LayoutParams lp=(LayoutParams) centerView.getLayoutParams();
		lp.topMargin=(int) (rawY/multiple);
		centerView.setLayoutParams(lp);*/
		centerView.setY(centerview_point.y+rawY/multiple);
	}
	@SuppressLint("NewApi")
	private void  setCenterViewBottom(float bottomMargin){
		centerView.setBottom((int) bottomMargin);
 	}
	
	
    int dp2px=(int) (getResources().getDisplayMetrics().density*300);
 	private void  fingerUp(final int y){
   		if(y>0){
   			if( y<dp2px){
   	 			centerview_animation(y);
   				topOrBottomView_animation(y);
   	 		}else
   	 		   topView_animation_( y);
   		}else{
   			if(y>dp2px*-1){
   				topOrBottomView_animation(y);
   			}else{
   				bottomView_animation_(y);
   			}
   		}
  		
	}
	
	@SuppressLint("NewApi")
	private void   centerview_animation(int y){
		final View v=getChildAt(1);
		float target_y=centerview_point.y;
		float now_y=v.getY();
		ObjectAnimator  obj=ObjectAnimator.ofFloat(v, "Y", now_y,target_y);
		obj.setInterpolator(new DecelerateInterpolator());
		obj.setDuration(1000);
		obj.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				float y=arg0.getAnimatedFraction();
				setCenterViewValue(y);
			}
		});
		obj.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=true;
 			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;

			}
		});
		obj.start();
		
		
	}
	@SuppressLint("NewApi")
	private  void  centerView_height_animation(int y){
		final View v=getChildAt(1);
		float target_bottom=center_bottom;
		float now_bottom=v.getBottom();
		ObjectAnimator  obj=ObjectAnimator.ofFloat(v, "height", now_bottom,target_bottom);
		obj.setInterpolator(new DecelerateInterpolator());
		obj.setDuration(1000);
		obj.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				float y=arg0.getAnimatedFraction();
				setCenterViewBottom(y);
			}
		});
		obj.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=true;
 			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;

			}
		});
		obj.start();
	}
	
	
	@SuppressLint("NewApi")
	private  void  topOrBottomView_animation(int y){
		final View v=getChildAt(y>0?0:2);
		int topOrBottomHeight=y/multiple;
		topOrBottomHeight=topOrBottomHeight>0?topOrBottomHeight:topOrBottomHeight*-1;
 		ObjectAnimator   obj=ObjectAnimator.ofInt(v, "height", topOrBottomHeight,0);
 		obj.setDuration(1000);
		obj.setInterpolator(new DecelerateInterpolator());
  		obj.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				int height=(Integer) arg0.getAnimatedValue();
				LayoutParams lp=(LayoutParams) v.getLayoutParams();
				lp.height=height;
				v.setLayoutParams(lp);
 			}
		});
 		obj.start();
	}
	@SuppressLint("NewApi")
	private void   centerview_animation_bottom(int y){
		final View v=getChildAt(1);
		float target_bottom=center_bottom;
		float now_bottom=v.getBottom();
		ObjectAnimator  obj=ObjectAnimator.ofFloat(v, "bottom", now_bottom,target_bottom);
		obj.setInterpolator(new DecelerateInterpolator());
		obj.setDuration(1000);
		obj.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				float bottom=arg0.getAnimatedFraction();
				setCenterViewBottom(bottom);
			}
		});
		obj.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=true;
 			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				// TODO Auto-generated method stub
				isScrollAnimor=false;

			}
		});
		obj.start();
		
		
	}
	
	
	
	/**
	 * 本方法是 执行 头部 动画的 ，头部 子View 的动画		
	 */
	private  void  topView_animation_(final int y){
		if(y>dp2px(getContext(),400))return; 
		 ViewGroup v=(ViewGroup) getChildAt(0);
		 View ch=v.getChildAt(0);
		 final RotateAnimation animation =new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF, 
				 0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
				 animation.setDuration(3000);//设置动画持续时间 
				 /** 常用方法 */ 
				 //animation.setRepeatCount(100);//设置重复次数 
				 //animation.setFillAfter(boolean);//动画执行完后是否停留在执行完的状态
				 animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
						centerview_animation(y);
						topOrBottomView_animation(y);
					}
				});
 		 ch.startAnimation(animation);
		 
	}
	int i_num;
	Timer timer;
	private  void  bottomView_animation_(final int y){
 		 ViewGroup v=(ViewGroup) getChildAt(2);
 		 final TextView bottom1=(TextView) bottom.findViewById(R.id.head);
 		timer= new Timer();
 		timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					bottom1.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							i_num++;
							isScrollAnimor=!(i_num>20);
							if(i_num<20)
							bottom1.setText("刷新中"+i_num);
							else if(i_num==20){
				   				topOrBottomView_animation(y);
 							}else{
 								timer.cancel();
 								timer=null;
 							}
							
						}
					});
				}
			}, 0,500);	
 		 
		 
	}
	
	
	
	 @Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		bottom=inflate(getContext(), R.layout.bottom, null);
		LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT,0);
		addView(bottom,lp);
		TextView bottom1=(TextView) bottom.findViewById(R.id.head);
		bottom1.setText("bottom");
	 
	 }
	
	 private  int dp2px(Context context, float dpVal)  
	    {  
	        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,  
	                dpVal, context.getResources().getDisplayMetrics());  
	    }  
	  
}
