package net.kojec.uicontrols.adapter;

import java.util.List;




import net.kojec.uicontrols.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyViewAdapter extends BaseAdapter{

	private Context context;
	private List<String> list;
	public MyViewAdapter(Context context,List<String> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view=arg1;
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.list_item, null);
		}
		TextView tv=(TextView) view.findViewById(R.id.tv_list);
		tv.setText(list.get(arg0));
		return view;
	}

}
