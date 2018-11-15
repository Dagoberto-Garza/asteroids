package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.asteroids.Asteroid;
import com.mygdx.game.asteroids.Bullet;
import com.mygdx.game.asteroids.Collision;
import com.mygdx.game.asteroids.Ship;
import com.mygdx.game.command.Command;
import com.mygdx.game.shapes.Circle;
import com.mygdx.game.shapes.ShapeRendererExt;
import com.mygdx.game.shapes.Triangle;
import com.mygdx.game.tools.Delta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.mygdx.game.Main.*;

public class AsteroidState extends State {

    public static Random rn = new Random();
    ShapeRendererExt shape;
    Collision collision = new Collision();
    public static BitmapFont font = new BitmapFont();
    public static Ship player;
    public static ArrayList<Asteroid> rocks = new ArrayList<>();
    public static Delta dt_spawn;
    private Sound boom ;
    private Sound gameOver;
    private Music music;
    public AsteroidState(GameStateManager gsm) {
        super(gsm);
        music =  Gdx.audio.newMusic(Gdx.files.internal("sfx/VeridisQuo.mp3"));
       // music.setPosition(200);
        music.setVolume(1f);
        boom = Gdx.audio.newSound(Gdx.files.internal("sfx/boom.mp3"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sfx/gameover.mp3"));
        rocks = Asteroid.generate(8);
        shape = new ShapeRendererExt();
        shape.setAutoShapeType(true);
        player = new Ship();
        dt_spawn = new Delta(120*ft);
        System.out.print(music.getPosition()+" "+music.getVolume());
        music.play();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.push(new PauseState(gsm));
        }
        for(Command c: queue){
            c.execute();
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        player.update(dt);
        collisionHandler(dt);
        if(player.isGameOver()){
            gameOver.play(1f);
            music.stop();
            gsm.pop();

        }
        spawn(dt);


    }
public void spawn(float dt){
        dt_spawn.update(dt);
     if(dt_spawn.isDone()){
         rocks.add(new Asteroid(new Vector2(0,0),100,rn.nextInt(8)+3));
         dt_spawn.reset();
     }

}

    public void collisionHandler(float dt) {
        int newScore = 0;
        for (Asteroid a : rocks) {
            for (Triangle t : Triangle.triangulate(a.getShape())) {
                boolean a1=player.getShape().overlaps(t);
                boolean b=t.overlaps(player.getShape());
                boolean c=!player.isGameOver();

                if ((a1 || b) && c) {
                    player.setDeath(true);
                    boom.play(.7f);
                }
            }

        }
        for (Bullet b : player.getBullets()) {
            for (Asteroid a : rocks) {
                if (!b.isDeath()) {
                    if (collision.bulletRockColl(b, a.getShape())) {
                        if (a.getSize() == 3) {
                            newScore = 50 + player.getScore();
                            player.setScore(newScore);
                        }
                        if (a.getSize() == 2) {
                            newScore = 70 + player.getScore();
                            player.setScore(newScore);
                        }
                        if (a.getSize() == 1) {
                            newScore = 150 + player.getScore();
                            player.setScore(newScore);
                        }
                        boom.play(.5f);
                        a.wasShot();
                        b.collided();

                    }
                }
            }
        }
        ArrayList<Asteroid> tempRocks = new ArrayList<>();
        for (int y = rocks.size() - 1; y >= 0; y--) {
            if (rocks.get(y).isDead()) {
                tempRocks.addAll(Arrays.asList(rocks.get(y).split()));
                rocks.remove(y);

            }
        }
        rocks.addAll(tempRocks);
        rocks.forEach(x -> x.update(dt));

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        sb.begin();
        font.setColor(1, 1, 1, 1);

        GlyphLayout draw = font.draw(sb, "Score: " + player.getScore(), WIDTH - 900, HIGHT - 100);
        sb.end();

        shape.begin();
        player.display(shape);
        rocks.forEach(x -> x.display(shape));
        shape.end();

    }

    @Override
    public void dispose() {
        boom.dispose();

    }


    public static void clearPath() {
        Circle c = new Circle(new Vector2(WIDTH/2,HIGHT/2),200);
        for(Asteroid a: rocks){
            boolean b=false;
            for(Triangle t: Triangle.triangulate(a.getShape())){
                if(c.overlaps(t)){
                    b=true;
                }
            }
            if(b)
                a.setPos(new Vector2(0,0));
        }
    }
}
