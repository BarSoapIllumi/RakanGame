package objects;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidArea_DX = 0;
	public int solidArea_DY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(image, screenX + gp.TileSize/4, screenY + gp.TileSize/4, gp.TileSize/2, gp.TileSize/2, null);
	}
	
	public void draw_hut(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(image, screenX + gp.TileSize, screenY + gp.TileSize, gp.TileSize, gp.TileSize, null);
	}
}
