
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
public class Spacecraft extends VectorSprite {
    public Spacecraft(){
        shape=new Polygon();
        shape.addPoint(20, 0);
        shape.addPoint(-10, 15);
        shape.addPoint(-10, -15);
        drawShape=new Polygon();
        drawShape.addPoint(20, 0);
        drawShape.addPoint(-10, 15);
        drawShape.addPoint(-10, -15);
        ROTATION=0.1;
        THRUST=6;
        active = true;


        xposition=450;
        yposition=300;
    
}   
     
    public void hit(){
        active = false;
        counter = 0;
        
    }
    
    public void reset(){
        xposition = 450;
        yposition = 300;
        xspeed = 0;
        yspeed = 0;
        active = true;
    }
    
    public void accelerate(){
        xspeed=Math.cos(angle)*THRUST;
        yspeed=Math.sin(angle)*THRUST;
    }
    
    public void rotateLeft(){
     angle-= ROTATION;
     

    }
    public void rotateRight() {
     angle+= ROTATION;  
    }
    
}
