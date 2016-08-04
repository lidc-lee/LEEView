package net.kojec.uicontrols.view;


import net.kojec.uicontrols.interf.OnSwitchFLListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
public class SylphCircleSlider extends View implements Runnable{

	private int mwidth;//宽度
	private int mheight;//高度
	
    // 画圆环的画笔  
    private Paint mRingPaint;  
    // 半径  
    private float mRadius;  
    // 圆环半径  
    private float mRingRadius;  
    // 圆环宽度,画笔的笔触宽度
    private float mStrokeWidth;
    //颜色
    private int mRingColor;
    // 圆心x坐标  
    private int mXCenter;  
    // 圆心y坐标  
    private int mYCenter;
    //小圆的半径
    private float mRadiusin;
    //圆环的宽度
    private float mStrokeWidthin;
    //圆环的半径
    private float mRingRadiusin;
    //小圆环的画笔
    private Paint mRingPaintin;
    //圆环颜色
    private int mRingColorin;
    //画矩形
    private Paint RectPaint;
    private float width;//矩形的宽
    private float height;//矩形的高
    //标记触发的部位
    int flag=0;
    private Context context;
    private RectF oval;
    private RectF oval2;
    private RectF oval3;
    private RectF oval4;
    //圆的半径
    private float mRadiusmiddle;
    //圆环宽度
    private float mStrokeWidthmiddle;
    //圆环的半径
    private float mRingRadiusmiddle;
    //圆环的颜色
    private int mRingColormiddle;
    /**画笔**/
    private Paint mRingPaintmiddle;
   //当前的角度
    private float endAngleR=-90,endAngleL=90;
    //圆环的重画笔
    private Paint leftRingPaint,rightRingPaint;  
    //起始角度
    private float startAngleR=-90,startAngleL=90;
    //线的颜色
    private int lineColor;
    /**开关的2种状态**/
    public static final int SWITCH_OFF = 0;// 关闭状态  
	public static final int SWITCH_ON = 1;// 打开状态  
	/**当前的开关的状态**/
	private int mSwitchStatus = SWITCH_ON;
	/**最大值**/
	private int maxleft=100; 
	private int maxright=100;
	/**当前强度**/
	private int valueL;
	private int valueR;
	/**判断是否有级**/
	private boolean isStepLeft=false;
	private boolean isStepRight=false;
	private int stepNumLeft=2;
	private int stepNumRight=3;
	/**判断是否有效**/
	private boolean leftEnabled=true;
	private boolean rightEnabled=true;
	/**监听接口**/
	private OnSwitchFLListener switchListener=null;
	private int b=0;
	private static final int ONECOLOR=Color.rgb(130, 198, 226);
	private static final int TWOCOLOR=Color.rgb(81, 181, 224);
	private static final int THREECOLOR=Color.rgb(0, 163, 221);
	private static final int FOURCOLOR=Color.rgb(0, 142, 214);
	private static final int FIVECOLOR=Color.rgb(0, 142, 214);
	
