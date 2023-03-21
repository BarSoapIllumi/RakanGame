package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class hut extends SuperObject{
	
	GamePanel gp;
	
	public hut(GamePanel gp) {
		
		name = "hut";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("hutInteractable.png"));
			uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
