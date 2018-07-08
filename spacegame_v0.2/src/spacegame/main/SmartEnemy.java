package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{
	private Handler handler;
	private GameObject player;
	private BufferedImage enemy_image;
	
	//to do: private boolean pursuit;
	//ai check to see if the smart enemy should pursue or flee from player
	
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_image = ss.crop(2, 1, 32, 32);
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}//if our variable gets the id of player we set the game object to player
		
		
		
	} 
	
	public void tick(){
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));
		
		if(distance <= 120){
			x -=1;
			y -= 1;
		
		}else{
			velX = (float)(-1.0 / distance * diffX)*2;
			velY = (float) (-1.0 / distance * diffY)*2;
		}
		//handler.addObject(new Trail((int)x+16, (int)y+42, ID.Trail, Color.cyan, 4, 4, 0.1f, handler));
		//adds engine trail
		
	}
	
	
	
	
	public void render(Graphics g){
		g.drawImage(enemy_image.getScaledInstance(48, 48, 32),(int)x, (int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,48,48);
		
	}
}//end of BasicEnemy Class
