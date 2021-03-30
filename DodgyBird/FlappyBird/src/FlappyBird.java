import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;


public class FlappyBird extends MovingImage {

	public double vX;

	public double vY;

	public boolean onASurface, collided;
	
	public FlappyBird(int x, int y) {

		super("flappybird1.png", x, y,70,45);
		//70,45
		vX = 0;
		vY = 0;
		onASurface = false;
		collided = false;

	}
	
	// METHODS
	
	public void walk(int dir) {
		// WALK!
		// moveByAmount(dir*5,0);
		vX = dir*7;
	
	}
	
	public void jump() {
		// JUMP!

		if (onASurface == false) {
			
			vY -= 11;
			
		}
	}
		
	public void fall() {
		// fall

		if (onASurface == false) {
				
			vY += 11;
				
			
		}
	}
	
	public void testCollision(MovingImage enemy) {
		
		if (this.getX() > enemy.getX() && this.getX() < enemy.getX() + 300 && this.getY() > enemy.getY() && this.getY() < enemy.getY() + 100) {
			
			System.out.println("Collided");
			collided = true;
			
		}
		
	}
	
	
	
	public void act(MovingImage platform) {
		// FALL!
		
		moveByAmount((int)vX, (int)vY);
		
		if (!platform.isPointInImage(getX()+getWidth(), getY()+5+getHeight())) { // not on the platform
			onASurface = false;
			vY += .4;

		} else {
			
			
			vY = 0;
			

		}
		
	}

	
	public double getvY() {
		
		return vY;
		
	
	}

}
