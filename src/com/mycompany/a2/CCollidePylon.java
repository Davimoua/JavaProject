package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CCollidePylon extends Command {
	private GameWorld gw;
	
	public CCollidePylon(String commandName, GameWorld gameWorld) {
		super(commandName);
		this.gw = gameWorld;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.pylonCollision();
	}
}
