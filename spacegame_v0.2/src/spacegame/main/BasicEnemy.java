package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{
	private Handler handler;
	private int dir;
	Random r = new Random();
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		dir = r.nextInt(4);
		if(dir==0){
			velX = 2;
			velY = 2;
		}else if(dir ==1){
			velX = -2;
			velY = 2;
		}else if(dir ==2){
			velX = 2;
			velY = -2;
		}else if(dir == 3){
			velX = -2;
			velY = -2;
		}
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
		
		
	public void tick(){
		
		collision();
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 10, 10, 0.1f, handler));
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.red);
		g.drawOval((int)x,(int)y,16,16);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
		
	}
}//end of BasicEnemy Class
