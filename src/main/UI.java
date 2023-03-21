package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.awt.BasicStroke;
import java.awt.Color;

public class UI implements ActionListener {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial, SS_50; // Fonts
	Timer creditTimer = new Timer(25, this); // Credit scroll speed
	
	public String currentDialogue = ""; // Dialogue with NPCs
	public int commandNum = 0; // Menu selection
	int credY = 600; // Credits start y position
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial = (new Font("arial", Font.PLAIN, 20)); // Basic fonts
		SS_50 = (new Font("Segoe Script", Font.PLAIN, 50));
	}
	
	public void draw(Graphics2D g2) { // called through gamePanel
	
		this.g2 = g2; // to use g2 in other functions in UI
		g2.setFont(SS_50); // Set basic font
		
		// TITLE SCREEN
		if(gp.gameState == gp.titleState) { // In title page
			drawTitle(); // Call to draw title page
		}
		// CREDITS PAGE
		if(gp.gameState == gp.creditState) {
			creditTimer.start();
			drawCredits();
		}
		// PLAYSTATE
		if(gp.gameState == gp.playState) {
			// PlayState stuff
		}
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogue();
		}
		// GAME END
		if(gp.gameState == gp.endState) {
			drawEnd();
		}
	}
	
	public void drawCredits() { // Draw credit screen
		int x; // Declare x position variable
		int y = credY; // Declare another y position variable
		g2.setColor(new Color(228, 229, 255)); // Set text color
		g2.setFont(arial); // Set text font
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F)); // Set font type and size
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // Makes text prettier
		
		String text = "Credits\n\n\n"
						+ "Story\nAngela Zhou\nBrian Ye\nYuki Chen\n\n\n"
						+ "Art\nAngela Zhou\nYuki Chen\n\n\n"
						+ "References\ncazterjames\nYuki Chen\nArcane\n\n\n"
						+ "Music\nAngela Zhou\nFeihan Yu\nNicole Chen\n\n\n"
						+ "Sheet Music\nAngela Zhou\nPandaTooth\nXenodot\nRoyal Conservatory of Music Level 10 Etude\nPlagal\n\n\n"
						+ "Sound Effects\nAngela Zhou\n\n\n"
						+ "Game Programmer\nAngela Zhou\n\n\n"
						+ "Special thanks to:\nYilin Liao for inspiring my Java jounrney\n"
						+ "Yuki Chen, Nicole Chen, William Sui, John Lee, and Griffin Chen for illustration advice\n"
						+ "Monica Ye and Alexandra for being amazing piano teachers so I could create the music\n"
						+ "Riot Games for Arcane and character inspirations\n"
						+ "Moon Studios and Team Cherry for soundtrack inspirations\n"
						+ "Omocat for Omori and background inspirations\n"
						+ "And Lastly, RyiSnow for every Java tutorial you created on youtube and for being\nthe reason I was able to finish the game\n\n\n\n\n\n\n"
						+ "Thank you, player, for playing this game"; // Credits content
		
		for(String line : text.split("\n")) { // Line breaks
			x = getCenterX(line); // Calls on function to get x position for ... line
			g2.drawString(line, x, y += 20); // Write string
		}
	}

	public void drawTitle() { // Draw title screen
		
		// GAME TITLE
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F)); // Set text size
		String text = "Vi's Quest"; // Title name
		int x = gp.TileSize * 2; // Set text position
		int y = gp.TileSize * 5;
		
		// SHADOW
		g2.setColor(new Color(28, 16, 68, 100)); // Set shadow color
		g2.drawString(text, x+2, y+2);  // Write text
		
		// MAIN TEXT
		g2.setColor(new Color(28, 16, 68));
		g2.drawString(text, x, y);
		
		// MENU
		g2.setFont(arial); // Set font
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
		
		text = "new game";
		x = gp.TileSize * 3 + 24;
		y = gp.TileSize * 9 - gp.TileSize/2 - 8;
		g2.drawString(text, x, y);
		if(commandNum == 0) { // If player selects "new game" option
			g2.drawString(">", x - 20, y + 1); // Pointer
		}
		
		text = "load game";
		x = gp.TileSize * 6 + 24;
		y = gp.TileSize * 9 - gp.TileSize/2 - 8;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 20, y + 1);
		}
		
		text = "credits";
		x = gp.TileSize * 3 + 24;
		y = gp.TileSize * 10 - gp.TileSize/2 - 10;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - 20, y + 1);
		}
		
		text = "exit";
		x = gp.TileSize * 6 + 24;
		y = gp.TileSize * 10 - gp.TileSize/2 - 10;
		g2.drawString(text, x, y);
		if(commandNum == 3) {
			g2.drawString(">", x - 20, y + 1);
		}
	}

	private void drawDialogue() {
		
		// WINDOW SIZE
		int x = gp.TileSize/2, y = gp.TileSize * 8;
		int width = gp.screenWidth - gp.TileSize, height = gp.TileSize * 3 + gp.TileSize/2;
		
		drawWindow(x, y, width, height);
		
		g2.setFont(arial);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
		x += gp.TileSize/2;
		y += gp.TileSize;
		
		for(String line : currentDialogue.split("\n")) { // Line breaks
			g2.drawString(line, x, y);
			y += 40; // next line
		}
	}

	public void drawPauseScreen() {
		
		drawWindow(gp.TileSize/2, gp.TileSize/2, gp.screenWidth - gp.TileSize, gp.screenHeight - gp.TileSize);
		g2.setFont(arial);
		String text = "PAUSED";
		int x = getCenterX(text); 
		int y = gp.TileSize * 2;
		
		g2.drawString(text, x, y);	
	}
	
	public void drawEnd() {
		
		drawWindow(gp.TileSize/2, gp.TileSize/2, gp.screenWidth - gp.TileSize, gp.screenHeight - gp.TileSize);
		g2.setFont(arial);
		String text = "CONGRATULATIONS!";
		int x = getCenterX(text); 
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}

	public void drawWindow(int x, int y, int width, int height) {
		
		Color c = new Color(13, 14, 40, 220); // rbg (red/blue/green) color + opacity
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(228, 229, 255, 200);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+4, y+4, width-8, height-8, 25, 25); // Border
	}
	
	public int getCenterX(String text) { // get center x position
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // String length
		int x = gp.screenWidth/2 - length/2; // Calculate center x position for string
		
		return x; // Return x position
	}

	public void actionPerformed(ActionEvent e) { // For credits
		
		credY--; // Make the words all go higher
		if(credY < -1280) { // If all words disappear
			creditTimer.stop(); // stop timer
			gp.stopMusic(); // Stop current music(melancholy)
			gp.gameState = gp.titleState; // Change game states
		}
		drawCredits();
	}
}
