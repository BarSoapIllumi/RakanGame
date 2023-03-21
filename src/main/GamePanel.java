package main;

// GAMEPANEL
import java.awt.Dimension; // Java library dimensions
import java.awt.Color; // Import colors
import javax.swing.JPanel; 

// PACKAGES 
import Entity.Entity; // Import classes from other packages
import Entity.Player;
import background.BackgroundManager;
import objects.SuperObject;
import tiles.TileManager;

// GRAPHICS
import java.awt.Graphics; //to draw on the screen
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int orgTileSize = 32; //32x32 tiles
	final double scale = 1.5; //32*2 size
	
	public final int TileSize = (int) (orgTileSize * scale); //tile size (48), public = access from other packages
	public final int ScreenCol = 16, ScreenRow = 12; // 16 horizontal, 12 vertical
	public final int screenWidth = TileSize * ScreenCol; // 768
	public final int screenHeight = TileSize * ScreenRow; // 576
	
	// WORLD SETTINGS
	public final int WorldCol = 66, WorldRow = 60; // World/map dimensions, relative to map file size
	
	int FPS = 60; // Frame rate
	
	// OBJECT DECLARATIONS
	// SYSTEM
	public TileManager tileM = new TileManager(this); //declare tilemanager
	public KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	public CollisionDetection collisionDetect = new CollisionDetection(this);
	public Player player = new Player(this, keyH);
	public AssetSetter aSet = new AssetSetter(this);
	public BackgroundManager bManage = new BackgroundManager(this);
	public UI ui = new UI(this);
	Thread gameThread; // good for repetition, requires runnable
	
	// ENTITIES AND OBJECTS
	public String playerName = "yuki";
	public Entity npc[] = new Entity[10]; // New npc array 
	public SuperObject obj[] = new SuperObject[10]; // object slots
	
	// GAME STATES
	public int gameState; // Variable to determine state of the game
	public final int titleState = 0; // This makes it easier when the values change later
	public final int creditState = 1;
	public final int playState = 2;
	public final int pauseState = 3;
	public final int dialogueState = 4;
	public final int endState = 5;
	
	public GamePanel() { //panel settings
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Screen/window dimensions
		this.setBackground(Color.black); // Background filled color
		this.setDoubleBuffered(true); // Program can do multiple things at once
		this.addKeyListener(keyH); // Listen to key input, must have runnable
		this.setFocusable(true); // Window can be focused, unfocused
	}
	
	public void setUpGame() { // Default game 
		
		aSet.setOBJ(); // Set up objects in AssetSetter
		aSet.setNPC(); // Set up npcs in AssetSetter
		gameState = titleState; // Set default game state
		playMusic(0); // Play "oriGood" music
	}

	public void GameThreadS() { // Runnable (loop)
		
		gameThread = new Thread(this); // new loop
		gameThread.start(); // start loop
	}
	
	public void run() { // Run program (required in "runnable")
		
		double drawInterval = 1000000000/FPS; // 0.016666 seconds for 60fps, time between draw
		double nextDrawTime = System.nanoTime() + drawInterval; // fix drawing time, frame rate stays at 60
		
		while(gameThread != null) { // While gameThread exists, game still running
			
			update(); // UPDATE 1: update info like character position
			repaint(); // UPDATE 2: draw screen with updated info
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; // Wait time
				
				if(remainingTime < 0) { // if update -> repaint took longer than... 
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime); // Stop program for...
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void update() { // Update the program
		
		if(gameState == playState) { // While the game is playing
			player.update(); // Update player
			//NPC
			for(int i = 0; i < npc.length; i++) { // Update ___ npc
				if(npc[i] != null) { // If NPC__ exists
					npc[i].update(); // Update NPC
				}
			}
		}
		if(gameState == pauseState) { // While game is paused
			// menu
		}
	}
	public void paintComponent(Graphics g) { // Paint stuff
		
		super.paintComponent(g); //use component in JPanel = use graphics g
		
		Graphics2D g2 = (Graphics2D)g; //2D extends class (more control geo, coor, color, layout)
		
		// DEGUG
		long drawStart = 0;
		if(keyH.debug == true) { // "t"
			drawStart = System.nanoTime(); // Time this method starts at
		}
		
		// TITLE SCREEN
		if(gameState == titleState) { // While in title page
			bManage.draw(g2); // Draw background
			ui.draw(g2); // Draw strings
		}
		else if (gameState == creditState) { // In credit page
			bManage.draw(g2);
			ui.draw(g2);
		}
		
		// OTHER STUFF
		else { // In play state, etc.
			// TILES
			tileM.draw(g2); // draws background before player
			
			// OBJECTS
			for(int i = 0; i < obj.length; i++) { // Scan through object array
				if(obj[i] != null) { // check if object exists
					if(i == 3 || i == 4 || i == 5) { // If the object is "hut", sizing difference
						obj[i].draw_hut(g2, this); // Call drawing function in superobj
					} if(i == 0 || i == 1 || i == 2) {
						obj[i].draw(g2, this);
					} if(i == 6 && tileM.mapNumber == 1) { // If object is a door and the map has changed (not yet implemented)
						obj[6].draw(g2, this);
					} 
				}
			}
			
			// NPCS
			for(int i = 0; i < npc.length; i++) { // Scan through npc array
				if(npc[i] != null) { // Check if NPC exists
					npc[i].draw(g2); // Draw npc with entity function
				}
			}
			
			// PLAYER
			player.draw(g2); // Draw player
			
			// UI
			ui.draw(g2); // Draw strings (if necessary)
			
		}
		
		// DEBUG CONT.
		if(keyH.debug == false) {
			long drawEnd = System.nanoTime(); // Get current system time
			long passed = drawEnd - drawStart; // Time it took to draw everything
			g2.setColor(Color.white);
			g2.setFont(ui.arial);
			g2.drawString("draw time: " + passed, 10, 300); // Display draw time
		}	
		
		g2.dispose(); // dispose graphic context, releases system resources/save memory
	}
	
	public void playMusic(int i) { // Play music, i is refered to in Sound class
		
		sound.setFile(i); // Set audio file
		sound.play(); // Play sound
		sound.loop(); // Loop music
		
	}
	public void stopMusic() {
		sound.stop(); // Stop playing music
	}
	public void soundEffect(int i) { // Play sound effect, i is from Sound class
		sound.setFile(i);
		sound.play(); // There is no loop
	}
}