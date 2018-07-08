package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBoss extends GameObject{
	private Handler handler;
	private int timer = 130;
	static int HEALTH = 500;
	private int timer2 = 10;
	Random r = new Random();
	private BufferedImage enemy_boss_image;
	private Color col;
	
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = 1;
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		enemy_boss_image = ss.crop(5, 2, 128, 32);
		col = new Color(255,160,0);
		HEALTH = 500;
	} 
	

		
	public void tick(){
		
		//collision();
		x += velX;
		y += velY;
		if(timer <=0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		
		if(timer2 <= 0){
			if(velX == 0) velX = 2;
			int spawn = r.nextInt(20);
			int spawn2 = r.nextInt(1000);
			if(spawn == 0) {
				handler.addObject(new EnemyBossBullet((int)x+128, (int)y+120, ID.EnemyBossBullet, handler));
				handler.addObject(new EnemyBossBullet((int)x+256, (int)y+120, ID.EnemyBossBullet, handler));
			}
			
			if(spawn2 == 1) {
				handler.addObject(new SmartEnemy((int)x+128, (int)y+120, ID.SmartEnemy, handler));
				
			}
		}
		
		if(x <= 0 || x >= Game.WIDTH - 512 ) velX *= -1;
		//handler.addObject(new Trail((int)x+90 , (int)y+64, ID.Trail, col, 10, 10, 0.05f, handler));
		if(HEALTH <= 0){
			handler.removeObject(this);
		}
	}
	
	public void render(Graphics g){
		
		g.drawImage(enemy_boss_image.getScaledInstance(512, 128, 128),(int)x, (int) y, null);
		
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH-1000,Game.HEIGHT-64,500,16);
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH-1000,Game.HEIGHT-64,HEALTH,16);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH-1000,Game.HEIGHT-64,500,16);

	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,512,128);
		
	}
}//end of BasicEnemy Class
