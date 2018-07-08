package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MenuParticle extends GameObject{
	private Handler handler;
	Random r = new Random();
	private Color col;
	int dir = 0;
	private BufferedImage enemy_image;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		dir = r.nextInt(2);
		if(dir==0){
			velX = 10;
			velY = 2;
		}else if(dir ==1){
			velX = 2;
			velY = 10;
		}
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_image = ss.crop(2, 1, 32, 32);
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
	} 
	
	public void tick(){
		x += velX;
		y -= velY;
	
		if(y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16 ) velX *= -1;
		//handler.addObject(new Trail((int)x,(int) y, ID.Trail, col, 24, 24, 0.075f, handler));
	}
	
	
	public void render(Graphics g){
		
		g.drawImage(enemy_image.getScaledInstance(48, 48, 32),(int)x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}//end of BasicEnemy Class
