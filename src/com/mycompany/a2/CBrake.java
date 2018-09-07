package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CBrake extends Command {

	private GameWorld gw;
		
	public CBrake(String commandName, GameWorld gameWorld) {
		super(commandName);
		this.gw = gameWorld;
	}
		
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.brake();
	}
}
