package com.koject.canvas.view;

import com.koject.canvas.interf.OnSwitchFLListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

public class FLView extends View implements Runnable{

	private int mwidth;//���
	private int mheight;//�߶�
	
    // ��Բ���Ļ���  
    private Paint mRingPaint;  
    // �뾶  
    private float mRadius;  
    // Բ���뾶  
    private float mRingRadius;  
    // Բ�����,���ʵıʴ����
    private float mStrokeWidth;
    //��ɫ
    private int mRingColor;
    // Բ��x����  
    private int mXCenter;  
    // Բ��y����  
    private int mYCenter;
    //СԲ�İ뾶
    private float mRadius2;
    //Բ���Ŀ��
    private float mStrokeWidth2;
    //Բ���İ뾶
    private float mRingRadius2;
    //СԲ���Ļ���
    private Paint mRingPaint2;
    //Բ����ɫ
    private int mRingColor2;
    //������
    private Paint RectPaint;
    private float width;//���εĿ�
    private float height;//���εĸ�
    //��Ǵ����Ĳ�λ
    int flag=0;
    private Context context;
    private RectF oval;
    private RectF oval2;
    private RectF oval3;
    private RectF oval4;
    //Բ�İ뾶
    private float mRadius3;
    //Բ�����
    private float mStrokeWidth3;
    //Բ���İ뾶
    private float mRingRadius3;
    //Բ������ɫ
    private int mRingColor3;
    /**����**/
    private Paint mRingPaint3;
   //��ǰ�ĽǶ�
    private float centuralAngle=-90,centuralAngle2=90;
    //Բ�����ػ���
    private Paint cenRingPaint,cenRingPaint2;  
    //��ʼ�Ƕ�
    private float startAngle=-90,startAngle2=90;
    //�ߵ���ɫ
    private int lineColor;
    /**���ص�2��״̬**/
    public static final int SWITCH_OFF = 0;// �ر�״̬  
	public static final int SWITCH_ON = 1;// ��״̬  
	/**��ǰ�Ŀ��ص�״̬**/
	private int mSwitchStatus = SWITCH_OFF;
	/**���ֵ**/
	private int maxleft=90; 
	private int maxright=90;
	/**��ǰǿ��**/
	private int strengthL;
	private int strengthR;
	/**�ж��Ƿ��м�**/
	private boolean mIsScrolled=false;
	/** �ж��Ƿ�������*/
	private boolean mDirecEnabled = true;

	/**�м���5��״̬**/
	public static final int FANONE=1;
	public static final int FANTWO=2;
	public static final int FANTHREE=3;
	public static final int POSITIVE=4;//����
	public static final int NEGATIVE=5;//����
	/**�����ӿ�**/
	private OnSwitchFLListener switchListener=null;
	private int b=0;
	
