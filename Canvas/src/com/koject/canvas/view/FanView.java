/* 
 * 自定义view的几个步骤： 
 * 1、首先需要写一个类来继承自View 
 * 2、需要得到view的对象，那么需要重写构造方法，其中一参的构造方法用于new，二参的构造方法用于xml布局文件使用，三参的构造方法可以传入一个样式 
 * 3、需要设置view的大小，那么需要重写onMeasure方法 
 * 4、需要设置view的位置，那么需要重写onLayout方法，但是这个方法在自定义view的时候用的不多，原因主要在于view的位置主要是由父控件来决定 
 * 5、需要绘制出所需要显示的view，那么需要重写onDraw方法 
 * 6、当控件状态改变的时候，需要重绘view，那么调用invalidate();方法，这个方法实际上会重新调用onDraw方法 
 * 7、在这其中，如果需要对view设置点击事件，可以直接调用setOnClickListener方法 
 */ 
package com.koject.canvas.view;

import com.koject.canvas.interf.OnSwitchFanListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.FontMetrics;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FanView extends View{
	
	// 画实心圆的画笔  
    private Paint mCirclePaint;  
    // 画圆环的画笔  
    private Paint mRingPaint;  
    // 画字体的画笔  
    private Paint mTextPaint;  
    // 圆形颜色  
    private int mCircleColor;  
    // 圆环颜色  
    private int mRingColor;  
    // 半径  
    private float mRadius;  
    // 圆环半径  
    private float mRingRadius;  
    // 圆环宽度,画笔的笔触宽度
    private float mStrokeWidth;  
    // 圆心x坐标  
    private int mXCenter;  
    // 圆心y坐标  
    private int mYCenter;  
    // 字的长度  
    private float mTxtWidth;  
    // 字的高度  
    private float mTxtHeight;  
    private float mRadius2;
    private float mStrokeWidth2;
    private float mRingRadius2;
    private Paint mRingPaint2;
    public int mRingColor2;
    //画矩形
    private Paint RectPaint;
    private float width;
    private float height;
    
    private float centuralAngle=-90;
    private Paint cenRingPaint;  
    private float startAngle=-90;
    private Context context;
    private RectF oval;
    private RectF oval2;
    private RectF oval3;
    private RectF oval4;
    private float mRadius3;
    private float mStrokeWidth3;
    private float mRingRadius3;
    private Paint mRingPaint3;
    public int mRingColor3;
    float startX;
	float startY;
	float stopX;
	float stopY;
	float startY2;
	float stopY2;
    //判断所在的区域
    private int flag=0;
    //属性
    public static final int SWITCH_OFF = 0;// 关闭状态  
	public static final int SWITCH_ON = 1;// 打开状态  
	private int mSwitchStatus = SWITCH_OFF;  
	/**判断是否有级**/
	private boolean mIsScrolled=true;
	/** 判断是否有正反*/
	private boolean mDirecEnabled = true;

	public static final int FANONE=1;
	public static final int FANTWO=2;
	public static final int FANTHREE=3;
	public static final int POSITIVE=4;//正向
	public static final int NEGATIVE=5;//反向
	//判断档位
	private int position=0;
	//监听接口
	OnSwitchFanListener switchListener=null;
	ShapeDrawable shapeDrawable=null;
	public FanView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
		initAttrs();
		initVariable();
//		shapeDrawable=new ShapeDrawable(context);
	}

	public FanView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		initAttrs();
		initVariable();
