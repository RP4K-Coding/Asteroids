/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
//import java.util.ArrayList<E>;


/**
 *
 * @author RealProgramming4Kids
 */
public class Asteroids extends Applet implements KeyListener, ActionListener{
    int[] x,y;
    Spacecraft ship;
    Timer timer;
    Image offscreen;
    Graphics offg;
    boolean upKey, leftKey, rightKey, fireKey;
    ArrayList<Asteroid> asteroidlist;
    ArrayList<Bullet> BulletBill;
    ArrayList<Debris> DebrisList;
    AudioClip shipHit, asteroidDestruction, bulletSound, spaceshipThrusters;
    int SCORE;
    int LIVES;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        shipHit = getAudioClip(getCodeBase(), "ship destroyed.wav");
        asteroidDestruction = getAudioClip(getCodeBase(), "explosion.wav");
        bulletSound = getAudioClip(getCodeBase(), "laser.wav");
//        spaceshipThrusters = getAudioClip(getCodeBase(), "thrusters.wav");
        SCORE=0;
        LIVES=3;
        this.setSize(900, 600);
        this.addKeyListener(this);
        x=new int[3];
        x[0]=15;
        x[1]=0;
        x[2]=30;
        y=new int[]{0, 30, 30};
        ship=new Spacecraft();
        timer=new Timer(20, this);
        offscreen=createImage(this.getWidth(),this.getHeight());
        asteroidlist = new ArrayList();
        BulletBill = new ArrayList();
        DebrisList = new ArrayList();
        offg=offscreen.getGraphics();
        for (int i = 0; i < 6; i++)
            {
                asteroidlist.add(new Asteroid());
            }
        
        start();
        
        // TODO start asynchronous download of heavy resources
    }
    public boolean isRespawnSafe(){
        int x, y , h;
         for(int i =0; i < asteroidlist.size(); i++){
             x=(int)asteroidlist.get(i).xposition - 450;
             y=(int)asteroidlist.get(i).xposition - 300;
             h=(int)Math.sqrt(x*x+y*y);
             if ( h < 150 ) {
                 return false;
             }
             
         }
         return true;
           
    }
    public void checkCollision(){
        for(int i =0; i < asteroidlist.size(); i++) {
            if ( collision(ship, asteroidlist.get(i)) == true){
                ship.hit();
                
                shipHit.play();
            }
            for(int b =0; b < BulletBill.size(); b++) { 
                if ( collision(BulletBill.get(b), asteroidlist.get(i))){
                    BulletBill.get(b).active = false;
                    asteroidlist.get(i).active = false;
                    for(int d = 0; d < 15; d++){
                        DebrisList.add(new Debris(asteroidlist.get(i).xposition, asteroidlist.get(i).yposition));
                    }
                    
                    
                }
            }
            
        }
    }
    public void respawnShip(){
        if(ship.active == false && ship.counter > 40 && isRespawnSafe() )
        {    
            ship.reset();
            LIVES -=1;
        }
    }
    
    public void actionPerformed(ActionEvent e){
        keyCheck();
        respawnShip();
        ship.updatePosition();
        
        for (int i = 0; i < asteroidlist.size(); i++)
        {
            asteroidlist.get(i).updatePosition();
        }
        
        for (int i = 0; i < BulletBill.size(); i++)
        {
            BulletBill.get(i).updatePosition();
            if(BulletBill.get(i).counter == 50 || BulletBill.get(i).active ==false){
                BulletBill.remove(i);
            }
        }
        
        for (int i = 0; i < DebrisList.size(); i++)
        {
            DebrisList.get(i).updatePosition();
        }
        
        checkCollision();
        checkAsteroidDestruction();
        
    }
    public void checkAsteroidDestruction() {
        for(int i=0; i<asteroidlist.size(); i++) {
            if(asteroidlist.get(i).active == false){
            if(asteroidlist.get(i).size>1){
               asteroidlist.add(new Asteroid(asteroidlist.get(i).xposition, 
               asteroidlist.get(i).yposition, asteroidlist.get(i).size - 1));
               asteroidlist.add(new Asteroid(asteroidlist.get(i).xposition, 
               asteroidlist.get(i).yposition, asteroidlist.get(i).size - 1));
            }
            asteroidlist.remove(i);
            asteroidDestruction.play();
            SCORE +=1;
            }
        }
    }
    public void paint(Graphics g)  {
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        offg.setColor(Color.green);
        offg.drawString("SCORE:" + SCORE, 700, 500);
        offg.drawString("LIVES" + LIVES, 100, 500);
        
        if(LIVES > 0){
            if(ship.active){
                ship.paint(offg);
            }
        

            if(asteroidlist.isEmpty()){
                Font myFont = new Font("Courier New", 1, 150);
                offg.setFont(myFont);
                offg.drawString("You Win", 110, 300);

            }

            if(LIVES == 0){
                Font myFont2 = new Font("Courier New", 20, 100);
                Font myFont = new Font("Courier New", 1, 150);
                offg.setFont(myFont2);
                offg.drawString("You Lose", 110, 300);
            }

            if (ship.active){
                ship.paint(offg);
            }
            offg.setColor(Color.orange);
            for (int i = 0; i < asteroidlist.size(); i++)
            {
                asteroidlist.get(i).paint(offg);
            }

            for (int i = 0; i < BulletBill.size(); i++)
            {
                offg.setColor(Color.green);
                BulletBill.get(i).paint(offg);
            }
            
            for (int i = 0; i < DebrisList.size(); i++)
            {
                DebrisList.get(i).paint(offg);
            }
        }else{
            Font myFont3 = new Font("Courier New", 20, 20);
 
            offg.setFont(myFont3);
            offg.drawString("YOU LOSE GAME OVER", 110, 300);

        }
        g.drawImage(offscreen, 0, 0, this);
        
        repaint();
        
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    public void keyPressed (KeyEvent e)  {
   
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
          rightKey = true;               
        
        }
           
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
          leftKey = true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            upKey = true;
            spaceshipThrusters.loop();
           
        }
        
         if(e.getKeyCode()==KeyEvent. VK_W) {
             fireKey = true;
         }
        repaint();
        
        
        
        
   repaint();
    }
    public void keyCheck (){
        if(upKey){
            ship.accelerate();
            
        }
        if(leftKey) {
            ship.rotateLeft();
        }
        if(rightKey) {
            ship.rotateRight();
        }
        if(fireKey) {
            shoot();
        }
       
        
    }
   
    public void keyReleased (KeyEvent e)  {
         if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
          rightKey = false;               
        
        }
           
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
          leftKey = false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            upKey = false;
           
        }
        
        if(e.getKeyCode()==KeyEvent.VK_W) {
            fireKey = false;
        }
        
    }
    public void keyTyped (KeyEvent e) {
        
    }
    public void start()
    {
        timer.start();
    }
    public void stop()
    {
        timer.stop();
    }
    public boolean collision(VectorSprite thing1, VectorSprite thing2){
        int x,y;
        for(int i = 0; i<ship.drawShape.npoints;i++){

            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if(thing2.drawShape.contains(x,y)) {
                return true;
            }
        }
        
        for(int i = 0; i<ship.drawShape.npoints;i++){

            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if(thing1.drawShape.contains(x,y)) {
                return true;
            }
        }
       
        return false;
    }
    
    public void shoot() {
        if(ship.counter > 13 && ship.active){
            BulletBill.add(new Bullet(ship.xposition, ship.yposition, ship.angle)); 
            ship.counter = 0;
            bulletSound.play();
        }
    }
    // TODO overwrite start(), stop() and destroy() methods
}

