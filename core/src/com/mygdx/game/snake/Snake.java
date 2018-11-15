package com.mygdx.game.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.shapes.Circle;
import com.mygdx.game.shapes.ShapeRendererExt;

import java.util.ArrayList;

import static com.mygdx.game.Main.HIGHT;
import static com.mygdx.game.Main.WIDTH;

public class Snake {
    private  static Circle head;
    private static ArrayList<Circle> body = new ArrayList<>();
    private static Vector2 pos = new Vector2();
    float angle = 0;
    float r = 50;
    Color c = new Color(1, 0, 0, 1);

    public Snake() {
        pos = new Vector2(WIDTH / 2, HIGHT / 2);
        head = new Circle(new Vector2(pos.x,pos.y),r);

        body.add(head);


    }

    public Circle getHead() {
        return head;
    }

    public void display(ShapeRendererExt sr) {
        sr.setColor(c);
        for (int x = 0; x < body.size(); x++) {
            sr.circle(body.get(x));
            System.out.println();

        }
     
   


    }
    public void update(float dt){

    }
    public void updateBody(){

    }


    public static void move(Snake s,Vector2 v) {
        s.getPos().add(v);

    }

    public ArrayList<Circle> getBody() {
        return body;
    }


    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }


}
