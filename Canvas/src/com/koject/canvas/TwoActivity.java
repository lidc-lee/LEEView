package com.koject.canvas;

import com.koject.canvas.interf.OnSwitchFLListener;
import com.koject.canvas.view.FLView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class TwoActivity extends Activity{

	private FLView fan;
	private FLView light;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_loyout);
		initView();
//		new Thread(new ProgressRunable()).start();
	}
	
	private void initView(){
		fan=(FLView) findViewById(R.id.fan2);
		light=(FLView) findViewById(R.id.light2);
		fan.setmIsScrolled(true);
		fan.setmDirecEnabled(false);
		fan.setmRingColor(Color.rgb(100, 255, 0));
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
			intent.setClass(TwoActivity.this, SurfaceActivity.class);
			startActivity(intent);
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
				fan.invalidate();
				light.invalidate();
				if(msg.what==1){
					fan.setSwitchListener(new OnSwitchFLListener() {
						
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
					
				light.setSwitchListener(new OnSwitchFLListener() {
					
					@Override
					public void onSwitch(Context context, int status,int strengthR,int strengthL) {
						// TODO Auto-generated method stub
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
