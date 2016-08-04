package com.koject.canvas.view;

import com.koject.canvas.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends RelativeLayout{

	private Button back;
	private TextView tv_title;
	public TitleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title, this);
		back=(Button) findViewById(R.id.back);
		tv_title=(TextView) findViewById(R.id.tv_title);
//		back.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
////				TitleView.this.context.finish();
//				((Activity)getContext()).finish();
//				
//			}
//		});
//		tv_title.setText("title");
	}
	
	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title, this);
		back=(Button) findViewById(R.id.back);
		tv_title=(TextView) findViewById(R.id.tv_title);
//		back.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
////				TitleView.this.context.finish();
//				((Activity)getContext()).finish();
//				
//			}
//		});
//		tv_title.setText("title");
	}

	public String getBack() {
		return back.toString();
	}
	public void setBack(String text) {
		this.back.setText(text);
	}
	public String getTv_title() {
		return tv_title.toString();
	}
	public void setTv_title(String text) {
		this.tv_title.setText(text);
	}

	public void setButtonListener(View.OnClickListener l){
		back.setOnClickListener(l);
	}
	
}
