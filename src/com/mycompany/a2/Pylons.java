package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Pylons extends FixedObject {
	private int sequenceNumber;
	private static final String NAME = "Pylons";
	
	
	public Pylons() {
		//sequenceNumber = 1;
		setColor(ColorUtil.BLUE);
		setLocation(200.0f,200.0f);
		
	}
	
	public String getName() {
		return NAME;
	}
	
	
	public int getColor() {
		return 0;
	}
	public void setSequenceNumber(int sequenceNum){
		if(this.sequenceNumber > 4)
			this.sequenceNumber = 1;
		else
			this.sequenceNumber = sequenceNum;
	}
	
	public void increaseSequenceNumber() {
		this.sequenceNumber++;
	}
	
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	public String toString() {
		String color = "[" + ColorUtil.red(getColor()) + ", "
							+ColorUtil.green(getColor()) + ", "
							+ColorUtil.blue(getColor()) + "]";
		return ("Pylon: loc=" + getLocationX() + ", " + getLocationY() + " color =" + color + " size=" 
							+getSize() + " seqNum=" + getSequenceNumber());
	}
}
