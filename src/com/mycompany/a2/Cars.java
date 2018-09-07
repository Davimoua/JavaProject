package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Cars extends MoveableObject implements ISteerable {
	private int steeringDirection;
	private int maximumSpeed;
	private int fuelLevel;
	private int fuelConsumptionRate;
	private int damageLevel;
	private int lastPylonReached;
	private Random ran;
	
	public Cars() {
		ran = new Random();
		steeringDirection = 0;
		maximumSpeed = 50;
		fuelLevel = 40;
		fuelConsumptionRate = 2;
		damageLevel = 0;
		lastPylonReached=1;
		setColor(ColorUtil.rgb(255,0,0));
	}
	public void move() {
		int tmp = getHeading() + getSteering();
		setHeading(tmp);
		float deltaX = (float) (Math.cos(Math.toRadians((90-getHeading())))*getSpeed());
		float deltaY = (float) (Math.sin(Math.toRadians((90-getHeading())))*getSpeed());
		float newX = getLocationX() + deltaX;
		float newY = getLocationY() + deltaY;
		float xVal = (float) (Math.round(newX*10.0)/10.0);
		float yVal = (float) (Math.round(newY*10.0)/10.0);
		setLocation(xVal, yVal);
	}
	
	public void pretendPylonReach() {
		if(this.lastPylonReached + 1 > 9)
			this.lastPylonReached = 1;
		else 
			this.lastPylonReached++ ;
	}
	
	public int getLastPylonReached() {
		return this.lastPylonReached;
	}
	
	public void reduceFuelLevel() {
		this.fuelLevel = fuelLevel - fuelConsumptionRate;
	}
	
	
	public int getDamageLevel() {
		return this.damageLevel;
	}
	
	public void increaseDamage() {
		this.damageLevel++;
	}
	public void setDamage(int dmg) {
		this.damageLevel = dmg;
	}
	
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	
	public void setMaxSpeed(int speed) {
		this.maximumSpeed = speed;
	}
	
	public void increaseSpeed() {
		super.increaseSpeed(5);
	}
	
	public void decreaseSpeed() {
		super.decreaseSpeed(2);
	}
	
	
	public int getFuelLevel() {
		return fuelLevel;
	}
	
	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
	public void updateSteering() {
		int tmp = ran.nextInt(40)+5;
		steeringDirection = tmp;
	}
	
	public int getSteering() {
		return steeringDirection;
	}
	
	public void updateHeading() {
		int tmp = getHeading() + steeringDirection;
		if(tmp < 0 )
			tmp = 360 + tmp;
		else if(tmp > 360)
			tmp = 360 - tmp;
		setHeading(tmp);
	}
	
	public void updateHeading(double heading) {
		setHeading(heading);
	}
	
	public String toString() {
		String color = "[" + ColorUtil.red(getColor()) + ", "
							+ColorUtil.green(getColor()) + ", "
							+ColorUtil.blue(getColor()) + "]";
		return ("Car: loc= " + getLocationX() + "," + getLocationY() + " color =" + color + 
				" heading="+ getSteering() + " Speed=" + getSpeed() + " size=" + getSize() + " seqNum=" + getLastPylonReached()
				+ " maxSpeed=" + getMaxSpeed()+ " steeringDirection=" + getSteering() + " Fuel Level=" 
				+ getFuelLevel() + " damageLevel=" + getDamageLevel());
	}


}
