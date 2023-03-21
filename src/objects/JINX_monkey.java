package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class JINX_monkey extends SuperObject{
	
	GamePanel gp;
	
	public JINX_monkey(GamePanel gp) {
		
		name = "monkey";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("JinxMonkey.png"));
			uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
