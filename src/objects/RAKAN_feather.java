package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class RAKAN_feather extends SuperObject{
	
	GamePanel gp;
	
	public RAKAN_feather(GamePanel gp) {
		
		name = "orange feather";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("rakanFeather.png"));
			uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