	private int oneColor;
	private int twoColor;
	private int threeColor;
	private int fourColor;
	private int fiveColor;
	private int leftColor;
	private int rightColor;
	public FLView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		initAttrs();
		initVariable();
		new Thread(this).start();
	}

	public FLView(Context context) {
		super(context);
		this.context=context;
		initAttrs();
		initVariable();
		new Thread(this).start();
		
	}
	private void initAttrs() {
		
		mStrokeWidth=100;
		mRadius=(500-50)/2-mStrokeWidth;
		mRingRadius = mRadius + mStrokeWidth / 2;
		mRingColor=Color.rgb(192, 192, 192);
		mRadius2=40;
		mStrokeWidth2=20;
		mRingRadius2=mRadius2+mStrokeWidth2/2;
		width=10;
		height=35;
		
		mStrokeWidth3=40;
		mRadius3=mRadius-mStrokeWidth3;
		mRingRadius3=mRadius3+mStrokeWidth3/2;
		mRingColor3=Color.TRANSPARENT;
		
		lineColor=Color.WHITE;
		
		oneColor=Color.rgb(130, 198, 226);
		twoColor=Color.rgb(81, 181, 224);
		threeColor=Color.rgb(0, 163, 221);
		fourColor=Color.rgb(0, 142, 214);
		fiveColor=Color.rgb(0, 142, 214);
		leftColor=Color.WHITE;
		rightColor=Color.YELLOW;
		
	}
	private void changColor(){
		switch (mSwitchStatus) {
		case SWITCH_OFF:
			mRingColor2=Color.RED;
			break;
		case SWITCH_ON:
			mRingColor2=Color.BLUE;
			break;
		default:
			break;
		}
	}
	private void initVariable() {
		//��Բ��
		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mStrokeWidth);
		//����ťԲ��
		mRingPaint2=new Paint();
		mRingPaint2.setAntiAlias(true);
		mRingPaint2.setStyle(Paint.Style.STROKE);
		mRingPaint2.setStrokeWidth(mStrokeWidth2);
		//��СԲ��
		mRingPaint3=new Paint();
		mRingPaint3.setAntiAlias(true);
		mRingPaint3.setColor(mRingColor3);
		mRingPaint3.setStyle(Paint.Style.STROKE);
		mRingPaint3.setStrokeWidth(mStrokeWidth3);
				
		//����
		RectPaint=new Paint();
		RectPaint.setAntiAlias(true);
		//�ػ�Բ��
		cenRingPaint = new Paint();
		cenRingPaint.setAntiAlias(true);
		cenRingPaint.setStyle(Paint.Style.STROKE);
		cenRingPaint.setStrokeWidth(mStrokeWidth);
		//�ػ�Բ��
		cenRingPaint2 = new Paint();
		cenRingPaint2.setAntiAlias(true);
		cenRingPaint2.setStyle(Paint.Style.STROKE);
		cenRingPaint2.setStrokeWidth(mStrokeWidth);
		
		
	}
	
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mXCenter=getWidth()/2;
		mYCenter=getHeight()/2;
		onDrawArc(canvas);
		onDrawLine(canvas);
		canvas.drawArc(oval, startAngle, centuralAngle-startAngle, false, cenRingPaint);
		canvas.drawArc(oval, startAngle2, centuralAngle2-startAngle2, false, cenRingPaint2);
		
	}
	//��Բ��
	private void onDrawArc(Canvas canvas){
		oval = new RectF();
		oval.left = (mXCenter - mRingRadius);
		oval.top = (mYCenter - mRingRadius);
		oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
		oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
		
		oval2=new RectF();
		oval2.left=mXCenter-mRingRadius2;
		oval2.top=mYCenter-mRingRadius2;
		oval2.right=mXCenter+mRingRadius2;
		oval2.bottom=mYCenter+mRingRadius2;
		
		oval3=new RectF();
		oval3.left=mXCenter-width;
		oval3.top=mYCenter-height*2;
		oval3.right=mXCenter+width;
		oval3.bottom=mYCenter;
		
		oval4=new RectF(mXCenter-mRingRadius3, mYCenter-mRingRadius3, mXCenter+mRingRadius3, mYCenter+mRingRadius3);
			changColor();
			mRingPaint.setColor(mRingColor);
			canvas.drawArc(oval, -90, 360, false, mRingPaint);
			mRingPaint2.setColor(mRingColor2);
			canvas.drawArc(oval2, -45, 270, false, mRingPaint2);
			RectPaint.setColor(mRingColor2);
			canvas.drawRoundRect(oval3, 10, 10, RectPaint);
			canvas.drawArc(oval4, -90, 360, false, mRingPaint3);
	}
	//����
	private void onDrawLine(Canvas canvas){
		Paint paint=new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(lineColor);
		paint.setAntiAlias(true);
		canvas.drawLine(mXCenter, mYCenter-mRadius, mXCenter, mYCenter-mRadius-mStrokeWidth, paint);
		canvas.drawLine(mXCenter, mYCenter+mRadius, mXCenter, mYCenter+mRadius+mStrokeWidth, paint);
		float startX=(float) (mRadius*Math.cos(Math.PI/6))+mXCenter;
		float startY=mYCenter-(float) (mRadius*Math.sin(Math.PI/6));
		float stopX=(float) (mXCenter+(mRadius+mStrokeWidth)*Math.cos(Math.PI/6));
		float stopY=(float) (mYCenter-(mRadius+mStrokeWidth)*Math.sin(Math.PI/6));
		float startY2=mYCenter+(float) (mRadius*Math.sin(Math.PI/6));
		float stopY2=(float) (mYCenter+(mRadius+mStrokeWidth)*Math.sin(Math.PI/6));
		if(mIsScrolled&&mDirecEnabled){
			canvas.drawLine(mXCenter-mRadius, mYCenter, mXCenter-mRadius-mStrokeWidth, mYCenter, paint);
			canvas.drawLine(startX, startY, stopX, stopY, paint);
			canvas.drawLine(startX, startY2, stopX, stopY2, paint);
		}else if(mIsScrolled&&!mDirecEnabled){
			canvas.drawLine(startX, startY, stopX, stopY, paint);
			canvas.drawLine(startX, startY2, stopX, stopY2, paint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//��ȡ�����Ļ�ĵ������
		float x= event.getX();
		float y=event.getY();
		whichCircle(x, y);
		float jiaodu=(float) getTangle(x, y);
		Log.i("jiaodu", jiaodu+"");
		switch (flag) {
		case 0:
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if(mIsScrolled){
					if(mSwitchStatus==SWITCH_OFF){
						mSwitchStatus=SWITCH_ON;
						setmSwitchStatus(mSwitchStatus);
						
					}
					else {
						mSwitchStatus=SWITCH_OFF;
						setmSwitchStatus(mSwitchStatus);
						setStrengthR(0);
						setStrengthL(0);
						cenRingPaint.setColor(mRingColor);
						cenRingPaint2.setColor(mRingColor);
						mRingPaint3.setColor(mRingColor3);
					}
				
				}else {
					if(mSwitchStatus==SWITCH_OFF){
						mSwitchStatus=SWITCH_ON;
						setmSwitchStatus(mSwitchStatus);
						
					}
					else {
						mSwitchStatus=SWITCH_OFF;
						setmSwitchStatus(mSwitchStatus);
						setStrengthL(0);
						setStrengthR(0);
						cenRingPaint.setColor(mRingColor);
						cenRingPaint2.setColor(mRingColor);
						mRingPaint3.setColor(mRingColor3);
					}
				}
				postInvalidate();
				Log.i("MotionEvent", "MotionEvent");
				break;
			case MotionEvent.ACTION_UP:
				break;
			default:
				break;
			}
			break;

		case 1:
			
			break;
		case 2:
			//�м�
			if(mIsScrolled){
				if(mSwitchStatus==SCREEN_STATE_ON){
					if(-90<jiaodu&&jiaodu<=-30){
					startAngle=-90;
					centuralAngle=-30;
					cenRingPaint.setColor(oneColor);
					setStrengthR(FANONE);
					mRingPaint3.setColor(oneColor);
					break;
					}
					if(-30<jiaodu&&jiaodu<=30){
						startAngle=-30;
						centuralAngle=30;
						cenRingPaint.setColor(twoColor);
						setStrengthR(FANTWO);
						mRingPaint3.setColor(twoColor);
						break;
					}
					if(jiaodu>30&&jiaodu<=90){
						startAngle=30;
						centuralAngle=90;
						cenRingPaint.setColor(threeColor);
						setStrengthR(FANTHREE);
						mRingPaint3.setColor(threeColor);
						break;
					}
					
					postInvalidate();
				}else if (mSwitchStatus==SCREEN_STATE_OFF) {
					if(-90<jiaodu&&jiaodu<=-30){
					startAngle=-90;
					centuralAngle=-30;
					cenRingPaint.setColor(oneColor);
					mSwitchStatus=SCREEN_STATE_ON;
					setStrengthR(FANONE);
					mRingPaint3.setColor(oneColor);
					break;
					}
					if(-30<jiaodu&&jiaodu<=30){
						startAngle=-30;
						centuralAngle=30;
						cenRingPaint.setColor(twoColor);
						mSwitchStatus=SCREEN_STATE_ON;
						setStrengthR(FANTWO);
						mRingPaint3.setColor(twoColor);
						break;
					}
					if(jiaodu>30&&jiaodu<=90){
						startAngle=30;
						centuralAngle=90;
						cenRingPaint.setColor(threeColor);
						mSwitchStatus=SCREEN_STATE_ON;
						setStrengthR(FANTHREE);
						mRingPaint3.setColor(threeColor);
						break;
					}
					if(!mDirecEnabled){
						if(jiaodu>90&&jiaodu<=270){
							startAngle2=90;
							centuralAngle2=270;
							cenRingPaint2.setColor(fourColor);
							setStrengthL(POSITIVE);
						}
					}else {
						if(jiaodu>90&&jiaodu<=180){
							
							startAngle2=90;
							centuralAngle2=180;
							cenRingPaint2.setColor(fourColor);
							setStrengthL(POSITIVE);
							break;
						}
						if(jiaodu>180&&jiaodu<=270){
							
							startAngle2=180;
							centuralAngle2=270;
							cenRingPaint2.setColor(fiveColor);
							setStrengthL(NEGATIVE);
							break;
						}
						
					}
					postInvalidate();
					
				
				}
			}
			else {
				if(mSwitchStatus==SCREEN_STATE_ON){
					if(-90<jiaodu&&jiaodu<=90){
					centuralAngle=jiaodu;
					strengthR=(int) ((centuralAngle-startAngle)/180*maxright);
					setStrengthR(strengthR);
					b=255-(int) ((centuralAngle-startAngle)/180*255);
					cenRingPaint.setColor(rightColor);
					mRingPaint3.setColor(Color.rgb(255, 255, b));
					break;
					}
					if(jiaodu>90&&jiaodu<=270){
						centuralAngle2=jiaodu;
						strengthL = (int) ((centuralAngle2-startAngle2)/180*maxleft);
						setStrengthL(strengthL);
						b=(int) ((centuralAngle2-startAngle2)/180*255);
						cenRingPaint2.setColor(leftColor);
						mRingPaint3.setColor(Color.rgb(255, 255, b));
						break;
					}
					postInvalidate();
				}else if (mSwitchStatus==SCREEN_STATE_OFF) {
//					mRingColor=Color.GRAY;
//					postInvalidate();
				}
			}
			break;
		case 3:
			break;
		default:
			break;
		}
		
		
		return true;
	}
	/**  
     * ȷ������ĵ����ĸ�Բ��  
     * @param x  
     * @param y  
     */  
    private void whichCircle(float x, float y) {   
        // ����Ļ�еĵ�ת��������Ļ����Ϊԭ��������   
        float mx = x -mXCenter ;   
        float my = y - mYCenter;   
        float result = mx * mx + my * my;             
        StringBuilder tip = new StringBuilder();   
        tip.append("�������");   
        tip.append(mx+"mx");
        tip.append(my+"my");
        // ���еĽ�������   
        if(result <=mRadius2*mRadius2 ) {// ����ĵ���СԲ��   
            tip.append("СԲ��");   
            tip.append("on/off");   
            tip.append("����");
            flag=0;
//            postInvalidate();
        } else if(result <= mRadius*mRadius) {// ����ĵ��ڴ�Բ��   
            tip.append("��Բ��");   
            tip.append("�հ�");   
            tip.append("����"); 
            flag=1;
           
        }else if(result<=(mRadius+mRingRadius)*(mRadius+mRingRadius)) {  
            tip.append("Բ��������");   
            tip.append("����");  
            flag=2;
		}
        else {// �㲻����������   
            tip.append("�����������������");
            flag=3;
        }   
//        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show(); 
        
    }   
    private double getTangle(float x,float y){
	
    	float downx = x -mXCenter ;   
        float downy = y - mYCenter;   

        //ȡ����ֵ
        float tanx=Math.abs(downx);
        float tany=Math.abs(downy);
        double angle=0.0;
        double c=((double)tany/(double)tanx);
    	Log.i("c", c+"");
    	if(downx>0&&downy>0){
//    		double c=(double)y/(double)x;
    		angle=Math.toDegrees(Math.atan(c));
    	}else if(downx<=0&&downy>0){
			
			angle=180-Math.toDegrees(Math.atan(c));
		}else if (downx>0&&downy<=0) {
			angle=-Math.toDegrees(Math.atan(c));
		}else {
			angle=180+Math.toDegrees(Math.atan(c));
		}
    	return angle;
    	
    }
	@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	    	int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
	    	int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
	         mwidth = widthSize;  
	         mheight = (int) (mRadius+mStrokeWidth)*2; 
	  
	        setMeasuredDimension(mwidth, mheight);
	    }
	 // ������������Զ���view��ʱ�����������Ϊview��λ��һ���ɸ������������
	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right,
	    		int bottom) {
	    	// TODO Auto-generated method stub
	    	super.onLayout(changed, left, top, right, bottom);
	    }

	    public int getOneColor() {
			return oneColor;
		}

		public void setOneColor(int oneColor) {
			this.oneColor = oneColor;
		}

		public int getTwoColor() {
			return twoColor;
		}

		public void setTwoColor(int twoColor) {
			this.twoColor = twoColor;
		}

		public int getThreeColor() {
			return threeColor;
		}

		public void setThreeColor(int threeColor) {
			this.threeColor = threeColor;
		}

		public int getFourColor() {
			return fourColor;
		}

		public void setFourColor(int fourColor) {
			this.fourColor = fourColor;
		}

		public int getFiveColor() {
			return fiveColor;
		}

		public void setFiveColor(int fiveColor) {
			this.fiveColor = fiveColor;
		}

		public int getLeftColor() {
			return leftColor;
		}

		public void setLeftColor(int leftColor) {
			this.leftColor = leftColor;
		}

		public int getRightColor() {
			return rightColor;
		}

		public void setRightColor(int rightColor) {
			this.rightColor = rightColor;
		}

		public float getmStrokeWidth() {
			return mStrokeWidth;
		}

		public void setmStrokeWidth(float mStrokeWidth) {
			this.mStrokeWidth = mStrokeWidth;
		}

		public float getmStrokeWidth2() {
			return mStrokeWidth2;
		}

		public void setmStrokeWidth2(float mStrokeWidth2) {
			this.mStrokeWidth2 = mStrokeWidth2;
		}

		public float getmStrokeWidth3() {
			return mStrokeWidth3;
		}

		public void setmStrokeWidth3(float mStrokeWidth3) {
			this.mStrokeWidth3 = mStrokeWidth3;
		}

		public int getLineColor() {
			return lineColor;
		}

		public void setLineColor(int lineColor) {
			this.lineColor = lineColor;
		}

		
		public int getmRingColor() {
			return mRingColor;
		}

		public void setmRingColor(int mRingColor) {
			this.mRingColor = mRingColor;
		}

		public int getmRingColor3() {
			return mRingColor3;
		}

		public void setmRingColor3(int mRingColor3) {
			this.mRingColor3 = mRingColor3;
		}

		public int getMaxleft() {
			return maxleft;
		}

		public void setMaxleft(int maxleft) {
			this.maxleft = maxleft;
		}

		public int getMaxright() {
			return maxright;
		}

		public void setMaxright(int maxright) {
			this.maxright = maxright;
		}

		public boolean ismDirecEnabled() {
			return mDirecEnabled;
		}

		public void setmDirecEnabled(boolean mDirecEnabled) {
			this.mDirecEnabled = mDirecEnabled;
		}

		public int getmSwitchStatus() {
			return mSwitchStatus;
		}

		public void setmSwitchStatus(int mSwitchStatus) {
			this.mSwitchStatus = mSwitchStatus;
			if(switchListener!=null){
				switchListener.onSwitch(context, mSwitchStatus,strengthR,strengthL);
			}
		}

		public int getStrengthL() {
			return strengthL;
		}

		public void setStrengthL(int strengthL) {
			this.strengthL = strengthL;
			if(switchListener!=null){
				switchListener.onSwitch(context, mSwitchStatus, strengthR, strengthL);
				switchListener.onStrengthChange(context, strengthR, strengthL,mIsScrolled,mDirecEnabled);
			}
		}

		public int getStrengthR() {
			return strengthR;
		}

		public void setStrengthR(int strengthR) {
			this.strengthR = strengthR;
			if(switchListener!=null){
				switchListener.onSwitch(context, mSwitchStatus, strengthR, strengthL);
				switchListener.onStrengthChange(context, strengthR, strengthL,mIsScrolled,mDirecEnabled);
			}
		}

		public boolean ismIsScrolled() {
			return mIsScrolled;
		}

		public void setmIsScrolled(boolean mIsScrolled) {
			this.mIsScrolled = mIsScrolled;
		}

		public OnSwitchFLListener getSwitchListener() {
			return switchListener;
		}

		public void setSwitchListener(OnSwitchFLListener switchListener) {
			this.switchListener = switchListener;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!Thread.currentThread().isInterrupted()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
				
				postInvalidate();
			}
			
		}
		
		
}
