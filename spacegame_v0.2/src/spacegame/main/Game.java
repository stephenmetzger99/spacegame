package spacegame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4606948098448081780L;
	
	public static final int WIDTH = 1200, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static int fps;
	public static BufferedImage sprite_sheet;
	//MouseInput mouse = new MouseInput();

	
	public enum STATE{
		Menu(),
		Game(),
		End();
	}
	
	public static STATE gameState = STATE.Menu;
	
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(new MouseInput(handler));
		
		new Window(WIDTH, HEIGHT, "Space Game!", this);
		
		ImageLoader loader = new ImageLoader();
		
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		spawner = new Spawn(handler, hud);
		
		r = new Random();
		
		if(gameState == STATE.Game){
			 this.setCursor( this.getToolkit().createCustomCursor(
	                 new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
	                 new Point(),
	                 null ) );
		}else{
			for(int i = 0; i < 10; i++){
	    		//handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.MenuParticle, handler));

			}
		}
		
	}//end of Game() 
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
                            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                fps = frames;
                frames = 0;
            }
        }
        stop();
	}
	
	private void tick(){
    	handler.tick();
    	

    	if(gameState == STATE.Game){
    		hud.tick();
        	spawner.tick();
        	//System.out.println(MouseInput.getX() + " " + MouseInput.getY()) ;
        	if(HUD.HEALTH <= 0){
        		HUD.HEALTH = 100;
        		HUD.AMMO = 100;
        		gameState = STATE.End;
        		handler.clearEnemies();

        		for(int i = 0; i < 10; i++){
    	    		handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.MenuParticle, handler));
    			}
				
			}
    	}else if(gameState == STATE.Menu || gameState == STATE.End){
    		
    		menu.tick();
    	}
    	
    	
    	
    }
    
	
	
    private void render(){
    	BufferStrategy bs = this.getBufferStrategy();
    	if(bs == null){
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	g.setColor(Color.black);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	
    	handler.render(g);
    	
    	if(gameState == STATE.Game){
    		hud.render(g);
    		g.setColor(Color.red);
			//g.drawOval((int)mouse.getPosition().getX(), (int)mouse.getPosition().getY(), 16, 16);
			//System.out.println(mouse.getPosition());
    	}else if(gameState == STATE.Menu || gameState == STATE.End){
    		menu.render(g);

    	}
    	
    	
    	g.dispose();
    	bs.show();
    } 
    
    public static float clamp(float var, float min, float max){
    	if(var >= max)
    		return var = max;
    	else if(var <= min)
    		return var = min;
    	else 
    		return var;
    }//end of clamp
    
	public static void main(String args[]){
		new Game();
	}
}
