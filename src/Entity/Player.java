package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import tiles.TileManager;

public class Player extends	Entity{

	KeyHandler keyH;
	TileManager tManager;
	
	public final int screenX, screenY; // camera position on map
	
	public int hasMonkey = 1;
	public boolean hasRakan = false;
	public int hasXayah = 1;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.TileSize/2);
		screenY = gp.screenHeight/2 - (gp.TileSize/2);
	
		// HITBOX
		solidArea = new Rectangle(9, 16, 26, 34); // pass 4 hitbox parameters
		solidArea_DX = solidArea.x;
		solidArea_DY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.TileSize * 32;
		worldY= gp.TileSize * 28;
		direction = "stand";
	}
	
	public void getPlayerImage() { //get image info
		
		stand = setup("/yuki/yuki");
		up1 = setup("/yuki/yukiUp1");
		up2 = setup("/yuki/yukiUp2");
		front1 = setup("/yuki/yukiDown1");
		front2 = setup("/yuki/yukiDown2");
		left1 = setup("/yuki/yukiLeft1");
		left2 = setup("/yuki/yukiLeft2");
		right1 = setup("/yuki/yukiRight1");
		right2 = setup("/yuki/yukiRight2");
		
	}
	
	public void update() {
		

		if(keyH.shift == false) {
			speed = 3; // walk speed
		} else {
			speed = 6; // run speed
		}
		if(keyH.up == true) {
			direction = "up";
		} else if(keyH.down == true) {
			direction = "down";
		} else if(keyH.left == true) {
			direction = "left";
		} else if(keyH.right == true) {
			direction = "right";
		} else {
			direction = "stand";
		}
		
		// Check tile collision
		collisionOn = false;
		gp.collisionDetect.checkTile(this);
		
		// Check object collision
		int objIndex = gp.collisionDetect.checkObject(this, true);
		objInteraction(objIndex);
		
		// Check NPC collision
		int npcIndex = gp.collisionDetect.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
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
		if(speed == 3) {
			changeTime = 14;
		} else if(speed == 8) {
			changeTime = 5;
		}
		if(spriteCounter > changeTime) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if (spriteNum == 2) {
				spriteNum = 1;
		}
		spriteCounter = 0;
		}
	}
	
	public void objInteraction(int i) {
		
		if(i != 999) {
			
			String objName = gp.obj[i].name; // deletes object
			
			switch(objName) {
			case "hut":
				if(i == 3) {
					if(gp.keyH.select == true) {
						if(hasXayah == 2) {
							gp.tileM.mapNumber = 1;
							gp.obj[i] = null;
						} else if(hasXayah == 1){
							// Later
						}
					}
					gp.keyH.select = false;
				}
				if(i == 4) {
					if(gp.keyH.select == true) {
						if(hasXayah == 2) {
							gp.tileM.mapNumber = 1;
							gp.obj[i] = null;
						} else if(hasXayah == 1){
							// Later
						}
					}
					gp.keyH.select = false;
				}
				if(i == 5) {
					if(gp.keyH.select == true) {
						if(hasXayah == 2) {
							gp.tileM.mapNumber = 1;
							gp.obj[i] = null;
						} else if(hasXayah == 1) {
							// Later
						}
					}
					gp.keyH.select = false;
				}
				break;
			case "monkey":
				if(gp.keyH.select == true) {
					hasMonkey = 2;
					gp.obj[i] = null;
				}
				gp.keyH.select = false;
				break;
			case "orange feather":
				if(gp.keyH.select == true) {
					hasRakan = true;
					gp.obj[i] = null;
				}
				gp.keyH.select = false;
				break;
			case "purple feather":
				if(gp.keyH.select == true) {
					hasXayah = 2;
					gp.obj[i] = null;
				}
				gp.keyH.select = false;
				break;
			case "cupcake":
				if(gp.keyH.select == true) {
					gp.obj[i] = null;
				}
				gp.keyH.select = false;
				break;
			case "door":
				if(gp.keyH.select == true) {
					gp.obj[i] = null;
					gp.tileM.mapNumber = 0;
				}
				gp.keyH.select = false;
			}
		}
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.select == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.select = false;
	}
	
	public void draw(Graphics g2) {

		BufferedImage image = null;
		
		switch(direction) { // basically an if/if else statement
		case "stand":
			image = stand;
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
		g2.drawImage(image, screenX, screenY, null); 
	}
}