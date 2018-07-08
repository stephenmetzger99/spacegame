package spacegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	
	private BufferedImage player_image;
	
	static boolean primaryFire = false;
	static boolean secondaryFire = false;
	static double rotation = 0;
	static int velocity = 2;
	
	private int gunsCooldown = 0;
	private int gunsRecharge = 0;
	
	
public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player_image = ss.crop(1, 1, 32, 32);
	}

	public void tick(){
		x += velX;
		y += velY;
		
	
		
		
		velocity = (int) Game.clamp(velocity,  0,  3);
		
		
		x = Game.clamp((int)x,  0,  Game.WIDTH - 32);
		y = Game.clamp((int)y,  0,  Game.HEIGHT - 60);
		
		if(MouseInput.getX() > x+400){
	    	   x+=velocity;
	       }
		if(MouseInput.getX() < x+400){
	    	   x-=velocity;
	       }
		
		if(MouseInput.getY() > y+130){
	    	   y+=velocity;
	       }
		if(MouseInput.getY() < y+130){
	    	   y-=velocity;
	       }
		if (MouseInput.getX() == x){
			x+=0;
		}
		if (MouseInput.getY() == y){
			y+=0;
		}
		  
		   
		collision();

		gunsCooldown++;
		gunsRecharge++;
		
		if(gunsRecharge > 100) HUD.AMMO+=.05;
		
		if(primaryFire == true && gunsCooldown > 10 && HUD.AMMO > 0){
			handler.addObject(new Bullet((int)x+10, (int)y, ID.Bullet, handler, (int)MouseInput.getX(), (int)MouseInput.getY()));
			HUD.AMMO-=2.5;
			gunsCooldown = 0;
			gunsRecharge = 0;
		}//check if the cooldown period has expired, then guns are ready and timer resets
		
		if(secondaryFire == true && gunsCooldown > 10 && HUD.AMMO > 0){
			handler.addObject(new PlayerMissle((int)x+10, (int)y, ID.PlayerMissle, handler));
			HUD.AMMO-=2.5;
			gunsCooldown = 0;
			gunsRecharge = 0;
			//handler.clearEnemies();
			//handler.addObject(new Player((int)x, (int)y, ID.Player, handler));
		}//check if the cooldown period has expired, then guns are ready and timer resets
		
	}//end of tick
	
	

	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.SmartEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 2;
					//handler.removeObject(tempObject);
				}//end of collision if
			}//end of if
			
			else if(tempObject.getID() == ID.MediumEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 3;
					
				}//end of collision if
			}//end of if
			
			else if(tempObject.getID() == ID.Asteroid){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 1;
					velX = velX*=-1;
					velY = velY*=-1; //bounces player off asteroid
				}//end of collision if
			}//end of if
			
			else if(tempObject.getID() == ID.Health){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH += 4;
					handler.removeObject(tempObject);
				}//end of collision if
			}//end of if
			
			else if(tempObject.getID() == ID.EnemyBoss){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 100;
					
				}//end of collision if
			}//end of if
			
			else if(tempObject.getID() == ID.EnemyBossBullet){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 10;
					handler.removeObject(tempObject);
				}//end of collision if
			}//end of if
			
		}//end of for loop
	}//end of collision()
	
	public void render(Graphics g){	
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at = AffineTransform.getTranslateInstance(x,y); 
		  
        double deltaX = (x - MouseInput.getX()+400);
        double deltaY =  (y - MouseInput.getY()+130);
        double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        
    
        
        double angle = -Math.atan2(deltaX, deltaY);
        
        
        
        //double angle = Math.toRadians(rotation);
        
        //if (MouseInput.getX() > x) angle = Math.toRadians(90);
        //else if (MouseInput.getX() < x) angle = Math.toRadians(-90);
		
		at.rotate(angle,25,22);//rotate about center of sprite
		g2d.drawImage((player_image.getScaledInstance(49, 49, 32)),  at, null);
		angle = 0; //reset angle to 0 after each calculation because rotate() is additive


		
		
		
		
		//Unit Circle -- debugging rotations. This section is temporary
		/*
		g.setColor(Color.blue);
		g.drawRect(0, (int)y+22, Game.WIDTH, 1);
		
		g.setColor(Color.red);
		g.drawRect((int)x+24, 0, 1, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawString("0, 2 PI", (int)x+150, (int)y+24);
		g.drawString("PI", (int)x-150, (int)y+24);
		g.drawString("PI/2", (int)x+24, (int)y-150);
		g.drawString("3PI/2", (int)x+24, (int)y+150);
		*/
		
		
	}//end of render
	


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,48,48);
		
	}
	
	
	
	

}
