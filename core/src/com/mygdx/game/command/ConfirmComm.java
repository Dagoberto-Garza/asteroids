package com.mygdx.game.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.states.GameState;



public class ConfirmComm extends Command {
    public ConfirmComm(){
        name="Confirm";
        keyboard= Input.Keys.ENTER;
    }
    @Override
    public void execute() {
        if(pressed()){
            if (cls.equals(GameState.class)) {

            }
        }
        if (cls.equals(GameState.class)) {
            if(Gdx.input.isKeyPressed(Input.Keys.C)){

            }
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){

            }
        }
    }
}
