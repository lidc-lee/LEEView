package com.koject.canvas.interf;

import android.content.Context;

public interface OnSwitchFLListener {

	public void onSwitch(Context context, int status,int strengthR,int strengthL);
	public void onStrengthChange(Context context,int strengthR,int strengthL,boolean isScrolled,boolean direcEnabled);
}
