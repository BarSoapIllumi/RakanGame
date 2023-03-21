package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Entity {

	GamePanel gp;
	
	public int worldX, worldY, speed; // player position on world map
	
	//SPRITE 
	public BufferedImage stand, stand1, stand2, up1, up2, front1, front2, left1, left2, right1, right2; // buffered = access to image
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int changeTime;
	public boolean standing;
	
	//PLAYER HITBOX
	public Rectangle solidArea; // store rectangle data
	public int solidArea_DX, solidArea_DY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	String dialogue[] = new String[20]; // dialogue information 
	int dialogueIndex = 3; // dialogue player is on
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void speak() {
		switch(gp.player.direction) {
		case "up":
			direction = "stand";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.collisionDetect.checkTile(this); // Check for NPC class
		gp.collisionDetect.checkPlayer(this);
		
		// If collision is false, player can move
		if(collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		spriteCounter++;
		changeTime = 35;
		if(spriteCounter > changeTime) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if (spriteNum == 2) {
				spriteNum = 1;
			}
		spriteCounter = 0;
		}
	}
	
	public void draw(Graphics g2) {
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.TileSize*2 > gp.player.worldX - gp.player.screenX && 
           worldX - gp.TileSize*2 < gp.player.worldX + gp.player.screenX &&
           worldY + gp.TileSize*2 > gp.player.worldY - gp.player.screenY &&
           worldY - gp.TileSize*2 < gp.player.worldY + gp.player.screenY) { //only draw npc when in screen
        
        switch(direction) { // animations
        case "stand":
        	if(spriteNum == 1) {
				image = stand1;
			}
			if(spriteNum == 2) {
				image = stand2;
			}
			break;
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = front1;
			}
			if(spriteNum == 2) {
				image = front2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
        }
        
        g2.drawImage(image, screenX + gp.TileSize, screenY + gp.TileSize, gp.TileSize, gp.TileSize, null);
	}
	
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaledImage(image, gp.TileSize, gp.TileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
