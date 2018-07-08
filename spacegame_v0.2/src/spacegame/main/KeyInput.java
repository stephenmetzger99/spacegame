package spacegame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private boolean[] keyDown2 = new boolean[2];
	private boolean[] keyDown3 = new boolean[2];
			
	public KeyInput(Handler handler){
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown2[0] = false;
		keyDown2[1] = false;
		keyDown3[0] = false;
		keyDown3[1] = false;

	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//key events for player 
				
				if(key == KeyEvent.VK_W) { 
					tempObject.setVelY(-5);
					Player.velocity++;
					keyDown[0] = true;
					}
				if(key == KeyEvent.VK_S) { 
					tempObject.setVelY(5);
					Player.velocity--;
					keyDown[1] = true;
					}
				if(key == KeyEvent.VK_D) { 
					tempObject.setVelX(5);	
					keyDown[2] = true;
					}
				if(key == KeyEvent.VK_A) { 
					tempObject.setVelX(-5);	
					keyDown[3] = true;
					}
				if(key == KeyEvent.VK_SPACE) { 
					//Player.primaryFire = true;	
					keyDown2[0] = true;
					}
				if(key == KeyEvent.VK_F) { 
					Player.secondaryFire = true;	
					keyDown2[1] = true;
					}
				
				if(key == KeyEvent.VK_Q) { 
					Player.rotation-=10;	
					keyDown2[0] = true;
					}
				
				if(key == KeyEvent.VK_E) { 
					Player.rotation+=10;	
					keyDown2[1] = true;
					}

			}
			
			
		}//end of for loop
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}//end of key pressed
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//key events for player 
				
				if(key == KeyEvent.VK_W) keyDown[0] = false;
				if(key == KeyEvent.VK_S) keyDown[1] = false;
				if(key == KeyEvent.VK_D) keyDown[2] = false;
				if(key == KeyEvent.VK_A) keyDown[3] = false;
				if(key == KeyEvent.VK_SPACE) {
					Player.primaryFire = false;	
					keyDown2[0] = false;
					}
				if(key == KeyEvent.VK_F) { 
					Player.secondaryFire = false;	
					keyDown2[1] = false;
					}
				if(key == KeyEvent.VK_Q) { 
					
					keyDown2[0] = false;;
					}
				
				if(key == KeyEvent.VK_E) { 
					
					keyDown2[1] = false;
					}

				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}
			
			
		}//end of for loop
	}
	
	}
