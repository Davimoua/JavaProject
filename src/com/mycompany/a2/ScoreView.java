package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	
	private Label time, totalScore, lastPylon,lives, carDamage, fuelLevel, sound, timeVal,
					livesVal, lastPylonVal, fuelLevelVal, carDamageVal, soundVal;
	
	public ScoreView()  {
		time = new Label("Time: ");
		timeVal = new Label();
		//totalScore = new Label();
		lives = new Label("Lives Left: ");
		livesVal = new Label();
		lastPylon = new Label("Highest Player Pylon: ");
		lastPylonVal = new Label();
		fuelLevel = new Label("Player Fuel Remaining: ");
		fuelLevelVal = new Label();
		carDamage = new Label("Player Damage level: ");
		carDamageVal = new Label();
		sound = new Label("Sound: ");
		soundVal = new Label();
		addLabels();
	}
	
	private void addLabels() {
		this.add(time);
		this.add(timeVal);
		this.add(lives);
		this.add(livesVal);
		this.add(lastPylon);
		this.add(lastPylonVal);
		this.add(fuelLevel);
		this.add(fuelLevelVal);
		this.add(carDamage);
		this.add(carDamageVal);
		this.add(sound);
		this.add(soundVal);
	}
	
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		GameWorld gw = (GameWorld) observable;
		timeVal.setText(Integer.toString(gw.getTick()));
		livesVal.setText(Integer.toString(gw.getLives()));
		lastPylonVal.setText(Integer.toString(gw.getLastPylon()));
		fuelLevelVal.setText(Integer.toString(gw.getFuelLevel()));
		carDamageVal.setText(Integer.toString(gw.getCarDamage()));
		soundVal.setText(gw.getSoundString());
	}
	
	
}