//		shapeDrawable=new ShapeDrawable(context);
	}



	public boolean ismIsScrolled() {
		return mIsScrolled;
	}

	public void setmIsScrolled(boolean mIsScrolled) {
		this.mIsScrolled = mIsScrolled;
	}

	public boolean ismDirecEnabled() {
		return mDirecEnabled;
	}

	public void setmDirecEnabled(boolean mDirecEnabled) {
		this.mDirecEnabled = mDirecEnabled;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
		if(switchListener!=null){
			switchListener.onPositionChange(context, mIsScrolled,mDirecEnabled,position);
		}
	}


	public int getmSwitchStatus() {
		return mSwitchStatus;
	}

	public void setmSwitchStatus(int mSwitchStatus) {
		this.mSwitchStatus = mSwitchStatus;
		if(switchListener!=null){
			switchListener.onSwitch(context, mSwitchStatus);
		}
		postInvalidate();
	}

	public float getCenturalAngle() {
		return centuralAngle;
	}

	public void setCenturalAngle(float centuralAngle) {
		this.centuralAngle = centuralAngle;
		postInvalidate();
	}
	/**
	 * 获取switch开关接口
	 * @return
	 */
	public OnSwitchFanListener getSwitchListener() {
		return switchListener;
	}

	/**
	 * 设置监听接口
	 * @param switchListener
	 */
	public void setSwitchListener(OnSwitchFanListener switchListener) {
		this.switchListener = switchListener;
	}

	private void initAttrs() {
		mStrokeWidth=100;
		mRadius=(500-50)/2-mStrokeWidth;
		
		mCircleColor=Color.WHITE;
		mRingColor=Color.GRAY;
		mRingRadius = mRadius + mStrokeWidth / 2;
		
		mRadius2=40;
		mStrokeWidth2=20;
		mRingRadius2=mRadius2+mStrokeWidth2/2;
		
		width=10;
		height=35;
		
		mStrokeWidth3=40;
		mRadius3=mRadius-mStrokeWidth3;
		mRingRadius3=mRadius3+mStrokeWidth3/2;
		mRingColor3=Color.TRANSPARENT;
		
		
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
		//画圆
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(mCircleColor);
		mCirclePaint.setStyle(Paint.Style.FILL);
		//画圆环
		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
//		mRingPaint.setColor(mRingColor);
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mStrokeWidth);
		//画text
		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setColor(Color.BLACK);
		mTextPaint.setTextSize(mRadius / 2);
		
		FontMetrics fm = mTextPaint.getFontMetrics();
		mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
		
		mRingPaint2=new Paint();
		mRingPaint2.setAntiAlias(true);
//		mRingPaint2.setColor(mRingColor2);
		mRingPaint2.setStyle(Paint.Style.STROKE);
		mRingPaint2.setStrokeWidth(mStrokeWidth2);
		
		mRingPaint3=new Paint();
		mRingPaint3.setAntiAlias(true);
		mRingPaint3.setColor(mRingColor3);
		mRingPaint3.setStyle(Paint.Style.STROKE);
		mRingPaint3.setStrokeWidth(mStrokeWidth3);
		
		
		//矩形
		RectPaint=new Paint();
		RectPaint.setAntiAlias(true);
//		RectPaint.setColor(mRingColor2);
		
		cenRingPaint = new Paint();
		cenRingPaint.setAntiAlias(true);
		cenRingPaint.setStyle(Paint.Style.STROKE);
		cenRingPaint.setStrokeWidth(mStrokeWidth);

		

		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
			mXCenter = getWidth() / 2;
			mYCenter = getHeight()/2;
			onDrawArc(canvas);
			onDrawLine(canvas);
			canvas.drawArc(oval, startAngle, centuralAngle-startAngle, false, cenRingPaint);
			
