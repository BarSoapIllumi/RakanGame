package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class door extends SuperObject{
	
	GamePanel gp;
	
	public door(GamePanel gp) {
		
		name = "door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("door.png"));
			uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
