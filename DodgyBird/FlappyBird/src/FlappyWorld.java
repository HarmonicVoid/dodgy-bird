import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//https://www.youtube.com/watch?v=oynZhQjMv0c

public class FlappyWorld extends JPanel implements KeyListener, MouseListener{

	public static final int DRAWING_WIDTH = 700;
	public static final int DRAWING_HEIGHT = 800;
	private Image flappyBackground, playButton, gameoverPic, title;
	private MovingImage ground;
	private MovingImage enemy, enemy2, enemy3, enemy4, enemy5;
	private FlappyBird flappyBird;
	private int width;
	private int height, x;
	private int fX = DRAWING_WIDTH/2;
	private int score;
	private int highScore = 0;
	public boolean reset;
	private int gameState;

	
	public FlappyWorld() {

		reset = false;
		gameState = 0;
		flappyBackground = (new ImageIcon("nyanBackground.png")).getImage();
		title = (new ImageIcon("Title.png")).getImage();
		gameoverPic = (new ImageIcon("game_over.png")).getImage();
		playButton = (new ImageIcon("tutorial.png")).getImage();
		//flappyGround = (new ImageIcon("flappyGround.png")).getImage();
		flappyBird = new FlappyBird(fX - 20, 250);
		ground = new MovingImage("flappyGround.png", 0, 595, 1300, 205);
		enemy = new MovingImage("nyan.png", 900, 0, 300,100);
		enemy2 = new MovingImage("nyan.png", 900, 0, 300,100);
		enemy3 = new MovingImage("nyan.png", 900, 0, 300,100);
		enemy4 = new MovingImage("nyan.png", 900, 0, 300,100);
		enemy5 = new MovingImage("nyan.png", 900, 0, 300,100);


	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		width = getWidth();
		height = getHeight();
		 
		double ratioX = width/700.0;    
		double ratioY = height/800.0;
		 
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);
		Font a = new Font("Serif",Font.PLAIN , 40);
		
		if ( gameState == 0 ) {
			
			// Background
			g.drawImage(flappyBackground, 0, -70, 1000, 800, this);
			//g.drawImage(flappyBackground, 260, -70, 260, 800, this);
			//g.drawImage(flappyBackground, 520, -70, 260, 800, this);
			g.drawImage(title, 65, 30, 600, 400, this);
			
			//Buttons
			g.drawImage(playButton, fX - 115, 300, 250, 200, this);
			// Ground
			g.fillRect(0, 593, 1000, 3);
			ground.draw(g,this);
			flappyBird.draw(g,this);
			flappyBird.onASurface = false;
			flappyBird.draw(g,this);
			repaint();

			
		
		}
		
		if (gameState == 1) {
			// Background
			g.drawImage(flappyBackground, 0, -70, 1500, 800, this);
			//g.drawImage(flappyBackground, 260, -70, 260, 800, this);
			//g.drawImage(flappyBackground, 520, -70, 260, 800, this);
			g.setColor(Color.BLACK);
			g.fillRect(0, 593, 1000, 3);
			ground.draw(g,this);
			enemy.draw(g,this);
			enemy2.draw(g,this);
			enemy3.draw(g,this);
			enemy4.draw(g,this);
			enemy5.draw(g,this);
			flappyBird.draw(g,this);

			g.setColor(Color.WHITE);
			g.setFont(a);
			g.drawString("Score"+ " " + score, 20, 30); 
			
			
		}
		
		if (gameState == 2) {
					
			g.drawImage(flappyBackground, 0, -70, 1000, 800, this);
			g.fillRect(0, 593, 1000, 3);
			ground.draw(g,this);
			ground.moveByAmount(2, 0);
			g.drawImage(gameoverPic, fX - 190, 300, 400, 100, this);
			g.setColor(Color.WHITE);
			g.setFont(a);
			g.drawString("HighScore"+ " " + highScore, 250, 500); 
			flappyBird = new FlappyBird(fX - 20, 250);
			g.drawString("Click to replay!", 230, 550); 
			
			enemy.moveByAmount(6, 0);
			enemy2.moveByAmount(5, 0);
			enemy3.moveByAmount(3, 0);
			enemy4.moveByAmount(8, 0);
			enemy5.moveByAmount(9, 0);
			
			enemy.moveToLocation(900, 0);
			enemy2.moveToLocation(900, 0);
			enemy3.moveToLocation(900, 0);
			enemy4.moveToLocation(900, 0);
			enemy5.moveToLocation(900, 0);
			
			
			

		
			
		}
		
