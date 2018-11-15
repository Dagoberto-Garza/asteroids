package com.mygdx.game.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.snake.Snake;
import com.mygdx.game.states.AsteroidState;
import com.mygdx.game.asteroids.Ship;
import com.mygdx.game.states.SnakeState;

public class UpComm extends Command {
    public UpComm(){
        name="Up";
        keyboard= Input.Keys.W;
    }
    @Override
    public void execute() {

        if (cls.equals(AsteroidState.class)) {
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                Ship.move(AsteroidState.player);
            }
        }
        if(cls.equals(SnakeState.class)){
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                Snake.move(SnakeState.player,new Vector2(0,1));
            }
        }
    }
}
