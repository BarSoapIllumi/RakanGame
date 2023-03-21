package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class XAYAH_feather extends SuperObject{
	
	GamePanel gp;
	
	public XAYAH_feather(GamePanel gp) {
		
		name = "purple feather";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("XayahFeather.png"));
			uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}