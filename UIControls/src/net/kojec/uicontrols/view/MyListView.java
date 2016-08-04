package net.kojec.uicontrols.view;

import net.kojec.uicontrols.R;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;



public class MyListView extends ListView implements OnTouchListener,OnGestureListener{

	private GestureDetector gestureDetector;
	private OnDeleteListener listener;
	private boolean isDeleteShown;
	private ViewGroup itemLayout;
	private View deleteButton;
	private int selectedItem;
	
	
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		gestureDetector=new GestureDetector(getContext(),(OnGestureListener) this);
		setOnTouchListener((OnTouchListener) this);
	}

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gestureDetector=new GestureDetector(getContext(),(OnGestureListener) this);
		setOnTouchListener((OnTouchListener) this);
	}

	
	public OnDeleteListener getListener() {
		return listener;
	}

	public void setDeleteListener(OnDeleteListener listener) {
		this.listener = listener;
	}


	public interface OnDeleteListener{
		public void onDelete(int selectedItem);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		int x=(int) arg1.getX();
		int y=(int) arg1.getY();
		if (isDeleteShown) {
			itemLayout.removeView(deleteButton);
			deleteButton = null;
			isDeleteShown = false;
			return false;
			}else{
				return gestureDetector.onTouchEvent(arg1);
			}
		
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if(!isDeleteShown){
			selectedItem=pointToPosition((int)arg0.getX(), (int)arg0.getY());
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if(!isDeleteShown&&Math.abs(arg2)>Math.abs(arg3)){
			deleteButton=LayoutInflater.from(getContext()).inflate(R.layout.delect, null);
			deleteButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					itemLayout.removeView(deleteButton);
					deleteButton=null;
					isDeleteShown=false;
					listener.onDelete(selectedItem);
				}
			});
			itemLayout=(ViewGroup) getChildAt(selectedItem-getFirstVisiblePosition());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					params.addRule(RelativeLayout.CENTER_VERTICAL);
					itemLayout.addView(deleteButton, params);
					isDeleteShown = true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
