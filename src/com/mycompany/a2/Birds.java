package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;


public class Birds extends MoveableObject{
	private Random ran;
	private int gameSizeWidth = 1024;
	private int gameSizeHeight = 768;
	private static final String NAME = "BIRD";
	
	public Birds() {
		ran = new Random();
		super.setColor(ColorUtil.YELLOW);
		
		setSize(ran.nextInt(10)+1);
		setSpeed(ran.nextInt(10)+1);
	}
	
	public String getName() {
		return NAME;
	}
	
	public void move() {
		float deltaX = (float) (Math.cos(Math.toRadians(90-getHeading()))*getSpeed());
		float deltaY = (float) (Math.sin(Math.toRadians(90-getHeading()))*getSpeed());
		float newX = getLocationX() + deltaX;
		float newY = getLocationY() + deltaY;
		if(newX <= 0) {
			newX = 0;
			changeHeading();
		}
		else if (newX >= 1024) {
			newX = 1024;
			changeHeading();
		}
		
		if(newY <= 0) {
			newY = 0;
			changeHeading();
		}
		else if (newY >= 768) {
			newY = 768;
			changeHeading();
		}
		
		float xVal = (float) (Math.round(newX*10.0)/10.0);
		float yVal = (float) (Math.round(newY*10.0)/10.0);
		setLocation(xVal, yVal);
		changeHeading();
		
	}
	
	public void setColor(int color) {
		
	}
	
	public void changeHeading() {
		int tmp = ran.nextInt(2)+1;
		if(tmp == 1) {
			int total = getHeading() -5;
			if(total < 0)
				setHeading(360-total);
		}
		else {
			int total = getHeading() +5;
			if(total > 360)
				setHeading(total - 360);
		}
	}
	
	
	public String toString() {
		String color = "[" + ColorUtil.red(getColor()) + ", "
							+ColorUtil.green(getColor()) + ", "
							+ColorUtil.blue(getColor()) + "]";
		return ("Birds: loc=" + getLocationX() + ", " + getLocationY() + " color =" + color
				+" heading=" + getHeading() + " speed=" + getSpeed() + " size=" + getSize());
	}
}
