/* FlappyBird based game.
 * 
 * Author: Marvin Bolanos
 * Date: 5/21/15
 * period 3
 * 
 * 
 */
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args)
	  {
	    JFrame w = new JFrame("Simple Window");
	    w.setBounds(100, 100, 500, 700);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    FlappyWorld panel = new FlappyWorld();
	    w.add(panel);
	    Sound.sound1.loop();
	    w.addKeyListener(panel);
	    w.addMouseListener(panel);
	    w.setResizable(false);
	    w.setVisible(true);
	    panel.run();
	    
	  }
	
	
}
