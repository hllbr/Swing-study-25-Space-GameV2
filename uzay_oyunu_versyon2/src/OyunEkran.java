
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class OyunEkran extends JFrame{

    public OyunEkran(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        //ANA EKRAN
        OyunEkran screen = new OyunEkran("SPACE GAME");
        
        screen.setResizable(false);
        
        screen.setFocusable(false);//bütün focusu taşıyıcı panele aktarmak için kullanılır.
        
        screen.setSize(800, 600);//screen size
        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game gamebuild = new Game();//THE OBJECT
        
        gamebuild.requestFocus();//key operation
        
        gamebuild.addKeyListener(gamebuild);//this operation is for understanding and adding keys
        
        gamebuild.setFocusable(true);//used to transfer the entire focus to the carrier panel.
        
        gamebuild.setFocusTraversalKeysEnabled(false);
        
        screen.add(gamebuild);
        
        screen.setVisible(true);
        //The order of the transactions made in this area should not be changed.
        
        
        
    }
}
