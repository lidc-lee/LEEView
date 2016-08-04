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
		//��ӻص�����
		surfaceHolder.addCallback(this);
		this.setFocusable(true);
//		shapeDrawable=new ShapeDrawable(context);
	}

	public SurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		surfaceHolder=this.getHolder();
		//��ӻص�����
		surfaceHolder.addCallback(this);
		/**
		 * ʹ�ܿؼ���ý��㣬����Ϊtrueʱ��������˵���̻�ý��㣬Ҫ�����̻�ý��㣬����requestFocus��
			ʹ�ܻ�ý��㣬����˵�߱���ý���Ļ��ᡢ���������н����ڿؼ�֮���ƶ�ʱ���ؼ�����������ᡢ�����õ����㡣
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
		//��������
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
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);//����
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
		//���ƺ����������ʾ
		surfaceHolder.unlockCanvasAndPost(canvas);
		
//		shapeDrawable.DrawShape(canvas);
		
	}

	/**��surface�Ĵ�С�����ı�ʱ����**/
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	/**�ڴ���surfaceʱ����**/
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	/**������surfaceʱ����**/
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		loop=false;
	}

	
}
