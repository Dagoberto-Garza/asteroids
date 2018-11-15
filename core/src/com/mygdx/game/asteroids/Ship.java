package com.mygdx.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.AsteroidState;
import com.mygdx.game.tools.Delta;
import com.mygdx.game.shapes.ShapeRendererExt;
import com.mygdx.game.shapes.Triangle;

import java.util.ArrayList;

import static com.mygdx.game.Main.WIDTH;
import static com.mygdx.game.Main.HIGHT;
import static com.mygdx.game.Main.ft;

public class Ship {
    private Triangle shape;
    private Vector2 pos = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    private float r = 30f;
    private float angle = 0;
    private Vector2 vel = new Vector2();
    private ArrayList<Bullet> bullets = new ArrayList<>(4);
    private Delta dt_shoot;
    private boolean death = false;
    private int score = 0;
    private int lives = 3;
    public static int finalScore = 0;
    private Delta dt_deathcount;
    private Sound bleep;
    private int tempScore = 0;
    private boolean[] debug = new boolean[3];

    public Ship() {
        shape = Triangle.updatePoints(pos, angle, r);
        dt_shoot = new Delta(12 * ft);
        lives = 3;
        death = false;
        dt_deathcount = new Delta(3 * ft);
        bleep = Gdx.audio.newSound(Gdx.files.internal("sfx/bleep.mp3"));
    }

    public void display(ShapeRendererExt sr) {
        ArrayList<Triangle> livesDrawList = new ArrayList<>();
        for (int x = 0; x < lives - 1; x++) {
            livesDrawList.add(new Triangle(shape));
        }

        sr.set(ShapeRendererExt.ShapeType.Filled);
        sr.setColor(new Color(1, 0, 0, 1));
        sr.triangle(shape);
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isDeath())
                sr.circle(bullets.get(i).getShape());
        }
        for (int x = 0; x < lives - 1; x++) {
            float inix = WIDTH - 300;
            float iniy = HIGHT - 100;
            livesDrawList.get(x).setPos(new Vector2(inix + (50 * x), iniy));
            sr.triangle(livesDrawList.get(x));
        }


    }

    public void displaySB(SpriteBatch sb) {

    }

    public void updateShape() {
        shape = Triangle.updatePoints(pos, angle, r);

    }

    public Triangle getShape() {
        return shape;
    }

    public void setShape(Triangle shape) {
        this.shape = shape;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public float getR() {
        return r;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public static void move(Ship s) {
        float m = .5f;
        float x = (float) (m * Math.cos(Math.toRadians((s.getAngle()))));
        float y = (float) (m * Math.sin(Math.toRadians((s.getAngle()))));
        s.vel.add(x, y);
    }


    public void rotate(float angle) {
        this.angle += angle;
    }

    public static void rotate(Ship s, float angle) {
        s.rotate(angle);
    }

    public void wrap() {
        if (pos.x >= WIDTH) {
            pos.x = 1;
        }
        if (pos.y >= HIGHT) {
            pos.y = 1;
        }
        if (pos.x <= 0) {
            pos.x = WIDTH - 1;
        }
        if (pos.y <= 0) {
            pos.y = HIGHT - 1;
        }
    }

    public void update(float dt) {
        dt_shoot.update(dt);
        dt_deathcount.update(dt);
        vel.x *= .97f;
        vel.y *= .97f;

        pos.add(vel);
        wrap();
        updateShape();
        finalScore = score;

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(dt);
        }

    }

    public static void shoot(Ship s) {
        s.shoot();
    }

    public void shoot() {
        if (dt_shoot.isDone()) {
            bleep.play(.8f);
            if (bullets.size() < 4) {
                bullets.add(new Bullet(pos, angle));
            } else {
                for (int i = bullets.size() - 1; i >= 0; i--) {
                    if (bullets.get(i).isDeath()) {
                        bullets.get(i).setDeath(true);
                        bullets.remove(i);
                    }
                }
                if (bullets.size() < 4)
                    bullets.add(new Bullet(pos, angle));
            }

            dt_shoot.reset();
        }
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setDeath(boolean death) {
        this.death = death;
        if (death && dt_deathcount.isDone()) {
            pos.set(WIDTH / 2, HIGHT / 2);
            this.death = false;
            lives -= 1;
            angle = 0;
            dt_deathcount.reset();

        }
        AsteroidState.clearPath();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public boolean getDeath() {
        return death;
    }

    public void extraLife(int goal) {


    }

    public void setTempScore(int score) {
        tempScore += score;
    }

    public boolean isGameOver() {
        return lives == 0;
    }

    public void setDebug(boolean[] debug) {
        this.debug = debug;
    }
}
