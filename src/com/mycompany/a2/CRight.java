package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CRight extends Command {
	private GameWorld gw;
	
	public CRight(String commandName, GameWorld gameWorld) {
		super(commandName);
		this.gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.right();
	}
}