package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MediumEnemy extends GameObject{
	private Handler handler;
	
	public MediumEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 10;
		velY = 10;
		this.handler = handler;
	} 
	
	public void tick(){
		x += velX;
		y += velY;
		collision();
		if(y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 14, 14, 0.1f, handler));
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player || tempObject.getID() == ID.Asteroid){
				if(getBounds().intersects(tempObject.getBounds())){
					velX *= -1;
					velY *= -1;
				}//end of collision if
			}//end of if
			
			if(tempObject.getID() == ID.PlayerBullet || tempObject.getID() == ID.PlayerMissle){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}//end of collision if
			}//end of if
		}
	}//end of collision
	
	public void render(Graphics g){
		
		g.setColor(Color.blue);
		g.drawOval((int)x,(int)y,16,16);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,20,20);
		
	}
}//end of BasicEnemy Class
