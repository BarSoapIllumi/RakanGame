// Name: Angela Zhou
// Date: Wednesday, January 26, 2022
// File name: CPT!
// Description: This program is a runnable treasure hunting game with menu and credits. To play, use the up, down, left, right keys to move; z key to select, and esc key to pause. Extra: t key is a draw time debug key.

package main;

import javax.swing.JFrame; // Import java library window

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(); // Create new window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when "x" is clicked
		window.setResizable(false); // user cannot change size with drag
		window.setTitle("Where's Rakan sub1"); // game title
		
		GamePanel gp = new GamePanel(); // Create new "gamepanel" object
		window.add(gp);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true); // make window visible
		
		gp.setUpGame(); // Set up game method in gamepanel class
		gp.GameThreadS(); // Start game (loop) method in gamepanel class
	}

} 