package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaledImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // Blank canvas
		Graphics2D g2 = scaledImage.createGraphics(); // g2 drawing saved in scaledImage
		g2.drawImage(original, 0, 0, width, height, null); // g2 will be drawed with sclaed image
		g2.dispose();
		
		return scaledImage;
	}
	
}
