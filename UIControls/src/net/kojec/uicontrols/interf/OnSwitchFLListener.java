package net.kojec.uicontrols.interf;


public interface OnSwitchFLListener {

	public void onSwitch(int status);
	public void onValueChange(int valueR, int valueL,
			boolean isStepLeft, boolean isStepRight, boolean leftEnabled,
			boolean rightEnabled,boolean isPressed);
}
