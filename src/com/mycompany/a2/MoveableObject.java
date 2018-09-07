package com.mycompany.a2;

public abstract class MoveableObject extends GameObject {
	private int speed;
	private int heading;
	
	public MoveableObject() {
		speed = 0;
		heading = 0;
	}
	
	public abstract void move();

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void increaseSpeed(int num) {
		setSpeed(this.speed + num);
	}
	
	public void decreaseSpeed(int num) {
		if(this.speed- num < 0)
			setSpeed(0);
		else {
			setSpeed(this.speed - num);
		}
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public void setHeading(double heading) {
		this.heading = (int) heading;
	}
	
	public int getHeading() {
		return this.heading;
	}
}