		g2.setTransform(at);
	}

	public void run() {
	  	
		
		while(true) {
	  		// MAKE A CHANGE
			ground.moveByAmount(-2, 0);
			
			if (gameState == 1) {
				
				// Medium Enemy
				enemy.moveByAmount(-6, 0);
				
				if ( enemy.getX() + enemy.getWidth() <= DRAWING_WIDTH - 700 ) {
					enemy.moveByAmount(900, 0);
					enemy.moveToLocation(800, flappyBird.getY() - 30);
					score += 1;
					highScore += 1;
				}
				
				// Normal Enemy
				enemy2.moveByAmount(-5, 0);	
				if ( enemy2.getX() + enemy2.getWidth() <= DRAWING_WIDTH - 700 ) {
					enemy2.moveByAmount(900, 0);
					enemy2.moveToLocation(800, flappyBird.getY() - 85);
					score += 1;
					highScore += 1;
				}	
				
				// Slow Enemy
				enemy3.moveByAmount(-3, 0);	
				if ( enemy3.getX() + enemy3.getWidth() <= DRAWING_WIDTH - 700 ) {
					enemy3.moveByAmount(900, 30);
					enemy3.moveToLocation(800, flappyBird.getY() + 50);
					score += 1;
					highScore += 1;
				}	
				
				// Fast Enemy
				enemy4.moveByAmount(-7, 0);	
				if ( enemy4.getX() + enemy4.getWidth() <= DRAWING_WIDTH - 700 ) {
					enemy4.moveByAmount(900, 30);
					enemy4.moveToLocation(700, flappyBird.getY() - 100);
					score += 1;
					highScore += 1;
				}	
				
				enemy5.moveByAmount(-8, 0);	
				if ( enemy5.getX() + enemy5.getWidth() <= DRAWING_WIDTH - 700 ) {
					enemy5.moveByAmount(900, 30);
					enemy5.moveToLocation(700, flappyBird.getY() -10);
					score += 1;
					highScore += 1;
				}	
				
				if (flappyBird.collided == true) {
					
					gameState = 2;	

					repaint();		

				}
				
				if (gameState == 2) {			
					score = 0;
					
				}
				
			flappyBird.testCollision(enemy);
			flappyBird.testCollision(enemy2);
			flappyBird.testCollision(enemy3);
			flappyBird.testCollision(enemy4);
			flappyBird.testCollision(enemy5);
		
			}
			
			if ( ground.getX() + ground.getWidth() <= DRAWING_WIDTH ) {
				ground.moveByAmount(500, 0);
			}
			

			flappyBird.act(ground);
			//flappyBird.act(enemy);
			//flappyBird.act(enemy2);
	  		checkFlappyBird();
	  		// SHOW THE CHANGE
	  		repaint();
	  		
	  		// WAIT
	  		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	  		
	}
	
	public void checkFlappyBird() {
	  	int x = flappyBird.getX() + flappyBird.getWidth()/2;
	  	int y = flappyBird.getY() + flappyBird.getHeight()/2;
	  	if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)

	  		gameState = 2;
	  		repaint();
	  		
	  }

	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			// WALK LEFT
	  		flappyBird.walk(-1);
	  	} else if (e.getKeyCode() == KeyEvent.VK_D) {
	  		// WALK RIGHT
	  		flappyBird.walk(1);
	  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	  		// JUMP
	  		flappyBird.jump();
	  		
	  	} else if (e.getKeyCode() == KeyEvent.VK_S) {
	  		// JUMP
	  		flappyBird.fall();
	  		
	  	}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			flappyBird.walk(0);
	  	} else if (e.getKeyCode() == KeyEvent.VK_D) {
	  		flappyBird.walk(0);
	  	} else if (e.getKeyCode() == KeyEvent.VK_S) {
	  		flappyBird.jump();
	  	}
	}


	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		
		int keyPressed = e.getButton();

			gameState = 1;
			repaint();
			if (keyPressed == e.BUTTON1) {
				  // JUMP
				flappyBird.jump();
				
				if (gameState == 2)	{
					score = 0;
					gameState = 0;
					repaint();
				}
				
		}
	
	}
		

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
