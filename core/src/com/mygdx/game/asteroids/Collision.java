package com.mygdx.game.asteroids;

import com.mygdx.game.shapes.Ngon;
import com.mygdx.game.shapes.Triangle;

public class Collision {

    public boolean bulletRockColl(Bullet bullet,Ngon ngon){
       for(Triangle t: Triangle.triangulate(ngon)){
          if (bullet.getShape().overlaps(t))
              return true;
       }
       return false;
    }
    public boolean shipRockColl(Ship s, Ngon ngon){
        for(Triangle t: Triangle.triangulate(ngon)){
            if (s.getShape().overlaps(t))
                return true;
        }
        return false;
    }
}
