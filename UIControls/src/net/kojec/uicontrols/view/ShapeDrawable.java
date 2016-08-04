package net.kojec.uicontrols.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

public class ShapeDrawable extends View implements Runnable{

	private android.graphics.drawable.ShapeDrawable shapeDrawable=null;
	Context context;
	private int X;
	private int Y;
	private boolean loop=true;
	int count=0;
	public ShapeDrawable(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
		new Thread(this).start();
	}
	
	public ShapeDrawable(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		// TODO Auto-generated constructor stub
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		 X=getWidth();
		 Y=getHeight();
		 if(!isInEditMode()){
			 DrawShape(canvas);
		 }
		
	}
	public void DrawShape(Canvas canvas){
		shapeDrawable=new android.graphics.drawable.ShapeDrawable(new RectShape());
		shapeDrawable.getPaint().setColor(Color.BLACK);
		shapeDrawable.setBounds(0,0,X,Y);
		shapeDrawable.draw(canvas);
		if(count<=100){
			count++;
		}else {
			count=0;
		}
		
		//สตภýปฏ
		shapeDrawable=new android.graphics.drawable.ShapeDrawable(new OvalShape());
		Paint paint=shapeDrawable.getPaint();
//		paint.setColor(Color.GREEN);
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
		shapeDrawable.setBounds(100,100,X-100,X-100);
		shapeDrawable.draw(canvas);
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (loop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			postInvalidate();
		}
			
		
		
	}

	
}
