package spacegame.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void clearEnemies(){
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
				i--;
			
				removeObject(tempObject);
				
			}//end of if
		}

	 
	public void tick(){
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}//end of tick
	
	public void render(Graphics g){
		for(int i = 0; i < object.size();i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}//end of render
	
	public void addObject(GameObject object){
		this.object.add(object);
	}//end of add object
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}//end of remove object
	
}//end of handler class
