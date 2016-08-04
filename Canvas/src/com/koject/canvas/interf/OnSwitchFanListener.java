package com.koject.canvas.interf;

import android.content.Context;


public interface OnSwitchFanListener {
	public void onSwitch(Context context, int status);
	public void onPositionChange(Context context,boolean mIsScrolled,boolean mDirecEnabled,int position);

}

