package refresh;

import com.example.cicleprogrossbar.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class RefreshListview  extends ListView{
	boolean  isScrollBottom,isScrollTop;
	
	
	public RefreshListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
 		init();
		initTop();
	}
	 private void initTop() {
		// TODO Auto-generated method stub
		addHeaderView(inflate(getContext(), R.layout.head, null));
		
		
		
	}
	private void init() {
		// TODO Auto-generated method stub
		setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int scrollState) {
				// TODO Auto-generated method stub
				 switch (scrollState) {  
				    // ��������ʱ  
				    case OnScrollListener.SCROLL_STATE_IDLE:  
				    // �жϹ������ײ�  
				    if (getLastVisiblePosition() == (getCount() - 1)) {  
				    	isScrollBottom=true;
				    	isScrollTop=!isScrollBottom;
 				     }  
				    // �жϹ���������  
 				    if(getFirstVisiblePosition() == 0){  
 				    	isScrollTop=true;
  				    	isScrollBottom=!isScrollTop;
				    }  
				  
				     break;  
				 }   
			}
			
			@Override
			public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem + visibleItemCount ==totalItemCount) {    
					isScrollBottom = true; 
			    	isScrollTop=!isScrollBottom;

	            } else{
	            	isScrollBottom = false;    
				    isScrollTop=false;
 	            }
			}
		});
	}
	 
	
	

}
