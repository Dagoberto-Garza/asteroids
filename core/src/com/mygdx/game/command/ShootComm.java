package com.mygdx.game.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.states.AsteroidState;
import com.mygdx.game.asteroids.Ship;

public class ShootComm extends Command {
    public ShootComm(){

    }
    @Override
    public void execute() {
        if (cls.equals(AsteroidState.class)) {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                Ship.shoot(AsteroidState.player);
            }

        }
    }
}
