package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.asteroids.Ship;
import com.mygdx.game.tools.Delta;

import static com.mygdx.game.Main.font;
import static com.mygdx.game.Main.ft;
import static com.mygdx.game.Main.WIDTH;
import static com.mygdx.game.Main.HIGHT;


public class MenuState extends State {
    Delta dtStart = new Delta(20*ft);

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void handleInput() {

            if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) && dtStart.isDone()){
               gsm.push(new AsteroidState(gsm));
               dtStart.reset();
            }

    }

    @Override
    public void update(float dt) {
        handleInput();
        dtStart.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.getData().setScale(20,15);
        GlyphLayout layout = new GlyphLayout(font,"ASTEROIDS");
        font.draw(sb,layout, (WIDTH/2)-(layout.width/2),(HIGHT-200));
        font.getData().setScale(2);
        layout.setText(font,"PRESS ANY KEY");
        font.draw(sb,layout,(WIDTH/2)-(layout.width/2),(HIGHT/2)-200);
        if(Ship.finalScore!=0){
            layout.setText(font,"FINAL SCORE:"+Ship.finalScore);
            font.draw(sb,layout,(WIDTH/2)-(layout.width/2),(HIGHT/2));
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
