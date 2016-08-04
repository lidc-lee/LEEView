// ����LinearGradient�����ý�����ɫ����  
        // ��һ��,�ڶ���������ʾ������� ������������յ��ڶԽǵ�����λ��  
        // ������,���ĸ�������ʾ�����յ�  
        // �����������ʾ������ɫ  
        // ��������������Ϊ��,��ʾ����,ֵΪ0-1 new float[] {0.25f, 0.5f, 0.75f, 1 }  
        // ������ǿյģ���ɫ���ȷֲ������ݶ��ߡ�  
        // ���߸���ʾƽ�̷�ʽ  
        // CLAMP�ظ����һ����ɫ�����  
        // MIRROR�ظ���ɫ��ͼ��ˮƽ��ֱ�����Ѿ���ʽ�����з�תЧ��  
        // REPEAT�ظ���ɫ��ͼ��ˮƽ��ֱ����  
package com.koject.canvas.view;


import com.koject.canvas.interf.OnSwitchFLListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LigthtView extends View {

	public static final int SWITCH_OFF = 0;// �ر�״̬  
	public static final int SWITCH_ON = 1;// ��״̬  
	public static final int SWITCH_SCROLING = 2;// ����״̬  
	private int mSwitchStatus = SWITCH_OFF;  
	// ��ʵ��Բ�Ļ���  
    private Paint mCirclePaint;  
    // ��Բ���Ļ���  
    private Paint mRingPaint;  
    // ������Ļ���  
    private Paint mTextPaint;  
    // Բ����ɫ  
    private int mCircleColor;  
    // Բ����ɫ  
    private int mRingColor;  
    // �뾶  
    private float mRadius;  
    // Բ���뾶  
    private float mRingRadius;  
    // Բ�����,���ʵıʴ����
    private float mStrokeWidth;  
    // Բ��x����  
    private int mXCenter;  
    // Բ��y����  
    private int mYCenter;  
    // �ֵĳ���  
    private float mTxtWidth;  
    // �ֵĸ߶�  
    private float mTxtHeight;  

    // ��ǰ����  
    private int mProgress; 
    private float mRadius2;
    private float mStrokeWidth2;
    private float mRingRadius2;
    private Paint mRingPaint2;
    public int mRingColor2;
    //������
    private Paint RectPaint;
    private float width;
    private float height;
    int mwidth ;  
    int mheight;
    int flag=0;
    int flsh=0;
    private float centuralAngle=-90,centuralAngle2=90;
    private Paint cenRingPaint,cenRingPaint2;  
    private int cenRingColor,cenRingColor2;
    private float startAngle=-90,startAngle2=90;
    private Context context;
    private RectF oval;
    private RectF oval2;
    private RectF oval3;
    private RectF oval4;
    private float mRadius3;
    private float mStrokeWidth3;
    private float mRingRadius3;
    private Paint mRingPaintleft,mRingPaintright;
    private int mRingColor3;
  
    public static final int ONELIGHT=1; 
    public static final int DOUBLELIGHT=2; 
    public static final int NOLIGHT=0;
    private int model=DOUBLELIGHT;
    public static final int WHITE=0;
    public static final int YELLOW=1;
    /**������ǿ��**/
    private int maxYellow=90;
    private int maxWhite=90;
    /**��ǰ���ǿ��**/
    private int strengthY;
    private int strengthW;
    private int type=YELLOW;
    private int g;
    private int b;
    private int r;
    OnSwitchFLListener switchListener=null;
	private boolean mIsScrolled=false;
	private boolean mDirecEnabled=false;
	public LigthtView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
		initAttrs();
		initVariable();
		
	}

	public LigthtView(Context context) {
		super(context);
		this.context=context;
		
		initAttrs();
		initVariable();
		
	}

	public int getmSwitchStatus() {
		return mSwitchStatus;
	}

	public void setmSwitchStatus(int mSwitchStatus) {
		this.mSwitchStatus = mSwitchStatus;
		if(switchListener!=null){
			switchListener.onSwitch(context, mSwitchStatus,strengthY,strengthW);
		}
		postInvalidate();
	}

	

	public OnSwitchFLListener getSwitchListener() {
		return switchListener;
	}

	public void setSwitchListener(OnSwitchFLListener switchListener) {
		this.switchListener = switchListener;
	}

	public int getStrengthY() {
		return strengthY;
	}

	public void setStrengthY(int strengthY) {
		this.strengthY = strengthY;
		if(switchListener!=null){
			
			switchListener.onStrengthChange(context, strengthY, strengthW,mIsScrolled,mDirecEnabled);
		}
		postInvalidate();
	}

	public int getStrengthW() {
		return strengthW;
	}

	public void setStrengthW(int strengthW) {
		this.strengthW = strengthW;
		if(switchListener!=null){
			switchListener.onStrengthChange(context, strengthY, strengthW,mIsScrolled,mDirecEnabled);
		}
		postInvalidate();
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
		cenRingColor=Color.YELLOW;
		cenRingColor2=Color.WHITE;
		
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
		//��Բ
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(mCircleColor);
		mCirclePaint.setStyle(Paint.Style.FILL);
		//��Բ��
		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
//		mRingPaint.setColor(mRingColor);
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mStrokeWidth);
		//��text
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
		
		mRingPaintleft=new Paint();
		mRingPaintleft.setAntiAlias(true);
		mRingPaintleft.setColor(mRingColor3);
		mRingPaintleft.setStyle(Paint.Style.STROKE);
		mRingPaintleft.setStrokeWidth(mStrokeWidth3);
		
		mRingPaintright=new Paint();
		mRingPaintright.setAntiAlias(true);
		mRingPaintright.setColor(mRingColor3);
		mRingPaintright.setStyle(Paint.Style.STROKE);
		mRingPaintright.setStrokeWidth(mStrokeWidth3);
		
		//����
		RectPaint=new Paint();
		RectPaint.setAntiAlias(true);
