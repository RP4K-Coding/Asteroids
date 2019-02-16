
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RealProgramming4Kids
 */
public class VectorSprite {
    double xposition;
    double yposition;
    double xspeed;
    double yspeed;
    double angle;
    double ROTATION;
    double THRUST;
    Polygon shape,drawShape;
    boolean active;
    int counter;
    
      
    public void paint(Graphics g)  {
        g.drawPolygon(drawShape);
    }
    
    public void updatePosition(){
        xposition+=xspeed;
        yposition+=yspeed;
        counter ++;
        
        wraparound();
       
        
       int x, y;
       for(int i=0; i<shape.npoints; i++){
//           shape.xpoints[i] += xspeed;
//           shape.ypoints[i] += yspeed;
           x = (int)Math.round(shape.xpoints[i] * Math.cos(angle) -
                   shape.ypoints[i] * Math.sin(angle));
           y = (int)Math.round(shape.xpoints[i] * Math.sin(angle) +
                   shape.ypoints[i] * Math.cos(angle));
           drawShape.xpoints[i] = x;
           drawShape.ypoints[i] = y;
                   
       }
       drawShape.invalidate();
       drawShape.translate((int) xposition, (int)yposition);
                
    }
    public void wraparound() {
        if(xposition>900) {
            xposition=0;
        }
        if(xposition<0) {
            xposition=900;
        }
        if(yposition>600) {
            yposition=0;
        }
        if(yposition<0) {
            yposition=600;
        }
        
       
    
    }
    
}
