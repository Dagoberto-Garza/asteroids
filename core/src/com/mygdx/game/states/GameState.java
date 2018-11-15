package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Board;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;

import static com.mygdx.game.Main.ft;


public class GameState extends State {

    Board board = new Board();
    ShapeRenderer shape;
    float dt = 0;
    int numMoves = 0;

    public GameState() {
        super(new GameStateManager());
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);

    }

    @Override
    protected void handleInput() {
        if (dt > (8 *ft)) {
            if (!board.hasWon())
               // music.play(.7f);
                if (Gdx.input.isButtonPressed(0)) {
                    board.place(numMoves % 2);
                    numMoves++;
                    dt = 0;

                }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        this.dt += dt;
    }

    @Override
    public void render(SpriteBatch sb) {

        shape.begin();
        if (board.hasWon()) {
            Gdx.gl.glClearColor(0, 0, .4f, 1);
        } else
            Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        board.display(shape);
        shape.end();


    }

    @Override
    public void dispose() {

    }
}