	private boolean isPressed=false;
	private int onColor;
	private int offColor;
	private int selectColorR;
	private int selectColorL;
	public SylphCircleSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		initAttrs();
		initVariable();
		new Thread(this).start();
	}

	public SylphCircleSlider(Context context) {
		super(context);
		this.context=context;
		initAttrs();
		initVariable();
		new Thread(this).start();
		
	}
	/**默认属性值**/
	private void initAttrs() {
		
		mStrokeWidth=100;
		mRadius=125;
		mRingRadius = mRadius + mStrokeWidth / 2;
		mRingColor=Color.rgb(192, 192, 192);
		mRadiusin=40;
		mStrokeWidthin=20;
		mRingRadiusin=mRadiusin+mStrokeWidthin/2;
		width=10;
		height=35;
		
		mStrokeWidthmiddle=40;
		mRadiusmiddle=mRadius-mStrokeWidthmiddle;
		mRingRadiusmiddle=mRadiusmiddle+mStrokeWidthmiddle/2;
		mRingColormiddle=Color.TRANSPARENT;
		
		lineColor=Color.WHITE;
		
		onColor=Color.BLUE;
		offColor=Color.RED;
		selectColorR=ONECOLOR;
		selectColorL=FOURCOLOR;
		
	}
	private void changColor(){
		switch (mSwitchStatus) {
		case SWITCH_OFF:
			mRingColorin=offColor;
			break;
		case SWITCH_ON:
			mRingColorin=onColor;
			break;
		default:
			break;
		}
	}
	private void initVariable() {
		//画圆环
		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mStrokeWidth);
		//画按钮圆环
		mRingPaintin=new Paint();
		mRingPaintin.setAntiAlias(true);
		mRingPaintin.setStyle(Paint.Style.STROKE);
		mRingPaintin.setStrokeWidth(mStrokeWidthin);
		//画小圆环
		mRingPaintmiddle=new Paint();
		mRingPaintmiddle.setAntiAlias(true);
		mRingPaintmiddle.setColor(mRingColormiddle);
		mRingPaintmiddle.setStyle(Paint.Style.STROKE);
		mRingPaintmiddle.setStrokeWidth(mStrokeWidthmiddle);
				
		//矩形
		RectPaint=new Paint();
		RectPaint.setAntiAlias(true);
		//重画圆环
		rightRingPaint = new Paint();
		rightRingPaint.setAntiAlias(true);
		rightRingPaint.setStyle(Paint.Style.STROKE);
		rightRingPaint.setStrokeWidth(mStrokeWidth);
		rightRingPaint.setColor(selectColorR);
		//重画圆环
		leftRingPaint = new Paint();
		leftRingPaint.setAntiAlias(true);
		leftRingPaint.setStyle(Paint.Style.STROKE);
		leftRingPaint.setStrokeWidth(mStrokeWidth);
		leftRingPaint.setColor(selectColorL);
		
	}
	
	
	private void initData(){
		if(mSwitchStatus==SWITCH_ON){
			if(isStepLeft){
				if(valueL!=0){
					int id=(valueL-1)%stepNumLeft;
					startAngleL=90+id*180/stepNumLeft;
					endAngleL=90+(id+1)*180/stepNumLeft;
				}
				
			}else {
				endAngleL=startAngleL+((float)valueL/maxleft*180);
			}
			if(isStepRight){
				if(valueR!=0){
					int id=(valueR-1)%stepNumRight;
					startAngleR=-90+id*180/stepNumRight;
					endAngleR=-90+(id+1)*180/stepNumRight;
				}
				
			}else {
				endAngleR=startAngleR+((float)valueR/maxright*180);
			}
		}
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mXCenter=getWidth()/2;
		mYCenter=getHeight()/2;
		onDrawArc(canvas);
		onDrawLine(canvas);
		if(!isPressed){
		onDrawinit(canvas);
		}
		canvas.drawArc(oval, startAngleR, endAngleR-startAngleR, false, rightRingPaint);
		canvas.drawArc(oval, startAngleL, endAngleL-startAngleL, false, leftRingPaint);
		
	}
	//初始化化数据
	private void onDrawinit(Canvas canvas){
		initData();
		rightRingPaint.setColor(selectColorR);
		leftRingPaint.setColor(selectColorL);
		canvas.drawArc(oval, startAngleR, endAngleR-startAngleR, false, rightRingPaint);
		canvas.drawArc(oval, startAngleL, endAngleL-startAngleL, false, leftRingPaint);
	}
	//画圆环
	private void onDrawArc(Canvas canvas){
		oval = new RectF();
		oval.left = (mXCenter - mRingRadius);
		oval.top = (mYCenter - mRingRadius);
		oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
		oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
		
		oval2=new RectF();
		oval2.left=mXCenter-mRingRadiusin;
		oval2.top=mYCenter-mRingRadiusin;
		oval2.right=mXCenter+mRingRadiusin;
		oval2.bottom=mYCenter+mRingRadiusin;
		
		oval3=new RectF();
		oval3.left=mXCenter-width;
		oval3.top=mYCenter-height*2;
		oval3.right=mXCenter+width;
		oval3.bottom=mYCenter;
		
		oval4=new RectF(mXCenter-mRingRadiusmiddle, mYCenter-mRingRadiusmiddle, mXCenter+mRingRadiusmiddle, mYCenter+mRingRadiusmiddle);
			changColor();
			mRingPaint.setColor(mRingColor);
			canvas.drawArc(oval, -90, 360, false, mRingPaint);
			mRingPaintin.setColor(mRingColorin);
			canvas.drawArc(oval2, -45, 270, false, mRingPaintin);
			RectPaint.setColor(mRingColorin);
			canvas.drawRoundRect(oval3, 10, 10, RectPaint);
			canvas.drawArc(oval4, -90, 360, false, mRingPaintmiddle);
	}
	//画线
	private void onDrawLine(Canvas canvas){
		Paint paint=new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(lineColor);
		paint.setAntiAlias(true);
		canvas.drawLine(mXCenter, mYCenter-mRadius, mXCenter, mYCenter-mRadius-mStrokeWidth, paint);
		canvas.drawLine(mXCenter, mYCenter+mRadius, mXCenter, mYCenter+mRadius+mStrokeWidth, paint);
		if(isStepRight){
			for(int i=1;i<stepNumRight;i++){
				float startX=(float) (mXCenter+(mRadius)*Math.sin(Math.PI/stepNumRight*i));
				float startY=(float) (mYCenter-mRadius*Math.cos(Math.PI/stepNumRight*i));
				float stopX=(float) (mXCenter+(mRadius+mStrokeWidth)*Math.sin(Math.PI/stepNumRight*i));
				float stopY=(float) (mYCenter-(mRadius+mStrokeWidth)*Math.cos(Math.PI/stepNumRight*i));
				canvas.drawLine(startX, startY, stopX, stopY, paint);
			}
		}
		if(isStepLeft){
			for(int i=1;i<stepNumLeft;i++){
				float startX=(float) (mXCenter-(mRadius)*Math.sin(Math.PI/stepNumLeft*i));
				float startY=(float) (mYCenter-mRadius*Math.cos(Math.PI/stepNumLeft*i));
				float stopX=(float) (mXCenter-(mRadius+mStrokeWidth)*Math.sin(Math.PI/stepNumLeft*i));
				float stopY=(float) (mYCenter-(mRadius+mStrokeWidth)*Math.cos(Math.PI/stepNumLeft*i));
				canvas.drawLine(startX, startY, stopX, stopY, paint);
			}
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
		Log.i("jiaodu", jiaodu+"");
		isPressed=true;
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
					setValueL(0);
					setValueR(0);
					rightRingPaint.setColor(mRingColor);
					leftRingPaint.setColor(mRingColor);
					mRingPaintmiddle.setColor(mRingColormiddle);
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
			//右边有效
			if(rightEnabled){
				if(isStepRight){//右边有级
					if(jiaodu>-90&&jiaodu<=90){
						for (int i = 0; i < stepNumRight; i++) {
							startAngleR=-90+180/stepNumRight*i;
							endAngleR=-90+180/stepNumRight*(i+1);
							if(startAngleR<jiaodu&&jiaodu<=endAngleR
									){
								rightRingPaint.setColor(selectColorR);
								setValueR(i+1);
								mSwitchStatus=SCREEN_STATE_ON;
								mRingPaintmiddle.setColor(selectColorR);
								break;
								
							}
						}
					}
					
					
				}else {//右边无级
					if(mSwitchStatus==SCREEN_STATE_ON){
						if(-90<jiaodu&&jiaodu<=90){
							endAngleR=jiaodu;
						valueR=(int) ((endAngleR-startAngleR)/180*maxright);
						setValueR(valueR);
						b=255-(int) ((endAngleR-startAngleR)/180*255);
						rightRingPaint.setColor(selectColorR);
						mRingPaintmiddle.setColor(Color.rgb(255, 255, b));
						break;
						}
					}
					
				}
//				postInvalidate();
			}
			//左边有效
			if(leftEnabled){
				if(isStepLeft){//左边有级
					if(mSwitchStatus==SWITCH_OFF){
						if(jiaodu>90&&jiaodu<=270){
							for (int i = 0; i < stepNumLeft; i++) {
								startAngleL=90+180/stepNumLeft*i;
								endAngleL=90+180/stepNumLeft*(i+1);
								if(startAngleL<jiaodu&&jiaodu<=endAngleL
										){
									leftRingPaint.setColor(selectColorL);
									setValueL(i+1);
									break;
								}
							}
						}
					}
				}else {//左边无级
						if(mSwitchStatus==SWITCH_ON){
							if(jiaodu>90&&jiaodu<=270){
								endAngleL=jiaodu;
								valueL = (int) ((endAngleL-startAngleL)/180*maxleft);
								setValueL(valueL);
								b=(int) ((endAngleL-startAngleL)/180*255);
								leftRingPaint.setColor(selectColorL);
								mRingPaintmiddle.setColor(Color.rgb(255, 255, b));
//								
								break;
						}
					}
								
				}
				
				
			}
			postInvalidate();
			
			break;
		case 3:
			break;
		default:
			break;
		}
		
		Toast.makeText(context, "mSwitchStatus"+mSwitchStatus+"\nvalueR"+valueR+"valueL"+valueL+"\nisPressed"+isPressed, Toast.LENGTH_SHORT).show();
		if(isStepLeft||isStepRight){
			return false;
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
        if(result <=mRadiusin*mRadiusin ) {// 点击的点在小圆内   
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
    	Log.i("c", c+"");
    	if(downx>0&&downy>0){

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
	         mheight = 450; 
	  
	        setMeasuredDimension(mwidth, mheight);
	    }
	 // 这个方法对于自定义view的时候帮助不大，因为view的位置一般由父组件来决定的
	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right,
	    		int bottom) {
	    	// TODO Auto-generated method stub
	    	super.onLayout(changed, left, top, right, bottom);
	    }

		
		public int getSelectColorR() {
			return selectColorR;
		}

		public void setSelectColorR(int selectColorR) {
			this.selectColorR = selectColorR;
		}

		public int getSelectColorL() {
			return selectColorL;
		}

		public void setSelectColorL(int selectColorL) {
			this.selectColorL = selectColorL;
		}



		public float getmStrokeWidth() {
			return mStrokeWidth;
		}

		public void setmStrokeWidth(float mStrokeWidth) {
			this.mStrokeWidth = mStrokeWidth;
		}

		
		public float getmStrokeWidthin() {
			return mStrokeWidthin;
		}

		public void setmStrokeWidthin(float mStrokeWidthin) {
			this.mStrokeWidthin = mStrokeWidthin;
		}

		

		public float getmStrokeWidthmiddle() {
			return mStrokeWidthmiddle;
		}

		public void setmStrokeWidthmiddle(float mStrokeWidthmiddle) {
			this.mStrokeWidthmiddle = mStrokeWidthmiddle;
		}

		public int getLineColor() {
			return lineColor;
		}

		public void setLineColor(int lineColor) {
			this.lineColor = lineColor;
		}


		public void setOnColor(int onColor) {
			this.onColor = onColor;
		}

		public void setOffColor(int offColor) {
			this.offColor = offColor;
		}

		
		public int getmRingColor() {
			return mRingColor;
		}

		public void setmRingColor(int mRingColor) {
			this.mRingColor = mRingColor;
		}

		

		public int getmRingColormiddle() {
			return mRingColormiddle;
		}

		public void setmRingColormiddle(int mRingColormiddle) {
			this.mRingColormiddle = mRingColormiddle;
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

	
		public int getmSwitchStatus() {
			return mSwitchStatus;
		}

		public void setmSwitchStatus(int mSwitchStatus) {
			this.mSwitchStatus = mSwitchStatus;
			if(switchListener!=null){
				switchListener.onSwitch(mSwitchStatus);
				switchListener.onValueChange(valueR, valueL, isStepLeft, isStepRight, leftEnabled, rightEnabled,isPressed);
			}
		}


		public OnSwitchFLListener getSwitchListener() {
			return switchListener;
		}

		public void setSwitchListener(OnSwitchFLListener switchListener) {
			this.switchListener = switchListener;
		}

		
		public boolean isStepLeft() {
			return isStepLeft;
		}

		public void setIsStepLeft(boolean isStepLeft) {
			this.isStepLeft = isStepLeft;
		}

		public boolean isStepRight() {
			return isStepRight;
		}

		public void setIsStepRight(boolean isStepRight) {
			this.isStepRight = isStepRight;
		}

		public boolean isLeftEnabled() {
			return leftEnabled;
		}

		public void setLeftEnabled(boolean leftEnabled) {
			this.leftEnabled = leftEnabled;
		}

		public boolean isRightEnabled() {
			return rightEnabled;
		}

		public void setRightEnabled(boolean rightEnabled) {
			this.rightEnabled = rightEnabled;
		}

		
		public int getStepNumLeft() {
			return stepNumLeft;
		}

		public void setNumLeft(int stepNumLeft) {
			this.stepNumLeft = stepNumLeft;
		}

		public int getStepNumRight() {
			return stepNumRight;
		}

		public void setNumRight(int stepNumRight) {
			this.stepNumRight = stepNumRight;
		}
		

		
		
		public int getValueL() {
			return valueL;
		}

		public void setValueL(int valueL) {
			this.valueL = valueL;
//			initData();
			if(switchListener!=null){
				switchListener.onSwitch(mSwitchStatus);
				switchListener.onValueChange(valueR, valueL,isStepLeft,isStepRight,leftEnabled,rightEnabled,isPressed);
			}
		}

		public int getValueR() {
			return valueR;
		}

		public void setValueR(int valueR) {
			this.valueR = valueR;
//			initData();
			if(switchListener!=null){
				switchListener.onSwitch(mSwitchStatus);
				switchListener.onValueChange(valueR, valueL,isStepLeft,isStepRight,leftEnabled,rightEnabled,isPressed);
			}
			
		}

		

		public int getMwidth() {
			return mwidth;
		}

		public void setMwidth(int mwidth) {
			this.mwidth = mwidth;
		}

		public int getMheight() {
			return mheight;
		}

		public void setMheight(int mheight) {
			this.mheight = mheight;
		}

		
		public float getmRadius() {
			return mRadius;
		}

		public void setmRadius(float mRadius) {
			this.mRadius = mRadius;
		}

		
		public boolean isPressed() {
			return isPressed;
		}

		public void setPressed(boolean isPressed) {
			this.isPressed = isPressed;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!Thread.currentThread().isInterrupted()){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
			
				
				
				postInvalidate();
			}
			
		}
		
	class Sylph{
		int id;
		int type;
		int step;
		int color;
		
		public Sylph(){
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getStep() {
			return step;
		}

		public void setStep(int step) {
			this.step = step;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}
		
	}
		
		
}
