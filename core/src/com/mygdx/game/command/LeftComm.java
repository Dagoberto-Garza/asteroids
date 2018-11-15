package com.mygdx.game.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.snake.Snake;
import com.mygdx.game.states.AsteroidState;
import com.mygdx.game.asteroids.Ship;
import com.mygdx.game.states.SnakeState;


public class LeftComm extends Command {
    public LeftComm() {
        name = "Left";
        keyboard = Input.Keys.A;

    }

    @Override
    public void execute() {

        if (cls.equals(AsteroidState.class)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                Ship.rotate(AsteroidState.player, 4f);
            }
        }
        if (cls.equals(SnakeState.class)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                Snake.move(SnakeState.player, new Vector2(-1, 0));
                //System.out.println("left pressed");
            }
        }
    }
}
