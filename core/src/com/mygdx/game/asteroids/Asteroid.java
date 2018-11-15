package com.mygdx.game.asteroids;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.tools.Delta;
import com.mygdx.game.shapes.Ngon;
import com.mygdx.game.shapes.ShapeRendererExt;

import java.util.ArrayList;

import static com.mygdx.game.states.AsteroidState.rn;
import static com.mygdx.game.Main.HIGHT;
import static com.mygdx.game.Main.WIDTH;
import static com.mygdx.game.Main.ft;

public class Asteroid {
    Color c = new Color();
    private Ngon shape;
    private int n = 8;
    private int r = 20;
    private Vector2 pos = new Vector2();
    private Vector2 vel = new Vector2();
    private float angle = 0;
    private boolean death = false;
    private int size = 3;
    private Delta dt_flash = new Delta(100 * ft);


    public Asteroid(Vector2 pos, int r, int n) {
        this(pos, r, n, 3);

    }

    public Asteroid(Vector2 pos, int r, int n, int size) {
        this.pos.set(pos);
        this.r = r;
        this.n = n;
        shape = new Ngon(pos, r, n);
        vel.set(ranVel());
        this.size = size;
        c.set((rn.nextInt(190)+65)/255f,(rn.nextInt(190)+65)/255f,(rn.nextInt(190)+65)/255f, 1);


    }

    public Asteroid() {
        this(new Vector2((int) (Math.random() * WIDTH), (int) (Math.random() * HIGHT)), 100, 5);
    }


    public Vector2 ranVel() {
        Vector2 pos = new Vector2();
        float m = 1.7f;
        pos.x = (float) (m * Math.cos(Math.toRadians(rn.nextInt(360))));
        pos.y = (float) (m * Math.sin(Math.toRadians(rn.nextInt(360))));

        return pos;
    }


    public void display(ShapeRendererExt sr) {
        if (dt_flash.isDone()) {
            float x = .2f;
            float y =(rn.nextInt(200)+55)/255f;
            c.set((rn.nextInt(190)+65)/255f,(rn.nextInt(190)+65)/255f,(rn.nextInt(190)+65)/255f, 1);
            dt_flash.reset();
        }

        sr.setColor(c);
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.ngon(shape);
    }

    public void update(float dt) {
        dt_flash.update(dt);
        angle+=1f;
        wrap();
        updateShape();
        move();

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

    public Ngon getShape() {
        return shape;
    }

    public void setShape(Ngon shape) {
        this.shape = shape;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(Vector2 vel) {
        this.vel.set(vel);
    }

    public float setRanAngle(int n) {
        return (float) Math.random() * (360);
    }

    public void move() {
        pos.add(vel);
    }

    public void updateShape() {
        shape = new Ngon(pos, r, n,angle);
    }

    public static ArrayList<Asteroid> generate(int n) {
        ArrayList<Asteroid> rocks = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            Vector2 pos = new Vector2();
            int r = 100;
            int num = rn.nextInt(8) + 3;
            rocks.add(new Asteroid(pos, r, num));
        }
        return rocks;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public Asteroid[] split() {
        if (size > 1) {
            Asteroid r1 = new Asteroid(this.pos.add(6,0), this.r / 2, n, size - 1);
            Asteroid r2 = new Asteroid(this.pos.add(-6,0), this.r / 2, n, size - 1);
            r1.setVel(this.vel);
            r2.setVel(new Vector2(vel.y,vel.x));
            return new Asteroid[]{r1, r2};

        }
        return new Asteroid[0];

    }

    public void wasShot() {
        this.death = true;

    }

    public boolean isDead() {
        return death;
    }

    public int getSize() {
        return size;
    }
}
