package spacegame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
		
		public static float HEALTH = 100;
		private float greenValue = 255;
		private boolean death = false;
		public static int score = 0;
		private int level = 1;
		private float velocity = 0;
		static float AMMO = 100;
		
		
		public void tick(){
			
			HEALTH = Game.clamp(HEALTH, 0, 100);
			greenValue = Game.clamp(greenValue,  0, 255);
			AMMO = Game.clamp(AMMO, 0, 100);
			greenValue = HEALTH * 2;
			score++;
			
			
		}//end of tick
		
		public void render(Graphics g){
			g.setColor(Color.gray);
			g.fillRect(15,15,200,16);
			g.setColor(new Color(75, (int)greenValue, 0));
			g.fillRect(15,15,(int)HEALTH*2,16);
			g.setColor(Color.white);
			g.drawRect(15,15,200,16);
			
			g.drawString("FPS: "+ Game.fps, Game.WIDTH-64, Game.HEIGHT-64);
			g.drawString("vel: "+ Player.velocity, Game.WIDTH-64, Game.HEIGHT-48);
			
			g.drawString("Score: "+ score , 10, 64);
			g.drawString("Level: "+ level , 10, 96);
			//g.drawString("Speed: "+ velocity + " m/s" , 10, 128);
			g.drawString("Lasers: "+ (int)AMMO +"%", 10, 146);
			
			if (death == true){
				g.setColor(Color.red);
				Font fnt = new Font("arial",1,50);
				g.setFont(fnt);
				g.drawString("DEFEAT", Game.WIDTH / 2 , Game.HEIGHT /2 );
			}
		}//end of render
		
		public void setScore(int score){
			this.score = score;
		}
		
		public int getScore(){
			return score;
		}
		
		public int getLevel(){
			return level;
		}
		
		public void setLevel(int level){
			this.level = level;
		}

		

		
		
}//end of HUD class
