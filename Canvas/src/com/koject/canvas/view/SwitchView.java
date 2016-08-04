package com.koject.canvas.view;


import com.koject.canvas.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwitchView extends View{

	//��ָ����ʱ��ʽ
	private Bitmap mSwitchThumbPressed ;
	/* �޲�����ʽ*/
	private Bitmap mSwitchThumbNormal ;
	//�ײ���ʽ
	private Bitmap mSwitchBottom ;
	//���
	private Bitmap mSwitchFrame;
	
	private Bitmap mSwitchMask ;
	//��ǰ��ʽ
	private Bitmap mSwitchThumb ;
	//����ƶ���
	private int mMoveLength ;
	//�ж��Ƿ��϶�
	private boolean mIsScrolled=false;
	//switch���ص�״̬
	private boolean mSwitchOn=true;
	//switch��ƫ����
	private int mMoveDeltX=0;
	private Rect mDest ;
	/** ��ȡԴͼƬ�Ĵ�С  */
	private Rect mSrc;
	/** ���͸���ȣ����ǲ�͸�� */
	private final int MAX_ALPHA = 255;
	/** ��ǰ͸���ȣ�������Ҫ��������ؼ���enable����Ϊfalseʱ�����ð�͸�� ���������Ե�� */
	private int mAlpha = MAX_ALPHA;
	private Paint mPaint;
	/** enabled ���� Ϊ true */
	private boolean mEnabled = true;
	/*��һ�ΰ��µ���Ч����*/
	private float mLastX;
	/** Switch ״̬�����ӿ�  */
	private OnSwitchChangedListener switchListener = null;
	private boolean mFlag=false;
	public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SwitchView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init(){
		 mSwitchThumbPressed = BitmapFactory.decodeResource(getResources(),R.drawable.checkswitch_btn_pressed);
		 mSwitchThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.checkswitch_btn_unpressed);
		 mSwitchBottom = BitmapFactory.decodeResource(getResources(), R.drawable.checkswitch_bottom);
		 mSwitchFrame = BitmapFactory.decodeResource(getResources(), R.drawable.checkswitch_frame);
		 mSwitchMask = BitmapFactory.decodeResource(getResources(), R.drawable.checkswitch_mask);
		 mSwitchThumb = mSwitchThumbNormal;
		mMoveLength = mSwitchBottom.getWidth()-mSwitchFrame.getWidth();
		//�����С
		 mDest = new Rect(0, 0, mSwitchFrame.getWidth(), mSwitchFrame.getHeight());
		 mSrc=new Rect();
		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setAlpha(255);
		mPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(mSwitchFrame.getWidth(), mSwitchFrame.getHeight());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(mMoveDeltX>0||mMoveDeltX==0&& mSwitchOn){
			if(mSrc!=null){
				mSrc.set(mMoveLength-mMoveDeltX,0,mSwitchBottom.getWidth()-mMoveDeltX,mSwitchFrame.getHeight());
				
			}else if (mMoveDeltX<0||mMoveDeltX==0&&!mSwitchOn) {
				if(mSrc!=null){
					mSrc.set(-mMoveDeltX, 0, mSwitchFrame.getWidth()-mMoveDeltX, mSwitchFrame.getHeight());
				}
			}
		}
		Log.i("mAlpha","mAlpha:"+ mAlpha);
		canvas.saveLayerAlpha(new RectF(mDest), mAlpha, Canvas.MATRIX_SAVE_FLAG|
				Canvas.CLIP_SAVE_FLAG|Canvas.HAS_ALPHA_LAYER_SAVE_FLAG|Canvas.FULL_COLOR_LAYER_SAVE_FLAG|
				Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		canvas.drawBitmap(mSwitchBottom, mSrc, mDest,null);
		canvas.drawBitmap(mSwitchThumb, mSrc, mDest, null);
		canvas.drawBitmap(mSwitchFrame, 0, 0, null);
		canvas.drawBitmap(mSwitchMask, 0, 0, mPaint);
		canvas.restore();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(!mEnabled){
			return true;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mSwitchThumb=mSwitchThumbPressed;
			mLastX = event.getX();
			break;

		case MotionEvent.ACTION_MOVE:
			float mCurrentX = event.getX();
			mMoveDeltX=(int) (mCurrentX-mLastX);
			if(mMoveDeltX>10){
				mIsScrolled = true;
			}
			//С��0����
			if((mSwitchOn&&mMoveDeltX<0)||(!mSwitchOn&&mMoveDeltX>0)){
				mFlag = true;
				mMoveDeltX = 0;
			}
			if(Math.abs(mMoveDeltX)>mMoveLength){
				mMoveDeltX=mMoveDeltX>0 ? mMoveLength : -mMoveLength;
			}
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			mSwitchThumb=mSwitchThumbNormal;
			if(!mIsScrolled){
				mMoveDeltX=mSwitchOn ? mMoveLength : -mMoveLength;
				mSwitchOn=!mSwitchOn;
				if(switchListener!=null){
					switchListener.onSwitchChange(this, mSwitchOn);
				}
				invalidate();
				mMoveDeltX=0;
				break;
			}
			mIsScrolled=false;
			if(Math.abs(mMoveDeltX)>0&&Math.abs(mMoveDeltX)<mMoveLength/2){
				mMoveDeltX=0;
				invalidate();
			}else if (Math.abs(mMoveDeltX)>mMoveLength/2&&Math.abs(mMoveDeltX)<=mMoveLength) {
				mMoveDeltX=mMoveDeltX>0?mMoveLength: -mMoveLength;
				mSwitchOn=!mSwitchOn;
				if(switchListener!=null){
					switchListener.onSwitchChange(this, mSwitchOn);
				}
				invalidate();
				mMoveDeltX=0;
				
			}else if (mMoveDeltX==0&&mFlag) {
				mMoveDeltX=0;
				mFlag=false;
			}
			break;
		default:
			break;
		}
		postInvalidate();
		return super.onTouchEvent(event);
	}
	/**
	 * ��ȡswitch���ؽӿ�
	 * @return
	 */
	public OnSwitchChangedListener getSwitchListener() {
		return switchListener;
	}

	/**
	 * ���ü����ӿ�
	 * @param switchListener
	 */
	public void setSwitchListener(OnSwitchChangedListener switchListener) {
		this.switchListener = switchListener;
	}

	public interface OnSwitchChangedListener{
		public void onSwitchChange(SwitchView switchView, boolean isChecked);
	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		mEnabled=enabled;
		mAlpha=enabled?MAX_ALPHA:MAX_ALPHA/2;
		super.setEnabled(enabled);
		invalidate();
	}

	public boolean ismSwitchOn() {
		return mSwitchOn;
	}

	public void setmSwitchOn(boolean mSwitchOn) {
		this.mSwitchOn = mSwitchOn;
		invalidate();
	}
	public void toggle(){
		setmSwitchOn(!mSwitchOn);
	}
	
	
}
