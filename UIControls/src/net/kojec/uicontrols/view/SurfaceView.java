/***
 * 
 * 
 */
package net.kojec.uicontrols.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

public class SurfaceView extends android.view.SurfaceView implements SurfaceHolder.Callback,Runnable{

	private SurfaceHolder surfaceHolder=null;
	private boolean loop=true;
	int count=0;
	
	ShapeDrawable shapeDrawable=null;
	public SurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		surfaceHolder=this.getHolder();
		//添加回调函数
		surfaceHolder.addCallback(this);
		this.setFocusable(true);
//		shapeDrawable=new ShapeDrawable(context);
	}

	public SurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		surfaceHolder=this.getHolder();
		//添加回调函数
		surfaceHolder.addCallback(this);
		/**
		 * 使能控件获得焦点，设置为true时，并不是说立刻获得焦点，要想立刻获得焦点，得用requestFocus；
			使能获得焦点，就是说具备获得焦点的机会、能力，当有焦点在控件之间移动时，控件就有这个机会、能力得到焦点。
		 */
		this.setFocusable(true);
//		shapeDrawable=new ShapeDrawable(context);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(loop){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (surfaceHolder) {
				draw();
			}
		}
		
	}

	private void draw() {
		// TODO Auto-generated method stub
		//锁定画布
		Canvas canvas=surfaceHolder.lockCanvas();
		if(surfaceHolder==null||canvas==null){
			return;
		}
		if(count<=100){
			count++;
		}else {
			count=0;
		}
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);//清屏
		int X=getWidth()/2;
		int Y=getHeight()/2;
		switch (count%7) {
		case 0:
			paint.setColor(Color.YELLOW);
			break;
		case 1:
			paint.setColor(Color.GREEN);
			break;
		case 2:
			paint.setColor(Color.BLUE);
			break;
		case 3:
			paint.setColor(Color.RED);
			break;
		case 4:
			paint.setColor(Color.WHITE);
			break;
		case 5:
			paint.setColor(Color.GRAY);
			break;
		case 6:
			paint.setColor(Color.TRANSPARENT);
			break;
		default:
			break;
		}
		
		canvas.drawCircle(X, Y, 200, paint);
		//绘制后解锁，才显示
		surfaceHolder.unlockCanvasAndPost(canvas);
		
//		shapeDrawable.DrawShape(canvas);
		
	}

	/**在surface的大小发生改变时激发**/
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	/**在创建surface时激发**/
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	/**在销毁surface时激发**/
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		loop=false;
	}

	
}
