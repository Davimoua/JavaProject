package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class FuelCans extends FixedObject {
	private int capacity;
	private Random num;
	private static final String NAME="Fuel Can";
	
	
	public FuelCans() {
		setColor(ColorUtil.GREEN);
	}
	
	public String getName() {
		return NAME;
	}
	public FuelCans(int size){
		this.capacity = size;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public String toString() {
		String color = "[" + ColorUtil.red(getColor()) + ", "
							+ColorUtil.green(getColor()) + ", "
							+ColorUtil.blue(getColor()) + "]";
		return ("FuelCan: loc=" + getLocationX() + ", " + getLocationY() + " color=" + color + " size="+getSize());
	}

}
