package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	private Handler handler;
	
	Random r = new Random();
	
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = r.nextInt((5 - -5) + -5);
		velY = 10;
		this.handler = handler;
	} 
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 10;
				}//end of collision if
			}//end of if
		}
	}//end of collision
		
		
	public void tick(){
		
		collision();
		x += velX;
		y += velY;
		

		if(y >=Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 10, 10, 0.1f, handler));
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.red);
		g.drawOval((int)x,(int)y,8,8);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
		
	}
}//end of BasicEnemy Class
