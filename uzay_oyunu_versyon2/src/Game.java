
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fıre{
    private int x ;
    private int y;
    //these variables are fire coordinates

    public Fıre(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
public class Game extends JPanel implements KeyListener,ActionListener{//taşıyıcı sistem
    //sub class
    
    Timer tımer = new Timer(15, this);//this methods is important for actionperformed of operations
    //this method allows the ball to accelerate
    
    private int passingTıme = 0;
    
    private int shotMade = 0 ;
    
    private BufferedImage ımage;//this ımage is a png file
    
    private ArrayList<Fıre> fıres_array = new ArrayList<Fıre>();
    
    private int fıreUpY = 1;//ateşdırY
    
    private int ballX = 0;//topx
    
    private int ballCornerX = 2;//topdırX
    
    private int spaceShipX = 0;
    
    private int spaceX = 20;//dırUzayX
    public boolean controlMachine(){
        for(Fıre f : fıres_array){
            
         
        if(new Rectangle(f.getX(),f.getY(),15,25).intersects(new Rectangle(ballX,0,20,20))){
           
                return true;
            }

        }
        return false;
    }
    public Game() {//this area is constructor
        try {
            ımage = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.DARK_GRAY);
        tımer.start();
        
    }

    @Override
    public void paint(Graphics g) {//this area is in component
        passingTıme += 5;
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.red);
        //ı need a ball
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(ımage, spaceShipX, 490 ,ımage.getWidth()/10,ımage.getHeight()/10, this);
         for(Fıre ates : fıres_array){
            if(ates.getY() < 0){
                fıres_array.remove(ates);
            }
        }
        g.setColor(Color.CYAN);
        for(Fıre f : fıres_array){
            g.fillRect(f.getX(), f.getY(), 15, 25);
        }
        if(controlMachine()){
            tımer.stop();
            String mesaj ="kazandınız...\n"
                    + "yapılan atış miktarı :"+shotMade+"\n"
                    + "Geçen Süre : "+passingTıme/1000.0;
            JOptionPane.showMessageDialog(this, mesaj);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {//this arae is in jcomponent
        //repaint calls paint
        //this method is used in 2d games
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int pushkey = e.getKeyCode();
        if(pushkey == KeyEvent.VK_LEFT){
            if(spaceShipX <= 0){
                spaceShipX = 0;
            }else{
                spaceShipX -= spaceX;
            }
            
        }else if(pushkey == KeyEvent.VK_RIGHT){
            if(spaceShipX >=740){
                spaceShipX = 740;
            }else{
                spaceShipX += spaceX;
            }
        }else if(pushkey == KeyEvent.VK_SPACE){
            fıres_array.add(new Fıre(spaceShipX+14, 460));
            shotMade++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Fıre f :fıres_array){
            f.setY(f.getY() -fıreUpY);
        }
        //this area is critical for us
        //this method is sub class of actionlistener interface
        ballX += ballCornerX;
        if(ballX >=750){
            ballCornerX = -ballCornerX;
        }
        if(ballX <= 0){
            ballCornerX = -ballCornerX;
        }
        repaint();
    }
   
    
    
}
