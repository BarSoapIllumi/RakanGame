package background;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import main.GamePanel;
	
public class BackgroundManager {
	
	GamePanel gp;
	public BufferedImage title, credits;
		
	public BackgroundManager(GamePanel gp) {
		this.gp = gp;
		getBackground();
	}
	
	private void getBackground() {
		try {
			title = ImageIO.read(getClass().getResourceAsStream("/background/title.png"));
			credits = ImageIO.read(getClass().getResourceAsStream("/background/credits page.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		if(gp.gameState == gp.titleState) {
	       image = title;
		}
		if(gp.gameState == gp.creditState) {
		       image = credits;
			}
		g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
	}
}