//			shapeDrawable.DrawShape(canvas);
			
			}
	//初始化
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
			
			mRingColor=Color.rgb(192, 192, 192);
			mRingPaint.setColor(mRingColor);
			canvas.drawArc(oval, -90, 360, false, mRingPaint);
			mRingPaint2.setColor(mRingColor2);
			canvas.drawArc(oval2, -45, 270, false, mRingPaint2);
			RectPaint.setColor(mRingColor2);
			canvas.drawRoundRect(oval3, 10, 10, RectPaint);
			
			canvas.drawArc(oval4, -90, 360, false, mRingPaint3);
	}
	private void onDrawLine(Canvas canvas){
		Paint paint=new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		float startX=(float) (mRadius*Math.cos(Math.PI/6))+mXCenter;
		float startY=mYCenter-(float) (mRadius*Math.sin(Math.PI/6));
		float stopX=(float) (mXCenter+(mRadius+mStrokeWidth)*Math.cos(Math.PI/6));
		float stopY=(float) (mYCenter-(mRadius+mStrokeWidth)*Math.sin(Math.PI/6));
		float startY2=mYCenter+(float) (mRadius*Math.sin(Math.PI/6));
		float stopY2=(float) (mYCenter+(mRadius+mStrokeWidth)*Math.sin(Math.PI/6));
		canvas.drawLine(mXCenter-mRadius, mYCenter, mXCenter-mRadius-mStrokeWidth, mYCenter, paint);
		canvas.drawLine(mXCenter, mYCenter-mRadius, mXCenter, mYCenter-mRadius-mStrokeWidth, paint);
		canvas.drawLine(mXCenter, mYCenter+mRadius, mXCenter, mYCenter+mRadius+mStrokeWidth, paint);
		if(mIsScrolled){
			canvas.drawLine(startX, startY, stopX, stopY, paint);
			canvas.drawLine(startX, startY2, stopX, stopY2, paint);
		}
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		//获取点击屏幕的点的坐标
		float x= event.getX();
		float y=event.getY();
		whichCircle(x, y);
		float jiaodu=(float) getTangle(x, y);
//		Log.i("jiaodu", jiaodu+"");
		
		switch (flag) {
		case 0:
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if(mSwitchStatus==SWITCH_OFF){
					mSwitchStatus=SWITCH_ON;
					setmSwitchStatus(mSwitchStatus);
					
				}
				else {
					mSwitchStatus=SWITCH_OFF;
					setmSwitchStatus(mSwitchStatus);
				}
				
//				Log.i("MotionEvent", "MotionEvent");
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
			if(mIsScrolled){
			if(mSwitchStatus==SCREEN_STATE_ON){
				if(-90<jiaodu&&jiaodu<=-30){
				startAngle=-90;
				centuralAngle=-30;
				cenRingPaint.setColor(Color.rgb(130, 198, 226));
				setPosition(FANONE);
				break;
				}
				if(-30<jiaodu&&jiaodu<=30){
					startAngle=-30;
					centuralAngle=30;
					cenRingPaint.setColor(Color.rgb(81, 181, 224));
					setPosition(FANTWO);
					break;
				}
				if(jiaodu>30&&jiaodu<=90){
					startAngle=30;
					centuralAngle=90;
					cenRingPaint.setColor(Color.rgb(0, 163, 221));
					setPosition(FANTHREE);
					break;
				}
				postInvalidate();
			}else if (mSwitchStatus==SCREEN_STATE_OFF) {
				if(-90<jiaodu&&jiaodu<=-30){
				startAngle=-90;
				centuralAngle=-30;
				cenRingPaint.setColor(Color.rgb(130, 198, 226));
				mSwitchStatus=SCREEN_STATE_ON;
				setPosition(FANONE);
				break;
				}
				if(-30<jiaodu&&jiaodu<=30){
					startAngle=-30;
					centuralAngle=30;
					cenRingPaint.setColor(Color.rgb(81, 181, 224));
					mSwitchStatus=SCREEN_STATE_ON;
					setPosition(FANTWO);
					break;
				}
				if(jiaodu>30&&jiaodu<=90){
					startAngle=30;
					centuralAngle=90;
					cenRingPaint.setColor(Color.rgb(0, 163, 221));
					mSwitchStatus=SCREEN_STATE_ON;
					setPosition(FANTHREE);
					break;
				}
				if(jiaodu>90&&jiaodu<=180){
					startAngle=90;
					centuralAngle=180;
					cenRingPaint.setColor(Color.rgb(0, 142, 214));
					setPosition(POSITIVE);
					break;
				}
				if(jiaodu>180&&jiaodu<=270){
					startAngle=180;
					centuralAngle=270;
					cenRingPaint.setColor(Color.rgb(0, 142, 214));
					setPosition(NEGATIVE);
					break;
				}
				postInvalidate();
				
			
			}
			}else if (mDirecEnabled) {
			if(mSwitchStatus==SCREEN_STATE_OFF){
				if(jiaodu>90&&jiaodu<=180){
					startAngle=90;
					centuralAngle=180;
					cenRingPaint.setColor(Color.rgb(0, 142, 214));
					setPosition(POSITIVE);
					break;
				}
				if(jiaodu>180&&jiaodu<=270){
					startAngle=180;
					centuralAngle=270;
					cenRingPaint.setColor(Color.rgb(0, 142, 214));
					setPosition(NEGATIVE);
					break;
				}
				if(jiaodu>=-90&&jiaodu<=90){
					mSwitchStatus=SCREEN_STATE_ON;
					setmSwitchStatus(mSwitchStatus);
					startAngle=-90;
					centuralAngle=jiaodu;
					cenRingPaint.setColor(Color.rgb(81, 181, 224));
					position=(int) ((centuralAngle-startAngle)/180*90);
					setPosition(position);
					break;
				}
				
			}else {
				if(jiaodu>=-90&&jiaodu<=90){
					startAngle=-90;
					centuralAngle=jiaodu;
					cenRingPaint.setColor(Color.rgb(81, 181, 224));
					position=(int) ((centuralAngle-startAngle)/180*90);
					setPosition(position);
					break;
				}
			}
				postInvalidate();
			}else if (!mDirecEnabled) {
				if(mSwitchStatus==SCREEN_STATE_ON){
					if(-90<jiaodu&&jiaodu<=90){
						startAngle=-90;
						centuralAngle=jiaodu;
						cenRingPaint.setColor(Color.rgb(81, 181, 224));
						position=(int) ((centuralAngle-startAngle)/180*90);
						setPosition(position);
						break;
					}
				}
				postInvalidate();
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
     * 确定点击的点在哪个圆内  
     * @param x  
     * @param y  
     */  
    private void whichCircle(float x, float y) {   
        // 将屏幕中的点转换成以屏幕中心为原点的坐标点   
        float mx = x -mXCenter ;   
        float my = y - mYCenter;   
        float result = mx * mx + my * my;             
        StringBuilder tip = new StringBuilder();   
        tip.append("您点击了");   
        tip.append(mx+"mx");
        tip.append(my+"my");
        // 高中的解析几何   
        if(result <=mRadius2*mRadius2 ) {// 点击的点在小圆内   
            tip.append("小圆的");   
            tip.append("on/off");   
            tip.append("区域");
            flag=0;
//            postInvalidate();
        } else if(result <= mRadius*mRadius) {// 点击的点在大圆内   
            tip.append("大圆的");   
            tip.append("空白");   
            tip.append("区域"); 
            flag=1;
           
        }else if(result<=(mRadius+mRingRadius)*(mRadius+mRingRadius)) {  
            tip.append("圆环区域内");   
            tip.append("区域");  
            flag=2;
		}
        else {// 点不在作作区域   
            tip.append("作用区域以外的区域");
            flag=3;
        }   
//        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show(); 
        
    }   
    private double getTangle(float x,float y){
	
    	float downx = x -mXCenter ;   
        float downy = y - mYCenter;   

        //取绝对值
        float tanx=Math.abs(downx);
        float tany=Math.abs(downy);
        double angle=0.0;
        double c=((double)tany/(double)tanx);
//    	Log.i("c", c+"");
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
        int width = widthSize;  
        int height = (int) ((mRadius+mStrokeWidth)*2); 
       
        setMeasuredDimension(width, height);
    }
 // 这个方法对于自定义view的时候帮助不大，因为view的位置一般由父组件来决定的
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
    		int bottom) {
    	// TODO Auto-generated method stub
    	super.onLayout(changed, left, top, right, bottom);
    }
}

