package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerCar extends Cars {
	private IStrategy s;
	
	
	public NonPlayerCar() {
		super();
		setColor(ColorUtil.BLACK);
	}
	

	public String toString() {
		String color = "[" + ColorUtil.red(getColor()) + ", "
							+ColorUtil.green(getColor()) + ", "
							+ColorUtil.blue(getColor()) + "]";
		return ("NPC: loc= " + getLocationX() + "," + getLocationY() + " color =" + color + 
				" heading="+ getHeading() + " Speed=" + getSpeed() + " size=" + getSize() + " seqNum=" + getLastPylonReached()
				+ " maxSpeed=" + getMaxSpeed()+ " steeringDirection=" + getHeading() + " Fuel Level=" 
				+ getFuelLevel() + " damageLevel=" + getDamageLevel() + "Strategy=" + getStringStrategy());
	}
	
	public void setStrategy(IStrategy s) {
		this.s = s;
	}
	
	public String getStringStrategy() {
		if(s instanceof StrategyCar)
			return "Car Strategy";
		else 
			return "Pylon Strategy";
	}
	
	public IStrategy getStrategy() {
		return this.s;
	}
	
	public void invokeStrategy() {
		this.s.apply();
	}
	
}
