/**
 * 
 * ¼Ì³Ð¿Ø¼þ
 */
package com.koject.canvas;

import java.util.ArrayList;
import java.util.List;

import com.koject.canvas.adapter.MyViewAdapter;
import com.koject.canvas.view.MyListView;
import com.koject.canvas.view.MyListView.OnDeleteListener;
import com.koject.canvas.view.TitleView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LayoutActivity extends Activity{

	TitleView tview;
	MyListView myList;
	MyViewAdapter adapter;
	private List<String> list=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zuhe_layout);
		initdata();
		initView();
	}
	
	private void initView(){
		tview=(TitleView) findViewById(R.id.titleView1);
		tview.setTv_title("Title");
		tview.setBack("back");
		tview.setButtonListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutActivity.this.finish();
			}
		});
		myList=(MyListView) findViewById(R.id.myListView1);
		adapter=new MyViewAdapter(LayoutActivity.this, list);
		myList.setAdapter(adapter);
		myList.setDeleteListener(new OnDeleteListener() {
			
			@Override
			public void onDelete(int selectedItem) {
				// TODO Auto-generated method stub
				list.remove(selectedItem);
				adapter.notifyDataSetChanged();
				
			}
		});
	}
	
	private void initdata(){
		for(int i=1;i<=10;i++){
			list.add("item"+i);
		}
		
	}
}
