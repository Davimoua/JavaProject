package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to exit the game
 * @author Kyle Szombathy
 *
 */
public class CExit extends Command {
    private GameWorld gw;

    /**
     * Constructor that passes command name and gameWorld object
     * @param commandName Name of the command
     * @param gameWorld Object necessary to use in actionPerformed
     */
    public CExit(String commandName, GameWorld gameWorld) {
        super(commandName);
        this.gw = gameWorld;
    }

    /**
     * Perform the action related to this commands name
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.exit();
    }
}
