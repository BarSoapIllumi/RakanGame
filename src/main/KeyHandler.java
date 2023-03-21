package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean up, down, left, right, shift, select; // Key events
	public boolean debug = true; // Debug option
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void keyTyped(KeyEvent e) { // Necessary for keylistener
	}

	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); 
		// TITLE STATE/MENU
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_UP) { // If up arrow key pressed
				if(gp.ui.commandNum == 2) { // From "credits to "new game"
					gp.ui.commandNum = 0;
				}
				if(gp.ui.commandNum == 3) { // From "exit" to "load game"
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				if(gp.ui.commandNum == 0) {
					gp.ui.commandNum = 2;
				}
				if(gp.ui.commandNum == 1) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_LEFT) {
				if(gp.ui.commandNum == 1) {
					gp.ui.commandNum = 0;
				}
				if(gp.ui.commandNum == 3) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_RIGHT) {
				if(gp.ui.commandNum == 0) {
					gp.ui.commandNum = 1;
				}
				if(gp.ui.commandNum == 2) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_Z) {
				switch(gp.ui.commandNum) {
					case 0: gp.gameState = gp.playState; gp.stopMusic(); gp.playMusic(2); break; // New game
					case 1: break; // add later 
					case 2: gp.gameState = gp.creditState; gp.stopMusic(); gp.playMusic(1); break; // Credits
					case 3: System.exit(0); // Exit program
				}
			}
		}
		// CREDIT PAGE
		if(gp.gameState == gp.creditState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.playMusic(0);
			}
		}
		if(gp.gameState == gp.endState) {
			if(code == KeyEvent.VK_Z) {
				gp.gameState = gp.creditState;
				gp.stopMusic();
				gp.playMusic(1);
			}
			
		}
		// PLAY STATE
		if(gp.gameState == gp.playState) {

			if(code == KeyEvent.VK_UP) { // if up arrow key pressed
				up = true; // Character goes up
			}
			if(code == KeyEvent.VK_DOWN) {
				down = true;
			}
			if(code == KeyEvent.VK_LEFT) {
				left = true;
			}
			if(code == KeyEvent.VK_RIGHT) {
				right = true;
			}
			if(code == KeyEvent.VK_SHIFT) {
				shift = true;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.pauseState; // Change to pause state
				
			}
			if(code == KeyEvent.VK_Z) {
				select = true;
			}
			
			// DEBUG
			if(code == KeyEvent.VK_T) {
				if(debug == true) {
					debug = false;
				} 
				else if(debug == false) {
					debug = true;
				}
			}
		}
		// in pause state
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState; // change from pause state to play state
			}
		}
		// in dialogue state
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_Z) {
				gp.gameState = gp.playState;
			}
			
		}
	}

	public void keyReleased(KeyEvent e) { // When key no longer pressed
	
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			up = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			down = false;
		}
		if(code == KeyEvent.VK_LEFT) {
			left = false;
		}
		if(code == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			shift = false;
		}
		if(code == KeyEvent.VK_Z) {
			select = false;
		}
	}

}