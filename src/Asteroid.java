
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RealProgramming4Kids
 */
public class Asteroid extends VectorSprite {
     
    int size; 
    public void initializeAsteroid(){
    
        
    shape = new Polygon();
    shape.addPoint(15*size, 6*size);
    shape.addPoint(7*size, 17*size);
    shape.addPoint(-13*size, 8*size);
    shape.addPoint(-11*size, -10*size);
    shape.addPoint(12*size, -16*size);
    drawShape = new Polygon();
    drawShape.addPoint(15*size, 6*size);
    drawShape.addPoint(7*size, 17*size);
    drawShape.addPoint(-13*size, 8*size);
    drawShape.addPoint(-11*size, -10*size);
    drawShape.addPoint(12*size, -16*size);
    ROTATION=0.1;
    THRUST=1;

        xposition=450;
        yposition=300;
        
        double h, a;
        h = Math.random() + 5;
        a = Math.random()* 5*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
        
        h = Math.random() * 400+100;
        a = Math.random()*5*Math.PI;
        xposition = Math.cos(a)*h + 450;
        yposition = Math.sin(a)*h + 300;
        active = true;
        
    
     }
     public Asteroid(){
         size = 2;
         initializeAsteroid();
     }
     public Asteroid(double x, double y, int s) {
         size = s;
         initializeAsteroid();
         xposition = x;
         yposition = y;        
     }
     
     public void updatePosition() {
         angle += ROTATION;
         super.updatePosition();
     }
}
