package com.mygdx.game.asteroids;

import com.mygdx.game.shapes.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.tools.Delta;

import static com.mygdx.game.Main.HIGHT;
import static com.mygdx.game.Main.WIDTH;
import static com.mygdx.game.Main.ft;

public class Bullet {

    boolean death=false;
    Delta dt=new Delta(50*ft);
    Circle shape;
    Vector2 vel=new Vector2();

    public Bullet(Vector2 pos, float theta){
        shape = new Circle(new Vector2(pos),2f);
        float m =15f;
        float vx = m*(float)(Math.cos(Math.toRadians(theta)));
        float vy = m*(float)(Math.sin(Math.toRadians(theta)));
        this.vel.set(vx,vy);
    }
    public void update(float dt){
        wrap();
        shape.center.add(vel);
        this.dt.update(dt);
        if(this.dt.isDone())
            death=true;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public Circle getShape() {
        return shape;
    }

    public void setShape(Circle shape) {
        this.shape = shape;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public void wrap() {
        if (shape.center.x >= WIDTH) {
            shape.center.x = 1;
        }
        if (shape.center.y >= HIGHT) {
            shape.center.y = 1;
        }
        if (shape.center.x <= 0) {
            shape.center.x = WIDTH - 1;
        }
        if (shape.center.y <= 0) {
            shape.center.y = HIGHT - 1;
        }
    }


    public void collided() {
        this.death=true;
    }
}
