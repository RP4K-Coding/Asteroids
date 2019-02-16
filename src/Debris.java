
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
public class Debris extends VectorSprite {
    public Debris(double x, double y){
        shape=new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);
        shape.addPoint(0, 0);

        drawShape=new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);

        xposition = x;
        yposition = y;
        
        double h;
        h = Math.random()* 5 *Math.PI;
        angle = h;

        xspeed = Math.cos(h)*h;
        yspeed = Math.sin(h)*h;
    }

  
    
}
