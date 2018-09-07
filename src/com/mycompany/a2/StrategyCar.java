package com.mycompany.a2;

import com.codename1.util.MathUtil;

public class StrategyCar implements IStrategy {
	
	private NonPlayerCar npc;
	private Cars car;
	
	public StrategyCar(NonPlayerCar npc, Cars car) {
		this.npc = npc;
		this.car = car;
	}
	
	public void apply() {
		double npcLocationX = npc.getLocationX();
		double npcLocationY = npc.getLocationY();
		double carLocationX = car.getLocationX();
		double carLocationY = car.getLocationY();
		double totalX = npcLocationX - carLocationX;
		double totalY = npcLocationY - carLocationY;
		if(totalX < 0)
			totalX = totalX * -1;
		if(totalY < 0)
			totalY = totalY * -1;
		double tmp = MathUtil.atan(totalY/totalX);
		npc.setHeading(tmp);
	}
}
