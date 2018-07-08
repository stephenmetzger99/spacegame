package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Health extends GameObject {
	private Handler handler;
	
	public Health(int x, int y, ID id, Handler handler) {
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
		g.setColor(Color.green);
		g.fillOval((int)x,(int)y, 8, 8);
		
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,8,8);
	}

}
