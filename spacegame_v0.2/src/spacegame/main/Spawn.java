package spacegame.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep >= 100){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy, handler));
			if(hud.getLevel()==2) handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));
			//if(hud.getLevel()==1) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH/2),r.nextInt(Game.HEIGHT),ID.SmartEnemy, handler));

			//if(hud.getLevel()==2) handler.addObject(new MediumEnemy((Game.WIDTH/2)-48, -120, ID.MediumEnemy, handler));
			
			if(hud.getLevel() > 5 && hud.getLevel()%2==0){
				
			
			
			if(hud.getLevel() % 5 == 0){
				//handler.addObject(new MediumEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.MediumEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH/2),r.nextInt(Game.HEIGHT),ID.SmartEnemy, handler));

				
				}//end of for loop (spawns as many repairs as the level #)
				
				
			}//end of if
		}//end of score keep if 
	}//end of tick
}//end of class
