package spacegame.main;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	
	public static double mx;
	public static double my;
	
			
	public MouseInput(Handler handler){
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		 mx = (double)(e.getX());
		 my = (double)(e.getY());
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				handler.addObject(new Bullet((int)tempObject.getX()+12,(int) tempObject.getY()+24, ID.Bullet, handler, mx, my));
				
			}
		}
	}
	
	public void movement(){
		
		
		for(int i=0;i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//key events for player 
				tempObject.setVelY(-5);
				tempObject.setVelY(5);
				tempObject.setVelX(5);
				tempObject.setVelX(-5);
			
				

			}
			
			
		}//end of for loop
	}
	
	public static double getX(){
		 return MouseInfo.getPointerInfo().getLocation().getX();
	    
	}
	public static double getY(){
		 return MouseInfo.getPointerInfo().getLocation().getY();
	      
	}
	
}