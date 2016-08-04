package com.koject.canvas;

import com.koject.canvas.interf.OnSwitchFLListener;
import com.koject.canvas.view.FLView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

public class MainActivity extends Activity {

	int screenWidth;
	int screenHeight;
	private int fSwitchStatus,lSwitchStatus;//开关的状态（0关1开）
	private FLView fv;
	private FLView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Screen();
       
        setContentView(R.layout.view_layout);
        initView(); 
//        new Thread(new ProgressRunable()).start();		
        

    }
   private void initView(){
	   fv=(FLView) findViewById(R.id.fan);
	   lv=(FLView) findViewById(R.id.flview);   
	   fv.setmIsScrolled(true);
	   fv.setmDirecEnabled(true);
	   fv.setSwitchListener(new OnSwitchFLListener() {
		
		@Override
		public void onSwitch(Context context, int status, int strengthR,
				int strengthL) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStrengthChange(Context context, int strengthR, int strengthL,
				boolean isScrolled, boolean direcEnabled) {
			// TODO Auto-generated method stub
			
		}
	});
	   
   }
    private void Screen(){
    	//无标题栏
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	//设置为全屏
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	//设置为横屏
//    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	DisplayMetrics dm=new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(dm);
    	screenWidth=dm.widthPixels;
    	screenHeight=dm.heightPixels;
    	Log.i("screenWidth", screenWidth+"");
    	Log.i("screenHeight", screenHeight+"");
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	MenuInflater inflater=getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return true;
    }
   @Override
   	public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	   int id=item.getItemId();
	   
	   switch (id) {
	case R.id.action_settings:
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, TwoActivity.class);
		startActivity(intent);
//		MainActivity.this.finish();
		break;
	case R.id.layout:
		Intent intent1=new Intent();
		intent1.setClass(MainActivity.this, LayoutActivity.class);
		startActivity(intent1);
//		MainActivity.this.finish();
		break;

	default:
		break;
	}
	return super.onOptionsItemSelected(item);
}
    class ProgressRunable implements Runnable {

		@Override
		public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					Message msg=new Message();
					msg.what=1;
					getHandler.sendMessage(msg);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		}
		Handler getHandler=new Handler(){
			public void handleMessage(android.os.Message msg) {
				fv.invalidate();
				lv.invalidate();
				if(msg.what==1){
					fv.setSwitchListener(new OnSwitchFLListener() {
						
						@Override
						public void onSwitch(Context context, int status,int strengthR,int strengthL) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "status:"+status+"\nposition:"+strengthR+"\ndiection:"+strengthL, Toast.LENGTH_SHORT).show();
						}
						
						@Override
						public void onStrengthChange(Context context,
								int strengthR, int strengthL, boolean isScrolled,boolean mDirecEnabled) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "position:"+strengthR+"diection:"+strengthL, Toast.LENGTH_SHORT).show();
						}
					});
					
				lv.setSwitchListener(new OnSwitchFLListener() {
					
					@Override
					public void onSwitch(Context context, int status,int strengthR,int strengthL) {
						// TODO Auto-generated method stub
						lSwitchStatus=status;
						Toast.makeText(getApplicationContext(), "status:"+status+"\nstrengthY:"+strengthR+"\nstrengthW:"+strengthL, Toast.LENGTH_SHORT).show();
					}





					@Override
					public void onStrengthChange(Context context,
							int strengthR, int strengthL, boolean isScrolled,boolean mDirecEnabled) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "strengthY:"+strengthR+"strengthW:"+strengthL, Toast.LENGTH_SHORT).show();
					}
					
					
				});
				}
			};
		};
    
    
}
    


