package com.group23.app.utils;


/**
 * Class used in testing to simulate player actions without direct input from the player
 */
public class AutomaticPlayerAction {
    public AutomaticPlayerMovement movement;
    public AutomaticPlayerSpell spellcast;

    public AutomaticPlayerAction(AutomaticPlayerMovement move, AutomaticPlayerSpell spell){
        movement = move;
        spellcast = spell;
    }
}
