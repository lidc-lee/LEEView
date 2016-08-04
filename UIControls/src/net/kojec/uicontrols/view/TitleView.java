package net.kojec.uicontrols.view;


import net.kojec.uicontrols.R;
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

	}
	
	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title, this);
		back=(Button) findViewById(R.id.back);
		tv_title=(TextView) findViewById(R.id.tv_title);
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
