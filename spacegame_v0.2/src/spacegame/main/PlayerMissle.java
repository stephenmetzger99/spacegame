package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PlayerMissle extends GameObject{
	private Handler handler;
	private GameObject smartenemy;
	private int fuse = 0;
	Random r = new Random();
	
	
	public PlayerMissle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getID() != ID.SmartEnemy) smartenemy = handler.object.get(i);
		}
	} 
	/*
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.SmartEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}//end of collision if
			}//end of if
			
			
			
		}//end of for loop
	}//end of collision
		*/
		
	public void tick(){
		fuse++;
		if (fuse > 400) handler.removeObject(this); //detonate missle if it can't reach target

		x += velX;
		y += velY;
		
		float deltaX = x - smartenemy.getX();
		float deltaY = y - smartenemy.getY();
		
		float distance = (float) Math.sqrt((x - smartenemy.getX())*(x - smartenemy.getX()) + (y - smartenemy.getY())*(y - smartenemy.getY()));
		
		velX = (float)(-1 / distance * deltaX * 2);
		velY = (float) (-1 / distance * deltaY * 2);

		if(y >=Game.HEIGHT) handler.removeObject(this); //keeps within game border
		if(x >=Game.WIDTH || x <= 0) handler.removeObject(this);
		
		//handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.MAGENTA, 10, 10, 0.1f, handler));
		
		
		//collision();
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x,(int)y,8,8);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
		
	}
}//end of BasicEnemy Class
