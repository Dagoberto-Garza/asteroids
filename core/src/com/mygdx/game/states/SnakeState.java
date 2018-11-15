package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.snake.Snake;
import com.mygdx.game.command.Command;
import com.mygdx.game.shapes.ShapeRendererExt;

import static com.mygdx.game.Main.queue;

public class SnakeState extends State {
    ShapeRendererExt shape;
    public static Snake player;

    public SnakeState(GameStateManager gsm) {
        super(gsm);
        shape = new ShapeRendererExt();
        shape.setAutoShapeType(true);
        player = new Snake();
    }

    @Override
    protected void handleInput() {
        for (Command c : queue) {
            c.execute();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shape.begin();
        player.display(shape);
        shape.end();
    }

    @Override
    public void dispose() {

    }
}
