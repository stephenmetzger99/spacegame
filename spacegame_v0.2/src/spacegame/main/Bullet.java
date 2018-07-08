package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	private Color col;
	
	public Bullet(float x, float y, ID id, Handler handler,double mx, double my) {
		super(x, y, id);
		this.handler = handler;
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		velX = (float) ((mx - x )/10);
		velY = (float) ((my - y )/10);
	}

	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					handler.removeObject(this);
					int rng = r.nextInt(4);
					if (rng == 1)handler.addObject(new Health((int)this.getX(),(int)this.getY(),ID.Health, handler));
				}//end of collision if
			}//end of if
			
			if(tempObject.getID() == ID.EnemyBoss){
				if(getBounds().intersects(tempObject.getBounds())){
					EnemyBoss.HEALTH-=1;
				
				}//end of collision if
			}//end of if
			
			
			
		}//end of for loop
	}//end of collision
	
	public void tick() {
		x += velX;
		y += velY;
		collision();
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(col);
		g.setColor(Color.yellow);
		g.fillOval((int)x,(int) y, 4, 4);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,4,4);
	}
	

}
