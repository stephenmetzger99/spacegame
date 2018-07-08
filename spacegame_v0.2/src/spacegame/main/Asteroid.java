package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Asteroid extends GameObject {
	private Handler handler;
	private int size = (int)(Math.random() * 32 +8);
	
	
	public Asteroid(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x,  0,  Game.WIDTH - 32);
		y = Game.clamp((int)y,  0,  Game.HEIGHT - 60);
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillOval((int)x, (int)y, size, size);
		g.setColor(Color.gray);
		g.fillOval((int)x+4,(int) y+4, size, size);
		g.setColor(Color.gray);
		g.fillOval((int)x-6,(int) y+7, size, size);
		
		
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,size,size);
	}

}
