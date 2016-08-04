package net.kojec.uicontrols;

import net.kojec.uicontrols.view.SylphCircleSlider;


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

	private SylphCircleSlider fan;
	private SylphCircleSlider light;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_loyout);
		initView();
//		new Thread(new ProgressRunable()).start();
	}
	
	private void initView(){
		fan=(SylphCircleSlider) findViewById(R.id.fan2);
		light=(SylphCircleSlider) findViewById(R.id.light2);
		fan.setIsStepLeft(true);
		fan.setIsStepRight(true);
		fan.setNumLeft(2);
		fan.setNumRight(5);
		fan.setOnColor(Color.GREEN);
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
					
				
				}
			};
		};
    
}