//		RectPaint.setColor(mRingColor2);
		
		cenRingPaint = new Paint();
		cenRingPaint.setAntiAlias(true);
//		cenRingPaint.setColor(cenRingColor);
		cenRingPaint.setStyle(Paint.Style.STROKE);
		cenRingPaint.setStrokeWidth(mStrokeWidth);
		
		cenRingPaint2 = new Paint();
		cenRingPaint2.setAntiAlias(true);
//		cenRingPaint2.setColor(cenRingColor);
		cenRingPaint2.setStyle(Paint.Style.STROKE);
		cenRingPaint2.setStrokeWidth(mStrokeWidth);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		mXCenter = getWidth() / 2;
		mYCenter = getHeight()/2;
		onDrawArc(canvas);
		onDrawLine(canvas);
			String txt = mProgress + "%";
			mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
//			canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);
			cenRingPaint.setColor(cenRingColor);;
			canvas.drawArc(oval, startAngle, centuralAngle-startAngle, false, cenRingPaint);
			cenRingPaint2.setColor(cenRingColor2);
			canvas.drawArc(oval, startAngle2, centuralAngle2-startAngle2, false, cenRingPaint2);
			
			canvas.drawArc(oval4, -90, 180, false, mRingPaintleft);
			canvas.drawArc(oval4, 90, 180, false, mRingPaintright);
	}
	
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
			mRingPaint.setColor(Color.rgb(192, 192, 192));
			canvas.drawArc(oval, -90, 360, false, mRingPaint);
			mRingPaint2.setColor(mRingColor2);
			canvas.drawArc(oval2, -45, 270, false, mRingPaint2);
			RectPaint.setColor(mRingColor2);
			canvas.drawRoundRect(oval3, 10, 10, RectPaint);
			
			canvas.drawArc(oval4, -90, 360, false, mRingPaintleft);
	}
	private void onDrawLine(Canvas canvas){
		Paint paint=new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		canvas.drawLine(mXCenter, mYCenter-mRadius, mXCenter, mYCenter-mRadius-mStrokeWidth, paint);
		canvas.drawLine(mXCenter, mYCenter+mRadius, mXCenter, mYCenter+mRadius+mStrokeWidth, paint);
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
				if(mSwitchStatus==SWITCH_OFF){
					mSwitchStatus=SWITCH_ON;
					setmSwitchStatus(mSwitchStatus);
				}
				else {
					mSwitchStatus=SWITCH_OFF;
					setmSwitchStatus(mSwitchStatus);
				}
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
			if(model==DOUBLELIGHT){
			if(mSwitchStatus==SCREEN_STATE_ON){
				if(-90<jiaodu&&jiaodu<=90){
				centuralAngle=jiaodu;
				strengthY=(int) ((centuralAngle-startAngle)/180*maxYellow);
				setStrengthY(strengthY);
				r=(int) ((centuralAngle-startAngle)/180*255);
				g=(int) ((centuralAngle-startAngle)/180*255);
				b=(int) ((centuralAngle-startAngle)/180*255);
				mRingPaintleft.setColor(Color.rgb(r, g, 0));
				break;
				}
				if(jiaodu>90&&jiaodu<=270){
					centuralAngle2=jiaodu;
					strengthW=(int) ((centuralAngle2-startAngle2)/180*maxWhite);
					setStrengthW(strengthW);
					r=(int) ((centuralAngle2-startAngle2)/180*255);
					g=(int) ((centuralAngle2-startAngle2)/180*255);
					b=(int) ((centuralAngle2-startAngle2)/180*255);
					mRingPaintright.setColor(Color.rgb(255, 255, b));
					break;
				}
//				mRingPaintleft.setColor(Color.rgb(r, g, b));
				postInvalidate();
			}else if (mSwitchStatus==SCREEN_STATE_OFF) {
//				mRingColor=Color.GRAY;
//				postInvalidate();
			}
			}else if(model==NOLIGHT){
				
			}else if (model==ONELIGHT) {
				if(type==WHITE){
					if(jiaodu>90&&jiaodu<=270){
						centuralAngle2=jiaodu;
						strengthW=(int) ((centuralAngle2-startAngle2)/180*maxWhite);
						setStrengthW(strengthW);
						r=(int) ((centuralAngle2-startAngle2)/180*255);
						g=(int) ((centuralAngle2-startAngle2)/180*255);
						b=(int) ((centuralAngle2-startAngle2)/180*255);
						mRingPaintright.setColor(Color.rgb(255, 255, b));
						break;
					}
				}else if(type==YELLOW){
					if(-90<jiaodu&&jiaodu<=90){
						centuralAngle=jiaodu;
						strengthY=(int) ((centuralAngle-startAngle)/180*maxYellow);
						setStrengthY(strengthY);
						r=(int) ((centuralAngle2-startAngle2)/180*255);
						g=(int) ((centuralAngle2-startAngle2)/180*255);
						b=(int) ((centuralAngle2-startAngle2)/180*255);
						mRingPaintleft.setColor(Color.rgb(r, g, 0));
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
    
}
