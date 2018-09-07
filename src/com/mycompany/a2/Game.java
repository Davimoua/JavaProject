package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Observer; 

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Label sound;
	private Container leftSide, rightSide, bottomSide;
	private Command moveLeft, moveRight, accelerate, brake, fuelCollision, 
					birdCollision, pylonCollision,  giveAboutInfo, giveHelpInfo,
					turnSoundOnOff, exitGame, tickClock, displayMap, switchStrategy, 
					npcCollision;
	private Toolbar toolBar;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		mv = new MapView();
		sv = new ScoreView();
		leftSide = new Container();
		bottomSide = new Container();
		gw.addObserver(mv);
		gw.addObserver(sv);
		/* 
		 * The start of the GUI for the buttons
		 * */
		this.setLayout(new BorderLayout());
		//initializing containers
		sv.setLayout(new FlowLayout(Component.CENTER));
		mv.setLayout(new FlowLayout());
		leftSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		bottomSide.setLayout(new FlowLayout(Component.CENTER));
		//setting containers to border layout sections
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.WEST, leftSide);
		this.add(BorderLayout.SOUTH, bottomSide);
		commandObjects();
		addButtonCommands();
		addKeyboardCommands();
		toolBar = new Toolbar();
		this.setToolbar(toolBar);
		this.setTitle("Race Car Game");
		//Toolbar creation
		toolBar.addCommandToSideMenu(accelerate);
		toolBar.addCommandToSideMenu(turnSoundOnOff);
	    toolBar.addCommandToSideMenu(giveAboutInfo);
	    toolBar.addCommandToSideMenu(exitGame);
	    CheckBox soundCheckBox = new CustomCheckBox("Sound Status: ", turnSoundOnOff);
	    soundCheckBox.setSelected(gw.getSound());
	    toolBar.addCommandToSideMenu(turnSoundOnOff);
	    toolBar.addCommandToRightBar(giveHelpInfo);
		//toolBar.addCommandToRightBar(giveAboutInfo);
		this.show();
	}
	
	private void addButtonCommands() {
		leftSide.add(new CustomButton(switchStrategy));
		leftSide.add(new CustomButton(accelerate));
		leftSide.add(new CustomButton(brake));
		leftSide.add(new CustomButton(moveRight));
		leftSide.add(new CustomButton(moveLeft));
		leftSide.add(new CustomButton(exitGame));
		bottomSide.add(new CustomButton(npcCollision));
		bottomSide.add(new CustomButton(pylonCollision));
		bottomSide.add(new CustomButton(birdCollision));
		bottomSide.add(new CustomButton(fuelCollision));
		bottomSide.add(new CustomButton(tickClock));
		
	}
	
	private void commandObjects() {
		
		moveLeft = new CLeft("Left", gw);
		moveRight = new CRight("Right", gw);
		brake = new CBrake("Brake", gw);
		birdCollision = new CCollideBird("Bird Collision", gw);
		pylonCollision = new CCollidePylon("Pylon Collision", gw);
		giveAboutInfo = new CInfo("Additional Information", gw);
		turnSoundOnOff = new CSound("Sound Button", gw);
		exitGame = new CExit("Exit",gw);
		tickClock = new CTick("Tick",gw);
		accelerate = new CAccelerate("Accelerate", gw);
		fuelCollision = new CFuelCan("Pickup Fuel Can", gw);
		giveAboutInfo = new CInfo("About", gw);
		giveHelpInfo = new CHelp("Help",gw);
		npcCollision = new CCollideNPC("NPC Collision", gw);
		switchStrategy = new CStrategy("Switch Strategy", gw);
	}
	
	private void addKeyboardCommands() {
		this.addKeyListener('a', accelerate);
		this.addKeyListener('l', moveLeft);
		this.addKeyListener('r', moveRight);
		this.addKeyListener('b', brake);
		this.addKeyListener('f', fuelCollision);
		this.addKeyListener('g', birdCollision);
		this.addKeyListener('t', tickClock);
		this.addKeyListener('e', exitGame);
		this.addKeyListener('m', displayMap);
	}

}
