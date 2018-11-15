package com.mygdx.game.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.snake.Snake;
import com.mygdx.game.states.AsteroidState;
import com.mygdx.game.states.GameState;
import com.mygdx.game.asteroids.Ship;
import com.mygdx.game.states.SnakeState;


public class DownComm extends Command {
    public DownComm(){
        name="Down";
        keyboard= Input.Keys.S;
    }
    @Override
    public void execute() {

        if (cls.equals(AsteroidState.class)) {
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                Ship.move(AsteroidState.player);
            }
        }
        if(cls.equals(SnakeState.class)){
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                Snake.move(SnakeState.player,new Vector2(0,-1));
               // System.out.println("down pressed");
            }
        }
    }
}
