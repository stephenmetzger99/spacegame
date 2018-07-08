package spacegame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import spacegame.main.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game; 
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu){
			
			//play button
			if(mouseOver(mx, my, Game.WIDTH/2-64, Game.HEIGHT/2-50, 256, 64)){
				game.gameState = game.gameState.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2,Game.HEIGHT/2,ID.Player, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT/8),ID.SmartEnemy, handler));
				for (int i=0;i<10;i++){
					handler.addObject(new Asteroid(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Asteroid, handler));
					
				}
				for (int i=0;i<100;i++){
					handler.addObject(new Star(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Star, handler));
					
				}
			}
			
			
			//quit button
			if(mouseOver(mx, my, Game.WIDTH/2-64, Game.HEIGHT/2+90, 256, 64)){
				game.gameState = game.gameState.Game;
				System.exit(1);
			}
			
			
			
		}
		
		
		else if (game.gameState == STATE.End){
			if(mouseOver(mx, my, Game.WIDTH/2-84, Game.HEIGHT/2-60, 256, 64)){
				game.gameState = game.gameState.Menu;
				hud.setLevel(1);
        		hud.setScore(0);
			}
		}//end of else if
		
	}//end of mouse pressed
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			

			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,40);
			g.setFont(fnt);
	
			//title
			g.setColor(Color.white);
			g.drawString("Arena Commander", Game.WIDTH/2-150, Game.HEIGHT/2-250);
			
			//play button
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-64, Game.HEIGHT/2-60, 256, 64);
			g.setColor(Color.white);
			g.drawString("Play", Game.WIDTH/2+8, Game.HEIGHT/2-10);
			
			//settings button
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-64, Game.HEIGHT/2+20, 256, 64);
			g.setColor(Color.white);
			g.drawString("Settings", Game.WIDTH/2-18, Game.HEIGHT/2+60);
			
			//quit button
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-64, Game.HEIGHT/2+90, 256, 64);
			g.setColor(Color.white);
			g.drawString("Quit", Game.WIDTH/2+8, Game.HEIGHT/2+135);
			
		}else if(game.gameState == STATE.End){
			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,40);
			g.setFont(fnt);
	
			g.setColor(Color.white);
			g.drawString("Game Over", Game.WIDTH/2-100, Game.HEIGHT/2-250);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("You lost with a score of: "+HUD.score, Game.WIDTH/2-200, Game.HEIGHT/2-90);
			
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-84, Game.HEIGHT/2-60, 256, 64);
			g.setColor(Color.white);
			g.drawString("Try Again", Game.WIDTH/2-60, Game.HEIGHT/2-10);
		}
	}

}//end of class
