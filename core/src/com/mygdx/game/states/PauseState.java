package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.command.Command;

import static com.mygdx.game.Main.HIGHT;
import static com.mygdx.game.Main.WIDTH;
import static com.mygdx.game.Main.font;

public class PauseState extends State {
    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.pop();
            Command.cls=AsteroidState.class;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.getData().setScale(4);
        GlyphLayout layout = new GlyphLayout(font, "PAUSED");
        font.draw(sb, layout, (WIDTH / 2) - (layout.width / 2), HIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
