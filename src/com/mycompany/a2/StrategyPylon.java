package com.mycompany.a2;

import com.codename1.util.MathUtil;

public class StrategyPylon implements IStrategy {
	private NonPlayerCar npc;
	private Pylons pylon;
	
	public StrategyPylon(NonPlayerCar npc, Pylons pylon) {
		this.npc = npc;
		this.pylon = pylon;
	}
	
	public void apply() {
		double npcLocationX = npc.getLocationX();
		double npcLocationY = npc.getLocationY();
		double pylonLocationX = pylon.getLocationX();
		double pylonLocationY = pylon.getLocationY();
		double totalX = npcLocationX - pylonLocationX;
		double totalY = npcLocationY - pylonLocationY;
		if(totalX < 0)
			totalX = totalX * -1;
		if(totalY < 0)
			totalY = totalY * -1;
		double tmp = MathUtil.atan(totalY/totalX);
		npc.setHeading(tmp);
	}
}
